package com.tipwheal.el;

import java.util.*;

public class UsefulInfo {
	private GameInfo info = new GameInfo();
	private int[] location;

	/**
	 * Constructs a UsefulInfo and initialize location a new int[2].
	 */
	public UsefulInfo() {
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
	public UsefulInfo(int x, int y) {
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

	public int[][] getOccupiedCells(int weapon) {
		int[][] field = info.getField();
		ArrayList<int[]> cells = new ArrayList<>();
		for (int i = 0; i < 15; i++) {
			for (int j = 0; i < 15; j++) {
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
						set.add(result);
					}
				}
			}
			return set;
		}
	}

	public int[][] getField() {
		return info.getField();
	}

	public int getWeapon() {
		return info.getWeapon();
	}

	public int getTurn() {
		return info.getTurn();
	}

	public SamuraiInfo getSamuraiInfo(int weapon) {
		return info.getSamuraiInfo()[weapon];
	}
}
