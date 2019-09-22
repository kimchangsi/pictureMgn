package com.yi.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yi.domain.PersonalData;
@Repository
public class PersonalDataDaoImpl implements PersonalDataDao {
	private static final String namespace = "com.yi.mapper.PersonalData";
	
	@Autowired
	SqlSession session;
	@Override
	public List<PersonalData> selectList(PersonalData data) {
		// TODO Auto-generated method stub
		return session.selectList(namespace+".selectList",data);
	}

	@Override
	public int insertData(PersonalData data) {
		// TODO Auto-generated method stub
		return session.insert(namespace+".insertData",data);
	}

	@Override
	public int deleteData(PersonalData data) {
		// TODO Auto-generated method stub
		return session.delete(namespace+".deleteData",data);
	}

	@Override
	public List<PersonalData> selectMyList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return session.selectList(namespace+".selectMyList",map);
	}

}
