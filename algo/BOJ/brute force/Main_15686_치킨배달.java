import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15686_치킨배달 {
	static int N, M;
	static ArrayList<int[]> home;//집 좌표
	static ArrayList<int[]> chicken;//치킨집 좌표
	static int[] dist;//각 집의 치킨거리
	static int ans = 1000000000;//최소 치킨 거리 합
	static int len;//선택된 치킨집 수
	
	public static int total() {//치킨 거리 합 구하는 함수
		int t = 0;
		for(int i = 0; i < dist.length; i++)
			t += dist[i];
		return t;
	}
	
	public static void update(int x, int y) {//치킨 집의 좌표를 받아 치킨 거리를 최소로 업데이트하는 함수
		for(int i = 0; i < home.size(); i++) {
			int[] hxy = home.get(i);
			int cur = Math.abs(hxy[0] - x) + Math.abs(hxy[1] - y);//치킨 거리 구하기
			if(dist[i] > cur)//기존의 치킨 거리와 비교하여 업데이트
				dist[i] = cur;
		}
	}
	
	public static void solve(int idx) {
		if(len == M) {//M개의 치킨집을 모두 골랐을 때
			int t = total();
			if(t < ans)//치킨 거리의 합을 계산하여 현재까지의 최솟값인 ans와 비교하여 업데이트
				ans = t;
			return;
		}
		int[] temp = new int[home.size()];
		temp = Arrays.copyOf(dist, dist.length);//이전 상태로 돌아가기 위해 백업
		
		for(int i = idx; i < chicken.size(); i++) {
			int[] nxy = chicken.get(i);//치킨집 좌표
			update(nxy[0], nxy[1]);//현재 선택된 치킨집 좌표로 치킨 거리 업데이트
			len++;//선택된 치킨집 수 증가
			solve(i + 1);//다음 치킨집 선택
			len--;//선택된 치킨집 수 감소
			dist = Arrays.copyOf(temp, temp.length);//이전 상태로
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());//남겨야 하는 치킨집 수
		
		home = new ArrayList<>();
		chicken = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				int a = Integer.parseInt(st.nextToken());
				if(a == 1) {//집의 좌표 저장
					int[] temp = {i, j};
					home.add(temp);
				} else if (a == 2) {//치킨집의 좌표 저장
					int[] temp = {i, j};
					chicken.add(temp);
				}
			}
		}
		
		dist = new int[home.size()];
		for(int i = 0; i < dist.length; i++)//치킨 거리 최대로 초기화
			dist[i] = 1000000000;
		
		solve(0);
		System.out.println(ans);
	}

}
