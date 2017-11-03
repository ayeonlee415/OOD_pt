package cs3500.animator.model;

import java.awt.Point;

/**
 * Class that provides some functionality that the java point class does not provide.
 */
public class PointUtils {
  /**
   * Returns the string equivalent of the given point.
   *
   * @return the string equivalent of the given point
   */
  static public String locationToString(Point.Double point) {
    return "(".concat(Double.toString(point.getX())).concat(",")
            .concat(Double.toString(point.getY())).concat(")");
  }
}
