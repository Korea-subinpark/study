import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*크루스칼 알고리즘을 이용한다.*/

public class Solution_3124_최소스패닝트리_박수빈 {

	public static class Edge implements Comparable<Edge> {
		int s, e, w;
		public Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge o) {//가중치로 비교하기 위하여 Override
			return this.w - o.w;
		}
	}
	
	static int[] p;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int V = Integer.parseInt(st.nextToken());//정점의 수
			int E = Integer.parseInt(st.nextToken());//간선의 수
			ArrayList<Edge> list = new ArrayList<>();
			
			p = new int[V + 1];//DisJoint Sets를 구하기 위한 representative 배열
			
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				list.add(new Edge(s, e, w));//간선 추가
			}
			
			Collections.sort(list);//간선을 가중치를 기준으로 오름차순 정렬
			
			for(int i = 0; i < p.length; i++)//Disjoint Sets 생성
				makeSet(i);
			
			long sum = 0;//가중치 합
			int lim = 0;
			for(int i = 0; i < list.size(); i++) {
				Edge ed = list.get(i);
				if(findSet(ed.s) != findSet(ed.e)) {//loop가 생성되지 않을 때
					union(ed.s, ed.e);
					sum += ed.w;
					lim++;
				}
				if(lim == V)//최소 스패닝 트리 완성
					break;
			}
			
			System.out.println("#" + tc + " " + sum);//결과 출력
			
		}
	}
	
	public static void makeSet(int x) {
		p[x] = x;
	}
	
	public static int findSet(int x) {//representative를 반환하는 함수
		if (x == p[x])
			return x;
		else {
			p[x] = findSet(p[x]);// Path Compression
			return p[x];
		}
	}
	
	public static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		
		if(px != py) { //서로 다른 집합일 경우만 통합
			p[px] = py;
		}
	}
}
