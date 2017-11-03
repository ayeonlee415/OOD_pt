package cs3500.animator.model.actions;

import cs3500.animator.model.shapes.Oval;
import cs3500.animator.model.shapes.Rectangle;

/**
 * An abstract class that represents action in an animation.
 */
public abstract class AAction implements IAction {
  protected final String name;
  protected final int start;
  protected final int end;

  /**
   * Constructor for Action. INVARIANCE: start < end, start >= 0, end > 0.
   *
   * @param name  the name of the shape being effected
   * @param start the tick when the action starts
   * @param end   the tick when the action ends
   */
  public AAction(String name, int start, int end) {
    if (start >= end || start < 0 || end < 0) {
      throw new IllegalArgumentException("Start must be before end");
    }
    this.name = name;
    this.start = start;
    this.end = end;
  }

  @Override
  public String getShapeName() {
    return this.name;
  }

  @Override
  public int getStart() {
    return this.start;
  }

  @Override
  public int getEnd() {
    return this.end;
  }

  @Override
  public boolean compatible(ScaleRectangleAction scaleRectangleAction) {
    return true;
  }

  @Override
  public boolean compatible(ScaleOvalAction scaleOvalAction) {
    return true;
  }

  @Override
  public boolean compatible(ChangeColorAction changeColorAction) {
    return true;
  }

  @Override
  public boolean compatible(MoveAction moveAction) {
    return true;
  }

  @Override
  public boolean isHappening(int tick) {
    return tick <= this.end && tick >= this.start;
  }

  /**
   * Determines is two actions occur at the same time.
   *
   * @param action the action being compared
   * @return whether the two actions are occurring at the same time
   */
  protected boolean atSameTime(AAction action) {
    return (this.start >= action.getStart() && this.start <= action.getEnd())
            || (this.end <= action.getEnd() && this.end >= action.getStart());
  }

  @Override
  public String printText(int speed) {
    return "Shape ".concat(this.getShapeName()).concat(" ").concat(this.getActionName())
            .concat(" ").concat(this.getAffectedString()).concat(" from t=")
            .concat(Double.toString((double) this.getStart() / speed)).concat("s to t=")
            .concat(Double.toString((double) this.getEnd() / speed)).concat("s");
  }

  /**
   * Returns the name of the action being performed.
   *
   * @return the name of the action being performed
   */
  abstract protected String getActionName();

  /**
   * Returns a string of what aspect of the shape was effected.
   *
   * @return a string of what aspect of the shape was effected
   */
  abstract protected String getAffectedString();

  /**
   * Determines if this action and the given action are operating on the same shape.
   *
   * @param action the action being compared to this one.
   * @return whether this action and the given action are effecting the same shape
   */
  protected boolean effectingSameShape(AAction action) {
    return this.getShapeName().equalsIgnoreCase(action.getShapeName());
  }

  @Override
  public boolean validMove(Rectangle rectangle) {
    return true;
  }

  @Override
  public boolean validMove(Oval oval) {
    return true;
  }
}
