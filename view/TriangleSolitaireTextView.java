package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;


import java.io.IOException;
import java.util.Scanner;

/**
 * To properly implement viewing the game.
 */


public class TriangleSolitaireTextView implements MarbleSolitaireView {

  /**
   * displaying the game.
   *
   * @param mS modelState
   * @throws IllegalArgumentException when modelState is null.
   */
  MarbleSolitaireModelState ms;
  Appendable appendable;
  MarbleSolitaireModel model;

  /**
   * to rep. a model state with strings
   *
   * @param ms MODEL STATE
   * @throws IllegalArgumentException when its null
   */

  public TriangleSolitaireTextView(MarbleSolitaireModelState ms)
          throws IllegalArgumentException {
    if (ms == null) {
      throw new IllegalArgumentException("state is null");
    }
    this.ms = ms;
  }

  /**
   * to rep text view with a model state and now an appendable.
   * @param ms MODEL STATE
   * @param appendable APPENDABLE
   * @throws IllegalArgumentException when either are null.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState ms, Appendable appendable)
          throws IllegalArgumentException {

    if (ms == null || appendable == null) {
      throw new IllegalArgumentException("null model or appendable");
    }
    this.ms = ms;
    this.appendable = appendable;

  }


  @Override
  public void renderBoard() throws IOException {
    // ignore to do later
  }

  @Override
  public void renderMessage(String message) throws IOException {
    Scanner input = new Scanner(message);
    while (input.hasNextLine()) {
      String message1 = input.nextLine();
    }
    input.close();
  }

  @Override
  public Appendable getAppendable() {
    return this.appendable;
  }

  @Override
  public String toString() {
    return model.toString();
  }
}
