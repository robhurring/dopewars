package tradewars;

/**
 * Class to hold the fight and NPC iformation 
 * @author rob
 */
public class Fight implements TraderConstants {
  final static int RUN_CHANCE = 3;
  final static int DAMAGE_VARIABLE = 20;

  private Npc npc;
  private Player player;
  private boolean over;
  private Fighter winner;

  public Fight(Npc npc) {
    this.npc = npc;
    player = Game.getInstance().getPlayer();
    over = false;
  }

  public Npc getNpc() {
    return npc;
  }

  public Message attack() {
    int pd = damageDealt(player, npc);
    int nd = damageDealt(npc, player);
    Message m;


    npc.takeDamage(pd);
    if (npc.isAlive()) {
      player.takeDamage(nd);
      if (player.isAlive()) {
        m = new Message(FIGHT_COLOR, String.format("You hit %s for %d damage! (Took %d damage.)", npc.getName(), pd, nd));
      } else {
        over = true;
        winner = npc;
        m = new Message(KILLED_COLOR, "You've been killed by " + npc.getName() + "!");
      }
    } else {
      over = true;
      winner = player;
      
      // get a reward
      long reward = npc.getCash();
      player.addCash(reward);
      
      m = new Message(String.format("You killed %s and won %s!", npc, CURRENCY_FORMATTER.format(reward)));
    }

    return m;
  }

  public Message run() {
    int r = (int) (Math.random() * RUN_CHANCE);
    boolean gotAway = (r == 1);
    Message m;

    if (gotAway) {
      over = true;
      m = new Message("You successfully ran away!");
    } else {
      int pd = damageDealt(player, npc);
      player.takeDamage(pd);

      if (player.isAlive()) {
        m = new Message(String.format("You couldn't get away! You were hit for %d damage!", pd));
      } else {
        over = true;
        m = new Message("You were killed while trying to run!");
      }
    }

    return m;
  }
  
  public boolean fightOver() {
    return over;
  }

  public Fighter getWinner() {
    return winner;
  }

  private int damageDealt(Fighter attacker, Fighter opponent) {
    int amplifier = (int) (Math.random() * DAMAGE_VARIABLE);
    int damage = 0;

    boolean landed = (amplifier + attacker.getStrength()) > opponent.getDefense();
    boolean amplified = amplifier == DAMAGE_VARIABLE;

    if (landed || amplified) {
      double modifier = Math.random() * 0.5;
      
      damage = (int)(attacker.getStrength() * modifier);
      damage -= opponent.getDefense();
      if (damage < 0) {
        damage = 1;
      }
    }

    return damage;
  }
}
