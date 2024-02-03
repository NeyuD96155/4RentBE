package tech.rent.be.utils;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import tech.rent.be.entity.Users;

import java.util.Date;

@Component
public class TokenHandler {
    private final String SECRET_KEY = "6e14ca83-50ae-4a85-87de-f47e5ddc074a";
    //    1s => 1000ms
//    private final UUID EXPIRATION = 1 * 60 * 1000;
    private final long EXPIRATION = 1 * 24 * 60 * 60 * 1000;
                                        // ngày giờ phut giay micirogiay
    // create token (encode)
    public String generateToken(Users userSecurity) {
        Date now = new Date(); // get current time
        Date expirationDate = new Date(now.getTime() + EXPIRATION);

        String token = Jwts.builder()
                .setSubject(userSecurity.getUsername())
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
        return token;
    }


    // validate token
    // get info from token (decode)
    public String getInfoByToken(String token) throws ExpiredJwtException, MalformedJwtException {
        String username;
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        username = claims.getSubject();
        // xuống đc đây => token đúng
        return username;
    }

}
