
package superCrateBox;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*IMPORTANT - Place the file JavaImages in the C: drive because the files 
 * are read from \\Images\\fileName...
 * 
 * This is a game where the player controls a character that needs to 
 collect keys in order to open treasure chests scattered across the map
 The keys drop from enemies that periodically spawn from the top of the 
 floor and when they fall beneath the hole on the bottom, spawn back at 
 the top of the floor. The character must defeat the enemies with various
 weapons that are provided to him when he opens treasure chests. Each time
 the character opens a treasure chest, the score is increased by 1. The 
 high score and current score is recorded and shown to the player when he 
 loses.

 Note # 1: This was made to replace the code rally assignment. 

 Note # 2: All the artwork is original except for the explosion animation and
 the background image. All code is original.*/

public class Main extends Declutter {

	private static final long serialVersionUID = 1L;

	Menu menu;
	Weapon weapon;
	MainChar mainChar;
	KeyHandler keyHandler;
	Random r = new Random();

	public void gameReset() {
		// Resets the entire game

		gameLose = false;
		score = 0;
		waveCounter = 0;

		mainChar = null;
		weapon = null;

		mainChar = new MainChar(500, 100);
		mainChar.loadImages();

		weapon = new Weapon(mainChar.x, mainChar.y);

		wave1 = false;
		wave2 = false;
		wave3 = false;
		wave4 = false;

		waveComplete = true;
		while (enemies.size() > 0) {
			for (int i = 0; i < enemies.size(); i++) {
				Enemy e = enemies.get(i);
				enemies.remove(i);
				e = null;
			}
		}

		while (enemies2.size() > 0) {
			for (int i = 0; i < enemies2.size(); i++) {
				Enemy2 e2 = enemies2.get(i);
				enemies2.remove(i);
				e2 = null;
			}
		}

		while (enemies3.size() > 0) {
			for (int i = 0; i < enemies3.size(); i++) {
				Enemy3 e3 = enemies3.get(i);
				enemies3.remove(i);
				e3 = null;
			}
		}

		while (boxes.size() > 0) {
			for (int i = 0; i < boxes.size(); i++) {
				Box b = boxes.get(i);
				boxes.remove(i);
				b = null;
			}
		}

		while (keys.size() > 0) {
			for (int i = 0; i < keys.size(); i++) {
				Key k = keys.get(i);
				keys.remove(i);
				k = null;
			}
		}

	}

	Image backgroundImage;

	private int score = 0, highScore = 0;

	private boolean running, paused = true, gameLose, menuScreen = true;

	public static void main(String[] args) {
		// Initializes the JFrame that will hold the JPanel that holds the game
		JFrame frame = new JFrame();
		frame.setSize(650, 640);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.BLACK);

		// Creates a new Main class and runs it
		Main main = new Main();
		frame.add(main);

		main.setFocusable(true);
		main.requestFocus();

		main.run();
	}

	public void init() {
		// Initializese the game. Creates various components of the game

		// Loads main character and images
		mainChar = new MainChar(500, 100);
		mainChar.loadImages();

		// Loads the weapon
		weapon = new Weapon(mainChar.x, mainChar.y);

		// Loads the keyHandler
		keyHandler = new KeyHandler();
		addKeyListener((KeyListener) keyHandler);

		// Loads the Menu
		menu = new Menu();

		System.out.println("Hello");
		// Loads the backgroundImage
		backgroundImage = new ImageIcon(getClass().getResource("/images/backgroundImage.jpg")).getImage();

		// Set up main stage
		createTilesX(0, 30, 13);
		createTilesX(20, 30, 13);

		createTilesX(0, 29, 13);
		createTilesX(20, 29, 13);

		createTilesY(0, 0, 32);
		createTilesY(31, 0, 32);

		createTilesX(8, 24, 16);

		createTilesX(1, 19, 7);
		createTilesX(24, 19, 7);

		createTilesX(8, 14, 16);

		createTilesX(1, 9, 7);
		createTilesX(24, 9, 7);

		createTilesX(8, 4, 16);

		// Lets the enemies bounce
		createWall(1 * 20 - 16, 28);
		createWall(31 * 20 - 4, 28);

		createWall(1 * 20 - 16, 18);
		createWall(31 * 20 - 4, 18);

		createWall(1 * 20 - 16, 8);
		createWall(31 * 20 - 4, 8);

		createWall(31 * 20 - 4, 4);
		createWall(1 * 20 - 16, 4);

		createWall(31 * 20 - 4, 14);
		createWall(1 * 20 - 16, 14);

		createWall(31 * 20 - 4, 24);
		createWall(1 * 20 - 16, 24);

		// Sends the enemy to the top when it touches it
		createGoUp(14 * 20, 31);
		createGoUp(15 * 20, 31);
		createGoUp(16 * 20, 31);
		createGoUp(17 * 20, 31);
		createGoUp(18 * 20, 31);
		createGoUp(13 * 20, 30);
		createGoUp(19 * 20, 30);

	}

	public void run() {
		// Runs initialize, followed by the gameLoop.
		try {
			init();
			gameLoop();
		} finally {
			running = false;
		}
	}

	public void gameLoop() {
		while (true) {
			// Updates the game and repaints the screen
			update();
			repaint();

			// Sleeps for 17 milliseconds after every update and repaint
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void update() {
		// Check for any key press
		checkKeyPress();

		if (!menuScreen) {

			if (!paused && !gameLose) {
				// If the game is not paused, lost, or on the menu, the main
				// game will be updated

				spawnBoxes();
				counters();
				collisionDetection();
				mainChar.update();
				weapon.update();

				// Gets the direction of the main character and then sets the
				// weapon to the same direction
				if (mainChar.direction == 1) {
					weapon.x = mainChar.x + mainChar.width / 2;
					weapon.y = mainChar.y + mainChar.height / 2;
					weapon.direction = 1;

				} else if (mainChar.direction == -1) {
					weapon.x = mainChar.x + mainChar.width / 2 - weapon.width;
					weapon.y = mainChar.y + mainChar.height / 2;
					weapon.direction = -1;
				}

				/*
				 * Updates all the arraylists. If an object is not visible, it
				 * is removed and then deleted from the arraylist
				 */

				for (int i = 0; i < slimes.size(); i++) {
					Slime s = slimes.get(i);
					if (s.isVisible()) {
						s.update();
					} else {
						slimes.remove(i);
						s = null;

					}
				}

				for (int i = 0; i < waters.size(); i++) {
					WaterDecoration w = waters.get(i);
					if (w.isVisible()) {
						w.update();
					} else {
						waters.remove(i);
						w = null;
					}
				}

				for (int i = 0; i < projectiles.size(); i++) {
					Projectile p = projectiles.get(i);
					if (p.isVisible()) {
						p.update();
					} else {
						projectiles.remove(i);
						p = null;
					}
				}

				for (int i = 0; i < explosions.size(); i++) {
					Explosion e = explosions.get(i);
					if (e.isVisible()) {
						e.update();
					} else {
						explosions.remove(i);
						e = null;
					}
				}
				for (int i = 0; i < boxes.size(); i++) {
					Box b = boxes.get(i);
					if (b.isVisible()) {
						b.update();
					} else {
						boxes.remove(i);
						b = null;
					}
				}

				for (int i = 0; i < keys.size(); i++) {
					Key k = keys.get(i);
					if (k.isVisible()) {
						k.update();
					} else {
						keys.remove(i);
						k = null;
					}
				}

				for (int i = 0; i < enemies.size(); i++) {
					Enemy e = enemies.get(i);
					if (e.isVisible()) {

						if (e.isBleeding()) {
							createSlime(e.getX() + e.getWidth() / 2, e.getY()
									+ e.getHeight() / 2);
						}
						e.update();
					} else if (!e.isVisible()) {
						if (e.isHasKey()) {
							createKey(e.getX() - 1, e.getY() - 1, -5);
						}
						enemies.remove(e);
						e = null;
					}
				}

				for (int i = 0; i < enemies2.size(); i++) {
					Enemy2 e2 = enemies2.get(i);
					if (e2.isVisible()) {
						if (e2.isBleeding()) {
							createSlime(e2.getX() + e2.getWidth() / 2,
									e2.getY() + e2.getHeight() / 2);
						}
						e2.update();
					} else if (!e2.isVisible()) {

						if (e2.isHasKey()) {
							createKey(e2.getX() - 20, e2.getY() - 20, -5);
						}

						enemies2.remove(e2);
						e2 = null;
					}
				}

				for (int i = 0; i < words.size(); i++) {
					Words w = words.get(i);
					if (w.isVisible()) {
						w.update();
					} else {
						words.remove(i);
						w = null;
					}
				}

				for (int i = 0; i < enemies3.size(); i++) {
					Enemy3 e3 = enemies3.get(i);
					if (e3.isVisible()) {
						if (e3.isBleeding()) {
							createSlime(e3.getX() + e3.getWidth() / 2,
									e3.getY() + e3.getHeight() / 2);
						}
						e3.update();
					} else {

						if (e3.isHasKey()) {
							createKey(e3.getX() - 20, e3.getY() - 20, -5);
						}
						enemies3.remove(i);
						e3 = null;
					}
				}
			}
		}
	}

	public void counters() {
		// Calls the counters located on declutter
		super.counters();

		// If the wave is complete, a certain amount of time is waited, then the
		// next wave is spawned
		if (waveComplete) {
			if (waveCounter < waveMax) {
				waveCounter += 1;
			} else if (waveCounter == waveMax) {
				spawnRandomWave();
			}
		}
	}

	public void spawnBoxes() {
		// Spawns a box whenever there are less than 2 boxes on the screen at a
		// random location
		if (boxes.size() <= 1) {
			createBox(r.nextInt(580) + 20, r.nextInt(600));
		}
	}

	public void spawnRandomWave() {

		Random r = new Random();

		// Sets wave to be a random integer from 1-8 to correspond with a wave
		// number
		int wave = r.nextInt(8) + 1;

		// Checks the value of wave, and then sets values for that wave
		switch (wave) {
		case 1:
			wave1 = true;
			createEnemyMax = 30;
			waveEnemy = 0;
			waveComplete = false;
			createEnemyCounter = 0;
			break;
		case 2:
			wave2 = true;
			createEnemyMax = 30;
			waveEnemy = 0;
			waveComplete = false;
			createEnemyCounter = 0;
			break;
		case 3:
			wave3 = true;
			createEnemyMax = 15;
			waveEnemy = 0;
			waveComplete = false;
			createEnemyCounter = 0;
			break;
		case 4:
			wave4 = true;
			createEnemyMax = 15;
			waveEnemy = 0;
			waveComplete = false;
			createEnemyCounter = 0;
			break;
		case 5:
			wave5 = true;
			createEnemyMax = 60;
			waveEnemy = 0;
			waveComplete = false;
			createEnemyCounter = 0;
			break;
		case 6:
			wave6 = true;
			createEnemyMax = 60;
			waveEnemy = 0;
			waveComplete = false;
			createEnemyCounter = 0;
			break;
		case 7:
			wave7 = true;
			createEnemyMax = 30;
			waveEnemy = 0;
			waveComplete = false;
			createEnemyCounter = 0;
			break;
		case 8:
			wave8 = true;
			createEnemyMax = 90;
			waveEnemy = 0;
			waveComplete = false;
			createEnemyCounter = 0;
			break;
		}
	}

	public void paint(Graphics g) {
		// Clears the screen
		g.clearRect(0, 0, 640, 640);

		if (!menuScreen) {
			// If the menu screen is not visible, the objects are painted
			g.drawImage(backgroundImage, 0, 0, 650, 640, null);

			for (Key k : keys) {
				k.paint(g);
			}

			for (Box b : boxes) {
				b.paint(g);
			}

			for (Projectile p : projectiles) {
				p.paint(g);
			}

			for (WaterDecoration w : waters) {
				w.paint(g);
			}

			mainChar.paint(g);
			weapon.paint(g);

			for (Enemy e : enemies) {
				e.paint(g);
			}

			for (Enemy2 e2 : enemies2) {
				e2.paint(g);
			}

			for (Enemy3 e3 : enemies3) {
				e3.paint(g);
			}
			for (Tile t : tiles) {
				t.paint(g);
			}

			// Paints the walls for testing purposes
			/*
			 * for (Wall w : walls) { w.paint(g); }
			 */

			for (Explosion e : explosions) {
				e.paint(g);
			}

			// Draws the score in the upper right hand corner
			g.setColor(Color.WHITE);
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
			g.drawString("Chests Opened: " + score, 430, 20);

			for (Slime s : slimes) {
				s.paint(g);
			}

			for (Words w : words) {
				w.paint(g);
			}

			if (paused) {
				// If the game is paused, draws words on top of the paused game
				g.setColor(Color.WHITE);
				g.setFont(new Font("Comic Sans MS", Font.BOLD, 70));
				g.drawString("PAUSED", 185, 300);
				g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
				g.drawString("Press P to resume...", 225, 380);

			}

			if (gameLose) {
				// If the game is lost, draws words on top of the lost game

				// Sets High Score
				if (score > highScore) {
					highScore = score;
				}

				g.setColor(Color.WHITE);
				g.setFont(new Font("Comic Sans MS", Font.BOLD, 70));
				g.drawString("You Lose", 175, 300);
				g.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
				g.drawString("Please press R to restart", 150, 380);
				g.drawString("Your Score: " + score, 150, 430);
				g.drawString("High Score: " + highScore, 150, 480);

			}
		} else if (menuScreen) {
			// Paints menu screen when the menu screen is active
			menu.paint(g);
		}

	}

	public void collisionDetection() {

		/*
		 * Checks for collisions between one object's hitBox and the other
		 * object's hitBox Cycles through arraylists and checks for possible
		 * collisions
		 */

		for (Tile t : tiles) {
			Rectangle tHitBox = t.hitBox();

			for (Projectile p : projectiles) {
				Rectangle pHitBox = p.hitBox();
				if (tHitBox.intersects(pHitBox)) {
					if (p.getType() == 6) {

					} else {
						p.delete();
					}
				}
			}

			for (WaterDecoration w : waters) {
				Rectangle wHitBox = w.hitBox();
				if (tHitBox.intersects(wHitBox)) {
					w.delete();
				}
			}

			for (Box b : boxes) {
				Rectangle bHitBox = b.hitBox();
				if (bHitBox.intersects(tHitBox)) {
					b.setNoGravity(true);
					b.setY(t.getY() - b.getHeight());
				}
			}

			for (Slime s : slimes) {
				Rectangle sHitBox = s.hitBox();
				if (sHitBox.intersects(tHitBox)) {
					s.delete();
				}
			}

			for (Key k : keys) {
				// Sets no gravity for the keys when it hits a tile
				Rectangle kHitBox = k.hitBox();
				if (kHitBox.intersects(tHitBox)) {
					if (k.getSpeedY() < 0) {
						k.setY(t.getY() + t.getHeight() + 1);
					} else {
						k.setY(t.getY() - k.getHeight() - 15);
						k.setNoGravity(true);
					}

				}
			}

		}

		// Projectiles and Enemies...

		for (Projectile p : projectiles) {
			Rectangle pHitBox = p.hitBox();

			for (Enemy e : enemies) {
				Rectangle eHitBox = e.hitBox();
				if (eHitBox.intersects(pHitBox)) {
					// various damage mechanisms for various weapons.
					if (p.getType() == 4) {
						double eHealth = e.getHealth();
						double pHealth = p.getHealth();

						e.hit(pHealth);
						p.hit(eHealth);

					} else if (p.getType() == 5) {
						if (!e.isBleeding()) {
							e.bleed();
						}
					} else if (p.getType() == 6) {
						e.hit(p.getDamage());
					} else {
						p.delete();
						e.hit(p.getDamage());
					}
				}
			}

			for (Enemy2 e2 : enemies2) {
				Rectangle e2HitBox = e2.hitBox();
				if (e2HitBox.intersects(pHitBox)) {
					// various damage mechanisms for various weapons.
					if (p.getType() == 4) {
						double eHealth = e2.getHealth();
						double pHealth = p.getHealth();

						e2.hit(pHealth);
						p.hit(eHealth);
					} else if (p.getType() == 5) {
						if (!e2.isBleeding()) {
							e2.bleed();
						}
					} else if (p.getType() == 6) {
						e2.hit(p.getDamage());
					} else {
						p.delete();
						e2.hit(p.getDamage());
					}
				}
			}

			for (Enemy3 e3 : enemies3) {
				Rectangle e3HitBox = e3.hitBox();
				if (e3HitBox.intersects(pHitBox)) {
					// various damage mechanisms for various weapons.
					if (p.getType() == 4) {
						double eHealth = e3.getHealth();
						double pHealth = p.getHealth();

						e3.hit(pHealth);
						p.hit(eHealth);
					} else if (p.getType() == 5) {
						if (!e3.isBleeding()) {
							e3.bleed();
						}
					} else if (p.getType() == 6) {
						e3.hit(p.getDamage());
					} else {
						p.delete();
						e3.hit(p.getDamage());
					}
				}
			}

		}

		// Player...

		// First, moves the player horizontally to check for collision, then
		// moves the player vertically and checks for collision
		movePlayerX();
		movePlayerY();
		// First moves the enemies vertically to check for collision, then
		// moves the enemy horizontally and checks for collision
		moveEnemyY();
		moveEnemyX();
	}

	public void moveEnemyX() {
		// Moves the enemy horizontally and checks for collisions.
		for (Enemy e : enemies) {
			Rectangle eHitBox = e.hitBox();

			// If the enemy hits a wall, the enemy reverses directions
			for (Wall w : walls) {
				Rectangle wHitBox = w.hitBox();
				if (wHitBox.intersects(eHitBox)) {
					if (e.getSpeed() > 0) {
						e.setX(w.getX() - e.getWidth() - 1);
						e.setSpeed(-e.getMAX_SPEED());
					} else if (e.getSpeed() < 0) {
						e.setX(w.getX() + w.getWidth() + 1);
						e.setSpeed(e.getMAX_SPEED());
					}
				}
			}

			// Moves the enemy at the enemy's speed
			e.setX(e.getX() + e.getSpeed());
		}

		for (Enemy2 e : enemies2) {
			Rectangle eHitBox = e.hitBox();

			for (Wall w : walls) {
				Rectangle wHitBox = w.hitBox();
				if (wHitBox.intersects(eHitBox)) {
					if (e.getSpeed() > 0) {
						e.setX(w.getX() - e.getWidth() - 1);
						e.setSpeed(-e.getMAX_SPEED());
					} else if (e.getSpeed() < 0) {
						e.setX(w.getX() + w.getWidth() + 1);
						e.setSpeed(e.getMAX_SPEED());
					}
				}
			}

			e.setX(e.getX() + e.getSpeed());
		}

		for (Enemy3 e3 : enemies3) {
			Rectangle e3HitBox = e3.hitBox();

			for (Wall w : walls) {
				Rectangle wHitBox = w.hitBox();
				if (wHitBox.intersects(e3HitBox)) {
					if (e3.getSpeed() > 0) {
						e3.setX(w.getX() - e3.getWidth() - 1);
						e3.setSpeed(-e3.getMAX_SPEED());
					} else if (e3.getSpeed() < 0) {
						e3.setX(w.getX() + w.getWidth() + 1);
						e3.setSpeed(e3.getMAX_SPEED());
					}
				}
			}
			e3.setX(e3.getX() + e3.getSpeed());
		}
	}

	public void moveEnemyY() {
		// Moves the enemy Horizontally
		for (Enemy e : enemies) {
			Rectangle eHitBox = e.hitBox();
			e.setY(e.getY() + e.getSpeedY());

			for (Tile t : tiles) {
				// If the enemy hits a tile, it is set to a position outside the
				// tile, and gravity and vertical speed equals 0
				Rectangle tHitBox = t.hitBox();

				if (tHitBox.intersects(eHitBox)) {
					e.setGravity(0);
					e.setSpeedY(0);
					e.setY(t.getY() - e.getHeight());
				}
			}

			// If the enemy hits a GoUp, it is sent to the top of the screen
			for (GoUp go : goUps) {
				Rectangle goHitBox = go.hitBox();
				if (goHitBox.intersects(eHitBox)) {
					e.moveUp();
				}

			}

		}

		for (Enemy2 e : enemies2) {
			Rectangle eHitBox = e.hitBox();
			e.setY(e.getY() + e.getSpeedY());

			for (Tile t : tiles) {
				Rectangle tHitBox = t.hitBox();

				if (tHitBox.intersects(eHitBox)) {
					e.setGravity(0);
					e.setSpeedY(0);
					e.setY(t.getY() - e.getHeight());
				}
			}

			for (GoUp go : goUps) {
				Rectangle goHitBox = go.hitBox();
				if (goHitBox.intersects(eHitBox)) {
					e.moveUp();
				}

			}

		}

		for (Enemy3 e : enemies3) {
			Rectangle eHitBox = e.hitBox();
			e.setY(e.getY() + e.getSpeedY());

			for (Tile t : tiles) {
				Rectangle tHitBox = t.hitBox();

				if (tHitBox.intersects(eHitBox)) {
					e.setY(t.getY() - e.getHeight());
				}
			}

			for (GoUp go : goUps) {
				Rectangle goHitBox = go.hitBox();
				if (goHitBox.intersects(eHitBox)) {
					e.moveUp();
				}

			}
		}

	}

	public void movePlayerY() {
		// Moves the player vertically

		// If the player drops below the screen, the player loses
		if (mainChar.y > 670) {
			gameLose = true;
		}

		// If the player presses the z key, and the main character can jump, the
		// main character jumps
		if (keyHandler.isZKey() && mainChar.canJump) {
			mainChar.speedY = 0;
			mainChar.gravity = 0;
			mainChar.canJump = false;
			// Jumps at initial speed -7.4
			mainChar.speedY = -7.4;
		}

		mainChar.y += mainChar.speedY;

		Rectangle hitBox = mainChar.hitBox();

		// Y collision detection here

		for (Tile t : tiles) {
			Rectangle tHitBox = t.hitBox();

			if (tHitBox.intersects(hitBox) && mainChar.speedY >= 0) {
				mainChar.gravity = 0;
				mainChar.speedY = 0;
				mainChar.canJump = true;
				mainChar.y = t.getY() - mainChar.height;
			} else if (tHitBox.intersects(hitBox) && mainChar.speedY < 0) {
				mainChar.gravity = 2;
				mainChar.y = t.getY() + t.getHeight();
			}
		}
	}

	public void movePlayerX() {
		// Moves the player horizontally

		if (keyHandler.isRightKey()) {
			mainChar.x += mainChar.speed;
			mainChar.moving = 1;
			mainChar.direction = 1;
		} else if (keyHandler.isLeftKey()) {
			mainChar.x -= mainChar.speed;
			mainChar.moving = -1;
			mainChar.direction = -1;
		} else {
			mainChar.moving = 0;
		}

		Rectangle hitBox = mainChar.hitBox();
		Rectangle damageHitBox = mainChar.damageHitBox();

		// Collisions with crates. If the character hits a crate and has a key,
		// a word is displayed and the crate is opened
		if (mainChar.key >= 1) {

			for (Box b : boxes) {
				Rectangle bHitBox = b.hitBox();
				if (bHitBox.intersects(hitBox) && !b.isOpened()) {
					score += 1;
					mainChar.key -= 1;
					b.setOpened(true);
					int nextWeapon = r.nextInt(6) + 1;

					boolean getNextWep = true;

					while (nextWeapon == weapon.equipped) {
						nextWeapon = r.nextInt(6) + 1;
					}

					weapon.equipped = nextWeapon;

					switch (weapon.equipped) {
					case 1:
						createWord(mainChar.x - 20, mainChar.y, "Water Gun");
						break;
					case 2:
						createWord(mainChar.x - 20, mainChar.y,
								"Potato Launcher");
						break;
					case 3:
						createWord(mainChar.x - 7, mainChar.y, "Sling");
						break;
					case 4:
						createWord(mainChar.x - 10, mainChar.y, "Shuriken");
						break;
					case 5:
						createWord(mainChar.x - 10, mainChar.y, "Spear");
						break;
					case 6:
						createWord(mainChar.x - 10, mainChar.y, "Laser");
						break;
					}

					weapon.checkEquipped();
				}

			}

		}

		// Collisions with keys
		for (Key k : keys) {
			Rectangle kHitBox = k.hitBox();
			if (kHitBox.intersects(hitBox)) {
				mainChar.key += 1;
				k.delete();
			}
		}

		// Collision with enemies
		for (Enemy e : enemies) {
			Rectangle eHitBox = e.hitBox();
			if (eHitBox.intersects(damageHitBox)) {
				// If the character is not invulnerable, the character is
				// damaged
				if (mainChar.invulnCounter == mainChar.invulnMax) {
					mainChar.heart -= 1;
					mainChar.invulnCounter = 0;

					// If the character's hearts are equal to or below zero, the
					// game is lost
					if (mainChar.heart <= 0) {
						gameLose = true;
					}
				}

			}
		}

		for (Enemy2 e2 : enemies2) {
			Rectangle e2HitBox = e2.hitBox();
			if (e2HitBox.intersects(damageHitBox)) {
				if (mainChar.invulnCounter == mainChar.invulnMax) {
					mainChar.heart -= 1;
					mainChar.invulnCounter = 0;

					if (mainChar.heart <= 0) {
						gameLose = true;
					}
				}
			}
		}

		for (Enemy3 e3 : enemies3) {
			Rectangle e3HitBox = e3.hitBox();
			if (e3HitBox.intersects(damageHitBox)) {
				if (mainChar.invulnCounter == mainChar.invulnMax) {
					mainChar.heart -= 1;
					mainChar.invulnCounter = 0;

					if (mainChar.heart <= 0) {
						gameLose = true;
					}
				}
			}
		}
		// Collision Detection Tiles here
		for (Tile t : tiles) {
			Rectangle tHitBox = t.hitBox();
			if (tHitBox.intersects(hitBox)) {

				if (mainChar.direction == -1) {
					mainChar.x = t.getX() + t.getWidth() + 1;
				} else if (mainChar.direction == 1) {
					mainChar.x = t.getX() - mainChar.width - 1;
				}

			}
		}

	}

	public void checkKeyPress() {
		// Checks for key presses

		if (menuScreen) {
			// Checks for key presses on the menu screen
			if (keyHandler.downKey) {
				if (menu.onStart) {
					menu.onInstructions = true;
					menu.onStart = false;
					menu.onBack = false;
				}
			}

			if (keyHandler.upKey) {
				if (menu.onInstructions) {
					menu.onStart = true;
					menu.onInstructions = false;
					menu.onBack = false;
				}
			}

			if (keyHandler.enterKey) {
				if (menu.onStart) {
					menuScreen = false;
					running = true;
					paused = false;
				} else if (menu.onInstructions) {
					menu.showInstructions = true;
				} else if (menu.onBack) {
					menu.showInstructions = false;
					menu.onInstructions = true;
				}

				keyHandler.enterKey = false;
			}

		} else if (!paused) {
			// Checls for key presses when the player is playing
			Random r = new Random();

			if (keyHandler.isXKey() && weapon.canShoot) {

				/*
				 * When the player presses the X key, it checks if the player
				 * can shoot, then checks for the equipped weapon in order to
				 * create the appropriate projectile
				 */

				if (weapon.equipped == 1) {

					if (mainChar.direction == -1) {
						createProjectile(weapon.x, weapon.y,
								mainChar.direction, weapon.damage,
								weapon.spread, 1);
						createWater(weapon.x, weapon.y, mainChar.direction,
								weapon.spread);
						createWater(weapon.x + weapon.width - 7, weapon.y,
								mainChar.direction, weapon.spread);
					} else if (mainChar.direction == 1) {
						createProjectile(weapon.x + weapon.width - 10,
								weapon.y, mainChar.direction, weapon.damage,
								weapon.spread, 1);
						createWater(weapon.x + weapon.width - 7, weapon.y,
								mainChar.direction, weapon.spread);
						createWater(weapon.x + weapon.width - 7, weapon.y,
								mainChar.direction, weapon.spread);
					}

				} else if (weapon.equipped == 2) {
					if (mainChar.direction == -1) {
						createProjectile(weapon.x, weapon.y,
								mainChar.direction, weapon.damage,
								weapon.spread, 2);
						createExplosion(weapon.x - 50, weapon.y - 20,
								mainChar.direction);
					} else if (mainChar.direction == 1) {
						createProjectile(weapon.x + weapon.width, weapon.y,
								mainChar.direction, weapon.damage,
								weapon.spread, 2);
						createExplosion(weapon.x + weapon.width + 5,
								weapon.y - 20, mainChar.direction);
					}
				} else if (weapon.equipped == 3) {
					createProjectile(weapon.x, weapon.y - 5,
							mainChar.direction, weapon.damage, weapon.spread, 3);
					weapon.spread += 1.3;
					weapon.shooting = true;
				} else if (weapon.equipped == 4) {
					if (mainChar.direction == -1) {
						createProjectile(weapon.x, weapon.y - 10,
								mainChar.direction, weapon.damage,
								weapon.spread, 4);
					} else if (mainChar.direction == 1) {
						createProjectile(weapon.x, weapon.y - 10,
								mainChar.direction, weapon.damage,
								weapon.spread, 4);
					}
				} else if (weapon.equipped == 5) {
					createProjectile(weapon.x, weapon.y, mainChar.direction,
							weapon.damage, weapon.spread, 5);
				} else if (weapon.equipped == 6) {
					if (mainChar.direction == -1) {
						createProjectile(weapon.x - 650, weapon.y - 5,
								mainChar.direction, weapon.damage,
								weapon.spread, 6);
					} else if (mainChar.direction == 1) {
						createProjectile(weapon.x + 35, weapon.y - 5,
								mainChar.direction, weapon.damage,
								weapon.spread, 6);
					}
				}

				weapon.canShoot = false;
				weapon.shootCounter = 0;
			}

			if (!keyHandler.isXKey()) {
				weapon.shooting = false;
			}

			// Pauses the game when the player presses p
			if (keyHandler.isPKey()) {
				paused = true;
				keyHandler.setPKey(false);
			}

		} else if (paused) {
			if (keyHandler.isPKey()) {
				paused = false;
				keyHandler.setPKey(false);
			}
		}

		if (gameLose) {
			// If the game is lost, the player has an option to reset the game
			if (keyHandler.isRKey()) {
				keyHandler.setRKey(false);
				gameReset();
			}
		}
	}
}
