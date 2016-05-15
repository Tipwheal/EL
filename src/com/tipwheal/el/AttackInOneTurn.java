package com.tipwheal.el;

import java.util.ArrayList;

/**
 * Attack in one turn is about the cells that an ai can attack in one turn.
 * 
 * @author Yzh
 *
 */
public class AttackInOneTurn {
	UsefulInfo info = new UsefulInfo();

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
}
