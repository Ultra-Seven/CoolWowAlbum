package com.fudan2015.biz;

import com.fudan2015.entity.User;

//Created by XuHailong
public interface IUcpBiz {
	/*
	 * 用户头像修改
	 * 用户密码检查(1为正确，0为错误)
	 * 用户资料修改（性别和签名）
	 * 用户密码修改
	 */
	public void updateUserAvatar();//未实现
	public boolean checkUserPassWord(String account, String password);
	public void updateUserProfile(String account, int gender, String description);
	public void updateUserPassword(String account, String password);
}
