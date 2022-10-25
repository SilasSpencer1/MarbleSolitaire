package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;

import cs3500.marblesolitaire.model.hw02.AbstractMethods;
import cs3500.marblesolitaire.model.hw02.SlotState;

/**
 * This class represents a concrete implementation of the marble solitaire model, representing a
 * game of European marble solitaire.
 */

public class EuropeanSolitaireModel extends AbstractMethods {

  /**
   * Zero parameter constructor for the class.
   */
  public EuropeanSolitaireModel() {
    this(3, 3, 3);
  }

  /**
   * Constructor to specify starting marble position for the empty slot in a model with default arm
   * thickness a = 5.
   *
   * @param    sRow Starting row for empty spot.
   * @param    sCol Starting column for empty spot.
   * @throws   IllegalArgumentException if sRow and sCol correspond to an invalid starting position
   */
  public EuropeanSolitaireModel(int sRow, int sCol) {
    this(3, sRow, sCol);
  }

  /**
   * Constructor to instantiate a EuropeanSolitaireModel with only the arm thickness specified.
   *
   * @param    a to represent the arm thickness.
   * @throws   IllegalArgumentException if a is not a positive odd number.
   */
  public EuropeanSolitaireModel(int a) {
    this(a, (3 * a) / 2 - 1, (3 * a) / 2 - 1);
  }

  /**
   * Zero parameter constructor for the class.
   *
   * @param    a to represent the arm thickness.
   * @param    sRow Starting row for empty spot.
   * @param    sCol Starting column for empty spot.
   * @throws   IllegalArgumentException if the arm thickness is not a positive odd number, or the
   *           empty cell position is invalid.
   */
  public EuropeanSolitaireModel(int a, int sRow, int sCol) {
    if (a < 1 || a % 2 == 0) {
      throw new IllegalArgumentException(String.format("Bad Thickness: %d", a));
    }
    for (int rowIndex = 0; rowIndex < (3 * a) - 2; rowIndex++) {
      ArrayList<SlotState> rowTemp = new ArrayList<SlotState>();
      for (int colIndex = 0; colIndex < ((3 * a) - 2) / 2; colIndex++) {
        if (rowIndex >= a - 1 - colIndex && rowIndex <= 2 * a - 2 + colIndex) {
          rowTemp.add(SlotState.Marble);
        }
        else {
          rowTemp.add(SlotState.Invalid);
        }
      }
      // For the right half of the board:
      for (int colIndex = ((3 * a) - 2) / 2; colIndex < (3 * a) - 2; colIndex++) {
        if (rowIndex >= - 2 * a + 2 + colIndex && rowIndex <= 5 * a - 5 - colIndex) {
          rowTemp.add(SlotState.Marble);
        }
        else {
          rowTemp.add(SlotState.Invalid);
        }
      }
      board.add(rowTemp);
    }
    if (!board.get(sRow).get(sCol).equals(SlotState.Marble)) {
      throw new IllegalArgumentException("Bad Starting position given");
    }
    board.get(sRow).set(sCol, SlotState.Empty);
  }


}
