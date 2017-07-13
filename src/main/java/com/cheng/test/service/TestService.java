package com.cheng.test.service;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cheng.test.domain.TestUser;
@Mapper
public interface TestService {
	
	public TestUser getUserById(@Param("id")Long id);
	
	public void update(TestUser testUser);
}
