package superCrateBox;

import java.util.ArrayList;

import javax.swing.JPanel;

//Abstract class solely to put all the ugly stuff so the main class does not get crowded

public abstract class Declutter extends JPanel {

	protected int createEnemyCounter, createEnemyMax, waveEnemy,
			waveCounter = 0, waveMax = 170;
	protected boolean wave1, wave2, wave3, wave4, wave5, wave6, wave7, wave8;
	protected boolean waveComplete = true;

/*	Arrays for all the waves that will be spawned. 
	The numbers in the arrays represent the enemy that is spawned.
	The array is read for which enemy to spawn, and that enemy is spawned*/
	private int[] spawnWave1 = { 11, 11, 11 };
	private int[] spawnWave2 = { 10, 10, 10 };
	private int[] spawnWave3 = { 20, 20, 20, 20, 20 };
	private int[] spawnWave4 = { 21, 21, 21, 21, 21 };
	private int[] spawnWave5 = { 31, 31 };
	private int[] spawnWave6 = { 30, 30 };
	private int[] spawnWave7_1 = { 20, 20, 20 };
	private int[] spawnWave7_2 = { 21, 21, 21 };
	private int[] spawnWave8_1 = { 10, 10 };
	private int[] spawnWave8_2 = { 11, 11 };

	//Huge list of ArrayLists for stuff
	ArrayList<GoUp> goUps = new ArrayList<GoUp>();
	ArrayList<Enemy3> enemies3 = new ArrayList<Enemy3>();
	ArrayList<Words> words = new ArrayList<Words>();
	ArrayList<Slime> slimes = new ArrayList<Slime>();
	ArrayList<Explosion> explosions = new ArrayList<Explosion>();
	ArrayList<Key> keys = new ArrayList<Key>();
	ArrayList<Tile> tiles = new ArrayList<Tile>();
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	ArrayList<Wall> walls = new ArrayList<Wall>();
	ArrayList<Enemy2> enemies2 = new ArrayList<Enemy2>();
	ArrayList<WaterDecoration> waters = new ArrayList<WaterDecoration>();
	ArrayList<Box> boxes = new ArrayList<Box>();

	/*All the below "creators" are a way of shortening the typing.
	The "creators" creates a certain object, then adds it to that
	respective array list so the object will be updated, etc.*/
	
	public void createEnemy3(double x, double y, int direction) {
		Enemy3 e3 = new Enemy3(x, y, direction);
		enemies3.add(e3);
	}

	public void createExplosion(double x, double y, int direction) {
		Explosion e = new Explosion(x, y, direction);
		explosions.add(e);
	}

	public void createKey(double x, double y, double speedY) {
		Key k = new Key(x, y, speedY);
		keys.add(k);
	}

	public void createBox(double x, double y) {
		Box b = new Box(x, y);
		boxes.add(b);
	}

	public void createTile(int x, int y) {
		Tile t = new Tile(x * 20, y * 20);
		tiles.add(t);
	}

	public void createTilesX(int x, int y, int numberOfTiles) {
		for (int i = 0; i < numberOfTiles; i++) {
			Tile t = new Tile((x + i) * 20, y * 20);
			tiles.add(t);
		}
	}

	public void createWall(double x, int y) {
		Wall w = new Wall(x, y * 20);
		walls.add(w);
	}

	public void createGoUp(double x, int y) {
		GoUp go = new GoUp(x, y * 20);
		goUps.add(go);
	}

	public void createTilesY(int x, int y, int numberOfTiles) {
		for (int i = 0; i < numberOfTiles; i++) {
			Tile t = new Tile(x * 20, (y + i) * 20);
			tiles.add(t);
		}
	}

	public void createProjectile(double x, double y, int direction,
			double damage, double spread, int type) {
		Projectile p = new Projectile(x, y, direction, damage, spread, type);
		projectiles.add(p);
	}

	public void createWater(double x, double y, int direction, double spread) {
		WaterDecoration w = new WaterDecoration(x, y, direction, spread, 1);
		waters.add(w);
	}

	public void createSteam(double x, double y, int direction, double spread) {
		WaterDecoration w = new WaterDecoration(x, y, direction, spread, 2);
		waters.add(w);
	}

	public void createEnemy(int enemy) {
		/*When the wave array is read, the numbers on the array will correspond
		with a number below, and that enemy will be spawned*/
		
		switch (enemy) {

		case 10:
			Enemy e10 = new Enemy(320, -10, "left");
			enemies.add(e10);
			break;
		case 11:
			Enemy e11 = new Enemy(320, -10, "right");
			enemies.add(e11);
			break;
		case 20:
			Enemy2 e20 = new Enemy2(320, -10, "left");
			enemies2.add(e20);
			break;
		case 21:
			Enemy2 e21 = new Enemy2(320, -10, "right");
			enemies2.add(e21);
			break;
		case 30:
			Enemy3 e30 = new Enemy3(320, -10, -1);
			enemies3.add(e30);
			break;
		case 31:
			Enemy3 e31 = new Enemy3(320, -10, 1);
			enemies3.add(e31);
			break;

		}

	}

	public void createEnemy2(double x, double y, String direction) {
		Enemy2 e2 = new Enemy2(x, y, direction);
		enemies2.add(e2);
	}

	public void counters() {
		/*Spawns the enemy with a time (createEnemyMax) in between 
		the enemy spawns. If the wave is not complete yet, the 
		enemies will be spawned until the wave is complete. If a 
		certain wave boolean is true, enemies will be spawned from
		that wave. */
		if (!waveComplete) {

			if (wave1) {
				if (createEnemyCounter < createEnemyMax) {
					createEnemyCounter += 1;
				} else if (createEnemyCounter == createEnemyMax) {
					createEnemyCounter = 0;
					if (waveEnemy < spawnWave1.length) {
						createEnemy(spawnWave1[waveEnemy]);
						waveEnemy += 1;
					} else if (waveEnemy == spawnWave1.length) {
						waveComplete = true;
						wave1 = false;
						waveCounter = 0;
					}
				}
			}

			if (wave2) {
				if (createEnemyCounter < createEnemyMax) {
					createEnemyCounter += 1;
				} else if (createEnemyCounter == createEnemyMax) {
					createEnemyCounter = 0;
					if (waveEnemy < spawnWave2.length) {
						createEnemy(spawnWave2[waveEnemy]);
						waveEnemy += 1;
					} else if (waveEnemy == spawnWave2.length) {
						waveComplete = true;
						wave2 = false;
						waveCounter = 0;
					}
				}
			}

			if (wave3) {
				if (createEnemyCounter < createEnemyMax) {
					createEnemyCounter += 1;
				} else if (createEnemyCounter == createEnemyMax) {
					createEnemyCounter = 0;
					if (waveEnemy < spawnWave3.length) {
						createEnemy(spawnWave3[waveEnemy]);
						waveEnemy += 1;
					} else if (waveEnemy == spawnWave3.length) {
						waveComplete = true;
						wave3 = false;
						waveCounter = 0;
					}
				}
			}

			if (wave4) {
				if (createEnemyCounter < createEnemyMax) {
					createEnemyCounter += 1;
				} else if (createEnemyCounter == createEnemyMax) {
					createEnemyCounter = 0;
					if (waveEnemy < spawnWave4.length) {
						createEnemy(spawnWave4[waveEnemy]);
						waveEnemy += 1;
					} else if (waveEnemy == spawnWave4.length) {
						waveComplete = true;
						wave4 = false;
						waveCounter = 0;
					}
				}
			}

			if (wave5) {
				if (createEnemyCounter < createEnemyMax) {
					createEnemyCounter += 1;
				} else if (createEnemyCounter == createEnemyMax) {
					createEnemyCounter = 0;
					if (waveEnemy < spawnWave5.length) {
						createEnemy(spawnWave5[waveEnemy]);
						waveEnemy += 1;
					} else if (waveEnemy == spawnWave5.length) {
						waveComplete = true;
						wave5 = false;
						waveCounter = 0;
					}
				}
			}

			if (wave6) {
				if (createEnemyCounter < createEnemyMax) {
					createEnemyCounter += 1;
				} else if (createEnemyCounter == createEnemyMax) {
					createEnemyCounter = 0;
					if (waveEnemy < spawnWave6.length) {
						createEnemy(spawnWave6[waveEnemy]);
						waveEnemy += 1;
					} else if (waveEnemy == spawnWave6.length) {
						waveComplete = true;
						wave6 = false;
						waveCounter = 0;
					}
				}
			}

			if (wave7) {
				if (createEnemyCounter < createEnemyMax) {
					createEnemyCounter += 1;
				} else if (createEnemyCounter == createEnemyMax) {
					createEnemyCounter = 0;
					if (waveEnemy < spawnWave7_1.length) {
						createEnemy(spawnWave7_1[waveEnemy]);
						createEnemy(spawnWave7_2[waveEnemy]);
						waveEnemy += 1;
					} else if (waveEnemy == spawnWave7_1.length
							&& waveEnemy == spawnWave7_2.length) {
						waveComplete = true;
						wave7 = false;
						waveCounter = 0;
					}
				}
			}

			if (wave8) {
				if (createEnemyCounter < createEnemyMax) {
					createEnemyCounter += 1;
				} else if (createEnemyCounter == createEnemyMax) {
					createEnemyCounter = 0;
					if (waveEnemy < spawnWave8_1.length) {
						createEnemy(spawnWave8_1[waveEnemy]);
						createEnemy(spawnWave8_2[waveEnemy]);
						waveEnemy += 1;
					} else if (waveEnemy == spawnWave8_1.length
							&& waveEnemy == spawnWave8_2.length) {
						waveComplete = true;
						wave8 = false;
						waveCounter = 0;
					}
				}
			}
		}
	}

	public void createWord(double x, double y, String word) {
		Words w = new Words(x, y, word);
		words.add(w);
	}

	public void createSlime(double x, double y) {
		Slime s = new Slime(x, y);
		slimes.add(s);
	}
}
