import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14890_���� {
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
		for(int i = 0; i < N; i++) {//���� �˻�
			valid = 1;
			for(j = 1; j < N; j++) {
				if(map[i][j] == map[i][j - 1])//���� ���ڿ� ���� ��� ��ȿ���� ����
					valid++;
				else if(map[i][j] == map[i][j - 1] + 1 && valid >= L)//�������� ��� ��ȿ ���ڰ� ���� �����̻��̾�� �Ѵ�
					valid = 1;
				else if(map[i][j] == map[i][j - 1] - 1 && valid >= 0)//�������� ���
					valid = 1 - L;
				else
					break;
			}
			if(j == N && valid >= 0)
				ans++;
		}
		for(int i = 0; i < N; i++) {//���� �˻�
			valid = 1;
			for(j = 1; j < N; j++) {
				if(map[j][i] == map[j - 1][i])//���� ���ڿ� ���� ��� ��ȿ���� ����
					valid++;
				else if(map[j][i] == map[j - 1][i] + 1 && valid >= L)//�������� ��� ��ȿ ���ڰ� ���� �����̻��̾�� �Ѵ�
					valid = 1;
				else if(map[j][i] == map[j - 1][i] - 1 && valid >= 0)//�������� ���
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
