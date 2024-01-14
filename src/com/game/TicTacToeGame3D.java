package com.game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;

//main class
public class TicTacToeGame3D extends JFrame implements ActionListener {
	
	//variable declarations
	int[] winningSequence = new int[4];
	TicTacToeClick[] winningSequenceButtons = new TicTacToeClick[4];
	public char humanMove = 'X';
	public char computerMove = 'O';

	Font buttonFont = new Font("Arial", Font.PLAIN, 16);
	private char gameConfiguration[][][]; 
	private TicTacToeClick[][][] gameBoardButtons;
	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel buttonPanel;
	private JPanel textPanel;
	private JPanel textPanel1;
	private JPanel textPanel2;
	private JButton startNewGameButton;
	private JButton clearScoresNewGameButton;
	private JLabel gameStatus;
	private JLabel gameScore;
	private JLabel infoLabel;
	private JRadioButton radioButtonO;
	private JRadioButton radioButtonX;
	private JRadioButton computerFirstRadBtn;
	private JRadioButton humanFirstRadBtn;
	private JRadioButton easyBtn;
	private JRadioButton difficultBtn;
	private JRadioButton insaneBtn;
	private ButtonGroup xoSelect;
	private EveryMoveListener xoListener;
	private ButtonGroup defaultSelection;
	private ButtonGroup difficultyLevelGroup;
	private boolean isHumanFirst = true;
	private int gameDifficulty = 1;
	private int totalLooksAhead = 2;
	private int lookAheadCountValue = 0;
	private int humanPlayerScore = 0;
	private int computerPlayerScore = 0;
	public boolean isGameWon = false;

	public class Move {
		int board;
		int row;
		int column;
	}

	private class TicTacToeClick extends JButton {
		public int sqaureboxRow;
		public int sqaureboxColumn;
		public int sqaureboxBoard;
	}

	public TicTacToeGame3D() {
		super("TIC TAC TOE 3D View GAME"); // Title
		setSize(800, 800); // window size
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // To close the game while clicking on the close button
		initializeBoard(); // user defined method
		setResizable(false); // this prevents the user from resizing the window
		setLocationRelativeTo(null); // This makes sure that the window is in the center of the screen
		setVisible(true); // To make the window visible
	}
	
	//To draw the 3D view of the Tic Tac Toe Game
	public class GamePanel extends JPanel {
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D graphics2d = (Graphics2D) g;
			graphics2d.setStroke(new BasicStroke(3));
			int value = 50;

			// Level 0
			graphics2d.setColor(Color.CYAN);
			// L
			graphics2d.drawLine(110, 60, 35, 210);
			// T
			graphics2d.drawLine(110, 60, 315, 60);
			// R
			graphics2d.drawLine(315, 60, 240, 215);
			// B
			graphics2d.drawLine(240, 215, 35, 213);

			graphics2d.drawLine(value + 50, 95, value + 250, 95);
			graphics2d.drawLine(value + 25, 137, value + 225, 137);
			graphics2d.drawLine(value + 10, 179, value + 210, 179);
			graphics2d.drawLine(value + 110, 60, value + 35, 210);
			graphics2d.drawLine(value + 160, 60, value + 85, 210);
			graphics2d.drawLine(value + 210, 60, value + 135, 210);

			// Level 1
			// L
			// graphics2d.drawLine(value + 35, 210, value + 10, 354);
			graphics2d.drawLine(value + 60, 240, 35, 384);
			graphics2d.drawLine(value + 50, 270, value + 250, 270);
			graphics2d.drawLine(value + 25, 312, value + 225, 312);
			graphics2d.drawLine(value + 10, 354, value + 210, 354);
			graphics2d.drawLine(value + 120, 240, value + 45, 384);
			graphics2d.drawLine(value + 170, 240, value + 95, 384);
			graphics2d.drawLine(value + 220, 240, value + 145, 384);

			// L
			graphics2d.drawLine(110, 240, 35, 384);
			// T
			graphics2d.drawLine(110, 240, 315, 240);
			// R
			graphics2d.drawLine(315, 240, 240, 385);
			// B
			graphics2d.drawLine(240, 385, 35, 385);

			// Level 2
			graphics2d.drawLine(value + 50, 445, value + 250, 445);
			graphics2d.drawLine(value + 25, 487, value + 225, 487);
			graphics2d.drawLine(value + 10, 529, value + 210, 529);
			graphics2d.drawLine(value + 120, 410, value + 45, 562);
			graphics2d.drawLine(value + 170, 410, value + 95, 562);
			graphics2d.drawLine(value + 220, 410, value + 145, 562);

			// L
			graphics2d.drawLine(105, 415, 40, 562);
			// T
			graphics2d.drawLine(110, 410, 315, 410);
			// R
			graphics2d.drawLine(315, 410, 240, 565);
			// B
			graphics2d.drawLine(240, 565, 45, 563);

			// Level 3
			graphics2d.drawLine(value + 50, 622, value + 250, 622);
			graphics2d.drawLine(value + 25, 664, value + 225, 664);
			graphics2d.drawLine(value + 10, 706, value + 210, 706);
			graphics2d.drawLine(value + 120, 590, value + 45, 740);
			graphics2d.drawLine(value + 170, 590, value + 95, 740);
			graphics2d.drawLine(value + 220, 590, value + 145, 740);

			// L
			graphics2d.drawLine(110, 590, 45, 740);
			// T
			graphics2d.drawLine(110, 590, 315, 590);
			// R
			graphics2d.drawLine(315, 590, 240, 745);
			// B
			graphics2d.drawLine(240, 745, 45, 743);

			
			//To draw a line through the coordinates when the win condition satisfies
			if (isGameWon) {
				graphics2d.setColor(Color.WHITE);
				value = 10; 
				graphics2d.drawLine(winningSequenceButtons[0].getBounds().x + value + 27,
						winningSequenceButtons[0].getBounds().y + 20,
						winningSequenceButtons[3].getBounds().x + value + 27,
						winningSequenceButtons[3].getBounds().y + 20);
			}

		}
	}
	
	//setting up the GUI of the board
	public void initializeBoard() {

		gameConfiguration = new char[4][4][4];
		gameBoardButtons = new TicTacToeClick[4][4][4];
		mainPanel = new GamePanel();
		buttonPanel = new JPanel();
		topPanel = new JPanel();
		textPanel = new JPanel();
		textPanel1 = new JPanel();
		textPanel2 = new JPanel();
		buttonFont = new Font("Arial", Font.PLAIN, 16);

		// Variables for the tictactoe clicks
		int rowShift = 25;
		int rowStart = 50;
		int xPosition = 49;
		int yPosition = 45;
		int width = 60;
		int height = 50;
		int boardNumber = 0;
		int rowNumber = 0;
		int colNumber = 0;
		int boxCounterValue = 0;

		int value = 52;

		// Welcome title
		gameStatus = new JLabel("Play the latest 3D Tic-Tac-Toe");
		buttonFont = new Font("Arial", Font.BOLD, 16);
		gameStatus.setFont(buttonFont);
		gameStatus.setHorizontalAlignment(JLabel.CENTER);
		gameStatus.setVerticalAlignment(JLabel.CENTER);
		// Current score panel
		gameScore = new JLabel("Player: " + humanPlayerScore + "   Computer: " + computerPlayerScore);
		gameScore.setFont(buttonFont);
		gameScore.setHorizontalAlignment(JLabel.CENTER);
		gameScore.setVerticalAlignment(JLabel.CENTER);

		infoLabel = new JLabel("Who plays first?");
		buttonFont = new Font("Arial", Font.BOLD, 12);
		infoLabel.setFont(buttonFont);
		infoLabel.setHorizontalAlignment(JLabel.CENTER);
		infoLabel.setVerticalAlignment(JLabel.CENTER);

		humanFirstRadBtn = new JRadioButton("Human", true);
		humanFirstRadBtn.setForeground(Color.WHITE);
		humanFirstRadBtn.setFont(buttonFont);
		computerFirstRadBtn = new JRadioButton("Computer");
		computerFirstRadBtn.setForeground(Color.WHITE);
		computerFirstRadBtn.setFont(buttonFont);
		humanFirstRadBtn.setBounds(500, 210, 150, 40);
		computerFirstRadBtn.setBounds(640, 210, 150, 40);
		defaultSelection = new ButtonGroup();
		defaultSelection.add(computerFirstRadBtn);
		defaultSelection.add(humanFirstRadBtn);
		FirstListener firstListener = new FirstListener();
		computerFirstRadBtn.addActionListener(firstListener);
		humanFirstRadBtn.addActionListener(firstListener);

		JLabel radioDifficultyLabel = new JLabel("Select a difficulty level:");
		buttonFont = new Font("Arial", Font.BOLD, 12); // Replace with your preferred font details
		radioDifficultyLabel.setFont(buttonFont);
		radioDifficultyLabel.setHorizontalAlignment(JLabel.CENTER);
		radioDifficultyLabel.setVerticalAlignment(JLabel.CENTER);

		easyBtn = new JRadioButton("Easy", true);
		easyBtn.setForeground(Color.WHITE);
		easyBtn.setFont(buttonFont);
		easyBtn.setBounds(500, 290, 150, 40);

		difficultBtn = new JRadioButton("Difficult");
		difficultBtn.setForeground(Color.WHITE);
		difficultBtn.setFont(buttonFont);
		difficultBtn.setBounds(500, 320, 150, 40);

		insaneBtn = new JRadioButton("Insane");
		insaneBtn.setForeground(Color.WHITE);
		insaneBtn.setFont(buttonFont);
		insaneBtn.setBounds(500, 350, 150, 40);

		difficultyLevelGroup = new ButtonGroup();
		difficultyLevelGroup.add(easyBtn);
		difficultyLevelGroup.add(difficultBtn);
		difficultyLevelGroup.add(insaneBtn);

		DifficultyListener difficultyListener = new DifficultyListener();
		easyBtn.addActionListener(difficultyListener);
		difficultBtn.addActionListener(difficultyListener);
		insaneBtn.addActionListener(difficultyListener);

		JLabel yourChoiceOfMove = new JLabel("Select your choice");
		// setting up X-O radio buttons
		Font newFont = new Font("Arial", Font.BOLD, 12); // Replace with your preferred font details
		yourChoiceOfMove.setFont(newFont);
		yourChoiceOfMove.setHorizontalAlignment(JLabel.CENTER);
		yourChoiceOfMove.setVerticalAlignment(JLabel.CENTER);

		radioButtonX = new JRadioButton("X", true);
		radioButtonX.setForeground(Color.WHITE);
		radioButtonX.setFont(buttonFont);
		radioButtonX.setBounds(500, 420, 50, 50);
		radioButtonO = new JRadioButton("O");
		radioButtonO.setForeground(Color.WHITE);
		radioButtonO.setFont(buttonFont);
		radioButtonO.setBounds(550, 420, 50, 50);
		// Grouping the buttons
		xoSelect = new ButtonGroup();
		xoSelect.add(radioButtonX);
		xoSelect.add(radioButtonO);
		// X-O piece listeners
		xoListener = new EveryMoveListener();
		radioButtonX.addActionListener(xoListener);
		radioButtonO.addActionListener(xoListener);

		// Start a New Game Button
		startNewGameButton = new JButton("<html><b>Start a New Game</b></html>");
		startNewGameButton.setBounds(500, 500, 130, 40);
		System.out.println("start");
		startNewGameButton.addActionListener(new StartNewGameButtonListener());
		startNewGameButton.setBackground(new Color(255, 165, 0)); // Orange color
		startNewGameButton.setName("newGameBtn");
		
		//Clear scores button
		clearScoresNewGameButton = new JButton("<html><b>Clear Scores</b></html>");
		clearScoresNewGameButton.setBounds(500, 550, 130, 40);
		clearScoresNewGameButton.addActionListener(new ClearScoresListener());
		clearScoresNewGameButton.setName("clearScoresBtn");

		for (int i = 0; i <= 3; i++) {
			for (int j = 0; j <= 3; j++) {
				for (int k = 0; k <= 3; k++) {
					gameConfiguration[i][j][k] = '-';
					gameBoardButtons[i][j][k] = new TicTacToeClick();
					gameBoardButtons[i][j][k].setFont(new Font("Arial", Font.BOLD, 18));
					gameBoardButtons[i][j][k].setForeground(Color.WHITE);
					gameBoardButtons[i][j][k].setText("");
					gameBoardButtons[i][j][k].setContentAreaFilled(false);
					gameBoardButtons[i][j][k].setBorderPainted(false);
					gameBoardButtons[i][j][k].setFocusPainted(false);
					gameBoardButtons[i][j][k].setBounds(xPosition + value, yPosition, width, height);
					gameBoardButtons[i][j][k].setName(Integer.toString(boxCounterValue));
					gameBoardButtons[i][j][k].sqaureboxBoard = boardNumber;
					gameBoardButtons[i][j][k].sqaureboxRow = rowNumber;
					gameBoardButtons[i][j][k].sqaureboxColumn = colNumber;
					gameBoardButtons[i][j][k].addActionListener(this);
					colNumber++;
					boxCounterValue++;
					xPosition += 49;
					getContentPane().add(gameBoardButtons[i][j][k]);
				}
				colNumber = 0;
				rowNumber++;
				xPosition = rowStart -= rowShift;
				yPosition += 44;
			}
			rowNumber = 0;
			rowShift = 26;
			rowStart = 58;
			boardNumber++;
			xPosition = rowStart;
			yPosition += 2;
		}

		mainPanel.setVisible(true);
		topPanel.setVisible(true);
		textPanel.setVisible(true);
		textPanel1.setVisible(true);
		textPanel2.setVisible(true);
		buttonPanel.setVisible(true);
		gameStatus.setVisible(true);

		topPanel.setLayout(new GridLayout(2, 1));
		topPanel.add(gameStatus);
		topPanel.add(gameScore);
		topPanel.setBounds(200, 0, 440, 40);

		textPanel.setBounds(505, 190, 200, 20);
		textPanel.add(infoLabel);
		textPanel1.setBounds(505, 270, 200, 20);
		textPanel1.add(radioDifficultyLabel);
		textPanel2.setBounds(505, 410, 200, 20);
		textPanel2.add(yourChoiceOfMove);

		add(radioButtonX);
		add(radioButtonO);
		add(humanFirstRadBtn);
		add(computerFirstRadBtn);
		add(easyBtn);
		add(difficultBtn);
		add(insaneBtn);
		add(startNewGameButton);
		add(clearScoresNewGameButton);
		add(topPanel);
		add(textPanel);
		add(textPanel1);
		add(textPanel2);
		add(mainPanel);

		setVisible(true);
		mainPanel.setBackground(Color.BLACK);
		topPanel.setBackground(Color.CYAN);
		textPanel.setBackground(Color.CYAN);
		textPanel1.setBackground(Color.CYAN);
		textPanel2.setBackground(Color.CYAN);

	}

	class FirstListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("entered-->");
			// Clear the board.
			boardClear();
			gameStatus.setForeground(Color.BLUE);
			gameStatus.setText("Game Begins! Best of Luck");
			gameStatus.setHorizontalAlignment(JLabel.CENTER);
			if (computerFirstRadBtn.isSelected()) {
				System.out.println("-->");
				isHumanFirst = false;
				if (insaneBtn.isSelected()) {
					System.out.println("insane-->");
					performInsaneComputerMove();
				} else {
					System.out.println("regular-->");
					performRegularComputerMove();
					System.out.println("out-->");
				}
			} else {
				isHumanFirst = true;
			}
		}
	}
	
	private void performRegularComputerMove() {
		computerMove();
	}

	private void performInsaneComputerMove() {
		computerMove();
	}
	
	public class ClearScoresListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			boardClear();
			gameStatus.setText("Its a new session!!");
			gameStatus.setHorizontalAlignment(JLabel.CENTER);
			humanPlayerScore = 0;
			computerPlayerScore = 0;
			updateNewScore(); 

		}
	}


	public class StartNewGameButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			boardClear();
			gameStatus.setForeground(Color.BLUE);
			gameStatus.setText("NEW GAME!!");
			gameStatus.setHorizontalAlignment(JLabel.CENTER);
			if (!isHumanFirst) {
				System.out.println("human first" + isHumanFirst);
				if (gameDifficulty == 3)
					performInsaneComputerMove();
				else
					performRegularComputerMove();
			}
		}
	}
	
	public class DifficultyListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			boardClear();
			gameStatus.setForeground(Color.BLUE);
			System.out.println("difficulty-->");
			gameStatus.setText("Game Begins! Best of Luck");
			gameStatus.setHorizontalAlignment(JLabel.CENTER);
			if (easyBtn.isSelected()) {
				gameDifficulty = 1;
				totalLooksAhead = 2;
			} else if (difficultBtn.isSelected()) {
				gameDifficulty = 2;
				totalLooksAhead = 4;
			} else {
				gameDifficulty = 3;
				totalLooksAhead = 6;
			}
			if (!isHumanFirst) {
				if (gameDifficulty == 3)
					computerMovesDifficulty();
				else
					computerMove();
			}
		}
	}
	
	public class EveryMoveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			boardClear();
			gameStatus.setForeground(Color.BLUE);
			gameStatus.setText("Game Begins! Best of Luck");
			gameStatus.setHorizontalAlignment(JLabel.CENTER);
			if (radioButtonX.isSelected()) {
				humanMove = 'X';
				computerMove = 'O';
			} else {
				humanMove = 'O';
				computerMove = 'X';
			}
			if (!isHumanFirst) {
				if (gameDifficulty == 3)
					computerMovesDifficulty();
				else
					computerMove();
			}
		}
	}

	

	public void actionPerformed(ActionEvent e) {
		TicTacToeClick clickedButton = (TicTacToeClick) e.getSource();
		gameConfiguration[clickedButton.sqaureboxBoard][clickedButton.sqaureboxRow][clickedButton.sqaureboxColumn] = humanMove;
		gameBoardButtons[clickedButton.sqaureboxBoard][clickedButton.sqaureboxRow][clickedButton.sqaureboxColumn]
				.setText(Character.toString(humanMove));
		//disabling the place
		gameBoardButtons[clickedButton.sqaureboxBoard][clickedButton.sqaureboxRow][clickedButton.sqaureboxColumn].setEnabled(false);
		Move newMove = new Move();
		newMove.board = clickedButton.sqaureboxBoard;
		newMove.row = clickedButton.sqaureboxRow;
		newMove.column = clickedButton.sqaureboxColumn;
		if (isWinningMove(humanMove, newMove)) {
	        handleWin("Computer lost! Press Start a New Game to play again.", Color.BLUE);
	    } else {
	        computerMovesDifficulty();
	    }
	}

	public void boardClear() {
		repaint();
		isGameWon = false;
		lookAheadCountValue = 0;
		for (int i = 0; i <= 3; i++) {
			for (int j = 0; j <= 3; j++) {
				for (int k = 0; k <= 3; k++) {
					gameConfiguration[i][j][k] = '-';
					gameBoardButtons[i][j][k].setText("");
					gameBoardButtons[i][j][k].setEnabled(true);
				}
			}
		}
		winningSequence = new int[4];
	}

	public void disableBoard() {
		int topValue = 0;
		for (int i = 0; i <= 3; i++) {
			for (int j = 0; j <= 3; j++) {
				for (int k = 0; k <= 3; k++) {
					if (contains(winningSequence, Integer.parseInt(gameBoardButtons[i][j][k].getName()))) {
						gameBoardButtons[i][j][k].setEnabled(true);
						gameBoardButtons[i][j][k].setForeground(Color.CYAN);
						winningSequenceButtons[topValue] = gameBoardButtons[i][j][k];
						topValue++;
					} else {
						gameBoardButtons[i][j][k].setEnabled(false);
					}
				}
			}
		}

		repaint();

	}
	
	private void handleWin(String message, Color color) {
	    gameStatus.setText(message);
	    gameStatus.setForeground(color);
	    gameStatus.setHorizontalAlignment(JLabel.CENTER);
	    humanPlayerScore++;
	    isGameWon = true;
	    disableBoard();
	    updateNewScore();
	}

	public void updateNewScore() {
		gameScore.setText("Player: " + humanPlayerScore + "    Computer: " + computerPlayerScore);
		gameScore.setHorizontalAlignment(JLabel.CENTER);
	}

	private boolean contains(int[] a, int k) {
		for (int i : a) {
			if (k == i)
				return true;
		}
		return false;
	}

	private void computerMove() {
		System.out.println("computer move--");
		Random random = new Random();
		int row = random.nextInt(4);
		int column = random.nextInt(4);
		int board = random.nextInt(4);
		gameConfiguration[board][row][column] = computerMove;
		gameBoardButtons[board][row][column].setText(Character.toString(computerMove));
		gameBoardButtons[board][row][column].setForeground(Color.RED);
		gameBoardButtons[board][row][column].setEnabled(false);
	}
	
	private void computerMovesDifficulty() {
	    int goodScore = -1000;
	    int goodBoard = -1;
	    int goodRow = -1;
	    int goodColumn = -1;

	    for (int i = 0; i <= 3; i++) {
	        for (int j = 0; j <= 3; j++) {
	            for (int k = 0; k <= 3; k++) {
	                if (gameConfiguration[i][j][k] == '-') {
	                    Move nextClick = new Move();
	                    nextClick.board = i;
	                    nextClick.row = j;
	                    nextClick.column = k;

	                    if (isWinningMove(computerMove, nextClick)) {
	                        gameConfiguration[i][j][k] = computerMove;
	                        gameBoardButtons[i][j][k].setText(Character.toString(computerMove));
	                        gameStatus.setText("Computer wins! Press Start a New Game to play again.");
	                        gameStatus.setForeground(Color.BLUE);
	                        isGameWon = true;
	                        computerPlayerScore++;
	                        disableBoard();
	                        updateNewScore();
	                        return;
	                    } else {
	                        int evaluation;
	                        if (gameDifficulty != 1) {
	                        	evaluation = minimax(humanMove, -1000, 1000);
	                        } else {
	                        	evaluation = evaluateBoard();
	                        }

	                        lookAheadCountValue = 0;
	                        if (evaluation >= goodScore) {
	                        	goodScore = evaluation;
	                        	goodBoard = i;
	                        	goodRow = j;
	                        	goodColumn = k;
	                            gameConfiguration[i][j][k] = '-';
	                        } else {
	                            gameConfiguration[i][j][k] = '-';
	                        }
	                    }
	                }
	            }
	        }
	    }

	    if (!isGameWon) {
	        gameConfiguration[goodBoard][goodRow][goodColumn] = computerMove;
	        gameBoardButtons[goodBoard][goodRow][goodColumn].setText(Character.toString(computerMove));
	        gameBoardButtons[goodBoard][goodRow][goodColumn].setEnabled(false);
	    }
	}
	
	private int minimax(char currentPlayer, int alpha, int beta) {
	    int localAlpha = alpha;
	    int localBeta = beta;

	    if (lookAheadCountValue > totalLooksAhead) {
	        return evaluateBoard();
	    }

	    lookAheadCountValue++;

	    char opponentPlayer = (currentPlayer == computerMove) ? humanMove : computerMove;

	    int bestScore = (currentPlayer == computerMove) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

	    for (int i = 0; i <= 3; i++) {
	        for (int j = 0; j <= 3; j++) {
	            for (int k = 0; k <= 3; k++) {
	                if (gameConfiguration[i][j][k] == '-') {
	                    Move nextMove = new Move();
	                    nextMove.board = i;
	                    nextMove.row = j;
	                    nextMove.column = k;

	                    if (isWinningMove(currentPlayer, nextMove)) {
	                        gameConfiguration[i][j][k] = '-';
	                        return (currentPlayer == computerMove) ? 1000 : -1000;
	                    } else {
	                        gameConfiguration[i][j][k] = currentPlayer;

	                        int hValue = minimax(opponentPlayer, localAlpha, localBeta);

	                        gameConfiguration[i][j][k] = '-';

	                        if (currentPlayer == computerMove) {
	                            bestScore = Math.max(bestScore, hValue);
	                            localAlpha = Math.max(localAlpha, bestScore);
	                        } else {
	                            bestScore = Math.min(bestScore, hValue);
	                            localBeta = Math.min(localBeta, bestScore);
	                        }

	                        if (localAlpha >= localBeta) {
	                            break;
	                        }
	                    }
	                }
	            }
	        }
	    }

	    return bestScore;
	}


	private int evaluateBoard() {
		return (checkAvailable(computerMove) - checkAvailable(humanMove));
	}
	
	public int[][] winningCombinations = { { 0, 1, 2, 3 }, { 4, 5, 6, 7 }, { 8, 9, 10, 11 }, { 12, 13, 14, 15 }, { 16, 17, 18, 19 },
			{ 20, 21, 22, 23 }, { 24, 25, 26, 27 }, { 28, 29, 30, 31 }, { 32, 33, 34, 35 }, { 36, 37, 38, 39 },
			{ 40, 41, 42, 43 }, { 44, 45, 46, 47 }, { 48, 49, 50, 51 }, { 52, 53, 54, 55 }, { 56, 57, 58, 59 },
			{ 60, 61, 62, 63 }, { 0, 4, 8, 12 }, { 1, 5, 9, 13 }, { 2, 6, 10, 14 }, { 3, 7, 11, 15 },
			{ 16, 20, 24, 28 }, { 17, 21, 25, 29 }, { 18, 22, 26, 30 }, { 19, 23, 27, 31 }, { 32, 36, 40, 44 },
			{ 33, 37, 41, 45 }, { 34, 38, 42, 46 }, { 35, 39, 43, 47 }, { 48, 52, 56, 60 }, { 49, 53, 57, 61 },
			{ 50, 54, 58, 62 }, { 51, 55, 59, 63 }, { 0, 5, 10, 15 }, { 3, 6, 9, 12 }, { 16, 21, 26, 31 },
			{ 19, 22, 25, 28 }, { 32, 37, 42, 47 }, { 35, 38, 41, 44 }, { 48, 53, 58, 63 }, { 51, 54, 57, 60 },
			{ 0, 16, 32, 48 }, { 1, 17, 33, 49 }, { 2, 18, 34, 50 }, { 3, 19, 35, 51 }, { 4, 20, 36, 52 },
			{ 5, 21, 37, 53 }, { 6, 22, 38, 54 }, { 7, 23, 39, 55 }, { 8, 24, 40, 56 }, { 9, 25, 41, 57 },
			{ 10, 26, 42, 58 }, { 11, 27, 43, 59 }, { 12, 28, 44, 60 }, { 13, 29, 45, 61 }, { 14, 30, 46, 62 },
			{ 15, 31, 47, 63 }, { 0, 20, 40, 60 }, { 1, 21, 41, 61 }, { 2, 22, 42, 62 }, { 3, 23, 43, 63 },
			{ 12, 24, 36, 48 }, { 13, 25, 37, 49 }, { 14, 26, 38, 50 }, { 15, 27, 39, 51 }, { 4, 21, 38, 55 },
			{ 8, 25, 42, 59 }, { 7, 22, 37, 52 }, { 11, 26, 41, 56 }, { 0, 17, 34, 51 }, { 3, 18, 33, 48 },
			{ 12, 29, 46, 63 }, { 15, 30, 45, 60 }, { 0, 21, 42, 63 }, { 3, 22, 41, 60 }, { 12, 25, 38, 51 },
			{ 15, 26, 37, 48 }, };

	private boolean isWinningMove(char player, Move position) {
		gameConfiguration[position.board][position.row][position.column] = player;
		int[] gameBoard = new int[64];
		int countValue = 0;
		for (int i = 0; i <= 3; i++) {
		      for (int j = 0; j <= 3; j++) {
		          for (int k = 0; k <= 3; k++) {
		              gameBoard[countValue] = (gameConfiguration[i][j][k] == player) ? 1 : 0;
		              countValue++;
		          }
		      }
	    }
		 for (int[] win : winningCombinations) {
			 countValue = 0;
		        for (int j : win) {
		            if (gameBoard[j] == 1) {
		            	countValue++;
		                winningSequence[countValue - 1] = j;
		                if (countValue == 4) {
		                    return true;
		                }
		            }
		        }
		    }

		return false;
	}
	


	private int checkAvailable(char player){
		int countWin = 0;
		int[] gameBoard = new int[64];
		int counter = 0;
		for (int i = 0; i <= 3; i++){
			for (int j = 0; j <= 3; j++){
				for(int k = 0; k <= 3; k++){
					gameBoard[counter] = (gameConfiguration[i][j][k] == player || gameConfiguration[i][j][k] == '-') ? 1 : 0;
		             counter++;
					}
			}
			 
		}
		for (int i = 0; i <= 75; i++){
			counter = 0;
			for (int j = 0; j <= 3; j++){
				if(gameBoard[winningCombinations[i][j]] == 1){
					counter++;
					winningSequence[j] = winningCombinations[i][j];
					if(counter == 4)
						countWin++;
				}
			}
		}
		return countWin;
		}
	
//main method
	public static void main(String a[]) {
		TicTacToeGame3D tt3d = new TicTacToeGame3D();
	}

}