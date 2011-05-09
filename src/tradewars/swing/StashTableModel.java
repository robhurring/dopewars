/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tradewars.swing;

import java.util.ArrayList;
import java.util.Map;
import javax.swing.table.AbstractTableModel;
import tradewars.Game;
import tradewars.Product;

/**
 *
 * @author rob
 */
public class StashTableModel extends AbstractTableModel {

  private String[] columns = {"Name", "Price", "Quantity"};
  private ArrayList<Object[]> data;
  private Game game;

  public StashTableModel() {
    game = Game.getInstance();
    reload();
  }

  public int getRowCount() {
    return data.size();
  }

  public int getColumnCount() {
    return columns.length;
  }

  @Override
  public String getColumnName(int c) {
    return columns[c];
  }

  public Object getValueAt(int i, int j) {
    return data.get(i)[j];
  }

  @Override
  public Class getColumnClass(int c) {
    return getValueAt(0, c).getClass();
  }

  public final void reload() {
    data = new ArrayList<Object[]>();

    Product p;
    String price;
    int i = 0;

    Map<Product, Map<Long, Integer>> productList = game.getPlayer().getProducts();
    for (Map.Entry<Product, Map<Long, Integer>> entry : productList.entrySet()) {
      p = entry.getKey();
      for (Map.Entry<Long, Integer> priceSchedule : entry.getValue().entrySet()) {
        price = Game.CURRENCY_FORMATTER.format(priceSchedule.getKey());
        data.add(new Object[]{p, price, priceSchedule.getValue()});
        i++;
      }
    }

    fireTableDataChanged();
  }
}
