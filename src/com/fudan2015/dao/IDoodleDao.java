package com.fudan2015.dao;

import java.util.List;

import com.fudan2015.entity.Doodle;

public interface IDoodleDao {
	public void addDoodle(Doodle d);
	public void deleteDoodle(int id);
	public void updateDoodle(Doodle d);
	public Doodle showDoodleById(int id);
	public Doodle showDoodleByPicId(int picId);
	public List<Doodle> showDoodleByAccount(int userId);
}
