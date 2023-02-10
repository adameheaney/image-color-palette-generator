package photoToColorPalette;
import java.awt.*;

public class Testing {

	public static void main(String[] args) {
		Color c = new Color(255,0,127);
		float[] HSB = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
		System.out.println(HSB[0]);
		System.out.println(HSB[1]);
		System.out.println(HSB[2]);


	}

}
