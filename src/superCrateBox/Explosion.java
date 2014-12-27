
package superCrateBox;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/*creates an explosion animation at some x and y
 *  values, then deletes itself when the animation is over*/

public class Explosion {

	private double x, y;
	private int width, height;

	private int frameCounter, frameMax, frameIndex, direction;

	private boolean visible;

	ArrayList<Image> explodeAnimation = new ArrayList<Image>();
	ArrayList<Image> explodeAnimationR = new ArrayList<Image>();

	private Image explode_1, explode_2, explode_3, explode_4, explode_5,
			explode_6, explode_7, explode_8, explode_9, explode_10, explode_11,
			explode_12, explodeR_1, explodeR_2, explodeR_3, explodeR_4,
			explodeR_5, explodeR_6, explodeR_7, explodeR_8, explodeR_9,
			explodeR_10, explodeR_11, explodeR_12;

	public Explosion(double x, double y, int direction) {
		//Sets up the Explosion
		this.x = x;
		this.y = y;

		width = 50;
		height = 50;

		this.direction = direction;

		if (direction == 1) {
			loadRImages();
		} else if (direction == -1) {
			loadImages();
		}

		visible = true;

	}

	public void counters() {
		//Counts the amount of frames, then deletes the explosion
		if (frameIndex < 11) {
			frameIndex++;
		} else if (frameIndex == 11) {
			visible = false;
		}
	}

	public void update() {
		//Updates the explosion
		counters();
	}

	public void loadRImages() {
		//Loads all right explosion Images
		explodeR_1 = new ImageIcon(getClass().getResource("/images/ExplodeR_1.png")).getImage();
		explodeR_2 = new ImageIcon(getClass().getResource("/images/ExplodeR_2.png")).getImage();
		explodeR_3 = new ImageIcon(getClass().getResource("/images/ExplodeR_3.png")).getImage();
		explodeR_4 = new ImageIcon(getClass().getResource("/images/ExplodeR_4.png")).getImage();
		explodeR_5 = new ImageIcon(getClass().getResource("/images/ExplodeR_5.png")).getImage();
		explodeR_6 = new ImageIcon(getClass().getResource("/images/ExplodeR_6.png")).getImage();
		explodeR_7 = new ImageIcon(getClass().getResource("/images/ExplodeR_7.png")).getImage();
		explodeR_8 = new ImageIcon(getClass().getResource("/images/ExplodeR_8.png")).getImage();
		explodeR_9 = new ImageIcon(getClass().getResource("/images/ExplodeR_9.png")).getImage();
		explodeR_10 = new ImageIcon(getClass().getResource("/images/ExplodeR_10.png")).getImage();
		explodeR_11 = new ImageIcon(getClass().getResource("/images/explodeR_12.png")).getImage();
		explodeR_12 = new ImageIcon(getClass().getResource("/images/explodeR_12.png")).getImage();

		explodeAnimationR.add(explodeR_1);
		explodeAnimationR.add(explodeR_2);
		explodeAnimationR.add(explodeR_3);
		explodeAnimationR.add(explodeR_4);
		explodeAnimationR.add(explodeR_5);
		explodeAnimationR.add(explodeR_6);
		explodeAnimationR.add(explodeR_7);
		explodeAnimationR.add(explodeR_8);
		explodeAnimationR.add(explodeR_9);
		explodeAnimationR.add(explodeR_10);
		explodeAnimationR.add(explodeR_11);
		explodeAnimationR.add(explodeR_12);
	}

	public void loadImages() {
		//Loads all left explosion images
		explode_1 = new ImageIcon(getClass().getResource("/images/Explode_1.png")).getImage();
		explode_2 = new ImageIcon(getClass().getResource("/images/Explode_2.png")).getImage();
		explode_3 = new ImageIcon(getClass().getResource("/images/Explode_3.png")).getImage();
		explode_4 = new ImageIcon(getClass().getResource("/images/Explode_4.png")).getImage();
		explode_5 = new ImageIcon(getClass().getResource("/images/Explode_5.png")).getImage();
		explode_6 = new ImageIcon(getClass().getResource("/images/Explode_6.png")).getImage();
		explode_7 = new ImageIcon(getClass().getResource("/images/Explode_7.png")).getImage();
		explode_8 = new ImageIcon(getClass().getResource("/images/Explode_8.png")).getImage();
		explode_9 = new ImageIcon(getClass().getResource("/images/Explode_9.png")).getImage();
		explode_10 = new ImageIcon(getClass().getResource("/images/Explode_10.png")).getImage();
		explode_11 = new ImageIcon(getClass().getResource("/images/explode_12.png")).getImage();
		explode_12 = new ImageIcon(getClass().getResource("/images/explode_12.png")).getImage();

		explodeAnimation.add(explode_1);
		explodeAnimation.add(explode_2);
		explodeAnimation.add(explode_3);
		explodeAnimation.add(explode_4);
		explodeAnimation.add(explode_5);
		explodeAnimation.add(explode_6);
		explodeAnimation.add(explode_7);
		explodeAnimation.add(explode_8);
		explodeAnimation.add(explode_9);
		explodeAnimation.add(explode_10);
		explodeAnimation.add(explode_11);
		explodeAnimation.add(explode_12);
	}

	public void paint(Graphics g) {
		//Paints the explosion in a certain direction
		if (direction == 1) {
			g.drawImage(explodeAnimationR.get(frameIndex), (int) x, (int) y,
					width, height, null);
		} else if (direction == -1) {
			g.drawImage(explodeAnimation.get(frameIndex), (int) x, (int) y,
					width, height, null);
		}
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
