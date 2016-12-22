package old;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HiepHolmes
 */
public class UserDAO {

	
	
	public static Connection getConnetion() throws ClassNotFoundException, SQLException {
		Connection c = null;

		Class.forName("org.postgresql.Driver");
		c = DriverManager.getConnection(Constant.DOMAIN, Constant.USER_NAME, Constant.PASSWORD);
		c.setAutoCommit(false);
		System.out.println("Connect successfully");
		return c;

	}

	/**
	 * @throws ClassNotFoundException
	 *
	 */
	public static List<User> getAllUser() throws ClassNotFoundException {
		List<User> users = new ArrayList<User>();
		try {

			Connection c = null;
			Statement stmt = null;

			c = UserDAO.getConnetion();
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM user_infor");
			while (rs.next()) {
				String name = rs.getString("name");
				String password = rs.getString("password");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				boolean can_view = rs.getBoolean("can_view");
				boolean can_update = rs.getBoolean("can_update");
				boolean can_delete = rs.getBoolean("can_delete");

				User user = new User(name, password, phone, address, can_view, can_update, can_delete);
				users.add(user);

			}
			rs.close();
			stmt.close();
			c.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
			return users;

		}
		return users;
	}

	/**
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * 
	 */
	public static List<User> getUserByPhone(String phoneInput) throws ClassNotFoundException, SQLException {
		List<User> users = new ArrayList<User>();

		Connection c = null;
		Statement stmt = null;

		c = UserDAO.getConnetion();
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM user_infor where phone ='" + phoneInput + "'");
		while (rs.next()) {

			String name = rs.getString("name");
			String password = rs.getString("password");
			String phone = rs.getString("phone");
			String address = rs.getString("address");
			boolean can_view = rs.getBoolean("can_view");
			boolean can_update = rs.getBoolean("can_update");
			boolean can_delete = rs.getBoolean("can_delete");

			User user = new User(name, password, phone, address, can_view, can_update, can_delete);
			users.add(user);

		}
		rs.close();
		stmt.close();
		c.close();

		return users;
	}

	/**
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *
	 */
	public static boolean insertUser(User user) throws ClassNotFoundException, SQLException {

		Connection c = getConnetion();
		// -----------------------------------------
		String updateSql = "INSERT INTO public.user_infor"
				+ "( name, password, phone, address,can_view,can_update, can_delete )"
				+ "	VALUES ( ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement stmt = c.prepareStatement(updateSql);
		stmt.setString(1, user.getName());
		stmt.setString(2, user.getPassword());
		stmt.setString(3, user.getPhone());
		stmt.setString(4, user.getAddress());
		stmt.setBoolean(5, user.isCan_view());
		stmt.setBoolean(6, user.isCan_update());

		stmt.setBoolean(7, user.isCan_delete());

		// -----------------------------------------------
		long rowIdUpdate = stmt.executeUpdate();
		// -----------------------------------------

		stmt.close();
		c.commit();
		c.close();

		return rowIdUpdate == 1;
	}

	/**
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 *
	 */
	public static boolean deleteUser(String phone) throws ClassNotFoundException, SQLException {

		Connection c = null;
		Statement stmt = null;

		c = UserDAO.getConnetion();
		stmt = c.createStatement();
		String sqlDelete = "DELETE from user_infor where phone='" + phone+ "';";
		long rowIdDelete = stmt.executeUpdate(sqlDelete);
		c.commit();
		stmt.close();
		c.close();
		return rowIdDelete== -1;

	}

	/**
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 *
	 */
	public static boolean updateUser(User user) throws ClassNotFoundException, SQLException {

		Connection c = getConnetion();
		// -----------------------------------------
		String updateSql = "update user_infor set name=?,password=?,phone=?,address=?,can_view=?,can_update=?,can_delete=? where phone =?";
		PreparedStatement stmt = c.prepareStatement(updateSql);
		stmt.setString(1, user.getName());
		stmt.setString(2, user.getPassword());
		stmt.setString(3, user.getPhone());
		stmt.setString(4, user.getAddress());
		stmt.setBoolean(6, user.isCan_view());
		stmt.setBoolean(5, user.isCan_update());
		stmt.setBoolean(7, user.isCan_delete());
		stmt.setString(8, user.getPhone());
		// -----------------------------------------------
		long rowIdChange = stmt.executeUpdate();
		// -----------------------------------------

		stmt.close();
		c.commit();
		return rowIdChange == 1;
	}
}
