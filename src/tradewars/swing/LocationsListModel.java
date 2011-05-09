/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tradewars.swing;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import tradewars.Game;
import tradewars.Location;

/**
 *
 * @author rob
 */
public class LocationsListModel extends AbstractListModel implements ComboBoxModel {

  private Game game;
  private Location[] locations;
  private Location selected;

  public LocationsListModel() {
    game = Game.getInstance();
    locations = game.getLocations();
    selected = locations[0];
  }

  public int getSize() {
    return locations.length;
  }

  public Object getElementAt(int i) {
    return locations[i];
  }

  public void setSelectedItem(Object o) {
    selected = (Location) o;
  }

  public Object getSelectedItem() {
    return selected;
  }
}
