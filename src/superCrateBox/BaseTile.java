package superCrateBox;

import java.awt.Rectangle;

public class BaseTile {

	protected double x, y;
	protected int width = 20, height = 20;

	public Rectangle hitBox(){
		//Gets a rectangular hitBox for the tile
		return new Rectangle((int)x, (int)y, width, height);
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
}
