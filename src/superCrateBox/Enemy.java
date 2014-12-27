
package superCrateBox;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;


//First type of enemy. Large enemy that just falls down and walks slowly
public class Enemy extends BaseEnemy {

	private final double MAX_SPEED = 1.5;
	private int walkingIndex = 0;

	private int walkingCounter, walkingMax;

	ArrayList<Image> walking = new ArrayList<Image>();
	ArrayList<Image> walkingR = new ArrayList<Image>();

	public Enemy(double x, double y, String direction) {
		this.x = x;
		this.y = y;

		width = 32;
		height = 32;

		if (direction.equals("left")) {
			speed = -1.5;
		} else if (direction.equals("right")) {
			speed = 1.5;
		}

		visible = true;

		keyChance = 15;

		walkingCounter = 0;
		walkingMax = 5;
		walkingIndex = 0;

		health = 100;
		loadImages();
	}

	public void update() {
		//Goes through the update method of the class that it this class extends
		super.update();

		/*Counts the amount of time between the animation change, then 
		changes the animation index that is painted.*/
		
		if (walkingCounter < walkingMax) {
			walkingCounter++;
		} else if (walkingCounter == walkingMax) {
			walkingIndex += 1;
			walkingCounter = 0;
		}

		//Loops the walkingAnimation
		if (walkingIndex == walking.size()) {
			walkingIndex = 0;
		}
	}

	public void loadImages() {
		//Loads images for the enemy
		
		//Image for the enemy walking left
		Image walking_1 = new ImageIcon(getClass().getResource("/images/Enemy.png")).getImage();
		Image walking_2 = new ImageIcon(getClass().getResource("/images/Enemy_2.png")).getImage();

		walking.add(walking_1);
		walking.add(walking_2);
		
		//Images for the enemy walking right.
		Image walkingR_1 = new ImageIcon(getClass().getResource("/images/EnemyR.png")).getImage();
		Image walkingR_2 = new ImageIcon(getClass().getResource("/images/EnemyR_2.png")).getImage();

		walkingR.add(walkingR_1);
		walkingR.add(walkingR_2);
	}

	public void paint(Graphics g) {
		//Draws the enemy facing a certain direction.
		if (speed > 0) {
			g.drawImage(walking.get(walkingIndex), (int) x, (int) y, width,
					height, null);
		} else if (speed < 0) {
			g.drawImage(walkingR.get(walkingIndex), (int) x, (int) y, width,
					height, null);
		}
	}

	public double getMAX_SPEED() {
		return MAX_SPEED;
	}

}
