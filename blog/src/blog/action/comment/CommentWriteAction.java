package blog.action.comment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import blog.action.Action;
import blog.dao.CommentDao;
import blog.dao.UserDao;
import blog.model.Comment;
import blog.model.User;
import blog.util.Script;

public class CommentWriteAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("userId")==null||request.getParameter("userId").equals("")) 
			return;
		int id = Integer.parseInt(request.getParameter("userId"));
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		String content =request.getParameter("content");
		Comment comment = new Comment();
		comment.setUserId(id);
		comment.setBoardId(boardId);
		comment.setContent(content);
		
		CommentDao dao = new CommentDao();
		UserDao userDao = new UserDao();
		int result = dao.save(comment);

		System.out.println("comment write comment:  "+comment);
		
		if (result == 1) {
			//db에서 가져와
			comment = dao.findByMaxId();
			comment.getResponseData().setStatusCode(1);
			comment.getResponseData().setStatus("ok");
			comment.getResponseData().setStatusMsg("Write was completed");
			Gson gson = new Gson();
			User user = userDao.findByUserId(id);
			comment.setUser(user);
			System.out.println("comment write comment2:  "+comment);
			String commentJSon = gson.toJson(comment);

			PrintWriter out =response.getWriter();
			response.setContentType("application/json");
			out.print(commentJSon);
			out.flush();
		} else {
			Script.back(response);
		}
	}
}
