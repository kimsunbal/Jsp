package blog.action.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.action.Action;
import blog.dao.BoardDao;
import blog.model.Board;
import blog.util.Utils;

public class BoardSearchAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rawcontent = request.getParameter("content");
		String content = "%" + request.getParameter("content") + "%";
		int page = Integer.parseInt(request.getParameter("page"));
		if (request.getParameter("page") == null)
			return;
		System.out.println(page + "번 페이지");

		BoardDao dao = new BoardDao();

		int result = dao.searchCount(content);
		// 최대 페이지
		int count = 0;
		if (result % 3 == 0) {
			count = result / 3;
		} else {
			count = result / 3 + 1;
		}

		if (page <= 0) {
			page = 1;
		} else if (page > count) {
			page = count;
		}

		System.out.println("최대 페이지는 " + count + "입니다.");
		// 검색된 보드 수
		System.out.println(result + "건이 검색되었습니다");

		// 페이지
		List<Board> boards = new ArrayList<Board>();
		boards = dao.searchBoard(content, page);
		List<Board> hotBoards = dao.findOrderByReadCountDesc();
		Utils.setPreviewImg(boards);
		Utils.setPreviewContent(boards);
		Utils.setPreviewImg(hotBoards);
		request.setAttribute("result", result);
		request.setAttribute("boards", boards);
		request.setAttribute("hotBoards", hotBoards);
		request.setAttribute("count", count);
		request.setAttribute("searchContent", rawcontent);

		RequestDispatcher dis = request.getRequestDispatcher("/board/searchList.jsp");
		dis.forward(request, response);

	}
}
