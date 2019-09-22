package com.yi.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yi.domain.MemberPicture;
import com.yi.domain.PersonalData;
import com.yi.persistence.MemberPictureDao;
import com.yi.persistence.PersonalDataDao;
@Service
public class PictureServiceDaoImpl implements PictureServiceDao {
	
	@Autowired
	MemberPictureDao mDao;
	@Autowired
	PersonalDataDao pDao;
	
	@Override
	public int insertMember(MemberPicture member) {
		// TODO Auto-generated method stub
		return mDao.insertMember(member);
	}

	@Override
	public MemberPicture selectMember(MemberPicture member) {
		// TODO Auto-generated method stub
		return mDao.selectMember(member);
	}

	@Override
	public List<PersonalData> selectList(PersonalData data) {
		// TODO Auto-generated method stub
		return pDao.selectList(data);
	}

	@Override
	public int insertData(PersonalData data) {
		// TODO Auto-generated method stub
		return pDao.insertData(data);
	}

	@Override
	public int deleteData(PersonalData data) {
		// TODO Auto-generated method stub
		return pDao.deleteData(data);
	}

	@Override
	public List<PersonalData> selectMyList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return pDao.selectMyList(map);
	}

}
