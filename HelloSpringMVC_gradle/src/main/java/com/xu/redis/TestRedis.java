package com.xu.redis;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class TestRedis {

	@Autowired
    private RedisCacheManager redisCacheManager;
	@RequestMapping("/testredis")
	public String testRedis() {
	        Object object = redisCacheManager.get("now");
	        if(object ==null){
	            //redisCacheManager.set("phil_token", HttpReqUtil.getAccessToken(WechatConfig.APP_ID, WechatConfig.APP_SECRET), 3600);
	        	redisCacheManager.set("now", new Date().toString());
	        	object = redisCacheManager.get("now");
	        	System.out.println(redisCacheManager.get("now"));
	        }else {
	        	redisCacheManager.set("now", new Date().toString());
	        	object = redisCacheManager.get("now");
	        	System.out.println(redisCacheManager.get("now"));
	        }
	        return "/testredis";
	    }
}