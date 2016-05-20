package com.tipwheal.el;

public class AtkCount {
	private GameInfo info;

	public AtkCount(GameInfo info) {
		this.info = info;
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
}
