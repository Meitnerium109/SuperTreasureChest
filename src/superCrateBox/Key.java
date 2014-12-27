
package superCrateBox;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import javax.swing.ImageIcon;

//Key collides with player and then dissapears, giving player a key
public class Key extends BaseTile {
	final double MAX_GRAVITY = 5;
	final double TERMINAL_VELOCITY = 15;

	private double gravity, acceleration, speedY;
	private boolean visible, noGravity;

	Image keyImage;

	public Key(double x, double y, double speedY) {
		// Constructor for key
		this.x = x;
		this.y = y;

		this.speedY = speedY;

		height = 32;
		width = 32;

		acceleration = 0.06;
		gravity = 0;

		visible = true;

		keyImage = new ImageIcon("C:\\JavaImages\\Key.png").getImage();

	}

	public void update() {
		// Deletes the key when it is greater than 640
		gravity();
		if (y > 640) {
			delete();
		}
	}

	double rotation = 0;

	public void paint(Graphics g) {
		// Rotates the key when it is in the air
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform turn = new AffineTransform();
		turn.translate(x + width / 2, y + height / 2);
		turn.rotate(rotation, width / 2, height / 2);

		if (!noGravity) {
			rotation += 0.25;
		} else if (noGravity) {
			rotation = 0;
		}

		g2.drawImage(keyImage, turn, null);

	}

	public void gravity() {
		// When the key experiences gravity, the key experiences gravity
		if (!noGravity) {

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
		}
	}

	public void delete() {
		// Deletes the key
		x = -100;
		y = -100;
		visible = false;
	}

	public boolean isVisible() {
		return visible;
	}

	public double getGravity() {
		return gravity;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	public double getSpeedY() {
		return speedY;
	}

	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isNoGravity() {
		return noGravity;
	}

	public void setNoGravity(boolean noGravity) {
		this.noGravity = noGravity;
	}

}
