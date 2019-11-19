//package com.safefood.repository;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.safefood.dto.TakeInDTO;
//import com.safefood.dto.TakeinFoodDTO;
//import com.safefood.dto.TakeinSumDTO;
//
//public class TakeinFoodRepositoryImpl implements ITakeinFoodRepository {
//	private Connection conn = null;
//	private PreparedStatement ps = null;
//	private ResultSet rs = null;
//	
//	private void close() {
//		try {
//			if(rs!=null) rs.close();
//			if(conn!=null) conn.close();
//			if(ps!=null) ps.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("DB 닫기 에러");
//		}
//}
//	@Override
//	public int intakeInsert(int code, String id, int icount) {
//		boolean res = false;
//		try {
//			conn = ConnectionProxy.getConnection();
//			String fsql = "select icount from intake where code=? and id=? and idate = current_date()";
//			ps = conn.prepareStatement(fsql);
//			ps.setInt(1, code);
//			ps.setString(2, id);
//			rs = ps.executeQuery();
//			String sql = "";
//			int ex = 0;
//			if(rs.next()) {
//				sql = "update intake set icount = icount+1 where code = ? and id = ? and idate = current_date()";
//				ps = conn.prepareStatement(sql);
//				ps.setInt(1, code);
//				ps.setString(2, id);
//				ex = ps.executeUpdate();
//			}else {
//				System.out.println(11);
//				sql = "insert into intake values(?,?,current_date(), ?)";
//				ps = conn.prepareStatement(sql);
//				ps.setInt(1, code);
//				ps.setString(2, id);
//				ps.setInt(3, 1);
//				ex = ps.executeUpdate();
//			}
//			
//			if(ex > 0) {
//				res = true;
//			}
//			return 0;
//			
//		} catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			res = false;
//		} finally {
//			close();
//		}
//		
//		return 0;
//	}
//
//	@Override
//	public ArrayList<TakeinFoodDTO> intakeList(String id) {
//		ArrayList<TakeinFoodDTO> list = new ArrayList<TakeinFoodDTO>();
//		try {
//			conn = ConnectionProxy.getConnection();
//			String sql = "select f.*, i.id, i.idate, i.icount from food as f join intake as i on (f.code = i.code) where i.id=? and icount>0";
//			ps = conn.prepareStatement(sql);
//			ps.setString(1,id);
//			rs = ps.executeQuery();
//			while(rs.next()) {
//				list.add(new TakeinFoodDTO(rs.getInt("code"), rs.getString("id"), rs.getString("idate"), rs.getInt("icount"), rs.getString("name"), rs.getDouble("supportpereat"), rs.getDouble("calory"), rs.getDouble("carbo"), rs.getDouble("protein")
//						, rs.getDouble("fat"), rs.getDouble("sugar"), rs.getDouble("natrium"), rs.getDouble("chole"), rs.getDouble("fattyacid"), rs.getDouble("transfat")
//						, rs.getString("maker"), rs.getString("material"), rs.getString("img"), rs.getString("allergy"), rs.getInt("searchCount")));	
//		}
//			} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			close();
//		}
//		
//		return list;
//	}
//
//	@Override
//	public TakeinFoodDTO intakeInfo(String id,int code) {
//		TakeinFoodDTO ifood = null;
//		try {
//			conn = ConnectionProxy.getConnection();
//			String sql = "select f.*, i.id, i.idate, i.icount from food as f join intake as i on (f.code = i.code) where i.code = ? and i.id=? and icount>0";
//			ps = conn.prepareStatement(sql);
//			ps.setString(1,id);
//			ps.setInt(2, code);
//			rs = ps.executeQuery();
//			while(rs.next()) {
//				ifood = new TakeinFoodDTO(rs.getInt("code"), rs.getString("id"), rs.getString("idate"), rs.getInt("icount"), rs.getString("name"), rs.getDouble("supportpereat"), rs.getDouble("calory"), rs.getDouble("carbo"), rs.getDouble("protein")
//						, rs.getDouble("fat"), rs.getDouble("sugar"), rs.getDouble("natrium"), rs.getDouble("chole"), rs.getDouble("fattyacid"), rs.getDouble("transfat")
//						, rs.getString("maker"), rs.getString("material"), rs.getString("img"), rs.getString("allergy"), rs.getInt("searchCount"));	
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			close();
//		}
//		
//		return ifood;
//	}
//
//	
//	@Override
//	public int intakeDelete(String id,int code) {
//		
//		boolean res = false;
//		try {
//			conn = ConnectionProxy.getConnection();
//			String fsql = "select icount from intake where code=? & id=?";
//			ps = conn.prepareStatement(fsql);
//			ps.setInt(1, code);
//			ps.setString(2, id);
//			rs = ps.executeQuery();
//			String sql = "";
//			if(rs.getInt("icount")!=0) {
//				sql = "update intake set icount = icount-1";
//				ps = conn.prepareStatement(sql);
//			}else {
//				//icount지울게 없엉
//			}
//			
//			int ex = ps.executeUpdate();
//			if(ex > 0) {
//				res = true;
//			}
//			return 0;
//			
//		} catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			res = false;
//		} finally {
//			close();
//		}
//		
//		return 0;
//		
//	}
//	@Override
//	public TakeinSumDTO intakeSum(String id) {
//		TakeinSumDTO sum = new TakeinSumDTO();
//		try {
//			conn = ConnectionProxy.getConnection();
//			String sql = "select sum(f.calory*i.icount) as calory,"
//					+ " sum(f.carbo*i.icount) as carbo,"
//					+ " sum(f.protein*i.icount) as protein,"
//					+ " sum(f.fat*i.icount) as fat,"
//					+ " sum(f.sugar*i.icount) as sugar,"
//					+ " sum(f.natrium*i.icount) as natrium,"
//					+ " sum(f.chole*i.icount) as chole,"
//					+ " sum(f.fattyacid*i.icount) as fattyacid,"
//					+ " sum(f.transfat*i.icount) as transfat  from food as f join intake as i on (f.code = i.code) where i.id=? and icount>0";
//			ps = conn.prepareStatement(sql);
//			ps.setString(1,id);
//			rs = ps.executeQuery();
//			while(rs.next()) {
//				sum = new TakeinSumDTO(rs.getDouble("calory"),rs.getDouble("carbo"),rs.getDouble("protein")
//						,rs.getDouble("fat"),rs.getDouble("sugar"),rs.getDouble("natrium")
//						,rs.getDouble("chole"),rs.getDouble("fattyacid"),rs.getDouble("transfat"));
//		}
//			} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			close();
//		}
//		
//		return sum;
//	}
//	@Override
//	public List<TakeinFoodDTO> intakeSearch(String id, String keyword) {
//		List<TakeinFoodDTO> list = new ArrayList<TakeinFoodDTO>();
//		try {
//			conn = ConnectionProxy.getConnection();
//			String sql = "select f.*, i.id, i.idate, i.icount from food as f join intake as i on (f.code = i.code) where i.id=? and icount>0 and f.name like ?";
//			ps = conn.prepareStatement(sql);
//			ps.setString(1,id);
//			ps.setString(2, "%"+keyword+"%");
//			rs = ps.executeQuery();
//			while(rs.next()) {
//				list.add(new TakeinFoodDTO(rs.getInt("code"), rs.getString("id"), rs.getString("idate"), rs.getInt("icount"), rs.getString("name"), rs.getDouble("supportpereat"), rs.getDouble("calory"), rs.getDouble("carbo"), rs.getDouble("protein")
//						, rs.getDouble("fat"), rs.getDouble("sugar"), rs.getDouble("natrium"), rs.getDouble("chole"), rs.getDouble("fattyacid"), rs.getDouble("transfat")
//						, rs.getString("maker"), rs.getString("material"), rs.getString("img"), rs.getString("allergy"), rs.getInt("searchCount")));	
//		}
//			} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			close();
//		}
//		
//		return list;
//	}
//	@Override
//	public int intakeInsert(TakeInDTO t) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//	@Override
//	public int intakeUpdate(TakeInDTO t) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//	@Override
//	public int selectOne(int code, String id, int icount) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//	
//}
