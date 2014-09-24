public class sdfsd {
	public static void main(String[] args) {
		int[] current = new int[3];
		current[0] = 1;
		current[1] = 2;
		current[2] = 7;
		int[] p = new int[3];
		p[0] = 0;
		p[1] = 1;
		p[2] = 6;
		String s = "><>";
		for (int i = 0; i < s.length(); i++) {
			if ((s.charAt(i) == '>' && current[i] <= p[i])
					|| (s.charAt(i) == '<' && current[i] >= p[i])) {
				System.out.println("Stämmer ej");
				System.exit(1);
			}
		}
		System.out.println("Stämmer");
	}
}
