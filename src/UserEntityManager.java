import java.awt.Color;
import java.awt.Point;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserEntityManager {
	private User resultUser = null;
	private User user;

	public UserEntityManager() {
		super();
	}

	public UserEntityManager(User user) {
		this.user = user;
	}

	public boolean addUser(User user) throws SQLException {
		String query = null;
		Statement stmt = SqlConnection.getConnection().createStatement();
		ResultSet rsUser = stmt
				.executeQuery("SELECT * FROM `HASHTEST`.USER WHERE USERNAME='" + user.getUsername() + "';");
		if (!rsUser.next()) {
			query = "INSERT INTO `HASHTEST`.USER (`NAME`, `FAMILY`, `USERNAME`, `PASSWORD`) VALUES ('" + user.getName()
					+ "', '" + user.getFamily() + "', '" + user.getUsername() + "','" + user.getPassword() + "');";
			if (update(query))
				return true;
		}
		return false;
	}

	public User userExist(User user) throws SQLException {

		Statement stmt = SqlConnection.getConnection().createStatement();
		ResultSet rsUser = stmt.executeQuery("SELECT * FROM `HASHTEST`.`USER` WHERE USERNAME='" + user.getUsername()
				+ "' and PASSWORD='" + user.getPassword() + "';");
		if (rsUser.next()) {
			String name = rsUser.getString(1);
			String family = rsUser.getString(2);
			String username = rsUser.getString(3);
			String password = rsUser.getString(4);
			resultUser = new User(name, family, username, password);
		}
		return resultUser;
	}

	public void saveShape(Shape shape) throws SQLException {
		String query = null;
		if (shape instanceof Line) {
			Line line = (Line) shape;
			query = "INSERT INTO `line` (`x1`, `y1`, `x2`, `y2`, `color`, `username`) VALUES ('"
					+ line.getStart().getX() + "', '" + line.getStart().y + "', '" + line.getEnd().x + "', '"
					+ line.getEnd().y + "', '" + Paint.colorToString(line.getColor()) + "', '"
					+ shape.getUser().getUsername() + "');";
		} else if (shape instanceof Circle) {
			Circle circle = (Circle) shape;
			query = "INSERT INTO `circle` (`x1`, `y1`, `x2`, `y2`, `color`, `username`) VALUES ('" + circle.getStart().x
					+ "', '" + circle.getStart().y + "', '" + circle.getEnd().getX() + "', '" + circle.getEnd().getY()
					+ "', '" + Paint.colorToString(circle.getColor()) + "', '" + shape.getUser().getUsername() + "');";
		} else if (shape instanceof Rectangle) {
			Rectangle rectangle = (Rectangle) shape;
			query = "INSERT INTO `rectangle` (`x1`, `y1`, `x2`, `y2`, `color`, `username`) VALUES ('"
					+ rectangle.getStart().x + "', '" + rectangle.getStart().y + "', '" + rectangle.getEnd().getX()
					+ "', '" + rectangle.getEnd().getY() + "', '" + Paint.colorToString(rectangle.getColor()) + "', '"
					+ shape.getUser().getUsername() + "');";
		} else if (shape instanceof RoundRectangle) {
			RoundRectangle roundRec = (RoundRectangle) shape;
			query = "INSERT INTO `round_rectangle` (`x1`, `y1`, `x2`, `y2`, `color`, `username`) VALUES ('"
					+ roundRec.getStart().x + "', '" + roundRec.getStart().y + "', '" + roundRec.getEnd().getX()
					+ "', '" + roundRec.getEnd().getY() + "', '" + Paint.colorToString(roundRec.getColor()) + "', '"
					+ shape.getUser().getUsername() + "');";
		}
		update(query);
	}

	private boolean update(String query) throws SQLException {
		Statement stmt = SqlConnection.getConnection().createStatement();
		try {
			stmt.executeUpdate(query);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public ArrayList<Shape> loadShape() throws SQLException {
		ArrayList<Shape> shapes = new ArrayList<>();
		addRectangle(shapes);
		addLine(shapes);
		addCircle(shapes);
		addRoundRectangle(shapes);
		return shapes;
	}

	private void addRectangle(ArrayList<Shape> shapes) throws SQLException {
		String query = "SELECT * FROM `hashtest`.`rectangle` where `username` = '" + user.getUsername() + "';";
		Statement stmt = SqlConnection.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(query);
		if (rs != null) {
			while (rs.next()) {
				int x1 = rs.getInt("x1");
				int y1 = rs.getInt("y1");
				int x2 = rs.getInt("x2");
				int y2 = rs.getInt("y2");
				Color color = Paint.strToColor(rs.getString("color"));
				shapes.add(new Rectangle(color, new Point(x1, y1), new Point(x2, y2), user));
			}
		}
	}

	private void addLine(ArrayList<Shape> shapes) throws SQLException {
		String query = "SELECT * FROM `hashtest`.`line` where `username`= '" + user.getUsername() + "';";
		Statement stmt = SqlConnection.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(query);
		if (rs != null) {
			while (rs.next()) {
				int x1 = rs.getInt("x1");
				int y1 = rs.getInt("y1");
				int x2 = rs.getInt("x2");
				int y2 = rs.getInt("y2");
				Color color = Paint.strToColor(rs.getString("color"));
				shapes.add(new Line(color, new Point(x1, y1), new Point(x2, y2), user));
			}
		}
	}

	private void addRoundRectangle(ArrayList<Shape> shapes) throws SQLException {
		String query = "SELECT * FROM `hashtest`.`round_rectangle` where `username`= '" + user.getUsername() + "';";
		Statement stmt = SqlConnection.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(query);
		if (rs != null) {
			while (rs.next()) {
				int x1 = rs.getInt("x1");
				int y1 = rs.getInt("y1");
				int x2 = rs.getInt("x2");
				int y2 = rs.getInt("y2");
				Color color = Paint.strToColor(rs.getString("color"));
				shapes.add(new RoundRectangle(color, new Point(x1, y1), new Point(x2, y2), user));
			}
		}
	}

	private void addCircle(ArrayList<Shape> shapes) throws SQLException {
		String query = "SELECT * FROM `hashtest`.`circle` where `username`= '" + user.getUsername() + "';";
		Statement stmt = SqlConnection.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(query);
		if (rs != null) {
			while (rs.next()) {
				int x1 = rs.getInt("x1");
				int y1 = rs.getInt("y1");
				int x2 = rs.getInt("x2");
				int y2 = rs.getInt("y2");
				Color color = Paint.strToColor(rs.getString("color"));
				shapes.add(new Circle(color, new Point(x1, y1), new Point(x2, y2), user));
			}
		}
	}

	protected void updateShape(Shape shape, Color color) throws SQLException {
		String query = null;
		if (shape instanceof Line) {
			query = "UPDATE `hashtest`.`LINE` SET COLOR= '" + Paint.colorToString(color) + "' WHERE X1="
					+ shape.getStart().x + " AND Y1=" + shape.getStart().y + " AND X2=" + shape.getEnd().x + " AND y2="
					+ shape.getEnd().y + " AND USERNAME='" + shape.getUser().getUsername() + "';";
		} else if (shape instanceof Rectangle) {
			query = "UPDATE `hashtest`.`RECTANGLE` SET COLOR= '" + Paint.colorToString(color) + "' WHERE X1="
					+ shape.getStart().x + " AND Y1=" + shape.getStart().y + " AND X2=" + shape.getEnd().x + " AND y2="
					+ shape.getEnd().y + " AND USERNAME='" + shape.getUser().getUsername() + "';";
		} else if(shape instanceof Circle) {
			query = "UPDATE `hashtest`.`CIRCLE` SET COLOR= '" + Paint.colorToString(color) + "' WHERE X1="
					+ shape.getStart().x + " AND Y1=" + shape.getStart().y + " AND X2=" + shape.getEnd().x + " AND y2="
					+ shape.getEnd().y + " AND USERNAME='" + shape.getUser().getUsername() + "';";
		}
		update(query);
	}
}