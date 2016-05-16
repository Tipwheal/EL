package com.tipwheal.eltest;

/**
 * Test class ActionFilter
 * 
 * @author Yzh
 *
 */
public class ActionFilterTest {

	public static void main(String[] args) {
		new ActionFilterTest().test();
	}

	public void test() {
		int[] loc = { 3, 4 };
		int[] newLoc = getNextLocation("1 4 4 4", loc);
		System.out.println(newLoc[0] + " " + newLoc[1]);
	}

	/**
	 * pass
	 * @param actionLine
	 * @param curLoc
	 * @return
	 */
	public int[] getNextLocation(String actionLine, int[] curLoc) {
		int[] location = curLoc;
		String[] actions = actionLine.split(" ");
		for (String s : actions) {
			int action = Integer.parseInt(s);
			switch (action) {
			case 1:
				if (location[1] < 14) {
					location[1]++;
				}
				break;
			case 2:
				if (location[0] < 14) {
					location[0]++;
				}
				break;
			case 3:
				if (location[1] > 0) {
					location[1]--;
				}
				break;
			case 4:
				if (location[0] > 0) {
					location[0]--;
				}
				break;
			default:
				break;
			}
		}
		return location;
	}
}
