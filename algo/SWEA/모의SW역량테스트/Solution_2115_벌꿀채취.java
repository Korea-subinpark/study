import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2115_����ä�� {
	static int N, M, C, max;
	static int[][] map;
	static boolean[] visited;
	
	public static boolean valid(int x, int y, int x2, int y2) {
		return ((y + M) <= N && (y2 + M) <= N && (x != x2 || (x == x2 && (y + M) <= y2)));
	}
	
	public static void dfs(int[] arr, int depth, int sum, int sqrSum) {
		if(depth > M)//��� ������ ��ȸ�� ��� ��ȯ
			return;
		if(max < sqrSum)//�ִ� ���� ������Ʈ
			max = sqrSum;
		
		for(int i = 0; i < M; i++) {
			if(!visited[i]) {//���� ä������ ���� ������ ���
				visited[i] = true;
				if(sum + arr[i] <= C)//ä���� �� �ִ� �ִ뷮�� ���� �ʴ� ���
					dfs(arr, depth + 1, sum + arr[i], sqrSum + arr[i] * arr[i]);
				visited[i] = false;
			}
		}
	}
	
	public static int chk(int x, int y, int x2, int y2) {
		int[] a = new int[M];
		int[] b = new int[M];
		for(int i = 0; i < M; i++) {//ä���� ���� ����
			a[i] = map[x][y + i];
			b[i] = map[x2][y2 + i];
		}
		
		int ret = 0;
		max = 0;
		dfs(a, 0, 0, 0);//ù ��° �ϲ��� �ִ�� ���� �� �ִ� ����
		ret += max;
		max = 0;
		dfs(b, 0, 0, 0);//�� ��° �ϲ��� �ִ�� ���� �� �ִ� ����
		ret += max;
		
		return ret;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			visited = new boolean[M];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			int ans = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					for(int k = 0; k < N; k++) {
						for(int m = 0; m < N; m++) {
							if(valid(i, j, k, m)) {//������ ���� ��ġ�� �ʴ��� üũ
								int ret = chk(i, j, k, m);
								if(ans < ret)//�ִ� ������Ʈ
									ans = ret;
							}
						}
					}
				}
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}

}