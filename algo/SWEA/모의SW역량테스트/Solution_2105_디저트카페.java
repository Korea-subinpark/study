import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 순회를 시작하는 지점을 r,c로 놓고 변을 w와 h라고 하면
 * (r,c), (r+w, r+c), (r+w+h, c+w-h), (r+h, c-h)를 꼭지점으로 하는 사각형이 구성된다
 * 이 꼭지점들이 범위를 벗어나지 않는지 확인한 뒤에
 * 사각형을 모두 순회하며 겹치는 번호가 없는지 확인한다
 * 모든 조건을 만족하면 카페의 개수는 (w+h) * 2가 된다
 */
public class Solution_2105_디저트카페 {
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			int maxCnt = -1;
			for(int r = 0; r < N - 2; r++) {
				for(int c = 1; c < N - 1; c++) {
					for(int w = 1; w < N; w++) {
						if(r + w >= N || c + w >= N) continue;
						for(int h = 1; h < N; h++) {
							if(c - h < 0 || r + h + w >= N) continue;
							int cnt = visit(r, c, w, h);
							if(maxCnt < cnt)
								maxCnt = cnt;
						}
					}
				}
			}
			System.out.println("#" + tc + " " + maxCnt);
		}
	}
	
	public static int visit(int r, int c, int w, int h) {//만들어진 사각형에서 카페 수 카운트
		boolean[] number = new boolean[101];
		for(int i = 0; i <= w; i++) {
			if(number[map[r + i][c + i]]) return -1;//번호가 겹치는 경우
			number[map[r + i][c + i]] = true;
			if(number[map[r + h + i][c - h + i]]) return -1;//번호가 겹치는 경우
			number[map[r + h + i][c - h + i]] = true;
		}
		
		for(int i = 1; i < h; i++) {
			if(number[map[r + i][c - i]]) return -1;//번호가 겹치는 경우
			number[map[r + i][c - i]] = true;
			if(number[map[r + w + i][c + w - i]]) return -1;//번호가 겹치는 경우
			number[map[r + w + i][c + w - i]] = true;
		}
		
		return (w + h) << 1;
	}
	
}