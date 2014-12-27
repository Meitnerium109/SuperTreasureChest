package superCrateBox;

import java.awt.Rectangle;
import java.util.Random;

/*abstract class to help with easy construction of enemies.
 Sets default values and declares a number of variables that all
 or most of the enemies will use.
 */
public abstract class BaseEnemy {

	protected double x, y, acceleration = 0.06, gravity, speedY, speed, health;
	protected int width, height, bleedCounter, bleedMax;
	protected boolean visible, hasKey, bleeding = false;

	protected final double MAX_GRAVITY = 5;
	protected final double TERMINAL_VELOCITY = 15;
	// The keyChance number is a percentage out of one hundred.
	protected int keyChance;

	Random r = new Random();

	public Rectangle hitBox() {
		return new Rectangle((int) x, (int) y, width, height);
	}

	public void checkHealth() {
		// Randomizes keyChanceVariable every time the enemy is hit.
		int keyChanceVariable = r.nextInt(100) + 1;

		if (health <= 0) {
			// Deletes enemy when health is less than 0
			visible = false;
			if (keyChanceVariable <= keyChance) {
				// Checks if keyChanceVariable is less than keyChance, if so, a
				// key is dropped
				hasKey = true;
			}
		}
	}

	public void bleed() {
		// Causes the enemy to bleed, making it lose health in the process
		bleeding = true;
		bleedCounter = 0;
		bleedMax = 50;
	}

	public void hit(double damage) {
		// Takes away a certain amount of health from the enemy
		health -= damage;
		// Checks the health of the enemy.
		checkHealth();
	}

	public void delete() {
		// Deletes the enemy
		x = -100;
		y = -100;
		visible = false;
	}

	public void moveUp() {
		// Moves the enemy to the top of the screen, where it "respawns"
		y = -10;
	}

	public void update() {
		// Updates the enemy
		gravity();
		counters();
	}

	public void gravity() {
		// Sets the gravity for the enemy
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

	public void counters() {
		if (bleeding) {
			if (bleedCounter < bleedMax) {
				bleedCounter++;
				health -= 0.9;
				checkHealth();
			} else if (bleedCounter == bleedMax) {
				bleeding = false;
			}
		}
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isVisible() {
		return visible;
	}

	public double getSpeed() {
		return speed;
	}

	public double getSpeedY() {
		return speedY;
	}

	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}

	public double getGravity() {
		return gravity;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isHasKey() {
		return hasKey;
	}

	public void setHasKey(boolean hasKey) {
		this.hasKey = hasKey;
	}

	public boolean isBleeding() {
		return bleeding;
	}

}
