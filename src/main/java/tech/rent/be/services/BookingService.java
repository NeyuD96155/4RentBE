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

}
