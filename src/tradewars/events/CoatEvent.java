package tradewars.events;

import tradewars.Game;
import tradewars.Message;
import tradewars.Player;

/**
 * Event to handle if the user wants to upgrade their coat and gain more space
 * @author rob
 */
public class CoatEvent extends Event {

  final static int CHANCE = 10;
  final static long COST = 5000;
  final static int SPACE = 50;

  public CoatEvent(Game game) {
    super(game);
    message = new Message(String.format("Would you like to buy a bigger coat for %s?", CURRENCY_FORMATTER.format(COST)));
  }

  @Override
  public boolean inEvent() {
    boolean b = false;

    if (hit(CHANCE) && (player.getCash() >= COST && player.getSpace() == STARTING_SPACE)) {
      b = true;
    }

    return b;
  }

  @Override
  public Message handleEvent(boolean yes) {
    Message m;

    if (yes) {
      player.removeCash(COST);
      player.setSpace(player.getSpace() + SPACE);
      m = new Message(String.format("You gained %d more pockets!", SPACE));
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
