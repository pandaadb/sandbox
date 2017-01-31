package hackerrank;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConnectedZombies2 {

	static class Zombie {
		boolean seen = false;
		Set<Zombie> connectedZombies = new HashSet<>();
		
		int x = 0;
		int y = 0;
	}
	
	static int zombieCluster(String[] zombies) {
		Zombie[] zombieCon = new Zombie[zombies.length];
		
		for(int i = 0; i < zombies.length; i++) {
			char[] chars = zombies[i].toCharArray();
			// init zombie
			Zombie z = putIfAbsent(zombieCon, i);
			// check the connections and add them
			for(int j =0;j< chars.length; j++) {
				if(i == j) {
					continue; // know itself
				}
				if(chars[j] == '1') {
					// knowing each other
					Zombie other = putIfAbsent(zombieCon, j);
					// add them together
					z.connectedZombies.add(other);
					z.connectedZombies.addAll(other.connectedZombies);
				}
			}
		}
		
		int groups = 0;
		
		for(Zombie z : zombieCon) {
			
			if(z.seen) {
				continue;
			}
			
			groups ++;
			markAsSeen(z);
		}
		return groups;
    }
	
	static void markAsSeen(Zombie z) {
		if(z.seen) return;
		z.seen = true;
		z.connectedZombies.forEach(other -> markAsSeen(other));
	}
	
	static Zombie putIfAbsent(Zombie[] zombies, int index) {
		if(zombies[index] == null) {
			zombies[index] = new Zombie();
		}
		return zombies[index];
	}
	public static void main(String[] args) {
		String[] test = new String[] {
				"0100",
				"1110",
				"0110",
				"0001"
		};
		System.out.println(zombieCluster(test));
				
	}
	
	
}
