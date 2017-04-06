package hackerrank.algo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.geometry.spherical.twod.Edge;

public class ShortestPath {

	
	
	
	public static void main(String[] args) {
		
//		Name            Price      Units in storage
//		Supplier #1     17$        1 Unit
//		Supplier #2     18$        3 Units
//		Supplier #3     23$        5 Units
		
		// create the graph 
		
		Node source = node("Source", 0);
		
		Node nOne_1 = node("#1", 1);
		Node nOne_2 = node("#2", 1);
		Node nOne_3 = node("#3", 1);

		source.edges.add(link(source, nOne_1, 17.0));
		source.edges.add(link(source, nOne_2, 18.0));
		source.edges.add(link(source, nOne_3, 23.0));
		
		Node nTwo_1 = node("#1", 2);
		Node nTwo_2 = node("#2", 2);
		Node nTwo_3 = node("#3", 2);
		
		nOne_1.edges.add(link(nOne_1, nTwo_2, 18.0));
		nOne_1.edges.add(link(nOne_1, nTwo_3, 23.0));
		
		nOne_2.edges.add(link(nOne_2, nTwo_2, 18.0));
		nOne_2.edges.add(link(nOne_2, nTwo_1, 17.0));
		nOne_2.edges.add(link(nOne_2, nTwo_3, 23.0));
		
	}
	
	public static Link link(Node s, Node t, double weight) {
		Link l = new Link();
		l.source = s;
		l.destination = t;
		l.weight = weight;
		return l;
	}
	
	public static Node node(String supplier, int quantity) {
		Node n = new Node();
		n.supplier = supplier;
		n.quantity = quantity;
		return n;
	}
	
	public static class Node {
		String supplier;
		int quantity;
		
		List<Link> edges = new ArrayList<>();
	}
	
	public static class Link {
		
		Node source;
		Node destination;
		
		double weight;
	}
	
}
