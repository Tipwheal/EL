package com.tipwheal.el;

import java.util.ArrayList;

public class OccupyArea {
	int[] myLocation = new int[2];
	int ID;

	public OccupyArea(int[] l, int id) {
		myLocation = l;
		ID = id % 3;
	}

	public ArrayList<int[]> getBiggestOccupyArea(int size) {
		ArrayList<int[]> list = new ArrayList<int[]>();
		ArrayList<int[]> list1 = new ArrayList<int[]>();
		list.addAll(this.Manhattan(myLocation, size));
		for (int i = 0; i < list.size(); i++) {
			list1.addAll(this.getOccupyArea(list.get(i)));
		}
		list.addAll(list1);
		return this.Remove(list);
	}

	public ArrayList<int[]> getCanBeHertArea(int[] enemyLoca) {
		ArrayList<int[]> list = new ArrayList<int[]>();
		ArrayList<int[]> list1 = new ArrayList<int[]>();
		list.addAll(this.getOccupyArea(enemyLoca));
		for (int i = 0; i < list.size(); i++) {
			list1.addAll(this.Manhattan(list.get(i), 1));
		}
		list.addAll(list1);
		list = this.Remove(list);
		for (int i = 0; i < list.size(); i++) {
			if (Math.abs(list.get(i)[0] - enemyLoca[0]) + Math.abs(list.get(i)[1] - enemyLoca[1]) > 4) {
				list.remove(i);
			}
		}
		return list;
	}

	private ArrayList<int[]> getOccupyArea(int[] currentLoca) {
		ArrayList<int[]> list = new ArrayList<int[]>();
		switch (ID) {
		case 0:
			list = this.getSpearOccupyArea(currentLoca);
			break;
		case 1:
			list = this.getSwordOccupyArea(currentLoca);
			break;
		case 2:
			list = this.getAXOccupyArea(currentLoca);
		}
		return list;
	}

	public ArrayList<int[]> getSpearOccupyArea(int[] currentLoca) {
		ArrayList<int[]> list = new ArrayList<int[]>();
		Spear spear = new Spear();
		for (int i = 1; i < 5; i++) {
			list.addAll(spear.getArea(Integer.toString(i), currentLoca));
		}
		return this.Remove(list);
	}

	public ArrayList<int[]> getSwordOccupyArea(int[] currentLoca) {
		ArrayList<int[]> list = new ArrayList<int[]>();
		Sword sword = new Sword();
		for (int i = 1; i < 5; i++) {
			list.addAll(sword.getArea(Integer.toString(i), currentLoca));
		}
		return this.Remove(list);
	}

	public ArrayList<int[]> getAXOccupyArea(int[] currentLoca) {
		ArrayList<int[]> list = new ArrayList<int[]>();
		Ax ax = new Ax();
		for (int i = 1; i < 5; i++) {
			list.addAll(ax.getArea(Integer.toString(i), currentLoca));
		}
		return this.Remove(list);
	}

	public ArrayList<int[]> Remove(ArrayList<int[]> list) {
		ArrayList<int[]> a = new ArrayList<int[]>();
		for (int i = 1; i < list.size(); i++) {
			boolean tag = true;
			for (int j = 0; j < i; j++) {
				if ((list.get(i)[0] == list.get(j)[0] && list.get(i)[1] == list.get(j)[1]) || list.get(i)[0] > 14
						|| list.get(i)[1] > 14 || list.get(i)[0] < 0 || list.get(i)[1] < 0) {
					tag = false;
					break;
				}
			}
			if (tag) {
				a.add(list.get(i));
			}
		}
		a.add(list.get(0));
		return a;
	}

	public ArrayList<int[]> Manhattan(int[] location, int size) {
		ArrayList<int[]> list = new ArrayList<int[]>();
		for (int i = -size; i <= size; i++) {
			int anotherAxis = Math.abs(size - Math.abs(i));
			for (int j = -anotherAxis; j <= anotherAxis; j++) {
				int[] loca = { location[0] + i, location[1] + j };
				list.add(loca);
			}
		}
		return this.Remove(list);
	}
}
