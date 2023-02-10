# image-color-palette-generator
A static class PhotoToColorPalette that can be used to generate color palettes based on your inputted image. Includes classes to display the color palette on a window.
# Different methods
1. <b>generateRandomPalette(int numColors, BufferedImage image)</b>: generates a completely random palette of size <b>numColors</b> based on colors within the image, with no bias for anything.
2. <b>generatePhotoPalette(int numColors, BufferedImage image)</b>: generates a random palette from the photo of size <b>numColors</b> based on colors within an image similar to the method above, but the palette will only return colors that are "unique" with a bias towards amount of the color in the image.
3. <b>generatePerfectPalette(int numColors, BufferedImage image)</b>: generates the ideal color palette from the image of size <b>numColors</b> based on weights given to each unique color. Weights are decided based on amount, uniqueness from other colors, and favors colors that are more unique in its R G B values, so as to lower the weight given to values with no saturation, which I've found to give better results.
#What I learned
As this is my first real project uploaded to github, I've learned how to set up a repo and upload a project onto github. This project serves as my introduction to github in general.
I've also learned how to properly make a static class and how to work with images in Java.
# Disclaimer!
This project is still in development and there is more to be tweaked, as there are still some edge cases to cover and possible tuning to be done!
