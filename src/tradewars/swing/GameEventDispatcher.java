/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tradewars.swing;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

/**
 *
 * @author rob
 */
public class GameEventDispatcher implements KeyEventDispatcher {

  @Override
  public boolean dispatchKeyEvent(KeyEvent e) {
    if (e.getID() == KeyEvent.KEY_PRESSED) {
      System.out.println("tester");
    } else if (e.getID() == KeyEvent.KEY_RELEASED) {
      System.out.println("2test2");
    } else if (e.getID() == KeyEvent.KEY_TYPED) {
      System.out.println("3test3");
    }
    return false;
  }
}
