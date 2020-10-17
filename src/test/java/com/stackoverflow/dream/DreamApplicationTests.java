package com.stackoverflow.dream;

import com.alibaba.fastjson.JSONObject;
import com.stackoverflow.dream.dao.UserMapper;
import com.stackoverflow.dream.pojo.LoginTicket;
import com.stackoverflow.dream.util.MallUtils;
import com.stackoverflow.dream.util.MmMallConstants;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DreamApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void testUserMapper(){
		int res = userMapper.checkUserName("geely");
		Assert.assertEquals(res,1);
	}


	@Test
	public void testRedis(){
		// 生成登录凭证
		LoginTicket loginTicket = new LoginTicket();
		loginTicket.setUserId(1);
		loginTicket.setTicket(MallUtils.generateUUID());
		loginTicket.setStatus(0);
		loginTicket.setExpired(new Date(System.currentTimeMillis() + 2 * 1000));
		redisTemplate.opsForValue().set("11112", JSONObject.toJSON(loginTicket));
		Object o = redisTemplate.opsForValue().get("11112");
		System.out.println(o);
	}

}
