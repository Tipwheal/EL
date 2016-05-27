package com.tipwheal.el;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Test {
	public static void main(String[] args) {
		new Test().start();
	}

	public void start() {
		int ac = 0;
		int bc = 0;
		int at = 0;
		int bt = 0;
		int a = 0;
		int b = 0;
		int aw = 0;
		int bw = 0;
		File file = new File(
				"D:\\My Documents\\鲱鱼\\Tipwheal\\Tipfiles\\作业\\cache\\cygwin64\\cygwin64\\home\\Administrator\\el\\log1.txt");
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			while (line != null) {
				if (line.startsWith("#")) {
					switch (line.substring(8, 9)) {
					case "A":
						a = Integer.parseInt(line.substring(10));
						ac++;
						at += a;
						break;
					case "B":
						b = Integer.parseInt(line.substring(10));
						bc++;
						bt += b;
						if (a > b) {
							aw++;
						} else if (b > a) {
							bw++;
						}
					}
				}
				line = br.readLine();
			}
			br.close();
			System.out.println("A total:" + at);
			System.out.println("B total:" + bt);
			System.out.println("A count:" + ac);
			System.out.println("B count:" + bc);
			System.out.println("A win:" + aw);
			System.out.println("B win:" + bw);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
