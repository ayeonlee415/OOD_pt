package cs3500.animator.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import cs3500.animator.model.AnimationOperations;
import cs3500.animator.model.shapes.Shape;

import javax.swing.*;

/**
 * Class that represents the visual view for the animation.
 */
public class VisualView extends JFrame implements GraphicalView {
  private ShapePanel shapePanel;

  /**
   * A constructor for the visual view for the animation. Creates new visual view.
   */
  public VisualView() {
    super();
    this.setTitle("Easy Animator");
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    shapePanel = new ShapePanel();
    this.add(shapePanel, BorderLayout.CENTER);

    JScrollPane scrollPane = new JScrollPane(shapePanel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setBounds(0, 0, 600, 600);
    JPanel contentPane = new JPanel(null);
    contentPane.setPreferredSize(new Dimension(600, 600));
    contentPane.add(scrollPane);
    this.setContentPane(contentPane);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setVisible(true);

    this.pack();
    this.setVisible(true);
  }

  @Override
  public void animate(AnimationOperations model, int speed) throws InterruptedException {
    int endTick = model.getActions().get(model.getActions().size() - 1).getEnd();
    while (true) {
      for (int ticks = 0; ticks < model.getActions().get(model.getActions().size() - 1).getEnd();
           ticks++) {
        this.updateShapes(model.getState(ticks));
        TimeUnit.MILLISECONDS.sleep(100 / speed);
      }
    }
  }

  @Override
  public void updateShapes(ArrayList<Shape> shapes) {
    this.shapePanel.updateShapes(shapes);
  }
}
