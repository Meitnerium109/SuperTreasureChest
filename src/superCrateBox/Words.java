
package superCrateBox;
import java.awt.Font;
import java.awt.Graphics;

public class Words {
	
	private double x, y;
	private String word;
	private boolean visible;
	
	private int moveWord = 0;
	
	private int visibleCounter = 0, visibleMax = 50;
	
	private Font defaultFont = new Font("Comic Sans MS", Font.BOLD, 20);
	
	public Words(double x, double y, String word){
		//Constructs the word at x and y locations with the string "word"
		
		this.x = x;
		this.y = y;
		
		this.word = word;
		
		visible = true;
	}
	
	public void update(){
		movement();
	}
	
	public void movement(){
		//Deletes the word once visibleCounter has reached visibleMax
		if(visibleCounter < visibleMax){
			visibleCounter ++;
		} else if (visibleCounter == visibleMax){
			visible = false;
		}
		
		//Moves the word upwards
		y -= 1;
	}
	
	public void paint(Graphics g){
		g.drawString(word,(int) x, (int)y);
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

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
}
