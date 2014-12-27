
package superCrateBox;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

//Decoration for the water gun
public class WaterDecoration extends BaseProjectile {

	private boolean waterDecoration = false, steamDecoration = false;
	private int steamCounter, steamMax;

	private Image waterDecorationImage;

	Random r = new Random();

	public WaterDecoration(double x, double y, int direction, double spread,
			int version) {

		// Sets the display to the correct version of the decoration. A steam
		// decoration was planned, but was not placed in final project. It will
		// possibly be added when I feel like adding it.
		
		if (version == 1) {
			waterDecoration = true;
		} else if (version == 2) {
			steamDecoration = true;
		}

		this.x = x;
		this.y = y;

		if (waterDecoration) {
			width = 6;
			height = 6;

			if (direction == -1) {
				speed = -10;
			} else if (direction == 1) {
				speed = 10;
			}

		} else if (steamDecoration) {
			width = 4;
			height = 4;

			steamCounter = 0;
			steamMax = 10;

			if (direction == -1) {
				speed = -5;
			} else if (direction == 1) {
				speed = 5;
			}
		}

		//Loads the image for the water decoration
		waterDecorationImage = new ImageIcon(getClass().getResource(
				"/images/waterDecoration.png")).getImage();
		visible = true;

		//Creates the spread for the water decoration
		speedY = (r.nextDouble() - 0.5) * spread;

	}

	public void update() {
		//Updates the water decoration
		movement();
		//counters();
	}

	public void counters() {
		//Not in use for now. Deletes the steam when steamCounter reaches steamMax
		if (steamDecoration) {
			if (steamCounter < steamMax) {
				steamCounter++;
			} else if (steamCounter == steamMax) {
				visible = false;
			}
		}
	}

	public void movement() {
		//Moves the decoration
		x += speed;
		y += speedY;
	}

	public void paint(Graphics g) {
		//Paints the decoration
		g.drawImage(waterDecorationImage, (int) x, (int) y, width, height, null);
	}
}
