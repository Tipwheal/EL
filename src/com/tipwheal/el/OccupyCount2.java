package com.tipwheal.el;

import java.util.ArrayList;

public class OccupyCount2 {
	private int[] myLocation = new int[2];
	private int ID;
	private int[][] board;
	private ArrayList<int[]> countOccupyCells = new ArrayList<>();
	private GameInfo gi;
	private UsefulInfo ui;
	private ActionFilter filter;
	private Spear spear = new Spear();
	private Sword sword = new Sword();
	private Ax ax = new Ax();

	public OccupyCount2(GameInfo info) {
		gi = info;
		ui = new UsefulInfo(gi);
		filter = new ActionFilter(gi);
	}

	public double getOcpNum(String action, int yourself) {
		double num = 0;
		int[] yourLoc = ui.getSamuraiLocation(yourself);
		ArrayList<int[]> locs = filter.getNextOccupiedLocation(action, yourLoc, yourself);
		for (int[] loc : locs) {
			int state = ui.getField()[loc[1]][loc[0]];
			if (gi.getSide() == 1) {
				if (state == 8) {
					num += 1;
				}
				if (state >= 3 && state <= 5) {
					num += 1.3;
				}
				if (state >= 0 && state <= 2) {
					num += 0.3;
				}
			} else {
				if (state == 8) {
					num += 1;
				}
				if (state >= 3 && state <= 5) {
					num += 2;
				}
			}
			for (int i = 3; i < 6; i++) {
				int enemyX = gi.getSamuraiInfo()[i].getCurX();
				int enemyY = gi.getSamuraiInfo()[i].getCurY();
				if ((enemyX == loc[0] && enemyY == loc[1]) && (enemyX != gi.getSamuraiInfo()[i].getHomeX()
						&& enemyY != gi.getSamuraiInfo()[i].getHomeY())) {
					num += 10;
				}
			}
		}
		return num;
	}

	public ArrayList<int[]> getCountOccupyCells() {
		return countOccupyCells;
	}

	public OccupyCount2(int[] l, int id) {
		myLocation = l;
		ID = id % 3;
		board = new int[15][15];
		board = ui.getField();
	}

	private int isHide = 0;
	private Behavior behavior = new Behavior(isHide);

	public ArrayList<String> occupyCells(int[] myLocation, int ID) {
		ArrayList<String> occupy = new ArrayList<>();
		switch (ID) {
		case 0: {
			for (int a = 0; a < behavior.behaviors.size(); a++) {
				String order = behavior.getBehavior(a);
				String[] step = order.split(" ");
				int[] score = { 0, 0, 0, 0, 0, 0, 0, 0 };
				for (String steps : step) {
					if (gi.isValid(Integer.valueOf(steps))) {
						if (steps.equals("1") || steps.equals("2") || steps.equals("3") || steps.equals("4")) {
							board = spear.newBoard(board, steps, myLocation);
						} else if (steps.equals("5") || steps.equals("6") || steps.equals("7") || steps.equals("8")) {
							myLocation = spear.location(myLocation, steps);
						} else if (steps.equals("9")) {
							isHide = spear.isHide(isHide, steps);
						}

					}
					int tempUs = score[0] + score[1] + score[2];
					int tempEnemy = score[3] + score[4] + score[5];
					score = occupyCount(board);
					int us = score[0] + score[1] + score[2];
					int enemy = score[3] + score[4] + score[5];
					if (us > tempUs) {
						occupy.add(behavior.getBehavior(a));
						int[] tempCount = { (us - tempUs) - (enemy - tempEnemy), enemy - tempEnemy };
						countOccupyCells.add(tempCount);
					}
				}
			}
			return occupy;
			// break;
		}
		case 2: {
			for (int a = 0; a < behavior.behaviors.size(); a++) {
				String order = behavior.getBehavior(a);
				String[] step = order.split(" ");
				int[] score = { 0, 0, 0, 0, 0, 0, 0, 0 };
				for (String steps : step) {
					if (gi.isValid(Integer.valueOf(steps))) {
						if (steps.equals("1") || steps.equals("2") || steps.equals("3") || steps.equals("4")) {
							board = ax.newBoard(board, steps, myLocation);
						} else if (steps.equals("5") || steps.equals("6") || steps.equals("7") || steps.equals("8")) {
							myLocation = ax.location(myLocation, steps);
						} else if (steps.equals("9")) {
							isHide = ax.isHide(isHide, steps);
						}

					}
					int tempUs = score[0] + score[1] + score[2];
					int tempEnemy = score[3] + score[4] + score[5];
					score = occupyCount(board);
					int us = score[0] + score[1] + score[2];
					int enemy = score[3] + score[4] + score[5];
					if (us > tempUs) {
						occupy.add(behavior.getBehavior(a));
						int[] tempCount = { (us - tempUs) - (enemy - tempEnemy), enemy - tempEnemy };
						countOccupyCells.add(tempCount);
					}
				}
			}
		}
		case 1: {
			for (int a = 0; a < behavior.behaviors.size(); a++) {
				String order = behavior.getBehavior(a);
				String[] step = order.split(" ");
				int[] score = { 0, 0, 0, 0, 0, 0, 0, 0 };
				for (String steps : step) {
					if (gi.isValid(Integer.valueOf(steps))) {
						if (steps.equals("1") || steps.equals("2") || steps.equals("3") || steps.equals("4")) {
							board = sword.newBoard(board, steps, myLocation);
						} else if (steps.equals("5") || steps.equals("6") || steps.equals("7") || steps.equals("8")) {
							myLocation = sword.location(myLocation, steps);
						} else if (steps.equals("9")) {
							isHide = sword.isHide(isHide, steps);
						}

					}
					int tempUs = score[0] + score[1] + score[2];
					int tempEnemy = score[3] + score[4] + score[5];
					score = occupyCount(board);
					int us = score[0] + score[1] + score[2];
					int enemy = score[3] + score[4] + score[5];
					if (us > tempUs) {
						occupy.add(behavior.getBehavior(a));
						int[] tempCount = { (us - tempUs) - (enemy - tempEnemy), enemy - tempEnemy };
						countOccupyCells.add(tempCount);
					}
				}
			}

		}
		}
		return occupy;
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

	public int[] getMyLocation() {
		return myLocation;
	}

	public int getID() {
		return ID;
	}

}
