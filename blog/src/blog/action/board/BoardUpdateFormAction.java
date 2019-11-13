package blog.action.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.action.Action;
import blog.dao.BoardDao;
import blog.model.Board;
import blog.util.Script;
import blog.util.Utils;

public class BoardUpdateFormAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") == null || request.getParameter("id").equals(""))
			return;
		int id = Integer.parseInt(request.getParameter("id"));
		BoardDao dao = new BoardDao();
		Board board = dao.findById(id);
		if (board != null) {
			int result = dao.increaseReadCount(id);
			if (result == 1) {
				// 유튜브 주소 파싱
				Utils.setPreviewYoutube(board);
				// board를 request에 담고 dispatcher로 이동
				request.setAttribute("board", board);
				RequestDispatcher dis = request.getRequestDispatcher("/board/updateForm.jsp");
				dis.forward(request, response);
			} else {
				Script.back(response);
			}
		} else {
			Script.back(response);
		}

	}
}
