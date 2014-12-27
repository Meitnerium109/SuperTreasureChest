
package superCrateBox;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

//Second type of enemy. Enemy that walks faster and is smaller ]than the first enemy but has less health
public class Enemy2 extends BaseEnemy {

	private final double MAX_SPEED = 3;

	private Image enemy2;
	private Image enemy2R;
	
	public Enemy2(double x, double y, String direction) {
		//Constructor for enemy2
		this.x = x;
		this.y = y;

		width = 24;
		height = 24;

		if (direction.equals("left")) {
			speed = -3;
		} else if (direction.equals("right")) {
			speed = 3;
		}

		visible = true;

		keyChance = 15;

		health = 40;

		//Load images for the enemy
		enemy2 = new ImageIcon(getClass().getResource("/images/Enemy2.png")).getImage();
		enemy2R = new ImageIcon(getClass().getResource("/images/Enemy2R.png")).getImage();
	}

	public void paint(Graphics g) {
		//Draws a different image if the enemy is facing a different direction
		if (speed > 0) {
			g.drawImage(enemy2, (int) x, (int) y, width, height, null);
		} else if (speed < 0) {
			g.drawImage(enemy2R, (int) x, (int) y, width, height, null);
		}
	}

	public double getMAX_SPEED() {
		return MAX_SPEED;
	}
}
