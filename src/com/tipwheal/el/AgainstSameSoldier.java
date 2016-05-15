package com.tipwheal.el;

import java.util.ArrayList;

public class AgainstSameSoldier {
	/**
	 * ����ͬ����ʿԭ��ռ�� ����һ������move����Ϊlist
	 * 
	 * @return
	 */
	public static ArrayList<String> localOccupy() {
		Behavior beh = new Behavior(0);
		String[] num = new String[beh.behaviors.size()];
		int n1, n2, n3, n4;
		for (int i = 0; i < beh.behaviors.size(); i++) {
			num[i] = beh.getBehavior(i);
			n1 = num[i].indexOf("5");
			n2 = num[i].indexOf("6");
			n3 = num[i].indexOf("7");
			n4 = num[i].indexOf("8");
			if (n1 >= 0 || n2 >= 0 || n3 >= 0 || n4 >= 0) {
				num[i] = "flag";
			}
		}
		boolean enemy = false;
		ArrayList<String> behaviors = new ArrayList<String>();
		if (!enemy) {
			for (int i = 0; i < num.length; i++) {
				if (!num[i].equals("flag")) {
					behaviors.add(num[i]);
				}
			}
		}
		return behaviors;
	}

	/**
	 * 
	 * @param x�ҷ���ʿ������
	 * @param y�ҷ���ʿ������
	 * @param curX�з���ʿ������
	 * @param curY�з���ʿ������
	 *            ��׷���з�ְͬҵ��ʿ���ɸ÷�������һ����Ϊ��list�������ּ�Ȩ
	 * @return
	 */
	public static ArrayList<String> chastSameSoldier(int x, int y, int curX, int curY) {
		Behavior beh = new Behavior(0);
		ArrayList<String> behaviors = new ArrayList<String>();
		String[] num = new String[beh.behaviors.size()];
		int n1, n2, n3, n4;
		for (int i = 0; i < beh.behaviors.size(); i++) {
			num[i] = beh.getBehavior(i);
			n1 = num[i].indexOf("5");
			n2 = num[i].indexOf("6");
			n3 = num[i].indexOf("7");
			n4 = num[i].indexOf("8");
			if (x > curX && y > curY) {
				if ((n1 >= 0) || (n3 >= 0)) {
					behaviors.add(num[i]);
				}
			}
			if (x < curX && y > curY) {
				if ((n2 >= 0) || (n3 >= 0)) {
					behaviors.add(num[i]);
				}
			}
			if (x > curX && y < curY) {
				if ((n1 >= 0) || (n4 >= 0)) {
					behaviors.add(num[i]);
				}
			}
			if (x < curX && y < curY) {
				if ((n2 >= 0) || (n4 >= 0)) {
					behaviors.add(num[i]);
				}
			}
		}
		return behaviors;
	}

	/**
	 * 
	 * @param x�ҷ���ʿ������
	 * @param y�ҷ���ʿ������
	 * @param curX�з���ʿ������
	 * @param curY�з���ʿ������
	 *            ����ܵз�ְͬҵ��ʿ���ɸ÷�������һ����Ϊ��list�����ӷּ�Ȩ
	 * @return
	 */
	public static ArrayList<String> evadeSameSoldier(int x, int y, int curX, int curY) {
		Behavior beh = new Behavior(0);
		ArrayList<String> behaviors = new ArrayList<String>();
		String[] num = new String[beh.behaviors.size()];
		int n1, n2, n3, n4;
		for (int i = 0; i < beh.behaviors.size(); i++) {
			num[i] = beh.getBehavior(i);
			n1 = num[i].indexOf("5");
			n2 = num[i].indexOf("6");
			n3 = num[i].indexOf("7");
			n4 = num[i].indexOf("8");
			if (x > curX && y > curY) {
				if ((n2 >= 0) || (n4 >= 0)) {
					behaviors.add(num[i]);
				}
			}
			if (x < curX && y > curY) {
				if ((n1 >= 0) || (n4 >= 0)) {
					behaviors.add(num[i]);
				}
			}
			if (x > curX && y < curY) {
				if ((n2 >= 0) || (n3 >= 0)) {
					behaviors.add(num[i]);
				}
			}
			if (x < curX && y < curY) {
				if ((n1 >= 0) || (n3 >= 0)) {
					behaviors.add(num[i]);
				}
			}
		}
		return behaviors;
	}
}
