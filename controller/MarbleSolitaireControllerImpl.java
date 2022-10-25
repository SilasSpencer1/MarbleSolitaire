package cs3500.marblesolitaire.controller;


import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;


/**
 * Implementation of a marble solitaire controller. Controls a game of Marble Solitaire.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  protected PrintStream out;
  protected Readable rd;
  protected MarbleSolitaireModel model;
  protected MarbleSolitaireView view;
  protected Appendable ap;

  /**
   * Constructor taking Readable and Appendable objects to handle input and apput.
   *
   * @param rd To handle inputs coming from the user.
   * @throws IllegalArgumentException iff either of the arguments supplied to the controller are
   *                                  null.
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model,
                                       MarbleSolitaireView view, Readable rd)
          throws IllegalArgumentException {
    if (model == null || view == null || rd == null) {
      throw new IllegalArgumentException();
    }
    this.model = model;
    this.view = view;
    this.rd = rd;
  }

  public MarbleSolitaireControllerImpl(InputStreamReader rd, PrintStream out) {
    this.rd = rd;
    this.out = out;
  }


  @Override
  public void playGame() throws IllegalArgumentException, IllegalStateException {
    playGame(model);
  }

  @Override
  public void playGame(MarbleSolitaireModel model)
          throws IllegalArgumentException, IllegalStateException {
    Scanner scan = new Scanner(this.rd);
    // todo stringbuilder
    // todo processing inputs error
    try {
      while (!model.isGameOver()) {
        if (!scan.hasNext()) {
          throw new IllegalStateException("Readable out of arguments.");
        }
        // Part a
        this.appendGameState(model);
        // Part b
        this.appendScore(model);
        // Part c (Boolean flag -> false if quit)
        if (!this.dispatchInput(model, scan)) {
          String toAppend = "Score: " + model.getScore();
          this.tryAppend("Game quit!\nState of game when quit:\n",
                  "Couldn't append end game information.");
          this.appendGameState(model);
          this.tryAppend(toAppend, "Couldn't append end game information.");
          return;
        }
      }
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("Received null model.");
    }
    // Part d
    this.tryAppend("Game over!\n", "Couldn't append game over information.");
    this.appendGameState(model);
    this.appendScore(model);
  }

  /**
   * Appends current game state (from model) to the appendable.
   *
   * @param model The model that is being used for the game.
   * @throws IllegalStateException If the game state can't be appended to the appendable.
   */
  private void appendGameState(MarbleSolitaireModel model) {
    this.tryAppend(model.toString() + "\n", "Couldn't append game state to appendable.");
  }

  /**
   * Appends current score of game to model.
   *
   * @param model The model that is being used for the game
   * @throws IllegalStateException If the score can't be appended to the appendable.
   */
  private void appendScore(MarbleSolitaireModel model) {
    String toAppend = "Score: " + model.getScore() + "\n";
    this.tryAppend(toAppend, "Couldn't append score to appendable.");
  }

  /**
   * Makes the next move specified by the user to the given model. This method utilizes a boolean
   * flag to determine if the game has been quit by the user.
   *
   * @param model to represent the model of the game to act on.
   * @param scan  a scanner, which represents desired move data.
   * @returns true if the game is ongoing, false if the game has been quit.
   */
  private boolean dispatchInput(MarbleSolitaireModel model, Scanner scan) {
    int validInputs = 0;
    // To represent a move: {fromRow, fromCol, toRow, toCol}
    int[] moveInfo = new int[]{0, 0, 0, 0};
    while (scan.hasNext() && validInputs < 4) {
      String next = scan.next();
      int intNext;
      // Check if user has pressed Q/q
      // todo read above
      try {
        // Try to convert next to be an integer
        intNext = Integer.valueOf(next);
      } catch (NumberFormatException e) {
        // If it can't be converted to an integer, interpret it as a string
        return this.interpretStringInput(next);
      }
      // At this point we know intNext is an int
      if (this.validateIntInput(intNext)) {
        if (validInputs <= 3) {
          moveInfo[validInputs] = intNext - 1;
          validInputs++;
        }
        if (validInputs == 4) {
          try {
            model.move(moveInfo[0], moveInfo[1], moveInfo[2], moveInfo[3]);
          } catch (IllegalArgumentException e) {
            this.tryAppend("Invalid move. Play again. " + e.getMessage() + "\n",
                    "Couldn't prompt user to re-enter move.");
            validInputs = 0;
          }
        }
      }
    }
    return true;
  }

  /**
   * Interprets a string input from the user.
   *
   * @param next to represent the next string input by the user.
   * @return true if the game is ongoing, false if the game has been quit.
   */
  private boolean interpretStringInput(String next) {
    if (next.equals("Q") || next.equals("q")) {
      return false;
    } else {
      this.tryAppend("Please re-enter that move\n", "Couldn't prompt user to re-enter move.");
      return true;
    }
  }

  /**
   * Interprets an integer input from the user.
   *
   * @param next to represent the next int input by the user.
   * @returns true if input is valid, false otherwise.
   */
  private boolean validateIntInput(int next) {
    if (next < 0) {
      this.tryAppend("Please re-enter that move\n", "Couldn't prompt user to re-enter move.");
      return false;
    } else {
      return true;
    }
  }

  /**
   * Tries to append a message to the appendable. Gives a specific error message if append fails.
   *
   * @param msg      String desired to append to the appendable.
   * @param errorMsg Desired error message to be returned.
   * @throws IllegalStateException if the String can't be added to the appendable.
   */
  private void tryAppend(String msg, String errorMsg) {
    try {
      this.ap.append(msg);
    } catch (IOException e) {
      throw new IllegalStateException(errorMsg);
    }
  }
}