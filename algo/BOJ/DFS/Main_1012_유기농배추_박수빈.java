import java.util.Scanner;

public class Main_1012_유기농배추_박수빈 {
	static int[][] arr;
	static boolean[][] visited;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void dfs(int x, int y) {
		if (visited[x][y]) return;
		else visited[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (arr[nx][ny] == 1)
				dfs(nx, ny);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int M = sc.nextInt();
			int N = sc.nextInt();
			arr = new int[M + 2][N + 2];
			visited = new boolean[M + 2][N + 2];
			int ans = 0;

			int K = sc.nextInt();
			for (int i = 0; i < K; i++)
				arr[sc.nextInt() + 1][sc.nextInt() + 1] = 1;// 배추를 심은 곳

			for (int i = 1; i < M + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					if (arr[i][j] == 1 && !visited[i][j]) {// 배추를 심었고 아직 확인하지 않은 곳
						ans++;
						dfs(i, j);
					}
				}
			}
			System.out.println(ans);
		}

		sc.close();
	}

}
