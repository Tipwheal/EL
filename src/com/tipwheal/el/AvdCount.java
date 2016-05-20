package com.tipwheal.el;

public class AvdCount {
	private GameInfo info;

	public AvdCount(GameInfo info) {
		this.info = info;
	}

	/**
	 * 是否进入了危险区域。
	 * 
	 * @param action
	 * @param x
	 * @param y
	 * @param ID
	 * @return
	 */
	public boolean intoDgrArea(String action, int x, int y, int ID, int side) {
		for (int i = 3; i < 6; i++) {
			if (this.inAtkArea(action, x, y, ID, i, side)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否进入某敌人打击范围。
	 * 
	 * @param action
	 * @param x
	 * @param y
	 * @param ID
	 * @param enemyID
	 * @return
	 */
	private boolean inAtkArea(String action, int x, int y, int ID, int enemyID, int side) {
		if (info.getSamuraiInfo()[enemyID].getCurX() == -1) {
			return false;
		} else {
			int[] loc = new int[2];
			loc[0] = x;
			loc[1] = y;
			for (String s : action.split(" ")) {
				int d = Integer.parseInt(s);
				if (d >= 5 && d <= 8) {
					loc = ActImit.getNextCell(d - 5, loc[0], loc[1]);
					for (int[] dgr : ActImit.psbAtkArea(info.getSamuraiInfo()[enemyID].getCurX(),
							info.getSamuraiInfo()[enemyID].getCurY(), enemyID, side)) {
						if (loc[0] == dgr[0] && loc[1] == dgr[1]) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

}
