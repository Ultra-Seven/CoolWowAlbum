package com.fudan2015.biz.impl;

import java.util.List;

import com.fudan2015.biz.IShareBiz;
import com.fudan2015.dao.impl.PhotoDaoImpl;
import com.fudan2015.dao.impl.ShareDaoImpl;
import com.fudan2015.entity.Photo;
import com.fudan2015.entity.Share;

public class ShareBizImpl implements IShareBiz {
	ShareDaoImpl dao=new ShareDaoImpl();
	PhotoDaoImpl pDao=new PhotoDaoImpl();
	public void addShare(Share share) {
		// TODO Auto-generated method stub
		dao.addShare(share);
	}

	public void deleteShare(int id) {
		// TODO Auto-generated method stub
		dao.deleteShare(id);
	}

	public List<Share> showShareAll(int idShare) {
		// TODO Auto-generated method stub
		return dao.showShareAll(idShare);
	}

	public Photo showPhotoZX(int id) {
		// TODO Auto-generated method stub
		return pDao.showPhotoZX(id);
	}

	public Share showShareNotById(int idShare, int pictureId) {
		// TODO Auto-generated method stub
		return dao.showShareNotById(idShare, pictureId);
	}

}
