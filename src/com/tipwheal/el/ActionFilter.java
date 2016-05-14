package com.tipwheal.el;

import java.util.ArrayList;

public class ActionFilter {
	GameInfo gi = new GameInfo();
	UsefulInfo ui = new UsefulInfo();
	Behavior behavior = new Behavior(ui.getSamuraiInfo(ui.getWeapon()).getHidden());

	public ArrayList<int[]> canAttackInOneTurn(int weapon) {
		
		return null;
	}
	
	private int[] getNextLocation(String action,int curLoc,int weapon) {
		int[] location = new int[2];
		return null;
	}
}
