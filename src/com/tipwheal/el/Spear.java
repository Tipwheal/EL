package com.tipwheal.el;

import java.util.ArrayList;

/**
 * Use to get area that spear can attack.
 * 
 * @author Wx
 *
 */
public class Spear {
	private int[] myLocation = new int[2];

	/**
	 * Get the cells in the direction of a location.
	 * 
	 * @param direction
	 *            <br>
	 *            About direction:<br>
	 *            1 south<br>
	 *            2 east<br>
	 *            3 west<br>
	 *            4 north<br>
	 * @param L
	 *            Location
	 * @return A list of cells.
	 */
	public ArrayList<int[]> getArea(String direction, int[] L) {
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

	/**
	 * Attack south.
	 * 
	 * @return A list of cells.
	 */
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

	/**
	 * Attack east.
	 * 
	 * @return A list of cells.
	 */
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

	/**
	 * Attack west.
	 * 
	 * @return A list of cells.
	 */
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

	/**
	 * Attack north.
	 * 
	 * @return A list of cells.
	 */
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

	public int[] location(int[] location, String s) {
		switch (s) {
		case "5":
			if (location[1] >= 1) {
				location[1]--;
			}
			break;
		case "6":
			if (location[1] < 15) {
				location[1]++;
			}
			break;
		case "7":
			if (location[0] >= 1) {
				location[0]--;
			}
			break;
		case "8":
			if (location[1] < 15) {
				location[1]++;
			}
			break;
		}
		return location;
	}

	public int isHide(int isHide, String s) {
		if (s.equals("9"))
			isHide = 1;
		else
			isHide = 0;
		return isHide;
	}

	public int[][] newBoard(int[][] board, String s, int[] L) {
		ArrayList<int[]> getArea = getArea(s, L);
		for (int[] location : getArea) {
			board[location[0]][location[1]] = 0;
		}
		return board;
	}
}
