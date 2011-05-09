/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tradewars.events;

import tradewars.Game;
import tradewars.Message;
import tradewars.Product;

/**
 *
 * @author rob
 */
public class LuckyEvent extends Event {

  final static int CHANCE = 7;
  final static int MAX_QUANTITY = 15;

  public LuckyEvent(Game game){
    super(game);
  }
  
  @Override
  public boolean inEvent() {
    return hit(CHANCE);
  }

  @Override
  public Message handleEvent(boolean yes) {
    Product[] products = game.getProducts();

    int t = (int) (Math.random() * products.length);
    int q = (int) (Math.random() * MAX_QUANTITY) + 1;

    Product product = products[t];
    player.addProduct(product, q, 0);

    Message m = new Message(EVENT_COLOR, String.format("You just found %d %s on the ground!", q, product.getName()));
    return m;
  }

  @Override
  public boolean requiresInput() {
    return false;
  }
}
