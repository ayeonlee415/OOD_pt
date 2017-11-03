package cs3500.animator.model.actions;

import cs3500.animator.model.shapes.Oval;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.model.shapes.Shape;

/**
 * A class that represents the scaling action of an Oval shape.
 */
public class ScaleOvalAction extends AScaleAction {
  private final double startXRadius;
  private final double startYRadius;
  private final double endXRadius;
  private final double endYRadius;

  /**
   * A constructor for the scaling action of an Oval shape.
   *
   * @param name         the name of the oval shape being scaled
   * @param start        the start of the scaling action
   * @param end          the end of the scaling action
   * @param startXRadius the starting x-radius of the oval
   * @param startYRadius the starting y-radius of the oval
   * @param endXRadius   the ending x-radius of the oval
   * @param endYRadius   the ending y-radius of the oval
   */
  private ScaleOvalAction(String name, int start, int end, double startXRadius, double startYRadius,
                          double endXRadius, double endYRadius) {
    super(name, start, end);
    this.startXRadius = startXRadius;
    this.startYRadius = startYRadius;
    this.endXRadius = endXRadius;
    this.endYRadius = endYRadius;
  }

  /**
   * Function to create a scaleOvalAction that checks that the dimensions being set are greater than
   * 0. INVARIANCE: Dimensions must be greater than 0
   *
   * @param name         the name of the shape being effected
   * @param start        the start time of the scaling
   * @param end          the end time of scaling
   * @param startXRadius the initial xRadius
   * @param startYRadius the initial yRadius
   * @param endXRadius   the final xRadius
   * @param endYRadius   the final yRadius
   * @return a new scaleOvalAction with the dimensions that are greater than 0
   */
  public static ScaleOvalAction createScaleOvalAction(String name, int start, int end,
                                                      double startXRadius, double startYRadius,
                                                      double endXRadius, double endYRadius) {
    if (startXRadius <= 0 || startYRadius <= 0 || endXRadius <= 0 || endYRadius <= 0) {
      throw new IllegalArgumentException("Oval Dimensions must be greater than 0");
    }
    return new ScaleOvalAction(name, start, end, startXRadius, startYRadius, endXRadius,
            endYRadius);
  }

  @Override
  public boolean isCompatible(IAction action) {
    return action.compatible(this);
  }

  @Override
  public String printStartValue() {
    return null;
  }

  @Override
  public String printEndValue() {
    return null;
  }

  @Override
  public boolean compatible(ScaleOvalAction scaleOvalAction) {
    return !(this.atSameTime(scaleOvalAction) && this.effectingSameShape(scaleOvalAction));
  }

  @Override
  protected String getAffectedString() {
    return "from ".concat("XRadius: ").concat(Double.toString(this.startXRadius))
            .concat(" YRadius: ").concat(Double.toString(this.startYRadius))
            .concat(" to XRadius: ").concat(Double.toString(this.endXRadius))
            .concat(" YRadius: ").concat(Double.toString(this.endYRadius));
  }

  @Override
  public Shape currentState(Shape shape, int tick) {
    return shape.acceptAction(this, tick);
  }

  @Override
  public Rectangle visit(Rectangle rectangle, int tick) {
    throw new IllegalArgumentException("Cannot use scale oval action on scale rectangle.");
  }

  @Override
  public Oval visit(Oval oval, int tick) {
    return Oval.createOval(oval.getName(), oval.getLocationPoint(), oval.getColor(),
            oval.getAppears(), oval.getDisappears(), this.getXRadius(tick), this.getYRadius(tick));
  }

  @Override
  public boolean validMove(Rectangle rectangle) {
    return false;
  }

  /**
   * A method that gets the x-radius of the oval at a given tick.
   *
   * @param tick the tick of the state
   * @return the x-radius of the oval at a given tick
   */
  private double getXRadius(int tick) {
    return this.startXRadius + ((this.endXRadius - this.startXRadius) /
            (this.end - this.start) * (tick - start));
  }

  /**
   * A method that gets the y-radius of the oval at a given tick.
   *
   * @param tick the tick of the state
   * @return the y-radius of the oval at a given tick
   */
  private double getYRadius(int tick) {
    return this.startYRadius + ((this.endYRadius - this.startYRadius) /
            (this.end - this.start) * (tick - start));
  }
}
