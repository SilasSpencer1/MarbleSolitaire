package cs3500.marblesolitaire.model.hw02;

/**
 * This enum represents the two different states a square can have in the MarbleSolitaireImpl.
 * Empty - Not containing a marble, but in the play area
 * Marble - A square containing a marble
 * Invalid - A square outside of the play area
 */
public enum SlotState {
  Empty {
    @Override
    public final String toString() {
      return "_";
    }
  },
  Marble {
    @Override
    public final String toString() {
      return "O";
    }
  },
  Invalid {
    @Override
    public final String toString() {
      return " ";
    }
  }
}
