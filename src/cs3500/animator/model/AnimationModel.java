package cs3500.animator.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import cs3500.animator.model.actions.IAction;
import cs3500.animator.model.shapes.Shape;

/**
 * Class with the purposed of running the operations of the animationOperation interface.
 */
public class AnimationModel implements AnimationOperations {
  private List<Shape> shapes;
  private List<IAction> actions;

  /**
   * Constructor for the implementation of the cs3500.animator.model.
   */
  public AnimationModel() {
    shapes = new ArrayList<Shape>();
    actions = new ArrayList<IAction>();
  }

  @Override
  public void addShape(Shape shape) {
    for (Shape s : this.shapes) {
      if (s.getName().equalsIgnoreCase(shape.getName())) {
        throw new IllegalArgumentException("Shape with that name already exists");
      }
    }
    this.shapes.add(shape);
  }

  @Override
  public void addAction(IAction newAction) {
    //Need to add some checks to see if the move is compatible.
    if (shapes.size() == 0) {
      throw new IllegalArgumentException("There must be shapes to preform actions");
    }
    if (!this.shapes.get(getIndexOfShapeWithName(newAction.getShapeName()))
            .validAction(newAction)) {
      throw new IllegalArgumentException("Cannot use that operation on the shape.");
    }
    boolean compatible = true;
    for (IAction action : actions) {
      compatible = compatible && newAction.isCompatible(action);
    }
    if (compatible) {
      actions.add(newAction);
    } else {
      throw new IllegalArgumentException("Invalid action");
    }
    //Sort the actions by which change happens first
    actions.sort(new Comparator<IAction>() {
      @Override
      public int compare(IAction o1, IAction o2) {
        return (Integer.compare(o1.getStart(), o2.getStart()));
      }
    });
  }

  @Override
  public ArrayList<Shape> getState(int tick) {
    ArrayList<Shape> result = new ArrayList<Shape>();
    for (Shape shape : shapes) {
      if (shape.getAppears() <= tick && shape.getDisappears() > tick) {
        result.add(this.getCurrentState(shape, tick));
      }
    }
    return result;
  }

  @Override
  public ArrayList<Shape> getInitState() {
    ArrayList<Shape> result = new ArrayList<Shape>();
    for (Shape shape : shapes) {
      result.add(shape.copy());
    }
    return result;
  }

  @Override
  public ArrayList<IAction> getActions() {
    ArrayList<IAction> result = new ArrayList<IAction>();
    result.addAll(actions);
    return result;
  }

  @Override
  public void addScaleAction(String name, int start, int end, double fromX, double fromY, double toX, double toY) {
    addAction(shapes.get(getIndexOfShapeWithName(name))
            .scaleAction(name, start, end, fromX, fromY, toX, toY));
  }

  /**
   * Gets the index of the shape in the list of shapes with the given name.
   *
   * @param name the name of the shape
   * @return the index of the shape in the list of shapes with the given name
   */
  private int getIndexOfShapeWithName(String name) {
    for (int i = 0; i < shapes.size(); i++) {
      if (shapes.get(i).getName().equalsIgnoreCase(name)) {
        return i;
      }
    }
    throw new IllegalArgumentException("There is no shape with that name");
  }

  /**
   * Iterates through the actions in the animation and gets the state of the object that it would be
   * based on all of the actions that effect at that time.
   *
   * @param shape the shape that the current state is being retrieved
   * @param tick  the time at which the state is desired (ticks)
   * @return the current state of the shape
   */
  private Shape getCurrentState(Shape shape, int tick) {
    for (IAction action : actions) {
      if (action.isHappening(tick) && action.getShapeName().equalsIgnoreCase(shape.getName())) {
        shape = shape.acceptAction(action, tick);
      } else if (action.getEnd() < tick && action.getShapeName()
              .equalsIgnoreCase(shape.getName())) {
        shape = shape.acceptAction(action, action.getEnd());
      } else if (action.getStart() > tick) {
        break;
      }
    }
    return shape.copy();
  }
}