package hackerrank.algo;

public class Levenstien {

	public static void main(String[] args) {

		System.out.println(levenStein("batoon", "batuun"));
	}

	public static int levenStein(final String s1, final String s2) {
		int[][] l = new int[s1.length()][s2.length()];
		
//		printM(l);
		for (int i = 0; i < s1.length(); i++) {
			for (int j = 0; j < s2.length(); j++) {

				int cost = s1.charAt(i) == s2.charAt(j) ? 0 : 1;
				
				if( i == 0 && j == 0) {
					// first letter, no previous values
					l[i][j] = cost;
				} else {
					if( i == 0) {
						// first row. can only look at left value
						// top value is j, diagonal value is j - 1 
						l[i][j] = min(l[i][j -1], j, j > 0 ? j - 1 : j) + cost;
					} else if( j == 0) { 
						// reverse from above we are on the first column so no left value 
						// top value is above (i - 1), left value is 
						l[i][j] = min(l[i -1][j], i, i > 0 ? i -1 : i) + cost;
					} else {
						l[i][j] = min(l[i-1][j], l[i][j-1], l[i-1][j-1]) + cost;
					}
				}
			}
//			printM(l);
		}
		
		printM(l);
		return l[s1.length() - 1][s2.length() - 1];
	}
	
	public static int min(int i1, int i2, int i3) {
		return Math.min(Math.min(i1, i2), i3);
	}
	
	public static void printM(int[][] l) {
		for (int i = 0; i < l.length; i++) {
			for (int j = 0; j < l[i].length; j++) {
				System.out.print( l[i][j]);
			}
			System.out.println("");
		}
		System.out.println();
	}
}
