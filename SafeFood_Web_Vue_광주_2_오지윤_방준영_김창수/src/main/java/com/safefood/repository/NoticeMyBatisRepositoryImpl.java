package com.safefood.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safefood.dto.NoticeDTO;

@Repository("NoticeMyBatisRepositoryImpl")
public class NoticeMyBatisRepositoryImpl implements INoticeRepository {

	@Autowired
	SqlSession session;

	@Override
	public boolean noticeInsert(NoticeDTO n) {
		System.out.println(n.toString() + "데이터보내기전");
		try {
			session.insert("com.notice.notInsert", n);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public ArrayList<NoticeDTO> noticeList() {
		List<NoticeDTO> list = session.selectList("com.notice.notList");
		return (ArrayList<NoticeDTO>) list;
	}

	@Override
	public NoticeDTO noticeInfo(int nid) {
		NoticeDTO notice = session.selectOne("com.notice.notInfo",nid+"");
		return notice;
	}

	@Override
	public boolean noticeUpdate(NoticeDTO n) {
		int res = session.update("com.notice.notUpdate", n);
		if (res >= 1) {
			
			return true;
		}
		return false;
	}

	@Override
	public boolean noticeDelete(int nid) {
		int res = session.delete("com.notice.notDelete", nid);
		if(res>=1) {
			session.update("com.notice.noticeIndexRefresh1");
			session.update("com.notice.noticeIndexRefresh2");
			session.update("com.notice.noticeIndexRefresh3");
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<NoticeDTO> noticeSearch(String keyword) {

		return null;
	}

	@Override
	public boolean countUp(int nid) {
		int check = session.update("com.notice.countUp", nid);
		if(check == 1) 
			return true;
		else
			return false;
	}

}
