package com.tipwheal.el;

import java.util.ArrayList;

public class Behavior {
	ArrayList<String> behaviors = new ArrayList<String>();

	/**
	 * Constructs a Behavior that contents all possible behaviors.
	 * 
	 * @param isHide
	 *            if the samurai is hidden
	 */
	public Behavior(int isHide) {
		behaviors.add("9");
		behaviors.add("10");
		for (int a = 5; a < 9; a++) {
			for (int b = 5; b < 9; b++) {
				for (int c = 5; c < 9; c++) {
					String s = Integer.toString(a) + " " + Integer.toString(b) + " " + Integer.toString(c);
					if (isHide == 0) {
						behaviors.add(s + " " + "9");
					} else {
						behaviors.add(s);
					}
				}
			}
		}

		for (int a = 5; a < 9; a++) {
			for (int b = 5; b < 9; b++) {
				String s = Integer.toString(a) + " " + Integer.toString(b);
				if (isHide == 0) {
					behaviors.add(s + " " + "9");
				} else {
					behaviors.add(s);
				}
			}
		}

		for (int a = 1; a < 4; a++) {
			if (isHide == 0) {
				behaviors.add(Integer.toString(a) + " " + "9");
			} else {
				behaviors.add("10 " + Integer.toString(a) + " 9");
			}
		}

		for (int a = 1; a < 5; a++) {
			for (int b = 5; b < 9; b++) {
				String s = Integer.toString(a) + " " + Integer.toString(b);
				if (isHide == 0) {
					behaviors.add(s + " 9");
				} else {
					behaviors.add("10 " + s);
				}
			}
		}

		for (int a = 5; a < 9; a++) {
			for (int b = 1; b < 5; b++) {
				String s = Integer.toString(a) + " " + Integer.toString(b);
				if (isHide == 0) {
					behaviors.add(s + " 9");
				} else {
					behaviors.add("10 " + s);
				}
			}
		}
	}

	/**
	 * Get the Behavior you ask for.
	 * 
	 * @param i
	 *            the number of Behavior String in behaviors
	 * @return return a String of Behavior
	 */
	public String getBehavior(int i) {
		return behaviors.get(i);
	}

	public ArrayList<String> getBehaviors() {
		return behaviors;
	}
}
