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

	public abstract void draw(Graphics g);
}