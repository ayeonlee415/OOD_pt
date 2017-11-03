package cs3500.animator.utils;

import java.awt.*;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.actions.ChangeColorAction;
import cs3500.animator.model.actions.MoveAction;
import cs3500.animator.model.shapes.Oval;
import cs3500.animator.model.shapes.Rectangle;

/**
 * Class that acts as an adapter from the AnimationFileReader to the AnimationModel.
 */
public class ModelBuilder implements TweenModelBuilder<AnimationModel> {
  private AnimationModel model;

  /**
   * A constructor for the model builder which creates new animation model.
   */
  public ModelBuilder() {
    this.model = new AnimationModel();
  }

  @Override
  public TweenModelBuilder<AnimationModel> addOval(String name, float cx, float cy, float xRadius,
                                                   float yRadius, float red, float green,
                                                   float blue, int startOfLife, int endOfLife) {
    model.addShape(Oval.createOval(name, new Point.Double(cx, cy), new Color(red, green, blue),
            startOfLife, endOfLife, xRadius, yRadius));
    return null;
  }

  @Override
  public TweenModelBuilder<AnimationModel> addRectangle(String name, float lx,
                                                        float ly, float width, float height,
                                                        float red, float green, float blue,
                                                        int startOfLife, int endOfLife) {
    model.addShape(Rectangle.createRectangle(name, new Point.Double(lx, ly), new Color(red, blue,
            green), startOfLife, endOfLife, width, height));
    return null;
  }

  @Override
  public TweenModelBuilder<AnimationModel> addMove(String name, float moveFromX, float moveFromY,
                                                   float moveToX, float moveToY, int startTime,
                                                   int endTime) {
    model.addAction(new MoveAction(name, startTime, endTime,
            new Point.Double(moveFromX, moveFromY), new Point.Double(moveToX, moveToY)));
    return null;
  }

  @Override
  public TweenModelBuilder<AnimationModel> addColorChange(String name, float oldR, float oldG,
                                                          float oldB, float newR, float newG,
                                                          float newB, int startTime, int endTime) {
    model.addAction(new ChangeColorAction(name, startTime, endTime, new Color(oldR, oldG, oldB),
            new Color(newR, newG, newB)));
    return null;
  }

  @Override
  public TweenModelBuilder<AnimationModel> addScaleToChange(String name, float fromSx,
                                                            float fromSy, float toSx,
                                                            float toSy, int startTime,
                                                            int endTime) {
    model.addScaleAction(name, startTime, endTime, fromSx, fromSy, toSx, toSy);
    return null;
  }

  @Override
  public AnimationModel build() {
    return this.model;
  }
}
