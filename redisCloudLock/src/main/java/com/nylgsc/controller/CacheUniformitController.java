package com.nylgsc.controller;


import com.nylgsc.entity.OrderInfo;
import com.nylgsc.service.CacheService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Component
@RequestMapping(value = "/cache")
@AllArgsConstructor
public class CacheUniformitController {

    private final CacheService cacheService;

    @GetMapping(value = "/getAllOrder")
    public List<OrderInfo> getAllOrder(){
        return cacheService.getAllOrder();
    }

    @PostMapping(value = "/saveOrder")
    public void saveOrder(){
        cacheService.saveOrder();
    }

    @GetMapping("/get")
    public Map<String,Object> two(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("sessionId",request.getSession().getId());
        map.put("message",request.getSession().getAttribute("message"));
        return map;
    }


}
