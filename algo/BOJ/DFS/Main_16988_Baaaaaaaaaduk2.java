import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_16988_Baaaaaaaaaduk2 {
	static int N, M;
	static int[][] board;
	static ArrayList<int[]> list;
	static boolean[][] visited;
	static boolean flag;
	static int max;
	static int cnt;
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void dfs(int x, int y) {
		for(int i = 0; i < 4; i++) {//주변 순회
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(!visited[nx][ny] && board[nx][ny] == 2) {//2번 바둑돌 카운트하고 재귀 반복
				visited[nx][ny] = true;
				cnt++;
				dfs(nx, ny);
			} else if(board[nx][ny] == 0) {//빈 칸인 경우 flag값 변경
				flag = true;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N + 2][M + 2];
		list = new ArrayList<>();
		
		for(int i = 0; i < N + 2; i++) {//padding
			if(i == 0 || i == N + 1) {
				for(int j = 0; j < M + 2; j++) {
					board[i][j] = 1;
				}
			}
			board[i][0] = 1;
			board[i][M + 1] = 1;
		}
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j <= M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 0) {//빈 칸의 좌표 저장
					int[] xy = {i, j};
					list.add(xy);
				}
			}
		}

		for(int i = 0; i < list.size(); i++) {//빈 칸 두개를 고르는 모든 조합에 대하여 반복
			for(int j = i + 1; j < list.size(); j++) {
				int[] temp = list.get(i);
				int[] temp2 = list.get(j);
				board[temp[0]][temp[1]] = 1;//빈 칸에 바둑돌 두 개 놓기
				board[temp2[0]][temp2[1]] = 1;
				
				visited = new boolean[N + 2][M + 2];
				int total = 0;
				for(int k = 1; k <= N; k++) {
					for(int m = 1; m <= M; m++) {
						if(!visited[k][m] && board[k][m] == 2) {
							visited[k][m] = true;
							flag = false;
							cnt = 1;
							dfs(k, m);
							if(flag == false) {//빈 칸 없이 둘러 쌓인 경우 total 개수에 추가
								total += cnt;
							}
						}
					}
				}
				
				if(max < total)//max 값 업데이트
					max = total;
				
				board[temp[0]][temp[1]] = 0;//원상 복구
				board[temp2[0]][temp2[1]] = 0;
			}
		}
		System.out.println(max);
	}

}
