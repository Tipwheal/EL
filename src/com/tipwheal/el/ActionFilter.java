package com.tipwheal.el;

import java.util.ArrayList;

public class ActionFilter {
	private UsefulInfo ui = new UsefulInfo();
	private Behavior behavior = new Behavior(ui.getSamuraiInfo(ui.getWeapon()).getHidden());
	private int[][] field;
	private int[] curLocation;

	public ActionFilter() {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				field[i][j] = field[i][j];
			}
		}
		curLocation = ui.getSamuraiLocation(ui.getWeapon());
	}

	public ArrayList<String> canAttack(int weapon) {
		ArrayList<String> result = new ArrayList<>();
		for (String action : behavior.getBehaviors()) {
			ArrayList<int[]> occupied = getNextOccupiedLocation(action, curLocation, ui.getWeapon());
			for (int i = 3; i < 6; i++) {
				if (occupied.contains(ui.getSamuraiLocation(i))&&!result.contains(action)) {
					result.add(action);
				}
			}
		}
		return null;
	}

	public int[] getNextLocation(String actionLine, int[] curLoc, int weapon) {
		int[] location = curLoc;
		String[] actions = actionLine.split(" ");
		for (String s : actions) {
			int action = Integer.parseInt(s);
			switch (action) {
			case 1:
				if (location[1] < 14) {
					location[1]++;
				}
				break;
			case 2:
				if (location[0] < 14) {
					location[0]++;
				}
				break;
			case 3:
				if (location[1] > 0) {
					location[1]--;
				}
				break;
			case 4:
				if (location[0] > 0) {
					location[0]--;
				}
				break;
			default:
				break;
			}
		}
		return location;
	}

	public ArrayList<int[]> getNextOccupiedLocation(String actionLine, int[] curLoc, int weapon) {
		String[] actions = actionLine.split(actionLine);
		int[] location = curLoc;
		ArrayList<int[]> occupied = new ArrayList<>();
		for (String s : actions) {
			int action = Integer.parseInt(s);
			switch (action) {
			case 1:
				if (location[1] < 14) {
					location[1]++;
				}
				break;
			case 2:
				if (location[0] < 14) {
					location[0]++;
				}
				break;
			case 3:
				if (location[1] > 0) {
					location[1]--;
				}
				break;
			case 4:
				if (location[0] > 0) {
					location[0]--;
				}
				break;
			case 5:
			case 6:
			case 7:
			case 8:
				for (int[] loc : ui.getOccupiedCells(action - 5, location, weapon)) {
					if (!occupied.contains(loc)) {
						occupied.add(loc);
					}
				}
				break;
			default:
				break;
			}
		}
		return occupied;
	}
}
