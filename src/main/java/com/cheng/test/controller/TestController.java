package com.cheng.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cheng.test.domain.TestUser;
import com.cheng.test.service.TestService;

@RestController
@RequestMapping("/testUser")
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public TestUser getUserById(@PathVariable("id")Long id){
		return testService.getUserById(id);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public TestUser testPage(@PathVariable("id")Long id,@RequestBody TestUser newTestUser){
		TestUser user=testService.getUserById(id);
		user.setLoginName(newTestUser.getLoginName());
		user.setPassword(newTestUser.getPassword());
		testService.update(user);
		return user;
	}
}
