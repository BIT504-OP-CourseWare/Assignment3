import java.awt.Graphics;

public class Ball extends Sprite {

	private int xVelocity=1, yVelocity=-1;
	
	// Constructor
	public Ball() {
		// TODO: Set width to Settings.BALL_WIDTH as follows:
		setWidth(Settings.BALL_WIDTH);
		
		// TODO: Set width to Settings.BALL_HEIGHT. Do the same here by calling setHeight()
		setHeight(Settings.BALL_HEIGHT);
		
		// TODO: Call resetPosition. Just call the resetPosition(). Straight forward!!!
		resetPosition();
	}
	
	/**
	 * Resets the ball to the initial position
	 * Uses Settings.INITIAL_BALL_X/Y to set the position of the ball
	 */
	public void resetPosition() {
		
		//Although this is how you reset the position of x but you also need to update
		//the x variable in the Sprite class. Remember that this Ball class extends the
		//Sprite class. So just writing setX(Settings.INITIAL_BALL_X); is not sufficinet. So
		//change the below statement to:
		
		x = Settings.INITIAL_BALL_X;
		
				
		// TODO: Set the balls y by using the INITIAL_BALL_Y (see above). Do the same here
		//to update the y position for the Sprite class
		y = Settings.INITIAL_BALL_Y;
	
	}
	
	public void update() {
		
		//Remember this update actually changes the position of your ball as in the resetPosition
		//method. However, this one is for the entire game whereas the reset one is only at the 
		//beginnig of the game. The concept is same where you need to updat the x and y varialbes
		//belongiong to the sprite class. In this case the velocity updates the position.
		
		x += xVelocity;
		
		// TODO: Increase the y variable by yVelocity (see above). Do the same as above only update 
		//the y in this case
		
		y += yVelocity;
		
		//==========================================================================
		// Bounce off left side of screen
		if(x <= 0) { //This means that if the x position when updated becomes -1
			
						// TODO: Set x to 0 so it does not leave the screen
			setX(0);   //remember that setX() method is a Sprite class method
			
			// TODO: Change the x velocity to make the ball go right
			//Hint use setXVelocity(????) method but what value will you put between brackets
			//what is ????. Bouncing to right, what value will make the ball go to right?
			setXVelocity(x+1);
			
		}
		
		// Bounce off right side of screen
		if(x >= Settings.WINDOW_WIDTH - (2*Settings.BALL_WIDTH)) {
			
			//Again as it is the case with bouncing off left, use setX and setXVelocity
			//but with appropriate values to update
			
			// TODO: Set x to the right edge of the screen (see the above if condition)
			// TODO: Change the x velocity to make the ball go left
			setX(x);
			setXVelocity(-1);
		}
		//==========================================================================
		
		//REMEMBER THAT BECUASE BOUNCING OFF THE RIGHT OR LEFT SIDE ARE HORIZONTAL LIMITS
		//ONLY X IS CHANGED THROUGH setX and setXVelocity METHODS. IT DOES NOT EFFECT
		//THE Y DIRECTION
		
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		// Bounce off top of screen
		if(y <= 0) {
			
			//Obviously setY and setYVelocity withh be used but with appropriate values
			//rememeber there is not bottom screen bounce off becuase there is no boundary at 
			//the bottom of the screen
			
			// TODO: Set y to 0 so it does not leave the screen
			// TODO: Change the y velocity to make the ball go downward
			setY(0);
			setYVelocity(1);
		}
		else if(y >= Settings.WINDOW_HEIGHT - Settings.BALL_HEIGHT) {
			
			resetPosition();
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		}
	}
	
	public void setXVelocity(int x) {
		// TODO: Set the x velocity. How would you do this. 
		xVelocity = x;   //Remember that xVelocity is the attribute of the Ball class
						// this x is not the actual x of the Sprite class. This x is 
		                //only a local variable
		
	}
	public void setYVelocity(int y) {
		// TODO: Set the y velocity. Do the same as above
		yVelocity = y;
	}
	
	public int getXVelocity() {
		return xVelocity;	// TODO: Return the x velocity
	}
	public int getYVelocity() {

		return yVelocity;	// TODO: Return the y velocity. In place of 0, replace with the y velocity
		            //As simple as that!!!!!!!!
	}
	
	public void paint(Graphics g) {
		g.fillOval(x, y, Settings.BALL_WIDTH, Settings.BALL_HEIGHT);
	}
}
