import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
 * 각 방향마다 케이스를 나눠서 블록들을 이동시킨다
 * 현재 블록이 0이 아닐 때, 0이 아닌 블록을 만날 때까지 이동한다
 * 만약 그 블록이 자신의 블록 숫자와 같다면 둘 다 0으로 만들고 현재 블록에 2를 곱해서 ArrayList에 삽입한다
 * 자신의 블록 숫자와 같지 않다면 현재 블록을 0으로 만들고 ArrayList에 삽입한다, 그리고 같지 않던 블록을 현재 블록으로 하여 다시 반복한다
 * 마지막에 ArrayList에 있는 블록을 차례로 배열에 복사한다
 * */
public class Main_12100_2048 {
	static int N, ans;
	static int[][] map;
	
	public static int move(int dir, int max) {
		ArrayList<Integer> list = new ArrayList<>();
		switch(dir) {
			case 0:
				for(int i = 0; i < N; i++) {
					list = new ArrayList<>();
					for(int j = 0; j < N; j++) {
						if(map[j][i] == 0) continue;
						int k = j + 1;
						while(k < N && map[k][i] == 0)
							k++;
						if(k < N) {
							if(map[j][i] == map[k][i]) {
								int sum = map[j][i] << 1;
								if(max < sum)
									max = sum;
								list.add(sum);
								map[j][i] = 0;
								map[k][i] = 0;
								j = k;
							} else {
								list.add(map[j][i]);
								map[j][i] = 0;
								j = k - 1;
							}
						} else {
							list.add(map[j][i]);
							map[j][i] = 0;
						}
					}
					for(int j = 0; j < list.size(); j++)
						map[j][i] = list.get(j);
				}
				break;
			case 1:
				for(int i = 0; i < N; i++) {
					list = new ArrayList<>();
					for(int j = N - 1; j >= 0; j--) {
						if(map[j][i] == 0) continue;
						int k = j - 1;
						while(k >= 0 && map[k][i] == 0)
							k--;
						if(k >= 0) {
							if(map[j][i] == map[k][i]) {
								int sum = map[j][i] << 1;
								if(max < sum)
									max = sum;
								list.add(sum);
								map[j][i] = 0;
								map[k][i] = 0;
								j = k;
							} else {
								list.add(map[j][i]);
								map[j][i] = 0;
								j = k + 1;
							}
						} else {
							list.add(map[j][i]);
							map[j][i] = 0;
						}
					}
					for(int j = 1; j <= list.size(); j++)
						map[N - j][i] = list.get(j - 1);
				}
				break;
			case 2:
				for(int i = 0; i < N; i++) {
					list = new ArrayList<>();
					for(int j = 0; j < N; j++) {
						if(map[i][j] == 0) continue;
						int k = j + 1;
						while(k < N && map[i][k] == 0)
							k++;
						if(k < N) {
							if(map[i][j] == map[i][k]) {
								int sum = map[i][j] << 1;
								if(max < sum)
									max = sum;
								list.add(sum);
								map[i][j] = 0;
								map[i][k] = 0;
								j = k;
							} else {
								list.add(map[i][j]);
								map[i][j] = 0;
								j = k - 1;
							}
						} else {
							list.add(map[i][j]);
							map[i][j] = 0;
						}
					}
					for(int j = 0; j < list.size(); j++)
						map[i][j] = list.get(j);
				}
				break;
			case 3:
				for(int i = 0; i < N; i++) {
					list = new ArrayList<>();
					for(int j = N - 1; j >= 0; j--) {
						if(map[i][j] == 0) continue;
						int k = j - 1;
						while(k >= 0 && map[i][k] == 0)
							k--;
						if(k >= 0) {
							if(map[i][j] == map[i][k]) {
								int sum = map[i][j] << 1;
								if(max < sum)
									max = sum;
								list.add(sum);
								map[i][j] = 0;
								map[i][k] = 0;
								j = k;
							} else {
								list.add(map[i][j]);
								map[i][j] = 0;
								j = k + 1;
							}
						} else {
							list.add(map[i][j]);
							map[i][j] = 0;
						}
					}
					for(int j = 1; j <= list.size(); j++)
						map[i][N - j] = list.get(j - 1);
				}
				break;
			default:
				break;
		}
		return max;
	}
	
	public static void dfs(int cnt, int max, int dir) {
		if(cnt == 5) {
			if(ans < max)
				ans = max;
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(i == dir) continue;
			int[][] temp = new int[N][N];
			for(int j = 0; j < N; j++)
				System.arraycopy(map[j], 0, temp[j], 0, N);
			int curMax = move(i, max);
			if(max < curMax)
				max = curMax;
			dfs(cnt + 1, max, i);
			for(int j = 0; j < N; j++)
				System.arraycopy(temp[j], 0, map[j], 0, N);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(max < map[i][j])
					max = map[i][j];
			}
		}
		
		ans = max;
		dfs(0, max, -1);
		
		System.out.println(ans);
	}

}
