import boofcv.struct.image.GrayU8;
import boofcv.struct.image.Planar;
import com.harium.keel.core.helper.ColorHelper;
import com.harium.keel.core.source.ImageSource;

public class BoofRGBImageSource implements ImageSource {

    public static final int CHANNEL_RED = 0;
    public static final int CHANNEL_GREEN = 1;
    public static final int CHANNEL_BLUE = 2;

    private Planar<GrayU8> source;

    public BoofRGBImageSource(Planar<GrayU8> image) {
        this.source = image;
        if (image.getNumBands() < 3) {
            throw new RuntimeException("Image should have at least three bands");
        }
    }

    public int getWidth() {
        return source.width;
    }

    public int getHeight() {
        return source.height;
    }

    public int getA(int x, int y) {
        // Always Full Alpha
        return 0xff;
    }

    public int getR(int x, int y) {
        return source.getBand(CHANNEL_RED).unsafe_get(x, y);
    }

    public int getG(int x, int y) {
        return source.getBand(CHANNEL_GREEN).unsafe_get(x, y);
    }

    public int getB(int x, int y) {
        return source.getBand(CHANNEL_BLUE).unsafe_get(x, y);
    }

    public int getRGB(int x, int y) {
        int r = getR(x, y);
        int g = getG(x, y);
        int b = getB(x, y);
        return ColorHelper.getRGB(r, g, b);
    }

    public void setRGB(int x, int y, int rgb) {
        int r = ColorHelper.getRed(rgb);
        int g = ColorHelper.getGreen(rgb);
        int b = ColorHelper.getBlue(rgb);

        source.getBand(CHANNEL_RED).unsafe_set(x, y, r);
        source.getBand(CHANNEL_GREEN).unsafe_set(x, y, g);
        source.getBand(CHANNEL_BLUE).unsafe_set(x, y, b);
    }

    public boolean isGrayscale() {
        return source.getNumBands() == 1;
    }
}
