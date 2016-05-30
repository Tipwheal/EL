package com.tipwheal.el;

import java.util.ArrayList;

public class MemoryInfo {
	private int[][] lastLoc = { { 0, 0 }, { 0, 0 }, { 0, 0 } };
	private int[][] lastField = new int[15][15];
	private ArrayList<int[]> changedLoc = new ArrayList<>();

	public void setLastLoc(int enemyID, int enemyX, int enemyY) {
		lastLoc[enemyID - 3][0] = enemyX;
		lastLoc[enemyID - 3][1] = enemyY;
	}

	public int getLastX(int enemyID) {
		return lastLoc[enemyID - 3][0];
	}

	public int getLastY(int enemyID) {
		return lastLoc[enemyID - 3][1];
	}

	public void setLastField(int[][] field) {
		changedLoc.clear();
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				int lastState = lastField[i][j];
				int state = field[i][j];
				if (lastState != state) {
					lastField[i][j] = state;
					if (state >= 3 && state <= 5) {
						int[] loc = { i, j };
						changedLoc.add(loc);
					}
				}
			}
		}
	}

	public ArrayList<int[]> getChangedLoc() {
		return changedLoc;		
	}
}
