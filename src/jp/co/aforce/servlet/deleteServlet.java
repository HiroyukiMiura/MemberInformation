package jp.co.aforce.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class deleteServlet extends HttpServlet {

	protected void doPost(
			HttpServletRequest request, HttpServletResponse response
		) throws ServletException, IOException {

		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup("java:/comp/env/jdbc/database");
			Connection con = ds.getConnection();

			String member_no_d = request.getParameter("member_no");
			String name = request.getParameter("name");
			String age_d = request.getParameter("age");
			String birth_year_d = request.getParameter("birth_year");
			String birth_month_d = request.getParameter("birth_month");
			String birth_day_d = request.getParameter("birth_day");

			PreparedStatement st = con.prepareStatement("delete * from members where member_no = member_no_d");

			int line = st.executeUpdate();

			if (line>0) {
				request.getRequestDispatcher("../jsp/delete_success.jsp").include(request, response);
			}

			st.close();
			con.close();

		} catch (Exception e) {
			request.getRequestDispatcher("../jsp/delete_false.jsp").include(request, response);
		}
	}
}
