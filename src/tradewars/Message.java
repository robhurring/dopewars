package tradewars;

import java.awt.Color;

/**
 * A message holder that can handle colors
 * @author rob
 */
public class Message {
  final public static Color DEFAULT_COLOR = Color.BLACK;
  
  private String message;
  private Color color;
  
  
  public Message(Color color, String message) {
    this.color = color;
    this.message = message;
  }

  public Message(String message) {
    this(DEFAULT_COLOR, message);
  }
  
  public Color getColor(){
    return color;
  }
  
  public String getMessage(){
    return message;
  }
  
  @Override
  public String toString(){
    return message;
  }
}
