package hackerrank.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.tuple.Pair;

public class Graph {

	Set<Vertex> vertexes = new LinkedHashSet<>();
	Set<Edge> edges = new LinkedHashSet<>(); // preserve the order so our source is always first 
	
	public static class Vertex {
		
		Set<Edge> outgoing = new HashSet<>();
		
		String name;
		int level;
		double price;
		
		@Override
		public String toString() {
			return name + " | " + level + " | " + price;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj == null || !(obj instanceof Vertex)) return false;
			Vertex o = (Vertex)obj;
			return new EqualsBuilder().append(name, o.name).append(level, o.level).append(price, o.price).isEquals();
		}
		
		@Override
		public int hashCode() {
			return new HashCodeBuilder().append(name).append(level).append(price).toHashCode();
		}
	}
	
	public static class Edge {
		Vertex source;
		Vertex destination;
		
		double weight;
		
		public String toString() {
			StringBuilder b = new StringBuilder();
			return b.append("<").append(source.name).append(">").append(" to <").append(destination.name).append("> Cost " ).append(weight).toString();
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj == null || !(obj instanceof Edge)) return false;
			Edge o = (Edge)obj;
			return new EqualsBuilder().append(source, o.source).append(destination,o.destination).append(weight, o.weight).isEquals();
		}
		
		@Override
		public int hashCode() {
			return new HashCodeBuilder().append(source).append(destination).append(weight).toHashCode();
		}
		
	}
	
	public static void main(String[] args) {
		//		A     17$        1 Unit
		//		B     18$        3 Units
		//		C     23$        5 Units
		Graph g = new Graph();

		// Nodes
		List<Vertex> vertexes = new ArrayList<>();
		vertexes.add(v("source", 0, 0.0));
		vertexes.addAll(IntStream.range(1, 2).mapToObj(i -> v("A_"+ i, i, 17.0)).collect(Collectors.toList()));
		vertexes.addAll(IntStream.range(1, 4).mapToObj(i -> v("B_"+ i, i, 18.0)).collect(Collectors.toList()));
		vertexes.addAll(IntStream.range(1, 6).mapToObj(i -> v("C_"+ i, i, 23.0)).collect(Collectors.toList()));
		
		// Sort the nodes by level (e.g. level 1 means buying 1 item from the seller) 
		Collections.sort(vertexes, new Comparator<Vertex>() {

			@Override
			public int compare(Vertex o1, Vertex o2) {
				return Integer.compare(o1.level, o2.level);
			}
		});
		
		// connect the vertexes: 
		for(int i = 0; i< vertexes.size(); i++) {
			Vertex s = vertexes.get(i);
			for(int j = i+1; j < vertexes.size(); j++) {
				Vertex d = vertexes.get(j);

				if(d.level == s.level) continue; // same level can't connect as we can not buy 1 and end up with the same count 
				
				if(d.level -1 != s.level) break; // level difference > 1 means we would buy 1 and end up with 2 items 

				Edge e = e(s, d, d.price); // Create the edge from n to n+1 so that we can buy this 
				
				g.edges.add(e);
			}
		}
		
		g.edges.forEach(System.out::println);
		g.vertexes.addAll(vertexes);
		
//		shortestPath(g, 3);
	}
	
	public static void shortestPath(Graph g, int qunatity) {
		Vertex source = g.vertexes.iterator().next();

		Map<Vertex, Pair<Boolean, Double>> visitDistance = new HashMap<>();

		// mark source: 
		visitDistance.put(source, Pair.of(true, 0d));
		
		List<Vertex> path = new LinkedList<>();
		
		while(visitDistance.size() < g.vertexes.size()) {
			Set<Edge> outgoing = source.outgoing;

			path.add(source);
			
			double currentDistance = visitDistance.get(source).getRight();
			
			double nextDistance = Double.MAX_VALUE;
			
			for(Edge e : outgoing) {
				Vertex d = e.destination;
				
				if(visitDistance.containsKey(d)) {
					//already seen
					continue;
				}
				
				visitDistance.put(d, Pair.of(true, currentDistance + e.weight));
				
				if(currentDistance + e.weight < nextDistance) {
					nextDistance = currentDistance + e.weight;
					source = d;
				}
				
			}
		}
		
		Iterator<Vertex> iter = path.iterator();
		iter.next(); // skip source

		int index = 0; // count how many items we want to buy
		while (iter.hasNext() && index < qunatity) {
			index++;
			Vertex v = iter.next();
			System.out.println("Buy from " + v.name + " for " + v.price);
		}
		
	}
	
	public static Vertex v(final String name, int level, double price) {
		Vertex v = new Vertex();
		v.name = name;
		v.level = level;
		v.price = price;
		return v;
	}
	
	public static Edge e(final Vertex source, final Vertex dest, final double weight) {
		Edge e = new Edge();
		e.source = source;
		e.destination = dest;
		e.weight = weight;
		
		source.outgoing.add(e);
		
		return e;
	}
}
