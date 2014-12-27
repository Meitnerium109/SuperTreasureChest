package superCrateBox;

import java.awt.Rectangle;

//abstract class to help in the construction of projectiles
public abstract class BaseProjectile {

	protected double x, y, speedY, health, damage;
	protected int width, height, speed, type;
	protected boolean visible;

	public void delete() {
		// Deletes the projectile
		x = -100;
		y = -100;
		visible = false;
	}

	public void checkHealth() {
		// Checks the health of the projectile (if there is indeed health on the
		// projectile)
		if (health <= 0) {
			delete();
		}
	}

	public void hit(double damage) {
		//If the projectile has health, takes away certain amount of health and checks the health
		health -= damage;
		checkHealth();
	}

	public Rectangle hitBox() {
		//gets a rectangular hitBox for the projectile
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

	public boolean isVisible() {
		return visible;
	}

	public double getDamage() {
		return damage;
	}

	public int getType() {
		return type;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getHealth() {
		return health;
	}
}
