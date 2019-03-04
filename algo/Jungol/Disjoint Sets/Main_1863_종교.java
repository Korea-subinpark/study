import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
/*
 * Disjoint Sets을 이용하여 집합을 나누고 findSet의 결과를 모두 Hashset에 넣어 총 몇 가지인지 출력한다
 * */
public class Main_1863_종교 {
	static int[] p;
	static int[] rank;
	
	public static int findSet(int x) {
		if(x == p[x])
			return p[x];
		else {//path compression
			p[x] = findSet(p[x]);
			return p[x];
		}
	}
	
	public static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		if(px != py) {
			link(px, py);
		}
	}
	
	public static void link(int x, int y) {
		if(rank[x] > rank[y]) {
			p[y] = x;
		} else {
			p[x] = y;
		}
		if(rank[x] == rank[y]) {
			rank[y]++;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		p = new int[n + 1];
		rank = new int[n + 1];
		
		//make set
		for(int i = 1; i <= n; i++)
			p[i] = i;
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(findSet(a) != findSet(b)) {
				union(a, b);
			}
		}
		
		HashSet<Integer> s = new HashSet<>();
		for(int i = 1; i <= n; i++) {
			s.add(findSet(p[i]));
		}
		System.out.println(s.size());
	}

}
