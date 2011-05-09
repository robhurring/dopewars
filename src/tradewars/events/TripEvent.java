/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tradewars.events;

import tradewars.Game;
import tradewars.Message;

/**
 *
 * @author rob
 */
public class TripEvent extends Event {
  final static int CHANCE = 10;
  final static int PERCENT = 10; // may lose upto 10% of their cash
  
  public TripEvent(Game game){
    super(game);
  }
  
  @Override
  public boolean inEvent() {
    return hit(CHANCE);
  }

  @Override
  public Message handleEvent(boolean yes) {
    int rate = (int) (Math.random() * PERCENT) + 1;
    double pct = rate / 100.0;
    long lose = (long) (player.getCash() * pct);
   
    player.removeCash(lose);
    Message m = new Message(EVENT_COLOR, String.format("You tripped and lost %s!", CURRENCY_FORMATTER.format(lose)));
    
    return m;
  }

  @Override
  public boolean requiresInput() {
    return false;
  }
}
