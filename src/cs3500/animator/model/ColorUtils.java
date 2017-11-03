package cs3500.animator.model;

import java.awt.Color;

/**
 * Class that provides various color functions not provided by java's color class.
 */
public class ColorUtils {
  /**
   * Returns the color as a string (e.g. (1,1,1) for r=1 g=1 b=1.
   *
   * @return color as a string
   */
  static public String colorToString(Color color) {
    return "(".concat(Double.toString(color.getRed())).concat(",")
            .concat(Double.toString(color.getGreen())).concat(",")
            .concat(Double.toString(color.getBlue())).concat(")");
  }
}
