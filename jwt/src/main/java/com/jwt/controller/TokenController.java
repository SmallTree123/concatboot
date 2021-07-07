package com.jwt.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jwt.config.Token;
import com.jwt.entity.User;
import com.jwt.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/token")
public class TokenController {

    private final UserService userService;
    private final RedisTemplate redisTemplate;

    // 拦截器直接放行，返回Token
    @PostMapping("/login/{username}/{password}")
    public Map login (@PathVariable("username") String username,@PathVariable("password") String password){
        User user = userService.checkUser(username,password);
        Map map = new HashMap<>();
        if (user != null){
            String token = getToken(username, password);
            map.put("msg","登陆成功");
            map.put("token",token);
            return map;

        }else {
            map.put("msg","登陆失败,请查看该用户是否已经注册！！！");
        }
        return map;
    }

    @RequestMapping(value = "show")
    @Transactional
    public Map getShow(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) throws InterruptedException {
        String token = authHeader.substring(7);
        DecodedJWT jwt = JWT.decode(token);
        String username = jwt.getClaim("username").asString();
        String password = jwt.getClaim("password").asString();
        Object o = redisTemplate.opsForValue().get(username);
        Object o2 = redisTemplate.opsForValue().get(password);
        Map map = new HashMap<>();
        if (ObjectUtils.isEmpty(o)){
            map.put("error","token已经过期");
            return map;
        }
        if (ObjectUtils.isEmpty(o2)){
            map.put("error","请勿重复请求");
            return map;
        }
        map.put("token","处理成功---");
        //模拟处理业务
        System.out.println("正在处理请求-------");
        Thread.sleep(2000);

        redisTemplate.delete(password);
        return map;
    }


    // 根据用户名和密码生成一个token
    public String getToken(String username,String password){
        //过期时间  为1分钟
        Date date = new Date(System.currentTimeMillis() + 10*1000);
        //私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(Token.TOKEN_SECRET);
        Map map = new HashMap<>();
        String token = JWT.create().withClaim("username", username)
                .withClaim("password", password).withExpiresAt(date).sign(algorithm);
        //该token是看设置redis过期时间
        redisTemplate.opsForValue().set(username,token,1L,TimeUnit.MINUTES);
        //该token是为了设计接口幂等性
        redisTemplate.opsForValue().set(password,token);
        return token;
    }
}
