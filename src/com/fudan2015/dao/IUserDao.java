package com.fudan2015.dao;

import java.util.List;

import com.fudan2015.entity.User;

public interface IUserDao {
	/*
	 * 新增用户
	 * 修改用户信息
	 * 修改用户状态
	 * 查询某个用户
	 * 查询所有用户
	 *通过id来得到某个用户的方法
	 */
    public void addUser(User u);
    public void updateUser(User u);
    public void userState(User u,int state);
    public User findUserByAccount(String account);
    public List<User> showAllUser();
    public User findUserById(int id);
}
