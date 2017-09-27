package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDao {
	Connection conn = null;
	Statement stat = null;
	ResultSet rs = null;
	PreparedStatement pstat = null;

	public void getConnection() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.jdbc.Driver");

		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/school?characterEncoding=utf-8",
				"root", "123456");
	}

	public void getStatement() throws ClassNotFoundException, SQLException {

		getConnection();
		stat = conn.createStatement();

	}

	public void getPreparedStatement(String sql) throws ClassNotFoundException,
			SQLException {

		getConnection();
		pstat = conn.prepareStatement(sql);

	}

	public void closeAll() throws SQLException {

		if (rs != null) {
			rs.close();
		}
		if (stat != null) {

			stat.close();
		}
		if (pstat != null) {

			pstat.close();
		}
		if (conn != null) {

			conn.close();
		}

	}
}
