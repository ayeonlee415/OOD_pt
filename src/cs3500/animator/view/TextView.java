package cs3500.animator.view;

import java.io.BufferedWriter;
import java.io.IOException;

import cs3500.animator.model.AnimationOperations;
import cs3500.animator.model.ColorUtils;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.model.actions.IAction;

/**
 * Class that represents the text view of the animation.
 */
public class TextView implements TextOutputView {

  /**
   * Prints the current details of the Model in the format that first describes the shapes that are
   * a part of the animation then lists all of the moves that each of the shapes undergo during the
   * duration of the animation. <strong>Example:</strong> Shapes:<br> Name: R <br>Type:
   * rectangle<br> Lower-left corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color:
   * (1.0,0.0,0.0)<br> Appears at t=1<br> Disappears at t=100<br><br> Name: C<br> Type: oval<br>
   * Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)<br> Appears at
   * t=6<br> Disappears at t=100<br><br> cs3500.animator.model.shapes.Shape R moves from
   * (200.0,200.0) to (300.0,300.0) from t=10 to t=50<br> cs3500.animator.model.shapes.Shape C moves
   * from (500.0,100.0) to (500.0,400.0) from t=20 to t=70<br> Shape C changes color from
   * (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=50 to t=80<br> cs3500.animator.model.shapes.Shape R
   * scales from Width: 50.0 Height 100.0 to Width: 25.0 Height: 100.0 from t=51 to t=70<br> Shape R
   * moves from (300.0,300.0) to (200.0,200.0) from t=70 to t=100<br>
   *
   * @param model the model of the animation
   * @param speed the number of ticks per second
   * @param out   the place where the animation should be outputted
   */
  @Override
  public void animate(AnimationOperations model, int speed, Appendable out) {
    String result = this.animateString(model, speed);
    try {
      out.append(result);
    } catch (IOException e) {
      throw new IllegalArgumentException("IOException");
    }
  }

  @Override
  public void animate(AnimationOperations model, int speed, BufferedWriter out) {
    String result = this.animateString(model, speed);
    try {
      out.write(result);
      out.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("IOException");
    }
  }

  /**
   * Converts all of the information about the shape into the properly formatted String.
   *
   * @param shape the shape the information is coming from
   * @param speed the number of ticks per second
   * @return the string representing the information of the shape
   */
  private String shapeToString(Shape shape, int speed) {
    String result = "Name: ".concat(shape.getName()).concat("\n");
    result = result.concat("Type: ").concat(shape.getShapeType()).concat("\n");
    result = result.concat(shape.getLocation());
    result = result.concat(shape.getDimensions()).concat("Color: ");
    result = result.concat(ColorUtils.colorToString(shape.getColor())).concat("\n");
    result = result.concat("Appears at t=").concat(Double.toString(
            (double) shape.getAppears() / speed)).concat("s\n");
    return result.concat("Disappears at t=").concat(Double.toString(
            (double) shape.getDisappears() / speed)).concat("s\n\n");
  }

  /**
   * Converts the shapes and actions in the animation into properly formatted String.
   *
   * @param model the model of the animation
   * @param speed the number of ticks per second
   * @return the string representing the animation
   */
  private String animateString(AnimationOperations model, int speed) {
    String result = "Shapes:\n";
    for (Shape shape : model.getInitState()) {
      result = result.concat(shapeToString(shape, speed));
    }
    for (IAction action : model.getActions()) {
      result = result.concat(action.printText(speed)).concat("\n");
    }
    return result;
  }
}
