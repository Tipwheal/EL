package com.tipwheal.el;

import java.util.Random;

public class ScoreCounter {
	private GameInfo info;
	private ActionLibrary lib;
	private double[] scores;
	private Random rnd;

	/**
	 * 计算总分。
	 * 
	 * @param info
	 */
	public ScoreCounter(GameInfo info) {
		this.info = info;
		lib = new ActionLibrary(this.info);
		scores = new double[lib.getNum()];
		rnd = new Random();
		OcpCount ocp = new OcpCount(this.info);
		AtkCount atk = new AtkCount(this.info);
		AvdCount avd = new AvdCount(this.info);
		OutHomeCount out = new OutHomeCount();

		int ID = this.info.getWeapon();
		int side = this.info.getSide();
		int x = this.info.getSamuraiInfo()[ID].getCurX();
		int y = this.info.getSamuraiInfo()[ID].getCurY();

		for (int i = 0; i < lib.getNum(); i++) {
			String action = lib.getActions(i);
			scores[i] += ocp.getOcpCounts(lib.getActions(i), x, y, ID, side);
			if (avd.intoDgrArea(action, x, y, ID, side) && !atk.canAtk(action, x, y, ID, 3, side)
					&& !atk.canAtk(action, x, y, ID, 4, side) && !atk.canAtk(action, x, y, ID, 5, side)) {
				scores[i] -= 5;
			}
			if (atk.canAtk(action, x, y, ID, 3, side)) {
				scores[i] += 10;
			}
			if (atk.canAtk(action, x, y, ID, 4, side)) {
				scores[i] += 10;
			}
			if (atk.canAtk(action, x, y, ID, 5, side)) {
				scores[i] += 10;
			}
			if (action.endsWith("9")) {
				scores[i] += 1;
			}
			if (out.outHome(lib.getActions(i), x, y, 1, side)) {
				scores[i] += 0.5;
			}
		}
	}

	/**
	 * 得到分数最高的行为。
	 * 
	 * @return
	 */
	public String getAction() {
		double max = 0;
		int index = 0;
		for (int i = 0; i < scores.length; i++) {
			if (scores[i] > max) {
				max = scores[i];
				index = i;
			} else if (scores[i] == max) {
				if (rnd.nextBoolean()) {
					max = scores[i];
					index = i;
				}
			}
		}
		return lib.getActions(index);
	}
}
