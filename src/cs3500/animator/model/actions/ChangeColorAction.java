package cs3500.animator.model.actions;

import java.awt.*;

import cs3500.animator.model.ColorUtils;
import cs3500.animator.model.shapes.Oval;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.model.shapes.Shape;

/**
 * A class that represents an action that changes color in the animation.
 */
public class ChangeColorAction extends AAction {
  private final Color startColor;
  private final Color endColor;

  /**
   * Represents the constructor for changing color action in the animation.
   *
   * @param name       the name of the shape having its color changed
   * @param start      the start of the color changing action
   * @param end        the end of the color changing action
   * @param startColor the starting color of the shape
   * @param endColor   the ending color of the shape
   */
  public ChangeColorAction(String name, int start, int end, Color startColor, Color endColor) {
    super(name, start, end);
    this.startColor = startColor;
    this.endColor = endColor;
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
  public boolean compatible(ChangeColorAction changeColorAction) {
    return !(this.atSameTime(changeColorAction) && this.effectingSameShape(changeColorAction));
  }

  @Override
  protected String getActionName() {
    return "changes color";
  }

  @Override
  protected String getAffectedString() {
    return "from ".concat(ColorUtils.colorToString(startColor)).concat(" to ")
            .concat(ColorUtils.colorToString(endColor));
  }

  @Override
  public Shape currentState(Shape shape, int tick) {
    return shape.acceptAction(this, tick);
  }

  @Override
  public Rectangle visit(Rectangle rectangle, int tick) {
    return Rectangle.createRectangle(rectangle.getName(), rectangle.getLocationPoint(),
            this.getColor(tick), rectangle.getAppears(), rectangle.getDisappears(),
            rectangle.getWidth(), rectangle.getHeight());
  }

  @Override
  public Oval visit(Oval oval, int tick) {
    return Oval.createOval(oval.getName(), oval.getLocationPoint(), this.getColor(tick),
            oval.getAppears(), oval.getDisappears(), oval.getXRadius(), oval.getYRadius());
  }

  /**
   * A method that gets the color at a given tick time.
   *
   * @param tick the tick of the state
   * @return the red, green, and blue components of the color at a given tick time
   */
  private Color getColor(int tick) {
    int r, g, b;
    if (tick - this.start != 0) {
      r = startColor.getRed() + (endColor.getRed() - startColor.getRed()) /
              ((this.end - this.start) / (tick - this.start));
      g = startColor.getGreen() + (endColor.getGreen() - startColor.getGreen()) /
              ((this.end - this.start) / (tick - this.start));
      b = startColor.getBlue() + (endColor.getBlue() - startColor.getBlue()) /
              ((this.end - this.start) / (tick - this.start));
    } else {
      r = startColor.getRed();
      g = startColor.getGreen();
      b = startColor.getBlue();
    }
    return new Color(r, g, b);
  }
}