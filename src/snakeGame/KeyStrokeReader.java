package snakeGame;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class KeyStrokeReader {
	
	private int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;

	public KeyStrokeReader(JPanel panel, GameControl controller) {
		panel.getInputMap(IFW).put(KeyStroke.getKeyStroke("W"), "UP");
		panel.getInputMap(IFW).put(KeyStroke.getKeyStroke("UP"), "UP");

		panel.getInputMap(IFW).put(KeyStroke.getKeyStroke("S"), "DOWN");
		panel.getInputMap(IFW).put(KeyStroke.getKeyStroke("DOWN"), "DOWN");
		
		panel.getInputMap(IFW).put(KeyStroke.getKeyStroke("A"), "LEFT");
		panel.getInputMap(IFW).put(KeyStroke.getKeyStroke("LEFT"), "LEFT");
		
		panel.getInputMap(IFW).put(KeyStroke.getKeyStroke("D"), "RIGHT");
		panel.getInputMap(IFW).put(KeyStroke.getKeyStroke("RIGHT"), "RIGHT");
		
		panel.getActionMap().put("UP", new AbstractAction("UP") {

			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) {
				//System.out.println("UP");
				controller.ifDirectionPossible("UP");
			}
		});
		
		panel.getActionMap().put("DOWN", new AbstractAction("DOWN") {
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) {
				//System.out.println("DOWN");
				controller.ifDirectionPossible("DOWN");
			}
		});
		
		panel.getActionMap().put("LEFT", new AbstractAction("LEFT") {
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) {
				//System.out.println("LEFT");
				controller.ifDirectionPossible("LEFT");
			}
		});
		
		panel.getActionMap().put("RIGHT", new AbstractAction("RIGHT") {
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) {
				//System.out.println("RIGHT");
				controller.ifDirectionPossible("RIGHT");
			}
		});
	}
}
