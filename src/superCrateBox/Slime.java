
package superCrateBox;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;

/*creates slime at some x and y
 *  values*/

public class Slime {

	private double x, y, speedY, speed, gravity, acceleration;
	private int width, height, visibleCounter = 0, visibleMax = 50;

	private Image slime;

	private boolean visible;

	final double MAX_GRAVITY = 5;
	final double TERMINAL_VELOCITY = 15;

	public Slime(double x, double y) {
		// Constructs the Slime
		Random r = new Random();
		this.x = x;
		this.y = y;

		width = 4;
		height = 4;

		visible = true;

		acceleration = 0.04;

		// Creates random left and right movement
		speed = (r.nextDouble() - 0.5) * 1.5;

		// Loads the slime images
		slime = new ImageIcon(getClass().getResource(
				"/images/slime.png")).getImage();

	}

	public void counters() {
		// Counts until visibleCounter equals to visibleMax, then deletes itself
		if (visibleCounter < visibleMax) {
			visibleCounter++;
		} else if (visibleCounter == visibleMax) {
			delete();
		}
	}

	public void delete() {
		//Deletes the object
		x = -100;
		y = -100;
		visible = false;
	}

	public void gravity() {
		//Sets the gravity for the slime
		if (gravity < MAX_GRAVITY) {
			gravity += acceleration;
		} else {
			gravity = MAX_GRAVITY;
		}

		if (speedY < TERMINAL_VELOCITY) {
			speedY += gravity / 2;
		} else {
			speedY = TERMINAL_VELOCITY;
		}

		y += speedY;
		x += speed;

	}

	public void update() {
		//Updates the slime
		gravity();
		counters();
	}

	public void paint(Graphics g) {
		g.drawImage(slime, (int) x, (int) y, null);
	}

	public Rectangle hitBox() {
		return new Rectangle((int) x, (int) y, width, height);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getSpeedY() {
		return speedY;
	}

	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
