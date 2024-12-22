import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		Color[][] tinypic = read("tinypic.ppm");
		print(tinypic);
		

		// Creates an image which will be the result of various 
		// image processing operations:
		Color[][] image;

		// Tests the horizontal flipping of an image:
		image = scaled(tinypic, 3, 5);
		System.out.println();
		print(image);
		
		

		//// Write here whatever code you need in order to test your work.
		//// You can continue using the image array.
	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	public static Color[][] read(String fileName) {
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
		Color[][] image = new Color[numRows][numCols];
		for (int i = 0 ; i < numRows; i++){
			for ( int j = 0; j < numCols; j++){
				int r = in.readInt();
				int g = in.readInt();
				int b = in.readInt();
				image[i][j] = new Color(r , g , b);

			}
		}
		// Reads the RGB values from the file into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		//// Replace the following statement with your code.
		return image;
	}

    // Prints the RGB values of a given color.
	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	private static void print(Color[][] image) {
		for (int i = 0; i< image.length; i++){
			for (int j = 0; j < image[i].length; j++){
				print(image[i][j]);
			}
			System.out.println();
		}
		//// Notice that all you have to so is print every element (i,j) of the array using the print(Color) function.
	}
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		Color[][] imageflippedHorizontall = new Color[image.length][image[0].length];
		for (int j = 0; j < imageflippedHorizontall[0].length; j++ ){
			for( int i = 0 ; i <imageflippedHorizontall.length; i++){
				imageflippedHorizontall[i][imageflippedHorizontall[0].length-1-j]=image[i][j];
			}
		}
		return imageflippedHorizontall;
	}
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		Color[][] imageflippedVertically = new Color[image.length][image[0].length];
		for ( int i = 0 ; i <imageflippedVertically.length; i++){
			for(int j = 0; j < imageflippedVertically[0].length; j++){
				imageflippedVertically[imageflippedVertically.length-1-i][j]=image[i][j];
			}
		}
		return imageflippedVertically;
	}
	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	private static Color luminance(Color pixel) {

		int r = pixel.getRed();
		int g = pixel.getGreen();
		int b = pixel.getBlue();

		int lum = (int) (0.299 * r + 0.587 * g + 0.114 * b);
		int roundedLum = (int) (lum);
		return   new Color(roundedLum, roundedLum, roundedLum);
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		Color[][] grayScaled = new Color[image.length][image[0].length];
		for(int i = 0; i<image.length; i++ ){
			for(int j = 0; j < image[0].length; j++){
				 grayScaled[i][j] = luminance(image[i][j]);


			}
		}
		//// Replace the following statement with your code
		return grayScaled;
	}	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
        int h = image.length;
		int w = image[0].length;

		Color[][] scaledImg =new Color[height][width]; 	

		double newH = (double) (h/height);
		double newW = (double) (w/width);

			

		for (int i = 0 ; i< height; i++){
			for(int j = 0 ; j< width;j++){
				int heightScale = (int) (i*newH);
				int widthScale = (int) (j* newW);

				heightScale = Math.min(heightScale,h-1);
				widthScale = Math.min(widthScale,w-1);



				 scaledImg[i][j] = image[heightScale][widthScale];
            
			}
		}

		return scaledImg;
	}
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		//// Replace the following statement with your code 
		int r1 = c1.getRed();
        int g1 = c1.getGreen();
        int b1 = c1.getBlue();
    
        int r2 = c2.getRed();
        int g2 = c2.getGreen();
        int b2 = c2.getBlue();

		int r =(int) (alpha * r1 + (1 - alpha) * r2);
		int g =(int) (alpha * g1 + (1 - alpha) * g2);
		int b =(int) (alpha * b1 + (1 - alpha) * b2);

		r = Math.min(255, Math.max(0, r));
		g = Math.min(255, Math.max(0, g));
		b = Math.min(255, Math.max(0, b));
		
		return new Color(r, g, b);
	}
	
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		Color[][] blendImg = new Color[image1.length][image1[0].length];
		for (int i = 0; i < image1.length; i++) {
			for (int j = 0; j < image1[0].length; j++) {
				Color color1 = image1[i][j];
                Color color2 = image2[i][j];

				blendImg[i][j] = blend(color1, color2, alpha); 
			}
		}
		return blendImg;
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		Color[][] target1 = scaled(target, source[0].length, source.length);
		double stepSize = 1.0 / n;
		for (int i =0 ; i<n;i++){
			double alpha = (double)(stepSize*i);
			Color[][] morphed = blend(source, target1, alpha);
			display(morphed);
			StdDraw.pause(500);
		}
	}
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}

