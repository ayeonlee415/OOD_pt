package cs3500.animator.model.actions;

/**
 * An abstract class that represents a scaling action in the animation.
 */
public abstract class AScaleAction extends AAction {

  /**
   * Represents the constructor for a scaling action in the animation.
   *
   * @param name  the name of the shape being scaled
   * @param start the start of the scaling action
   * @param end   the end of the scaling action
   */
  public AScaleAction(String name, int start, int end) {
    super(name, start, end);
  }

  @Override
  protected String getActionName() {
    return "scales";
  }
}
