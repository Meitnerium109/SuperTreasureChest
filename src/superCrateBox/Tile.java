
package superCrateBox;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

//Regular tile. The character cannot move through these tiles
public class Tile extends BaseTile {

	private Image tileImage;
	
	public Tile(double x, double y){
		this.x = x;
		this.y = y;
		
		tileImage = new ImageIcon(getClass().getResource(
				"/images/Rock.png")).getImage();
		
	}
	
	public void paint(Graphics g){
		g.drawImage(tileImage,(int)x,(int)y, width, height,  null);
	}
}
