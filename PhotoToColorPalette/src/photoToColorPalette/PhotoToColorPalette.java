package photoToColorPalette;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.*;


public class PhotoToColorPalette {
	
	/**
	 * Generates a completely random color palette of size <b> numOfColors </b> based
	 * on the image
	 * @param numOfColors - number of colors in the palette
	 * @param image - the image
	 * @return an array of Color objects
	 */
	public static Color[] generateRandomPalette(int numOfColors, BufferedImage image) {
		HashMap<Color, Integer> colorMap = generateCountMap(image);
		List<Color> uniqueColorList = new ArrayList<Color>(colorMap.keySet());
		sortByCount(uniqueColorList, colorMap);
		Color[] colorPalette = new Color[numOfColors];
		for(int i = 0; i < numOfColors; i++) {
			colorPalette[i] = uniqueColorList.get((int)(Math.random() * uniqueColorList.size()));
		}
		return colorPalette;
	}
	public static Color[] generatePerfectPalette(int numOfColors, BufferedImage image) {
		HashMap<Color, Integer> countMap = generateCountMap(image);
		List<Color> uniqueColorList = new ArrayList<Color>(countMap.keySet());
		sortByCount(uniqueColorList, countMap);
		List<Color> uniqueColors = new ArrayList<Color>();
		Color[] perfectPalette = new Color[numOfColors];
		for(int j = 0; j < uniqueColorList.size(); j++) {
			Color col = uniqueColorList.get(j);
			boolean isUnique = true;
			for(Color col2 : uniqueColors) {
				if(col2 != null) {
					if(Math.abs(col.getRed() - col2.getRed()) <= 25 &&
							Math.abs(col.getGreen() - col2.getGreen()) <= 25 &&
							Math.abs(col.getBlue() - col2.getBlue()) <= 25) {
						isUnique = false;
						countMap.replace(col2, countMap.get(col) + countMap.get(col2));
						break;
					}
				}
			}
			if(isUnique) {
				uniqueColors.add(col);
			}
		}
		//return uniqueColors.toArray(new Color[uniqueColors.size()]); /*
		double[] weights = new double[uniqueColors.size()];
		for(int i = 0; i < uniqueColors.size(); i++) {
			weights[i] = countMap.get(uniqueColors.get(i)) * .01;
		}
		for(int i = 0; i < uniqueColors.size(); i++) {
			Color col = uniqueColors.get(i);
			for(int j = 0; j < uniqueColors.size(); j++) {
				
				Color col2 = uniqueColors.get(j);
				weights[i] += .22 * ((Math.abs(col.getRed() * col2.getRed()) * .001 +
								Math.abs(col.getGreen() * col2.getGreen()) * .001 +
								Math.abs(col.getBlue() * col2.getBlue()) * .001) / 3);
			}
			weights[i] += 2.3 * (((Math.abs(col.getRed() - col.getGreen())) +
						  (Math.abs(col.getRed() - col.getBlue())) +
						  (Math.abs(col.getBlue() - col.getGreen()))) / 3); 
		}
		for(int i = 0; i < numOfColors; i++) {
			int greatestIndex = indexOfGreatest(weights);
			perfectPalette[i] = uniqueColors.get(greatestIndex);
			weights[greatestIndex] = 0;
		}
		return perfectPalette; 
	}
	
	private static int indexOfGreatest(double[] arr) {
		double greatest = arr[0];
		int greatestIndex = 0;
		for(int i = 0; i < arr.length; i++) {
			if(greatest < arr[i]) {
				greatest = arr[i];
				greatestIndex = i;
			}
		}
		return greatestIndex;
	}
//	private List<Color> sortByWeights(){
//		
//	}
	
	/**
	 * Generates the optimal representation of an image in a color palette
	 * @param numOfColors
	 * @return
	 */
	public static Color[] generatePhotoPalette(int numOfColors, BufferedImage image) {
		HashMap<Color, Integer> countMap = generateCountMap(image);
		List<Color> uniqueColorList = new ArrayList<Color>(countMap.keySet());
		sortByCount(uniqueColorList, countMap);
		List<Color> photoPalette = new ArrayList<Color>();
		List<Color> uniqueColors = new ArrayList<Color>();		
			for(int j = 0; j < uniqueColorList.size(); j++) {
				Color col = uniqueColorList.get(j);
				boolean isUnique = true;
				for(Color col2 : uniqueColors) {
					if(col2 != null) {
						if(Math.abs(col.getRed() - col2.getRed()) <= 20 &&
								Math.abs(col.getGreen() - col2.getGreen()) <= 20 &&
								Math.abs(col.getBlue() - col2.getBlue()) <= 20) {
							isUnique = false;
							break;
						}
					}
				}
				if(isUnique) {
					uniqueColors.add(col);
				}
			}
		List<Integer> usedNums = new ArrayList<Integer>();
		if(uniqueColors.size() >= numOfColors) {
			for(int i = 0; i < numOfColors; i++) {
				int randNum = (int)(Math.random() * uniqueColors.size());
				while(usedNums.contains(randNum)) {
					randNum = (int)(Math.random() * uniqueColors.size());
				}
				usedNums.add(randNum);
				photoPalette.add(uniqueColors.get(randNum));
			}
			return photoPalette.toArray(new Color[photoPalette.size()]);
		}	
		for(int i = 0; i < numOfColors; i++) {
			if(uniqueColorList.size() <= i)
				return photoPalette.toArray(new Color[photoPalette.size()]);
			int randNum = (int)(Math.random() * uniqueColorList.size());
			while(usedNums.contains(randNum)) {
				randNum = (int)(Math.random() * uniqueColorList.size());
			}
			usedNums.add(randNum);
			photoPalette.add(uniqueColorList.get(randNum));
		}
		return photoPalette.toArray(new Color[photoPalette.size()]);
	}
	
	/**
	 * Generates a Hashset with all unique colors in the image
	 * @return
	 */
	private static HashMap<Color, Integer> generateCountMap(BufferedImage myImage) {
		HashMap<Color, Integer> colorMap = new HashMap<Color, Integer>();
		for(int r = 0; r < myImage.getWidth(); r++) {
			for(int c = 0; c < myImage.getHeight(); c++) {
				Color col = new Color(myImage.getRGB(r, c));
				if(colorMap.containsKey(col)) {
					colorMap.replace(col, colorMap.get(col) + 1);
				}
				else {
					colorMap.put(new Color(myImage.getRGB(r, c)), 1);
//					uniqueColorList.add(col);
				}
			}
		}
//		List<Color> lessUniqueColors = new ArrayList<Color>();
//		for(Color col : uniqueColorList) {
//			if(colorMap.get(col) >= (myImage.getWidth() * myImage.getHeight() / 35000))
//				lessUniqueColors.add(col);
//		}
//		System.out.println(lessUniqueColors.size());
//		uniqueColorList = lessUniqueColors;
		List<Color> uniqueColorList = new ArrayList<Color>(colorMap.keySet());
		for(int i = 0; i < uniqueColorList.size(); i++) {
			if(colorMap.get(uniqueColorList.get(i)) < (myImage.getWidth() * myImage.getHeight() / 35000)) {
				colorMap.remove(uniqueColorList.get(i));
			}
		}
		return colorMap;
	}
	
	private static void sortByCount(List<Color> colorList, HashMap<Color, Integer> colorMap) {
		for(int i = colorList.size()-2; i >= 0; i--) {
			Color col = colorList.get(i);
			int index = i;
			while(index + 1 < (colorList.size()) && colorMap.get(col) < colorMap.get(colorList.get(index+1))) {
				Color tempCol = colorList.get(index+1);
				colorList.set(index + 1, col);
				colorList.set(index, tempCol);
				index++;
			}
		}
	}
}


