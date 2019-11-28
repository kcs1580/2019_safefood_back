package com.safefood.service;

import java.util.ArrayList;
import java.util.List;

import com.safefood.dto.BoardDTO;
import com.safefood.dto.ChatDTO;
import com.safefood.dto.CommentDTO;
import com.safefood.dto.FoodDTO;

public interface IBoardService {
	public boolean deleteBoard(String user_id, int bid);

	public boolean updateBoard(String user_id, int bid, String btitle, String bcontent);

	public BoardDTO infoBoard(int bid);

	public List<BoardDTO> listBoard();

	
	public boolean registerBoard(String user_id, String btitle, String bcontent, String user_name);
	
	public List<BoardDTO> searchBoard(String keyword);

	public boolean countUp(int bid);
	
	public boolean registerComment(int cnum,int bid,  String ccontent, String user_id,String user_name);
	
	public List<CommentDTO> commentList(int bid);
	
	public boolean commentDelete(int cnum);
	
	public boolean registerChat(int cnum, String ccontent, String user_id,String user_name);
	
	public List<ChatDTO> chatList();

	public List<BoardDTO> searchBoardByName(String keyword);

	public List<BoardDTO> searchBoardByTitle(String keyword);
}
