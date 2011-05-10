package tradewars;

/**
 * Holds information about the location. Whats for sale, etc.
 */
public class Location 
{
  protected String name;
  protected Product[] products;

  public Location(String name, Product[] products)
  {
    this.name = name;
    this.products = products;
  }
  
  @Override
  public String toString()
  {
    return getName();
  }

  public String getName()
  {
    return name;
  }
  
  public Product[] getProducts()
  {
    return products;
  }
}