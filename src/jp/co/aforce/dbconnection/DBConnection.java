package jp.co.aforce.dbconnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet(urlPatterns = {"/jp.co.aforce.dbconnection/dbconnection"})
public class DBConnection extends HttpServlet {

	public void doGet(
			HttpServletRequest request, HttpServletResponse response
		) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup("java:/comp/env/jdbc/database");
			Connection con = ds.getConnection();

			PreparedStatement st = con.prepareStatement("select * from members");

			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace(out);
		}

	}

}
