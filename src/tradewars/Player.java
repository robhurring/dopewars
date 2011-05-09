package tradewars;

import tradewars.exceptions.*;
import java.util.*;

public class Player implements TraderConstants, Fighter {

  private String name;
  private long cash;
  private int health;
  private int space;
  private int strength;
  private int defense;
  private Map<Product, Map<Long, Integer>> products; //godhelpme

  public Player(String name) {
    this.name = name;
    cash = STARTING_CASH;
    health = STARTING_HEALTH;
    space = STARTING_SPACE;
    strength = STARTING_STRENGTH;
    defense = STARTING_DEFENSE;
    products = new HashMap<Product, Map<Long, Integer>>();
  }

  public int quantityForProduct(Product product, long price) {
    Map<Long, Integer> tmp;
    int ret = 0;

    if (products.containsKey(product)) {
      tmp = products.get(product);
      for (Map.Entry<Long, Integer> e : tmp.entrySet()) {
        if ((long) e.getKey() == price) {
          ret += e.getValue();
        }
      }
    }

    return ret;
  }

  public int quantityForProduct(Product product) {
    Map<Long, Integer> tmp;
    int ret = 0;

    if (products.containsKey(product)) {
      tmp = products.get(product);
      for (Map.Entry<Long, Integer> e : tmp.entrySet()) {
        ret += e.getValue();
      }
    }

    return ret;
  }

  public void sellProduct(Product product, int quantity, long price)
          throws QuantityOutOfBoundsException {

    long salePrice = price * quantity;
    long profit = (product.getPrice() - price) * quantity;
    removeProduct(product, quantity, price);
    cash += (salePrice + profit);
  }

  /**
   * @todo use .get() chaining since we've typed this out
   */
  public void removeProduct(Product product, int quantity, long price)
          throws QuantityOutOfBoundsException {
    Map<Long, Integer> tmp;
    if (products.containsKey(product)) {
      tmp = products.get(product);
      if (tmp.containsKey(price)) {
        Integer existing = tmp.get(price);
        int newQuantity = existing - quantity;

        if (quantity > existing) {
          throw new QuantityOutOfBoundsException("You cannot sell more than you have!");
        }

        if (newQuantity > 0) {
          tmp.put(price, newQuantity);
        } else {
          // remove the price point
          tmp.remove(price);
          // remove the product if we have no more price schedules
          if (quantityForProduct(product) <= 0) {
            products.remove(product);
          }
        }
      }
    }
  }

  /**
   * @todo use .get() chaining since we've typed the products hash
   * @param product
   * @param quantity
   * @param price
   */
  public void addProduct(Product product, int quantity, long price) {
    Map<Long, Integer> tmp;
    if (products.containsKey(product)) {
      tmp = products.get(product);
      if (tmp.containsKey(price)) {
        // update quantity
        Integer existing = tmp.get(price);
        tmp.put(price, quantity + existing);
      } else {
        // insert quantity
        tmp.put(price, quantity);
      }
    } else {
      // create inventory
      tmp = new HashMap<Long, Integer>();
      tmp.put(price, quantity);
      products.put(product, tmp);
    }
  }

  public void buyProduct(Product product, int quantity, long price)
          throws CannotAffordException, OutOfSpaceException {
    long total = price * quantity;
    if (total > cash) {
      throw new CannotAffordException("You cannot afford to buy this many.");
    }

    if (quantity > spaceLeft()) {
      throw new OutOfSpaceException("You cannot hold this many.");
    }

    cash -= total;
    addProduct(product, quantity, price);
  }

  public int spaceHeld() {
    int ret = 0;

    for (Map.Entry<Product, Map<Long, Integer>> e : getProducts().entrySet()) {
      for (Map.Entry<Long, Integer> q : e.getValue().entrySet()) {
        ret += q.getValue();
      }
    }

    return ret;
  }

  public int getMaxPurchase(Product p) {
    int max = (int) (cash / p.getPrice());
    int left = spaceLeft();

    if (max >= left) {
      max = left;
    }

    return max;
  }

// Accessors
  public int spaceLeft() {
    int avail = space - spaceHeld();
    return (avail < 0 ? 0 : avail);
  }

  public String getSpaceValue() {
    return String.format("%d/%d", spaceHeld(), space);
  }

  @Override
  public String toString() {
    return getName();
  }

  public Map<Product, Map<Long, Integer>> getProducts() {
    return products;
  }

  public String getName() {
    return name;
  }

  public int getSpace() {
    return space;
  }

  public void setSpace(int space) {
    this.space = space;
  }

  public boolean isAlive() {
    return health > 0;
  }

  public long getCash() {
    return cash;
  }

  public void setCash(long cash) {
    this.cash = cash;
  }
  
  public void addCash(long amount){
    this.cash += amount;
  }
  
  public void removeCash(long amount){
    this.cash -= amount;
  }

  public String getCashValue() {
    return CURRENCY_FORMATTER.format(cash);
  }

  public int getHealth() {
    return health;
  }

  public int getCapacity() {
    return (int) ((spaceHeld() / (double) space) * 100);
  }

  public int getStrength() {
    return strength;
  }

  public void addStrength(int value) {
    strength += value;
  }

  public int getDefense() {
    return defense;
  }

  public void addDefense(int value) {
    defense += value;
  }

  public void takeDamage(int damage) {
    health -= damage;
  }

  public int getMaxHealth() {
    return 100;
  }

  public void heal(int amount) {
    int max = getMaxHealth();

    if ((health + amount) > max) {
      health = max;
    } else {
      health += amount;
    }
  }

  public int getLevel() {
    int l = 1;

    if (strength > STARTING_STRENGTH) {
      l++;
    }

    if (defense > STARTING_DEFENSE) {
      l++;
    }

    return l;
  }
}
