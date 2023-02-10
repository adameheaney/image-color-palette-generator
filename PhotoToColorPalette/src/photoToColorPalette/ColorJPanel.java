package photoToColorPalette;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ColorJPanel extends JPanel {
	
	static final long serialVersionUID = 294293214L;
	public ColorJPanel(){
		this.setPreferredSize(new Dimension(500,500));
	}
	
	public void paint(Graphics g) {
		//turns the g to a g2d
		Graphics2D g2D = (Graphics2D) g;
		
		//size of the window
		Dimension windowSize = this.getSize();
		
		//length of a square side
		int squareSize = 50; //(int)Math.ceil(windowSize.getWidth()/windowSize.getHeight());
		
		//indexes for square placement
		int xIndex = 0;
		int yIndex = 0;

		//generate color palette
		Color[] colorPalette;
		
		//width for calculating amount of squares needed (based on the window size)
		int widthCalc = (int)(windowSize.getWidth() + (squareSize));
		int heightCalc = (int)(windowSize.getHeight() + squareSize);
		//amount of squares needed
		int numSquares = 400;
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("amongus.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//generates a color palette of the amount of squares needed
		colorPalette = PhotoToColorPalette.generatePerfectPalette(numSquares, image);
		//System.out.println("Window dimensions (W by H): ("+widthCalc + ", " +
		//windowSize.getHeight() + "). Number of Squares: " + numSquares);
		
		//for loop going through each color in the palette for a square
		for(Color c : colorPalette) {
			g2D.setColor(c);
			g2D.fillRect(xIndex, yIndex, squareSize, squareSize);		
			xIndex += squareSize;
			if(xIndex >= windowSize.getWidth()) {
				xIndex = 0; //-(int)size.getHeight()/3;
				yIndex += squareSize;
			}
			
		}
	}

}
