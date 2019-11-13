package blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import blog.model.User;
import blog.util.DBClose;

public class UserDao {
	// 싱글톤으로 만들어야 하는데 지금은 기본적인 로직만 파악

	private Connection conn; // MySQL과 연결하기 위해 필요
	private PreparedStatement pstmt; // 쿼리문을 작성-실행하기 위해 필요
	private ResultSet rs;

	public int save(User user) {
		final String query = "INSERT into user(username, password, email, address, userProfile, createDate) VALUES (?,?,?,?,?,now())";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getAddress());
			pstmt.setString(5, user.getUserProfile());
			int result = pstmt.executeUpdate();// 변경된 열의 갯수
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return -1;
	}

	public int update(User user) {
		final String query = "UPDATE user SET password=?,address=?,userProfile=? WHERE id=?";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getAddress());
			pstmt.setString(3, user.getUserProfile());
			pstmt.setInt(4, user.getId());
			int result = pstmt.executeUpdate();// 변경된 열의 갯수
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return -1;
	}

	public int login(User user) {
		final String query = "SELECT * FROM user WHERE username =? AND password = ?";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return -1;
	}

	public User findByUsername(String username) {
		final String query = "SELECT * FROM user WHERE username =?";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setAddress(rs.getString("address"));
				user.setCreateDate(rs.getTimestamp("createDate"));
				user.setUserProfile(rs.getString("userProfile"));
				user.setEmailCheck(rs.getString("emailCheck"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return null;
	}

	public User findByUserId(int userId) {
		final String query = "SELECT * FROM user WHERE id =?";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userId);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setAddress(rs.getString("address"));
				user.setCreateDate(rs.getTimestamp("createDate"));
				user.setUserProfile(rs.getString("userProfile"));
				user.setEmailCheck(rs.getString("emailCheck"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return null;
	}

	public int emailCheck(String email) {
		final String query = "UPDATE user SET emailCheck=1 WHERE email=? and emailCheck=0";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, email);

			int result = pstmt.executeUpdate();// 변경된 열의 갯수

			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return -1;
	}

	public int emailCheckById(int id) {
		final String query = "SELECT emailCheck FROM user WHERE id =?";
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				if(rs.getString("emailCheck").equals("1")) {
					return 1;
				}				
			}
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return -1;
	}

}
