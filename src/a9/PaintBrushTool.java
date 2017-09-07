package a9;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class PaintBrushTool implements Tool {

  private PaintBrushToolUI ui;
  private ImageEditorModel model;
  private int              brush_size = 5;
  PixelInspectorUI         inspectorUI;

  public PaintBrushTool(ImageEditorModel model) {
    this.model = model;
    ui = new PaintBrushToolUI();
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mousePressed(MouseEvent e) {
    Pixel og = model.current.getPixel(e.getX(), e.getY());
    Pixel color = ui.getBrushColor();

    // double redValue = ((ui.opacityValue * color.getRed()) / 100.0)
    // + ((1 - ui.opacityValue) / 100.00 * og.getRed());
    // double blueValue = ((ui.opacityValue * color.getGreen()) / 100.0)
    // + ((1 - ui.opacityValue) / 100.00 * og.getGreen());
    // double greenValue = ((ui.opacityValue * color.getBlue()) / 100.0)
    // + ((1 - ui.opacityValue) / 100.00 * og.getBlue());

    Pixel newPixel = color.blend(og, ui.opacityValue);
    // Pixel newPixel = new ColorPixel(redValue, greenValue, blueValue);

    model.paintAt(e.getX(), e.getY(), newPixel, ui.brushSize);
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseDragged(MouseEvent e) {
    Pixel og = model.current.getPixel(e.getX(), e.getY());
    // Pixel color = new ColorPixel(0, 0, 0);
    // redValue is -1 if copy button hasnt been pressed
    // if (inspectorUI.redValue > -1) {
    // color = new ColorPixel(inspectorUI.redValue, inspectorUI.greenValue,
    // inspectorUI.blueValue);
    // } else {
    Pixel color = ui.getBrushColor();
    // }
    //
    double redValue = ((ui.opacityValue / 100 * color.getRed()))
        + ((1 - ui.opacityValue / 100) * og.getRed());
    double greenValue = ((ui.opacityValue / 100 * color.getGreen()))
        + ((1 - ui.opacityValue / 100) * og.getGreen());
    double blueValue = ((ui.opacityValue / 100 * color.getBlue()))
        + ((1 - ui.opacityValue / 100) * og.getBlue());

    // Pixel newPixel = color.blend(og, ui.opacityValue);
    Pixel newPixel = new ColorPixel(redValue, greenValue, blueValue);

    model.paintAt(e.getX(), e.getY(), newPixel, ui.brushSize);
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public String getName() {
    return "Paint Brush";
  }

  @Override
  public JPanel getUI() {
    return ui;
  }

}
