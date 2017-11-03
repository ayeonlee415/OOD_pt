package cs3500.animator.model.actions;

import cs3500.animator.model.shapes.Oval;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.model.shapes.Shape;

/**
 * An interface that represents actions in the animation.
 */
public interface IAction {
  /**
   * Returns the name of the shape that this action is effecting.
   *
   * @return the name of the shape that this action is effecting
   */
  String getShapeName();

  /**
   * Returns the tick when this action starts.
   *
   * @return the tick when this action starts
   */
  int getStart();

  /**
   * Returns the tick when this action ends.
   *
   * @return the tick when this action
   */
  int getEnd();

  /**
   * Determines if two actions are compatible based on their times and whether the two actions are
   * allowed to occur at the same time.
   *
   * @param action the action this action is being compared to
   * @return whether the two actions are compatible
   */
  boolean isCompatible(IAction action);

  /**
   * Prints the starting value of the action as a string.
   *
   * @return the starting value of the action as a string
   */
  String printStartValue();

  /**
   * Prints the ending value of the action as a string.
   *
   * @return the ending value of the action as a string
   */
  String printEndValue();

  /**
   * Returns whether this action is compatible with the moveAction.
   *
   * @param moveAction the moveAction that is being compared
   * @return whether this action is compatible with the moveAction
   */
  boolean compatible(MoveAction moveAction);

  /**
   * Returns whether this action is compatible with the scaleOvalAction.
   *
   * @param scaleOvalAction the scaleOvalAction that is being compared
   * @return whether this action is compatible with the scaleOvalAction
   */
  boolean compatible(ScaleOvalAction scaleOvalAction);

  /**
   * Returns whether this action is compatible with the ScaleRectangleAction.
   *
   * @param scaleRectangleAction the scaleRectangleAction that is being compared
   * @return whether this action is compatible with the scaleRectangleAction
   */
  boolean compatible(ScaleRectangleAction scaleRectangleAction);

  /**
   * Returns whether this action is compatible with the changeColorAction.
   *
   * @param changeColorAction the changeColorActon that is being compared
   * @return whether this action is compatible with the changeColorAction
   */
  boolean compatible(ChangeColorAction changeColorAction);

  /**
   * Determines if the animation is happening at the given tick.
   *
   * @param tick to determine if the animation is happening
   * @return whether the animation is happening at that tick
   */
  boolean isHappening(int tick);

  /**
   * Produces a shape with the state of the given shape at the given tick.
   *
   * @param shape thats state is being retrieved
   * @param tick  of which state of the shape should be retrieved
   * @return the state of the shape at the given tick
   */
  Shape currentState(Shape shape, int tick);

  /**
   * Creates a new rectangle based on the given rectangle and this action's effects.
   *
   * @param rectangle the rectangle being effected
   * @param tick      the tick of the state when this action is being effected
   * @return a new rectangle based on the given rectangle and this action's effects
   */
  Rectangle visit(Rectangle rectangle, int tick);

  /**
   * Creates a new oval based on the given oval and this action's effects.
   *
   * @param oval the oval being effected
   * @param tick the tick of the state when this action is being effected
   * @return a new oval based on the given oval and this action's effects
   */
  Oval visit(Oval oval, int tick);

  /**
   * Prints the text version of the action with the given number of ticks.
   *
   * @param speed the number of ticks per second
   * @return the text version of the action with the given number of ticks
   */
  String printText(int speed);

  /**
   * Determines if this action can be applied to an oval.
   *
   * @param oval the oval the action is trying to be applied to
   * @return whether the action can be applied to an oval
   */
  boolean validMove(Oval oval);

  /**
   * Determines if this action can be applied to a rectangle.
   *
   * @param rectangle the rectangle this action is trying to be applied to
   * @return whether the action can be applied to a rectangle
   */
  boolean validMove(Rectangle rectangle);

//  /**
//   * Prints the text version of the action with the given number of ticks for SVG file format.
//   *
//   * @param speed the number of ticks per second
//   * @return the text version of the action with the given number of ticks for SVG file format
//   */
//  String printSVGAction(int speed);
}
