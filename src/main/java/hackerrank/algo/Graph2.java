package hackerrank.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph2 {

	public static class Vendor {
		int itemCount = 0;
		double price = 0.0;
		
		public Vendor(int itemCount, double price ) {
			this.price = price;
			this.itemCount = itemCount;
		}
	}
	
	public static class Combo {
		
		public Combo(int aCount, int bCount, int cCount, double total) {
			this.aCount = aCount;
			this.bCount = bCount;
			this.cCount = cCount;
			this.total = total;
		}

		public int aCount;
		public int bCount;
		public int cCount;
		
		public double total;
		
	}
	
	public static int counter = 0;
	
	public static void main(String[] args) {
		
		Vendor a = new Vendor(1, 17.0);
		Vendor b = new Vendor(3, 18.0);
		Vendor c = new Vendor(5, 23.0);
		
		Map<Integer, List<Combo>> comboMap = new HashMap<>();
		
		for(int i_a = 0; i_a <= a.itemCount; i_a++) {
			
			for(int i_b = 0; i_b <= b.itemCount; i_b++) {
				
				for(int i_c = 0; i_c <= c.itemCount; i_c++) {
					
					StringBuffer buf = new StringBuffer();
					buf.append("A: ").append(i_a).append(" B: ").append(i_b).append(" C: ").append(i_c);
					
					int totalCount = i_a + i_b + i_c;
					
					List<Combo> combos = comboMap.computeIfAbsent(totalCount, k -> new ArrayList<>());
					
					combos.add(new Combo(i_a, i_b, i_c, i_a * a.price + i_b * b.price + i_c * c.price));
					
				}
			}
		}
		
		comboMap.entrySet().stream().forEach(e -> {
			
			Integer totalCount = e.getKey();
			List<Combo> combos = e.getValue();
			
			combos.forEach( combo -> {
				counter++;
				StringBuffer buffer = new StringBuffer();
				buffer.append("Buying ").append(totalCount).append(" items. ").append("A: ").append(combo.aCount)
					  .append(" B: ").append(combo.bCount).append(" C: ").append(combo.cCount).append(" Costs: ").append(combo.total);
				
				System.out.println(buffer.toString());
			});
			
		});
		
		
		System.out.println("Combinations: " + counter);
	}
}
