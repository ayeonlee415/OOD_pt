package cs3500.animator.model.shapes;

import java.awt.Color;
import java.awt.Point;

/**
 * An abstract class representing the shapes that can be used in the animation
 * cs3500.animator.model.
 */
public abstract class AShape implements Shape {
  protected final String name;
  protected Point.Double location;
  protected Color color;
  protected final int appears;
  protected final int disappears;

  /**
   * Constructor for an cs3500.animator.model.shapes.AShape. INVARIANCE: The shape must appear after
   * 0 and must disappear after it appears and all values must not be null.
   *
   * @param name       the name of the shape
   * @param location   the location of the shape
   * @param color      the color of the shape
   * @param appears    the time at which the shape appears
   * @param disappears the time at which the shape disappears
   */
  public AShape(String name, Point.Double location, Color color, int appears, int disappears) {
    if (appears < 0 || disappears < 0 || appears > disappears) {
      throw new IllegalArgumentException("The shape must appear and disappear after t=0 and "
              + "appear must be before disappear");
    }
    if (location == null || name == null || color == null) {
      throw new IllegalArgumentException("All fields must not be initialized to null");
    }
    this.name = name;
    this.location = location;
    this.color = color;
    this.appears = appears;
    this.disappears = disappears;
  }

  @Override
  public String printInfo() {
    String result = "Name: ".concat(this.name).concat("\n");
    result = result.concat(this.getShapeType()).concat(
            this.getLocation()).concat(this.getDimensions());
    result = result.concat("Color: ").concat(colorToString()).concat("\n");
    result = result.concat("Appears at t=").concat(Integer.toString(appears)).concat("\n");
    return result.concat("Disappears at t=").concat(Integer.toString(disappears).concat("\n"));
  }

  /**
   * Returns the string equivalent of the point the shape is located.
   *
   * @return the string equivalent of the point the shape is located
   */
  public String locationToString() {
    return "(".concat(Double.toString(location.getX())).concat(",")
            .concat(Double.toString(location.getY())).concat(")");
  }

  /**
   * Returns the color as a string (e.g. (1,1,1) for r=1 g=1 b=1.
   *
   * @return color as a string
   */
  public String colorToString() {
    return "(".concat(Double.toString(color.getRed())).concat(",")
            .concat(Double.toString(color.getGreen())).concat(",")
            .concat(Double.toString(color.getBlue())).concat(")");
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getAppears() {
    return this.appears;
  }

  @Override
  public int getDisappears() {
    return this.disappears;
  }

}
