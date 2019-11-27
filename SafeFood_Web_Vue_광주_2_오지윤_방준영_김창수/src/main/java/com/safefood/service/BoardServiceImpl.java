package com.safefood.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.safefood.dto.BoardDTO;
import com.safefood.dto.CommentDTO;
import com.safefood.repository.IBoardRepository;

@Service("BoardServiceImpl")
public class BoardServiceImpl implements IBoardService {
	
	@Autowired
	@Qualifier("BoardMyBatisRepositoryImpl")
	IBoardRepository dao ;
	public BoardServiceImpl() {}
	
	@Override
	public boolean deleteBoard(String user_id, int bid) {
		boolean res = dao.boardDelete(user_id, bid);
		return res;
	}

	@Override
	public boolean updateBoard(String user_id, int bid, String btitle, String bcontent) {
		boolean res = dao.boardUpdate(user_id, bid, btitle, bcontent);
		return res;
	}

	@Override
	public BoardDTO infoBoard(int bid) {
		BoardDTO board = dao.boardInfo(bid);
		return board;
	}

	@Override
	public List<BoardDTO> listBoard() {
		List<BoardDTO> list = dao.boardList();
		return list;
	}

	@Override
	public boolean registerBoard(String user_id, String btitle, String bcontent, String user_name) {
		int count = dao.boardList().size();
		boolean res = dao.boardInsert((count+1)+"", user_id, btitle, bcontent, user_name);
		return res;
	}

	@Override
	public List<BoardDTO> searchBoard(String keyword) {
		List<BoardDTO> list = dao.boardSearch(keyword);
		return list;
	}

	@Override
	public boolean countUp(int bid) {
		boolean res = dao.countUp(bid);
		return res;
	}

	@Override
	public boolean registerComment(int cnum, int bid, String ccontent, String user_id) {
		// TODO Auto-generated method stub
			
		boolean res = dao.registerComment(0, bid, ccontent, user_id);
		return res;
	}

	@Override
	public List<CommentDTO> commentList(int bid) {
		// TODO Auto-generated method stub
		List<CommentDTO> list = dao.commentList(bid);
		return list;
	}

	@Override
	public boolean commentDelete(int cnum) {
		// TODO Auto-generated method stub
		boolean res = dao.commentDelete(cnum);
		return res;
	}
	
	

}
