package tradewars.events;

import tradewars.Game;
import tradewars.Message;

/**
 * Event to aks the user if they want to upgrade their weapon
 * @author rob
 */
public class GunEvent extends Event {

  final static long COST = 30000;
  final static int CHANCE = 10;
  final static int STRENGTH = 15;
  
  public GunEvent(Game game) {
    super(game);
    message = new Message(String.format("Would you like to buy a bigger gun for %s?", CURRENCY_FORMATTER.format(COST)));
  }

  @Override
  public boolean inEvent() {
    boolean b = false;
    
    if(hit(CHANCE) && (player.getCash() >= COST && player.getStrength() == STARTING_STRENGTH)){
      b = true;
    }

    return b;
  }

  @Override
  public Message handleEvent(boolean yes) {
    Message m;
    
    if (yes) {
      player.removeCash(COST);
      player.addStrength(STRENGTH);
      m = new Message(String.format("You gained %d more strength!", STRENGTH));
    } else {
      m = new Message("Ok. Your loss, buddy.");
    }

    return m;
  }

  @Override
  public boolean requiresInput() {
    return true;
  }
}
