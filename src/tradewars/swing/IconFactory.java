/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tradewars.swing;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import tradewars.Product;

/**
 *
 * @author rob
 */
public class IconFactory {

  static public ImageIcon getSpaceFullIcon() {
    return iconAtResourcePath("/tradewars/resources/space_full.png");
  }

  static public ImageIcon getSpaceEmptyIcon() {
    return iconAtResourcePath("/tradewars/resources/space_empty.png");
  }

  static public ImageIcon getDialogIcon(int messageType) {
    String resourceName;
    ImageIcon i = null;

    switch (messageType) {
      case JOptionPane.ERROR_MESSAGE:
        resourceName = "error.png";
        break;
      case JOptionPane.INFORMATION_MESSAGE:
      default:
        resourceName = "information.png";
    }

    if(resourceName != null){
      i = iconAtResourcePath(String.format("/tradewars/resources/%s", resourceName));
    }
    
    return i;
  }

  static public ImageIcon getProductIcon(Product p) {
    return iconAtResourcePath(String.format("/tradewars/resources/products/%s.png", p.getName().toLowerCase()));
  }

  static private ImageIcon iconAtResourcePath(String path) {
    ImageIcon i = null;
    URL u = "".getClass().getResource(path);

    if (u != null) {
      i = new ImageIcon(u);
    }

    return i;
  }
}
