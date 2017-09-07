package a9;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BrushInspectorUI extends JPanel implements ActionListener, ChangeListener {

  PictureView    color_preview;
  int            brushSize    = 5;
  Pixel          p;
  double         redValue;
  double         blueValue;
  double         greenValue;
  JLabel         x_label;
  private JLabel y_label;
  private JLabel pixel_info;
  public boolean copyButtonOn = false;

  public BrushInspectorUI() {
    setLayout(new BorderLayout());

    // inspector info
    JPanel gridPanel = new JPanel();
    gridPanel.setLayout(new GridLayout(3, 1));
    add(gridPanel, BorderLayout.WEST);

    x_label = new JLabel("X: ");
    y_label = new JLabel("Y: ");
    pixel_info = new JLabel("(r,g,b)");

    gridPanel.add(x_label);
    gridPanel.add(y_label);
    gridPanel.add(pixel_info);

    // copy button
    JButton copyButton = new JButton("Copy Color for Brush");
    copyButton.addActionListener(this);
    add(copyButton, BorderLayout.CENTER);

    color_preview = new PictureView(new ObservablePictureImpl(50, 50));
    add(color_preview, BorderLayout.EAST);

    stateChanged(null);
  }

  public boolean getCopyBoolean() {
    // TODO Auto-generated method stub
    return copyButtonOn;
  }

  public void setCopyBoolean(boolean tf) {
    copyButtonOn = tf;
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    p = new ColorPixel(redValue, greenValue, blueValue);

    ObservablePicture preview_frame = color_preview.getPicture();
    preview_frame.suspendObservable();
    for (int x = 0; x < preview_frame.getWidth(); x++) {
      for (int y = 0; y < preview_frame.getHeight(); y++) {
        preview_frame.setPixel(x, y, p);
      }
    }
    preview_frame.resumeObservable();
  }

  public Pixel getBrushColor() {
    return color_preview.getPicture().getPixel(0, 0);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    copyButtonOn = true;

  }

  public void setInfo(int x, int y, Pixel p) {
    x_label.setText("X: " + x);
    y_label.setText("Y: " + y);
    double red = p.getRed();
    double blue = p.getBlue();
    double green = p.getGreen();
    pixel_info.setText(String.format("(%3.2f, %3.2f, %3.2f)", red, green, blue));
  }

}
