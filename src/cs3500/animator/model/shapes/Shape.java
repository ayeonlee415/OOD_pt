package cs3500.animator.model.shapes;

import java.awt.*;

import cs3500.animator.model.actions.AScaleAction;
import cs3500.animator.model.actions.IAction;

/**
 * An interface for the representation of shapes in the animation.
 */
public interface Shape {
  /**
   * Returns a string representing the details of this shape with the purpose of being used to
   * describe the overall animation. It will display the name, type, location, size, color, when it
   * appears and when it disappears. <strong>Example:</strong> Name: R<br> Type: rectangle<br>
   * Lower-left corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)<br> Appears
   * at t=1<br> Disappears at t=100<br>
   *
   * @return a string representing the details of the string.
   */
  String printInfo();

  /**
   * Returns the name of the shape.
   *
   * @return the name of the shape
   */
  String getName();

  /**
   * Prints the dimensions of the shape to be used in printing the moves, not printing the details
   * of the shape.
   *
   * @return a string representing the dimensions of the shape
   */
  String printDimensions();

  /**
   * Creates a copy of this shape.
   *
   * @return a copy of this shape
   */
  Shape copy();

  /**
   * Gets the name of the type of shape.
   *
   * @return the name of the type of shape
   */
  String getShapeType();

  /**
   * Returns the location of the shape as a string, not just the string equivelant of the point,
   * (e.g. center: (10.0, 10.0)).
   *
   * @return the location of the shape as a string
   */
  String getLocation();

  /**
   * Returns the tick at which the shape appears.
   *
   * @return the tick at which the shape appears
   */
  int getAppears();

  /**
   * Returns the tick at which the shape disappears.
   *
   * @return the tick at which the shape disappears
   */
  int getDisappears();

  /**
   * Creates a new shape with the values according to the action at the given time.
   *
   * @param action the action effecting the shape
   * @return a shape with values according to the action at the given time
   */
  Shape acceptAction(IAction action, int tick);

  /**
   * Determines if the action can be applied to this shape.
   *
   * @param action the action that is trying to be applied
   * @return whether the action can be applied to the shape
   */
  boolean validAction(IAction action);

  /**
   * Gets the color of the shape.
   *
   * @return the color of the shape
   */
  Color getColor();

  /**
   * Gets the location point of the shape.
   *
   * @return the location of the shape
   */
  Point.Double getLocationPoint();

  /**
   * Gets the dimensions of the shape as a string.
   *
   * @return the dimensions of the shape as a String
   */
  String getDimensions();


  /**
   * Draws the shape on the graphics.
   *
   * @param g the graphics the shape is being drawn on to
   */
  void draw(Graphics2D g);

  /**
   * Creates a scaling action for the given shape.
   *
   * @param name   the name of the shape the action is effecting
   * @param start  the start of the animation
   * @param end    the end of the animation
   * @param startX the beginning x size
   * @param startY the beginning y size
   * @param endX   the ending x size
   * @param endY   the ending y size
   * @return a scaling action for this shape
   */
  AScaleAction scaleAction(String name, int start, int end, double startX, double startY,
                           double endX, double endY);

  /**
   * Gets the size of the shape along the x-axis.
   *
   * @return the size of the shape along the x-axis
   */
  Double getXLength();

  /**
   * Gets the size of the shape along the y-axis.
   *
   * @return the size of the shape along the y-axis
   */
  Double getYLength();

  /**
   * Gets the SVG Type of the shape.
   *
   * @return the SVG type of the shape
   */
  String getSVGType();

  /**
   * Gets the location of the shape for SVG file format.
   *
   * @return the location of the shape for SVG file format
   */
  String getSVGLocation();

  /**
   * Gets the dimensions of the shape for SVG file format.
   *
   * @return the dimension of the shape for SVG file format
   */
  String getSVGDimensions();
}
