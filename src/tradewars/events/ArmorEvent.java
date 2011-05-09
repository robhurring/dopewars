/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tradewars.events;

import tradewars.Game;
import tradewars.Message;
import tradewars.Player;
import tradewars.TraderConstants;

/**
 *
 * @author rob
 */
public class ArmorEvent extends Event {

  final static long COST = 25000;
  final static int CHANCE = 12;
  final static int DEFENSE = 10;

  public ArmorEvent(Game game) {
    super(game);
    message = new Message(String.format("Would you like to buy body armor for %s?", CURRENCY_FORMATTER.format(COST)));
  }
  
  @Override
  public boolean inEvent() {
    boolean b = false;

    if(hit(CHANCE) && (player.getCash() >= COST && player.getDefense() == STARTING_DEFENSE)){
      b = true;
    }

    return b;
  }

  @Override
  public Message handleEvent(boolean yes) {
    Message m;

    if (yes) {
      player.removeCash(COST);
      player.addDefense(DEFENSE);
      m = new Message(String.format("You gained %d more defense!", DEFENSE));
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
