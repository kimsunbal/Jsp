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

public class BoardListAction implements Action {

//	private final static String TAG = "BoardListAction: ";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BoardDao bDao = new BoardDao();
		int count = bDao.CountAll();
		if (request.getParameter("page") == null) return;

		int page = Integer.parseInt(request.getParameter("page"));
		if (page <= 0) {
			page = 1;
			response.sendRedirect("/blog/board?cmd=list&page=" + page);
			return;
		} else if (page > count) {
			response.sendRedirect("/blog/board?cmd=list&page=" + count);
			return;
		}

		// maxNum 버튼 활성화
		List<Board> boards = new ArrayList<Board>();
		boards = bDao.findAll(page);
		List<Board> hotBoards = bDao.findOrderByReadCountDesc();
		Utils.setPreviewImg(boards);
		Utils.setPreviewContent(boards);
		Utils.setPreviewImg(hotBoards);
		request.setAttribute("boards", boards);
		request.setAttribute("hotBoards", hotBoards);
		request.setAttribute("count", count);

		RequestDispatcher dis = request.getRequestDispatcher("/board/list.jsp");
		dis.forward(request, response);

	}
}
