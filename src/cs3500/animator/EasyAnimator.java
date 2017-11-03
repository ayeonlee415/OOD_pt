package cs3500.animator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.soap.Text;

import cs3500.animator.model.AnimationOperations;
import cs3500.animator.utils.AnimationFileReader;
import cs3500.animator.utils.ModelBuilder;
import cs3500.animator.view.GraphicalView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextOutputView;
import cs3500.animator.view.TextView;
import cs3500.animator.view.VisualView;

/**
 * A final class that represents the Easy Animator which plays out the animation.
 */
public final class EasyAnimator {
  public static void main(String[] args) throws InterruptedException, IOException {
    String inputFile = null;
    GraphicalView GView = null;
    TextOutputView TView = null;
    int speed = 1;
    BufferedWriter buff = null;
    Appendable out = System.out;
    for (int i = 0; i < args.length; i += 2) {
      switch (args[i]) {
        case "-if":
          inputFile = args[i + 1];
          break;
        case "-iv":
          switch (args[i + 1]) {
            case "text":
              TView = new TextView();
              break;
            case "visual":
              GView = new VisualView();
              break;
            case "svg":
              TView = new SVGView();
              break;
            default:
              throw new IllegalArgumentException("Invalid type of view");
          }
          break;
        case "-speed":
          speed = Integer.parseInt(args[i + 1]);
          break;
        case "-o":
          buff = new BufferedWriter(new FileWriter(args[i + 1]));
          break;
        default:
          throw new IllegalArgumentException("Invalid command");
      }
    }
    if (inputFile == null) {
      throw new IllegalArgumentException("No input file set");
    }
    if (GView == null && TView == null) {
      throw new IllegalArgumentException("No view selected");
    }
    AnimationOperations model = new AnimationFileReader()
            .readFile(inputFile, new ModelBuilder());
    if (GView != null) {
      GView.animate(model, speed);
    } else {
      if (buff == null) {
        TView.animate(model, speed, out);
      } else {
        TView.animate(model, speed, buff);
      }
    }
  }
}
