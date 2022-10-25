package cs3500.marblesolitaire.model.hw02;

import java.util.ArrayList;

/**
 * This class represents a concrete implementation of the marble solitaire model, representing a
 * game of marble solitaire.
 */

public class EnglishSolitaireModel extends AbstractMethods {

  /**
   * Zero parameter constructor for the class.
   */

  public EnglishSolitaireModel() {
    this(3,3,3);
  }

  /**
   * Constructor to specify starting marble position for the marble in a model with default arm
   * thickness a = 3.
   *
   * @param    sRow Starting row for marble.
   * @param    sCol Starting column for marble.
   * @throws   IllegalArgumentException if sRow and sCol correspond to an invalid starting position
   */
  public EnglishSolitaireModel(int sRow, int sCol) {
    this(3, sRow, sCol);
  }

  /**
   * Constructor to instantiate a EnglishSolitaireModel with only the arm thickness specified.
   *
   * @param    a to represent the arm thickness.
   * @throws   IllegalArgumentException if a is not a positive odd number.
   */
  public EnglishSolitaireModel(int a) {
    this(a, 3 * a / 2 - 1, 3 * a / 2 - 1);
  }

  /**
   * Zero parameter constructor for the class.
   *
   * @param    a to represent the arm thickness.
   * @param    sRow Starting row for marble.
   * @param    sCol Starting column for marble.
   * @throws   IllegalArgumentException if the arm thickness is not a positive odd number, or the
   *           empty cell position is invalid.
   */
  public EnglishSolitaireModel(int a, int sRow, int sCol) {
    if (a < 1 || a % 2 == 0) {
      throw new IllegalArgumentException("Bad Thickness");
    }
    for (int rowI = 0; rowI < (3 * a) - 2; rowI++) {
      ArrayList<SlotState> rowTemp = new ArrayList<SlotState>();
      for (int colI = 0; colI < (3 * a) - 2; colI++) {
        if ((a - 2 < rowI && rowI < 2 * a - 1) ||
                (a - 2 < colI && colI < 2 * a - 1)) {
          rowTemp.add(SlotState.Marble);
        }
        else {
          rowTemp.add(SlotState.Invalid);
        }
      }
      board.add(rowTemp);
    }
    try {
      if (board.get(sRow).get(sCol).equals(SlotState.Marble)) {
        board.get(sRow).set(sCol, SlotState.Empty);
      }
      else {
        // If empty slot is in board, but not at a marble,
        throw new IllegalArgumentException("Bad marble");
      }
    }
    catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Empty slot not at a valid position");
    }
  }

}
