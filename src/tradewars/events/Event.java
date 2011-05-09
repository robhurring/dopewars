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
public abstract class Event implements TraderConstants {
  protected Game game;
  protected Player player;
  protected Message message;
          
  public Event(Game game){
    this.game = game;
    player = game.getPlayer();
  }
  
  public Message getMessage(){
    return message;
  }

  public boolean hit(int sides){
    int r = (int)(Math.random() * sides) + 1;
    return (r == 1);
  }
  
  public abstract boolean inEvent();
  abstract public Message handleEvent(boolean yes);
  abstract public boolean requiresInput();
}
