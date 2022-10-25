package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;

import cs3500.marblesolitaire.model.hw02.AbstractMethods;

import cs3500.marblesolitaire.model.hw02.SlotState;

/**
 * This class represents a concrete implementation of the marble solitaire model, representing a
 * game of Triangle marble solitaire. Model version allows moves diagonally.
 */
public class TriangleSolitaireModel extends AbstractMethods {
  /**
   * Zero parameter constructor for the class.
   */
  public TriangleSolitaireModel() {
    this(5, 0, 0);
  }

  
  /**
   * Constructor to specify starting marble position for the empty slot in a model with default base
   * thickness a = 5.
   *
   * @param sRow Starting row for empty spot.
   * @param sCol Starting column for empty spot.
   * @throws IllegalArgumentException if sRow and sCol correspond to an invalid starting position
   */
  public TriangleSolitaireModel(int sRow, int sCol) {
    this(5, sRow, sCol);
  }

  /**
   * Constructor to instantiate a TriangleSolitaireModel with only the base thickness specified.
   *
   * @param a to represent the base thickness.
   * @throws IllegalArgumentException if a is not a positive number.18
   */
  public TriangleSolitaireModel(int a) {
    this(a, 0, 0);
  }

  /**
   * Zero parameter constructor for the class.
   *
   * @param a    to represent the base thickness.
   * @param sRow Starting row for empty spot.
   * @param sCol Starting column for empty spot.
   * @throws IllegalArgumentException if the base thickness is not a positive number, or the
   *                                  empty cell position is invalid.
   */
  public TriangleSolitaireModel(int a, int sRow, int sCol) {
    if (a < 1) {
      throw new IllegalArgumentException("Bad base thickness");
    }
    for (int i = 0; i < a; i++) {
      ArrayList<SlotState> rowTemp = new ArrayList<SlotState>();
      for (int j = 0; j < i + 1; j++) {
        rowTemp.add(SlotState.Marble);
      }
      for (int j = i + 1; j < a; j++) {
        rowTemp.add(SlotState.Invalid);
      }
      this.board.add(rowTemp);
    }
    try {
      if (board.get(sRow).get(sCol).equals(SlotState.Marble)) {
        board.get(sRow).set(sCol, SlotState.Empty);
      } else {
        // If empty slot is in board, but not at a marble,
        throw new IndexOutOfBoundsException("Marble has an invalid position; error.");
      }
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("The position of the empty space is invalid");
    }
  }

  protected boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    int size = board.size();
    boolean fromMarble;
    boolean toEmpty;
    boolean jumped;
    boolean positiveArea = (fromRow >= 0 && fromCol >= 0) && (toRow >= 0 && toCol >= 0);
    boolean posnsInScope = (fromRow < size && fromCol < size) && (toRow < size && toCol < size);
    boolean sameRowTwocols = fromRow == toRow && Math.abs(fromCol - toCol) == 2;
    boolean twoRowsOnDiagonal = (Math.abs(fromCol - toCol) == 0 || Math.abs(fromCol - toCol) == 2)
            && (Math.abs(fromRow - toRow) == 2);
    try {
      int jumpedRow = (toRow + fromRow) / 2;
      int jumpedCol = (toCol + fromCol) / 2;
      fromMarble = board.get(fromRow).get(fromCol).equals(SlotState.Marble);
      toEmpty = board.get(toRow).get(toCol).equals(SlotState.Empty);
      jumped = board.get(jumpedRow).get(jumpedCol).equals(SlotState.Marble);
    }
    catch (IndexOutOfBoundsException e) {
      return false;
    }
    return positiveArea && posnsInScope && (sameRowTwocols || twoRowsOnDiagonal)
            && fromMarble && toEmpty && jumped;
  }


   boolean hasValidMove(int row, int col) {
    if (board.get(row).get(col) != SlotState.Marble) {
      throw new IllegalArgumentException("hasValidMove not given marble location");
    }
    return this.isValidMove(row, col, row, col + 2)
                    || this.isValidMove(row, col, row, col - 2)
                    || this.isValidMove(row, col, row + 2, col + 2)
                    || this.isValidMove(row, col, row - 2, col - 2)
                    || this.isValidMove(row, col, row + 2, col)
                    || this.isValidMove(row, col, row - 2, col);
  }


  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (fromRow == toRow && toCol == fromCol) {
      throw new IllegalArgumentException();
    }
    if (Math.abs(fromRow - toRow) > 2 || Math.abs(fromCol - toCol) > 2) {
      throw new IllegalArgumentException();
    }
    if (!(isValidMove(fromRow, fromCol, toRow, toCol))) {
      throw new IllegalArgumentException();
    }
    int jumpedR = (fromRow + toRow) / 2;
    int jumpedC = (fromCol + toCol) / 2;
    board.get(fromRow).set(fromCol, SlotState.Empty);
    board.get(jumpedR).set(jumpedC, SlotState.Empty);
    this.addMarble(toRow, toCol);
  }
}
