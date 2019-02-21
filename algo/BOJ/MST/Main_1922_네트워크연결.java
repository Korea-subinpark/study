import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1922_네트워크연결 {
	
	static int[] p;

	static class Edge implements Comparable<Edge>{
		int a, b, cost;
		public Edge(int a, int b, int cost) {
			this.a = a;
			this.b = b;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge arg0) {
			return cost - arg0.cost;
		}
	}
	
	public static void makeSet(int x) {//parent 생성
		p[x] = x;
	}
	
	public static int findSet(int x) {//parent 반환
		if(p[x] == x)
			return x;
		else {
			p[x] = findSet(p[x]);//path compression
			return p[x];
		}
	}
	
	public static void union(int x, int y) {//disjoint sets 통합
		int px = findSet(x);
		int py = findSet(y);
		if(px != py) {
			p[px] = py;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());//컴퓨터의 수
		int M = Integer.parseInt(br.readLine());//연결할 수 있는 선의 수
		
		p = new int[N + 1];//disjoint sets
		Edge[] edges = new Edge[M];
		
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			edges[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(edges);//비용 적은 순으로 정렬
		
		for(int i = 0; i < N; i++)
			makeSet(i);
		
		int cnt = 0;
		int ans = 0;
		for(int i = 0; i < M; i++) {
			if(findSet(edges[i].a) != findSet(edges[i].b)) {
				union(edges[i].a, edges[i].b);
				ans += edges[i].cost;
				cnt++;
			}
			if(cnt == N - 1)
				break;
		}
		
		System.out.println(ans);
		
	}

}
