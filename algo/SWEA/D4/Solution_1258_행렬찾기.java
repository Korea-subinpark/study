import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//1258
public class Solution_1258_행렬찾기 {
	static int N;
	static int[][] map;
	static ArrayList<Matrix> list;
	
	public static class Matrix implements Comparable<Matrix> {
		int row, col;
		public Matrix(int row, int col) {
			this.row = row;
			this.col = col;
		}
		@Override
		public int compareTo(Matrix o) {//정렬 기준
			int area = row * col;
			int area2 = o.row * o.col;
			if(area == area2)
				return row - o.row;
			return area - area2;
		}
		
	}
	
	public static int cntRow(int x, int y) {//열 길이를 계산하는 함수
		int cnt = 0;
		for(int i = y; i < N; i++) {
			if(map[x][i] != 0)
				cnt++;
			else break;
		}
		return cnt;
	}
	
	public static int cntCol(int x, int y, int row) {//행 길이를 계산하며 행렬을 0으로 지우는 함수
		int cnt = 0;
		for(int i = x; i < N; i++) {
			if(map[i][y] != 0)
				cnt++;
			else break;
			for(int j = y; j < y + row; j++) {
				map[i][j] = 0;
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			list = new ArrayList<>();
			
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] != 0) {
						int col = cntRow(i, j);//열 계산
						int row = cntCol(i, j, col);//행 계산
						list.add(new Matrix(row, col));//행렬 추가
					}
				}
			}
			
			Collections.sort(list);//찾은 행렬 정렬
			sb.append("#" + tc + " " + list.size());
			for(int i = 0; i < list.size(); i++) {
				Matrix cur = list.get(i);
				sb.append(" " + cur.row + " " + cur.col);
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

}
