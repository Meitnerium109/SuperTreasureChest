
package superCrateBox;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

//Flying enemy that does not fall
public class Enemy3 extends BaseEnemy {

	private Image flyAnimation_1, flyAnimation_2, flyAnimation_3;
	private ArrayList<Image> flyAnimation = new ArrayList<Image>();
	private int animationCounter, animationMax, animationIndex;

	private final double MAX_SPEED = 2;

	public Enemy3(double x, double y, int direction) {
		//Constructor for enemy3
		this.x = x;
		this.y = y;

		width = 48;
		height = 32;

		health = 100;

		animationCounter = 0;
		animationMax = 4;
		animationIndex = 0;

		if (direction == 1) {
			speed = 2;
		} else if (direction == -1) {
			speed = -2;
		}
		
		speedY = 0.75;

		loadImages();

		visible = true;

		keyChance = 50;
	}

	public void loadImages() {
		//Images for the flying animation of this enemy
		flyAnimation_1 = new ImageIcon(getClass().getResource("/images/Enemy3_1.png")).getImage();
		flyAnimation_2 = new ImageIcon(getClass().getResource("/images/Enemy3_2.png")).getImage();
		flyAnimation_3 =new ImageIcon(getClass().getResource("/images/enemy3_3.png")).getImage();

		flyAnimation.add(flyAnimation_1);
		flyAnimation.add(flyAnimation_2);
		flyAnimation.add(flyAnimation_3);
		flyAnimation.add(flyAnimation_3);
		flyAnimation.add(flyAnimation_2);
		flyAnimation.add(flyAnimation_1);
	}

	public void update() {
		//Updates the enemy
		counters();
		animationCounters();

	}

	public void animationCounters() {
		//Updates the animationIndex when animationCounter equals to animationMax. 
		//animationCounter is reset after it reaches animationMax
		if (animationCounter < animationMax) {
			animationCounter++;
		} else if (animationCounter == animationMax) {
			animationCounter = 0;
			animationIndex += 1;
		}

		if (animationIndex == flyAnimation.size()) {
			animationIndex = 0;
		}
	}

	public void paint(Graphics g) {
		//Draws the animation index of fly animation
		g.drawImage(flyAnimation.get(animationIndex), (int) x, (int) y, width,
				height, null);
	}

	public double getMAX_SPEED() {
		return MAX_SPEED;
	}

}
