import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1932_정수삼각형 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n][n];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j <= i; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 1; i < n; i++) {//세 가지 case로 나눠서 연산
			for(int j = 0; j <= i; j++) {
				if(j == 0) {//각 행의 첫 번째 원소는 이전 행의 첫 번째 원소에서 더한다
					arr[i][j] += arr[i - 1][j];
				} else if(j == i) {//각 행의 마지막 원소는 이전 행의 마지막 원소에서 더한다
					arr[i][j] += arr[i - 1][j - 1];
				} else {//중간 원소들은 이전 행의 두 수 중에서 큰 값에 더한다
					arr[i][j] += Math.max(arr[i - 1][j - 1], arr[i - 1][j]);
				}
			}
		}
		
		int max = arr[n - 1][0];
		for(int i = 1; i < n; i++) {//마지막 행에서 최댓값 찾기
			if(max < arr[n - 1][i])
				max = arr[n - 1][i];
		}
		
		System.out.print(max);
	}

}
