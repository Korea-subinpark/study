import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2167_2차원배열의합 {
	static int N, M;
	static int[][] arr;
	
	public static void print() {
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N + 1][M + 1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				arr[i][j] += arr[i][j - 1] + arr[i - 1][j] - arr[i - 1][j - 1];//현재 좌표까지의 합 저장
			}
		}
		
		int K = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int[] xy = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			sb.append((arr[xy[2]][xy[3]] - arr[xy[2]][xy[1] - 1] - arr[xy[0] - 1][xy[3]] + arr[xy[0] - 1][xy[1] - 1]) + "\n");
		}
		System.out.print(sb);
	}

}
