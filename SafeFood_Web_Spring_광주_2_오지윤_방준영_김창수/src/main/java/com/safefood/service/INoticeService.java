package com.safefood.service;

import java.util.ArrayList;

import com.safefood.dto.NoticeDTO;

public interface INoticeService {
	public boolean deleteNotice(int nid);

	public boolean updateNotice(int nid, String ntitle, String ncontent);

	public NoticeDTO infoNotice(int nid);

	public ArrayList<NoticeDTO> listNotice();

	public boolean registerNotice(String ntitle, String ncontent);
	
	public ArrayList<NoticeDTO> searchNotice(String keyword);

	public boolean countUp(int nid);
}
