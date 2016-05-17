package com.tipwheal.el;

import java.util.*;

/**
 * Some useful info.
 * 
 * @author Yzh
 *
 */
public class UsefulInfo {
	private GameInfo info;
	private int[] location;

	/**
	 * Constructs a UsefulInfo and initialize location a new int[2].
	 */
	public UsefulInfo(GameInfo info) {
		this.info = info;
		location = new int[2];
	}

	/**
	 * Constructs a UsefulInfo and set location by parameters.
	 * 
	 * @param x
	 *            The second int in location.
	 * @param y
	 *            The first int in location.
	 */
	public UsefulInfo(GameInfo info, int x, int y) {
		this.info = info;
		int[] tmp = { x, y };
		location = tmp;
	}

	/**
	 * Get the location of a Samurai.
	 * 
	 * @param weapon
	 *            Samurai ID.
	 * @return return an int[].
	 */
	public int[] getSamuraiLocation(int weapon) {
		location[0] = info.getSamuraiInfo()[weapon].getCurX();
		location[1] = info.getSamuraiInfo()[weapon].getCurY();
		return location;
	}

	/**
	 * Get all ocupied cells by one samurai that you can see.
	 * 
	 * @param weapon
	 *            The weapon ID of samurai.
	 * @return return a list of cells.
	 */
	public int[][] getOccupiedCells(int weapon) {
		int[][] field = info.getField();
		ArrayList<int[]> cells = new ArrayList<>();
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (field[j][i] == weapon) {
					int[] tmp = { i, j };
					cells.add(tmp);
				}
			}
		}
		int[][] result = new int[cells.size()][];
		for (int i = 0; i < cells.size(); i++) {
			result[i] = cells.get(i);
		}
		return result;
	}

	/**
	 * Get a list of cells that a samurai can occupy by one occupy command.
	 * 
	 * @param direction
	 *            Occupy direction.
	 * @param curLoc
	 *            Current location of samurai.
	 * @param weapon
	 *            Weapon ID of samurai
	 * @return A list of cells that's occupied by this occupy action.
	 */
	public ArrayList<int[]> getOccupiedCells(int direction, int[] curLoc, int weapon) {
		int[] location = curLoc;
		ArrayList<int[]> list = new ArrayList<>();
		if (location[0] == -1) {
			return null;
		} else {
			int[] size = { 4, 5, 7 };
			int[][] possibleX = { { 0, 0, 0, 0 }, { 0, 0, 1, 1, 2 }, { -1, -1, -1, 0, 1, 1, 1 } };
			int[][] possibleY = { { 1, 2, 3, 4 }, { 1, 2, 0, 1, 0 }, { -1, 0, 1, 1, 1, -1, 0 } };
			for (int i = 0; i < size[weapon % 3]; i++) {
				int[] tmp = new int[2];
				tmp[0] = location[0] + possibleX[weapon % 3][i];
				tmp[1] = location[1] + possibleY[weapon % 3][i];
				tmp = info.rotate(direction, tmp[0], tmp[1]);
				if (tmp[0] >= 0 && tmp[0] <= 14 && tmp[1] >= 0 && tmp[1] <= 14) {
					if (!list.contains(tmp)) {
						list.add(tmp);
					}
				}
			}
			return list;
		}
	}

	/**
	 * Get a set of possible attack cells in one occupy action.
	 * 
	 * @param weapon
	 *            The weapon ID of Samurai whose possible attack cells wanted to
	 *            be known.
	 * @return A set of int[2].
	 */
	public TreeSet<int[]> getPossibleAttackCells(int weapon) {
		int[] location = getSamuraiLocation(weapon);
		TreeSet<int[]> set = new TreeSet<>();
		if (location[0] == -1) {
			return null;
		} else {
			int[] size = { 4, 5, 7 };
			int[][] possibleX = { { 0, 0, 0, 0 }, { 0, 0, 1, 1, 2 }, { -1, -1, -1, 0, 1, 1, 1 } };
			int[][] possibleY = { { 1, 2, 3, 4 }, { 1, 2, 0, 1, 0 }, { -1, 0, 1, 1, 1, -1, 0 } };
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < size[weapon % 3]; j++) {
					int[] result = info.rotate(i, possibleX[weapon % 3][j], possibleY[weapon % 3][j]);
					result[0] += location[0];
					result[1] += location[1];
					if (result[0] < 0 || result[0] > 14 || result[1] < 0 || result[1] > 14) {
						break;
					}
					for (int k = 0; k < 6; k++) {
						if (info.getSamuraiInfo()[k].getHomeX() == result[0]
								&& info.getSamuraiInfo()[k].getHomeY() == result[1]) {
							break;
						}
						set.add(result);
					}
				}
			}
			return set;
		}
	}

	/**
	 * Get possible attack cells in one occupy action.<br>
	 * 
	 * @param loc
	 *            The location of a samurai.
	 * @param weapon
	 *            The weapon ID of the samurai.
	 * @return A list of locations.
	 */
	public ArrayList<int[]> getPosAtkCells(int[] loc, int weapon) {
		ArrayList<int[]> list = new ArrayList<>();
		int[] location = new int[2];
		location[0] = loc[0];
		location[1] = loc[1];
		weapon = weapon % 3;
		int[] size = { 4, 5, 7 };
		int[][] possibleX = { { 0, 0, 0, 0 }, { 0, 0, 1, 1, 2 }, { -1, -1, -1, 0, 1, 1, 1 } };
		int[][] possibleY = { { 1, 2, 3, 4 }, { 1, 2, 0, 1, 0 }, { -1, 0, 1, 1, 1, -1, 0 } };
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < size[weapon]; j++) {
				int[] result = info.rotate(i, possibleX[weapon][j], possibleY[weapon][j]);
				result[0] += location[0];
				result[1] += location[1];
				if (result[0] < 0 || result[0] > 14 || result[1] < 0 || result[1] > 14) {
					break;
				}
				for (int k = 0; k < 6; k++) {
					if (info.getSamuraiInfo()[k].getHomeX() == result[0]
							&& info.getSamuraiInfo()[k].getHomeY() == result[1]) {
						break;
					}
					if (!list.contains(result)) {
						list.add(result);
					}
				}
			}
		}
		return list;
	}

	/**
	 * Get battle field.
	 * 
	 * @return int[][]
	 */
	public int[][] getField() {
		return info.getField();
	}

	/**
	 * Get weapon ID of the current samurai.
	 * 
	 * @return int weapon.
	 */
	public int getWeapon() {
		return info.getWeapon();
	}

	/**
	 * Get turn.
	 * 
	 * @return int turn.
	 */
	public int getTurn() {
		return info.getTurn();
	}

	/**
	 * Get samurai info according to weapon ID.
	 * 
	 * @param weapon
	 *            Weapon ID of samurai.
	 * @return SamuraiInfo.
	 */
	public SamuraiInfo getSamuraiInfo(int weapon) {
		return info.getSamuraiInfo()[weapon];
	}
}
