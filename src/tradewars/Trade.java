package tradewars;

import tradewars.exceptions.CannotAffordException;
import tradewars.exceptions.QuantityOutOfBoundsException;
import tradewars.exceptions.InvalidTradeException;
import tradewars.exceptions.OutOfSpaceException;

/**
 * Holds state for the current trade
 * @author rob
 */
public class Trade {

  public static enum Mode {
    BUY, SELL
  };
  private Product product;
  private long price;
  private int quantity;
  private Mode mode;
  private int max;
  private String errorMessage;

  public Trade() {
    this.price = 0;
    this.max = 0;
    this.quantity = 0;
    this.errorMessage = "";
  }

  public Trade(Product product, long price) {
    this();
    this.product = product;
    this.price = price;
  }

  public void setMax(int max) {
    this.max = max;
  }

  public int getMax() {
    return max;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Product getProduct() {
    return product;
  }

  public void setPrice(long price) {
    this.price = price;
  }

  public long getPrice() {
    return price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setMode(Mode mode) {
    this.mode = mode;
  }

  public Mode getMode() {
    return mode;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public void commit()
          throws InvalidTradeException, OutOfSpaceException, CannotAffordException, QuantityOutOfBoundsException {
    Game game = Game.getInstance();

    if (quantity <= 0) {
      throw new InvalidTradeException("Quantity must be greater than 0!");
    }

    if (mode == Mode.BUY) {
      game.getPlayer().buyProduct(product, quantity, price);
    } else {
      game.getPlayer().sellProduct(product, quantity, price);
    }
  }
}
