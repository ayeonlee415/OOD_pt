package cs3500.animator.model.actions;

import java.awt.*;

import cs3500.animator.model.shapes.Oval;
import cs3500.animator.model.PointUtils;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.model.shapes.Shape;

/**
 * A class that represents a moving action in the animation.
 */
public class MoveAction extends AAction {
  private final Point.Double startPos;
  private final Point.Double endPos;

  /**
   * A constructor for a moving action in the animation.
   *
   * @param name     the name of the shape being moved
   * @param start    the start of the moving action
   * @param end      the end of the moving action
   * @param startPos the starting position of the shape
   * @param endPos   the ending position of the shape
   */
  public MoveAction(String name, int start, int end, Point.Double startPos, Point.Double endPos) {
    super(name, start, end);
    this.startPos = startPos;
    this.endPos = endPos;
  }

  @Override
  public boolean isCompatible(IAction action) {
    return action.compatible(this);
  }

  @Override
  public boolean compatible(MoveAction moveAction) {
    return !(this.atSameTime(moveAction) && this.effectingSameShape(moveAction));
  }

  @Override
  protected String getActionName() {
    return "moves";
  }

  @Override
  protected String getAffectedString() {
    return "from ".concat(PointUtils.locationToString(startPos)).concat(" to ")
            .concat(PointUtils.locationToString(endPos));
  }

  @Override
  public String printStartValue() {
    return PointUtils.locationToString(startPos);
  }

  @Override
  public String printEndValue() {
    return PointUtils.locationToString(endPos);
  }

  @Override
  public Shape currentState(Shape shape, int tick) {
    return shape.acceptAction(this, tick);
  }

  @Override
  public Rectangle visit(Rectangle rectangle, int tick) {
    return Rectangle.createRectangle(rectangle.getName(), this.getLocation(tick),
            rectangle.getColor(), rectangle.getAppears(), rectangle.getDisappears(),
            rectangle.getWidth(), rectangle.getHeight());
  }

  @Override
  public Oval visit(Oval oval, int tick) {
    return Oval.createOval(oval.getName(), this.getLocation(tick), oval.getColor(),
            oval.getAppears(), oval.getDisappears(), oval.getXRadius(), oval.getYRadius());
  }

  /**
   * A method that gets the location of the shape at the given tick.
   *
   * @param tick the tick of the state
   * @return the location of the shape
   */
  private Point.Double getLocation(int tick) {
    Double x = startPos.x + (((endPos.x - startPos.x) / (this.end - this.start)) *
            (tick - this.start));
    Double y = startPos.y + (((endPos.y - startPos.y) / (this.end - this.start)) *
            (tick - this.start));
    return new Point.Double(x, y);
  }
}
