package com.lmarques.mystocktrader.security.jwt;

import com.lmarques.mystocktrader.exception.InvalidJwtAuthenticactionException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class JwtTokenProvider {

    private SecretKey SECRET_KEY;
    private String base64Key;

    @Value("${security.jwt.token.expire-lenght:3600000}")
    private long validityInMilliseconds = 3600000; //1h

    @Autowired
    private UserDetailsService userDetailsService;

    @PostConstruct
    public void init(){
        //creates a spec-compliant secure-random key:
        SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String createToken(String login, List<String> roles){

        System.out.println(roles);

        return Jwts.builder()
                .setSubject(login)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validityInMilliseconds))
                .signWith(SECRET_KEY)
                .compact();
    }

    public Authentication getAuthentication(String token){
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
    }

    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public boolean validateToken(String token){
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);

            if(claimsJws.getBody().getExpiration().before(new Date())){
                return false;
            }
            return true;
        } catch (Exception e){
            throw new InvalidJwtAuthenticactionException("Expired or invalid token");
        }
    }
}
