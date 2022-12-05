package org.vision.homebook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.vision.common.ConnectionFactory;

public class HomebookDAO implements IDao<HomebookVO, Long> {
	Connection conn;

	@Override
	public int insert(HomebookVO vo) throws SQLException {
		conn = ConnectionFactory.getConnection2();
		int res = 0;
		String sql = "" + "INSERT INTO T_HOMEBOOK " + "(SERIALNO,SECTION,ACCOUNTTITLE,REMARK,REVENUE,EXPENSE,MEM_ID) "
				+ "VALUES(SEQ_T_HOMEBOOK.NEXTVAL,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		pstmt = conn.prepareStatement(sql);
		// pstmt.setDate(1, vo.getDay());
		pstmt.setString(1, vo.getSection());
		pstmt.setString(2, vo.getAccounttitle());
		pstmt.setString(3, vo.getRemark());
		pstmt.setInt(4, vo.getRevenue());
		pstmt.setInt(5, vo.getExpense());
		pstmt.setString(6, vo.getMem_id());
		res = pstmt.executeUpdate();

		pstmt.close();
		conn.close();

		return res;
	}

	@Override
	public int delete(Long pk, String mem_id) throws SQLException {
		conn = ConnectionFactory.getConnection2();
		System.out.println("deleteHB1");
		String sql = "DELETE FROM T_HOMEBOOK WHERE SERIALNO=? AND MEM_ID=?";
		int res = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, pk);
			pstmt.setString(2, mem_id);
			res = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			return -1;
		}
		System.out.println("deleteHB2 res:"+res);
		return res;
	}
	
	@Override
	public int deleteById(String mem_id) throws SQLException {
		conn = ConnectionFactory.getConnection2();
		System.out.println("deleteById1");
		String sql = "DELETE FROM T_HOMEBOOK WHERE MEM_ID=?";
		int res = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			res = pstmt.executeUpdate();
			System.out.println("deleteById2");
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			return -1;
		}
		System.out.println("deleteById3 res:"+res);
		return res;
	}	

	@Override // 수정 - 수정할 내용을 전달해야 함
	public int update(HomebookVO vo) throws SQLException {
		conn = ConnectionFactory.getConnection2();
		String sql = "UPDATE T_HOMEBOOK " + "SET DAY=?, SECTION=?, ACCOUNTTITLE=? ," + "REMARK =?, REVENUE=?,EXPENSE=? "
				+ "WHERE SERIALNO=?";// MEM_ID는 수정하면 안됨
		int res = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, vo.getDay());
			pstmt.setString(2, vo.getSection());
			pstmt.setString(3, vo.getAccounttitle());
			pstmt.setString(4, vo.getRemark());
			pstmt.setInt(5, vo.getRevenue());
			pstmt.setInt(6, vo.getExpense());
			pstmt.setLong(7, vo.getSerialno());
			res = pstmt.executeUpdate();

			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			throw new SQLException("수정이 안되었을껄!");
		}
		return res;
	}

	@Override
	public HomebookVO select(Long pk) throws SQLException {
		conn = ConnectionFactory.getConnection2();
		String sql = "SELECT * FROM T_HOMEBOOK WHERE SERIALNO=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1, pk);
		ResultSet rs = pstmt.executeQuery();
		HomebookVO vo = new HomebookVO();
		rs.next();
		vo.setSerialno(pk);
		vo.setDay(rs.getDate("DAY"));
		vo.setSection(rs.getString("SECTION"));
		vo.setAccounttitle(rs.getString("ACCOUNTTITLE"));
		vo.setRemark(rs.getString("REMARK"));
		vo.setRevenue(rs.getInt("REVENUE"));
		vo.setExpense(rs.getInt("EXPENSE"));
		vo.setMem_id(rs.getString("MEM_ID"));
		pstmt.close();
		conn.close();
		return vo;
	}
	
	@Override
	public List<HomebookVO> selectAll(String mem_id) throws SQLException {
		conn = ConnectionFactory.getConnection2();
		String sql = "SELECT * FROM T_HOMEBOOK WHERE MEM_ID = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mem_id);
		ResultSet rs = pstmt.executeQuery();
		List<HomebookVO> data = new ArrayList<>();
		while (rs.next()) {
			HomebookVO vo = new HomebookVO();
			vo.setSerialno(rs.getLong("SERIALNO"));
			vo.setDay(rs.getDate("DAY"));
			vo.setSection(rs.getString("SECTION"));
			vo.setAccounttitle(rs.getString("ACCOUNTTITLE"));
			vo.setRemark(rs.getString("REMARK"));
			vo.setRevenue(rs.getInt("REVENUE"));
			vo.setExpense(rs.getInt("EXPENSE"));
			vo.setMem_id(rs.getString("MEM_ID"));
			data.add(vo);
		}
		pstmt.close();
		conn.close();
		return data;
	}

	@Override
	public List<HomebookVO> selectByDay(Timestamp startDate, Timestamp endDate, String mem_id) throws SQLException {
		conn = ConnectionFactory.getConnection2();
		String sql = "SELECT * FROM T_HOMEBOOK WHERE " + " DAY BETWEEN ? AND ? AND MEM_ID=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setTimestamp(1, startDate);
		pstmt.setTimestamp(2, endDate);
		pstmt.setString(3, mem_id);
		ResultSet rs = pstmt.executeQuery();
		List<HomebookVO> data = new ArrayList<>();
		while (rs.next()) {
			HomebookVO vo = new HomebookVO();
			vo.setSerialno(rs.getLong("SERIALNO"));
			vo.setDay(rs.getDate("DAY"));
			vo.setSection(rs.getString("SECTION"));
			vo.setAccounttitle(rs.getString("ACCOUNTTITLE"));
			vo.setRemark(rs.getString("REMARK"));
			vo.setRevenue(rs.getInt("REVENUE"));
			vo.setExpense(rs.getInt("EXPENSE"));
			vo.setMem_id(rs.getString("MEM_ID"));
			data.add(vo);
		}
		pstmt.close();
		conn.close();
		return data;
	}

}
