package com.yi.service;

import java.util.List;
import java.util.Map;

import com.yi.domain.MemberPicture;
import com.yi.domain.PersonalData;

public interface PictureServiceDao {
	public int insertMember(MemberPicture member);
	public MemberPicture selectMember(MemberPicture member);
	public List<PersonalData> selectList(PersonalData data); 
	public int insertData(PersonalData data);
	public int deleteData(PersonalData data);
	public List<PersonalData> selectMyList(Map<String, Object> map); 
}
