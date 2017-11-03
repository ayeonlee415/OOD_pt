package cs3500.animator.model.actions;

import cs3500.animator.model.shapes.Oval;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.model.shapes.Shape;

/**
 * A class to represent the scaling action in an animation.
 */
public class ScaleAction extends AAction {
  private double oldXSize;
  private double oldYSize;
  private double newXSize;
  private double newYSize;

  /**
   * A constructor for the scaling action.
   *
   * @param name     the name of the shape
   * @param start    the start time of the action
   * @param end      the end time of the action
   * @param oldXSize the original x size
   * @param oldYSize the original y size
   * @param newXSize the new x size after scaling
   * @param newYSize the new y size after scaling
   */
  private ScaleAction(String name, int start, int end, double oldXSize, double oldYSize,
                      double newXSize, double newYSize) {
    super(name, start, end);
    this.oldXSize = oldXSize;
    this.oldYSize = oldYSize;
    this.newXSize = newXSize;
    this.newYSize = newYSize;
  }

  @Override
  protected String getActionName() {
    return "scales";
  }

  @Override
  protected String getAffectedString() {
    return null;
  }

  @Override
  public boolean isCompatible(IAction action) {
    return false;
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
  public Shape currentState(Shape shape, int tick) {
    return null;
  }

  @Override
  public Rectangle visit(Rectangle rectangle, int tick) {
    return null;
  }

  @Override
  public Oval visit(Oval oval, int tick) {
    return null;
  }
}