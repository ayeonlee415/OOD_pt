package cs3500.animator.view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import cs3500.animator.model.shapes.Shape;

/**
 * A class that represents the panel for the shape in an animation.
 */
public class ShapePanel extends JPanel {
  private ArrayList<Shape> shapes;

  /**
   * A constructor for the shape panel which creates a new panel.
   */
  public ShapePanel() {
    super();
    shapes = new ArrayList<Shape>();
    this.setBackground(Color.WHITE);
  }

  /**
   * A method that updates the shapes in the panel.
   *
   * @param newShapes represents new shapes
   */
  public void updateShapes(ArrayList<Shape> newShapes) {
    shapes.clear();
    shapes.addAll(newShapes);
    this.repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.removeAll();
    this.setPreferredSize(this.getWindowSize());
    Graphics2D g2d = (Graphics2D) g;
    for (Shape shape : shapes) {
      shape.draw(g2d);
    }
  }

  /**
   * Gets the minim required dimensions such that the panel displays the whole animation.
   *
   * @return the minimum required dimensions so that the panel displays the whole animation
   */
  private Dimension getWindowSize() {
    Double maxX = 600.0;
    Double maxY = 600.0;
    for (Shape s : shapes) {
      maxX = Math.max(s.getLocationPoint().getX() + s.getXLength(), maxX);
      maxY = Math.max(s.getLocationPoint().getY() + s.getYLength(), maxY);
    }
    return new Dimension(maxX.intValue(), maxY.intValue());
  }
}