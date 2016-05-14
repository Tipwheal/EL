package com.tipwheal.el;

import java.util.Random;

public class GreedyPlayer extends Player{
	private final int[] cost = { 0, 4, 4, 4, 4, 2, 2, 2, 2, 1, 1 };
	private final int maxPower = 7;
	private Random rnd;

	public GreedyPlayer() {
		rnd = new Random();
	}

	public GameInfo play(GameInfo info) {
		return null;
	}

	public GameInfo play2(GameInfo info) {
		int power = maxPower;
		int action;

		while (power >= 2) {
			action = rnd.nextInt(10) + 1;
			if (cost[action] <= power && info.isValid(action)) {
				power -= cost[action];
				info.doAction(action);
			}
		}

		if (power != 0) {
			if (rnd.nextInt(11) > 8) {
				action = info.getSamuraiInfo()[info.getWeapon()].getHidden() == 1 ? 10 : 9;
				if (info.isValid(action)) {
					info.doAction(action);
				}
			}
		}

		return new GameInfo(info);
	}
}
