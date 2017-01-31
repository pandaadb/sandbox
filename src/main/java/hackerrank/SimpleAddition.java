package hackerrank;

public class SimpleAddition {

	public static void main(String[] args) {

	}

	public static class Add {
		
		public void add(int... numbers) {
			StringBuffer b = new StringBuffer();
			String prefix = "";
			int sum = 0;
			for(int n : numbers) {
				b.append(prefix).append(n);
				sum += n;
				prefix = "+";
			}
			
			b.append("=").append(sum);
			System.out.println(b.toString());
		}
		
	}
}
