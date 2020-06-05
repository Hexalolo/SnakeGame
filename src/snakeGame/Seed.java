package snakeGame;

import java.awt.Point;

public class Seed {
	private Point seedPosition;
	
	public Seed() {
		drawNewSeed();
	}
	
	public void drawNewSeed() {
		int randomValue;
		int x = 120;
		int y = 120;
		
		randomValue = (int) Math.round(Math.random()*60);
		y = (int)(randomValue*5 + 50);
		do {
			randomValue = (int) Math.round(Math.random()*60);
			x = (int)(randomValue*5 + 50);		
		} while(y == 200 && (x != 190 || x != 195 || x != 200 || x != 205 || x != 210));
		
		seedPosition = new Point(x, y);
	}
	
	public Point getSeedPosition() {
		return seedPosition;
	}
}
