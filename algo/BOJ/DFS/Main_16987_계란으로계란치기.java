import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16987_계란으로계란치기 {
	static int[][] egg;//계란의 내구도와 무게 저장
	static boolean[] crashed;//깨진 계란: true
	static int max, N;
	
	public static void dfs(int cur, int cnt) {
		if(max < cnt)//최댓값 업데이트
			max = cnt;
		if(cur >= N) return;//맨 오른쪽 계란에 도달한 이후 종료
		if(crashed[cur]) {//현재 집어든 계란이 깨져있는 경우 다음 계란으로 이동
			dfs(cur + 1, cnt);
			return;
		}
		for(int i = 0; i < N; i++) {
			if(cur == i) continue;//자기자신 건너뛰기
			if(!crashed[i] && !crashed[cur]) {//들고있는 계란이 깨지지 않았을 때
				if(egg[cur][0] <= egg[i][1]) {//들고 있는 계란이 깨진 경우
					crashed[cur] = true;
					if(egg[i][0] <= egg[cur][1]) {//내려친 계란이 깨진 경우
						crashed[i] = true;
						dfs(cur + 1, cnt + 2);//다음 계란으로
						crashed[cur] = false;
						crashed[i] = false;
					} else {//내려친 계란이 깨지지 않은 경우
						egg[i][0] -= egg[cur][1];
						dfs(cur + 1, cnt + 1);
						egg[i][0] += egg[cur][1];
						crashed[cur] = false;
					}
				} else {//현재 들고 있는 계란이 깨지지 않은 경우
					egg[cur][0] -= egg[i][1];
					if(egg[i][0] <= egg[cur][1]) {//내려친 계란이 깨진 경우
						crashed[i] = true;
						dfs(cur + 1, cnt + 1);//다음 계란으로
						crashed[i] = false;
						egg[cur][0] += egg[i][1];
					} else {//내려친 계란이 깨지지 않은 경우
						egg[i][0] -= egg[cur][1];
						dfs(cur + 1, cnt);
						egg[i][0] += egg[cur][1];
						egg[cur][0] += egg[i][1];
					}
				}
				
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		egg = new int[N + 1][2];
		crashed = new boolean[N + 1];
		crashed[N] = true;
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			egg[i][0] = Integer.parseInt(st.nextToken());//내구도
			egg[i][1] = Integer.parseInt(st.nextToken());//무게
		}
		
		dfs(0, 0);
		
		System.out.println(max);
	}

}
