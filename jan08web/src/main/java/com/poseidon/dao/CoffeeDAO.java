package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.poseidon.dto.CoffeeDTO;

public class CoffeeDAO extends AbstractDAO {

	public int order(CoffeeDTO dto) {
		int result = 0;

		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "";

		try {
			if (dto.getIcec() > 0) {
				// 아이스 아메리카노에 대한 SQL 실행
				sql = "UPDATE coffee SET icec = icec + 1";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, dto.getIcec());

			} else if (dto.getHotc() > 0) {
				sql = "UPDATE coffee SET hotc = hotc + 1";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, dto.getHotc());
			} else if (dto.getIcet() > 0) {
				sql = "UPDATE coffee SET icet = icet + 1";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, dto.getIcet());
			} else if (dto.getHott() > 0) {
				sql = "UPDATE coffee SET hott = hott + 1";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, dto.getHott());
			} else if (dto.getNone() > 0) {
				sql = "UPDATE coffee SET none = none + 1";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, dto.getNone());
			}

			result = pstmt.executeUpdate();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}

		return result;
	}

}