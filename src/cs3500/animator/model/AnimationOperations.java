package cs3500.animator.model;

import java.util.ArrayList;

import cs3500.animator.model.actions.IAction;
import cs3500.animator.model.shapes.Shape;

/**
 * An interface representing all of the functions in the animator.
 */
public interface AnimationOperations {

  /**
   * Adds a shape to the list of shapes in the animation.
   *
   * @param shape that will be added to list of shapes in the animation
   */
  void addShape(Shape shape);

  /**
   * Adds a action, according to the visitor, to be preformed on the shape at the given shape index
   * to occur at begin and end at the end specified in the method.
   *
   * @param action the action that is being added to the model
   */
  void addAction(IAction action);

  /**
   * Returns a list of shapes at the current state of the animation.
   *
   * @return a list of shapes in the animation that are visible
   */
  ArrayList<Shape> getState(int t);

  /**
   * Returns a list of shapes at the initial state of the animation.
   *
   * @return a list of shapes at their initial state
   */
  ArrayList<Shape> getInitState();

  /**
   * Returns a list of actions in the animation.
   *
   * @return a list of actions in the animation
   */
  ArrayList<IAction> getActions();

  /**
   * Adds a scaling action to the model.
   *
   * @param name  of shape getting the action added to
   * @param start when the action starts happening
   * @param end   when the action stops happening
   * @param fromX the start of the x scaling
   * @param fromY the start of the y scaling
   * @param toX   the end of the x scaling
   * @param toY   the end of the y scaling
   */
  void addScaleAction(String name, int start, int end, double fromX, double fromY,
                      double toX, double toY);
}
