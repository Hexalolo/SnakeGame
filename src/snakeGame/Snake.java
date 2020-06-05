package snakeGame;
import java.awt.*;
import java.util.LinkedList;

public class Snake {
	private LinkedList<Point> snakePoints;
	private int xActual;
	private int yActual;

	public Snake() {
		snakePoints = new LinkedList<Point>();
		Point p1 = new Point(190, 200);
		Point p2 = new Point(195, 200);
		Point p3 = new Point(200, 200);
		snakePoints.addFirst(p1);
		snakePoints.addFirst(p2);
		snakePoints.addFirst(p3);
	}
	
	public void displaceSnakeRight() {
	
		xActual = (int)getHeadPosition().getX();
		yActual = (int)getHeadPosition().getY();
		snakePoints.addFirst(new Point(xActual + 5, yActual));
		snakePoints.removeLast();
	}
	
	public void displaceSnakeUp() {
		
		xActual = (int)getHeadPosition().getX();
		yActual = (int)getHeadPosition().getY();
		snakePoints.addFirst(new Point(xActual, yActual - 5));
		snakePoints.removeLast();	
	}
	
	public void displaceSnakeDown() {
		xActual = (int)getHeadPosition().getX();
		yActual = (int)getHeadPosition().getY();
		snakePoints.addFirst(new Point(xActual, yActual + 5));
		snakePoints.removeLast();
	}
	
	public void displaceSnakeLeft() {
		xActual = (int)getHeadPosition().getX();
		yActual = (int)getHeadPosition().getY();
		snakePoints.addFirst(new Point(xActual - 5, yActual));
		snakePoints.removeLast();
	}
	
	
	public void prolongSnake(Point p) {
		snakePoints.addFirst(p);
	}
	
	public Point[] getSnakePosition(){
		Point[] snakePositionArray = new Point[snakePoints.size()];
		int  i = 0;
		for(Point element : snakePoints) {
			snakePositionArray[i] = element;
			i++;
		}
		return snakePositionArray;
	}
	
	public Point getHeadPosition() {
		return snakePoints.element();
	}
	
	public int getSnakeLength() {
		return snakePoints.size();
	}

}
