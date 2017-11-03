import org.junit.Before;
import org.junit.Test;

import java.awt.Point;
import java.awt.Color;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationOperations;
import cs3500.animator.model.actions.ChangeColorAction;
import cs3500.animator.model.actions.MoveAction;
import cs3500.animator.model.actions.ScaleOvalAction;
import cs3500.animator.model.actions.ScaleRectangleAction;
import cs3500.animator.model.shapes.Oval;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.view.TextView;

import static junit.framework.TestCase.assertEquals;

/**
 * A Class to test all functions related to the cs3500.animator.model.
 */
public class AnimationModelTest {
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
    modelWithRectAndOval = new AnimationModel();
    modelWithRectAndOval.addShape(Rectangle.createRectangle("R", new Point.Double(200,
                    200), new Color(1, 0, 0), 1, 100,
            50.0, 100.0));
    modelWithRectAndOval.addShape(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
  }

  /**
   * Tests that if there are no shapes or actions that it will just print out the word shapes with a
   * line break.
   */
  @Test
  public void printEmptyAnimationDetails() {
    textView1.animate(model1, 1, out);
    assertEquals("Shapes:\n", out.toString());
  }

  /**
   * Tests that if there is a rectangle and no actions it will print out the information for the
   * rectangle and print a return.
   */
  @Test
  public void printInfoRectangleNoMoves() {
    model1.addShape(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 100, 50.0, 100.0));
    textView1.animate(model1, 1, out);
    assertEquals("Shapes:\nName: R\n"
            + "Type: rectangle\n"
            + "Lower-left corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n"
            + "Appears at t=1.0s\n"
            + "Disappears at t=100.0s\n\n", out.toString());
  }

  /**
   * Test that if there are shapes and no moves that it will print the information for the shapes
   * with returns.
   */
  @Test
  public void pintInfoRectangleOvalNoMoves() {
    model1.addShape(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 100, 50.0, 100.0));
    model1.addShape(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    textView1.animate(model1, 1, out);
    assertEquals("Shapes:\n"
            + "Name: R\n"
            + "Type: rectangle\n"
            + "Lower-left corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n"
            + "Appears at t=1.0s\n"
            + "Disappears at t=100.0s\n"
            + "\n"
            + "Name: C\n"
            + "Type: oval\n"
            + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)\n"
            + "Appears at t=6.0s\n"
            + "Disappears at t=100.0s\n\n", out.toString());
  }

  /**
   * Tests that if there is a move on a rectangle that it will print out the name of the move, the
   * name of the shape it is affecting, the effects it has on the shape, and the time that it takes
   * place.
   */
  @Test
  public void printInfoRectangleOvalMoveRectangle() {
    model1.addShape(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 100, 50.0, 100.0));
    model1.addShape(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    model1.addAction(new MoveAction("R", 10, 50, new Point.Double(200, 200),
            new Point.Double(300, 300)));
    textView1.animate(model1, 1, out);
    assertEquals("Shapes:\n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Lower-left corner: (200.0,200.0), Width: 50.0, Height: 100.0, "
                    + "Color: (1.0,0.0,0.0)\n"
                    + "Appears at t=1.0s\n"
                    + "Disappears at t=100.0s\n"
                    + "\n"
                    + "Name: C\n"
                    + "Type: oval\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, "
                    + "Color: (0.0,0.0,1.0)\n"
                    + "Appears at t=6.0s\n"
                    + "Disappears at t=100.0s\n\n"
                    + "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10.0s to t=50.0s\n",
            out.toString());
  }

  /**
   * Tests that if there are multiple actions that it will print the information for each properly
   * and in order of which one starts to occur first.
   */
  @Test
  public void printInfoRectangleOvalMoveOvalAndRectangleOnce() {
    model1.addShape(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 100, 50.0, 100.0));
    model1.addShape(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    model1.addAction(new MoveAction("R", 10, 50, new Point.Double(200, 200),
            new Point.Double(300, 300)));
    model1.addAction(new MoveAction("C", 20, 70, new Point.Double(500, 100),
            new Point.Double(500, 400)));
    textView1.animate(model1, 1, out);
    assertEquals("Shapes:\n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Lower-left corner: (200.0,200.0), Width: 50.0, Height: 100.0, "
                    + "Color: (1.0,0.0,0.0)\n"
                    + "Appears at t=1.0s\n"
                    + "Disappears at t=100.0s\n"
                    + "\n"
                    + "Name: C\n"
                    + "Type: oval\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, "
                    + "Color: (0.0,0.0,1.0)\n"
                    + "Appears at t=6.0s\n"
                    + "Disappears at t=100.0s\n\n"
                    + "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10.0s to t=50.0s\n"
                    + "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20.0s to t=70.0s\n",
            out.toString());
  }

  /**
   * Testing that changing color works and prints the correct info.
   */
  @Test
  public void printInfoRectangleOvalMoveOvalandRectangleOnceChangeCircleColor() {
    model1.addShape(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 100, 50.0, 100.0));
    model1.addShape(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    model1.addAction(new MoveAction("R", 10, 50, new Point.Double(200, 200),
            new Point.Double(300, 300)));
    model1.addAction(new MoveAction("C", 20, 70, new Point.Double(500, 100),
            new Point.Double(500, 400)));
    model1.addAction(new ChangeColorAction("C", 50, 80,
            new Color(0, 0, 1), new Color(0, 1, 0)));
    textView1.animate(model1, 1, out);
    assertEquals("Shapes:\n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Lower-left corner: (200.0,200.0), Width: 50.0, Height: 100.0, "
                    + "Color: (1.0,0.0,0.0)\n"
                    + "Appears at t=1.0s\n"
                    + "Disappears at t=100.0s\n"
                    + "\n"
                    + "Name: C\n"
                    + "Type: oval\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, "
                    + "Color: (0.0,0.0,1.0)\n"
                    + "Appears at t=6.0s\n"
                    + "Disappears at t=100.0s\n\n"
                    + "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10.0s to t=50.0s\n"
                    + "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20.0s to t=70.0s\n"
                    + "Shape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=50.0s to "
                    + "t=80.0s\n",
            out.toString());
  }

  /**
   * Checks that the example provided on the assignment is achieved with the proper moves and shapes
   * input into the cs3500.animator.model.
   */
  @Test
  public void printInfoFullExampleFromAssignment() {
    model1.addShape(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 100, 50.0, 100.0));
    model1.addShape(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
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
    textView1.animate(model1, 1, out);
    assertEquals("Shapes:\n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Lower-left corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: "
                    + "(1.0,0.0,0.0)\n"
                    + "Appears at t=1.0s\n"
                    + "Disappears at t=100.0s\n"
                    + "\n"
                    + "Name: C\n"
                    + "Type: oval\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: "
                    + "(0.0,0.0,1.0)\n"
                    + "Appears at t=6.0s\n"
                    + "Disappears at t=100.0s\n"
                    + "\n"
                    + "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10.0s to t=50.0s\n"
                    + "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20.0s to t=70.0s\n"
                    + "Shape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=50.0s to "
                    + "t=80.0s\n"
                    + "Shape R scales from Width: 50.0 Height: 100.0 to Width: 25.0 Height: "
                    + "100.0 from t=51.0s to t=70.0s\n"
                    + "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=70.0s to " +
                    "t=100.0s\n",
            out.toString());
  }

  /**
   * Testing that shapes are not allowed to created before time 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidShapeAppearsBefore0() {
    model1.addShape(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), -1, 100, 50, 100));
  }

  /**
   * Testing that a shapes cannot disappear or appear before time 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidShapeDispersantAppearsBefore0() {
    model1.addShape(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), -10, -1, 50, 100));
  }

  /**
   * Testing that a shape cannot disappear before it appears.
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidShapeDisappearsBeforeAppearing() {
    model1.addShape(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 0, 50, 100));
  }

  /**
   * Testing that a rectangle can't be created with a width less than 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidRectangleWidthNotGreaterThan0() {
    model1.addShape(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 0, -50, 100));
  }

  /**
   * Testing that a rectangle can't be created with a height less than 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidRectangleHeightNotGreaterThan0() {
    model1.addShape(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 0, 50, -100));
  }

  /**
   * Testing that a cs3500.animator.model.shapes.Oval can't be created with an xradius less than 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidOvalXRadiusNotGreaterThan0() {
    model1.addShape(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, -60.0, 30.0));
  }

  /**
   * Testing that an oval can't be created with a yradius less than 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidOvalYRadiusNotGreaterThan0() {
    model1.addShape(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, -30.0));
  }

  /**
   * Testing that you can't submit a null location for a oval.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMakingShapeWithNullLocation() {
    model1.addShape(Oval.createOval("C", null,
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
  }

  /**
   * Testing that you can't create a oval with a name that is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMakingShapeWithNullName() {
    model1.addShape(Oval.createOval(null, new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
  }

  /**
   * Testing that you can't create an oval with a null color.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMakingShapeWithNullColor() {
    model1.addShape(Oval.createOval("C", new Point.Double(500, 100),
            null, 6, 100, 60.0, 30.0));
  }

  /**
   * Testing that you can't scale a rectangle so that the width becomes zero.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRectangleScaleWidthToZero() {
    model1.addShape(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 100, 50.0, 100.0));
    model1.addShape(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    model1.addAction(ScaleRectangleAction.createScaleRectangleAction("R", 10, 50,
            50, 100, 0, 100));
    model1.addAction(new MoveAction("C", 20, 70, new Point.Double(500, 100),
            new Point.Double(0, 300)));
  }

  /**
   * Testing that you can't scale a rectangle so that the height becomes zero.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRectangleScaleHeightToZero() {
    model1.addShape(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 100, 50.0, 100.0));
    model1.addShape(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    model1.addAction(ScaleRectangleAction.createScaleRectangleAction("R", 10, 50,
            50, 100, 10, 0));
  }

  /**
   * Testing that you can't scale a rectangle so that the width becomes negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRectangleScaleWidthToNegative() {
    model1.addShape(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 100, 50.0, 100.0));
    model1.addShape(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    model1.addAction(ScaleRectangleAction.createScaleRectangleAction("R", 10, 50,
            50, 100, -51, 100));
  }

  /**
   * Testing that you can't scale a rectangle so that the height is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRectangleScaleHeightToNegative() {
    model1.addShape(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 100, 50.0, 100.0));
    model1.addShape(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    model1.addAction(ScaleRectangleAction.createScaleRectangleAction("R", 10, 50,
            50, 100, 0, -101));
  }

  /**
   * Testing that scaling an oval succeeds.
   */
  @Test
  public void testScaleOval() {
    model1.addShape(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 100, 50.0, 100.0));
    model1.addShape(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    model1.addAction(new MoveAction("R", 10, 50, new Point.Double(200, 200),
            new Point.Double(300, 300)));
    model1.addAction(new MoveAction("C", 20, 70, new Point.Double(500, 100),
            new Point.Double(500, 400)));
    model1.addAction(new ChangeColorAction("C", 50, 80,
            new Color(0, 0, 1), new Color(0, 1, 0)));
    model1.addAction(ScaleOvalAction.createScaleOvalAction("C", 51, 70,
            60, 30, 160, 130));
    model1.addAction(new MoveAction("R", 70, 100, new Point.Double(300, 300),
            new Point.Double(200, 200)));
    textView1.animate(model1, 1, out);
    assertEquals("Shapes:\n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Lower-left corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: "
                    + "(1.0,0.0,0.0)\n"
                    + "Appears at t=1.0s\n"
                    + "Disappears at t=100.0s\n"
                    + "\n"
                    + "Name: C\n"
                    + "Type: oval\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: "
                    + "(0.0,0.0,1.0)\n"
                    + "Appears at t=6.0s\n"
                    + "Disappears at t=100.0s\n"
                    + "\n"
                    + "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10.0s to t=50.0s\n"
                    + "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20.0s to t=70.0s\n"
                    + "Shape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=50.0s to "
                    + "t=80.0s\n"
                    + "Shape C scales from XRadius: 60.0 YRadius: 30.0 to XRadius: 160.0 YRadius: "
                    + "130.0 from t=51.0s "
                    + "to t=70.0s\n"
                    + "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=70.0s to "
                    + "t=100.0s\n",
            out.toString());
  }

  /**
   * Testing that you can't scale a rectangle to make the xRadius 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testScaleOvalXRadiusToZero() {
    model1.addShape(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 100, 50.0, 100.0));
    model1.addShape(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    model1.addAction(new MoveAction("R", 10, 50, new Point.Double(200, 200),
            new Point.Double(100, 100)));
    model1.addAction(new MoveAction("C", 20, 70, new Point.Double(500, 100),
            new Point.Double(0, 300)));
    model1.addAction(new ChangeColorAction("C", 50, 80, new Color(0, 0,
            1), new Color(0, 1, -1)));
    model1.addAction(ScaleOvalAction.createScaleOvalAction("C", 51, 70,
            500, 100, -60, 100));
  }

  /**
   * Testing that you can't scale an oval to a yRadius of 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testScaleOvalYRadiusToZero() {
    model1.addShape(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 100, 50.0, 100.0));
    model1.addShape(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    model1.addAction(new MoveAction("R", 10, 50, new Point.Double(200, 200),
            new Point.Double(100, 100)));
    model1.addAction(new MoveAction("C", 20, 70, new Point.Double(500, 100),
            new Point.Double(0, 300)));
    model1.addAction(new ChangeColorAction("C", 50, 80, new Color(0, 0,
            1), new Color(0, 1, -1)));
    model1.addAction(ScaleOvalAction.createScaleOvalAction("C", 51, 70,
            60, 30, 0, -30));
  }

  /**
   * Testing that you can't scale an oval to a xRadius that is less than zero.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testScaleOvalXRadiusToNegative() {
    modelWithRectAndOval.addAction(ScaleOvalAction.createScaleOvalAction("C", 51,
            70, 100, 100, -61, 100));
  }

  /**
   * Testing that you can't scale an oval so that the yRadius is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testScaleOvalYRadiusToNegative() {
    modelWithRectAndOval.addAction(ScaleOvalAction.createScaleOvalAction("C", 51,
            70, 100, 100, 0, -31));
  }


  /**
   * Testing that you can't scale the same oval in during the same period of time.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testScaleOvalDuringSamePeriod() {
    modelWithRectAndOval.addAction(ScaleOvalAction.createScaleOvalAction("C", 10,
            50, 100, 100, 10, 10));
    modelWithRectAndOval.addAction(ScaleOvalAction.createScaleOvalAction("C", 40,
            60, 100, 100, 10, 10));
  }

  /**
   * Testing that you can scale and move an oval at the same time.
   */
  @Test
  public void testScaleOvalAndMoveOvalDuringSamePeriod() {
    model1.addShape(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    model1.addAction(ScaleOvalAction.createScaleOvalAction("C", 10, 50,
            60, 30, 160, 130));
    model1.addAction(new MoveAction("C", 40, 60, new Point.Double(500, 100),
            new Point.Double(510, 110)));
    textView1.animate(model1, 1, out);
    assertEquals("Shapes:\nName: C\n"
                    + "Type: oval\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, "
                    + "Color: (0.0,0.0,1.0)\n"
                    + "Appears at t=6.0s\n"
                    + "Disappears at t=100.0s\n"
                    + "\n"
                    + "Shape C scales from XRadius: 60.0 YRadius: 30.0 to XRadius: 160.0 "
                    + "YRadius: 130.0 from t=10.0s to t=50.0s\n"
                    + "Shape C moves from (500.0,100.0) to (510.0,110.0) from t=40.0s to t=60.0s\n",
            out.toString());
  }

  /**
   * Testing that you can't scale the same rectangle twice during the same period of time.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testScaleRectangleDuringSamePeriod() {
    model1.addShape(Rectangle.createRectangle("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    model1.addAction(ScaleRectangleAction.createScaleRectangleAction("R", 10, 50,
            100, 100, 100, 100));
    model1.addAction(ScaleRectangleAction.createScaleRectangleAction("R", 40, 60,
            100, 100, 10, 10));
  }

  /**
   * Tests that you can change the color of a rectangle.
   */
  @Test
  public void testChangeColorOfRectanlge() {
    model1.addShape(Rectangle.createRectangle("R", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    model1.addAction(new ChangeColorAction("R", 10, 50, new Color(0, 0,
            1), new Color(10, 10, 11)));
    textView1.animate(model1, 1, out);
    assertEquals("Shapes:\n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Lower-left corner: (500.0,100.0), Width: 60.0, Height: 30.0, "
                    + "Color: (0.0,0.0,1.0)\n"
                    + "Appears at t=6.0s\n"
                    + "Disappears at t=100.0s\n"
                    + "\n"
                    + "Shape R changes color from (0.0,0.0,1.0) to (10.0,10.0,11.0) from "
                    + "t=10.0s to t=50.0s\n",
            out.toString());
  }

  /**
   * Testing that you can't change the color of a shape twice in the same period.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorTwiceSameTime() {
    model1.addShape(Rectangle.createRectangle("R", new Point.Double(500, 200),
            new Color(0, 0, 0), 0, 10, 10, 10));
    model1.addAction(new ChangeColorAction("R", 10, 100,
            new Color(0, 0, 0), new Color(10, 10, 10)));
    model1.addAction(new ChangeColorAction("R", 50, 120,
            new Color(0, 0, 0), new Color(10, 10, 10)));
  }

  /**
   * Testing that instantaneous change will be properly converted to total change.
   */
  @Test
  public void testInstantChangeConvertsToTotalChange() {
    model1.addShape(Rectangle.createRectangle("R", new Point.Double(500, 200),
            new Color(0, 0, 0), 0, 10, 10, 10));
    model1.addAction(new ChangeColorAction("R", 0, 10,
            new Color(0, 0, 0), new Color(10, 10, 10)));
    textView1.animate(model1, 1, out);
    assertEquals("Shapes:\n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Lower-left corner: (500.0,200.0), Width: 10.0, Height: 10.0, Color: "
                    + "(0.0,0.0,0.0)\n"
                    + "Appears at t=0.0s\n"
                    + "Disappears at t=10.0s\n"
                    + "\n"
                    + "Shape R changes color from (0.0,0.0,0.0) to (10.0,10.0,10.0) from "
                    + "t=0.0s to t=10.0s\n",
            out.toString());
  }

  /**
   * Test that you cannot move the same shape in an overlapping period of time.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCantMoveSameShapeInOverlappingTime() {
    model1.addShape(Rectangle.createRectangle("R", new Point.Double(500, 200),
            new Color(0, 0, 0), 0, 10, 10, 10));
    model1.addAction(new MoveAction("R", 0, 100, new Point.Double(500, 200),
            new Point.Double(10, 10)));
    model1.addAction(new MoveAction("R", 50, 150,
            new Point.Double(500, 200), new Point.Double(10, 10)));
  }

  /**
   * Test that you cannot call scaleRectangle on a oval.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNoScaleRectangleOnOval() {
    model1.addShape(Oval.createOval("C", new Point.Double(500, 200),
            new Color(0, 0, 0), 0, 10, 10, 10));
    model1.addAction(ScaleRectangleAction.createScaleRectangleAction("C", 0,
            100, 100, 100, 1, 1));
    textView1.animate(model1, 1, out);
  }

  /**
   * Test that you cannot call print affected parts of a scaled oval on a rectangle.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNoScaleOvalOnRectangle() {
    model1.addShape(Rectangle.createRectangle("R", new Point.Double(500, 200),
            new Color(0, 0, 0), 0, 10, 10, 10));
    model1.addAction(ScaleOvalAction.createScaleOvalAction("R", 0, 100,
            100, 100, 1, 1));
  }

  /**
   * Test that you cannot create a shape with the same name as another shape in the animation.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNoShapeWithSameName() {
    model1.addShape(Rectangle.createRectangle("R", new Point.Double(500, 200),
            new Color(0, 0, 0), 0, 10, 10, 10));
    model1.addShape(Oval.createOval("R", new Point.Double(500, 200),
            new Color(0, 0, 0), 0, 10, 10, 10));
  }

  /**
   * Test that the model throws an error if you try to create an action on a shape name that doesn't
   * exist in the model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testErrorShapeDoesntExist() {
    model1.addShape(Rectangle.createRectangle("R", new Point.Double(500, 200),
            new Color(0, 0, 0), 0, 10, 10, 10));
    model1.addAction(ScaleOvalAction.createScaleOvalAction("O", 0, 100,
            100, 100, 1, 1));
  }

  /**
   * Check that you cannot create a shape with the same name regardless of case.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNoShapeWithSameNameCaseInsensitive() {
    model1.addShape(Rectangle.createRectangle("R", new Point.Double(500, 200),
            new Color(0, 0, 0), 0, 10, 10, 10));
    model1.addShape(Oval.createOval("r", new Point.Double(500, 200),
            new Color(0, 0, 0), 0, 10, 10, 10));
  }


  /**
   * Check that if you use a different case it still creates the action of the shape.
   */
  @Test
  public void testShapeNameCaseDoesntMatter() {
    model1.addShape(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 100, 50.0, 100.0));
    model1.addShape(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
    model1.addAction(new MoveAction("R", 10, 50, new Point.Double(200, 200),
            new Point.Double(300, 300)));
    model1.addAction(new MoveAction("C", 20, 70, new Point.Double(500, 100),
            new Point.Double(500, 400)));
    model1.addAction(new ChangeColorAction("C", 50, 80,
            new Color(0, 0, 1), new Color(0, 1, 0)));
    model1.addAction(new ScaleRectangleAction("R", 51, 70, 50,
            100, 25, 100));
    model1.addAction(new MoveAction("R", 70, 100, new Point.Double(300, 300),
            new Point.Double(200, 200)));
    textView1.animate(model1, 1, out);
    assertEquals("Shapes:\n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Lower-left corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: "
                    + "(1.0,0.0,0.0)\n"
                    + "Appears at t=1.0s\n"
                    + "Disappears at t=100.0s\n"
                    + "\n"
                    + "Name: C\n"
                    + "Type: oval\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: "
                    + "(0.0,0.0,1.0)\n"
                    + "Appears at t=6.0s\n"
                    + "Disappears at t=100.0s\n"
                    + "\n"
                    + "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10.0s to t=50.0s\n"
                    + "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20.0s to t=70.0s\n"
                    + "Shape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=50.0s to "
                    + "t=80.0s\n"
                    + "Shape R scales from Width: 50.0 Height: 100.0 to Width: 25.0 Height: "
                    + "100.0 from t=51.0s "
                    + "to t=70.0s\n"
                    + "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=70.0s to "
                    + "t=100.0s\n",
            out.toString());
  }

  /**
   * Checks that the example from the previous week works at 2 ticks per second.
   */
  @Test
  public void testFullExampleTwoTicksPerSecond() {
    model1.addShape(Rectangle.createRectangle("R", new Point.Double(200, 200),
            new Color(1, 0, 0), 1, 100, 50.0, 100.0));
    model1.addShape(Oval.createOval("C", new Point.Double(500, 100),
            new Color(0, 0, 1), 6, 100, 60.0, 30.0));
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
    textView1.animate(model1, 2, out);
    assertEquals("Shapes:\n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Lower-left corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: "
                    + "(1.0,0.0,0.0)\n"
                    + "Appears at t=0.5s\n"
                    + "Disappears at t=50.0s\n"
                    + "\n"
                    + "Name: C\n"
                    + "Type: oval\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: "
                    + "(0.0,0.0,1.0)\n"
                    + "Appears at t=3.0s\n"
                    + "Disappears at t=50.0s\n"
                    + "\n"
                    + "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=5.0s to t=25.0s\n"
                    + "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=10.0s to t=35.0s\n"
                    + "Shape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=25.0s to "
                    + "t=40.0s\n"
                    + "Shape R scales from Width: 50.0 Height: 100.0 to Width: 25.0 Height: 100.0 "
                    + "from t=25.5s to t=35.0s\n"
                    + "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=35.0s to t=50.0s\n",
            out.toString());
  }
}