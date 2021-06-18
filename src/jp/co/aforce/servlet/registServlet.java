package jp.co.aforce.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet(urlPatterns= {"/jp.co.aforce.servlet/registservlet"})
public class registServlet extends HttpServlet {

	public void doPost (
			HttpServletRequest request, HttpServletResponse response
		) throws ServletException, IOException {

		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup("java:/comp/env/jdbc/database");
			Connection con = ds.getConnection();

			Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmmss");
			String str = sdf.format(timeStamp);

			String name = request.getParameter("name");
			String age_r = request.getParameter("age");
			String birth_year_r = request.getParameter("birth_year");
			String birth_month_r = request.getParameter("birth_month");
			String birth_day_r = request.getParameter("birth_day");

			if (name==""||age_r==""||birth_year_r==""||birth_month_r==""||birth_day_r=="") {
				request.getRequestDispatcher("../jsp/input_error.jsp").include(request, response);
			} else {
				int age_R = Integer.parseInt(age_r);
				int birth_year_R = Integer.parseInt(birth_year_r);
				int birth_month_R = Integer.parseInt(birth_month_r);
				int birth_day_R = Integer.parseInt(birth_day_r);

				PreparedStatement st = con.prepareStatement("insert into members values(?,?,?,?,?,?)");
				st.setString(1, "A" + str);
				st.setString(2, name);
				st.setInt(3, age_R);
				st.setInt(4, birth_year_R);
				st.setInt(5, birth_month_R);
				st.setInt(6, birth_day_R);
				int line = st.executeUpdate();

				if (line>0) {
					request.getRequestDispatcher("../jsp/regist_success.jsp").include(request, response);
				}

				st.close();
				con.close();

			}
		} catch (Exception e) {
			request.getRequestDispatcher("../jsp/regist_false.jsp").include(request, response);
		}
	}
}
