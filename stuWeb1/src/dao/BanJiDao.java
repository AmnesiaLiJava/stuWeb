package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;

import entity.BanJi;

public class BanJiDao extends BaseDao {

	public int searchCount() {
		int count = 0;
		try {
			getStatement();
			Statement stat = conn.createStatement();

			String sql = "select count(id) from grade";
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeAll();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return count;

	}

	public int searchCount(BanJi condition) {
		int count = 0;
		try {
			getStatement();
			Statement stat = conn.createStatement();
			String where = " where 1=1 ";
			if (condition.getName() != null) {
				where += " and name like '%" + condition.getName() + "%'";
			}

			String sql = "select count(id) from grade " + where;
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeAll();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return count;

	}

	public List<BanJi> searchByBegin(int begin, int num) {
		List<BanJi> list = new ArrayList<BanJi>();

		try {
			getStatement();
			Statement stat = conn.createStatement();

			String sql = "select * from grade limit " + begin + "," + num;
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				BanJi bj = new BanJi();
				bj.setId(rs.getInt("id"));
				bj.setName(rs.getString("name"));
				bj.setStuNums(rs.getInt("stuNums"));
				list.add(bj);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeAll();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;

	}

	public List<BanJi> searchAll() {
		List<BanJi> list = new ArrayList<BanJi>();
		try {
			getStatement();

			String sql = "select * from grade";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				BanJi bj = new BanJi();
				bj.setId(rs.getInt("id"));
				bj.setName(rs.getString("name"));
				bj.setStuNums(rs.getInt("stuNums"));
				list.add(bj);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				closeAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;

	}

	public boolean add(BanJi bj) {
		boolean flag = false;

		try {
			String sql = "insert into grade (name,stuNums) " + " values(?,?)";
			getPreparedStatement(sql);
			pstat.setString(1, bj.getName());
			pstat.setInt(2, bj.getStuNums());
			int rs = pstat.executeUpdate();
			if (rs > 0) {
				flag = true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				closeAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return flag;
	}

	public BanJi searchById(int id) {
		BanJi bj = null;
		try {
			getStatement();

			String sql = "select * from grade where id=" + id;
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				bj = new BanJi();
				bj.setId(rs.getInt("id"));
				bj.setName(rs.getString("name"));
				bj.setStuNums(rs.getInt("stuNums"));
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				closeAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bj;
	}

	public List<BanJi> searchByIds(String ids) {
		List<BanJi> list = new ArrayList<BanJi>();
		try {
			getStatement();

			String sql = "select * from grade where id in( " + ids + " )";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				BanJi bj = new BanJi();
				bj.setId(rs.getInt("id"));
				bj.setName(rs.getString("name"));
				bj.setStuNums(rs.getInt("stuNums"));
				list.add(bj);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				closeAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public boolean modify(BanJi bj) {
		boolean flag = false;

		try {
			String sql = "update grade set name=?,stuNums=? where id=?";
			getPreparedStatement(sql);
			pstat.setString(1, bj.getName());
			pstat.setInt(2, bj.getStuNums());
			pstat.setInt(3, bj.getId());
			int rs = pstat.executeUpdate();
			if (rs > 0) {
				flag = true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				closeAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return flag;

	}

	public boolean modify2(List<BanJi> list) {
		boolean flag = false;

		try {
			for (int i = 0; i < list.size(); i++) {
				BanJi bj = list.get(i);
				String sql = "update grade set name=?,stuNums=? where id=?";
				getPreparedStatement(sql);
				pstat.setString(1, bj.getName());
				pstat.setInt(2, bj.getStuNums());
				pstat.setInt(3, bj.getId());
				int rs = pstat.executeUpdate();
				if (rs > 0) {
					flag = true;
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				closeAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return flag;

	}

	public boolean delete(int id) {
		boolean flag = false;

		try {
			String sql = "delete from grade where id=?";
			getPreparedStatement(sql);

			pstat.setInt(1, id);
			int rs = pstat.executeUpdate();

			if (rs > 0) {
				flag = true;
			} else {

				return flag;
			}
			sql = "update student set grade_id = null where grade_id=?";
			getPreparedStatement(sql);
			pstat.setInt(1, id);
			System.out.println(sql);
			rs = pstat.executeUpdate();

			if (rs > 0) {
				flag = true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				closeAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return flag;
	}

	public List<BanJi> searchByCondition(BanJi condition, int begin, int yeNum) {
		List<BanJi> list = new ArrayList<BanJi>();
		try {
			getStatement();

			String where = " where 1=1 ";
			if (condition.getName() != null) {
				where += " and name like '%" + condition.getName() + "%'";
			}

			String sql = "select * from grade " + where + " limit " + begin
					+ "," + yeNum;
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				BanJi bj = new BanJi();
				bj.setId(rs.getInt("id"));
				bj.setName(rs.getString("name"));
				bj.setStuNums(rs.getInt("stuNums"));
				list.add(bj);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				closeAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}

}
