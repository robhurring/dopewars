Java Tradewars
=============

Java Tradewars is a generic dopewars-like engine that can be easily re-themed. There is no XML (thank god) to configure, just straight java. This project is themed
to dopewars as an homage to one of my favorite oldschool games :)

I tried to keep the tradewars package outside of swing where I could, so it can be easily adapted to a console game if you wanted. 

Screenshots
-----------

![Tradewars](http://0rcus.com/i/tradewars/tradewars-1.png "TradeWars")


![Tradewars Event](http://0rcus.com/i/tradewars/tradewars-4.png "TradeWars - Event")

Customization
-------------

Customizing this game is done in the `tradewars.Game` file. Here is the general list of things to customize:

* Events: these are things that happen while moving around
  * Finding cash/stuff on the ground
  * Fights
  * Healing events
  * Bigger coat
  * etc.
* NPCs: these are the bad guys you fight
* Products: these are what can be bought/sold at the marketplace
* Locations: where to go?

Customizing Products
--------------------

To create a custom product you need a few things

    new Product(String name, int lowPrice, int highPrice[, String lowMessage, String highMessage]);
    
    // Example
    new Product("Product Name", 100, 500);
    new Product("Product Name", 100, 500, "%s prices have bottomed out!", "%s prices are at an all-time high!");
    
    // Add a bunch to Game.java
    final static private Product[] products = {
      new Product("Product Name", 100, 500)
    };
    
and you're off the the races. The movement will randomize the prices between the high and low numbers. Randomly the prices will spike. You can adjust this in the
`Product.EVENT_CHANCE` constant.

Customizing Locations
---------------------

This is run almost exactly like products. You specify an array of `Locations` to the game class to use. Each location can use a customized array of products.
  
    new Location(String name, Product[] products_to_sell);
    
    // Add a bunch to Game.java
    final static private Location[] locations = {
        new Location("Coney IslaLocationnd", products),
        new Location("Manhattan", products)
    };
    
Customizing NPCs
----------------

If you chose to enable fights, these are the guys that randomly appear. The `Game.startFight` method will try to match levels with the player if one is given,
otherwise it is randomized.

    new Npc(String name, int maxHealth, int strength, int defense, long low, long high[, int level]);
    
    // Example:
    new Npc("Bossman", 500, 25, 5, 1000, 3000, 5);
    
    // Add a bunch to Game.java
    final static private Npc[] npcs = {
        new Npc("Street Thug", 20, 20, 5, 2000, 5000, 1)
    };
    
Bossman will only show up when the player is level 5+ and will reward between 1-3k cash.

Customizing Events
------------------

Events are a bit trickier since they can encompass so many different things. A generic event will have access to the game, player and return messages. There are
2 basic ways events are used.

1. Prompt the user for an action and request a response (yes/no)
2. Notify the user of something that happened (no response necessary)


To trigger an event the `inEvent()` method should return true. there is a `hit` helper to assist with this -- pass in a number and it will match a random number against
it. For examples of events, check the `tradewars.events` package.
