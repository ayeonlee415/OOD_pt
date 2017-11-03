package cs3500.animator.view;

import java.util.ArrayList;

import cs3500.animator.model.AnimationOperations;
import cs3500.animator.model.shapes.Shape;

/**
 * An interface that represents that actions that a cs3500.animator.view will need to implement for
 * the Visual View.
 */
public interface GraphicalView {

  /**
   * Function that creates the display of the animation of the given animation model.
   *
   * @param model the model of the animation
   * @param speed the number of ticks per second
   */
  void animate(AnimationOperations model, int speed) throws InterruptedException;

  /**
   * Function that updates the list of shapes in the view.
   *
   * @param shapes the shapes that will be put in the view
   */
  void updateShapes(ArrayList<Shape> shapes);
}
