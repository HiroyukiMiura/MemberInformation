package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.beans.membersBeans;

public class membersDAO extends DAO {
	public membersBeans serch(String member_no) throws Exception {

		membersBeans membersBeans = null;
		Connection con = getConnection();

		PreparedStatement st;
		st = con.prepareStatement("select * from members where member_no = ?");
		st.setString(1, member_no);
		ResultSet rs = st.executeQuery();

		while(rs.next()) {
			membersBeans = new membersBeans();
			membersBeans.setMember_no(rs.getString("member_no"));
			membersBeans.setName(rs.getString("name"));
			membersBeans.setAge(rs.getInt("age"));
			membersBeans.setBirth_year(rs.getInt("birth_year"));
			membersBeans.setBirth_month(rs.getInt("birth_month"));
			membersBeans.setBirth_day(rs.getInt("birth_day"));
		}

		st.close();
		con.close();
		return membersBeans;
	}

}
