package com.tipwheal.el;

/**
 * Created by 李雨倩on 2016/5/13.
 */
public class ByLyq {
	/***
	 * test方法
	 */
	int[] first = { 0, 5 };
	int[] second = { 0, 14 };
	int[] third = { 9, 14 };
	int[][] board = board();

	// 以上是三个武士的位置
	ByLyq() {

		int[][] boardSeen = new int[15][15];
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				boardSeen[i][j] = 9;
				// 9代表情况不明
			}
		}
	}

	/**
	 * 假装自己是游戏解析器，脑部局势 注意：此处赋值出全地图情况
	 * 
	 * @return （脑补出的）战场实际形势
	 */
	int[][] board() {
		int[][] board = new int[15][15];
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				board[i][j] = 8;
				// 8代表没有被任何人占领
			}
		}
		return board;
	}

	/**
	 * 根据武士的位置，得到他的视野
	 * 
	 * @param i
	 *            武士的代号
	 * @param location
	 *            武士的位置
	 * @param board
	 *            战场实际情况
	 * @param boardSeen
	 *            我们能看见的战场情况
	 * @return 我们能看见的战场情况
	 */
	public int[][] manhattan(int i, int[] location, int[][] board, int[][] boardSeen) {
		for (int n = 0; n <= 5; n++) {
			for (int m = location[1] - 5 + n; m <= location[1] + 5 - n; m++) {
				boardSeen[location[0] + n][m] = board[location[0] + n][m];

			}
			for (n = 0; n <= 5; n++) {
				for (int m = location[1] - 5 + n; m <= location[1] + 5 - n; m++) {
					boardSeen[location[0] - n][m] = board[location[0] - n][m];
				}
			}
		}
		return boardSeen;
	}

	/**
	 * 计算出我方武士每个人占领了多少格子 计算出能看到的敌方武士每个人占领了多少格子
	 * 
	 * @param boardSeen
	 *            能看见的战场
	 * @return 一个int[]型数组，名称是score[]。
	 *         score[0]存储的是0号武士占领的格子数，score[1]存储的是1号武士占领的格子数；（到score[5]皆同）
	 *         score[6]存储的是没有被任何一方占领的格子数 score[7]存储的是未知的格子数
	 */
	public int[] occupyCount(int[][] boardSeen) {
		int[] score = { 0, 0, 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				switch (boardSeen[i][j]) {
				case 0:
					score[0]++;
					break;
				case 1:
					score[1]++;
					break;
				case 2:
					score[2]++;
					break;
				case 3:
					score[3]++;
					break;
				case 4:
					score[4]++;
					break;
				case 5:
					score[5]++;
					break;
				case 8:
					score[6]++;
					break;
				case 9:
					score[9]++;
					break;
				}

			}
		}
		return score;
	}

}