import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class BreakoutPanel extends JPanel implements ActionListener, KeyListener {
	
	static final long serialVersionUID = 2L;

	private boolean gameRunning = true;
	private boolean gameOver = false;      //I beleive this was not provided in the code. So add this
	private int livesLeft = 3;
	private String screenMessage = "";
	private Ball ball;
	private Paddle paddle;
	private Brick bricks[];
	
	public BreakoutPanel(Breakout game) {
		
		addKeyListener(this);
		setFocusable(true);
		
		Timer timer = new Timer(5, this);
		timer.start();
		
		// TODO: Create a new ball object and assign it to the appropriate variable
		ball = new Ball() ;
		
		// TODO: Create a new paddle object and assign it to the appropriate variable
		//As with the ball, we need to create a paddle object
		paddle = new Paddle();
		
		
		// TODO: Create a new bricks array (Use Settings.TOTAL_BRICKS)
		bricks = new Brick[Settings.TOTAL_BRICKS]; //For bricks, we are creating not one object but an
		                           //array of objects. So each brick is an object and the 
		                           //collection of bricks objects are stored in an array
		                           // Replace ????? with the total bricks mentioned in the 
		                           //Settings.java
		
		// TODO: Call the createBricks() method
		//Just call the createBricks() becuase so far you have declared an array of objects 
		//but have not created one.
		createBricks();
	}
	
	//Look how the Bricks are created. It is one single statment that creates one single brick
	//bricks[counter] = new Brick((x * Settings.BRICK_WIDTH) + Settings.BRICK_HORI_PADDING + x_space, (y * Settings.BRICK_HEIGHT) + Settings.BRICK_VERT_PADDING + y_space);
	//Now if you check the Bricks class and the constructor, you will find that a brick object requires
	//a position x and y (note both are set using the setX and setY methods as we discussed before as part of the Sprite class)
	//the complex statment inside new Brick(------) is a geometrical displacment calculation. If you do not understand it, then that is fine
	//The reason for having two nested loops is again has to do with the geometrical calculation as the bricks are arranged in rows and columns
	//We could have done this using a single loop but it would be more complicated calculation
	//Note however that the bricks array is single dimension. So if there are 4 rows of bricks and each row has 5 bricks, the total number of bricks become 20 and
	//that is the lenght of the array
	private void createBricks() {
		int counter = 0;
		int x_space = 0;
		int y_space = 0;
		for(int x = 0; x < 4; x++) {
			for(int y = 0; y < 5; y++) {
				bricks[counter] = new Brick((x * Settings.BRICK_WIDTH) + Settings.BRICK_HORI_PADDING + x_space, (y * Settings.BRICK_HEIGHT) + Settings.BRICK_VERT_PADDING + y_space);
				counter++;
				y_space++;
			}
			x_space++;
			y_space = 0;
		}
	}
	
	private void paintBricks(Graphics g) {
		// TODO: Loop through the bricks and call the paint() method
		//Again use the same concept as createBricks. Use doble nested loop but now in place of the 
		//brick creation statment, paint that brick you just created as follows:
		int counter = 0;
		for(int x = 0; x < 4; x++) {
			for(int y = 0; y < 5; y++) {
				bricks[counter].paint(g); //Note that paint(g) is a method in the Brick class
				counter++;
			}
		}
	}
	
	//This method is used to continously check the status of the game using the parameter
	//gameRunning. So if the gameRunning is true, then you need to issue a statement to update 
	//ball and paddle location and see if there is collision.  
	private void update() {
		if(gameRunning) {
			
			// TODO: Update the ball and paddle
			ball.update();
			//Do the same for paddle. Note both ball and paddle have their own update methods
			paddle.update();
			collisions();
			repaint();
		}
	}
	
	private void gameOver() {
		// TODO: Set screen message
		//You need to set the gameOver to true
		gameOver = true; 
		//Then set the screen message as follows:
		screenMessage = "Game over";
		stopGame();
	}
	
	private void gameWon() {
		// TODO: Set screen message. I will not repeat this here. See the gameOver() method
		gameOver = true;
		screenMessage = "You Won";
		stopGame();
	}
	
	private void stopGame() {
		gameRunning = false;
	}
	
	private void collisions() {
		// Check for loss
		if(ball.y > 450) {
			// Game over
			livesLeft--;
			if(livesLeft <= 0) {
				gameOver();
				return;
			} else {
				ball.resetPosition();
				ball.setYVelocity(-1);
			}
		}
		
		// Check for win
		boolean bricksLeft = false;
		for(int i = 0; i < bricks.length; i++) {
			// Check if there are any bricks left
			if(!bricks[i].isBroken()) {
				// Brick was found, close loop
				bricksLeft = true;
				break;
			}
		}
		if(!bricksLeft) {
			gameWon();
			return;
		}
		
		// Check collisions
		if(ball.getRectangle().intersects(paddle.getRectangle())) {
			// Simplified touching of paddle
			// Proper game would change angle of ball depending on where it hit the paddle
			ball.setYVelocity(-1);
		}
		
		for(int i = 0; i < bricks.length; i++) {
			if (ball.getRectangle().intersects(bricks[i].getRectangle())) {
				int ballLeft = (int) ball.getRectangle().getMinX();
	            int ballHeight = (int) ball.getRectangle().getHeight();
	            int ballWidth = (int) ball.getRectangle().getWidth();
	            int ballTop = (int) ball.getRectangle().getMinY();

	            Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
	            Point pointLeft = new Point(ballLeft - 1, ballTop);
	            Point pointTop = new Point(ballLeft, ballTop - 1);
	            Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

	            if (!bricks[i].isBroken()) {
	                if (bricks[i].getRectangle().contains(pointRight)) {
	                    ball.setXVelocity(-1);
	                } else if (bricks[i].getRectangle().contains(pointLeft)) {
	                    ball.setXVelocity(1);
	                }

	                if (bricks[i].getRectangle().contains(pointTop)) {
	                    ball.setYVelocity(1);
	                } else if (bricks[i].getRectangle().contains(pointBottom)) {
	                    ball.setYVelocity(-1);
	                }
	                bricks[i].setBroken(true);
	            }
			}
		}
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        ball.paint(g);
        paddle.paint(g);
        paintBricks(g);
        
        // Draw lives left
        // TODO: Draw lives left in the top left hand corner
        //???: should include the lives left which can be found in livesLeft variable.
        //??: The position in g.drawString(???) can be set using Settings.LIVES_POSITION_X
        //and Settings.LIVES_POSITION_Y

        g.drawString(Integer.toString(livesLeft), Settings.LIVES_POSITION_X,Settings.LIVES_POSITION_Y);
        
        // Draw screen message
        if(screenMessage != null) {
        	g.setFont(new Font("Arial", Font.BOLD, 18));
        	int messageWidth = g.getFontMetrics().stringWidth(screenMessage);
        	g.drawString(screenMessage, (Settings.WINDOW_WIDTH / 2) - (messageWidth / 2), Settings.MESSAGE_POSITION);
        }
    }

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO: Set the velocity of the paddle depending on whether the player is pressing left or right
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT) {
			paddle.setXVelocity(-1);
		//Do for the right now
		} else if(key == KeyEvent.VK_RIGHT) {
			paddle.setXVelocity(1);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO: Set the velocity of the paddle after the player has released the keys
		//This needs to reflect the movment and change of paddle velocity accordingly
		//as follows:
		
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_LEFT) {
			paddle.setXVelocity(0);
		//Do for the right now
		} else if(key == KeyEvent.VK_RIGHT) {
			paddle.setXVelocity(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
	}

}
