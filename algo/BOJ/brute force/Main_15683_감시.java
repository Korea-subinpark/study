import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15683_���� {
	static int N, M;
	static int[][] arr;
	static int[][] ccty;//ccty ��ȣ�� ��ǥ ����
	static int cctyNum;//ccty ����
	static int ans;
	static int[] loop = {0,4,2,4,4,1};//ccty ��ȣ���� �ʿ��� �ݺ��� Ƚ��
	
	public static int cntZero() {//�簢���븦 ���� �Լ�
		int cnt = 0;
		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++)
				if(arr[i][j] == 0)
					cnt++;
		return cnt;
	}
	
	public static void observe(int x, int y, int dir) {
		switch(dir % 4) {
		case 0://��
			for(int i = x - 1; i >= 0; i--) {
				if(arr[i][y] == 6)//���� ���� ��� Ż��
					break;
				arr[i][y] = 7;
			}
			break;
			
		case 1://��
			for(int i = y + 1; i < M; i++) {
				if(arr[x][i] == 6)//���� ���� ��� Ż��
					break;
				arr[x][i] = 7;
			}
			break;
			
		case 2://��
			for(int i = x + 1; i < N; i++) {
				if(arr[i][y] == 6)//���� ���� ��� Ż��
					break;
				arr[i][y] = 7;
			}
			break;
			
		case 3://��
			for(int i = y - 1; i >= 0; i--) {
				if(arr[x][i] == 6)//���� ���� ��� Ż��
					break;
				arr[x][i] = 7;
			}
			break;
		}
		
	}
	
	public static void solve(int idx) {
		if(idx == cctyNum) { //���� ���� (��� ccty Ž�� �Ϸ�)
			int cnt = cntZero();
			if(ans > cnt)//�ּ� �簢���� ���� ������Ʈ
				ans = cnt;
			return;
		}
		
		int[][] temp = new int[N][M];
		for(int i = 0; i < N; i++) //�迭 ����
			temp[i] = Arrays.copyOf(arr[i], M);
		
		for(int i = 0; i < loop[ccty[idx][0]]; i++) {//�� ccty���� �ʿ��Ѹ�ŭ ����
			switch(ccty[idx][0]) {
			case 1:
				observe(ccty[idx][1], ccty[idx][2], i);//�� ���� ���⸸ ����
				break;
			case 2:
				observe(ccty[idx][1], ccty[idx][2], i);//�ݴ� ���� ����
				observe(ccty[idx][1], ccty[idx][2], i + 2);
				break;
			case 3:
				observe(ccty[idx][1], ccty[idx][2], i);//���� ���� ����
				observe(ccty[idx][1], ccty[idx][2], i + 1);
				break;
			case 4:
				observe(ccty[idx][1], ccty[idx][2], i);//�� ���� ����
				observe(ccty[idx][1], ccty[idx][2], i + 1);
				observe(ccty[idx][1], ccty[idx][2], i + 2);
				break;
			case 5:
				observe(ccty[idx][1], ccty[idx][2], i);//�� ���� ����
				observe(ccty[idx][1], ccty[idx][2], i + 1);
				observe(ccty[idx][1], ccty[idx][2], i + 2);
				observe(ccty[idx][1], ccty[idx][2], i + 3);
				break;
			}
			
			solve(idx + 1);//���� cctv
			for(int j = 0; j < N; j++) //�迭 ����
				arr[j] = Arrays.copyOf(temp[j], M);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		ccty = new int[8][3];//�ִ� 8�� ccty�� ��ȣ�� ��ǥ
		ans = 100;//�簢���� ���� �ִ밪���� ũ�� ����
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] != 0 && arr[i][j] != 6) {//��ĭ�̳� ���� �ƴ� ��� ccty�̹Ƿ� ���� ����
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
