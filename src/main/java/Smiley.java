import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class Smiley extends JFrame {

	public static void main(String[] args) {
		new Smiley();
	}
	
	// constructor sets window's title bar string and dimensions (full screen)
	public Smiley() {
		super("Happy Face");
		setSize(1660, 1080);
		setVisible(true);
	}

	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		g2.setStroke(new BasicStroke(7));

		// call superclass's paint method
		super.paint(g);

		g.setColor(Color.yellow);
		g.fillOval(500, 200, 400, 400);
		g.setColor(Color.white);
		g.fillOval(520, 350, 100, 100);
		g.fillOval(780, 350, 100, 100);

		g2.setPaint(Color.black);

		g.drawOval(500, 200, 400, 400);

		g.fillArc(100, 120, 80, 80, 90, 50);
	}
}