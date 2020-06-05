package snakeGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board  {
	public boolean ifBoardVisible;
	public JFrame frame;
	private JPanel buttonPanel;
	private ImagePanel imagePanel;
	private ScoreCounter scoreLabel;
	private JButton newGame;
	private JButton highScores;
	private JButton exit;
	private GameControl controller;
	private JPanel contentPane;
	private JButton pause;
	private KeyStrokeReader reader;
	private String[] listOfRecords;
	private Record bestResults;
	private JTextArea recordTextArea;
	private boolean ifShowingRecords;

	
	public Board() {
		
		frame = new JFrame("Snake Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension (450,480));
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(contentPane);
		ifBoardVisible = false;
		ifShowingRecords = false;
		
		buttonPanel = new JPanel();
		newGame = new JButton("New Game");
		newGame.addActionListener(new newGameListener());
		highScores = new JButton("High Scores");
		highScores.addActionListener(new highScoresListener());
		exit = new JButton("Exit game");
		exit.addActionListener(new exitListener());
		buttonPanel.add(newGame);
		buttonPanel.add(highScores);
		buttonPanel.add(exit);
		
		contentPane.add(buttonPanel, BorderLayout.NORTH);
		scoreLabel = new ScoreCounter();
		
		imagePanel = new ImagePanel(ifBoardVisible);
		contentPane.add(imagePanel, BorderLayout.CENTER);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public class newGameListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			controller = new GameControl(frame, imagePanel, scoreLabel);
			reader = new KeyStrokeReader(imagePanel, controller);
			buttonPanel.remove(newGame);
			buttonPanel.remove(highScores);
			buttonPanel.remove(exit);
			
			if(ifShowingRecords) {
				imagePanel.remove(recordTextArea);
				ifShowingRecords = false;
			}
			
			pause = new JButton("Pause game");
			pause.addActionListener(new pauseListener());
			buttonPanel.add(pause);
			buttonPanel.add(scoreLabel.getLabel());
			buttonPanel.add(exit);
			
		}
	}

	public class highScoresListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			ifShowingRecords = true;
			bestResults = new Record();
			listOfRecords = bestResults.readRecord();
			imagePanel.loadingResults(listOfRecords);
			String text = "";			
			for(int i = 0; i < 25; i++) {
				text = text.concat(listOfRecords[i]).concat(System.lineSeparator());
			}
			recordTextArea = new JTextArea(text);
			imagePanel.add(recordTextArea);
			frame.revalidate();
			frame.repaint();
		}
	}
	
	public class exitListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			System.exit(0);
		}
	}
	
	public class pauseListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			controller.pauseGame();
		}
	}
}
