package com.tipwheal.el;

import java.util.ArrayList;

public class ActionLibrary {
	private GameInfo info;
	private ArrayList<String> actions = new ArrayList<>();

	/**
	 * 构造函数，得到所有合法行为。
	 * @param info
	 */
	public ActionLibrary(GameInfo info) {
		this.info = info;
		int ID = info.getWeapon();
		int hidden = info.getSamuraiInfo()[ID].getHidden();
		String action;

		/**
		 * 加上隐身现身。
		 */
		if (hidden == 0) {
			actions.add("9");
		} else {
			actions.add("10");
		}

		/**
		 * 加上单走行为。
		 */
		for (int a = 5; a < 9; a++) {
			action = a + "";
			this.add(action);
			for (int b = 5; b < 9; b++) {
				action = a + " " + b;
				this.add(action);
				for (int c = 5; c < 9; c++) {
					this.add(action);
				}
			}
		}

		/**
		 * 走加打。
		 */
		for (int a = 1; a < 5; a++) {
			this.add(a + "");
			for (int b = 5; b < 9; b++) {
				this.add(a + " " + b);
				this.add(b + " " + a);
			}
		}

	}

	/**
	 * 加上附属行为。
	 * 
	 * @param action
	 */
	private void add(String action) {
		int ID = info.getWeapon();
		int hidden = info.getSamuraiInfo()[ID].getHidden();
		int x = info.getSamuraiInfo()[ID].getCurX();
		int y = info.getSamuraiInfo()[ID].getCurY();
		ActionCheck check = new ActionCheck(info);
		if (check.validAction(action, x, y, hidden, ID)) {
			actions.add(action);
		}
		if (hidden == 0) {
			action += " 9";
			if (check.validAction(action, x, y, hidden, ID)) {
				actions.add(action);
			}
		} else {
			action = "10 " + action;
			if (check.validAction(action, x, y, hidden, ID)) {
				actions.add(action);
			}
		}
		if (hidden == 1 && action.length() <= 9) {
			action = "10 " + action + " 9";
			if (check.validAction(action, x, y, hidden, ID)) {
				actions.add(action);
			}
		}
	}

	/**
	 * 得到行为总数。
	 * @return
	 */
	public int getNum() {
		return actions.size();
	}

	/**
	 * 得到某个行为。
	 * @param i
	 * @return
	 */
	public String getActions(int i) {
		return actions.get(i);
	}
}
