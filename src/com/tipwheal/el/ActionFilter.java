package com.tipwheal.el;

import java.util.ArrayList;

/**
 * ActionFilter is to select some actions that meet your requirement.
 * 
 * @author Yzh
 *
 */
public class ActionFilter {
	private GameInfo gi;
	private UsefulInfo ui;
	private Behavior behavior;
	private int[][] field = new int[15][15];
	private int[] curLocation;

	public ActionFilter(GameInfo info) {
		gi = info;
		ui = new UsefulInfo(gi);
		behavior = new Behavior(ui.getSamuraiInfo(ui.getWeapon()).getHidden());
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

	public boolean canAtk(String action, int yourself) {
		for (int i = 3; i < 6; i++) {
			int[] enemyLoc = ui.getSamuraiLocation(i);
			if(enemyLoc != null) {
				ArrayList<int[]> occupied = getNextOccupiedLocation(action, ui.getSamuraiLocation(yourself), yourself);
				for(int j = 0;j<occupied.size();j++) {
					if(occupied.get(j)[0] == enemyLoc[0]&&occupied.get(j)[1] == enemyLoc[1]) {
						return true;
					}
				}
			}
		}
		return false;
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
		if (locEnemy[0] == -1) {
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
	 * Get behaviors that you can avoid an specific enemy if you do them.
	 * 
	 * @param yourself
	 *            The samurai ID to avoid enemy.
	 * @param enemy
	 *            The weapon ID of the enemy.
	 * @return A list of action strings.
	 */
	public ArrayList<String> avoidEnemyField(int yourself, int enemy) {
		ui.getOccupiedCells(enemy);
		ArrayList<String> result = new ArrayList<>();
		int[] curLocYourself = ui.getSamuraiLocation(yourself);
		int[] locEnemy = ui.getSamuraiLocation(enemy);
		int curDistance = 0;
		int finalDistance = 0;
		if (locEnemy == null) {
			return null;
		}
		for (int[] loc : ui.getOccupiedCells(enemy)) {
			curDistance += Math.abs(curLocYourself[0] - loc[0]) + Math.abs(curLocYourself[1] - loc[1]);
		}

		for (String action : behavior.getBehaviors()) {
			int[] location = this.getNextLocation(action, ui.getSamuraiLocation(yourself));
			for (int[] loc : ui.getOccupiedCells(enemy)) {
				finalDistance += Math.abs(location[0] - loc[0]) + Math.abs(location[1] - loc[1]);
			}
			if (curDistance < finalDistance) {
				result.add(action);
			}
		}
		return result;
	}

	public boolean avoidField(String action, int yourself, int enemy) {
		int[] yourLoc = ui.getSamuraiLocation(yourself);
		int[] enemyLoc = ui.getSamuraiLocation(enemy);
		if (enemyLoc == null) {
			return false;
		}
		int distence = Math.abs(yourLoc[0] - enemyLoc[0]) + Math.abs(yourLoc[1] - enemyLoc[1]);
		int[] finalLoc = this.getNextLocation(action, yourLoc);
		int finalDistence = Math.abs(finalLoc[0] - enemyLoc[0]) + Math.abs(finalLoc[1] - enemyLoc[1]);
		if (finalDistence > distence) {
			return true;
		}
		return false;
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
	public ArrayList<String> chaseSameEnemy(int yourself, int enemy) {
		ui.getOccupiedCells(enemy);
		ArrayList<String> result = new ArrayList<>();
		int[] curLocYourself = ui.getSamuraiLocation(yourself);
		int[] locEnemy = ui.getSamuraiLocation(enemy);
		int curDistance = 0;
		int finalDistance = 0;
		if (locEnemy[0] == -1) {
			return null;
		}
		for (int[] loc : ui.getOccupiedCells(enemy)) {
			curDistance += Math.abs(curLocYourself[0] - loc[0]) + Math.abs(curLocYourself[1] - loc[1]);
		}

		for (String action : behavior.getBehaviors()) {
			int[] location = this.getNextLocation(action, ui.getSamuraiLocation(yourself));
			for (int[] loc : ui.getOccupiedCells(enemy)) {
				finalDistance += Math.abs(location[0] - loc[0]) + Math.abs(location[1] - loc[1]);
			}
			if (curDistance > finalDistance) {
				result.add(action);
			}
		}
		return result;
	}

	/**
	 * Get behaviors that you can get close to an specific enemy if you do the
	 * actions.
	 * 
	 * @param yourself
	 *            The weapon ID of the samurai to get close to the enemy.
	 * @param enemy
	 *            The weapon ID of enemy.
	 * @return A list of action strings.
	 */
	public ArrayList<String> getCloseToEemy(int yourself, int enemy) {
		ArrayList<String> result = new ArrayList<>();
		int[] curLoc = ui.getSamuraiLocation(yourself);
		for (String action : behavior.getBehaviors()) {
			if (!curLoc.equals(getNextLocation(action, curLoc)) && !avoidEnemy(yourself, enemy).contains(action)) {
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
		String[] actions = actionLine.split(" ");
		System.err.println("getNextOccupiedLocation:Actions:");
		for(String s:actions) {
			System.err.print(s + " ");
		}
		System.err.println();
		int[] location = new int[2];
		location[0] = curLoc[0];
		location[1] = curLoc[1];
		ArrayList<int[]> occupied = new ArrayList<>();
		for (String s : actions) {
			int action = Integer.parseInt(s);
			System.err.println("now action: "+action);
			switch (action) {
			case 5:
				if (location[1] < 14) {
					location[1]++;
				}
				break;
			case 6:
				if (location[0] < 14) {
					location[0]++;
				}
				break;
			case 7:
				if (location[1] > 0) {
					location[1]--;
				}
				break;
			case 8:
				if (location[0] > 0) {
					location[0]--;
				}
				break;
			case 1:
				for (int[] loc : ui.getOccupiedCells(0, location, weapon)) {
					System.err.println("in5678: " + action);
					if (!occupied.contains(loc)) {
						occupied.add(loc);
						System.err.println("loc added in 5678: " + loc[0] + " " + loc[1]);
						System.err.println("and the state: " + ui.getField()[loc[1]][loc[0]]);
					}
				}
				break;
			case 2:
				for (int[] loc : ui.getOccupiedCells(1, location, weapon)) {
					System.err.println("in5678: " + action);
					if (!occupied.contains(loc)) {
						occupied.add(loc);
						System.err.println("loc added in 5678: " + loc[0] + " " + loc[1]);
						System.err.println("and the state: " + ui.getField()[loc[1]][loc[0]]);
					}
				}
				break;
			case 3:
				for (int[] loc : ui.getOccupiedCells(2, location, weapon)) {
					System.err.println("in5678: " + action);
					if (!occupied.contains(loc)) {
						occupied.add(loc);
						System.err.println("loc added in 5678: " + loc[0] + " " + loc[1]);
						System.err.println("and the state: " + ui.getField()[loc[1]][loc[0]]);
					}
				}
				break;
			case 4:
				for (int[] loc : ui.getOccupiedCells(3, location, weapon)) {
					System.err.println("in5678: " + action);
					if (!occupied.contains(loc)) {
						occupied.add(loc);
						System.err.println("loc added in 5678: " + loc[0] + " " + loc[1]);
						System.err.println("and the state: " + ui.getField()[loc[1]][loc[0]]);
					}
				}
				break;
			default:
				break;
			}
		}
		return occupied;
	}

	/**
	 * Occupy cells without move.
	 * 
	 * @author Zcq.
	 * 
	 * @return A list of action strings.
	 */
	public ArrayList<String> localOccupy() {
		Behavior behavior = new Behavior(ui.getSamuraiInfo(ui.getWeapon()).getHidden());
		String[] num = new String[behavior.getBehaviors().size()];
		int n1, n2, n3, n4;
		for (int i = 0; i < behavior.getBehaviors().size(); i++) {
			num[i] = behavior.getBehavior(i);
			n1 = num[i].indexOf("5");
			n2 = num[i].indexOf("6");
			n3 = num[i].indexOf("7");
			n4 = num[i].indexOf("8");
			if (n1 >= 0 || n2 >= 0 || n3 >= 0 || n4 >= 0) {
				num[i] = "flag";
			}
		}
		ArrayList<String> result = new ArrayList<String>();

		for (int i = 0; i < num.length; i++) {
			if (!num[i].equals("flag")) {
				result.add(num[i]);
			}
		}

		return result;
	}

}
