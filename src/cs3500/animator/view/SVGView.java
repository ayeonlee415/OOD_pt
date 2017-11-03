package cs3500.animator.view;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import cs3500.animator.model.AnimationOperations;
import cs3500.animator.model.ColorUtils;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.model.actions.IAction;

/**
 * Class that represents the SVGView of the animation.
 */
public class SVGView implements TextOutputView {

  /**
   * produce a textual description of the animation that is compliant with the popular SVG file
   * format. The SVG file format is an XML-based format that can be used to describe images and
   * animations. It is an example of "vector-based graphics" where it stores explicitly the shapes
   * to be drawn and manipulations to be done on them, instead of pixel values. Most browsers
   * support SVG rendering. Therefore, the output of your program, if saved in a file, can be
   * directly viewed in a web browser. Once again, to facilitate testing, this view should be able
   * to work with a variety of output destinations to which it transmits the SVG description.
   *
   * @param model the model of the animation
   * @param speed the number of ticks per second
   * @param out   the place where the animation should be outputted
   */
  @Override
  public void animate(AnimationOperations model, int speed, Appendable out) {
    String result = this.animateSVG(model, speed);
    try {
      out.append(result);
    } catch (IOException e) {
      throw new IllegalArgumentException("IOException");
    }
  }

  @Override
  public void animate(AnimationOperations model, int speed, BufferedWriter out) {
    String result = this.animateSVG(model, speed);
    try {
      out.write(result);
      out.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("IOException");
    }
  }

  /**
   * Converts all of the information about the shape into properly formatted String for SVG file.
   *
   * @param shape the shape the information is coming from
   * @return the string representing the information of the shape
   */
  private String shapeToSVG(Shape shape) {
    // Rectangle:
    // <rect id="R" x="200" y="200" width="50" height="100" fill="rgb(255,0,0)"
    // visibility="visible" >
    // Oval/Ellipse:
    // <ellipse id="C" cx="500" cy="100" rx="60" ry="30" fill="rgb(0,0,255)" visibility="visible" >
    String result = "<".concat(shape.getSVGType()).concat(" ");
    result = result.concat("id=\"").concat(shape.getName()).concat("\" ");
    result = result.concat(shape.getSVGLocation()).concat(" ");
    result = result.concat(shape.getSVGDimensions()).concat(" ");
    result = result.concat("fill=\"rgb").concat(ColorUtils.colorToString(shape.getColor())).concat(
            "\" ");
    result = result.concat("visibility=\"visible\" >");
    return result.concat("\n").concat("</ ").concat(shape.getSVGType()).concat(">");
  }

  /*
  private String actionToSVG() {
    // <animate attributeType="xml" begin="1000ms" dur="4000ms" attributeName="x"
    // from="200" to="300" fill="freeze" />
    // <animate attributeType="xml" begin="2000.0ms" dur="5000.0ms" attributeName="cx"
    // from="500" to="600" fill="remove" />
    // <animate attributeType="xml" begin="2000.0ms" dur="5000.0ms" attributeName="cy"
    // from="100" to="400" fill="remove" />

    String result = "<animate attributeType=\"xml\" ";
    result = result.concat("begin=\"").concat(
    String.valueOf((double) getStart * 1000)).concat("ms\" ");
    result = result.concat("dur=\"").concat(
    String.valueOf((double) getEnd * 1000)).concat("ms\" ");
    result = result.concat("attributeName=\"").concat(???).concat("\" ");
    result = result.concat("from=\"").concat(???).concat("\" to=\"").concat(???).concat("\" ");
    return result.concat("fill=\"").concat(???).concat("\" \>");
  }
  */

  /**
   * Converts the shapes and actions in the animation into properly formatted String for SVG file.
   *
   * @param model the model of the animation
   * @param speed the number of ticks per second
   * @return the string representing the animation for SVG file
   */
  private String animateSVG(AnimationOperations model, int speed) {
    //1. TODO: get the max width and height of the canvas for svg width and height
    //2. TODO: Convert tick to seconds --> tick speed * 1000
    //3. TODO: list of commands for each shape --> iterate through this
    //         *** Note *** : For this, make sure oval has CX and CY instead of just x and y

    String result = "<svg width=\"700\" height=\"500\" version=\"1.1\"".concat(
            "\nxmlns=\"http://www.w3.org/2000/svg\">\n");

    // use utils to convert ticks into seconds ?

    for (Shape shape : model.getInitState()) {
      result = result.concat(shapeToSVG(shape));
    }
//    for (IAction action : model.getActions()) {
//      result = result.concat(action.printText(speed)).concat("\n");
//    }
    return result.concat("\n </svg>");
  }
}