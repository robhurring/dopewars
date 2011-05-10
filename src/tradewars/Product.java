package tradewars;

/**
 * Holds product state
 */
public class Product implements TraderConstants {
  final static int EVENT_RATE = 3;
  final static int EVENT_CHANCE = 30;

  protected String name;
  protected int highPrice;
  protected int lowPrice;
  protected String lowMessage;
  protected String highMessage;
  protected int price;
  protected Message message;

  public Product(String name, int lowPrice, int highPrice) {
    this(name, lowPrice, highPrice, "%s prices have bottomed out!", "%s prices are sky high!");
  }

  public Product(String name, int lowPrice, int highPrice, String lowMessage, String highMessage) {
    this.name = name;
    this.highPrice = highPrice;
    this.lowPrice = lowPrice;
    this.highMessage = highMessage;
    this.lowMessage = lowMessage;
    updatePrice();
  }

  public int getPrice() {
    return price;
  }

  public String getPriceValue() {
    return CURRENCY_FORMATTER.format(price);
  }

  public String getName() {
    return name;
  }

  protected String getHighMessage() {
    return String.format(highMessage, name);
  }

  protected String getLowMessage() {
    return String.format(lowMessage, name);
  }

  @Override
  public String toString() {
    return getName();
  }

  final public void updatePrice() {
    price = lowPrice + (int) (Math.random() * ((highPrice - lowPrice) + 1));
    message = null;
    
    int rand = (int) (Math.random() * EVENT_CHANCE);
    if (rand == 1) {
      price *= EVENT_RATE;
      message = new Message(PRICES_HIGH, getHighMessage());
    } else if (rand == 0) {
      price /= EVENT_RATE;
      message = new Message(PRICES_LOW, getLowMessage());
    }
  }

 public Message getMessage() {
    return message;
  }
}
