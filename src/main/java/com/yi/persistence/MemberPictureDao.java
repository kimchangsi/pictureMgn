package com.yi.persistence;

import com.yi.domain.MemberPicture;

public interface MemberPictureDao {
	public int insertMember(MemberPicture member);
	public MemberPicture selectMember(MemberPicture member);
	
}	
