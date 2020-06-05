package snakeGame;

import javax.swing.JLabel;

public class ScoreCounter extends JLabel {

	private static final long serialVersionUID = 8310409079704620182L;
	private int score;
	private JLabel scoreLabel;
	
	public ScoreCounter() {
		score = 0;
		scoreLabel = new JLabel(Integer.toString(score));
	}

	public void setScore(int newScore) {
		score = newScore;
		scoreLabel.setText(Integer.toString(score));
	}
	
	public JLabel getLabel() {
		return scoreLabel;
	}
}
