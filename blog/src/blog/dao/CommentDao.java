package blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import blog.model.Comment;
import blog.util.DBClose;

public class CommentDao {

	private Connection conn; // MySQL과 연결하기 위해 필요
	private PreparedStatement pstmt; // 쿼리문을 작성-실행하기 위해 필요
	private ResultSet rs;

	public List<Comment> findAll(int boardId) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT c.id, c.userId, c.boardId, c.content, c.createDate, u.username ");
		sb.append("FROM comment c, user u ");
		sb.append("WHERE c.userId= u.id ");
		sb.append("and boardId=?");
		final String SQL = sb.toString();
		conn = DBConn.getConnection();
		try {
			List<Comment> comments = new ArrayList<>();

			pstmt = conn.prepareStatement(SQL);
			System.out.println("boardId" + boardId);
			pstmt.setInt(1, boardId);
			rs = pstmt.executeQuery();
			while (rs.next()) {// rs.next(); 커서이동 return값 boolean
				Comment comment = new Comment();
				comment.setId(rs.getInt("c.id"));
				comment.setBoardId(rs.getInt("c.boardId"));
				comment.setUserId(rs.getInt("c.userId"));
				comment.setContent(rs.getString("c.content"));
				comment.setCreateDate(rs.getTimestamp("c.createDate"));
				comment.getUser().setUsername(rs.getString("u.username"));
				comments.add(comment);
			}

			return comments;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {// 위에서 리턴해도 무조건 실행된다
			DBClose.close(conn, pstmt, rs);
		}
		return null;
	}
	public int save(Comment comment) {
		final String query = "INSERT into comment(userId, boardId, content, createDate) VALUES (?,?,?,now())";
		conn = DBConn.getConnection();
		System.out.println(comment.getContent());
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, comment.getUserId());
			pstmt.setInt(2, comment.getBoardId());
			pstmt.setString(3, comment.getContent());
			int result = pstmt.executeUpdate();// 변경된 열의 갯수
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return -1;
	}
	
	//동시 접근을 막는다 (임계구역)
	synchronized public Comment findByMaxId() {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT c.id, c.userId, c.boardId, c.content, c.createDate, u.username ");
		sb.append("FROM comment c, user u ");
		sb.append("WHERE c.userId= u.id ");
		sb.append("AND c.id=(select max(id) from comment)");
		final String SQL = sb.toString();
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {// rs.next(); 커서이동 return값 boolean
				Comment comment = new Comment();
				comment.setId(rs.getInt("c.id"));
				comment.setBoardId(rs.getInt("c.boardId"));
				comment.setUserId(rs.getInt("c.userId"));
				comment.setContent(rs.getString("c.content"));
				comment.setCreateDate(rs.getTimestamp("c.createDate"));
				comment.getUser().setUsername(rs.getString("u.username"));
				return comment;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {// 위에서 리턴해도 무조건 실행된다
			DBClose.close(conn, pstmt, rs);
		}
		return null;
	}
	public int delete(int id) {
		final String query = "DELETE FROM comment WHERE id=?";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			int result = pstmt.executeUpdate();// 변경된 열의 갯수
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return -1;
	}
}
