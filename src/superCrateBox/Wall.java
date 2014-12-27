

package superCrateBox;
import java.awt.Color;
import java.awt.Graphics;

//Used for collision with the enemies and reverses the enemy's direction
public class Wall extends BaseTile {

	public Wall(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void paint(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect((int) x, (int) y, width, height);
		g.setColor(Color.DARK_GRAY);
		g.drawRect((int) x, (int) y, width, height);
	}
	
}
