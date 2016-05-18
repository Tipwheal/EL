package com.tipwheal.el;

import java.util.ArrayList;

public class JudgeOfTwoTurnHurt {
	private GameInfo info;
	private Behavior behavior;
	private OccupyArea area;
	private int weapon;
	private int[] myLocation = new int[2];
	private int[] enemyLoca = new int[2];
	
	public JudgeOfTwoTurnHurt(GameInfo info) {
		this.info = info; 
	}

	/**
	 * Get actions that your samurai can attack enemy in two turns.
	 * 
	 * @return A list of actions.
	 */
	public ArrayList<String> judgeOfTwoTurnHurt() {
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> list1 = new ArrayList<String>();

		boolean isIn = false;
		boolean isArriavl = true;
		boolean isTurn = false;
		myLocation[0] = info.getSamuraiInfo()[weapon].getCurX();
		myLocation[1] = info.getSamuraiInfo()[weapon].getCurY();
		enemyLoca[0] = info.getSamuraiInfo()[weapon + 3].getCurX();
		enemyLoca[1] = info.getSamuraiInfo()[weapon + 3].getCurY();
		weapon = info.getWeapon();
		behavior = new Behavior(info.getSamuraiInfo()[weapon].getHidden());
		area = new OccupyArea(myLocation, weapon);

		// judge for isIn
		for (int i = 0; i < area.getBiggestOccupyArea(4).size(); i++) {
			if (area.getBiggestOccupyArea(4).get(i)[0] == enemyLoca[0]
					&& area.getBiggestOccupyArea(4).get(i)[1] == enemyLoca[1]) {
				isIn = true;
				break;
			}
		}

		for (int i = 0; i < area.getBiggestOccupyArea(1).size(); i++) {
			if (area.getBiggestOccupyArea(1).get(i)[0] == enemyLoca[0]
					&& area.getBiggestOccupyArea(1).get(i)[1] == enemyLoca[1]) {
				isIn = false;
			}
		}

		// judge for isTurn
		if (info.getSide() == 0) {
			for (int i = weapon + 7; i < info.getTurn(); i = i + 12) {
				if (info.getTurn() == i) {
					isTurn = true;
					break;
				}
			}
		} else {
			for (int i = weapon + 1; i < info.getTurn(); i = i + 12) {
				if (info.getTurn() == i) {
					isTurn = true;
					break;
				}
			}
		}

		// judge for isArravl
		for (int i = 0; i < behavior.behaviors.size(); i++) {
			String[] actions = behavior.getBehavior(i).split(" ");
			isArriavl = true;
			for (int j = 0; j < actions.length; j++) {
				if (!info.isValid(Integer.valueOf(actions[j]))) {
					isArriavl = false;
					break;
				}
			}
			if (isArriavl) {
				list1.add(behavior.getBehavior(i));
			}
		}
		for (int i = 0; i < behavior.behaviors.size(); i++) {
			list1.add(behavior.getBehavior(i));
		}

		if (isIn && isTurn) {
			for (int i = 0; i < list1.size(); i++) {
				for (int j = 0; j < area.getCanBeHertArea(enemyLoca).size(); j++) {
					Switcher switcher = new Switcher(list1.get(i), weapon);
					int[] a = switcher.getDoneLoca(myLocation);
					System.out.println(a[0] + " " + a[1] + " " + myLocation[1]);
					if ((a[0] == area.getCanBeHertArea(enemyLoca).get(j)[0])
							&& (a[1] == area.getCanBeHertArea(enemyLoca).get(j)[1])) {
						list.add(list1.get(i));
					}
				}
			}
		}
		return list;
	}
	
	
}
