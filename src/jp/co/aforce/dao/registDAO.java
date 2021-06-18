package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.aforce.beans.membersBeans;

public class registDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/database";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	public registDAO(membersBeans mb) {

		try (Connection con = DriverManager.getConnection(URL,USER,PASSWORD)) {

			String sql = "INSERT INTO database (member_no, name, birth_year, birth_month, birth_day) VALUES(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, mb.getMember_no());
			ps.setString(2,mb.getName());
			ps.setInt(3,mb.getBirth_year());
			ps.setInt(4, mb.getBirth_month());
			ps.setInt(5,mb.getBirth_day());

			con.close();
			ps.close();


		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
