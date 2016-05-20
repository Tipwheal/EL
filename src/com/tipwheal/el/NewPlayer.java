package com.tipwheal.el;

public class NewPlayer extends Player {

	@Override
	/**
	 * 这是一个开始的方法。
	 */
	public GameInfo play(GameInfo info) {
		print(info);
		ScoreCounter counter = new ScoreCounter(info);
		String[] actions = counter.getAction().split(" ");
		for (String action : actions) {
			info.doAction(Integer.parseInt(action));
		}
		return info;
	}

	/**
	 * 输出我想要的信息。
	 * 
	 * @param info
	 * @return
	 */
	public boolean print(GameInfo info) {
		int[][] field = info.getField();
		System.err.println("My id: " + info.getWeapon());
		System.err.println("My side: " + info.getSide());
		System.err.println("My homeX: " + info.getSamuraiInfo()[info.getWeapon()].getHomeX());
		System.err.println("My homeY: " + info.getSamuraiInfo()[info.getWeapon()].getHomeY());
		System.err.println("Is hid? " + info.getSamuraiInfo()[info.getWeapon()].getHidden());
		System.err.println("Enemy 3: " + info.getSamuraiInfo()[3].getCurX() + " " + info.getSamuraiInfo()[3].getCurY());
		System.err.println("Enemy 4: " + info.getSamuraiInfo()[4].getCurX() + " " + info.getSamuraiInfo()[4].getCurY());
		System.err.println("Enemy 5: " + info.getSamuraiInfo()[5].getCurX() + " " + info.getSamuraiInfo()[5].getCurY());
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				int state = field[i][j];
				if (state == 9) {
					System.err.print(" ");
				} else {
					System.err.print(field[i][j]);
				}
				if (j != 14) {
					System.err.print(" ");
				} else {
					System.err.println();
				}
			}

		}
		return true;
	}

}
