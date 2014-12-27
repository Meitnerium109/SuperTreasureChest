
package superCrateBox;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Handles all the key presses in the game
public class KeyHandler implements KeyListener {

	boolean upKey, leftKey, rightKey, downKey, zKey, xKey, pKey, rKey, eKey, enterKey;

	@Override
	public void keyPressed(KeyEvent e) {
		//When a key is pressed, set a boolean to equal true
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			upKey = true;
			break;
		case KeyEvent.VK_LEFT:
			leftKey = true;
			break;
		case KeyEvent.VK_RIGHT:
			rightKey = true;
			break;
		case KeyEvent.VK_DOWN:
			downKey = true;
			break;
		case KeyEvent.VK_Z:
			zKey = true;
			break;
		case KeyEvent.VK_X:
			xKey = true;
			break;
		case KeyEvent.VK_E:
			eKey = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//When a key is released, set a boolean to true or false, depending on the key
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			upKey = false;
			break;
		case KeyEvent.VK_LEFT:
			leftKey = false;
			break;
		case KeyEvent.VK_RIGHT:
			rightKey = false;
			break;
		case KeyEvent.VK_DOWN:
			downKey = false;
			break;
		case KeyEvent.VK_Z:
			zKey = false;
			break;
		case KeyEvent.VK_X:
			setxKey(false);
			break;
		case KeyEvent.VK_E:
			eKey = false;
			break;
		case KeyEvent.VK_P:
			pKey = true;
			break;
		case KeyEvent.VK_R:
			rKey = true;
			break;
		case KeyEvent.VK_ENTER:
			enterKey = true;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean isUpKey() {
		return upKey;
	}

	public void setUpKey(boolean upKey) {
		this.upKey = upKey;
	}

	public boolean isLeftKey() {
		return leftKey;
	}

	public void setLeftKey(boolean leftKey) {
		this.leftKey = leftKey;
	}

	public boolean isRightKey() {
		return rightKey;
	}

	public void setRightKey(boolean rightKey) {
		this.rightKey = rightKey;
	}

	public boolean isDownKey() {
		return downKey;
	}

	public void setDownKey(boolean downKey) {
		this.downKey = downKey;
	}

	public boolean isZKey() {
		return zKey;
	}

	public void setZKey(boolean key) {
		zKey = key;
	}

	public boolean isXKey() {
		return isxKey();
	}

	public void setXKey(boolean key) {
		setxKey(key);
	}

	public boolean isEKey() {
		return eKey;
	}

	public boolean isxKey() {
		return xKey;
	}

	public void setxKey(boolean xKey) {
		this.xKey = xKey;
	}

	public boolean isPKey() {
		return pKey;
	}

	public void setPKey(boolean pKey) {
		this.pKey = pKey;
	}

	public boolean isRKey() {
		return rKey;
	}

	public void setRKey(boolean key) {
		rKey = key;
	}
	
	
}
