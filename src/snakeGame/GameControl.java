package snakeGame;

import java.awt.Point;

import javax.swing.JFrame;

public class GameControl {
	private Snake snake;
	private int score;
	private JFrame frame;
	private ImagePanel imagePanel;
	private Thread thread;
	private boolean ifPaused;
	private String actualDirection;
	private boolean ifCrashed;
	private Seed seed;
	private ScoreCounter scoreLabel;
	private int speed;
	
	public GameControl(JFrame frame, ImagePanel imagePanel, ScoreCounter scoreLabel) {
		this.frame = frame;
		this.imagePanel = imagePanel;
		this.scoreLabel = scoreLabel;
		createSnake();
		actualDirection = "RIGHT";
		ifPaused = false;
		ifCrashed = false;
		seed = new Seed();
		score = 0;
		speed = 1;
		imagePanel.passSnake(snake);
		imagePanel.passSeed(seed);
		frame.repaint();
		thread = new Thread(new Task());
		thread.start();
	}
	
	public void createSnake() {
		snake = new Snake();
	}
	
	public void ifDirectionPossible(String askedDirection) {
		
		if(actualDirection == "UP" && ifPaused == false) {
			if(askedDirection == "UP" || askedDirection == "LEFT" || askedDirection == "RIGHT") {
				actualDirection = askedDirection;
			}
		}
		
		if(actualDirection == "LEFT" && ifPaused == false) {
			if(askedDirection == "UP" || askedDirection == "LEFT" || askedDirection == "DOWN") {
				actualDirection = askedDirection;
			}
		}
		
		if(actualDirection == "DOWN" && ifPaused == false) {
			if(askedDirection == "DOWN" || askedDirection == "LEFT" || askedDirection == "RIGHT") {
				actualDirection = askedDirection;
			}
		}
		
		if(actualDirection == "RIGHT" && ifPaused == false) {
			if(askedDirection == "UP" || askedDirection == "DOWN" || askedDirection == "RIGHT") {
				actualDirection = askedDirection;
			}
		}
	}
	
	public Point snakeMoveRight() {
		int xActual = (int)snake.getHeadPosition().getX();
		int yActual = (int)snake.getHeadPosition().getY();
		Point newHeadPosition = new Point(xActual + 5, yActual);
		return newHeadPosition;
	}
	
	public Point snakeMoveLeft() {
		int xActual = (int)snake.getHeadPosition().getX();
		int yActual = (int)snake.getHeadPosition().getY();
		Point newHeadPosition = new Point(xActual - 5, yActual);
		return newHeadPosition;
	}
	
	public Point snakeMoveUp() {
		int xActual = (int)snake.getHeadPosition().getX();
		int yActual = (int)snake.getHeadPosition().getY();
		Point newHeadPosition = new Point(xActual, yActual - 5);
		return newHeadPosition;
	}
	
	public Point snakeMoveDown() {
		int xActual = (int)snake.getHeadPosition().getX();
		int yActual = (int)snake.getHeadPosition().getY();
		Point newHeadPosition = new Point(xActual, yActual + 5);
		return newHeadPosition;
	}
	
	public void ifSeedEaten(Point newHeadPosition) {
		if(newHeadPosition.getX() == seed.getSeedPosition().getX() && newHeadPosition.getY() == seed.getSeedPosition().getY()) {
			seed.drawNewSeed();
			snake.prolongSnake(newHeadPosition);
			score = score + 1;
			speed = speed + 1;
		}
	}
	
	
	public void pauseGame() {
		ifPaused = !ifPaused;	
		if(ifPaused == false) {
			thread = new Thread(new Task());
			thread.start();
		}
	}
	
	public void finishGame(Point newHeadPosition) {
		
		Point[] occupiedPoints = new Point[snake.getSnakeLength()];
		occupiedPoints = snake.getSnakePosition();
		for(int i = 0; i < occupiedPoints.length; i++) {
			if(occupiedPoints[i].getX() == newHeadPosition.getX() && occupiedPoints[i].getY() == newHeadPosition.getY() && ifCrashed == false) {
				ifCrashed = true;
			}
		}
		
		if(ifCrashed == false) {
			if((int)newHeadPosition.getX() < 45 || (int)newHeadPosition.getX() > 350) {
				ifCrashed = true;
			}
		}
		if(ifCrashed == false) {
			if((int)newHeadPosition.getY() < 45 || (int)newHeadPosition.getY() > 350) {
				ifCrashed = true;
			}
		}
		
		if(ifCrashed == true) {
			imagePanel.showGameOver(score);
		}
	}
	
	private class Task implements Runnable{
		
		@Override
		public void run() {
		
			while (ifPaused == false && ifCrashed == false) {
			
				try {
					Thread.sleep(400 - speed*10);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if(actualDirection == "UP") {
					snake.displaceSnakeUp();
					ifSeedEaten(snakeMoveUp());
					finishGame(snakeMoveUp());
				}
				
				if(actualDirection == "DOWN") {
					snake.displaceSnakeDown();
					ifSeedEaten(snakeMoveDown());
					finishGame(snakeMoveDown());
				}
				
				if(actualDirection == "LEFT") {
					snake.displaceSnakeLeft();
					ifSeedEaten(snakeMoveLeft());
					finishGame(snakeMoveLeft());
				}
				
				if(actualDirection == "RIGHT") {
					snake.displaceSnakeRight();
					ifSeedEaten(snakeMoveRight());
					finishGame(snakeMoveRight());
				}
				
				imagePanel.passSnake(snake);
				imagePanel.passSeed(seed);
				scoreLabel.setScore(score);
				
				frame.revalidate();
				frame.repaint();
				
			}
		}
	}

}
