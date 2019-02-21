import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1922_��Ʈ��ũ���� {
	
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
	
	public static void makeSet(int x) {//parent ����
		p[x] = x;
	}
	
	public static int findSet(int x) {//parent ��ȯ
		if(p[x] == x)
			return x;
		else {
			p[x] = findSet(p[x]);//path compression
			return p[x];
		}
	}
	
	public static void union(int x, int y) {//disjoint sets ����
		int px = findSet(x);
		int py = findSet(y);
		if(px != py) {
			p[px] = py;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());//��ǻ���� ��
		int M = Integer.parseInt(br.readLine());//������ �� �ִ� ���� ��
		
		p = new int[N + 1];//disjoint sets
		Edge[] edges = new Edge[M];
		
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			edges[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(edges);//��� ���� ������ ����
		
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
