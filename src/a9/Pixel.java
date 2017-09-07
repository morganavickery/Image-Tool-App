package a9;

public interface Pixel {
  double getRed();

  double getGreen();

  double getBlue();

  double getIntensity();

  int toRGB();

  public Pixel blend(Pixel p, double weight);
}
