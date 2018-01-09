package map;

import java.awt.Dimension;

import javax.swing.JPanel;

public class OverlayPanel extends JPanel {

	public OverlayPanel() {
		setOpaque(false);
		setPreferredSize(new Dimension(370, 12 * 16 + 12));
	}
}