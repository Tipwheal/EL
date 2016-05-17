package com.tipwheal.el;

public class ByLyq {
	private int[] first = { 0, 5 };
	private int[] second = { 0, 14 };
	private int[] third = { 9, 14 };
	private int[][] board = board();

	public ByLyq() {

		int[][] boardSeen = new int[15][15];
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				boardSeen[i][j] = 9;
			}
		}
	}

	int[][] board() {
		int[][] board = new int[15][15];
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				board[i][j] = 8;
			}
		}
		return board;
	}

	public int[][] manhattan(int i, int[] location, int[][] board, int[][] boardSeen) {
		for (int n = 0; n <= 5; n++) {
			for (int m = location[1] - 5 + n; m <= location[1] + 5 - n; m++) {
				boardSeen[location[0] + n][m] = board[location[0] + n][m];

			}
			for (n = 0; n <= 5; n++) {
				for (int m = location[1] - 5 + n; m <= location[1] + 5 - n; m++) {
					boardSeen[location[0] - n][m] = board[location[0] - n][m];
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
					score[9]++;
					break;
				}

			}
		}
		return score;
	}

	public int[] getFirst() {
		return first;
	}

	public int[] getSecond() {
		return second;
	}

	public int[] getThird() {
		return third;
	}

	public int[][] getBoard() {
		return board;
	}
}