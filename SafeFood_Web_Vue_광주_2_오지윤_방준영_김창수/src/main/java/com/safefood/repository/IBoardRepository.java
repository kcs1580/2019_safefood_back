package com.safefood.repository;

import java.util.List;

import com.safefood.dto.BoardDTO;
import com.safefood.dto.ChatDTO;
import com.safefood.dto.CommentDTO;

public interface IBoardRepository {
	public boolean boardInsert(String bid, String user_id, String btitle, String bcontent, String user_name);
	
	public List<BoardDTO> boardList();
	
	public BoardDTO boardInfo(int bid);
	
	public boolean boardUpdate(String user_id, int bid, String btitle, String bcontent);
	
	public boolean boardDelete(String user_id, int bid);
	
	public List<BoardDTO> boardSearch(String keyword);

	public boolean countUp(int nid);
	
	public boolean registerComment(int cnum, int bid, String ccontent, String user_id,String user_name);
	
	public List<CommentDTO> commentList(int bid);
	
	public boolean commentDelete(int cnum);
	
	public boolean registerChat (int cnum, String ccontent, String user_id,String user_name);
	
	public List<ChatDTO> chatList();
	public List<BoardDTO> searchBoardByName(String keyword);

	public List<BoardDTO> searchBoardByTitle(String keyword);
}
