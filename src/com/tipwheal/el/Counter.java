package com.tipwheal.el;

public class Counter {
	private GameInfo gi;
	private Behavior behavior;
	private double[] score;
	private ActionFilter filter;
	private OccupyCount2 oc;

	public Counter(GameInfo info) {
		gi = info;
		behavior = new Behavior(gi.getSamuraiInfo()[gi.getWeapon()].getHidden());
		score = new double[behavior.behaviors.size()];
		filter = new ActionFilter(gi);
		oc = new OccupyCount2(info);
	}

	/**
	 * Test all possible actions and get their scores.
	 */
	private void ai() {
		for (int index = 0; index < score.length; index++) {
			String action = behavior.getBehavior(index);
			score[index] += oc.getOcpNum(action, gi.getWeapon());
			if (filter.canAtk(action, gi.getWeapon())) {
				// score[index] += 10;
			}
			if (filter.avoidField(action, gi.getWeapon(), 3)) {
				score[index] += gi.getWeapon() * 10;
			}
			if (gi.getWeapon() == 2 && filter.avoidField(action, gi.getWeapon(), 4)) {
				score[index] += 10;
			}
		}
	}

	/**
	 * Get score after test.
	 * 
	 * @return score.
	 */
	public double[] getScore() {
		this.ai();
		return score;
	}

	/**
	 * Get behaviors.
	 * 
	 * @return behavior.
	 */
	public Behavior getBehavior() {
		return behavior;
	}
}
