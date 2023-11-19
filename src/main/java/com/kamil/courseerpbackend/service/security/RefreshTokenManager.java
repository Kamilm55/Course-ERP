package com.kamil.courseerpbackend.service.security;

import com.kamil.courseerpbackend.model.dto.RefreshTokenDto;
import com.kamil.courseerpbackend.model.entity.User;
import com.kamil.courseerpbackend.model.properties.security.SecurityProperties;
import com.kamil.courseerpbackend.service.base.TokenGenerator;
import com.kamil.courseerpbackend.service.base.TokenReader;
import com.kamil.courseerpbackend.util.PublicPrivateKeyUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.kamil.courseerpbackend.constants.TokenConstants.EMAIL_KEY;
import static com.kamil.courseerpbackend.constants.TokenConstants.REFRESH_TOKEN_TYPE;

@Component
@Slf4j
@RequiredArgsConstructor
public class RefreshTokenManager implements TokenGenerator<RefreshTokenDto> ,TokenReader<Claims>{

    private final SecurityProperties securityProperties;
    @Override
    public String generate(RefreshTokenDto obj) {
        User user = obj.getUser();

        Claims claims = Jwts.claims();
        claims.put(EMAIL_KEY,user.getEmail() );
        claims.put("type" , REFRESH_TOKEN_TYPE);

        Date now = new Date();
        Date expDate = new Date(now.getTime() + securityProperties.getJwt().getRefreshTokenValidityTime(obj.isRememberMe()) );

        return Jwts.builder()
                .addClaims(claims)
                .setExpiration(expDate)
                .setIssuedAt(now)
                .setSubject(String.valueOf(user.getId()))
                .signWith(PublicPrivateKeyUtil.getPrivateKey() , SignatureAlgorithm.RS256)
                .compact();
    }


    @Override
    public Claims read(String token) {

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(PublicPrivateKeyUtil.getPublicKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        if(!REFRESH_TOKEN_TYPE.equals(claims.get("type"))){
            //refactorThis: custom exception
            throw new RuntimeException("This is not refresh token");
        }

        return claims;
    }
}
