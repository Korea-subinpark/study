import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 2���� dp �迭�� �ִ��� �����Ѵ�
 * 0���� ��㸸 ������� ��
 * 1���� ��㸸 ������� ��
 * 1, 2���� ��㸸 ������� �� ...
 * ... 1 ~ 7���� ����� ������� �� ������ �ִ��� ��� ������Ʈ �Ѵ�
 * ����� �ϴٰ� ������� �Ѿ�� ��쿡�� ���� �ִ��� �״�� �����Ͽ� ����ó���Ѵ�
 * */

public class Main_14501_��� {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] plan = new int[N + 1][2];//��� ����ǥ
		int[][] dp = new int[N + 1][N + 1];
		int ans = 0;
		
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			plan[i][0] = Integer.parseInt(st.nextToken());//����ϴµ� �ʿ��� �Ⱓ
			plan[i][1] = Integer.parseInt(st.nextToken());//���� �� �ִ� �ݾ�
		}
		
		for(int i = 1; i <= N; i++) { //1�������� ����� �ϳ��� ����Ѵ�
			int idx = plan[i][0] + i - 1;
			if(idx > N) {//����� �ϴٰ� ������� �Ѿ�� ���
				if(i != N)//������ ���� �ƴ϶�� ���� �ִ��� �״�� �����Ѵ�
					System.arraycopy(dp[i - 1], 0, dp[i], 0, N + 1);
				continue;
			}
			System.arraycopy(dp[i - 1], 0, dp[i], 0, idx);
			for(int j = idx; j <= N; j++) {//�ִ� ����
				dp[i][j] = Math.max(dp[i - 1][j], plan[i][1] + dp[i - 1][idx - plan[i][0]]);
			}
			if(ans < dp[i][N])
				ans = dp[i][N];
		}
		
		System.out.println(ans);
	}

}
