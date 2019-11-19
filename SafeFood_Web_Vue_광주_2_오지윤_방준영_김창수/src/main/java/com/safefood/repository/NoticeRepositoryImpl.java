package com.safefood.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.safefood.dto.NoticeDTO;

public class NoticeRepositoryImpl implements INoticeRepository {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	private void close() {
		try {
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("DB 닫기 에러");
		}
	}

	@Override
	public ArrayList<NoticeDTO> noticeList() {
		ArrayList<NoticeDTO> list = new ArrayList<NoticeDTO>();
		try {
			conn = ConnectionProxy.getConnection();
			String sql = "select * from notice";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new NoticeDTO(rs.getInt("nid"), rs.getString("ntitle"), rs.getString("ncontent"),
						rs.getInt("ncount")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return list;
	}

	@Override
	public NoticeDTO noticeInfo(int nid) {
		NoticeDTO notice = null;

		try {
			conn = ConnectionProxy.getConnection();
			String sql = "select * from notice where nid = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, nid);
			rs = ps.executeQuery();
			while (rs.next()) {
				notice = new NoticeDTO(rs.getInt("nid"), rs.getString("ntitle"), rs.getString("ncontent"),
						rs.getInt("ncount"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return notice;
	}

	@Override
	public boolean noticeUpdate(NoticeDTO n) {
		// TODO Auto-generated method stub
		boolean res = false;

		try {
			conn = ConnectionProxy.getConnection();
			String sql = "update notice set ntitle=?, ncontent=? where nid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, n.getNtitle());
			ps.setString(2, n.getNcontent());
			ps.setInt(3, n.getNid());
			ps.executeUpdate();
			res = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res = false;
		}

		return res;
	}

	@Override
	public boolean noticeDelete(int nid) {
		boolean res = false;
		try {
			conn = ConnectionProxy.getConnection();
			String sql = "delete from notice where nid=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, nid);
			res = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res = false;
		}
		return res;
	}

	@Override
	public ArrayList<NoticeDTO> noticeSearch(String keyword) {
		ArrayList<NoticeDTO> list = new ArrayList<NoticeDTO>();
		try {
			conn = ConnectionProxy.getConnection();
			String sql = "select * from notice where ntitle = LIKE %?% ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, keyword);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new NoticeDTO(rs.getInt("nid"), rs.getString("ntitle"), rs.getString("ncontent"),
						rs.getInt("ncount")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return list;
	}

	@Override
	public boolean countUp(int nid) {
		boolean res = false;

		try {
			conn = ConnectionProxy.getConnection();
			String sql = "update notice set ncount=ncount+1 where nid=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, nid);
			ps.executeUpdate();
			res = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res = false;
		}

		return res;
	}

	@Override
	public boolean noticeInsert(NoticeDTO n) {
		boolean res = false;

		try {
			conn = ConnectionProxy.getConnection();
			String sql = "insert into notice (nid, ntitle, ncontent) values(?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(n.getNid() + ""));
			ps.setString(2, n.getNtitle());
			ps.setString(3, n.getNcontent());
			ps.executeUpdate();
			res = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res = false;
		} finally {
			close();
		}

		return res;

	}
}
