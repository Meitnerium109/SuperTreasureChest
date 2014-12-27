package superCrateBox;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

//Creates a box (treasure chest) that the player will open to receive a new weapon

public class Box extends BaseTile {
	
	Random r = new Random();
	private int weaponNumber, openedCounter, openedMax;

	private double gravity, acceleration, speedY;

	final double MAX_GRAVITY = 5;
	final double TERMINAL_VELOCITY = 15;

	private boolean visible, noGravity, opened;

	private Image boxImage;
	private Image boxImageOpened;

	public Box(double x, double y) {
		//Creates a box and x and y
		this.x = x;
		this.y = y;

		height = 32;
		width = 32;

		acceleration = 0.06;
		gravity = 0;

		weaponNumber = r.nextInt(3) + 1;

		visible = true;
		
		opened = false;
		openedCounter = 0;
		//Deletes the box after 50 ticks
		openedMax = 50;
		
		//Loads images for the box
		boxImage = new ImageIcon(getClass().getResource("/images/chest.png")).getImage();
		boxImageOpened = new ImageIcon(getClass().getResource("/images/chestOpened.png")).getImage();

	}

	public void paint(Graphics g) {
		//Changes the sprite when the box is opened and closed
		if (!opened) {
			g.drawImage(boxImage, (int) x, (int) y, width, height, null);
		} else if (opened) {
			g.drawImage(boxImageOpened, (int) x, (int) y, width, height, null);
		}
	}

	public int getWeaponNumber() {
		return weaponNumber;
	}

	public void update() {
		//Updates the box
		gravity();
		counters();
		checkPosition();
	}

	public void counters() {
		//Counts up to openedMax and then deletes the box
		if (opened) {
			if(openedCounter < openedMax){
				openedCounter ++;
			} else if (openedCounter == openedMax){
				visible = false;
			}
		}
	}

	public void checkPosition(){
		//Deletes the box if it is out of bounds of the screen
		if(x > 650 || x < 0 || y < 0 || y > 640){
			visible = false;
		}
	}
	
	public void gravity() {
		//If the box is in the air, gravity will bring the box down
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

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}


}
