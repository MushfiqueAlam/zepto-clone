package com.central_api.security;

import com.central_api.models.AppUser;
import com.central_api.util.DatabaseApi;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {
    @Value("${jwt.secret}")
    String jwtSecret;
    Long expirationMilis=100000L;
    @Autowired
    DatabaseApi databaseApi;

    public String generateJwtToken(String credentials){
        String token= Jwts.builder()
                .setSubject(credentials)
                .setExpiration(new Date(System.currentTimeMillis()+expirationMilis))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,jwtSecret)
                .compact();
        return token;
    }

    public String decodeJwtToken(String token){
        String credentials=Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return credentials;
    }

    public boolean isValidToken(String token){
        String credentials=this.decodeJwtToken(token);
        String userEmail=credentials.split(":")[0];
        String password=credentials.split(":")[1];
        AppUser user=databaseApi.getUserByEmail(userEmail);
        if(user!=null && user.getPassword().equals(password)){
            return true;
        }
        return false;
    }
}
