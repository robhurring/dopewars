package tradewars.swing;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.Color;

/**
 * TextPage than can handle a different color per line
 */
public class ColorTextPane extends JTextPane {

  public void append(Color color, String text) {
    StyleContext context = StyleContext.getDefaultStyleContext();
    AttributeSet attr = context.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);

    int l = getDocument().getLength();
    setCaretPosition(l);
    setCharacterAttributes(attr, false);
    replaceSelection(text);
  }
}
