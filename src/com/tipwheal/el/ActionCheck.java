package com.tipwheal.el;

public class ActionCheck {
	private GameInfo info;

	public ActionCheck(GameInfo info) {
		this.info = info;
	}

	/**
	 * 检查行为是否合法。
	 * 
	 * @param action
	 * @param x
	 * @param y
	 * @param hid
	 * @param ID
	 * @return
	 */
	public boolean validAction(String action, int x, int y, int hid, int ID) {
		String[] actions = action.split(" ");
		int[] loc = new int[2];
		loc[0] = x;
		loc[1] = y;
		for (String s : actions) {
			int d = Integer.parseInt(s);
			if (d >= 5 && d <= 8) {
				loc = ActImit.getNextCell(d - 5, loc[0], loc[1]);
				if (loc == null) {
					return false;
				}
				if (this.isOthersHome(loc, ID)) {
					return false;
				}
				if (hid == 1 && !isFriendField(loc)) {
					return false;
				}
			}
			if (d >= 1 && d <= 4) {
				if (hid == 1) {
					return false;
				}
			}
			if (d == 9) {
				if (hid == 1) {
					return false;
				}
				hid = 1;
			}
			if (d == 10) {
				if (hid == 0) {
					return false;
				}
				hid = 0;
			}
		}
		return true;
	}

	/**
	 * 检查是不是别人的家。
	 * 
	 * @param loc
	 * @param ID
	 * @return
	 */
	private boolean isOthersHome(int[] loc, int ID) {
		int[][] home = { { 14, 9 }, { 14, 0 }, { 5, 0 }, { 0, 5 }, { 0, 14 }, { 9, 14 } };
		for (int i = 0; i < 6; i++) {
			if (i != ID) {
				if (home[i][0] == loc[0] && home[i][1] == loc[1]) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 检查是否友方地盘。
	 * 
	 * @param loc
	 */
	private boolean isFriendField(int[] loc) {
		int[][] field = info.getField();
		if (field[loc[0]][loc[1]] >= 0 && field[loc[0]][loc[1]] <= 2) {
			return true;
		}
		return false;
	}
}
