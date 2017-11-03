package cs3500.animator.view;

import java.io.BufferedWriter;

import cs3500.animator.model.AnimationOperations;

/**
 * An interface that represents that actions that a cs3500.animator.view will need to implement for
 * text output views which are SVG View and Text View.
 */
public interface TextOutputView {

  /**
   * Function that creates the display of the animation of the given animation model using
   * Appendable output.
   *
   * @param model the model of the animation
   * @param speed the number of ticks per second
   * @param out   the place where the animation should be outputted
   */
  void animate(AnimationOperations model, int speed, Appendable out);

  /**
   * A method that creates the display of the animation of the given animation model using
   * BufferedWriter output.
   *
   * @param model the model of the animation
   * @param speed the number of ticks per second
   * @param out   the place where the animation should be outputted
   */
  void animate(AnimationOperations model, int speed, BufferedWriter out);
}
