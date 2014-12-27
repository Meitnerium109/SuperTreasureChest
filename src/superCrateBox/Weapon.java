
package superCrateBox;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Weapon {

	double x, y, spread, spreadMax, slingRotation;
	int width, height, equipped, shootCounter, shootMax, direction,
			potatoCounter, potatoMax, potatoIndex;
	double damage;
	boolean canShoot, shooting;

	/*
	 * Equipped 1 - Water Sprayer 
	 * Equipped 2 - Potato Launcher 
	 * Equipped 3 - Sling 
	 * Equipped 4 - Shuriken 
	 * Equipped 5 - Spear 
	 * Equipped 6 - Laser
	 */

	private Image waterGun;
	private Image waterGunR;
	private Image sling;
	private Image ninjaStar;
	private Image potatoCannon_1, potatoCannonR_1;
	private Image spear, spearR;
	private Image laser, laserR;
	
	public Weapon(double x, double y) {
		//Weapon constructor
		this.x = x;
		this.y = y;

		width = 20;
		height = 10;

		canShoot = true;

		damage = 5;
		spread = 2;
		shootMax = 1;

		spreadMax = 30;

		//Player starts with the potato launcher
		equipped = 2;

		potatoCounter = 0;
		potatoMax = 0;
		potatoIndex = 0;

		slingRotation = 0;

		//Loads the images and checks for equipped weapon
		loadImages();
		//Checks for the equipped weapon and sets stats according to that.
		checkEquipped();
	}

	public void loadImages() {
		waterGun = new ImageIcon(getClass().getResource(
				"/images/WaterGun.png")).getImage();
		waterGunR = new ImageIcon(getClass().getResource(
				"/images/WaterGunR.png")).getImage();

		// Left Potato Cannon
		potatoCannon_1 = new ImageIcon(getClass().getResource(
				"/images/PotatoCannon.png")).getImage();

		// Right Potato Cannon

		potatoCannonR_1 = new ImageIcon(getClass().getResource(
				"/images/PotatoCannonR_1.png")).getImage();

		sling = new ImageIcon(getClass().getResource(
				"/images/Sling_1.png")).getImage();

		ninjaStar = new ImageIcon(getClass().getResource(
				"/images/NinjaStar.png")).getImage();

		spear = new ImageIcon(getClass().getResource(
				"/images/Spear.png")).getImage();
		spearR = new ImageIcon(getClass().getResource(
				"/images/SpearR.png")).getImage();
		
		laser = new ImageIcon(getClass().getResource(
				"/images/LaserWeapon.png")).getImage();
		laserR = new ImageIcon(getClass().getResource(
				"/images/LaserWeaponR.png")).getImage();
	}

	public void paint(Graphics g) {
		//Paints the appropriate weapon at the appropriate position
		if (equipped == 1) {
			if (direction == -1) {
				g.drawImage(waterGun, (int) x, (int) y - 3, width, height, null);
			} else if (direction == 1) {
				g.drawImage(waterGunR, (int) x, (int) y - 3, width, height,
						null);
			}
		} else if (equipped == 2) {
			if (direction == -1) {
				g.drawImage(potatoCannon_1, (int) x, (int) y - 3, width,
						height, null);
			} else if (direction == 1) {
				g.drawImage(potatoCannonR_1, (int) x, (int) y - 3, width,
						height, null);
			}

		} else if (equipped == 3) {

			Graphics2D g2 = (Graphics2D) g;
			AffineTransform turn = new AffineTransform();

			if (direction == 1) {
				if (shooting) {
					slingRotation += 0.5;
				}
				turn.translate(x - 10, y - height - 5);
			} else if (direction == -1) {
				if (shooting) {
					slingRotation -= 0.5;
				}
				turn.translate(x + 15, y - height - 5);
			}
			turn.rotate(slingRotation, width / 2, height);

			g2.drawImage(sling, turn, null);

		} else if (equipped == 4) {
			if (canShoot) {
				if (direction == -1) {
					g.drawImage(ninjaStar, (int) x + 5, (int) y - 5, null);
				} else if (direction == 1) {
					g.drawImage(ninjaStar, (int) x - 5, (int) y - 5, null);
				}
			}
		} else if (equipped == 5) {
			if (canShoot) {
				if (direction == -1) {
					g.drawImage(spear, (int) x + 5, (int) y - 5, width, height,
							null);
				} else if (direction == 1) {
					g.drawImage(spearR, (int) x - 5, (int) y - 5, width,
							height, null);
				}
			}

		} else if (equipped == 6){
			if(direction == -1){
				g.drawImage(laser, (int)x, (int)y -5, width, height, null);
			} else if (direction == 1){
				g.drawImage(laserR, (int)x, (int)y - 5, width, height, null);
			}
		}

	}

	public void update() {
		counters();
	}

	public void checkEquipped() {
		//Stats for all the weapons that will be checked whenever a weapon is traded from a crate
		if (equipped == 1) {
			damage = 5;
			spread = 2;
			shootMax = 1;
			width = 20;
			height = 10;
		} else if (equipped == 2) {
			width = 30;
			height = 10;
			damage = 40;
			spread = 1;
			shootMax = 20;
			width = 30;
			height = 10;
		} else if (equipped == 3) {
			damage = 5;
			spread = 0;
			shootMax = 0;
			width = 23;
			height = 35;
		} else if (equipped == 4) {
			damage = 50;
			spread = 0;
			shootMax = 50;
			width = 15;
			height = 15;
		} else if (equipped == 5) {
			damage = 10;
			spread = 0;
			shootMax = 85;
			width = 40;
			height = 10;
		} else if (equipped == 6) {
			damage = 2.5;
			spread = 0;
			shootMax = 75;
			width = 30;
			height = 10;
		}
	}

	public void counters() {
		//Cooldown for the weapon. When shootCounter equals to shootMax, the weapon can shoot again
		if (shootCounter < shootMax) {
			shootCounter += 1;
		} else if (shootCounter == shootMax) {
			canShoot = true;
		} else if (shootCounter > shootMax) {
			shootCounter = 0;
		}

		if (equipped == 3) {
			//Decreases the spread of the slingshot per update
			if (spread > 0.7) {
				spread -= 0.7;
			}
		}
		
		if (spread > spreadMax) {
			spread = spreadMax;
		}
	}

}
