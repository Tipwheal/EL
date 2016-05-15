package com.tipwheal.eltest;

public class Test {
	int[] loca = new int[2];
	public static void main(String[] args) {
		Test a = new Test();
		a.loca[0] = 5;
		a.loca[1] = 6;
		a.start();
	}
	
	public void start() {
		System.out.println(loca[0]+" "+loca[1]);
		change(loca);
		System.out.println(loca[0]+" "+loca[1]);
	}
	
	public int[] change(int[] loca) {
		int[] newLoca = loca;
		newLoca[0] ++;
		newLoca[1] --;
		return newLoca;
	}
}
