
package superCrateBox;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/*Sets up the main menu for the game*/

public class Menu {

	private int x, y, width, height;
	int selectX, selectY, selectHeight, selectWidth;

	private Image start, instructions, back, instructionsShown;
	boolean onStart = true, onInstructions, showInstructions, onBack;

	public Menu() {
		// Constructs the Menu
		x = 0;
		y = 0;

		width = 650;
		height = 640;

		loadImages();

		selectHeight = 75;
		selectWidth = 650;

		selectX = 0;
		selectY = 200;
	}

	public void loadImages() {
		// Loads all images

		start = new ImageIcon(getClass().getResource("/images/menuStart.png"))
				.getImage();
		instructions =new ImageIcon(getClass().getResource(
				"/images/menuInstructions.png")).getImage();
		back = new ImageIcon(getClass().getResource(
				"/images/menuBack.png")).getImage();
		instructionsShown = new ImageIcon(getClass().getResource(
				"/images/menuShowInstructions.png")).getImage();
	}

	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, 650, 640);

		// Sets the grey back area to be on whatever is selected
		if (onStart) {
			selectY = 200;
		} else if (onInstructions) {
			selectY = 275;
		} else if (onBack) {
			selectY = 500;
		}
		g.setColor(Color.DARK_GRAY);
		g.fillRect(selectX, selectY, selectWidth, selectHeight);

		if (!showInstructions) {
			// Draw Start Button
			g.drawImage(start, x + 100, y + 200, 440, 75, null);
			g.drawImage(instructions, x + 100, y + 275, 440, 75, null);
			g.drawString("By: Meitnerium109", 250, 580);
		} else if (showInstructions) {
			onBack = true;
			onInstructions = false;
			onStart = false;

			g.drawImage(back, x + 100, y + 500, 440, 75, null);
			g.drawImage(instructionsShown, 0, 0, 650, 640, null);
		}
		
		
	}

}
