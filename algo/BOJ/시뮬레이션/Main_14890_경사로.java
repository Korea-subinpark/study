import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14890_경사로 {
	static int N, L;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int ans = 0;
		int valid = 0;
		int j = 0;
		for(int i = 0; i < N; i++) {//가로 검사
			valid = 1;
			for(j = 1; j < N; j++) {
				if(map[i][j] == map[i][j - 1])//이전 숫자와 같은 경우 유효숫자 증가
					valid++;
				else if(map[i][j] == map[i][j - 1] + 1 && valid >= L)//오르막인 경우 유효 숫자가 경사로 길이이상이어야 한다
					valid = 1;
				else if(map[i][j] == map[i][j - 1] - 1 && valid >= 0)//내리막인 경우
					valid = 1 - L;
				else
					break;
			}
			if(j == N && valid >= 0)
				ans++;
		}
		for(int i = 0; i < N; i++) {//세로 검사
			valid = 1;
			for(j = 1; j < N; j++) {
				if(map[j][i] == map[j - 1][i])//이전 숫자와 같은 경우 유효숫자 증가
					valid++;
				else if(map[j][i] == map[j - 1][i] + 1 && valid >= L)//오르막인 경우 유효 숫자가 경사로 길이이상이어야 한다
					valid = 1;
				else if(map[j][i] == map[j - 1][i] - 1 && valid >= 0)//내리막인 경우
					valid = 1 - L;
				else
					break;
			}
			if(j == N && valid >= 0)
				ans++;
		}
		System.out.println(ans);
	}

}
