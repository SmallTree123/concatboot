package com.security.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Component
@RequestMapping(value = "/security")
public class SecurityController {
    /**
     * 首页
     */
    @RequestMapping("/")
    public String index (){
        return "home" ;
    }
    /**
     * 登录页
     */
    @RequestMapping("/userLogin")
    public String loginPage (){
        return "pages/login" ;
    }
    /**
     * page1 下页面
     */
    @PreAuthorize("hasAuthority('LEVEL1')")
    @RequestMapping("/page1/{pageName}")
    public String onePage (@PathVariable("pageName") String pageName){
        return "pages/page1/"+pageName ;
    }
    /**
     * page2 下页面
     */
    @PreAuthorize("hasAuthority('LEVEL2')")
    @RequestMapping("/page2/{pageName}")
    public String twoPage (@PathVariable("pageName") String pageName){
        return "pages/page2/"+pageName ;
    }
    /**
     * page3 下页面
     */
    @PreAuthorize("hasAuthority('LEVEL3')")
    @RequestMapping("/page3/{pageName}")
    public String threePage (@PathVariable("pageName") String pageName){
        return "pages/page3/"+pageName ;
    }

    public static void main(String[] args) {
        String s = "{\"bookingRule\":1,\"bookingTime\":{\"bookingDay\":2,\"refundDeadlineList\":[],\"timeInterval\":\"00:00:00-23:59:59\",\"beginTime\":1,\"endTime\":1,\"mealTypeList\":[{\"meal\":\"全天\",\"endHour\":0,\"time\":\"00:00-23:59\",\"firstHour\":0}]},\"isEnableRefundTime\":1,\"isBooking\":0,\"isCanOnlineOrder\":0,\"isPrint\":\"Y\",\"takeMealType\":[0,1,2],\"version\":71,\"bookingMealShowModel\":0}";

        Map map = JSON.parseObject(s, Map.class);
        map.put("isEnableRefundTime", 0);
        String string = JSON.toJSONString(map);
        System.out.println(string);
    }
}
