package tradewars.swing;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import tradewars.Game;
import tradewars.Location;

/**
 * Manages the locations table list
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
