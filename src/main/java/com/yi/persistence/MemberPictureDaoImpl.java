package com.yi.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yi.domain.MemberPicture;
@Repository
public class MemberPictureDaoImpl implements MemberPictureDao {
	private static final String namespace = "com.yi.mapper.MemberPicture";
	
	@Autowired
	SqlSession session;
	
	@Override
	public int insertMember(MemberPicture member) {
		// TODO Auto-generated method stub
		return session.insert(namespace+".insertMember",member);
	}

	@Override
	public MemberPicture selectMember(MemberPicture member) {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+".selectMember",member);
	}

}
