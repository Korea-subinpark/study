import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 왼쪽, 위쪽, 오른쪽에서 오른 경우를 모두 고려하여 최댓값을 저장한다
 * 위쪽과 왼쪽에서의 최댓값을 저장하고 위쪽과 오른쪽에서의 최댓값을 저장한 후에 둘 중 큰 값을 최종적으로 저장한다
 * */
public class Main_2169_로봇조종하기 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		int[][] max = new int[N][M];//현재좌표까지 최댓값 저장
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		max[0][0] = arr[0][0];
		for(int i = 1; i < M; i++) {
			arr[0][i] += arr[0][i - 1];
			max[0][i] = arr[0][i];//첫 행은 왼쪽으로 가는 경우의 수 밖에 존재하지 않는다
		}
		int[][] temp = new int[2][M];
		
		for(int i = 1; i < N; i++) {
			temp[0][0] = max[i - 1][0] + arr[i][0];
			for(int j = 1; j < M; j++) {
				temp[0][j] = Math.max(max[i - 1][j], temp[0][j - 1]) + arr[i][j];//왼쪽에서 오는 것과 위쪽에서 오는 것 비교
			}
			temp[1][M - 1] = max[i - 1][M - 1] + arr[i][M - 1];
			for(int j = M - 2; j >= 0; j--) {
				temp[1][j] = Math.max(max[i - 1][j], temp[1][j + 1]) + arr[i][j];//오른쪽에서 오는 것과 위쪽에서 오는 것 비교
			}
			for(int j = 0; j < M; j++) {
				max[i][j] = Math.max(temp[0][j], temp[1][j]);//최댓값 저장
			}
		}
		System.out.println(max[N - 1][M - 1]);
		
	}

}
