package blog.action.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import blog.action.Action;
import blog.dao.UserDao;
import blog.model.User;
import blog.util.SHA256;
import blog.util.Script;

public class UserJoinAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		목적: form 태그에 있는 name 값을 받아서 DB에 insert하고 나서 페이지 이동
		String path = request.getServletContext().getRealPath("media");
		MultipartRequest multi = new MultipartRequest(request, path, 1024 * 1024 * 2, "UTF-8",
				new DefaultFileRenamePolicy() // 동일한 파일명이 들어오면 파일명 뒤에 숫자를 붙임.
		);
		// null값 처리하기,유효성 검사(나중에)
		String username = multi.getParameter("username");
		String rawPassword = multi.getParameter("password");
		String email = multi.getParameter("email");
		String address = multi.getParameter("address");
		String password = SHA256.getEncrypt(rawPassword, "cos");

		System.out.println("path>> " + path);

		String filename = multi.getFilesystemName("userProfile");// 정책에 의해서 변경된 이름
//		String originFilename = multi.getOriginalFileName("userProfile");
		String contextpath = request.getServletContext().getContextPath();
		String filepath = contextpath + "/media/" + filename;

		System.out.println("filepath>> " + filepath);

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);// Encryption(암호화)해야한다.
		user.setEmail(email);
		user.setAddress(address);
		user.setUserProfilepath(filepath);
		// DAO 연결하기
		UserDao dao = new UserDao();
		int result = dao.save(user);

		if (result == 1) {
			request.setAttribute("user", user);
			RequestDispatcher dis = request.getRequestDispatcher("/api/gmail");
			dis.forward(request, response);
		} else {
			Script.back(response);
		}

	}
}
