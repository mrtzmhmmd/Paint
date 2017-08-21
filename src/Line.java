import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Line extends Shape {

	public Line(Color color, Point start, Point end, User user) {
		super(color, start, end, user);
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(color);
		g2d.drawLine(start.x, start.y, end.x, end.y);
	}
}