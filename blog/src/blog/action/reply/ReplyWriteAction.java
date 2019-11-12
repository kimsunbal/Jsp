package blog.action.reply;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import blog.action.Action;
import blog.dao.ReplyDao;
import blog.dao.UserDao;
import blog.model.Reply;
import blog.model.User;
import blog.util.Script;

public class ReplyWriteAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("userId")==null||request.getParameter("userId").equals("")) 
			return;
		int id = Integer.parseInt(request.getParameter("userId"));
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		String content =request.getParameter("content");
		Reply reply = new Reply();
		reply.setUserId(id);
		reply.setCommentId(commentId);
		reply.setContent(content);
		System.out.println("ReplyWriteAction>>"+id+"   "+commentId+"   "+content+"   ");
		ReplyDao dao = new ReplyDao();
		UserDao userDao = new UserDao();
		int result = dao.save(reply);
		
		if (result == 1) {
			//db에서 가져와
			reply = dao.findByMaxId();
			reply.getResponseData().setStatusCode(1);
			reply.getResponseData().setStatus("ok");
			reply.getResponseData().setStatusMsg("Write was completed");
			Gson gson = new Gson();
			User user = userDao.findByUserId(id);
			reply.setUser(user);
			System.out.println("reply:  "+reply);
			String replyJSon = gson.toJson(reply);
			PrintWriter out =response.getWriter();
			response.setContentType("application/json");
			System.out.println("json"+replyJSon);
			out.print(replyJSon);
			out.flush();
		} else {
			Script.back(response);
		}
	}
}
