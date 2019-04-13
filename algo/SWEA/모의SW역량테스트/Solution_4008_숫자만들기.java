import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/* 
 * ó���� �������� ��� ������ ���ؼ� ��ȸ�ϴ� ������� Ǯ������ �ð��ȿ� ���� ������ �ʾҴ�
 * ���� �������� ��� �ڸ��� �ٲ��� �ʾƵ� �Ǳ� ������ depth���� �����ڸ� �ѹ����� ����ϴ� ������� �ذ��Ͽ���
 */
public class Solution_4008_���ڸ���� {
	static int N, min, max;
	static int[] operator;
	static int[] num;
	static boolean[] visited;
	
	public static void init(BufferedReader br) throws Exception {
		N = Integer.parseInt(br.readLine());
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		
		operator = new int[4];
		visited = new boolean[N - 1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int idx = 0, step = 0;
		for(int i = 0; i < 4; i++)//������ ����
			operator[i] = Integer.parseInt(st.nextToken());
		num = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++)//����
			num[i] = Integer.parseInt(st.nextToken());
	}
	
	public static void dfs(int depth, int result) {
		if(depth == N - 1) {//����� ������ ��
			if(min > result)
				min = result;
			if(max < result)
				max = result;
			return;
		}
		
		
		for(int i = 0; i < 4; i++) {//depth���� �����ڸ� ���� �ѹ����� �ẻ��
			if(operator[i] != 0) {
				operator[i]--;
				if(i == 0)
					dfs(depth + 1, result + num[depth + 1]);
				else if(i == 1)
					dfs(depth + 1, result - num[depth + 1]);
				else if(i == 2)
					dfs(depth + 1, result * num[depth + 1]);
				else if(i == 3)
					dfs(depth + 1, result / num[depth + 1]);
				operator[i]++;
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			init(br);
			dfs(0, num[0]);
			System.out.println("#" + tc + " " + (max - min));
		}
		
	}
}