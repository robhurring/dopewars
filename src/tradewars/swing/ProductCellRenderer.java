/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tradewars.swing;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import tradewars.Product;

/**
 *
 * @author rob
 */
public class ProductCellRenderer extends DefaultTableCellRenderer {

  @Override
  public Component getTableCellRendererComponent(JTable table, Object o, boolean isSelected, boolean hasFocus, int row, int column) {
    ImageIcon i = IconFactory.getProductIcon((Product) o);

    super.setIcon(i);
    super.setIconTextGap(5);
    super.setText(o.toString());

    if (isSelected) {
      super.setForeground(table.getSelectionForeground());
      super.setBackground(table.getSelectionBackground());
    } else {
      super.setBackground(table.getBackground());
      super.setForeground(table.getForeground());
    }

    return this;
  }
}
