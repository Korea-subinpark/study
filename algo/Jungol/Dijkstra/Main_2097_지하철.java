import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2097_����ö {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());//���� ����
		int M = Integer.parseInt(st.nextToken());//������ ��ȣ
		
		int[][] arr = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int[] dist = arr[0].clone();//���� �������κ��� �� ���������� �Ÿ�
		int[] path = new int[N];
		boolean[] used = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			int minIndex = -1;
			int min = Integer.MAX_VALUE;
			for(int j = 0; j < N; j++) {
				if(!used[j] && min > dist[j]) {
					minIndex = j;
					min = dist[j];
				}
			}
			used[minIndex] = true;
			for(int j = 0; j < N; j++) {
				if(!used[j] && dist[j] > dist[minIndex] + arr[minIndex][j]) {
					dist[j] = dist[minIndex] + arr[minIndex][j];
					path[j] = minIndex;
				}
			}
		}
		System.out.println(dist[M - 1]);
		int[] ans = new int[N];
		int cur = M - 1;
		int idx = 1;
		ans[0] = M;
		while(true) {
			if(path[cur] == 0) {
				ans[idx] = 1;
				break;
			} else {
				ans[idx++] = path[cur] + 1;
				cur = path[cur];
			}
		}
		for(int i = idx; i >= 0; i--)
			System.out.print(ans[i] + " ");
	}

}
