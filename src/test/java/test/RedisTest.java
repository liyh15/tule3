package test;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisTest {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-redis.xml");
		RedisTemplate redisTemplate = context.getBean("redisTemplate", RedisTemplate.class);
		redisTemplate.opsForValue().set("user2", "lyh");
		
	}
}
