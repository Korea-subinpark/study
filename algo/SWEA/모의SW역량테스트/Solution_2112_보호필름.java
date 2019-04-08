import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2112_보호필름 {
	static int D, W, K, min;
	
	public static boolean[][] copy(boolean[][] f) {//2차원 배열 복사
		boolean[][] ret = new boolean[D][W];
		for(int i = 0; i < D; i++)
			System.arraycopy(f[i], 0, ret[i], 0, W);
		return ret;
	}
	
	public static boolean chk(boolean[][] f) {//성능 검사 함수
		for(int i = 0; i < W; i++) {
			boolean temp = f[0][i];
			boolean success = false;
			int cnt = 1;
			for(int j = 1; j < D; j++) {
				if(cnt >= K) {//성능을 만족하는 경우
					success = true;
					break;
				}
				if(temp == f[j][i])
					cnt++;
				else {
					temp = f[j][i];
					cnt = 1;
				}
			}
			if(cnt >= K)
				success = true;
			if(!success)//성능을 만족하지 못하는 경우
				return false;
		}
		return true;
	}
	
	public static void comb(int idx, boolean[][] f, int cnt) {
		if(min <= cnt)//백트래킹
			return;
		if(chk(f)) {//성능 검사를 만족한 경우
			if(min > cnt)
				min = cnt;
			return;
		}
		if(idx == D) return;//모든 행에 대하여 조합해본 경우
		
		boolean[][] A = copy(f);
		for(int i = 0; i < W; i++)//A약품 투입
			A[idx][i] = false;
		comb(idx + 1, A, cnt + 1);
		
		boolean[][] B = copy(f);
		for(int i = 0; i < W; i++)//B약품 투입
			B[idx][i] = true;
		comb(idx + 1, B, cnt + 1);
		
		comb(idx + 1, f, cnt);//약품을 투입하지 않았을 때
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			min = Integer.MAX_VALUE;
			
			boolean[][] film = new boolean[D][W];
			for(int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < W; j++) {
					int a = Integer.parseInt(st.nextToken());
					if(a == 0)
						film[i][j] = false;
					else
						film[i][j] = true;
				}
			}
			
			comb(0, film, 0);
			
			System.out.println("#" + tc + " " + min);
		}
	}

}
