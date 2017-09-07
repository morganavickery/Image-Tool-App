package a9;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class BrushInspectorTool implements Tool {

  private BrushInspectorUI ui;
  private ImageEditorModel model;
  private int              brush_size = 5;
  Pixel                    a;
  Pixel                    b          = null;
  ObservablePicture        preview_frame;

  public BrushInspectorTool(ImageEditorModel model) {
    this.model = model;
    ui = new BrushInspectorUI();
  }

  @Override
  public JPanel getUI() {
    return ui;
  }

  @Override
  public void mouseClicked(MouseEvent e) {}

  @Override
  public void mousePressed(MouseEvent e) {
    if (ui.getCopyBoolean() == true) {
      b = preview_frame.getPixel(0, 0);
      ui.setCopyBoolean(false);
    }
    ui.setInfo(e.getX(), e.getY(), model.getPixel(e.getX(), e.getY()));
    Pixel og = model.current.getPixel(e.getX(), e.getY());
    a = new ColorPixel(og.getRed(), og.getGreen(), og.getBlue());

    preview_frame = ui.color_preview.getPicture();
    preview_frame.suspendObservable();

    for (int x = 0; x < preview_frame.getWidth(); x++) {
      for (int y = 0; y < preview_frame.getHeight(); y++) {
        preview_frame.setPixel(x, y, a);
      }
    }
    preview_frame.resumeObservable();
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
    if (b != null) {
      model.paintAt(e.getX(), e.getY(), b, brush_size);
    }
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    // TODO Auto-generated method stub
  }

  @Override
  public String getName() {
    return "Paint Brush";
  }
}