
package superCrateBox;
import java.awt.Color;
import java.awt.Graphics;

//When collided with enemies, causes them to go to the top of the screen
public class GoUp extends BaseTile {
	
	public GoUp(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int) x, (int) y, width, height);
		g.setColor(Color.DARK_GRAY);
		g.drawRect((int) x, (int) y, width, height);
	}
}
