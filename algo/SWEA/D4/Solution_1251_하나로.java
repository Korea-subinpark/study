import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*생성할 수 있는 모든 간선을 만들고 크루스칼 알고리즘으로 최소신장트리를 구한다*/

public class Solution_1251_하나로 {
	
	public static class Edge implements Comparable<Edge> {
		int s, e;
		double w;
		public Edge(int s, int e, double w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {//가중치를 기준으로 비교
			return Double.compare(w, o.w);
		}
	}
	
	static int[] p;//Disjoint Sets의 representative배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());//간선의 수
			int[][] xy = new int[N][2];
			
			p = new int[N + 1];//Disjoint Sets의 representative배열
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++)
				xy[i][0] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++)
				xy[i][1] = Integer.parseInt(st.nextToken());
			
			double E = Double.parseDouble(br.readLine());
			
			ArrayList<Edge> edges = new ArrayList<>();
			
			for(int i = 0; i < N; i++) {//Vertex의 좌표를 이용하여 생성할 수 있는 간선을 모두 생성
				for(int j = i + 1; j < N; j++) {
					double a = E * Math.pow(xy[i][0] - xy[j][0], 2);
					double b = E * Math.pow(xy[i][1] - xy[j][1], 2);
					
					edges.add(new Edge(i, j, a + b));//간선 추가
				}
			}
			
			Collections.sort(edges);//가중치를 기준으로 정렬
			
			for (int i = 0; i < N; i++)
				makeSet(i);
			
			int cnt = 0;
			double ans = 0;
			for(int i = 0; i < edges.size(); i++) {
				Edge ed = edges.get(i);
				if(findSet(ed.s) != findSet(ed.e)) {//loop를 생성하지 않을 경우
					union(ed.s, ed.e);
					cnt++;
					ans += ed.w;
				}
				if(cnt == N)//최소신장트리 완성
					break;
			}
			
			System.out.println("#" + tc + " " + Math.round(ans));
		}
	}
		
	public static void makeSet(int x) {
		p[x] = x;
	}
	
	public static int findSet(int x) {
		if(p[x] == x)
			return x;
		else {
			p[x] = findSet(p[x]);//path compresstion
			return p[x];
		}
	}
	
	public static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		
		if(px != py)//representative가 다를 때 통합
			p[px] = py;
	}

}
