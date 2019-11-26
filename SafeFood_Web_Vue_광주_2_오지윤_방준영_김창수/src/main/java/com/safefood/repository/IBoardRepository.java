package com.safefood.repository;

import java.util.List;

import com.safefood.dto.BoardDTO;
import com.safefood.dto.CommentDTO;

public interface IBoardRepository {
	public boolean boardInsert(String bid, String user_id, String btitle, String bcontent);
	
	public List<BoardDTO> boardList();
	
	public BoardDTO boardInfo(int bid);
	
	public boolean boardUpdate(String user_id, int bid, String btitle, String bcontent);
	
	public boolean boardDelete(String user_id, int bid);
	
	public List<BoardDTO> boardSearch(String keyword);

	public boolean countUp(int nid);
	
	public boolean registerComment(int cnum, int bid, String ccontent, String user_id);
	
	public List<CommentDTO> commentList(int bid);
	
	public boolean commentDelete(int cnum);
}
