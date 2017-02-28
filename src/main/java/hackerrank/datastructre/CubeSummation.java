package hackerrank.datastructre;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CubeSummation {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);

		int tests = s.nextInt();

		for (int i = 0; i < tests; i++) {
			int n = s.nextInt(); // not needed at the moment

			Map<Integer, Map<Integer, Map<Integer, Integer>>> x_map = new HashMap<>();

			int ops = s.nextInt();
			for (int o = 0; o < ops; o++) {
				System.out.println(parse(s).result(x_map));
			}
		}
	}

	static X parse(final Scanner s) {
		String op = s.next();
		if (op.equals("UPDATE")) {
			return parseUpdate(s);
		} else {
			return parseQuery(s);
		}
	}

	static X parseQuery(final Scanner s) {
		Query q = new Query();
		q.x1 = s.nextInt();
		q.y1 = s.nextInt();
		q.z1 = s.nextInt();
		q.x2 = s.nextInt();
		q.y2 = s.nextInt();
		q.z2 = s.nextInt();
		return q;
	}

	static X parseUpdate(final Scanner s) {
		Update u = new Update();
		u.x = s.nextInt();
		u.y = s.nextInt();
		u.z = s.nextInt();
		u.w = s.nextLong();
		return u;
	}

	public static class Query implements X {
		int x1, x2, y1, y2, z1, z2;

		public int result(Map<Integer, Map<Integer, Map<Integer, Integer>>> matrix) {
			return 0;
		}
	}

	public static class Update implements X {
		int x, y, z;
		long w;

		public int result(Map<Integer, Map<Integer, Map<Integer, Integer>>> matrix) {
			return 0;
		}
	}

	public interface X {
		public int result(Map<Integer, Map<Integer, Map<Integer, Integer>>> matrix);
	}

}
