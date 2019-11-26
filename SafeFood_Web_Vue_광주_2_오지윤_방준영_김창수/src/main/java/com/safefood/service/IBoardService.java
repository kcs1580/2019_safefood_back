package com.safefood.service;

import java.util.ArrayList;
import java.util.List;

import com.safefood.dto.BoardDTO;
import com.safefood.dto.CommentDTO;

public interface IBoardService {
	public boolean deleteBoard(String user_id, int bid);

	public boolean updateBoard(String user_id, int bid, String btitle, String bcontent);

	public BoardDTO infoBoard(int bid);

	public List<BoardDTO> listBoard();

	public boolean registerBoard(String user_id, String btitle, String bcontent);
	
	public List<BoardDTO> searchBoard(String keyword);

	public boolean countUp(int bid);
	
	public boolean registerComment(int cnum,int bid,  String ccontent, String user_id);
	
	public List<CommentDTO> commentList(int bid);
	
	public boolean commentDelete(int cnum);
}
