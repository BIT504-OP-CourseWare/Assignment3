import java.awt.Rectangle;

public class Sprite {
	
	protected int x,y,width,height;
	
	// Note: This should only set a single value, they can be done in a single statement
	public void setX(int x) {
		// TODO
		this.x = x;
	}
	public void setY(int y) { 
		// TODO as in x
	}
	public void setWidth(int width) { 
		// TODO
		this.width = ???; //What is ???. Check the attributes of the Sprite class
	}
	public void setHeight(int height) { 
		// TODO as in width
	}
	
	// Note: Change the "0" to the correct variable
	public int getX() { 
		return 0;	// TODO: Return correct value
		//This should be return x; Do the rest for all the getter methods below
	}
	public int getY() { 
		return 0;	// TODO: Return correct value
	}
	public int getWidth() { 
		return 0;	// TODO: Return correct value
	}
	public int getHeight() { 
		return 0;	// TODO: Return correct value
	}
	
	Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}
}
