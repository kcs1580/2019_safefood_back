package com.safefood.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.safefood.dto.NoticeDTO;
import com.safefood.repository.INoticeRepository;

@Service("NoticeServiceImpl")
public class NoticeServiceImpl implements INoticeService {
	
	@Autowired
	@Qualifier("NoticeMyBatisRepositoryImpl")
	INoticeRepository dao;
	public NoticeServiceImpl() {}
	
	@Override
	public boolean deleteNotice(int nid) {
		boolean res = dao.noticeDelete(nid);
		return res;
	}

	@Override
	public boolean updateNotice(int nid, String ntitle, String ncontent) {
		boolean res = dao.noticeUpdate(new NoticeDTO(nid, ntitle, ncontent));
		return res;
	}

	@Override
	public NoticeDTO infoNotice(int nid) {
		NoticeDTO notice = dao.noticeInfo(nid);
		return notice;
	}

	@Override
	public ArrayList<NoticeDTO> listNotice() {
		ArrayList<NoticeDTO> list = dao.noticeList();
		return list;
	}

	@Override
	public boolean registerNotice(String ntitle, String ncontent) {
		int nid = dao.noticeList().size();
		boolean res = dao.noticeInsert(new NoticeDTO(nid+1, ntitle, ncontent));
		return res;
	}

	@Override
	public ArrayList<NoticeDTO> searchNotice(String keyword) {
		ArrayList<NoticeDTO> list = dao.noticeSearch(keyword);
		return list;
	}

	@Override
	public boolean countUp(int nid) {
		boolean res = dao.countUp(nid);
		return res;
	}

}
