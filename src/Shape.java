import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class Shape {

	protected Color color;
	protected Point start;
	protected Point end;
	protected User user;

	public Shape(Color color, Point start, Point end, User user) {
		this.color = color;
		this.start = start;
		this.end = end;
		this.user = user;
	}

	protected void setColor(Color color) {
		this.color = color;
	}
	
	protected Color getColor() {
		return color;
	}

	protected Point getStart() {
		return start;
	}

	protected Point getEnd() {
		return end;
	}

	protected User getUser() {
		return user;
	}

	protected double distance(Point p1, Point p2) {
		double dx = Math.pow(p1.getX() - p2.getX(), 2);
		double dy = Math.pow(p1.getY() - p2.getY(), 2);
		return Math.sqrt(dx + dy);
	}

	public abstract void draw(Graphics g);

	public abstract boolean contains(Point point);
}