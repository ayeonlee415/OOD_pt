package cs3500.animator.model.shapes;

import java.awt.*;

import cs3500.animator.model.actions.AScaleAction;
import cs3500.animator.model.actions.IAction;
import cs3500.animator.model.actions.ScaleRectangleAction;

/**
 * A class representing a rectangle in the animation.
 */
public class Rectangle extends AShape {
  private double width;
  private double height;

  /**
   * Private constructor for rectangles.
   *
   * @param name       the name of the rectangle
   * @param location   the location of the rectangle
   * @param color      the color of the rectangle
   * @param appears    when the rectangle appears
   * @param disappears when the rectangle disappears
   * @param width      the width of the rectangle
   * @param height     the height of the rectangle
   */
  private Rectangle(String name, Point.Double location, Color color, int appears, int disappears,
                    double width, double height) {
    super(name, location, color, appears, disappears);
    this.width = width;
    this.height = height;
  }

  /**
   * Static function that checks the values of width and height to make sure they are valid and then
   * calls the private constructor. INVARIANCE: Dimensions are positive.
   *
   * @param name       the name of the rectangle
   * @param location   the location of the rectangle
   * @param color      the color of the rectangle
   * @param appears    when the rectangle appears
   * @param disappears when the rectangle disappears
   * @param width      the width of the rectangle
   * @param height     the height of the rectangle
   */
  public static Rectangle createRectangle(String name, Point.Double location, Color color,
                                          int appears, int disappears, double width,
                                          double height) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("All dimensions must be greater than 0");
    } else {
      return new Rectangle(name, location, color, appears, disappears, width, height);
    }
  }

  @Override
  public String getShapeType() {
    return "rectangle";
  }

  @Override
  public String getLocation() {
    return "Lower-left corner: ".concat(locationToString()).concat(", ");
  }

  @Override
  public Shape acceptAction(IAction action, int tick) {
    return action.visit(this, tick);
  }

  @Override
  public boolean validAction(IAction action) {
    return action.validMove(this);
  }

  @Override
  public Color getColor() {
    return new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
  }

  @Override
  public Point.Double getLocationPoint() {
    return new Point.Double(this.location.x, this.location.y);
  }

  @Override
  public String getDimensions() {
    return "Width: ".concat(Double.toString(this.width)).concat(", Height: ").concat(Double
            .toString(this.height)).concat(", ");
  }

  @Override
  public void draw(Graphics2D g) {
    g.setPaint(this.color);
    g.fillRect(new Double(this.location.getX()).intValue(),
            new Double(this.location.getY()).intValue(),
            new Double(this.width).intValue(),
            new Double(this.height).intValue());
  }

  @Override
  public AScaleAction scaleAction(String name, int start, int end, double startX, double startY,
                                  double endX, double endY) {
    return ScaleRectangleAction.createScaleRectangleAction(name, start, end, startX, startY,
            endX, endY);
  }

  @Override
  public Double getXLength() {
    return this.width;
  }

  @Override
  public Double getYLength() {
    return this.height;
  }

  @Override
  public String getSVGType() {
    return "rect";
  }

  @Override
  public String getSVGLocation() {
    String result = "x=\"".concat(String.valueOf(location.getX())).concat("\" ");
    return result.concat("y=\"").concat(String.valueOf(location.getY())).concat("\"");
  }

  @Override
  public String getSVGDimensions() {
    String result = "width=\"".concat(String.valueOf(width)).concat("\" ");
    return result.concat("height=\"").concat(String.valueOf(height)).concat("\"");
  }

  /**
   * Adds the given additional width to the width and the additional given height to the height.
   * INVARIANCE: Dimensions are positive.
   *
   * @param widthAdd  additional width to be added to the width
   * @param heightAdd additional height to be added to the height
   */
  public void scale(double widthAdd, double heightAdd) {
    if (Double.compare(widthAdd + this.width, 0) <= 0 || Double.compare(heightAdd + this.height, 0)
            <= 0) {
      throw new IllegalArgumentException("All dimensions must be greater than 0");
    } else {
      this.height += heightAdd;
      this.width += widthAdd;
    }
  }

  @Override
  public String printDimensions() {
    return "Width: ".concat(Double.toString(this.width)).concat(" Height: ").concat(Double
            .toString(this.height));
  }

  @Override
  public Shape copy() {
    return new Rectangle(name, new Point.Double(location.x, location.y), new Color(color.getRed(),
            color.getGreen(), color.getBlue()), appears, disappears, width, height);
  }

  /**
   * A method that gets the width dimension of the rectangle.
   *
   * @return the width of the rectangle
   */
  public double getWidth() {
    return this.width;
  }

  /**
   * A method that gets the height dimension of the rectangle.
   *
   * @return the height of the rectangle
   */
  public double getHeight() {
    return this.height;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Rectangle)) {
      return false;
    } else {
      Rectangle rect = (Rectangle) o;
      return name.equalsIgnoreCase(rect.name) && location.equals(rect.location)
              && color.equals(rect.color) && appears == rect.appears
              && disappears == rect.disappears && (Math.abs(width - rect.getWidth()) < .001)
              && (Math.abs(height - rect.getHeight()) < .001);
    }
  }

  @Override
  public int hashCode() {
    int result = 21;
    result = 31 * result + name.hashCode();
    result = 31 * result + location.hashCode();
    result = 31 * result + color.hashCode();
    result = 31 * result + appears;
    result = 31 * result + disappears;
    result = 31 * result + Double.hashCode(width);
    result = 31 * result + Double.hashCode(height);
    return result;
  }
}
