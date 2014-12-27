
package superCrateBox;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class MainChar {

	double x, y, gravity, speedY, acceleration;
	int width, height, speed, key, heart, invulnCounter = 0, invulnMax = 75;
	boolean canJump;

	Image standing, standingR;

	int walkingCounter = 0, walkingMax = 1, walkingIndex = 0;

	ArrayList<Image> walkingAnimation = new ArrayList<Image>();
	ArrayList<Image> walkingAnimationR = new ArrayList<Image>();
	Image keyImage;

	final double MAX_GRAVITY = 5;
	final double TERMINAL_VELOCITY = 15;

	int direction = -1;
	int moving = -1;

	Image heartImage;

	public MainChar(double x, double y) {
		// Constructor for the mainChar
		this.x = x;
		this.y = y;

		width = 32;
		height = 48;

		speed = 5;
		acceleration = 0.06;
		gravity = 0;

		canJump = true;

		key = 10;

		heart = 3;
	}

	public void paint(Graphics g) {
		// Tile Hit Box

		/*
		 * g.setColor(Color.BLACK); g.drawRect((int) x, (int) y, width, height);
		 */

		// Damage Hit Box
		/*
		 * g.setColor(Color.RED); g.drawRect((int) x + 7, (int) y + 18, width -
		 * 14, height - 24);
		 */

		// Amimations for walking and standing
		if (moving == -1) {
			g.drawImage(walkingAnimation.get(walkingIndex), (int) x, (int) y,
					width, height, null);
		} else if (moving == 1) {
			g.drawImage(walkingAnimationR.get(walkingIndex), (int) x, (int) y,
					width, height, null);
		} else if (direction == 1) {
			g.drawImage(walkingAnimationR.get(0), (int) x, (int) y, width,
					height, null);
		} else if (direction == -1) {
			g.drawImage(walkingAnimation.get(0), (int) x, (int) y, width,
					height, null);
		}

		// Draws the number of keys that the player has (key) x #
		g.setColor(Color.WHITE);
		g.drawImage(keyImage, 32, 10, 16, 16, null);
		g.drawString("x " + key, 55, 20);

		// Draws the number of hearts that the player has
		for (int i = 0; i < heart; i++) {
			g.drawImage(heartImage, i * 18 + 32, 32, 16, 16, null);
		}

	}

	public void update() {
		// Updates the character
		gravity();
		counters();
		animationCounters();
	}

	public void counters() {
		// Counts for invulnerability
		if (invulnCounter < invulnMax) {
			invulnCounter++;
		}
	}

	public void loadImages() {

		// Loads all the images for the main character, and places it in the
		// appropriate arraylist
		Image walking_1 = new ImageIcon(getClass().getResource(
				"/images/Walking_1.png")).getImage();
		Image walking_2 = new ImageIcon(getClass().getResource(
				"/images/Walking_2.png")).getImage();
		Image walking_3 = new ImageIcon(getClass().getResource(
				"/images/Walking_3.png")).getImage();
		Image walking_4 = new ImageIcon(getClass().getResource(
				"/images/Walking_4.png")).getImage();
		Image walking_5 = new ImageIcon(getClass().getResource(
				"/images/Walking_5.png")).getImage();
		Image walking_6 = new ImageIcon(getClass().getResource(
				"/images/Walking_6.png")).getImage();

		walkingAnimation.add(walking_1);
		walkingAnimation.add(walking_2);
		walkingAnimation.add(walking_3);
		walkingAnimation.add(walking_4);
		walkingAnimation.add(walking_5);
		walkingAnimation.add(walking_6);

		Image walkingR_1 = new ImageIcon(getClass().getResource(
				"/images/WalkingR_1.png")).getImage();
		Image walkingR_2 = new ImageIcon(getClass().getResource(
				"/images/WalkingR_2.png")).getImage();
		Image walkingR_3 = new ImageIcon(getClass().getResource(
				"/images/WalkingR_3.png")).getImage();
		Image walkingR_4 = new ImageIcon(getClass().getResource(
				"/images/WalkingR_4.png")).getImage();
		Image walkingR_5 = new ImageIcon(getClass().getResource(
				"/images/WalkingR_5.png")).getImage();
		Image walkingR_6 = new ImageIcon(getClass().getResource(
				"/images/WalkingR_6.png")).getImage();

		walkingAnimationR.add(walkingR_1);
		walkingAnimationR.add(walkingR_2);
		walkingAnimationR.add(walkingR_3);
		walkingAnimationR.add(walkingR_4);
		walkingAnimationR.add(walkingR_5);
		walkingAnimationR.add(walkingR_6);

		keyImage = new ImageIcon(getClass().getResource("/images/key.png")).getImage();

		heartImage = new ImageIcon(getClass().getResource("/images/heart.png")).getImage();
	}

	public void gravity() {
		// Gravity for the player
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
	}

	public void animationCounters() {
		// Counts to create the animation for the character.
		// When the character is not moving, it is not animated
		if (moving == 1 || moving == -1) {
			if (walkingCounter < walkingMax) {
				walkingCounter += 1;
			} else if (walkingCounter == walkingMax) {
				walkingIndex += 1;
				walkingCounter = 0;
			}

			if (moving == -1) {
				if (walkingIndex == walkingAnimation.size()) {
					walkingIndex = 0;
				}
			} else if (moving == 1) {
				if (walkingIndex == walkingAnimationR.size()) {
					walkingIndex = 0;
				}
			}
		}

	}

	public Rectangle hitBox() {
		// Sets the hitbox for various things such as tiles
		return new Rectangle((int) x, (int) y, width, height);
	}

	public Rectangle damageHitBox() {
		// The hitBox that is used when the character is checked for damage
		return new Rectangle((int) x + 7, (int) y + 18, width - 14, height - 24);
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

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
