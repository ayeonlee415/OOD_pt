import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

import cs3500.animator.model.actions.ChangeColorAction;
import cs3500.animator.model.actions.ScaleOvalAction;
import cs3500.animator.model.actions.ScaleRectangleAction;
import cs3500.animator.model.shapes.*;

import java.awt.Point;
import java.awt.Color;
import java.util.ArrayList;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationOperations;
import cs3500.animator.model.actions.MoveAction;
import cs3500.animator.model.shapes.Oval;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.view.TextView;

/**
 * Class for testing getting the current state of the shapes in the animation.
 */
public class GetCurrentShapeStateTest {
  AnimationOperations model1;
  TextView textView1;
  StringBuffer out;
  AnimationModel modelWithRectAndOval;

  /**
   * Creates an empty cs3500.animator.model for testing.
   */
  @Before
  public void initialize() {
    model1 = new AnimationModel();
    textView1 = new TextView();
    out = new StringBuffer();
    model1.addShape(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 100, 50.0, 100.0));
    model1.addShape(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
  }

  /**
   * Test that if there are no actions and the shapes are visible it will return the initial sate of
   * the object but it will be a copy.
   */
  @Test
  public void testGettingCurrentStateNoActions() {
    ArrayList<Shape> expectedCurrentState = new ArrayList<Shape>();
    expectedCurrentState.add(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 100, 50.0, 100.0));
    expectedCurrentState.add(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    ArrayList<Shape> actualCurrentState = model1.getState(10);
    boolean same = actualCurrentState.size() == expectedCurrentState.size();
    for (int i = 0; i < actualCurrentState.size(); i++) {
      same = same && expectedCurrentState.get(i).equals(actualCurrentState.get(i))
              && expectedCurrentState.get(i) != actualCurrentState.get(i);
    }
    assertEquals(true, same);
  }

  /**
   * Test that if the shapes have disappeared, they will not be included.
   */
  @Test
  public void testGettingCurrentStateNoActionsDisappeared() {
    ArrayList<Shape> actualCurrentState = model1.getState(101);
    assertEquals(0, actualCurrentState.size());
  }

  /**
   * Test that if the shapes have not appeared they will not be included.
   */
  @Test
  public void testGettingCurrentStateNoActionsNotAppeared() {
    ArrayList<Shape> actualCurrentState = model1.getState(0);
    assertEquals(0, actualCurrentState.size());
  }

  /**
   * Test that if the shape appears in that tick then it will be included.
   */
  @Test
  public void testGettingCurrentStateAppearsOnTick() {
    ArrayList<Shape> actualCurrentState = model1.getState(1);
    boolean same = actualCurrentState.size() == 1 && actualCurrentState.get(0).equals(
            Rectangle.createRectangle("R", new Point.Double(200, 200), new Color(1,
                    0, 0), 1, 100, 50.0, 100.0));
    assertEquals(true, same);
  }

  /**
   * Test that if the shape disappears on the tick, it will disappear.
   */
  @Test
  public void testGettingCurrentStateDisappearsOnTick() {
    ArrayList<Shape> actualCurrentState = model1.getState(100);
    assertEquals(true, actualCurrentState.size() == 0);
  }

  /**
   * Test that if none of the actions have happened yet, the original state will be returned.
   */
  @Test
  public void testGettingCurrentStateMoveNotHappened() {
    model1.addAction(new MoveAction("R", 10, 11, new Point.Double(10, 100),
            new Point.Double(200, 200)));
    ArrayList<Shape> expectedCurrentState = new ArrayList<Shape>();
    expectedCurrentState.add(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 100, 50.0, 100.0));
    expectedCurrentState.add(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    ArrayList<Shape> actualCurrentState = model1.getState(9);
    boolean same = actualCurrentState.size() == expectedCurrentState.size();
    for (int i = 0; i < actualCurrentState.size(); i++) {
      same = same && expectedCurrentState.get(i).equals(actualCurrentState.get(i))
              && expectedCurrentState.get(i) != actualCurrentState.get(i);
    }
    assertEquals(true, same);
  }

  /**
   * Test that if a moveAction is happening and the tick is at the very start it should be in the
   * position that is the beginning of the move.
   */
  @Test
  public void testGettingCurrentStateMoveHappeningBeginning() {
    model1.addAction(new MoveAction("R", 10, 11, new Point.Double(10, 100),
            new Point.Double(200, 200)));
    ArrayList<Shape> expectedCurrentState = new ArrayList<Shape>();
    expectedCurrentState.add(Rectangle.createRectangle("R", new Point.Double(10, 100),
            new Color(1, 0, 0), 1, 100, 50.0, 100.0));
    expectedCurrentState.add(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    ArrayList<Shape> actualCurrentState = model1.getState(10);
    boolean same = actualCurrentState.size() == expectedCurrentState.size();
    for (int i = 0; i < actualCurrentState.size(); i++) {
      same = same && expectedCurrentState.get(i).equals(actualCurrentState.get(i))
              && expectedCurrentState.get(i) != actualCurrentState.get(i);
    }
    assertEquals(true, same);
  }

  /**
   * Test that if a moveAction is happening and the tick is at the very end it should be in the
   * position that is in the end of the move.
   */
  @Test
  public void testGettingCurrentStateMoveHappeningEnd() {
    model1.addAction(new MoveAction("R", 10, 11, new Point.Double(10, 100),
            new Point.Double(300, 300)));
    ArrayList<Shape> expectedCurrentState = new ArrayList<Shape>();
    expectedCurrentState.add(Rectangle.createRectangle("R", new Point.Double(300, 300),
            new Color(1, 0, 0), 1, 100, 50.0, 100.0));
    expectedCurrentState.add(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    ArrayList<Shape> actualCurrentState = model1.getState(11);
    boolean same = actualCurrentState.size() == expectedCurrentState.size();
    for (int i = 0; i < actualCurrentState.size(); i++) {
      same = same && expectedCurrentState.get(i).equals(actualCurrentState.get(i))
              && expectedCurrentState.get(i) != actualCurrentState.get(i);
    }
    assertEquals(true, same);
  }

  /**
   * Test that if a moveAction is happening it will take the right tween point.
   */
  @Test
  public void testGettingCurrentStateMoveHeppeningMiddle() {
    model1.addAction(new MoveAction("R", 10, 12, new Point.Double(10, 10),
            new Point.Double(20, 20)));
    ArrayList<Shape> expectedCurrentState = new ArrayList<Shape>();
    expectedCurrentState.add(Rectangle.createRectangle("R", new Point.Double(15, 15),
            new Color(1, 0, 0), 1, 100, 50.0, 100.0));
    expectedCurrentState.add(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    ArrayList<Shape> actualCurrentState = model1.getState(11);
    boolean same = actualCurrentState.size() == expectedCurrentState.size();
    for (int i = 0; i < actualCurrentState.size(); i++) {
      same = same && expectedCurrentState.get(i).equals(actualCurrentState.get(i))
              && expectedCurrentState.get(i) != actualCurrentState.get(i);
    }
    assertEquals(true, same);
  }

  /**
   * Test that is a moveAction has already happened and it is the last action to effect the
   * position, the position will be the final state of the action.
   */
  @Test
  public void testGettingCurrentStateMoveAlreadyHappened() {
    model1.addAction(new MoveAction("R", 10, 12, new Point.Double(10, 10),
            new Point.Double(20, 20)));
    ArrayList<Shape> expectedCurrentState = new ArrayList<Shape>();
    expectedCurrentState.add(Rectangle.createRectangle("R", new Point.Double(20, 20),
            new Color(1, 0, 0), 1, 100, 50.0, 100.0));
    expectedCurrentState.add(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    ArrayList<Shape> actualCurrentState = model1.getState(14);
    boolean same = actualCurrentState.size() == expectedCurrentState.size();
    for (int i = 0; i < actualCurrentState.size(); i++) {
      same = same && expectedCurrentState.get(i).equals(actualCurrentState.get(i))
              && expectedCurrentState.get(i) != actualCurrentState.get(i);
    }
    assertEquals(true, same);
  }

  /**
   * Testing that if a changeColorAction has not happened it will produce original state.
   */
  @Test
  public void testGettingCurrentStateChangeColorNotHappened() {
    model1.addAction(new ChangeColorAction("R", 10, 12,
            new Color(1, 1, 1), new Color(0, 0, 0)));
    ArrayList<Shape> expectedCurrentState = new ArrayList<Shape>();
    expectedCurrentState.add(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 100, 50.0, 100.0));
    expectedCurrentState.add(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    ArrayList<Shape> actualCurrentState = model1.getState(6);
    boolean same = actualCurrentState.size() == expectedCurrentState.size();
    for (int i = 0; i < actualCurrentState.size(); i++) {
      same = same && expectedCurrentState.get(i).equals(actualCurrentState.get(i))
              && expectedCurrentState.get(i) != actualCurrentState.get(i);
    }
    assertEquals(true, same);
  }

  /**
   * Testing that if a changeColorAction is happening and it is the first tick of the action it
   * should have the initial color specificity in the action.
   */
  @Test
  public void testGettingCurrentStateChangeColorHappeningStart() {
    model1.addAction(new ChangeColorAction("R", 10, 12,
            new Color(1, 1, 1), new Color(0, 0, 0)));
    ArrayList<Shape> expectedCurrentState = new ArrayList<Shape>();
    expectedCurrentState.add(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 1, 1), 1, 100, 50.0, 100.0));
    expectedCurrentState.add(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    ArrayList<Shape> actualCurrentState = model1.getState(10);
    boolean same = actualCurrentState.size() == expectedCurrentState.size();
    for (int i = 0; i < actualCurrentState.size(); i++) {
      same = same && expectedCurrentState.get(i).equals(actualCurrentState.get(i))
              && expectedCurrentState.get(i) != actualCurrentState.get(i);
    }
    assertEquals(true, same);
  }

  /**
   * Testing that if a changeColorAction is happening and it is the last tick of the action it
   * should have the final color specified in the action.
   */
  @Test
  public void testGettingCurrentStateChangeColorHappeningEnd() {
    model1.addAction(new ChangeColorAction("R", 10, 12,
            new Color(1, 1, 1), new Color(0, 0, 0)));
    ArrayList<Shape> expectedCurrentState = new ArrayList<Shape>();
    expectedCurrentState.add(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(0, 0, 0), 1, 100, 50.0, 100.0));
    expectedCurrentState.add(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    ArrayList<Shape> actualCurrentState = model1.getState(12);
    boolean same = actualCurrentState.size() == expectedCurrentState.size();
    for (int i = 0; i < actualCurrentState.size(); i++) {
      same = same && expectedCurrentState.get(i).equals(actualCurrentState.get(i))
              && expectedCurrentState.get(i) != actualCurrentState.get(i);
    }
    assertEquals(true, same);
  }

  /**
   * Testing that if a changeColorAction is happening and it is a middle tick of the action it
   * should have the appropriate tween value.
   */
  @Test
  public void testGettingCurrentStateChangeColorHappeningMiddle() {
    model1.addAction(new ChangeColorAction("R", 10, 12,
            new Color(1, 1, 1), new Color(3, 3, 3)));
    ArrayList<Shape> expectedCurrentState = new ArrayList<Shape>();
    expectedCurrentState.add(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(3, 3, 3), 1, 100, 50.0, 100.0));
    expectedCurrentState.add(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    ArrayList<Shape> actualCurrentState = model1.getState(12);
    boolean same = actualCurrentState.size() == expectedCurrentState.size();
    for (int i = 0; i < actualCurrentState.size(); i++) {
      same = same && expectedCurrentState.get(i).equals(actualCurrentState.get(i))
              && expectedCurrentState.get(i) != actualCurrentState.get(i);
    }
    assertEquals(true, same);
  }

  /**
   * Test that if a changeColorAction has already happened and it was the last action to effect the
   * shape then the state will be that defined as the final for the changeColorAction.
   */
  @Test
  public void testGettingCurrentStateChangeColorAlreadyHappened() {
    model1.addAction(new ChangeColorAction("R", 10, 12,
            new Color(1, 1, 1), new Color(0, 0, 0)));
    ArrayList<Shape> expectedCurrentState = new ArrayList<Shape>();
    expectedCurrentState.add(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(0, 0, 0), 1, 100, 50.0, 100.0));
    expectedCurrentState.add(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    ArrayList<Shape> actualCurrentState = model1.getState(20);
    boolean same = actualCurrentState.size() == expectedCurrentState.size();
    for (int i = 0; i < actualCurrentState.size(); i++) {
      same = same && expectedCurrentState.get(i).equals(actualCurrentState.get(i))
              && expectedCurrentState.get(i) != actualCurrentState.get(i);
    }
    assertEquals(true, same);
  }

  /**
   * Testing that if a scaleRectangle action hasn't happened yet, it will return the initial state
   * of the rectangle.
   */
  @Test
  public void testGettingCurrentStateScaleRectNotHappening() {
    model1.addAction(ScaleRectangleAction.createScaleRectangleAction("R", 10, 12,
            100, 150, 200, 300));
    ArrayList<Shape> expectedCurrentState = new ArrayList<Shape>();
    expectedCurrentState.add(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 100, 50.0, 100.0));
    expectedCurrentState.add(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    ArrayList<Shape> actualCurrentState = model1.getState(6);
    boolean same = actualCurrentState.size() == expectedCurrentState.size();
    for (int i = 0; i < actualCurrentState.size(); i++) {
      same = same && expectedCurrentState.get(i).equals(actualCurrentState.get(i))
              && expectedCurrentState.get(i) != actualCurrentState.get(i);
    }
    assertEquals(true, same);
  }

  /**
   * Testing that if a scaleRectangle action is happening and it is the first tick of the action
   * that it will show the intial state according to the action.
   */
  @Test
  public void testGettingCurrentStateScaleRectStart() {
    model1.addAction(ScaleRectangleAction.createScaleRectangleAction("R", 10, 12,
            100, 150, 200, 300));
    ArrayList<Shape> expectedCurrentState = new ArrayList<Shape>();
    expectedCurrentState.add(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 100, 100.0, 150.0));
    expectedCurrentState.add(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    ArrayList<Shape> actualCurrentState = model1.getState(10);
    boolean same = actualCurrentState.size() == expectedCurrentState.size();
    for (int i = 0; i < actualCurrentState.size(); i++) {
      same = same && expectedCurrentState.get(i).equals(actualCurrentState.get(i))
              && expectedCurrentState.get(i) != actualCurrentState.get(i);
    }
    assertEquals(true, same);
  }

  /**
   * Test that if a scaleRectangle action is happening and it is the last tick of the action that it
   * will show the final state according to the action.
   */
  @Test
  public void testGettingCurrentStateScaleRectEnd() {
    model1.addAction(ScaleRectangleAction.createScaleRectangleAction("R", 10, 12,
            100, 150, 200, 300));
    ArrayList<Shape> expectedCurrentState = new ArrayList<Shape>();
    expectedCurrentState.add(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 100, 200.0, 300.0));
    expectedCurrentState.add(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    ArrayList<Shape> actualCurrentState = model1.getState(12);
    boolean same = actualCurrentState.size() == expectedCurrentState.size();
    for (int i = 0; i < actualCurrentState.size(); i++) {
      same = same && expectedCurrentState.get(i).equals(actualCurrentState.get(i))
              && expectedCurrentState.get(i) != actualCurrentState.get(i);
    }
    assertEquals(true, same);
  }

  /**
   * Test that if a scaleRectangle action is happening and it is in the middle of the action that it
   * will show the correct tweener position.
   */
  @Test
  public void testGettingCurrentStateScaleRectMid() {
    model1.addAction(ScaleRectangleAction.createScaleRectangleAction("R", 10, 12,
            100, 150, 200, 300));
    ArrayList<Shape> expectedCurrentState = new ArrayList<Shape>();
    expectedCurrentState.add(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 100, 150.0, 225.0));
    expectedCurrentState.add(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    ArrayList<Shape> actualCurrentState = model1.getState(11);
    boolean same = actualCurrentState.size() == expectedCurrentState.size();
    for (int i = 0; i < actualCurrentState.size(); i++) {
      same = same && expectedCurrentState.get(i).equals(actualCurrentState.get(i))
              && expectedCurrentState.get(i) != actualCurrentState.get(i);
    }
    assertEquals(true, same);
  }

  /**
   * Test that if a scaleRectangleAction has already happened and it is the last action to effect
   * the rectangle then the size will be the final size of the scaleRectangleAction.
   */
  @Test
  public void testGettingCurrentStateScaleRectAlreadyHappened() {
    model1.addAction(ScaleRectangleAction.createScaleRectangleAction("R", 10, 12,
            100, 150, 200, 300));
    ArrayList<Shape> expectedCurrentState = new ArrayList<Shape>();
    expectedCurrentState.add(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 100, 200.0, 300.0));
    expectedCurrentState.add(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    ArrayList<Shape> actualCurrentState = model1.getState(20);
    boolean same = actualCurrentState.size() == expectedCurrentState.size();
    for (int i = 0; i < actualCurrentState.size(); i++) {
      same = same && expectedCurrentState.get(i).equals(actualCurrentState.get(i))
              && expectedCurrentState.get(i) != actualCurrentState.get(i);
    }
    assertEquals(true, same);
  }

  /**
   * Test that if a scaleOvalAction has not happened then it will return the initial state.
   */
  @Test
  public void testGettingCurrentStateScaleOvalNotHappening() {
    model1.addAction(ScaleOvalAction.createScaleOvalAction("C", 10, 12,
            100, 150, 200, 300));
    ArrayList<Shape> expectedCurrentState = new ArrayList<Shape>();
    expectedCurrentState.add(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 100, 50.0, 100.0));
    expectedCurrentState.add(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    ArrayList<Shape> actualCurrentState = model1.getState(6);
    boolean same = actualCurrentState.size() == expectedCurrentState.size();
    for (int i = 0; i < actualCurrentState.size(); i++) {
      same = same && expectedCurrentState.get(i).equals(actualCurrentState.get(i))
              && expectedCurrentState.get(i) != actualCurrentState.get(i);
    }
    assertEquals(true, same);
  }

  /**
   * Test that if a scaleOvalAction is happening and it is the first tick of the action that it will
   * return the state based on the initial state in the action.
   */
  @Test
  public void testGettingCurrentStateScaleOvalStart() {
    model1.addAction(ScaleOvalAction.createScaleOvalAction("C", 10, 12,
            100, 150, 200, 300));
    ArrayList<Shape> expectedCurrentState = new ArrayList<Shape>();
    expectedCurrentState.add(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 100, 50.0, 100.0));
    expectedCurrentState.add(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 100.0, 150.0));
    ArrayList<Shape> actualCurrentState = model1.getState(10);
    boolean same = actualCurrentState.size() == expectedCurrentState.size();
    for (int i = 0; i < actualCurrentState.size(); i++) {
      same = same && expectedCurrentState.get(i).equals(actualCurrentState.get(i))
              && expectedCurrentState.get(i) != actualCurrentState.get(i);
    }
    assertEquals(true, same);
  }

  /**
   * Test that if a scaleOvalAction is happening and it is in the last tick of the action that it
   * will return the state based on the final state in the action.
   */
  @Test
  public void testGetCurrentStateScaleOvalEnd() {
    model1.addAction(ScaleOvalAction.createScaleOvalAction("C", 10, 12,
            100, 150, 200, 300));
    ArrayList<Shape> expectedCurrentState = new ArrayList<Shape>();
    expectedCurrentState.add(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 100, 50.0, 100.0));
    expectedCurrentState.add(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 200.0, 300.0));
    ArrayList<Shape> actualCurrentState = model1.getState(12);
    boolean same = actualCurrentState.size() == expectedCurrentState.size();
    for (int i = 0; i < actualCurrentState.size(); i++) {
      same = same && expectedCurrentState.get(i).equals(actualCurrentState.get(i))
              && expectedCurrentState.get(i) != actualCurrentState.get(i);
    }
    assertEquals(true, same);
  }

  /**
   * Test that if a scaleOvalAction is happening and it is in the middle of the action it will
   * calculate the appropriate tweener stage.
   */
  @Test
  public void testGetCurrentStateScaleOvalMiddle() {
    model1.addAction(ScaleOvalAction.createScaleOvalAction("C", 10, 12,
            100, 150, 200, 300));
    ArrayList<Shape> expectedCurrentState = new ArrayList<Shape>();
    expectedCurrentState.add(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 100, 50.0, 100.0));
    expectedCurrentState.add(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 150.0, 225.0));
    ArrayList<Shape> actualCurrentState = model1.getState(11);
    boolean same = actualCurrentState.size() == expectedCurrentState.size();
    for (int i = 0; i < actualCurrentState.size(); i++) {
      same = same && expectedCurrentState.get(i).equals(actualCurrentState.get(i))
              && expectedCurrentState.get(i) != actualCurrentState.get(i);
    }
    assertEquals(true, same);
  }

  /**
   * Test that if a scaleOvalAction already happened and it is the last action to effect the oval,
   * the size will be the final size of the action.
   */
  @Test
  public void testGetCurrentStateOvalAlreadyHappened() {
    model1.addAction(ScaleOvalAction.createScaleOvalAction("C", 10, 12,
            100, 150, 200, 300));
    ArrayList<Shape> expectedCurrentState = new ArrayList<Shape>();
    expectedCurrentState.add(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 100, 50.0, 100.0));
    expectedCurrentState.add(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 200.0, 300.0));
    ArrayList<Shape> actualCurrentState = model1.getState(20);
    boolean same = actualCurrentState.size() == expectedCurrentState.size();
    for (int i = 0; i < actualCurrentState.size(); i++) {
      same = same && expectedCurrentState.get(i).equals(actualCurrentState.get(i))
              && expectedCurrentState.get(i) != actualCurrentState.get(i);
    }
    assertEquals(true, same);
  }

  /**
   * Test that when there are multiple actions effecting different aspects of the shapes, they are
   * all factored into the current state. Also if an action overwrites what another action does that
   * it also shows in the state.
   */
  @Test
  public void testGetCurrentStateMultipleActionsThatEffectSameAspects() {
    model1.addAction(new MoveAction("R", 10, 50, new Point.Double(200, 200),
            new Point.Double(300, 300)));
    model1.addAction(new MoveAction("C", 20, 70, new Point.Double(500, 100),
            new Point.Double(500, 400)));
    model1.addAction(new ChangeColorAction("C", 50, 80,
            new Color(0, 0, 1), new Color(0, 1, 0)));
    model1.addAction(ScaleRectangleAction.createScaleRectangleAction("R", 51,
            70, 50, 100, 25, 100));
    model1.addAction(new MoveAction("R", 70, 100, new Point.Double(300, 300),
            new Point.Double(200, 200)));
    ArrayList<Shape> expectedCurrentState = new ArrayList<Shape>();
    expectedCurrentState.add(Rectangle.createRectangle("R",
            new Point.Double(300 - (100.0 / 3), 300 - (100.0 / 3)),
            new Color(1, 0, 0), 1, 100, 25.0, 100.0));
    expectedCurrentState.add(Oval.createOval("C", new Point.Double(500, 400),
            new Color(0, 1, 0), 6, 100, 60.0, 30.0));
    ArrayList<Shape> actualCurrentState = model1.getState(80);
    boolean same = actualCurrentState.size() == expectedCurrentState.size();
    for (int i = 0; i < actualCurrentState.size(); i++) {
      same = same && expectedCurrentState.get(i).equals(actualCurrentState.get(i))
              && expectedCurrentState.get(i) != actualCurrentState.get(i);
    }
    assertEquals(true, same);
  }
}
