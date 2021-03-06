package com.tipwheal.el;

import java.util.ArrayList;

public class Switcher {
	private String[] actions;
	private Spear spear = new Spear();
	private Sword sword = new Sword();
	private Ax ax = new Ax();
	private int ID;

	public Switcher(String s, int id) {
		actions = s.split(" ");
		ID = id;
	}

	public int[] getDoneLoca(int[] myLoca) {
		int[] doneLoca = new int[2];
		doneLoca[0] = myLoca[0];
		doneLoca[1] = myLoca[1];
		for (int i = 0; i < actions.length; i++) {
			switch (actions[i]) {
			case "5":
				if (doneLoca[1] < 14)
					doneLoca[1] += 1;
				break;
			case "6":
				if (doneLoca[0] < 14)
					doneLoca[0] += 1;
				break;
			case "7":
				if (doneLoca[1] > 0)
					doneLoca[1] -= 1;
				break;
			case "8":
				if (doneLoca[0] > 0)
					doneLoca[0] -= 1;
				break;
			}
		}

		return doneLoca;
	}

	public ArrayList<int[]> getAreaOfThisBehavior(int[] myLoca) {
		int[] doneLoca = new int[2];
		doneLoca[0] = myLoca[0];
		doneLoca[1] = myLoca[1];
		for (int i = 0; i < actions.length; i++) {

			switch (actions[i]) {
			case "5":
				if (doneLoca[1] < 14)
					doneLoca[1]++;
				break;
			case "6":
				if (doneLoca[0] < 14)
					doneLoca[0]++;
				break;
			case "7":
				if (doneLoca[1] > 0)
					doneLoca[1]--;
				break;
			case "8":
				if (doneLoca[0] > 0)
					doneLoca[0]--;
				break;
			}

			if (Integer.valueOf(actions[i]) > 0 && Integer.valueOf(actions[i]) < 5) {
				if (ID == 0) {
					return spear.getArea(actions[i], doneLoca);
				} else if (ID == 1) {
					return sword.getArea(actions[i], doneLoca);
				} else {
					return ax.getArea(actions[i], doneLoca);
				}
			}
		}
		return null;
	}
}
