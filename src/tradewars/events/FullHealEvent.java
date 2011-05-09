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
public class FullHealEvent extends Event {

  final static int CHANCE = 5;
  final static int COST_PER = 1500;
  final static long MIN_COST = 30000; // don't offer until they're at 80% or lower

  public FullHealEvent(Game game) {
    super(game);
  }

  @Override
  public Message getMessage() {
    System.out.println(healAmount());
    return new Message(String.format("Would you like to heal yourself for %s?", CURRENCY_FORMATTER.format(healAmount())));
  }

  @Override
  public boolean inEvent() {
    boolean b = false;

    if (hit(CHANCE) && (healAmount() > MIN_COST) && (player.getCash() >= healAmount())) {
      b = true;
    }

    return b;
  }

  @Override
  public Message handleEvent(boolean yes) {
    Message m;

    if (yes) {
      long cost = healAmount();
      int n = player.getMaxHealth() - player.getHealth();

      player.removeCash(cost);
      player.heal(n);

      m = new Message(String.format("You've been healed for %d hitpoints!", n));
    } else {
      m = new Message("Fine, bleed out then.");
    }

    return m;
  }

  @Override
  public boolean requiresInput() {
    return true;
  }

  private long healAmount() {
    int n = player.getMaxHealth() - player.getHealth();
    return (long)(n * COST_PER);
  }
}
