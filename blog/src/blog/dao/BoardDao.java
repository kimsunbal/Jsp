package blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import blog.model.Board;
import blog.util.DBClose;

public class BoardDao {
	// 싱글톤으로 만들어야 하는데 지금은 기본적인 로직만 파악

	private Connection conn; // MySQL과 연결하기 위해 필요
	private PreparedStatement pstmt; // 쿼리문을 작성-실행하기 위해 필요
	private ResultSet rs;

	public int save(Board board) {
		final String query = "INSERT into board2(userId, title, content,searchContent, createDate) VALUES (?,?,?,?,now())";
		conn = DBConn.getConnection();
		System.out.println(board.getContent());
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, board.getUserId());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getSearchContent());
			int result = pstmt.executeUpdate();// 변경된 열의 갯수
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return -1;
	}

	public int update(Board board) {
		final String query = "UPDATE board2 SET title=?,content=? WHERE id=?";
		conn = DBConn.getConnection();
		System.out.println(board.getContent());
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getId());
			System.out.println("board id >>>>>>" + board.getId());
			int result = pstmt.executeUpdate();// 변경된 열의 갯수
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return -1;
	}

	public int delete(Board board) {
		final String query = "DELETE FROM board2 WHERE id=?";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, board.getId());
			int result = pstmt.executeUpdate();// 변경된 열의 갯수
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return -1;
	}

	// 리스트보기
	public List<Board> findAll() {
		final String SQL = "SELECT * FROM board2 ORDER BY id DESC";
		conn = DBConn.getConnection();
		try {
			List<Board> boards = new ArrayList<>();

			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {// rs.next(); 커서이동 return값 boolean
				Board board = new Board();
				board.setId(rs.getInt("id"));
				board.setUserId(rs.getInt("userId"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content")+"");
				board.setReadCount(rs.getInt("readCount"));
				board.setCreateDate(rs.getTimestamp("createDate"));
				boards.add(board);
			}
			return boards;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return null;
	}
	
	
	//page count
	public int CountAll() {
		final String SQL = "SELECT * FROM board2";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			int count=0;
			while (rs.next()) {// rs.next(); 커서이동 return값 boolean
				count++;
			}
			int realCount = 0;
			if (count%3==0) {
				realCount = count/3;
			}else {
				realCount = count/3+1;
			}
			return realCount;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return -1;
	}

	//페이징
	public List<Board> findAll(int page) {
		final String SQL = "SELECT * FROM board2 b, user u where b.userId = u.id ORDER BY b.id DESC limit ?, 3";
		conn = DBConn.getConnection();
		try {
			List<Board> boards = new ArrayList<>();

			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, (page - 1) * 3);
			rs = pstmt.executeQuery();
			while (rs.next()) {// rs.next(); 커서이동 return값 boolean
				Board board = new Board();
				board.setId(rs.getInt("b.id"));
				board.setUserId(rs.getInt("b.userId"));
				board.setTitle(rs.getString("b.title"));
				board.setContent(rs.getString("b.content"));
				board.setReadCount(rs.getInt("b.readCount"));
				board.setCreateDate(rs.getTimestamp("b.createDate"));
				board.getUser().setUsername(rs.getString("u.username"));
				boards.add(board);				
			}
			
			return boards;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return null;
	}

	// 상세보기

	
	public Board findById(int id) {
		final String SQL = "select * from board2 b, user u where b.userId =u.id and b.id=?";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {// rs.next(); 커서이동 return값 boolean
				Board board = new Board();
				board.setId(rs.getInt("b.id"));
				board.setUserId(rs.getInt("b.userId"));
				board.setTitle(rs.getString("b.title"));
				board.setContent(rs.getString("b.content"));
				board.setReadCount(rs.getInt("b.readCount"));
				board.setCreateDate(rs.getTimestamp("b.createDate"));
				board.getUser().setUsername(rs.getString("u.username"));

				return board;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return null;
	}


//	조회수 증가
	public int increaseReadCount(int id) {
		final String SQL = "UPDATE board2 SET readCount = readCount + 1 WHERE id = ?";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return -1;
	}
//
////	조회수 증가 안함
//	public int NotIncreaseReadCount(int id) {
//		final String SQL = "UPDATE board2 SET readCount = readCount WHERE id = ?";
//		conn = DBConn.getConnection();
//		try {
//			pstmt = conn.prepareStatement(SQL);
//			pstmt.setInt(1, id);
//			int result = pstmt.executeUpdate();
//			return result;
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			DBClose.close(conn, pstmt, rs);
//		}
//		return -1;
//	}

	
	public List<Board> findOrderByReadCountDesc() {
		final String SQL = "SELECT * FROM board2 ORDER BY readCount DESC limit 3";
		conn = DBConn.getConnection();
		try {
			List<Board> boards = new ArrayList<>();

			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {// rs.next(); 커서이동 return값 boolean
				Board board = new Board();
				board.setId(rs.getInt("id"));
				board.setUserId(rs.getInt("userId"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setReadCount(rs.getInt("readCount"));
				board.setCreateDate(rs.getTimestamp("createDate"));
				boards.add(board);
			}
			return boards;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return null;
	}
	
	public int searchCount(String searchContent) {
		final String query = "SELECT b.id, b.userId, u.username, b.title, b.content FROM board2 b, user u WHERE b.userId= u.id and (b.searchContent LIKE ? OR b.title LIKE ? OR u.username LIKE ?)";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchContent);
			pstmt.setString(2, searchContent);
			pstmt.setString(3, searchContent);
			rs = pstmt.executeQuery();
			int count = 0;
			while (rs.next()) {// rs.next(); 커서이동 return값 boolean
				count++;
			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return -1;
	}

	public List<Board> searchBoard(String searchContent, int page) {
		final String query = "SELECT b.id, b.userId, u.username, b.title, b.content,b.readCount,b.createDate FROM board2 b, user u WHERE b.userId= u.id and (b.searchContent LIKE ? OR b.title LIKE ? OR u.username LIKE ?) ORDER BY b.id DESC limit ?, 3";
		conn = DBConn.getConnection();
		List<Board> boards = new ArrayList<>();
		try {			

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchContent);
			pstmt.setString(2, searchContent);
			pstmt.setString(3, searchContent);
			pstmt.setInt(4, (page-1) * 3);
			rs = pstmt.executeQuery();
			while (rs.next()) {// rs.next(); 커서이동 return값 boolean
				Board board = new Board();
				board.setId(rs.getInt("b.id"));
				board.setUserId(rs.getInt("b.userId"));
				board.setTitle(rs.getString("b.title"));
				board.setContent(rs.getString("b.content"));
				board.setReadCount(rs.getInt("b.readCount"));
				board.setCreateDate(rs.getTimestamp("b.createDate"));
				boards.add(board);
			}
			return boards;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return boards;
	}
	
}
