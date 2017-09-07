package a9;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class PixelInspectorTool implements Tool {

  PixelInspectorUI         ui;
  private ImageEditorModel model;
  Pixel[]                  pixelList = new Pixel[400];

  public PixelInspectorTool(ImageEditorModel model) {
    this.model = model;
    ui = new PixelInspectorUI();
  }

  public PixelInspectorUI getInspectorUI() {
    return ui;
  }

  @Override
  public void mouseClicked(MouseEvent e) {}

  @Override
  public void mousePressed(MouseEvent e) {
    try {
      ui.setInfo(e.getX(), e.getY(), model.getPixel(e.getX(), e.getY()));
    } catch (Exception ex) {
      // Click may have been out of bounds. Do nothing in this case.
    }
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
  public String getName() {
    return "Pixel Inspector";
  }

  @Override
  public JPanel getUI() {
    return ui;
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    ObservablePicture preview_frame = ui.getMagnifier().getPicture();
    preview_frame.suspendObservable();

    int arrayCounter = 0;
    for (int x = -10; x < 10; x++) {
      for (int y = -10; y < 10; y++) {
        Pixel current = model.getPixel(e.getX() + x, e.getY() + y);
        pixelList[arrayCounter] = current;
      }
    }
    arrayCounter = 0;
    for (int x = 0; x < 20; x++) {
      for (int y = 0; y < 20; y++) {
        for (int a = 0; a < 10; a++) {
          for (int b = 0; b < 10; b++) {
            Pixel current = pixelList[arrayCounter];
            preview_frame.setPixel(x * 10 + a, y * 10 + b, current);
          }
        }
        arrayCounter++;
      }
    }

    preview_frame.resumeObservable();

    try {
      ui.setInfo(e.getX(), e.getY(), model.getPixel(e.getX(), e.getY()));
    } catch (Exception ex) {
      // Click may have been out of bounds. Do nothing in this case.
    }
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    // TODO Auto-generated method stub

  }

}
