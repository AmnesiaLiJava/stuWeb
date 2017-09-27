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
import entity.Student;

public class StudentDao extends BaseDao {

	public int searchCount() {
		int count = 0;
		try {
			getStatement();
			Statement stat = conn.createStatement();

			String sql = "select count(id) from student";
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

	public int searchCount(Student condition) {
		int count = 0;
		try {
			getStatement();
			Statement stat = conn.createStatement();
			String where = " where 1=1 ";
			if (condition.getName() != null) {
				where += " and s.name like '%" + condition.getName() + "%'";
			}
			if (condition.getSex() != null) {
				where += " and sex='" + condition.getSex() + "'";

			}

			if (condition.getAge() != -1) {
				where += " and age=" + condition.getAge() + "";

			}

			String sql = "select count(s.id) from student as s left join grade as bj on s.grade_id=bj.id "
					+ where;
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

	public List<Student> searchByBegin(int begin, int num) {
		List<Student> list = new ArrayList<Student>();

		try {
			getStatement();
			Statement stat = conn.createStatement();

			String sql = "select * from student limit " + begin + "," + num;
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Student stu = new Student();
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				stu.setSex(rs.getString("sex"));
				stu.setAge(rs.getInt("age"));
				list.add(stu);
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

	public List<Student> searchAll() {
		List<Student> list = new ArrayList<Student>();
		try {
			getStatement();

			String sql = "select * from student";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Student stu = new Student();
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				stu.setSex(rs.getString("sex"));
				stu.setAge(rs.getInt("age"));
				list.add(stu);
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

	public boolean add(Student stu) {
		boolean flag = false;

		try {
			String sql = "insert into student (name,sex,age,bj_id) "
					+ " values(?,?,?,?)";
			getPreparedStatement(sql);
			pstat.setString(1, stu.getName());
			pstat.setString(2, stu.getSex());
			pstat.setInt(3, stu.getAge());
			pstat.setInt(4, stu.getBj().getId());

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

	public Student searchById(int id) {
		Student stu = null;
		try {
			getStatement();

			String sql = "select * from student where id=" + id;
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				stu = new Student();
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				stu.setSex(rs.getString("sex"));
				stu.setAge(rs.getInt("age"));
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
		return stu;
	}

	public List<Student> searchByIds(String ids) {
		List<Student> list = new ArrayList<Student>();
		try {
			getStatement();

			String sql = "select s.*,bj.id as bjId,bj.name as bjName from student as s left join grade as bj on s.grade_id =bj.id where s.id in( "
					+ ids + " )";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Student stu = new Student();
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				stu.setSex(rs.getString("sex"));
				stu.setAge(rs.getInt("age"));
				BanJi bj = new BanJi();
				bj.setId(rs.getInt("bjId"));
				bj.setName(rs.getString("bjName"));
				stu.setBj(bj);
				list.add(stu);
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

	public boolean modify(Student stu) {
		boolean flag = false;

		try {
			String sql = "update student set name=?,sex=?,age=? where id=?";
			getPreparedStatement(sql);
			pstat.setString(1, stu.getName());
			pstat.setString(2, stu.getSex());
			pstat.setInt(3, stu.getAge());
			pstat.setInt(4, stu.getId());
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

	public boolean modify2(List<Student> list) {
		boolean flag = false;

		try {
			for (int i = 0; i < list.size(); i++) {
				Student stu = list.get(i);
				String sql = "update student set name=?,sex=?,age=?,grade_id=? where id=?";
				getPreparedStatement(sql);
				pstat.setString(1, stu.getName());
				pstat.setString(2, stu.getSex());
				pstat.setInt(3, stu.getAge());
				if (stu.getBj().getId() == 0) {
					pstat.setObject(4, null);
				} else {
					pstat.setInt(4, stu.getBj().getId());
				}
				pstat.setInt(5, stu.getId());
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
			String sql = "delete from student where id=?";
			getPreparedStatement(sql);

			pstat.setInt(1, id);
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

	public List<Student> searchByCondition(Student condition, int begin,
			int yeNum) {
		List<Student> list = new ArrayList<Student>();
		try {
			getStatement();

			String where = " where 1=1 ";
			if (condition.getName() != null) {
				where += " and s.name like '%" + condition.getName() + "%'";
			}
			if (condition.getSex() != null) {
				where += " and sex='" + condition.getSex() + "'";

			}

			if (condition.getAge() != -1) {
				where += " and age=" + condition.getAge() + "";

			}
			String sql = "select s.*,bj.id as bjId,bj.name as bjName from student as s left join grade  as bj on grade_id=bj.id "
					+ where + " limit " + begin + "," + yeNum;
			System.out.println(sql);
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Student stu = new Student();
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				stu.setSex(rs.getString("sex"));
				stu.setAge(rs.getInt("age"));
				BanJi bj = new BanJi();
				bj.setId(rs.getInt("bjId"));
				bj.setName(rs.getString("bjName"));
				stu.setBj(bj);
				list.add(stu);
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
