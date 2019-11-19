package com.safefood.repository;

import java.util.ArrayList;

import com.safefood.dto.NoticeDTO;

public interface INoticeRepository {
	public boolean noticeInsert(NoticeDTO n);
	
	public ArrayList<NoticeDTO> noticeList();
	
	public NoticeDTO noticeInfo(int nid);
	
	public boolean noticeUpdate(NoticeDTO n);
	
	public boolean noticeDelete(int nid);
	
	public ArrayList<NoticeDTO> noticeSearch(String keyword);

	public boolean countUp(int nid);

}
