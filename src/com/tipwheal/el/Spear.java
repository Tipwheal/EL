package com.tipwheal.el;

import java.util.ArrayList;

public class Spear {
	int[] myLocation = new int[2];

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
}
