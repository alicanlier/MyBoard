package org.vision.tboard;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	private DataSource dataFactory;
	Connection conn;
	PreparedStatement pstmt;

	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List selectAllArticles(Map pagingMap) {
		List articlesList = new ArrayList();
		int section = (Integer) pagingMap.get("section");
		int pageNum = (Integer) pagingMap.get("pageNum");
		try {
			conn = dataFactory.getConnection();
//			String query = "SELECT * FROM ( " + "select ROWNUM  as recNum," + "LVL," + "articleNO," + "parentNO,"
//					+ "title," + "id," + "writeDate" + " from (select LEVEL as LVL, " + "articleNO," + "parentNO,"
//					+ "title," + "id," + "writeDate" + " from t_board" + " START WITH  parentNO=0"
//					+ " CONNECT BY PRIOR articleNO = parentNO" + "  ORDER SIBLINGS BY articleNO DESC)" + ") "
//					// 페이징 처리의 핵심 로직 부분
//					+ " where recNum between(?-1)*100+(?-1)*10+1 and (?-1)*100+?*10";
			String query = "SELECT * FROM ( " + "select ROWNUM  as recNum," + "LVL," + "num," + "ref,"
					+ "subject," + "writer," + "reg_date" + " from (select LEVEL as LVL, " + "num," + "ref,"
					+ "subject," + "writer," + "reg_date" + " from t_board" + " START WITH  ref=0"
					+ " CONNECT BY PRIOR num = ref" + "  ORDER SIBLINGS BY num DESC)" + ") "
					// 페이징 처리의 핵심 로직 부분
					+ " where recNum between(?-1)*100+(?-1)*10+1 and (?-1)*100+?*10";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, section);
			pstmt.setInt(2, pageNum);
			pstmt.setInt(3, section);
			pstmt.setInt(4, pageNum);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int level = rs.getInt("lvl");
				int articleNO = rs.getInt("num"); //articleNO
				int parentNO = rs.getInt("ref"); //parentNO
				String title = rs.getString("subject");//title
				String id = rs.getString("writer");//id
				Date writeDate = rs.getDate("reg_date");//writeDate
				ArticleVO article = new ArticleVO();
				article.setLevel(level);
				article.setArticleNO(articleNO);
				article.setParentNO(parentNO);
				article.setTitle(title);
				article.setId(id);
				article.setWriteDate(writeDate);
				articlesList.add(article);
			} // end while
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articlesList;
	}

	public List selectAllArticles() {
		List articlesList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
//			String query = "SELECT LEVEL,articleNO,parentNO,title,content,id,writeDate" + " from t_board"
//					+ " START WITH  parentNO=0" + " CONNECT BY PRIOR articleNO=parentNO"
//					+ " ORDER SIBLINGS BY articleNO DESC";
			String query = "SELECT LEVEL,num,ref,subject,content,writer,reg_date" + " from t_board"
					+ " START WITH  ref=0" + " CONNECT BY PRIOR num=ref"
					+ " ORDER SIBLINGS BY num DESC";
			System.out.println(query);
			pstmt = conn.prepareStatement("selectAllArticles() query: "+query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int level = rs.getInt("level");
				int articleNO = rs.getInt("num");//articleNO
				int parentNO = rs.getInt("ref");//parentNO
				String title = rs.getString("subject");//title
				String content = rs.getString("content");
				String id = rs.getString("writer");//id
				Date writeDate = rs.getDate("reg_date");//writeDate
				ArticleVO article = new ArticleVO();
				article.setLevel(level);
				article.setArticleNO(articleNO);
				article.setParentNO(parentNO);
				article.setTitle(title);
				article.setContent(content);
				article.setId(id);
				article.setWriteDate(writeDate);
				articlesList.add(article);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articlesList;
	}

	// SEQ_BOARD.NEXTVAL
	private int getNewArticleNO() {
		int nextNum = 0;
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT  max(num) from t_board "; //articleNO
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
			if (rs.next()) {
				nextNum = (rs.getInt(1) + 1);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nextNum;
	}

	public int insertNewArticle(ArticleVO article) {
		int articleNO = getNewArticleNO();
		try {
			conn = dataFactory.getConnection();
			int parentNO = article.getParentNO();
			String title = article.getTitle();
			String content = article.getContent();
			String id = article.getId();
			String imageFileName = article.getImageFileName();
//			String query = "INSERT INTO t_board (articleNO, parentNO, title, content, imageFileName, id)"
//					+ " VALUES (?, ? ,?, ?, ?, ?)";
			String query = "INSERT INTO t_board (num, ref, subject, content, imageFileName, writer)"
					+ " VALUES (?, ? ,?, ?, ?, ?)";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			pstmt.setInt(2, parentNO);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, imageFileName);
			pstmt.setString(6, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return articleNO;
	}

	public ArticleVO selectArticle(int articleNO) {
		ArticleVO article = new ArticleVO();
		try {
			conn = dataFactory.getConnection();
//			String query = "select articleNO,parentNO,title,content, NVL(imageFileName, 'null') as imageFileName, id, writeDate"
//					+ " from t_board" + " where articleNO=?";
			String query = "select num,ref,subject,content, NVL(imageFileName, 'null') as imageFileName, writer, reg_date"
					+ " from t_board" + " where num=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int _articleNO = rs.getInt("num");//articleNO
			int parentNO = rs.getInt("ref");//parentNO
			String title = rs.getString("subject");//title
			String content = rs.getString("content");
			String imageFileName = URLEncoder.encode(rs.getString("imageFileName"), "UTF-8"); // �����̸��� Ư�����ڰ� ����
																								// ��� ���ڵ��մϴ�.
			if (imageFileName.equals("null")) {
				imageFileName = null;
			}

			String id = rs.getString("writer");//id
			Date writeDate = rs.getDate("reg_date");//writeDate

			article.setArticleNO(_articleNO);
			article.setParentNO(parentNO);
			article.setTitle(title);
			article.setContent(content);
			article.setImageFileName(imageFileName);
			article.setId(id);
			article.setWriteDate(writeDate);
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return article;
	}

	public void updateArticle(ArticleVO article) {
		int articleNO = article.getArticleNO();
		String title = article.getTitle();
		String content = article.getContent();
		String imageFileName = article.getImageFileName();
		try {
			conn = dataFactory.getConnection();
//			String query = "update t_board  set title=?,content=?";
			String query = "update t_board  set subject=?,content=?";
			if (imageFileName != null && imageFileName.length() != 0) {
				query += ",imageFileName=?";
			}
//			query += " where articleNO=?";
			query += " where num=?";

			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			if (imageFileName != null && imageFileName.length() != 0) {
				pstmt.setString(3, imageFileName);
				pstmt.setInt(4, articleNO);
			} else {
				pstmt.setInt(3, articleNO);
			}
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteArticle(int articleNO) {
		try {
			conn = dataFactory.getConnection();
			String query = "DELETE FROM t_board ";
//			query += " WHERE articleNO in (";
//			query += "  SELECT articleNO FROM  t_board ";
//			query += " START WITH articleNO = ?";
//			query += " CONNECT BY PRIOR  articleNO = parentNO )";
			query += " WHERE num in (";
			query += "  SELECT num FROM  t_board ";
			query += " START WITH num = ?";
			query += " CONNECT BY PRIOR  num = ref )";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Integer> selectRemovedArticles(int articleNO) {
		List<Integer> articleNOList = new ArrayList<Integer>();
		try {
			conn = dataFactory.getConnection();
//			String query = "SELECT articleNO FROM  t_board  ";
//			query += " START WITH articleNO = ?";
//			query += " CONNECT BY PRIOR  articleNO = parentNO";
			String query = "SELECT num FROM  t_board  ";
			query += " START WITH num = ?";
			query += " CONNECT BY PRIOR  num = ref";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				articleNO = rs.getInt("num");//articleNO
				articleNOList.add(articleNO);
			}
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleNOList;
	}

	public int selectTotArticles() {
		try {
			conn = dataFactory.getConnection();
//			String query = "select count(articleNO) from t_board ";
			String query = "select count(num) from t_board ";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				return (rs.getInt(1));
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}