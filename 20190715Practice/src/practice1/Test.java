package practice1;

import java.io.*;

public class Test {
	public static void main(String[] args) {
		int[] numbers = new int[3];

		for (int i = 0; i < 3;) {
			try {
				System.out.println("Input No." + (i + 1) + "Integer:");
				numbers[i] = Keyboard.getInteger();
				i++;
			} catch (Exception e) {
				 System.out.println(e);
			}
		}
		System.out.println("Finish");
	}

}

class Keyboard {
	static BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));

	public static int getInteger() throws Exception {
		try {
			return (Integer.valueOf(inputStream.readLine().trim()).intValue());
		} catch (Exception e) {
			throw new Exception("输入数字有误！");
		}
	}
}