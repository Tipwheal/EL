package com.tipwheal.el;

public class Counter {
	private GameInfo gi;
	private Behavior behavior;
	private double[] score;
	private ActionFilter filter;
	private AttackInOneTurn aiot;
	private JudgeOfTwoTurnHurt jotth;
	
	public Counter(GameInfo info) {
		gi = info;
		behavior = new Behavior(gi.getSamuraiInfo()[gi.getWeapon()].getHidden());
		score = new double[behavior.behaviors.size()];
		filter = new ActionFilter(gi);
		aiot = new AttackInOneTurn(info);
		jotth = new JudgeOfTwoTurnHurt(info);
	}

	/**
	 * Test all possible actions and get their scores.
	 */
	private void ai() {
		for (int index = 0; index < score.length; index++) {
			if (filter.canAttack(gi.getWeapon()).contains(behavior.getBehavior(index))) {
				score[index] += 10;
			}
//			if (filter.avoidEnemyField(gi.getWeapon(), 3).contains(behavior.getBehavior(index))) {
//				score[index] += gi.getWeapon() * 0.5;
//			}
//			if (gi.getWeapon() == 2
//					&& filter.avoidEnemyField(gi.getWeapon(), 4).contains(behavior.getBehavior(index))) {
//				score[index] += 0.5;
//			}
//			if (filter.chaseSameEnemy(gi.getWeapon(), 5).contains(behavior.getBehavior(index))) {
//				score[index] += (2 - gi.getWeapon()) * 0.5;
//			}
			if (gi.getWeapon() == 0 && filter.chaseSameEnemy(gi.getWeapon(), 4).contains(behavior.getBehavior(index))) {
				score[index] += 0.5;
			}
			if (aiot.getOutOfEnemyAtkArea(gi.getWeapon()).contains(behavior.getBehavior(index))) {
				score[index] += 10;
			}
			if (aiot.getFarFromEnemyAtkArea(gi.getWeapon(), 3).contains(behavior.getBehavior(index))) {
				score[index] += gi.getWeapon();
			}
			if (gi.getWeapon() == 2
					&& aiot.getFarFromEnemyAtkArea(gi.getWeapon(), 4).contains(behavior.getBehavior(index))) {
				score[index] += 1;
			}
			if (aiot.getCloseToEnemyAtkArea(gi.getWeapon(), 5).contains(behavior.getBehavior(index))) {
				score[index] += (2 - gi.getWeapon());
			}
			if (gi.getWeapon() == 0
					&& aiot.getCloseToEnemyAtkArea(gi.getWeapon(), 4).contains(behavior.getBehavior(index))) {
				score[index] += 1;
			}
			if (jotth.judgeOfTwoTurnHurt().contains(behavior.getBehavior(index))) {
				score[index] += 5;
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
