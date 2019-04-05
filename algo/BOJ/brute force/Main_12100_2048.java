import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
 * �� ���⸶�� ���̽��� ������ ��ϵ��� �̵���Ų��
 * ���� ����� 0�� �ƴ� ��, 0�� �ƴ� ����� ���� ������ �̵��Ѵ�
 * ���� �� ����� �ڽ��� ��� ���ڿ� ���ٸ� �� �� 0���� ����� ���� ��Ͽ� 2�� ���ؼ� ArrayList�� �����Ѵ�
 * �ڽ��� ��� ���ڿ� ���� �ʴٸ� ���� ����� 0���� ����� ArrayList�� �����Ѵ�, �׸��� ���� �ʴ� ����� ���� ������� �Ͽ� �ٽ� �ݺ��Ѵ�
 * �������� ArrayList�� �ִ� ����� ���ʷ� �迭�� �����Ѵ�
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
