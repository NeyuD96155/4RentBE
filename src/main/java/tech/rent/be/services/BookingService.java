package tech.rent.be.services;

import org.apache.catalina.User;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.rent.be.dto.BookingRequestDTO;
import tech.rent.be.dto.RealEstateDTO;
import tech.rent.be.entity.*;
import tech.rent.be.enums.BookingStatus;
import tech.rent.be.enums.EstateStatus;
import tech.rent.be.enums.PostStatus;
import tech.rent.be.enums.Role;
import tech.rent.be.exception.BadRequest;
import tech.rent.be.repository.*;
import tech.rent.be.utils.AccountUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class BookingService {
    public static Date convertDate(Date date, int hour, int minute, int second) {
        // Create a Calendar instance and set it to the input date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Set the fixed time
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);

        // Clear milliseconds to ensure consistent results
        calendar.set(Calendar.MILLISECOND, 0);

        // Get the updated Date from the Calendar
        return calendar.getTime();
    }
    public String getVnPay(BookingRequestDTO bookingRequestDTO) throws Exception {
        String amount = String.valueOf(bookingRequestDTO.getAmount());
        String price = String.valueOf(bookingRequestDTO.getPrice() * 100);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime createDate = LocalDateTime.now();
        String formattedCreateDate = createDate.format(formatter);
        Booking booking = new Booking();
        long realEstateId = bookingRequestDTO.getEstateId();
        RealEstate realEstate = realEstateService.finRealEstateById(realEstateId);
        booking.setBookingStatus(BookingStatus.ACTIVE);
        booking.setBookingDate(bookingRequestDTO.getDate());
        booking.setPrice(bookingRequestDTO.getPrice());
        booking.setAmount(bookingRequestDTO.getAmount());
        booking.setCheckIn(convertDate(bookingRequestDTO.getDate(), 14, 0, 0));
        Date checkOut = convertDate(bookingRequestDTO.getDate(), 12, 0, 0);
        Calendar c = Calendar.getInstance();
        c.setTime(checkOut);
        c.add(Calendar.DATE, bookingRequestDTO.getNumberOfDate());
        checkOut = c.getTime();
        booking.setCheckOut(checkOut);
        List<Booking> bookings = bookingRepository.findBookingsByRealEstate(realEstate);
        for (Booking booking1 : bookings) {
            if (realEstateService.checkIfBookingFromTo(booking1, booking.getCheckIn(), booking.getCheckOut())) {
                throw new BadRequest("Real Estate not available!");
            }
        }
        booking.setRealEstate(realEstate);
        booking.setUsers(accountUtils.getCurrentUser());
        booking.setStatus(false);
        booking.setAmount(bookingRequestDTO.getAmount());
        Booking newBooking = bookingRepository.save(booking);

        String tmnCode = "40A49IOQ";
        String secretKey = "AEEWMWRNYYHFFBUSVJDVOLNYMRXTBMTQ";
        String vnpUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
        String returnUrl = "http://4rent.tech/success";

        String currCode = "VND";
        Map<String, String> vnpParams = new TreeMap<>();
        vnpParams.put("vnp_Version", "2.1.0");
        vnpParams.put("vnp_Command", "pay");
        vnpParams.put("vnp_TmnCode", tmnCode);
        vnpParams.put("vnp_Locale", "vn");
        vnpParams.put("vnp_CurrCode", currCode);
        vnpParams.put("vnp_TxnRef", newBooking.getId().toString());
        vnpParams.put("vnp_OrderInfo", "Thanh toan cho ma GD: " + newBooking.getId());
        vnpParams.put("vnp_OrderType", "other");
        vnpParams.put("vnp_Amount", price);
        vnpParams.put("vnp_ReturnUrl", returnUrl);
        vnpParams.put("vnp_CreateDate", formattedCreateDate);
        vnpParams.put("vnp_IpAddr", "128.199.178.23");
        StringBuilder signDataBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : vnpParams.entrySet()) {
            signDataBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString()));
            signDataBuilder.append("=");
            signDataBuilder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString()));
            signDataBuilder.append("&");
        }
        signDataBuilder.deleteCharAt(signDataBuilder.length() - 1); // Remove last '&'

        String signData = signDataBuilder.toString();
        String signed = generateHMAC(secretKey, signData);

        vnpParams.put("vnp_SecureHash", signed);
        StringBuilder urlBuilder = new StringBuilder(vnpUrl);
        urlBuilder.append("?");
        for (Map.Entry<String, String> entry : vnpParams.entrySet()) {
            urlBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString()));
            urlBuilder.append("=");
            urlBuilder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString()));
            urlBuilder.append("&");
        }
        urlBuilder.deleteCharAt(urlBuilder.length() - 1); // Remove last '&'
        
        return urlBuilder.toString();    
    }
    public Booking updatePayment(long bookingId) {
        Booking booking = bookingRepository.findBookingById(bookingId);
        booking.setStatus(true);
        booking.setBookingStatus(BookingStatus.ACTIVE);

        Users renter = booking.getUsers();
        Users member = booking.getRealEstate().getUsers();
        Users admin = usersRepository.findUsersByRole(Role.ADMIN);

        Wallet renterWallet = renter.getWallet();
        Wallet memberWallet = member.getWallet();
        Wallet adminWallet = admin.getWallet();


        // create wallet
        if (renterWallet == null) {
            Wallet wallet = new Wallet();
            wallet.setUsers(renter);
            renter.setWallet(wallet);
            renterWallet = walletRepository.save(wallet);
        }

        if (memberWallet == null) {
            Wallet wallet = new Wallet();
            wallet.setUsers(member);
            member.setWallet(wallet);
            memberWallet = walletRepository.save(wallet);
        }

        if (adminWallet == null) {
            Wallet wallet = new Wallet();
            wallet.setUsers(admin);
            admin.setWallet(wallet);
            adminWallet = walletRepository.save(wallet);
        }


        // create transactions

        // add money renter
        Transactions transactions = new Transactions();
        transactions.setTo(renterWallet);
        transactions.setValue(booking.getPrice());
        transactionRepository.save(transactions);

        // tranfer money to admin wallet
        Transactions transactions2 = new Transactions();
        transactions2.setFrom(renterWallet);
        transactions2.setTo(adminWallet);
        transactions2.setValue(booking.getPrice());
        transactionRepository.save(transactions2);

        // tranfer money to member wallet
        Transactions transactions3 = new Transactions();
        transactions3.setFrom(adminWallet);
        transactions3.setTo(memberWallet);
        transactions3.setValue((float) (booking.getPrice() * 0.95));
        transactionRepository.save(transactions2);

        adminWallet.setBalance(adminWallet.getBalance() + booking.getPrice());
        walletRepository.save(adminWallet);

        return bookingRepository.save(booking);
    }
    private String generateHMAC(String secretKey, String signData) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac hmacSha512 = Mac.getInstance("HmacSHA512");
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
        hmacSha512.init(keySpec);
        byte[] hmacBytes = hmacSha512.doFinal(signData.getBytes(StandardCharsets.UTF_8));

        StringBuilder result = new StringBuilder();
        for (byte b : hmacBytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
    public Booking cancelBooking(long bookingId) {
        Booking booking = bookingRepository.findBookingById(bookingId);
        booking.setBookingStatus(BookingStatus.CANCEL);

        //refund
        Users renter = booking.getUsers();
        Users member = booking.getRealEstate().getUsers();
        Users admin = usersRepository.findUsersByRole(Role.ADMIN);
        Wallet renterWallet = renter.getWallet();
        Wallet memberWallet = member.getWallet();
        Wallet adminWallet = admin.getWallet();
        Date currentDate = new Date();
        long diff = booking.getBookingDate().getTime() - currentDate.getTime();//as given
        long days = TimeUnit.MILLISECONDS.toDays(diff);
        
        if (days > 7) {
            // refund full
            Transactions transactions = new Transactions();
            transactions.setFrom(adminWallet);
            transactions.setTo(renterWallet);
            transactions.setValue(booking.getPrice());
            transactionRepository.save(transactions);

            adminWallet.setBalance(adminWallet.getBalance() - booking.getPrice());
            renterWallet.setBalance(renterWallet.getBalance() + booking.getPrice());

            walletRepository.save(adminWallet);
            walletRepository.save(renterWallet);

        } else if (days > 2) {
            // refund 70%
            Transactions transactions = new Transactions();
            transactions.setFrom(adminWallet);
            transactions.setTo(renterWallet);
            transactions.setValue((float) (booking.getPrice() * 0.7));
            transactionRepository.save(transactions);

            Transactions transactions2 = new Transactions();
            transactions2.setFrom(adminWallet);
            transactions2.setTo(memberWallet);
            transactions2.setValue((float) (booking.getPrice() * 0.3));
            transactionRepository.save(transactions);

            adminWallet.setBalance(adminWallet.getBalance() - booking.getPrice());
            renterWallet.setBalance(renterWallet.getBalance() + (float) (booking.getPrice() * 0.7));
            memberWallet.setBalance(renterWallet.getBalance() + (float) (booking.getPrice() * 0.3));

            walletRepository.save(adminWallet);
            walletRepository.save(renterWallet);
            walletRepository.save(memberWallet);
        } else {
            // not refund
            throw new BadRequest("Không thể hủy!!!");
        }

        return bookingRepository.save(booking);
        }
        public Booking finishBooking(long bookingId) {
            Booking booking = bookingRepository.findBookingById(bookingId);
    //        booking.setBookingStatus(BookingStatus.FINISH);
    
            Users member = booking.getRealEstate().getUsers();
            Users admin = usersRepository.findUsersByRole(Role.ADMIN);
    
            Wallet memberWallet = member.getWallet();
            Wallet adminWallet = admin.getWallet();
            Date currentDate = new Date();
            Transactions transactions = new Transactions();
        transactions.setFrom(adminWallet);
        transactions.setTo(memberWallet);
        transactions.setValue((float) (booking.getPrice() * 0.95));
        transactionRepository.save(transactions);

        adminWallet.setBalance(adminWallet.getBalance() - (float) (booking.getPrice() * 0.95));
        memberWallet.setBalance(memberWallet.getBalance() + (float) (booking.getPrice() * 0.95));

        walletRepository.save(adminWallet);
        walletRepository.save(memberWallet);
        booking.setBookingStatus(BookingStatus.FINISH);
        return bookingRepository.save(booking);
    }
    }
