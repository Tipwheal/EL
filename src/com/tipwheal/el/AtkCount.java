package com.tipwheal.el;

public class AtkCount {
	private GameInfo info;

	public AtkCount(GameInfo info) {
		this.info = info;
	}

	/**
	 * 能够攻击到敌人。
	 * 
	 * @param action
	 * @param x
	 * @param y
	 * @param ID
	 * @param side
	 * @return
	 */
	public boolean canAtk(String action, int x, int y, int ID, int side) {
		for (int i = 3; i < 6; i++) {
			if (this.canAtk(action, x, y, ID, i, side)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 能够攻击到某个敌人。
	 * 
	 * @param action
	 * @param x
	 * @param y
	 * @param ID
	 * @param enemyID
	 * @return
	 */
	public boolean canAtk(String action, int x, int y, int ID, int enemyID, int side) {
		int[] enemyLoc = new int[2];
		enemyLoc[0] = info.getSamuraiInfo()[enemyID].getCurX();
		enemyLoc[1] = info.getSamuraiInfo()[enemyID].getCurY();
		if (enemyLoc[0] == -1) {
			return false;
		}
		if (ActImit.isOthersHome(enemyLoc, ID, side)) {
			return false;
		}
		for (int[] loc : ActImit.getOcyCells(action, x, y, ID, side)) {
			if (loc[0] == enemyLoc[0] && loc[1] == enemyLoc[1]) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 下回合能否打到敌人。
	 * 
	 * @param action
	 * @param x
	 * @param y
	 * @param ID
	 * @param side
	 * @return
	 */
	public boolean canAtkNextTurn(String action, int x, int y, int ID, int side) {
		for (int i = 3; i < 6; i++) {
			if (this.canAtkNextTurn(action, x, y, ID, i, side)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 下回合是否能打到某个敌人。
	 * 
	 * @param action
	 * @param x
	 * @param y
	 * @param ID
	 * @param enemyID
	 * @param side
	 * @return
	 */
	public boolean canAtkNextTurn(String action, int x, int y, int ID, int enemyID, int side) {
		int[] loc = ActImit.getNextCell(action, x, y);
		int[] enemyLoc = new int[2];
		enemyLoc[0] = info.getSamuraiInfo()[enemyID].getCurX();
		enemyLoc[1] = info.getSamuraiInfo()[enemyID].getCurY();
		if (enemyLoc[0] == -1) {
			return false;
		}
		if (ActImit.isOthersHome(enemyLoc, ID, side)) {
			return false;
		}
		for (int[] locs : ActImit.psbAtkArea(loc[0], loc[1], enemyID, side)) {
			if (locs[0] == enemyLoc[0] && locs[1] == enemyLoc[1]) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 第一次看见了敌人并且隐身。
	 * 
	 * @param action
	 * @param x
	 * @param y
	 * @param ID
	 * @param side
	 * @return
	 */
	public boolean seeAndHide(String action, int x, int y, int ID, int side) {
		for (int i = 3; i <= 5; i++) {
			if (this.seeAndHide(action, x, y, ID, i, side)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 第一次看见了某个敌人并且隐身。
	 * 
	 * @param action
	 * @param x
	 * @param y
	 * @param ID
	 * @param enemyID
	 * @param side
	 * @return
	 */
	public boolean seeAndHide(String action, int x, int y, int ID, int enemyID, int side) {
		MemoryInfo mi = info.getMemory();
		if (mi.getLastX(enemyID) != -1) {
			return false;
		}
		int enemyX = info.getSamuraiInfo()[enemyID].getCurX();
		int enemyY = info.getSamuraiInfo()[enemyID].getCurY();
		if (enemyX != -1 && ActImit.getDistence(x, y, enemyY, enemyY) <= 5 && action.endsWith("9")) {
			return true;
		}
		return false;
	}

	/**
	 * 按照打击能力判断是否靠近了敌人。
	 * 
	 * @param action
	 * @param x
	 * @param y
	 * @param ID
	 * @param side
	 * @return
	 */
	public boolean getCloseTo(String action, int x, int y, int ID, int side) {
		if (ID == 2) {
			return false;
		}
		if (ID == 1) {
			return this.getCloseTo(action, x, y, ID, 5, side);
		}
		if (ID == 0) {
			for (int i = 4; i <= 5; i++) {
				if (this.getCloseTo(action, x, y, ID, i, side)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 靠近了某个敌人。
	 * 
	 * @param action
	 * @param x
	 * @param y
	 * @param ID
	 * @param enemyID
	 * @param side
	 * @return
	 */
	public boolean getCloseTo(String action, int x, int y, int ID, int enemyID, int side) {
		int ex = info.getSamuraiInfo()[enemyID].getCurX();
		int ey = info.getSamuraiInfo()[enemyID].getCurY();
		if (ex == -1) {
			return false;
		}
		int curDis = ActImit.getDistence(ex, ey, x, y);
		int[] loc = ActImit.getNextCell(action, x, y);
		if (ActImit.getDistence(ex, ex, loc[0], loc[1]) > curDis) {
			return true;
		}
		return false;
	}
}
