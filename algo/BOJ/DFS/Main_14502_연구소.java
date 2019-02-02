import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
벽을 세울 수 있는 모든 위치의 좌표를 저장하고
모든 경우의 수를 탐색하여
안전 영역 최대 크기를 
*/
public class Main_14502_연구소 {
	static int[][] arr;
	static ArrayList<int[]> xy;//빈 칸의 좌표
	static boolean[][] visited;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int zeroCnt, tempCnt;//빈 칸의 개수
	static int maxCnt;//안전 영역 최대 크기
	static int N, M;
	
	public static void dfs(int x, int y) {
		if(!visited[x][y]) {
			visited[x][y] = true;
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(!visited[nx][ny] && arr[nx][ny] == 0) {
					tempCnt--;
					dfs(nx, ny);
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N + 2][M + 2];
		xy = new ArrayList<>();
		
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j < M + 1; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 0) {//빈 칸의 좌표를 저장
					int[] temp = {i, j};
					xy.add(temp);
					zeroCnt++;
				}
			}
		}
		
		for(int i = 0; i < arr.length; i++) {//padding
			arr[i][0] = 1;
			arr[i][M + 1] = 1;
		}
		for(int i = 0; i < arr[0].length; i++) {//padding
			arr[0][i] = 1;
			arr[N + 1][i] = 1;
		}
		
		for(int i = 0; i < xy.size() - 2; i++) {
			for(int j = i + 1; j < xy.size() - 1; j++) {
				for(int k = j + 1; k < xy.size(); k++) {
					visited = new boolean[N + 2][M + 2];
					int x1 = xy.get(i)[0];//1번 벽의 좌표
					int y1 = xy.get(i)[1];
					int x2 = xy.get(j)[0];//2번 벽의 좌표
					int y2 = xy.get(j)[1];
					int x3 = xy.get(k)[0];//3번 벽의 좌표
					int y3 = xy.get(k)[1];
					
					arr[x1][y1] = 1;//벽 설치
					arr[x2][y2] = 1;
					arr[x3][y3] = 1;
					tempCnt = zeroCnt - 3; //초기 빈 칸 개수에서 추가된 벽 개수 3 빼기
					
					for(int x = 1; x < N + 1; x++) {
						for(int y = 1; y < M + 1; y++) {
							if(!visited[x][y] && arr[x][y] == 2) {
								dfs(x, y);
							}
						}
					}
					
					if(maxCnt < tempCnt) {//안전 영역 최대 크기 업데이트
						maxCnt = tempCnt;
					}
					arr[x1][y1] = 0;//원상 복귀
					arr[x2][y2] = 0;
					arr[x3][y3] = 0;
				}
			}
		}
		
		System.out.println(maxCnt);
		
	}

}
