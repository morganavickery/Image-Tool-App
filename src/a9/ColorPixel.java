package a9;

public class ColorPixel implements Pixel {

  private double red;
  private double green;
  private double blue;

  public ColorPixel(double r, double g, double b) {
    if (r < 0.0 || r > 1.0 || g < 0.0 || g > 1.0 || b < 0.0 || b > 1.0) {
      throw new IllegalArgumentException("One or more color components out of range");
    }

    red = r;
    green = g;
    blue = b;
  }

  @Override
  public double getRed() {
    return red;
  }

  @Override
  public double getGreen() {
    return green;
  }

  @Override
  public double getBlue() {
    return blue;
  }

  @Override
  public double getIntensity() {
    return (0.2126 * getRed() + 0.7152 * getGreen() + 0.0722 * getBlue());
  }

  public Pixel blend(Pixel p, double weight) {
    if (weight < 0.0 || weight > 1.0) {
      throw new RuntimeException("Blend weight out of range");
    }
    if (p == null) {
      throw new RuntimeException("Blend pixel is null");
    }

    return new ColorPixel(getRed() * weight + p.getRed() * (1.0 - weight),
        getGreen() * weight + p.getGreen() * (1.0 - weight),
        getBlue() * weight + p.getBlue() * (1.0 - weight));
  }

  @Override
  public int toRGB() {
    return ((((int) (getRed() * 255.0 + 0.5)) << 16)
        | (((int) (getGreen() * 255.0 + 0.5)) << 8)
        | (((int) (getBlue() * 255.0 + 0.5))));
  }

  public static Pixel fromRGB(int rgb) {
    double red = ((double) ((rgb >> 16) & 0xff)) / 255.0;
    double green = ((double) ((rgb >> 8) & 0xff)) / 255.0;
    double blue = ((double) (rgb & 0xff)) / 255.0;

    return new ColorPixel(red, green, blue);
  }
}
