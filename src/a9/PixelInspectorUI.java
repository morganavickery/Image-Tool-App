package a9;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PixelInspectorUI extends JPanel implements ActionListener {

  private JLabel x_label;
  private JLabel y_label;
  private JLabel pixel_info;
  double         redValue     = -1;
  double         blueValue    = -1;
  double         greenValue   = -1;
  double         red          = -1;
  double         blue         = -1;
  double         green        = -1;
  public boolean copyButtonOn = false;
  PictureView    magnifier;

  public PixelInspectorUI() {
    setLayout(new BorderLayout());

    x_label = new JLabel("X: ");
    y_label = new JLabel("Y: ");
    pixel_info = new JLabel("(r,g,b)");

    JPanel gridPanel = new JPanel();
    gridPanel.setLayout(new GridLayout(3, 1));
    add(gridPanel, BorderLayout.WEST);

    magnifier = new PictureView(new ObservablePictureImpl(400, 400));
    add(magnifier, BorderLayout.EAST);

    gridPanel.add(x_label);
    gridPanel.add(y_label);
    gridPanel.add(pixel_info);
  }

  public PictureView getMagnifier() {
    // TODO Auto-generated method stub
    return magnifier;
  }

  public void setInfo(int x, int y, Pixel p) {
    x_label.setText("X: " + x);
    y_label.setText("Y: " + y);
    red = p.getRed();
    blue = p.getBlue();
    green = p.getGreen();
    pixel_info.setText(String.format("(%3.2f, %3.2f, %3.2f)", red, green, blue));
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (red > -1) {
      redValue = red;
      greenValue = green;
      blueValue = blue;
    } else {
    }
  }

}
