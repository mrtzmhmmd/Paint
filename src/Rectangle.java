import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Rectangle extends Shape {

	public Rectangle(Color color, Point start, Point end, User user) {
		super(color, start, end, user);
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(color);
		g2d.drawRect(Math.min(start.x, end.x), Math.min(start.y, end.y), Math.abs(start.x - end.x),
				Math.abs(start.y - end.y));
	}
}