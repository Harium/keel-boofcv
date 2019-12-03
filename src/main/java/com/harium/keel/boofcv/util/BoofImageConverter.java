package com.harium.keel.boofcv.util;

import boofcv.struct.image.GrayF32;
import com.harium.keel.core.helper.ColorHelper;
import com.harium.keel.core.source.ImageSource;

public class BoofImageConverter {

  public GrayF32 convert(ImageSource source) {
    int w = source.getWidth();
    int h = source.getHeight();
    float[] data = new float[w * h];

    for (int y = 0; y < h; y++) {
      for (int x = 0; x < w; x++) {
        data[y * w + x] = getGray(x, y, source);
      }
    }

    GrayF32 out = new GrayF32();
    out.width = w;
    out.height = h;
    out.data = data;
    return out;
  }

  /**
   * Method to turn rgb to grayscale
   * Maybe it should use grayscale effect?
   * @param x
   * @param y
   * @param source
   * @return
   */
  private int getGray(int x, int y, ImageSource source) {
    int rgb = source.getRGB(x, y);
    int r = ColorHelper.getRed(rgb);
    int g = ColorHelper.getGreen(rgb);
    int b = ColorHelper.getBlue(rgb);

    return (int) (r * 0.2125 + g * 0.7154 + b * 0.0721);
  }
}
