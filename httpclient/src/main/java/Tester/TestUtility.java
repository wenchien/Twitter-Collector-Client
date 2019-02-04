package Tester;

import utilies.IDGeneration;

public class TestUtility {
	public static void main(String[] args) {
		for (int i = 0; i < 10; i ++) 
			System.out.println(IDGeneration.generateID());
	}
}
