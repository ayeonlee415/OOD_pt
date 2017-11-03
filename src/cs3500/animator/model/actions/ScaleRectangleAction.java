package cs3500.animator.model.actions;

import cs3500.animator.model.shapes.Oval;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.model.shapes.Shape;

/**
 * A class that represents scaling action of a rectangle shape in the animation.
 */
public class ScaleRectangleAction extends AScaleAction {
  private final double startWidth;
  private final double startHeight;
  private final double endWidth;
  private final double endHeight;

  /**
   * A constructor of scaling action of a rectangle shape in the animation.
   *
   * @param name        the name of the rectangle shape being scaled
   * @param start       the start of the scaling action
   * @param end         the end of the scaling action
   * @param startWidth  the starting width dimension of the rectangle
   * @param startHeight the starting height dimension of the rectangle
   * @param endWidth    the ending width dimension of the rectangle
   * @param endHeight   the ending height dimension of the rectangle
   */
  public ScaleRectangleAction(String name, int start, int end, double startWidth,
                              double startHeight, double endWidth, double endHeight) {
    super(name, start, end);
    this.startWidth = startWidth;
    this.startHeight = startHeight;
    this.endWidth = endWidth;
    this.endHeight = endHeight;
  }

  /**
   * A method to create a ScaleRectangleAction and checking that the dimensions do not drop below
   * zero. INVARIANCE: dimensions must be > 0.
   *
   * @param name        the name of the shape being scaled
   * @param start       the start of the scaling
   * @param end         the end of the scaling
   * @param startWidth  the initial width of the rectangle
   * @param startHeight the initial height of the rectangle
   * @param endWidth    the final width of the rectangle
   * @param endHeight   the final height of the rectangle
   * @return a new ScaleRectangleAction where the dimensions are greater than 0
   */
  public static ScaleRectangleAction createScaleRectangleAction(String name, int start, int end,
                                                                double startWidth,
                                                                double startHeight,
                                                                double endWidth,
                                                                double endHeight) {
    if (startWidth <= 0 || startHeight <= 0 || endWidth <= 0 || endHeight <= 0) {
      throw new IllegalArgumentException("All dimensions must be greater than 0");
    }
    return new ScaleRectangleAction(name, start, end, startWidth, startHeight, endWidth,
            endHeight);
  }

  @Override
  public boolean isCompatible(IAction action) {
    return action.compatible(this);
  }

  @Override
  public boolean compatible(ScaleRectangleAction scaleRectangleAction) {
    return !(this.atSameTime(scaleRectangleAction)
            && this.effectingSameShape(scaleRectangleAction));
  }

  @Override
  public String printStartValue() {
    return "Width: ".concat(Double.toString(this.startWidth)).concat(", Height: ").concat(Double
            .toString(this.startHeight)).concat(", ");
  }

  @Override
  public String printEndValue() {
    return "Width: ".concat(Double.toString(this.endWidth)).concat(", Height: ").concat(Double
            .toString(this.endHeight)).concat(", ");
  }

  @Override
  public Shape currentState(Shape shape, int tick) {
    return shape.acceptAction(this, tick);
  }

  @Override
  public Rectangle visit(Rectangle rectangle, int tick) {
    return Rectangle.createRectangle(rectangle.getName(), rectangle.getLocationPoint(),
            rectangle.getColor(), rectangle.getAppears(), rectangle.getDisappears(),
            this.getWidth(tick), this.getHeight(tick));
  }

  @Override
  public Oval visit(Oval oval, int tick) {
    throw new IllegalArgumentException("Cannot use scale rectangle action on oval.");
  }

  @Override
  public boolean validMove(Oval oval) {
    return false;
  }

  @Override
  protected String getAffectedString() {
    return "from ".concat("Width: ").concat(Double.toString(this.startWidth))
            .concat(" Height: ").concat(Double.toString(this.startHeight))
            .concat(" to Width: ").concat(Double.toString(this.endWidth))
            .concat(" Height: ").concat(Double.toString(this.endHeight));
  }

  /**
   * A method that gets the width of the rectangle at a given tick.
   *
   * @param tick the tick of the state
   * @return the width of the rectangle at a given tick
   */
  private double getWidth(int tick) {
    return this.startWidth + (((this.endWidth - this.startWidth) / (this.end - this.start))
            * (tick - start));
  }

  /**
   * A method that gets the height of the rectangle at a given tick.
   *
   * @param tick the tick of the state
   * @return the height of the rectangle at a given tick
   */
  private double getHeight(int tick) {
    return this.startHeight + (((this.endHeight - this.startHeight) / (this.end - this.start))
            * (tick - start));
  }
}
