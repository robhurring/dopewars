/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tradewars.swing;

import javax.swing.table.AbstractTableModel;
import tradewars.Game;
import tradewars.Product;

/**
 *
 * @author rob
 */
public class ProductsTableModel extends AbstractTableModel {

  private String[] columns = {"Name", "Price"};
  private Object[][] data;
  private Game game;

  public ProductsTableModel() {
    game = Game.getInstance();
    reload();
  }

  public int getRowCount() {
    return data.length;
  }

  public int getColumnCount() {
    return columns.length;
  }

  @Override
  public String getColumnName(int c) {
    return columns[c];
  }

  public Object getValueAt(int i, int j) {
    return data[i][j];
  }

  @Override
  public Class getColumnClass(int c) {
    return getValueAt(0, c).getClass();
  }
  
  public final void reload() {
    Product[] products = game.getLocation().getProducts();
    data = new Object[products.length][2];

    int i = 0;
    for (Product p : products) {
      data[i] = new Object[]{p, p.getPriceValue()};
      i++;
    }
  }
}
