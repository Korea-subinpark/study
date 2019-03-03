import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1932_�����ﰢ�� {

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
		
		for(int i = 1; i < n; i++) {//�� ���� case�� ������ ����
			for(int j = 0; j <= i; j++) {
				if(j == 0) {//�� ���� ù ��° ���Ҵ� ���� ���� ù ��° ���ҿ��� ���Ѵ�
					arr[i][j] += arr[i - 1][j];
				} else if(j == i) {//�� ���� ������ ���Ҵ� ���� ���� ������ ���ҿ��� ���Ѵ�
					arr[i][j] += arr[i - 1][j - 1];
				} else {//�߰� ���ҵ��� ���� ���� �� �� �߿��� ū ���� ���Ѵ�
					arr[i][j] += Math.max(arr[i - 1][j - 1], arr[i - 1][j]);
				}
			}
		}
		
		int max = arr[n - 1][0];
		for(int i = 1; i < n; i++) {//������ �࿡�� �ִ� ã��
			if(max < arr[n - 1][i])
				max = arr[n - 1][i];
		}
		
		System.out.print(max);
	}

}
