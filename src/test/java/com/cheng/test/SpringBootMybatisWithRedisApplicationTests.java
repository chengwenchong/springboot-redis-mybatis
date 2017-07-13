package com.cheng.test;

import static org.assertj.core.api.Java6Assertions.assertThat;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.cheng.test.domain.TestUser;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootMybatisWithRedisApplicationTests {
	@LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    public void test() {
        long id = 1;
        TestUser test = restTemplate.getForObject("http://localhost:" + port + "/testUser/"+id, TestUser.class);
        System.out.println(test.getPassword().equals("cwc004"));
        TestUser newTestUser = new TestUser();
        String newPassword = String.valueOf(new Random().nextLong());
        newTestUser.setLoginName("cwc008");
        newTestUser.setPassword(newPassword);
        restTemplate.put("http://localhost:" + port + "/testUser/"+id, newTestUser);
        TestUser testUser = restTemplate.getForObject("http://localhost:" + port + "/testUser/"+id, TestUser.class);
        System.out.println(testUser.getPassword().equals(newPassword));
    }
}
