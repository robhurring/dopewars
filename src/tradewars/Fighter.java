package tradewars;

/**
 * Simple interface to a class to be "fightable"
 */
public interface Fighter 
{
  public boolean isAlive();
  public String getName();
  public int getHealth();
  public int getStrength();
  public int getDefense();
  public void takeDamage(int damage);
  public int getMaxHealth();
  public int getLevel();
}