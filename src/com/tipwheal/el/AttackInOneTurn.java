package com.tipwheal.el;

import java.util.ArrayList;

/**
 * Attack in one turn is about the cells that an ai can attack in one turn.
 * 
 * @author Yzh
 *
 */
public class AttackInOneTurn {
	private UsefulInfo info = new UsefulInfo();
	private Behavior behavior = new Behavior(info.getSamuraiInfo(info.getWeapon()).getHidden());
	private ActionFilter filter = new ActionFilter();

	/**
	 * Get possible locations a samurai can reach if it occupy some cells in
	 * this turn.<br>
	 * It means that the samurai can move one or no steps.So this method will
	 * return a list that contains no more than five cells.
	 * 
	 * @param curLoc
	 *            Current lovation.
	 * @return A list of cells.
	 */
	private ArrayList<int[]> locsCanReach(int[] curLoc) {
		ArrayList<int[]> result = new ArrayList<>();
		int x = curLoc[0];
		int y = curLoc[1];
		for (int i = 0; i < 5; i++) {
			int[] newLoc = new int[2];
			newLoc[0] = x;
			newLoc[1] = y;
			if (i == 0) {
				result.add(newLoc);
			} else {
				newLoc = move(i, newLoc);
				if (newLoc != null) {
					result.add(newLoc);
				}
			}
		}
		return result;
	}

	/**
	 * Move one cell and get final location.
	 * 
	 * @param direction
	 *            The direction to go.
	 * @param loc
	 *            The current location.
	 * @return A new location.
	 */
	private int[] move(int direction, int[] loc) {
		int[] result = new int[2];
		result[0] = loc[0];
		result[1] = loc[1];
		switch (direction) {
		case 1:
			result[1]++;
			if (result[1] > 14) {
				return null;
			}
			break;
		case 2:
			result[0]++;
			if (result[0] > 14) {
				return null;
			}
			break;
		case 3:
			result[1]--;
			if (result[1] < 0) {
				return null;
			}
			break;
		case 4:
			result[0]--;
			if (result[0] < 0) {
				return null;
			}
			break;
		}
		return result;
	}

	/**
	 * Get possible attack cells for a specific samurai in one turn.<br>
	 * Give a position of samurai and it's weapon id, then the method will
	 * return all cells that the samurai can attack.
	 * 
	 * @param curLoc
	 *            Current location of samurai.
	 * @param weapon
	 *            The weapon of samurai.
	 * @return A list of locations.
	 */
	public ArrayList<int[]> posAtkOneTurn(int[] curLoc, int weapon) {
		ArrayList<int[]> result = new ArrayList<>();
		for (int[] pos : locsCanReach(curLoc)) {
			for (int[] loc : info.getPosAtkCells(pos, weapon)) {
				if (!result.contains(loc)) {
					result.add(loc);
				}
			}
		}
		return result;
	}

	/**
	 * Get actions after doing which there will be some enemies in the area you
	 * can attack next turn.
	 * 
	 * @return A list of action strings.
	 */
	public ArrayList<String> enemyInAtkArea() {
		ArrayList<String> result = new ArrayList<>();
		for (int i = 3; i < 6; i++) {
			for (String action : enemyInAtkArea(i)) {
				if (!result.contains(action)) {
					result.add(action);
				}
			}
		}
		return result;
	}

	/**
	 * Get actions after doing which there will be a specific enemy in the area
	 * you can attack next turn.
	 * 
	 * @param enemy
	 *            The samurai you want to get close to so you can attack.
	 * @return A list of action strings.
	 */
	public ArrayList<String> enemyInAtkArea(int enemy) {
		ArrayList<String> result = new ArrayList<>();
		if (info.getSamuraiLocation(enemy) == null) {
			return null;
		} else {
			for (String action : behavior.getBehaviors()) {
				int[] loc = filter.getNextLocation(action, info.getSamuraiLocation(info.getWeapon()));
				ArrayList<int[]> area = posAtkOneTurn(loc, info.getWeapon());
				if (area.contains(info.getSamuraiLocation(enemy))) {
					result.add(action);
				}
			}
		}
		return result;
	}

	/**
	 * Get actions after doing which you will go into the area that will be
	 * attack by some enemy.
	 * 
	 * @return A list of action strings.
	 */
	public ArrayList<String> inEnemyAtkArea() {
		ArrayList<String> result = new ArrayList<>();
		for (int i = 3; i < 6; i++) {
			for (String action : inEnemyAtkArea(i)) {
				if (!result.contains(action)) {
					result.add(action);
				}
			}
		}
		return result;
	}

	/**
	 * Get actions after doing which you will go into the area that will be
	 * attack by a specific enemy.
	 * 
	 * @param enemy
	 *            The weapon ID of enemy.
	 * @return A list of action strings.
	 */
	public ArrayList<String> inEnemyAtkArea(int enemy) {
		ArrayList<String> result = new ArrayList<>();
		if (info.getSamuraiLocation(enemy) == null) {
			return null;
		} else {
			for (String action : behavior.getBehaviors()) {
				int[] loc = filter.getNextLocation(action, info.getSamuraiLocation(info.getWeapon()));
				ArrayList<int[]> area = this.posAtkOneTurn(info.getSamuraiLocation(enemy), enemy);
				if (area.contains(loc)) {
					result.add(action);
				}
			}
		}
		return result;
	}

	/**
	 * Get actions that, if your samurai is now in the attack area of an enemy,
	 * then get out of this area.
	 * 
	 * @param yourself
	 *            The weapon ID of your samurai.
	 * @return A list of action strings.
	 */
	public ArrayList<String> getOutOfEnemyAtkArea(int yourself) {
		ArrayList<String> result = new ArrayList<>();
		boolean in = false;
		for (int i = 3; i < 6; i++) {
			if (posAtkOneTurn(info.getSamuraiLocation(i), i).contains(info.getSamuraiLocation(yourself))) {
				in = true;
			}
		}
		if (in) {
			int[] curLoc = info.getSamuraiLocation(yourself);
			for (String action : behavior.getBehaviors()) {
				int[] loc = filter.getNextLocation(action, curLoc);
				for (int i = 3; i < 6; i++) {
					if ((!posAtkOneTurn(info.getSamuraiLocation(i), i).contains(loc)) && (!result.contains(action))) {
						result.add(action);
					}
				}
			}
		}
		return result;
	}

	/**
	 * Get actions after doing which you will get far from enemy attack area.
	 * 
	 * @param yourself
	 *            The samurai ID of yourself.
	 * @return A list of action strings.
	 */
	public ArrayList<String> getFarFromEnemyAtkArea(int yourself) {
		ArrayList<String> result = new ArrayList<>();
		OccupyArea area = new OccupyArea(info.getSamuraiLocation(yourself), yourself);
		for (int[] loc : area.Manhattan(info.getSamuraiLocation(yourself), 5)) {
			ArrayList<Integer> enemies = new ArrayList<>();
			for (int i = 3; i < 6; i++) {
				if (info.getSamuraiLocation(i) == null) {
					break;
				}
				for (int[] loc1 : posAtkOneTurn(info.getSamuraiLocation(i), i)) {
					if (loc[0] == loc1[0] && loc[1] == loc1[1]) {
						enemies.add(i);
						break;
					}
				}
			}
			for (int i : enemies) {
				int[] yourLoc = info.getSamuraiLocation(yourself);
				int[] enemyLoc = info.getSamuraiLocation(i);
				int distence = Math.abs(yourLoc[0] - enemyLoc[0]) + Math.abs(yourLoc[1] - enemyLoc[1]);
				for (String action : behavior.getBehaviors()) {
					int[] nextLoc = filter.getNextLocation(action, yourLoc);
					if (Math.abs(nextLoc[0] - enemyLoc[0]) + Math.abs(nextLoc[1] - enemyLoc[1]) > distence
							&& !posAtkOneTurn(enemyLoc, i).contains(nextLoc)) {
						if (!result.contains(action)) {
							result.add(action);
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * Get actions that you can get far from a specific enemy after doiong the
	 * actions.
	 * 
	 * @param yourself
	 *            The weapon id of yourself.
	 * @param enemy
	 *            The weapon id of enemy.
	 * @return A list of action strings.
	 */
	public ArrayList<String> getFarFromEnemyAtkArea(int yourself, int enemy) {
		ArrayList<String> result = new ArrayList<>();
		if (info.getSamuraiLocation(enemy) == null) {
			return null;
		}
		OccupyArea area = new OccupyArea(info.getSamuraiLocation(yourself), yourself);
		boolean in = false;
		for (int[] loc : area.Manhattan(info.getSamuraiLocation(yourself), 5)) {
			for (int[] loc1 : posAtkOneTurn(info.getSamuraiLocation(enemy), enemy)) {
				if (loc[0] == loc1[0] && loc[1] == loc1[1]) {
					in = true;
					break;
				}
			}
			if (in) {
				break;
			}
		}
		if (in) {
			int[] yourLoc = info.getSamuraiLocation(yourself);
			int[] enemyLoc = info.getSamuraiLocation(enemy);
			int distence = Math.abs(yourLoc[0] - enemyLoc[0]) + Math.abs(yourLoc[1] - enemyLoc[1]);
			for (String action : behavior.getBehaviors()) {
				int[] nextLoc = filter.getNextLocation(action, yourLoc);
				if (Math.abs(nextLoc[0] - enemyLoc[0]) + Math.abs(nextLoc[1] - enemyLoc[1]) > distence
						&& !posAtkOneTurn(enemyLoc, enemy).contains(nextLoc)) {
					if (!result.contains(action)) {
						result.add(action);
					}
				}
			}
		}
		return result;
	}

	/**
	 * Get actions after doing which you will get close to enemy attack area.
	 * 
	 * @param yourself
	 *            The samurai ID of yourself.
	 * @return A list of action strings.
	 */
	public ArrayList<String> getCloseToEnemyAtkArea(int yourself) {
		ArrayList<String> result = new ArrayList<>();
		OccupyArea area = new OccupyArea(info.getSamuraiLocation(yourself), yourself);
		for (int[] loc : area.Manhattan(info.getSamuraiLocation(yourself), 5)) {
			ArrayList<Integer> enemies = new ArrayList<>();
			for (int i = 3; i < 6; i++) {
				if (info.getSamuraiLocation(i) == null) {
					break;
				}
				for (int[] loc1 : posAtkOneTurn(info.getSamuraiLocation(i), i)) {
					if (loc[0] == loc1[0] && loc[1] == loc1[1]) {
						enemies.add(i);
						break;
					}
				}
			}
			for (int i : enemies) {
				int[] yourLoc = info.getSamuraiLocation(yourself);
				int[] enemyLoc = info.getSamuraiLocation(i);
				int distence = Math.abs(yourLoc[0] - enemyLoc[0]) + Math.abs(yourLoc[1] - enemyLoc[1]);
				for (String action : behavior.getBehaviors()) {
					int[] nextLoc = filter.getNextLocation(action, yourLoc);
					if (Math.abs(nextLoc[0] - enemyLoc[0]) + Math.abs(nextLoc[1] - enemyLoc[1]) < distence
							&& !posAtkOneTurn(enemyLoc, i).contains(nextLoc)) {
						if (!result.contains(action)) {
							result.add(action);
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * Get actions that you can get close to a specific enemy after doiong the
	 * actions.
	 * 
	 * @param yourself
	 *            The weapon id of yourself.
	 * @param enemy
	 *            The weapon id of enemy.
	 * @return A list of action strings.
	 */
	public ArrayList<String> getCloseToEnemyAtkArea(int yourself, int enemy) {
		ArrayList<String> result = new ArrayList<>();
		if (info.getSamuraiLocation(enemy) == null) {
			return null;
		}
		OccupyArea area = new OccupyArea(info.getSamuraiLocation(yourself), yourself);
		boolean in = false;
		for (int[] loc : area.Manhattan(info.getSamuraiLocation(yourself), 5)) {
			for (int[] loc1 : posAtkOneTurn(info.getSamuraiLocation(enemy), enemy)) {
				if (loc[0] == loc1[0] && loc[1] == loc1[1]) {
					in = true;
					break;
				}
			}
			if (in) {
				break;
			}
		}
		if (in) {
			int[] yourLoc = info.getSamuraiLocation(yourself);
			int[] enemyLoc = info.getSamuraiLocation(enemy);
			int distence = Math.abs(yourLoc[0] - enemyLoc[0]) + Math.abs(yourLoc[1] - enemyLoc[1]);
			for (String action : behavior.getBehaviors()) {
				int[] nextLoc = filter.getNextLocation(action, yourLoc);
				if (Math.abs(nextLoc[0] - enemyLoc[0]) + Math.abs(nextLoc[1] - enemyLoc[1]) < distence
						&& !posAtkOneTurn(enemyLoc, enemy).contains(nextLoc)) {
					if (!result.contains(action)) {
						result.add(action);
					}
				}
			}
		}
		return result;
	}
}
