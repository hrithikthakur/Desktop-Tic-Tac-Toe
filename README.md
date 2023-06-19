# Tic-Tac-Toe with GUI


This is a desktop Tic-Tac-Toe application developed in Java, featuring a graphical user interface (GUI) and an AI opponent. The game utilizes the Java Swing library for the GUI components and implements the Minimax algorithm to provide an intelligent AI opponent. The project also incorporates multithreading to enhance the game's performance and responsiveness.

## Features

- Interactive GUI: The game presents a user-friendly graphical interface where players can easily interact with the game board and make their moves.

- Player vs. AI: The application allows players to play against an AI opponent that utilizes the Minimax algorithm to make intelligent moves.

- Efficient AI: The AI opponent is designed to choose the best possible move by exploring the game tree using the Minimax algorithm. This ensures a challenging gameplay experience for the player.

- Threaded Execution: Multithreading is implemented to separate the AI calculations from the GUI thread. This allows the game to remain responsive even during the AI's decision-making process.

- Object-Oriented Design: The codebase is organized using object-oriented principles to achieve abstraction, stability, and reusability. The design promotes modular components and separation of concerns.

## How to Play

1. Run the application on your desktop by executing the main Java file.

2. The game board will be displayed on the GUI, consisting of a 3x3 grid.

3. The player and the AI opponent take turns making their moves by clicking on an empty cell on the game board.

4. The objective of the game is to get three of your symbols (X or O) in a row, either horizontally, vertically, or diagonally.

5. If the player or the AI achieves this objective, a message will be displayed indicating the winner. If the game ends in a draw, it will be indicated as well.

6. To start a new game, you can click on a "New Game" button or restart the application.

## Dependencies

The Tic-Tac-Toe application has the following dependencies:

- Java 8 or above
- Java Swing library

## Development

The project is developed using Java and the Java Swing library. The Minimax algorithm is employed to create an intelligent AI opponent. Multithreading is utilized to enhance the performance of the game and ensure a responsive user interface.

## Future Improvements

The Tic-Tac-Toe application can be further enhanced in the following ways:

- Implement different difficulty levels for the AI opponent, allowing players to choose the desired challenge.

- Add sound effects and animations to enhance the user experience.

- Implement a score tracking system to keep track of the player's and AI's wins and draws.

- Develop a networked multiplayer mode to enable players to compete against each other over a network.

## License

This Tic-Tac-Toe application is open-source and distributed under the [MIT License](LICENSE). Feel free to modify and use it according to your needs.
