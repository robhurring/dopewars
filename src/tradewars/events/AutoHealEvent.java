package tradewars.events;

import tradewars.Game;
import tradewars.Message;

/**
 * Simple event that will heal the user a small amount when they switch places
 * @author rob
 */
public class AutoHealEvent extends Event {
  final static int HEAL = 5;
  
  public AutoHealEvent(Game game) {
    super(game);
  }

  @Override
  public boolean inEvent() {
    return (player.getHealth() < player.getMaxHealth());
  }

  @Override
  public Message handleEvent(boolean yes) {
    int amount = (int)(Math.random() * HEAL) * player.getLevel();
    int max = player.getMaxHealth() - player.getHealth();
    
    if(amount > max){
      amount = max;
    }
    
    player.heal(amount);
    
    return new Message(String.format("You regenerated %d hitpoints.", amount));
  }

  @Override
  public boolean requiresInput() {
    return false;
  }
}
