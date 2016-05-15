package com.tipwheal.el;

/**
 * Copied from 万霄on 2016/5/12.
 */
import java.util.ArrayList;

class Spear {
	private int[] myLocation = new int[2];

	ArrayList<int[]> getArea(String direction, int[] L) {
		myLocation = L;
		ArrayList<int[]> list = new ArrayList<int[]>();
		switch (direction) {
		case "1":
			list = this.South();
			break;
		case "2":
			list = this.East();
			break;
		case "3":
			list = this.North();
			break;
		case "4":
			list = this.West();
			break;
		}

		return list;
	}

	private ArrayList<int[]> South() {
		ArrayList<int[]> list = new ArrayList<int[]>();
		for (int i = 1; i < 5; i++) {
			int[] temp = new int[2];
			temp[0] = myLocation[0];
			temp[1] = myLocation[1] + i;
			list.add(temp);

		}
		return list;
	}

	private ArrayList<int[]> East() {
		ArrayList<int[]> list = new ArrayList<int[]>();
		for (int i = 1; i < 5; i++) {
			int[] temp = new int[2];
			temp[0] = myLocation[0] + i;
			temp[1] = myLocation[1];
			list.add(temp);

		}
		return list;
	}

	private ArrayList<int[]> West() {
		ArrayList<int[]> list = new ArrayList<int[]>();
		for (int i = 1; i < 5; i++) {
			int[] temp = new int[2];
			temp[0] = myLocation[0] - i;
			temp[1] = myLocation[1];
			list.add(temp);

		}
		return list;
	}

	private ArrayList<int[]> North() {
		ArrayList<int[]> list = new ArrayList<int[]>();
		for (int i = 1; i < 5; i++) {
			int[] temp = new int[2];
			temp[0] = myLocation[0];
			temp[1] = myLocation[1] - i;
			list.add(temp);

		}
		return list;
	}

	int[] location(int[] location, String s) {
		switch (s) {
		case "5":
			try {
				if (location[1] >= 1)
					location[1]--;
				else
					break;
			} catch (ArrayIndexOutOfBoundsException e) {
				break;
			}
			break;
		case "6":
			try {
				if (location[1] < 15)
					location[1]++;
				else
					break;
			} catch (ArrayIndexOutOfBoundsException e) {
				break;
			}
			break;
		case "7":
			try {
				if (location[0] >= 1)
					location[0]--;
				else
					break;
				;
			} catch (ArrayIndexOutOfBoundsException e) {
				break;
			}
			break;
		case "8":
			try {
				if (location[1] < 15)
					location[1]++;
				else
					break;
				;
			} catch (ArrayIndexOutOfBoundsException e) {
				break;
			}
			break;
		}
		return location;
	}

	int isHide(int isHide, String s) {
		if (s.equals("9"))
			isHide = 1;
		else
			isHide = 0;
		return isHide;
	}

	int[][] newBoard(int[][] board, String s, int[] L) {
		ArrayList<int[]> getArea = getArea(s, L);
		for (int[] location : getArea) {
			try {
				board[location[0]][location[1]] = 0;
			} catch (ArrayIndexOutOfBoundsException e) {

			}
		}
		return board;

	}
}
