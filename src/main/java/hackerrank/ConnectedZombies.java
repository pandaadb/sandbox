package hackerrank;

public class ConnectedZombies {

	static class Element {
		int x = -1;
		int y = -1;
		boolean zombie = false;
		Element top, right, left, buttom;
		
		boolean visited = false;
	}
	
	static Element getElement(final char c, int x, int y) {
		Element e = new Element();
		e.zombie = c == '1';
		e.x = x;
		e.y = y;
		return e;
	}
	
	static void checkConnection(Element[][] zombieArray, int x, int y) {
		Element z = zombieArray[x][y];
		if(!z.zombie) {
			return;
		}
		
		if(y > 0) {
			// check to the left
			Element leftZombie = zombieArray[x][y -1];
			if(leftZombie.zombie) {
				z.left = leftZombie;
				leftZombie.right = z;
			}
		}
		// check to the top
		if(x > 0) {
			Element topZombie = zombieArray[x-1][y];
			if(topZombie.zombie) {
				z.top = topZombie;
				topZombie.buttom = z;
			}
		}
		
	}
	
	static int zombieCluster(String[] zombies) {
		Element[][] zombieArray = new Element[zombies.length][zombies.length];
		
		// do left to right while writing
		int x = 0;
		int y = 0;
		for(String s : zombies) {
			for(char c : s.toCharArray()) {
				zombieArray[x][y] = getElement(c,x,y);
				checkConnection(zombieArray, x, y);
				y++;
			}
			y = 0;
			x++;
		}
		
		int groups = 0;
		for(int i=0; i<zombieArray.length; i++) {
			for(int j =0; j<zombieArray.length; j++) {
				Element e = zombieArray[i][j];
				if(e.zombie && !e.visited) {
					groups++;
				}
				visitElements(e);
			}
		}
		
		return groups;
    }
	
	static void visitElements(Element e) {
		if(e.visited) return;
		
		e.visited = true;
		if(e.left != null) {
			visitElements(e.left);
		}
		if(e.right != null) {
			visitElements(e.right);
		}
		if(e.buttom != null) {
			visitElements(e.buttom);
		}
		if(e.top != null) {
			visitElements(e.top);
		}
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
