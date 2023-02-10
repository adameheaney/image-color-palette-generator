package photoToColorPalette;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class ColorJFrame extends JFrame {

	ColorJPanel CJP = new ColorJPanel();
	public ColorJFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(CJP);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setTitle("MyFrame!");
		Image icon = Toolkit.getDefaultToolkit().getImage("wishes by grant thumbnail.png");
		this.setIconImage(icon);
	}

}
