package com.jwt.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@AllArgsConstructor
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String token = request.getHeader("Authorization").substring(7);
//        if (StringUtils.isEmpty(token)){
//            return false;
//        }
//        //从token中获取想要的信息
//        System.out.println("token: "+token);
//        DecodedJWT jwt = JWT.decode(token);
//        String username = jwt.getClaim("username").asString();
//        String password = jwt.getClaim("password").asString();
//        System.out.println("username: "+username);
//        System.out.println("password: "+password);
//        // 验证 token
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(Token.TOKEN_SECRET);
//            JWTVerifier verifier = JWT.require(algorithm).build();
//            DecodedJWT decodedJWT = verifier.verify(token);
//        } catch (Exception e) {
////            e.printStackTrace();
//            return true;
//        }
        return true;
    }
}
