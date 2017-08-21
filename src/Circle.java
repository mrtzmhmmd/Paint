import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Circle extends Shape {

	private int radius;

	public Circle(Color color, Point start, Point end, User user) {
		super(color, start, end, user);
		radius = ((int) Math.sqrt(Math.pow(end.x - start.x, 2) + Math.pow(end.y - start.y, 2)));
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(color);
		g2d.drawOval(start.x - radius, start.y - radius, radius * 2, radius * 2);
	}
}