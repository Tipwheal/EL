package com.tipwheal.el;

import java.util.Random;

public class GreedyPlayer extends Player {
	private final int[] cost = { 0, 4, 4, 4, 4, 2, 2, 2, 2, 1, 1 };
	private final int maxPower = 7;
	private Random rnd;

	public GreedyPlayer() {
		rnd = new Random();
	}

	public GameInfo play(GameInfo info) {
		Counter counter = new Counter(info);
		int i = -1;
		double max = -1;
		double[] score = counter.getScore();
		for (int j = 0; j < score.length; j++) {
			if (score[j] > max) {
				i = j;
				max = score[j];
			}
		}
		String[] actions = counter.getBehavior().behaviors.get(i).split(" ");
		for (String action : actions) {
			if (action != null) {
				info.doAction(Integer.parseInt(action));
			}
		}
		return new GameInfo(info);
	}

	public GameInfo randomPlay(GameInfo info) {
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
