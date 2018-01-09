package reseau;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class lanceur {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
				}
				new FenetrePrincipale();
			}
		});
	}
}