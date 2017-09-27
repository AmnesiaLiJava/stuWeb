package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Pagination;
import dao.BanJiDao;
import entity.BanJi;

public class BanJiServlet extends HttpServlet {
	BanJiDao bjDao = new BanJiDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		String type = request.getParameter("type");

		if (type == null) {

			search(request, response);
		} else if (type.equals("showAdd")) {

			showAdd(request, response);
		} else if (type.equals("add")) {

			add(request, response);
		} else if (type.equals("showModify")) {

			showModify(request, response);
		} else if (type.equals("showModify2")) {
			showModify2(request, response);
		} else if (type.equals("modify")) {
			modify(request, response);
		} else if (type.equals("modify2")) {
			modify2(request, response);
		} else if (type.equals("delete")) {

			delete(request, response);
		} else if (type.equals("search")) {

			search(request, response);
		}
	}

	private void search(HttpServletRequest request, HttpServletResponse response) {
		BanJi condition = new BanJi();
		String name = request.getParameter("name");
		if (!"".equals(name)) {
			condition.setName(name);
		}

		int ye = 1;
		if (request.getParameter("ye") != null) {
			ye = Integer.parseInt(request.getParameter("ye"));
		}

		int max = bjDao.searchCount(condition);

		int yeNum = 2;
		int yeMa = 5;
		Pagination pagination = new Pagination(ye, max, yeNum, yeMa);
		ye = pagination.getYe();
		int begin = (ye - 1) * yeNum;
		List<BanJi> list = bjDao.searchByCondition(condition, begin, yeNum);
		request.setAttribute("p", pagination);
		request.setAttribute("condition", condition);
		request.setAttribute("bjs", list);
		try {
			request.getRequestDispatcher("WEB-INF/banji/list.jsp").forward(
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
			flag = bjDao.delete(Integer.parseInt(ids[i]));

			if (flag == false) {
				break;
			}
		}
		if (flag) {

			try {
				response.sendRedirect("bj");
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

			BanJi bj = new BanJi();
			bj.setId(id);
			bj.setName(name);

			boolean flag = bjDao.modify(bj);
			if (flag) {

				response.sendRedirect("bj");

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void modify2(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			String[] bjStrs = request.getParameter("bjs").split(";");

			List<BanJi> list = new ArrayList<BanJi>();
			for (int i = 0; i < bjStrs.length; i++) {
				String[] bjStr = bjStrs[i].split(",");

				int id = Integer.parseInt(bjStr[0]);

				String name = bjStr[1];

				BanJi bj = new BanJi();
				bj.setId(id);
				bj.setName(name);

				list.add(bj);

			}

			boolean flag = bjDao.modify2(list);
			if (flag) {

				response.sendRedirect("bj");

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
			BanJi bj = bjDao.searchById(Integer.parseInt(ids[0]));

			request.setAttribute("bj", bj);
			request.getRequestDispatcher("WEB-INF/banji/modify.jsp").forward(
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
			List<BanJi> list = bjDao.searchByIds(ids);

			request.setAttribute("bjs", list);
			request.getRequestDispatcher("WEB-INF/banji/modify2.jsp").forward(
					request, response);
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

			BanJi bj = new BanJi();
			bj.setName(name);

			boolean flag = bjDao.add(bj);
			if (flag) {

				response.sendRedirect("bj");

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showAdd(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.getRequestDispatcher("WEB-INF/banji/add.jsp").forward(
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
	// int max = bjDao.searchCount();
	//
	// int yeNum = 5;
	// int yeMa = 5;
	// Pagination pagination = new Pagination(ye, max, yeNum, yeMa);
	// ye = pagination.getYe();
	// int begin = (ye - 1) * yeNum;
	//
	// List<BanJi> list = bjDao.searchByBegin(begin, yeNum);
	//
	// request.setAttribute("bjs", list);
	// request.setAttribute("p", pagination);
	// request.getRequestDispatcher("WEB-INF/banji/list.jsp").forward(
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
