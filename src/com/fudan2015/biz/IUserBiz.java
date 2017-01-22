package com.fudan2015.biz;

import java.util.List;

import com.fudan2015.entity.User;

public interface IUserBiz {
	/*
	 * 用户登录
	 * 用户注册
	 * 用户头像上传修改
	 * 用户密码修改
	 * 用户签名修改
	 * 用户密码找回
	 * 通过account来得到某个用户
	 * 更新用户的状态信息
	 * 通过id来获取用户
	 */
	public User userLogin(User u);
	public void userRegist(User u);
	public void userFaceUpdate(User u);
	public void userUpdatePasswd(User u);
	public void userUpdateDescription(User u);
	public void userFindPasswd(User u);
	public User userFind(String account);
	public List<User> userAll();
	public User findUserByAccount(String account);
	public void updateUser(User u);
	public User findUserById(int id);
}
