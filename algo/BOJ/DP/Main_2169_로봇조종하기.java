import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * ����, ����, �����ʿ��� ���� ��츦 ��� ����Ͽ� �ִ��� �����Ѵ�
 * ���ʰ� ���ʿ����� �ִ��� �����ϰ� ���ʰ� �����ʿ����� �ִ��� ������ �Ŀ� �� �� ū ���� ���������� �����Ѵ�
 * */
public class Main_2169_�κ������ϱ� {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		int[][] max = new int[N][M];//������ǥ���� �ִ� ����
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		max[0][0] = arr[0][0];
		for(int i = 1; i < M; i++) {
			arr[0][i] += arr[0][i - 1];
			max[0][i] = arr[0][i];//ù ���� �������� ���� ����� �� �ۿ� �������� �ʴ´�
		}
		int[][] temp = new int[2][M];
		
		for(int i = 1; i < N; i++) {
			temp[0][0] = max[i - 1][0] + arr[i][0];
			for(int j = 1; j < M; j++) {
				temp[0][j] = Math.max(max[i - 1][j], temp[0][j - 1]) + arr[i][j];//���ʿ��� ���� �Ͱ� ���ʿ��� ���� �� ��
			}
			temp[1][M - 1] = max[i - 1][M - 1] + arr[i][M - 1];
			for(int j = M - 2; j >= 0; j--) {
				temp[1][j] = Math.max(max[i - 1][j], temp[1][j + 1]) + arr[i][j];//�����ʿ��� ���� �Ͱ� ���ʿ��� ���� �� ��
			}
			for(int j = 0; j < M; j++) {
				max[i][j] = Math.max(temp[0][j], temp[1][j]);//�ִ� ����
			}
		}
		System.out.println(max[N - 1][M - 1]);
		
	}

}
