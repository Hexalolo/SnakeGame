package snakeGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private boolean ifBoardVisible;
	private Snake snake;
	private Seed seed;
	private JTextField nickField;
	private JLabel endMessage;
	private JButton saveButton;
	private Record savingRecord;
	private int score;
	private boolean ifShowingRecords;
	
	public ImagePanel(boolean ifBoardVisible) {
		
		this.ifBoardVisible = ifBoardVisible;
		ifShowingRecords = false;
		loadingImage();
	}
	
	public void passSnake(Snake snake) {
		this.snake = snake;
		ifBoardVisible = true;
	}
	
	public void passSeed(Seed seed) {
		this.seed = seed;
	}
	

	public void loadingImage() {
		File imageFile = new File("snake.jpg");
 		try {
 			image = ImageIO.read(imageFile);
 		} catch (IOException e) {
 			System.err.println("Blad odczytu obrazka");
 			e.printStackTrace();
 		}
 
 		Dimension dimension = new Dimension(image.getWidth(), image.getHeight());
 		setPreferredSize(dimension);
 	}
	
	public void loadingResults(String[] listOfRecords) {
		ifShowingRecords = true;
	}
	
	public void showGameOver(int score) {
		this.score = score;
		
		endMessage = new JLabel("Your nick: ");
		add(endMessage);
		
		nickField = new JTextField();
		nickField.setPreferredSize(new Dimension(150, 20));
		add(nickField);
		
		saveButton = new JButton("Save");
		saveButton.addActionListener(new saveListener());
		add(saveButton);
	}
	
	public class saveListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			savingRecord = new Record();
			savingRecord.saveRecord(score, nickField.getText());
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		if(!ifBoardVisible && !ifShowingRecords) {
			g2d.drawImage(image, 0, 0, this);
		} else if(!ifBoardVisible && ifShowingRecords) {
			
		} else {

			g2d.setColor(Color.WHITE);
			g2d.fillRect(40, 40, 320, 320);
			g2d.setColor(Color.BLACK);
			g2d.drawRect(50, 50, 300, 300);
			g2d.setColor(Color.BLUE);
			Point[] snakePositionArray = new Point[snake.getSnakeLength()];
			snakePositionArray = snake.getSnakePosition();
			for(int i = 0; i < snake.getSnakeLength(); i++) {
				g2d.fillRect((int)snakePositionArray[i].getX(), (int)snakePositionArray[i].getY(), 5, 5);
			}
			g2d.setColor(Color.RED);
			g2d.fillRect((int)seed.getSeedPosition().getX(), (int)seed.getSeedPosition().getY(), 5, 5);
		}
	}
}
