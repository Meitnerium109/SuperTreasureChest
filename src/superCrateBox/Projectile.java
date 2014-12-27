
package superCrateBox;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

public class Projectile extends BaseProjectile {

	Image projectileImage, projectileImage2;

	Random r = new Random();

	private int visibleCounter, visibleMax;
	private double potatoRotation, ninjaStarRotation;

	public Projectile(double x, double y, int direction, double damage,
			double spread, int type) {

		// Constructor for the projectiles
		this.x = x;
		this.y = y;

		width = 10;
		height = 10;

		visible = true;

		this.damage = damage;
		this.type = type;

		// Sets the spread of the projectiles
		speedY = (r.nextDouble() - 0.5) * spread;

		// Sets the speed of the projectile
		if (direction == -1) {
			speed = -10;
		} else if (direction == 1) {
			speed = 10;
		}

		// Loads the correct images for each type of projectile
		if (type == 1) {
			loadWaters();
		} else if (type == 3) {
			loadSlingRocks();
		} else

		if (type == 2) {
			loadPotatos();
			potatoRotation = 1;
		} else if (type == 4) {
			width = 20;
			height = 20;
			health = 80;
			ninjaStarRotation = 0;
			loadNinjaStars();
		} else if (type == 5) {
			damage = 0;
			width = 40;
			height = 10;
			loadSpears();
		} else if (type == 6) {
			width = 640;
			height = 20;
			speed = 0;
			visibleCounter = 0;
			visibleMax = 20;
			loadLasers();
		}
	}

	public void loadLasers() {
		projectileImage = new ImageIcon(getClass().getResource(
				"/images/Laser.png")).getImage();
	}

	public void loadWaters() {
		projectileImage = new ImageIcon(getClass().getResource(
				"/images/Water.png")).getImage();
	}

	public void loadPotatos() {
		projectileImage = new ImageIcon(getClass().getResource(
				"/images/Potato.png")).getImage();
	}

	public void loadNinjaStars() {
		projectileImage = new ImageIcon(getClass().getResource(
				"/images/NinjaStar.png")).getImage();
	}

	public void loadSlingRocks() {
		// Randomizes drawing between two rocks
		Random r = new Random();

		switch (r.nextInt(2) + 1) {
		case 1:
			projectileImage = new ImageIcon(getClass().getResource(
					"/images/SlingRock_1.png")).getImage();
			break;
		case 2:
			projectileImage = new ImageIcon(getClass().getResource(
					"/images/SlingRock_2.png")).getImage();
			break;

		}
	}

	public void loadSpears() {
		projectileImage = new ImageIcon(getClass().getResource(
				"/images/Spear.png")).getImage();
		projectileImage2 = new ImageIcon(getClass().getResource(
				"/images/SpearR.png")).getImage();
	}

	public void update() {
		// Updates the projectiles
		movement();

		// Counters for the laser weapon
		if (type == 6) {
			laserCounters();
		}
	}

	public void laserCounters() {
		// Deletes the laser when visibleCounter has reached visibleMax
		if (visibleCounter < visibleMax) {
			visibleCounter++;
		} else if (visibleCounter == visibleMax) {
			visible = false;
		}
	}

	public void movement() {
		// Moves the projectiles
		x += speed;
		y += speedY;
	}

	public void paint(Graphics g) {
		// Paints the correct image for the projectiles

		// Some projectiles are rotated before being painted
		if (type == 1) {
			g.drawImage(projectileImage, (int) x, (int) y, width, height, null);
		} else if (type == 3) {
			g.drawImage(projectileImage, (int) x, (int) y, width, height, null);
		} else if (type == 2) {
			Graphics2D g2 = (Graphics2D) g;
			AffineTransform turn = new AffineTransform();
			turn.translate(x + width / 2, y + height / 2);
			turn.rotate(potatoRotation, width / 2, height / 2);

			potatoRotation += 0.25;

			g2.drawImage(projectileImage, turn, null);
		} else if (type == 4) {
			Graphics2D g2 = (Graphics2D) g;
			AffineTransform turn = new AffineTransform();
			turn.translate(x + width / 2, y + height / 2);
			turn.rotate(ninjaStarRotation, width / 2, height / 2);

			ninjaStarRotation += 0.25;

			g2.drawImage(projectileImage, turn, null);
		} else if (type == 5) {
			if (speed < 0) {
				g.drawImage(projectileImage, (int) x, (int) y, width, height,
						null);
			} else if (speed > 0) {
				g.drawImage(projectileImage2, (int) x, (int) y, width, height,
						null);
			}
		} else if (type == 6) {
			g.drawImage(projectileImage, (int) x, (int) y, width, height, null);

		}

	}

	public Rectangle hitBox() {
		return super.hitBox();
	}
}
