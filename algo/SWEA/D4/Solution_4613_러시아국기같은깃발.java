import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4613_러시아국기같은깃발 {
	static int[][] arr;
	
	public static int cntChar(int s, int e, char c) {
		int cnt = 0;
		for(int i = s; i < e; i++) {
			if(c == 'W') cnt += arr[i][1] + arr[i][2];
			else if(c == 'B') cnt += arr[i][0] + arr[i][2];
			else cnt += arr[i][0] + arr[i][1];
		}
		return cnt;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			arr = new int[N][3];//W:0, B:1, R:2
			for(int i = 0; i < N; i++) {
				String s = br.readLine();
				for(int j = 0; j < s.length(); j++) {
					if(s.charAt(j) == 'W') arr[i][0]++;
					else if(s.charAt(j) == 'B') arr[i][1]++;
					else arr[i][2]++;
				}
			}
			
			int min = Integer.MAX_VALUE;
			for(int i = 1; i < N - 1; i++) {//W
				int w = cntChar(0, i, 'W');
				for(int j = 1; i + j < N; j++) {//B
					int b = cntChar(i, i + j, 'B');
					int r = cntChar(i + j, N, 'R');
					int sum = w + b + r;
					if(min > sum)
						min = sum;
				}
			}
			System.out.println("#" + tc + " " + min);
		}
	}
	
}
