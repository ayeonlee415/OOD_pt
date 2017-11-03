package cs3500.animator.model.shapes;

import java.awt.*;

import cs3500.animator.model.actions.AScaleAction;
import cs3500.animator.model.actions.IAction;
import cs3500.animator.model.actions.ScaleOvalAction;

/**
 * A class that represents an Oval shape in the animation.
 */
public class Oval extends AShape {
  private double xRadius;
  private double yRadius;

  /**
   * Private constructor after values have been checked.
   *
   * @param name       the name of the oval
   * @param location   the location of the center of the oval
   * @param color      the color of the oval
   * @param appears    when the oval appears
   * @param disappears when the oval disappears
   * @param xRadius    the x-axis radius of the oval
   * @param yRadius    the y-axis radius of the oval
   */
  private Oval(String name, Point.Double location, Color color, int appears, int disappears,
               double xRadius, double yRadius) {
    super(name, location, color, appears, disappears);
    this.xRadius = xRadius;
    this.yRadius = yRadius;
  }

  /**
   * A static function that will check whether the values for xRadius and YRadius are valid and then
   * calls the private constructor. INVARIANCE: Dimensions are positive.
   *
   * @param name       the name of the oval
   * @param location   the location of the center of the oval
   * @param color      the color of the oval
   * @param appears    when the oval appears
   * @param disappears when the oval disappears
   * @param xRadius    the xRadius of the oval
   * @param yRadius    the yRadius of the oval
   */
  public static Oval createOval(String name, Point.Double location, Color color,
                                int appears, int disappears, double xRadius, double yRadius) {
    if (xRadius <= 0 || yRadius <= 0) {
      throw new IllegalArgumentException("Dimensions must be greater than 0");
    } else {
      return new Oval(name, location, color, appears, disappears, xRadius, yRadius);
    }
  }

  @Override
  public String getShapeType() {
    return "oval";
  }

  @Override
  public String getLocation() {
    return "Center: ".concat(locationToString()).concat(", ");
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
    return "X radius: ".concat(Double.toString(xRadius))
            .concat(", Y radius: ").concat(Double.toString(yRadius)).concat(", ");
  }

  @Override
  public void draw(Graphics2D g) {
    g.setPaint(this.color);
    g.fillOval(new Double(this.location.getX()).intValue(),
            new Double(this.location.getY()).intValue(),
            new Double(this.xRadius).intValue(),
            new Double(this.yRadius).intValue());
  }

  @Override
  public AScaleAction scaleAction(String name, int start, int end, double startX, double startY,
                                  double endX, double endY) {
    return ScaleOvalAction.createScaleOvalAction(name, start, end, startX, startY, endX, endY);
  }

  @Override
  public Double getXLength() {
    return this.xRadius;
  }

  @Override
  public Double getYLength() {
    return this.yRadius;
  }

  @Override
  public String getSVGType() {
    return "ellipse";
  }

  @Override
  public String getSVGLocation() {
    String result = "cx=\"".concat(String.valueOf(location.getX())).concat("\" ");
    return result.concat("cy=\"").concat(String.valueOf(location.getY())).concat("\"");
  }

  @Override
  public String getSVGDimensions() {
    String result = "rx=\"".concat(String.valueOf(xRadius)).concat("\" ");
    return result.concat("ry=\"").concat(String.valueOf(yRadius)).concat("\"");
  }

  /**
   * Increases the x and y radius by the given factors. INVARIANCE: Dimensions are greater than 0.
   *
   * @param xAdd amount added to x radius
   * @param yAdd amount added to y radius
   */
  public void scale(double xAdd, double yAdd) {
    if (Double.compare(xAdd + xRadius, 0) <= 0 || Double.compare(yAdd + yRadius, 0) <= 0) {
      throw new IllegalArgumentException("All dimensions must be greater than 0");
    } else {
      this.xRadius += xAdd;
      this.yRadius += yAdd;
    }
  }

  @Override
  public String printDimensions() {
    return "XRadius: ".concat(Double.toString(xRadius))
            .concat(" YRadius: ").concat(Double.toString(yRadius));
  }

  @Override
  public Shape copy() {
    return new Oval(name, new Point.Double(location.x, location.y), new Color(color.getRed(),
            color.getGreen(), color.getBlue()), appears, disappears, xRadius, yRadius);
  }

  /**
   * A method that gets the x-radius of the oval.
   *
   * @return the x-radius of the oval
   */
  public double getXRadius() {
    return this.xRadius;
  }

  /**
   * A method that gets the y-radius of the oval.
   *
   * @return the y-radius of the oval
   */
  public double getYRadius() {
    return this.yRadius;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Oval)) {
      return false;
    } else {
      Oval oval = (Oval) o;
      return name.equalsIgnoreCase(oval.name) && location.equals(oval.location)
              && color.equals(oval.color) && appears == oval.appears
              && disappears == oval.disappears && (Math.abs(xRadius - oval.getXRadius()) < .01)
              && (Math.abs(yRadius - oval.getYRadius()) < .01);
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
    result = 31 * result + Double.hashCode(xRadius);
    result = 31 * result + Double.hashCode(yRadius);
    return result;
  }
}
