import java.awt.Graphics;

public class Brick extends Sprite {
	
	private boolean broken = false;
	
	public Brick(int x, int y) {
		// TODO: Set x using the parameter
		setX(x);
		// TODO: Set y using the parameter. See above for X
		setY(y);
		
		// TODO: Set the width and height of the brick using Settings.BRICK_WIDTH/HEIGHT
		setWidth(Settings.BRICK_WIDTH); //use a parameter from the settings to assign a value to ???
		setHeight(Settings.BRICK_HEIGHT); //Do the same for height using appropriate method and parameter setting
	}

	public boolean isBroken() {
		
		return broken;	// TODO: Return the correct variable. //You can't write return false;
		                // or return trues; it has to be dynamic. Which variable contains the status
		                // of a Bricks object!?
	}
	public void setBroken(boolean b) {
		// TODO: Set the broken variable using the parameter given
		//again if you know which varaible contains the status of the Brick object from the 
		//above task then you could set its value as,
		broken = b;
	}
	
	public void paint(Graphics g) {
		if(!broken) {
			g.fillRect(x, y, Settings.BRICK_WIDTH, Settings.BRICK_HEIGHT);
		}
	}
}
