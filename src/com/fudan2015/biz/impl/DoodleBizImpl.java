package com.fudan2015.biz.impl;

import java.util.List;

import com.fudan2015.biz.IDoodleBiz;
import com.fudan2015.dao.impl.DoodleDaoImpl;
import com.fudan2015.entity.Doodle;

public class DoodleBizImpl implements IDoodleBiz {
	DoodleDaoImpl dDao=new DoodleDaoImpl();
	public void addDoodle(Doodle d) {
		// TODO Auto-generated method stub
		dDao.addDoodle(d);
	}

	public void deleteDoodle(int id) {
		// TODO Auto-generated method stub
		dDao.deleteDoodle(id);
	}

	public List<Doodle> showDoodleByAccount(int userId) {
		// TODO Auto-generated method stub
		return dDao.showDoodleByAccount(userId);
	}

	public Doodle showDoodleById(int id) {
		// TODO Auto-generated method stub
		return dDao.showDoodleById(id);
	}

	public void updateDoodle(Doodle d) {
		// TODO Auto-generated method stub
		dDao.updateDoodle(d);
	}

	public Doodle showDoodleByPicId(int picId) {
		// TODO Auto-generated method stub
		return dDao.showDoodleByPicId(picId);
	}

}
