import boofcv.struct.image.GrayU8;
import boofcv.struct.image.Planar;
import com.harium.keel.core.helper.ColorHelper;
import org.junit.Assert;
import org.junit.Test;

public class BoofRGBImageSourceTest {

    private static final int BLACK = ColorHelper.getRGB(0,0,0);
    private static final int WHITE = ColorHelper.getRGB(255,255,255);

    @Test
    public void testWrapGrayU8() {
        Planar<GrayU8> image = buildRGBCheckerImage();

        BoofRGBImageSource source = new BoofRGBImageSource(image);

        Assert.assertEquals(2, source.getWidth());
        Assert.assertEquals(3, source.getHeight());
        Assert.assertFalse(source.isGrayscale());

        Assert.assertEquals(ColorHelper.getRed(BLACK), source.getR(0,0));
        Assert.assertEquals(ColorHelper.getGreen(BLACK), source.getG(0,0));
        Assert.assertEquals(ColorHelper.getBlue(BLACK), source.getB(0,0));

        Assert.assertEquals(WHITE, source.getRGB(1,0));
    }

    private Planar<GrayU8> buildRGBCheckerImage() {
        Planar<GrayU8> image = new Planar<>(GrayU8.class, 2, 3, 3);

        // Checker
        image.set24u8(0,0, BLACK);
        image.set24u8(1,0, WHITE);
        image.set24u8(0,1, WHITE);
        image.set24u8(0,1, BLACK);
        image.set24u8(0,2, BLACK);
        image.set24u8(1,2, WHITE);
        return image;
    }

}
