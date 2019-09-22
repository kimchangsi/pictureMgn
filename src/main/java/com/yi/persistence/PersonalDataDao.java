package com.yi.persistence;

import java.util.List;
import java.util.Map;

import com.yi.domain.PersonalData;

public interface PersonalDataDao {
	public List<PersonalData> selectList(PersonalData data); 
	public int insertData(PersonalData data);
	public int deleteData(PersonalData data);
	public List<PersonalData> selectMyList(Map<String, Object> map); 
}
