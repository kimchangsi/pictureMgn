package com.yi.controller;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yi.domain.PersonalData;
import com.yi.persistence.PersonalDataDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class PersonalDataTest {

	@Autowired
	PersonalDataDao dao;

	@Test
	public void testCreate() throws Exception {
		Date date = new Date();
		PersonalData data = new PersonalData("aaaa", date,
				"/2019/09/23/s_9fe9c6ca-3c3b-41bd-9923-f4534cc44571_book5.jpg");
		dao.insertData(data);
	}
}
