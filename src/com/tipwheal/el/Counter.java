package com.tipwheal.el;

public class Counter {
	private GameInfo gi = new GameInfo();
	private Behavior behavior = new Behavior(gi.getSamuraiInfo()[gi.getWeapon()].getHidden());
	private double[] score = new double[behavior.behaviors.size()];
	private UsefulInfo ui = new UsefulInfo();
	private ActionFilter filter = new ActionFilter();
	private AttackInOneTurn aiot = new AttackInOneTurn();
	private JudgeOfTwoTurnHurt jotth = new JudgeOfTwoTurnHurt();

	public void ai() {
		for (int index = 0; index < score.length; index++) {
			if (filter.canAttack(gi.getWeapon()).contains(behavior.getBehavior(index))) {
				score[index] += 10;
			}
			if (filter.avoidEnemyField(gi.getWeapon(), 3).contains(behavior.getBehavior(index))) {
				score[index] += gi.getWeapon() * 0.5;
			}
			if (gi.getWeapon() == 2
					&& filter.avoidEnemyField(gi.getWeapon(), 4).contains(behavior.getBehavior(index))) {
				score[index] += 0.5;
			}
			if (filter.chaseSameEnemy(gi.getWeapon(), 5).contains(behavior.getBehavior(index))) {
				score[index] += (2 - gi.getWeapon()) * 0.5;
			}
			if (gi.getWeapon() == 0 && filter.chaseSameEnemy(gi.getWeapon(), 4).contains(behavior.getBehavior(index))) {
				score[index] += 0.5;
			}
			if (aiot.getOutOfEnemyAtkArea(gi.getWeapon()).contains(behavior.getBehavior(index))) {
				score[index] += 10;
			}
			//wo men xu yao xiu gai fang fa.dan shi xian zai zhixiang ceshi.
			if (aiot.getFarFromEnemyAtkArea(gi.getWeapon()).contains(behavior.getBehavior(index))) {
				score[index] += 1;
			}
			//wo men xu yao xiu gai fang fa.dan shi xian zai zhixiang ceshi.
			if (aiot.getCloseToEnemyAtkArea(gi.getWeapon()).contains(behavior.getBehavior(index))) {
				score[index] += 1;
			}
			if(jotth.judgeOfTwoTurnHurt().contains(behavior.getBehavior(index))) {
				score[index] += 5;
			}
		}
	}
	
	public double[] getScore() {
		this.ai();
		return score;
	}
}
