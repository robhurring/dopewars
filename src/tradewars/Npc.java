package tradewars;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rob
 */
public class Npc implements Fighter {
  private String name;
  private int health;
  private int maxHealth;
  private int strength;
  private int defense;
  private long low;
  private long high;
  private int level;

  public Npc(String name, int maxHealth, int strength, int defense, long low, long high){
    this(name, maxHealth, strength, defense, low, high, 1);
  }
  
  public Npc(String name, int maxHealth, int strength, int defense, long low, long high, int level) {
    this.name = name;
    this.maxHealth = maxHealth;
    this.health = maxHealth;
    this.strength = strength;
    this.defense = defense;
    this.low = low;
    this.high = high;
    this.level = level;
  }

  public boolean isAlive() {
    return (health > 0);
  }

  public String getName() {
    return name;
  }

  public int getHealth() {
    return health;
  }
  
  public int getMaxHealth(){
    return maxHealth;
  }

  public void takeDamage(int damage) {
    health -= damage;
  }

  public int getStrength() {
    return strength;
  }

  public int getDefense() {
    return defense;
  }

  public int getLevel() {
    return level;
  }

  public long getCash() {
    return low + (long)(Math.random() * ((high - low) + 1));
  }
  
  @Override
  public String toString(){
    return name;
  }

  public void reset() {
    health = maxHealth;
  }
}
