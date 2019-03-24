package com.ylzs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ylzs.core.mapper.UserReportMapper;
import com.ylzs.core.model.UserReport;


@Service
public class UserReportService {
	@Autowired
	private UserReportMapper userReportMapper;

	public void insert(UserReport userReport) {
		userReportMapper.insertSelective(userReport);
	}

}
