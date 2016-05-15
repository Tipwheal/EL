package com.tipwheal.el;

import java.util.ArrayList;

/**
 * ActionFilter is to select some actions that meet your requirement.
 * 
 * @author Yzh
 *
 */
public class ActionFilter {
	private UsefulInfo ui = new UsefulInfo();
	private Behavior behavior = new Behavior(ui.getSamuraiInfo(ui.getWeapon()).getHidden());
	private int[][] field;
	private int[] curLocation;

	/**
	 * Constructs an <tt>AnctionFilter</tt>.
	 */
	public ActionFilter() {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				field[i][j] = field[i][j];
			}
		}
		curLocation = ui.getSamuraiLocation(ui.getWeapon());
	}

	/**
	 * Get behaviors that you can attack an ememy if you do it.
	 * 
	 * @param weapon
	 *            The weapon ID of yourself.
	 * @return A list of behavior String.
	 */
	public ArrayList<String> canAttack(int weapon) {
		ArrayList<String> result = new ArrayList<>();
		for (String action : behavior.getBehaviors()) {
			ArrayList<int[]> occupied = getNextOccupiedLocation(action, curLocation, ui.getWeapon());
			for (int i = 3; i < 6; i++) {
				if (occupied.contains(ui.getSamuraiLocation(i)) && !result.contains(action)) {
					result.add(action);
				}
			}
		}
		return result;
	}

	/**
	 * Get actions by which a samurai can attack another specific samurai.
	 * 
	 * @param yourself
	 *            The samurai to do the action.
	 * @param enemy
	 *            The samurai to be attack.
	 * @return All action strings that meet the requirements.
	 */
	public ArrayList<String> canAttack(int yourself, int enemy) {
		ArrayList<String> result = new ArrayList<>();
		for (String action : behavior.getBehaviors()) {
			ArrayList<int[]> occupied = getNextOccupiedLocation(action, ui.getSamuraiLocation(yourself), yourself);
			if (occupied.contains(ui.getSamuraiLocation(enemy)) && !result.contains(action)) {
				result.add(action);
			}
		}
		return result;
	}

	/**
	 * Get behaviors that you can avoid an specific enemy if you do them.
	 * 
	 * @param yourself
	 *            The samurai ID to avoid enemy.
	 * @param enemy
	 *            The weapon ID of the enemy.
	 * @return A list of action strings.
	 */
	public ArrayList<String> avoidEnemy(int yourself, int enemy) {
		ArrayList<String> result = new ArrayList<>();
		int[] curLocYourself = ui.getSamuraiLocation(yourself);
		int[] locEnemy = ui.getSamuraiLocation(enemy);
		if(locEnemy[0] == -1) {
			return null;
		}
		int curDistance = Math.abs(curLocYourself[0] - locEnemy[0]) + Math.abs(curLocYourself[1] - locEnemy[1]);
		int finalDistance;
		for (String action : behavior.getBehaviors()) {
			int[] location = this.getNextLocation(action, ui.getSamuraiLocation(yourself));
			finalDistance = Math.abs(location[0] - locEnemy[0]) + Math.abs(location[1] - locEnemy[1]);
			if (curDistance < finalDistance) {
				result.add(action);
			}
		}
		return result;
	}

	/**
	 * Analyze a String of actions and return the location after the samurai do
	 * this action.
	 * 
	 * @param actionLine
	 *            The String of acitons to be analyze.
	 * @param curLoc
	 *            The current location of a samurai ai.
	 * @param weapon
	 *            The weapon ID of the samurai.
	 * @return An int[] that content the final location.
	 */
	public int[] getNextLocation(String actionLine, int[] curLoc) {
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

	/**
	 * Analyze a String of actions and return occupied cells if a samurai do
	 * this action.
	 * 
	 * @param actionLine
	 *            The action String to be analyze.
	 * @param curLoc
	 *            The current location of a samurai.
	 * @param weapon
	 *            The weapon of the samurai.
	 * @return
	 */
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
