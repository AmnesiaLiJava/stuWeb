package servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Pagination;
import dao.BanJiDao;
import dao.StudentDao;
import entity.BanJi;
import entity.Student;

public class StudentServlet extends HttpServlet {
	StudentDao stuDao = new StudentDao();
	BanJiDao bjDao = new BanJiDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		try {
			Method method = this.getClass().getDeclaredMethod(type,
					HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);

		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	private void search(HttpServletRequest request, HttpServletResponse response) {
		Student condition = new Student();
		String name = request.getParameter("name");
		if (!"".equals(name)) {
			condition.setName(name);
		}
		String sex = request.getParameter("sex");
		if ("ÄÐ".equals(sex) || "Å®".equals(sex)) {
			condition.setSex(sex);
		}
		if (null == request.getParameter("age")
				|| "".equals(request.getParameter("age"))) {
			condition.setAge(-1);
		} else {
			int age = Integer.parseInt(request.getParameter("age"));
			condition.setAge(age);
		}
		int ye = 1;
		if (request.getParameter("ye") != null) {
			ye = Integer.parseInt(request.getParameter("ye"));
		}

		int max = stuDao.searchCount(condition);

		int yeNum = 2;
		int yeMa = 5;
		Pagination pagination = new Pagination(ye, max, yeNum, yeMa);
		ye = pagination.getYe();
		int begin = (ye - 1) * yeNum;
		List<Student> list = stuDao.searchByCondition(condition, begin, yeNum);
		request.setAttribute("p", pagination);
		request.setAttribute("condition", condition);
		request.setAttribute("stus", list);
		try {
			request.getRequestDispatcher("WEB-INF/student/list.jsp").forward(
					request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		
		String[] ids = request.getParameter("selectId").split(",");
		boolean flag = false;
		for (int i = 0; i < ids.length; i++) {
			flag = stuDao.delete(Integer.parseInt(ids[i]));

			if (flag == false) {
				break;
			}
		}
		if (flag) {

			try {
				response.sendRedirect("stu?type=search");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private void modify(HttpServletRequest request, HttpServletResponse response) {
		try {

			int id = Integer.parseInt(request.getParameter("id"));

			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			int age = Integer.parseInt(request.getParameter("age"));

			Student stu = new Student();
			stu.setId(id);
			stu.setName(name);
			stu.setSex(sex);
			stu.setAge(age);
			boolean flag = stuDao.modify(stu);
			if (flag) {

				response.sendRedirect("stu?type=search");

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void modify2(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			String[] stuStrs = request.getParameter("stus").split(";");

			List<Student> list = new ArrayList<Student>();
			for (int i = 0; i < stuStrs.length; i++) {
				String[] stuStr = stuStrs[i].split(",");

				int id = Integer.parseInt(stuStr[0]);

				String name = stuStr[1];
				String sex = stuStr[2];
				int age = Integer.parseInt(stuStr[3]);
				Student stu = new Student();
				stu.setId(id);
				stu.setName(name);
				stu.setSex(sex);
				stu.setAge(age);
				int bj_id = Integer.parseInt(stuStr[4]);
				BanJi bj = new BanJi();
				bj.setId(bj_id);
				stu.setBj(bj);

				list.add(stu);

			}

			boolean flag = stuDao.modify2(list);
			if (flag) {

				response.sendRedirect("stu?type=search");

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showModify(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String[] ids = request.getParameter("selectId").split(",");
			Student stu = stuDao.searchById(Integer.parseInt(ids[0]));

			request.setAttribute("stu", stu);
			request.getRequestDispatcher("WEB-INF/student/modify.jsp").forward(
					request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showModify2(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String ids = request.getParameter("selectId");
			List<Student> list = stuDao.searchByIds(ids);
			List<BanJi> bjList = bjDao.searchAll();
			request.setAttribute("bjs", bjList);
			request.setAttribute("stus", list);
			request.getRequestDispatcher("WEB-INF/student/modify2.jsp")
					.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) {
		try {

			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			int age = Integer.parseInt(request.getParameter("age"));
			int bjId = Integer.parseInt(request.getParameter("bj"));

			Student stu = new Student();
			stu.setName(name);
			stu.setSex(sex);
			stu.setAge(age);
			BanJi bj = new BanJi();
			bj.setId(bjId);
			stu.setBj(bj);
			boolean flag = stuDao.add(stu);
			if (flag) {

				response.sendRedirect("stu?type=search");

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showAdd(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			List<BanJi> list = bjDao.searchAll();
			request.setAttribute("bjs", list);
			request.getRequestDispatcher("WEB-INF/student/add.jsp").forward(
					request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// private void show(HttpServletRequest request, HttpServletResponse
	// response) {
	// try {
	//
	// int ye = 1;
	// if (request.getParameter("ye") != null) {
	// ye = Integer.parseInt(request.getParameter("ye"));
	// }
	//
	// int max = stuDao.searchCount();
	//
	// int yeNum = 5;
	// int yeMa = 5;
	// Pagination pagination = new Pagination(ye, max, yeNum, yeMa);
	// ye = pagination.getYe();
	// int begin = (ye - 1) * yeNum;
	//
	// List<Student> list = stuDao.searchByBegin(begin, yeNum);
	//
	// request.setAttribute("stus", list);
	// request.setAttribute("p", pagination);
	// request.getRequestDispatcher("WEB-INF/student/list.jsp").forward(
	// request, response);
	// } catch (ServletException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
