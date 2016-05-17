package com.tipwheal.el;

import java.util.ArrayList;

public class Sword {
	private int[] myLocation = new int[2];

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
		}

		return list;
	}

	private ArrayList<int[]> South() {
		ArrayList<int[]> list = new ArrayList<int[]>();
		list.add(this.Change(0, 1));
		list.add(this.Change(0, 2));
		list.add(this.Change(1, 1));
		list.add(this.Change(1, 0));
		list.add(this.Change(2, 0));
		return list;
	}

	private ArrayList<int[]> East() {
		ArrayList<int[]> list = new ArrayList<int[]>();
		list.add(this.Change(0, -1));
		list.add(this.Change(0, -2));
		list.add(this.Change(1, -1));
		list.add(this.Change(1, 0));
		list.add(this.Change(2, 0));
		return list;
	}

	private ArrayList<int[]> North() {
		ArrayList<int[]> list = new ArrayList<int[]>();
		list.add(this.Change(0, -1));
		list.add(this.Change(0, -2));
		list.add(this.Change(-1, -1));
		list.add(this.Change(-1, 0));
		list.add(this.Change(-2, 0));
		return list;
	}

	private ArrayList<int[]> West() {
		ArrayList<int[]> list = new ArrayList<int[]>();
		list.add(this.Change(0, 1));
		list.add(this.Change(0, 2));
		list.add(this.Change(-1, 1));
		list.add(this.Change(-1, 0));
		list.add(this.Change(-2, 0));
		return list;
	}

	private int[] Change(int x, int y) {
		int[] change = new int[2];
		change[0] = myLocation[0] + x;
		change[1] = myLocation[1] + y;
		return change;
	}

	int[] location(int[] location, String s) {
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
			board[location[0]][location[1]] = 0;
		}
		return board;
	}
}
