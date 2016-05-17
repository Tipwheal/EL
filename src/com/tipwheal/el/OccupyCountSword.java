package com.tipwheal.el;

import java.util.ArrayList;

public class OccupyCountSword {
	private int[] first = { 0, 5 };
	int[] second = { 0, 14 };
	int[] third = { 9, 14 };
	private int[][] board = board();
	private Sword sword = new Sword();
	private int isHide = 0;
	private Behavior behavior = new Behavior(isHide);

	public OccupyCountSword() {

		int[][] boardSeen = new int[15][15];
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				boardSeen[i][j] = 9;
			}
		}
	}

	public ArrayList<String> occupyCountSword(int d) {

		int[][] boardSeen = new int[15][15];
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				boardSeen[i][j] = 9;
			}
		}
		ArrayList<String> occupy = new ArrayList<>();
		ArrayList<String> noOccupy = new ArrayList<>();
		ArrayList<String> beOccupied = new ArrayList<>();
		for (int a = 0; a < behavior.behaviors.size(); a++) {
			String order = behavior.getBehavior(a);
			String[] step = order.split(" ");
			int[] score = { 0, 0, 0, 0, 0, 0, 0, 0 };
			for (String steps : step) {
				if (steps.equals("1") || steps.equals("2") || steps.equals("3") || steps.equals("4")) {
					board = sword.newBoard(board, steps, first);
				} else if (steps.equals("5") || steps.equals("6") || steps.equals("7") || steps.equals("8")) {
					first = sword.location(first, steps);
				} else if (steps.equals("9")) {
					isHide = sword.isHide(isHide, steps);
				}
			}
			boardSeen = manhattan(first, board, manhattan(second, board, manhattan(third, board, boardSeen)));
			int tempUs = score[0] + score[1] + score[2];
			score = occupyCount(boardSeen);
			int us = score[0] + score[1] + score[2];
			if (us > tempUs) {
				occupy.add(behavior.getBehavior(a));
			} else if (us == tempUs) {
				noOccupy.add(behavior.getBehavior(a));
			} else if (us < tempUs) {
				beOccupied.add(behavior.getBehavior(a));
			}

		}
		switch (d) {
		case 1:
			return occupy;
		// break;
		case 2:
			return noOccupy;
		// break;;
		case 3:
			return beOccupied;
		// break;;
		default:
			break;
		}
		return occupy;
	}

	private int[][] board() {
		int[][] board = new int[15][15];
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				board[i][j] = 8;
			}
		}
		return board;
	}

	public int[][] manhattan(int[] location, int[][] board, int[][] boardSeen) {
		for (int n = 0; n <= 5; n++) {
			for (int m = location[1] - 5 + n; m <= location[1] + 5 - n; m++) {
				try {
					boardSeen[location[0] + n][m] = board[location[0] + n][m];
				} catch (ArrayIndexOutOfBoundsException e) {
					break;
				}
			}
			for (n = 0; n <= 5; n++) {
				for (int m = location[1] - 5 + n; m <= location[1] + 5 - n; m++) {
					try {
						boardSeen[location[0] - n][m] = board[location[0] - n][m];
					} catch (ArrayIndexOutOfBoundsException e) {
						break;
					}
				}
			}
		}
		return boardSeen;
	}

	public int[] occupyCount(int[][] boardSeen) {
		int[] score = { 0, 0, 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				switch (boardSeen[i][j]) {
				case 0:
					score[0]++;
					break;
				case 1:
					score[1]++;
					break;
				case 2:
					score[2]++;
					break;
				case 3:
					score[3]++;
					break;
				case 4:
					score[4]++;
					break;
				case 5:
					score[5]++;
					break;
				case 8:
					score[6]++;
					break;
				case 9:
					score[7]++;
					break;
				default:
					break;
				}

			}
		}
		return score;
	}

}
