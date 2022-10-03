import java.util.ArrayList;

class NoRed extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                //Sets the red pixels at index pixels[x][y] equal to 0 while retaining the green and blue values
                pixels[x][y] = makePixel(0, getGreen(pixels[x][y]), getBlue(pixels[x][y]));
            }
        }
        return pixels;
    }
}

class NoGreen extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                //Sets the green pixels at index pixels[x][y] equal to 0 while retaining the red and blue values
                pixels[x][y] = makePixel(getRed(pixels[x][y]), 0, getBlue(pixels[x][y]));
            }
        }
        return pixels;
    }
}

class NoBlue extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                //Sets the blue pixels at index pixels[x][y] equal to 0 while retaining the green and red values
                pixels[x][y] = makePixel(getRed(pixels[x][y]), getGreen(pixels[x][y]), 0);
            }
        }
        return pixels;
    }
}

class RedOnly extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                //Sets the green and blue pixels at index pixels[x][y] equal to 0 while retaining the red value
                pixels[x][y] = makePixel(getRed(pixels[x][y]), 0, 0);
            }
        }
        return pixels;
    }
}

class GreenOnly extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                //Sets the red and blue pixels at index pixels[x][y] equal to 0 while retaining the green value
                pixels[x][y] = makePixel(0, getGreen(pixels[x][y]), 0);
            }
        }
        return pixels;
    }
}

class BlueOnly extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                //Sets the green and red pixels at index pixels[x][y] equal to 0 while retaining the blue value
                pixels[x][y] = makePixel(0, 0, getBlue(pixels[x][y]));
            }
        }
        return pixels;
    }
}

class BlackAndWhite extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                //Averages the red, green, and blue pixel values at index pixels[x][y] and makes a new pixel with that
                //value at the same index
                int avgRGB = (getRed(pixels[x][y]) + getGreen(pixels[x][y]) + getBlue(pixels[x][y])) / 3;
                pixels[x][y] = makePixel(avgRGB, avgRGB, avgRGB);
            }
        }
        return pixels;
    }
}

class VerticalReflect extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        //Creates new 2 dimensional array that will store the vertically flipped image
        int[][]verticalPixels = new int[height][width];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                //Traverses the array placing pixels on the right half of the image on the left.
                verticalPixels[y][x] = pixels[y][width-x-1];
            }
        }

        return verticalPixels;
    }
}

class HorizontalReflect extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        //Creates new 2 dimensional array that will store the horizontally flipped image
        int[][]horizontalPixels = new int[height][width];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                //Traverses the array placing pixels on the top half of the image on the bottom.
                horizontalPixels[y][x] = pixels[height-y-1][x];
            }
        }

        return horizontalPixels;
    }
}

class Grow extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        //This creates a new array called growPixels with double the width and height of pixels array
        int[][]growPixels = new int[pixels[0].length * 2][pixels.length * 2];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                //The next four lines of code sets one pixel value from array pixels to 4 pixel values in a 2 by 2 grid
                //in the array growPixels. For example, the pixel value at index pixels[0][0] would be inputted into
                //growPixels[0][0], growPixels[1][0], growPixels[0][1], and growPixels[1][1]
                growPixels[2 * x][2 * y] = pixels[x][y];
                growPixels[2 * x + 1][2 * y] = pixels[x][y];
                growPixels[2 * x][2 * y + 1] = pixels[x][y];
                growPixels[2 * x + 1][2 * y + 1] = pixels[x][y];
            }
        }
        return growPixels;
    }
}

class Shrink extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        //This if statement is used so that if one of the dimensions (height or width) divided by 2 is equal to 0, then
        //the original pixels array would be returned because otherwise one or both dimensions would equal 0 and no
        //picture would be returned.
        if(width/2 <= 0 || height/2 <= 0)
            return pixels;

        //This creates a new array called shrinkPixels with half the width and height of pixels array
        int[][]shrinkPixels = new int[pixels[0].length / 2][pixels.length / 2];

        for (int x = 0; x < width/2; x++) {
            for (int y = 0; y < height/2; y++) {
                //The next three lines of code average the red, green, and blue pixel values in a 2 by 2 grid by adding
                //them up and dividing by 4. Example: the 2 by 2 grid would consist index [0][0], [0][1], [1][0], and
                //[1][1].
                int redAverage = (getRed(pixels[2*x][2*y]) + getRed(pixels[2*x+1][2*y]) + getRed(pixels[2*x][2*y+1]) + getRed(pixels[2*x+1][2*y+1])) / 4;
                int greenAverage = (getGreen(pixels[2*x][2*y]) + getGreen(pixels[2*x+1][2*y]) + getGreen(pixels[2*x][2*y+1]) + getGreen(pixels[2*x+1][2*y+1])) / 4;
                int blueAverage = (getBlue(pixels[2*x][2*y]) + getBlue(pixels[2*x+1][2*y]) + getBlue(pixels[2*x][2*y+1]) + getBlue(pixels[2*x+1][2*y+1])) / 4;
                shrinkPixels[x][y] = makePixel(redAverage, greenAverage, blueAverage);
            }
        }
        return shrinkPixels;
    }
}

