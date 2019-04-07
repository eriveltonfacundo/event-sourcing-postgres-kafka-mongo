package com.invillia.challenge.comand.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;

@Component
public class TokenAuthenticationService {
    private static final Logger logger = LoggerFactory.getLogger(TokenAuthenticationService.class);
    private static String header;
    private static String prefix;
    private static int expiration;
    private static String secret;

    static void addAuthentication(HttpServletResponse response, String username) {
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        response.addHeader(header, prefix + JWT);
    }

    static Authentication getAuthentication(HttpServletRequest request) {
        try {
            String token = request.getHeader(header);
            if (token != null) {
                Claims claimsJws = Jwts.parser()
                        .setSigningKey(secret)
                        .parseClaimsJws(token.replace(prefix, "")).getBody();
                if (claimsJws.getExpiration().before(new Date()))
                    return null;

                String user = claimsJws.getSubject();
                if (user != null)
                    return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Value("${security.jwt.header:Authorization}")
    public void setHeader(String header) {
        TokenAuthenticationService.header = header;
    }

    @Value("${security.jwt.prefix:Bearer }")
    public void setPrefix(String prefix) {
        TokenAuthenticationService.prefix = prefix;
    }

    @Value("${security.jwt.expiration:#{240*600*600}}")
    public void setExpiration(int expiration) {
        TokenAuthenticationService.expiration = expiration;
    }

    @Value("${security.jwt.secret:JwtSecretKey}")
    public void setSecret(String secret) {
        TokenAuthenticationService.secret = secret;
    }
}