import java.util.Random;


public class insättningssortering {
	public static void main(String[] args) {
		Random rand = new Random();
		int[] list = new int[7];
		for (int i = 0; i < 7; i++) {
			list[i] = rand.nextInt(2000);
		}
		int index = 0;
		for (int i = 0; i < list.length; i++) {
			int min = Integer.MAX_VALUE;
			for (int k = i; k < list.length; k++) {
				if (list[k] < min) {
					min = list[k];
					index = k;
				}
			}
			list[index] = list[i];
			list[i] = min;
		}
		for (int s = 0; s < list.length; s++) {
			System.out.print(list[s] + " ");
		}
	}
}
