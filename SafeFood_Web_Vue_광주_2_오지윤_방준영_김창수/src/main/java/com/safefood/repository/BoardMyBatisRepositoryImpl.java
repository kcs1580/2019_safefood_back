package com.safefood.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safefood.dto.BoardDTO;

@Repository("BoardMyBatisRepositoryImpl")
public class BoardMyBatisRepositoryImpl implements IBoardRepository {

	@Autowired
	SqlSession session;

	public BoardMyBatisRepositoryImpl() {

	}

	@Override
	public boolean boardInsert(String bid, String user_id, String btitle, String bcontent) {
		BoardDTO m = new BoardDTO();
		m.setBid(Integer.parseInt(bid));
		m.setUser_id(user_id);
		m.setBtitle(btitle);
		m.setBcontent(bcontent);
		m.setBcount(0);
		int res = session.insert("com.board.boardInsert", m);
		if (res >= 1)
			return true;
		return false;
	}

	@Override
	public List<BoardDTO> boardList() {
		List<BoardDTO> selectList = session.selectList("com.board.boardList");
		return selectList;
	}

	@Override
	public BoardDTO boardInfo(int bid) {
		BoardDTO board = session.selectOne("com.board.boardInfo", bid+"");
		return board;
	}

	@Override
	public boolean boardUpdate(String user_id, int bid, String btitle, String bcontent) {
		BoardDTO m = new BoardDTO();
		m.setBid(bid);
		m.setUser_id(user_id);
		m.setBtitle(btitle);
		m.setBcontent(bcontent);
		int res = session.update("com.board.boardUpdate", m);
		if (res >= 1)
			return true;
		return false;
	}

	@Override
	public boolean boardDelete(String user_id, int bid) {
		int res = session.delete("com.board.boardDelete", bid+"");
		if(res>=1) {
			session.update("com.board.boardIndexRefresh1");
			session.update("com.board.boardIndexRefresh2");
			session.update("com.board.boardIndexRefresh3");
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<BoardDTO> boardSearch(String keyword) {

		return null;
	}

	@Override
	public boolean countUp(int bid) {
		int check = session.update("com.board.countUp", bid);
		if(check == 1) 
			return true;
		else
			return false;
	}

}
