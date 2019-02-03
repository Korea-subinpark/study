import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15683_감시 {
	static int N, M;
	static int[][] arr;
	static int[][] ccty;//ccty 번호와 좌표 저장
	static int cctyNum;//ccty 개수
	static int ans;
	static int[] loop = {0,4,2,4,4,1};//ccty 번호마다 필요한 반복문 횟수
	
	public static int cntZero() {//사각지대를 세는 함수
		int cnt = 0;
		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++)
				if(arr[i][j] == 0)
					cnt++;
		return cnt;
	}
	
	public static void observe(int x, int y, int dir) {
		switch(dir % 4) {
		case 0://상
			for(int i = x - 1; i >= 0; i--) {
				if(arr[i][y] == 6)//벽을 만난 경우 탈출
					break;
				arr[i][y] = 7;
			}
			break;
			
		case 1://우
			for(int i = y + 1; i < M; i++) {
				if(arr[x][i] == 6)//벽을 만난 경우 탈출
					break;
				arr[x][i] = 7;
			}
			break;
			
		case 2://하
			for(int i = x + 1; i < N; i++) {
				if(arr[i][y] == 6)//벽을 만난 경우 탈출
					break;
				arr[i][y] = 7;
			}
			break;
			
		case 3://좌
			for(int i = y - 1; i >= 0; i--) {
				if(arr[x][i] == 6)//벽을 만난 경우 탈출
					break;
				arr[x][i] = 7;
			}
			break;
		}
		
	}
	
	public static void solve(int idx) {
		if(idx == cctyNum) { //종료 조건 (모든 ccty 탐색 완료)
			int cnt = cntZero();
			if(ans > cnt)//최소 사각지대 수로 업데이트
				ans = cnt;
			return;
		}
		
		int[][] temp = new int[N][M];
		for(int i = 0; i < N; i++) //배열 복사
			temp[i] = Arrays.copyOf(arr[i], M);
		
		for(int i = 0; i < loop[ccty[idx][0]]; i++) {//각 ccty마다 필요한만큼 감시
			switch(ccty[idx][0]) {
			case 1:
				observe(ccty[idx][1], ccty[idx][2], i);//한 가지 방향만 감시
				break;
			case 2:
				observe(ccty[idx][1], ccty[idx][2], i);//반대 방향 감시
				observe(ccty[idx][1], ccty[idx][2], i + 2);
				break;
			case 3:
				observe(ccty[idx][1], ccty[idx][2], i);//직각 방향 감시
				observe(ccty[idx][1], ccty[idx][2], i + 1);
				break;
			case 4:
				observe(ccty[idx][1], ccty[idx][2], i);//세 방향 감시
				observe(ccty[idx][1], ccty[idx][2], i + 1);
				observe(ccty[idx][1], ccty[idx][2], i + 2);
				break;
			case 5:
				observe(ccty[idx][1], ccty[idx][2], i);//네 방향 감시
				observe(ccty[idx][1], ccty[idx][2], i + 1);
				observe(ccty[idx][1], ccty[idx][2], i + 2);
				observe(ccty[idx][1], ccty[idx][2], i + 3);
				break;
			}
			
			solve(idx + 1);//다음 cctv
			for(int j = 0; j < N; j++) //배열 복사
				arr[j] = Arrays.copyOf(temp[j], M);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		ccty = new int[8][3];//최대 8개 ccty의 번호와 좌표
		ans = 100;//사각지대 수를 최대값보다 크게 설정
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] != 0 && arr[i][j] != 6) {//빈칸이나 벽이 아닐 경우 ccty이므로 정보 저장
					ccty[cctyNum][0] = arr[i][j];
					ccty[cctyNum][1] = i;
					ccty[cctyNum++][2] = j;
				}
			}
		}
		
		solve(0);
		System.out.println(ans);
	}

}
