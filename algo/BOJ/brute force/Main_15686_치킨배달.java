import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15686_ġŲ��� {
	static int N, M;
	static ArrayList<int[]> home;//�� ��ǥ
	static ArrayList<int[]> chicken;//ġŲ�� ��ǥ
	static int[] dist;//�� ���� ġŲ�Ÿ�
	static int ans = 1000000000;//�ּ� ġŲ �Ÿ� ��
	static int len;//���õ� ġŲ�� ��
	
	public static int total() {//ġŲ �Ÿ� �� ���ϴ� �Լ�
		int t = 0;
		for(int i = 0; i < dist.length; i++)
			t += dist[i];
		return t;
	}
	
	public static void update(int x, int y) {//ġŲ ���� ��ǥ�� �޾� ġŲ �Ÿ��� �ּҷ� ������Ʈ�ϴ� �Լ�
		for(int i = 0; i < home.size(); i++) {
			int[] hxy = home.get(i);
			int cur = Math.abs(hxy[0] - x) + Math.abs(hxy[1] - y);//ġŲ �Ÿ� ���ϱ�
			if(dist[i] > cur)//������ ġŲ �Ÿ��� ���Ͽ� ������Ʈ
				dist[i] = cur;
		}
	}
	
	public static void solve(int idx) {
		if(len == M) {//M���� ġŲ���� ��� ����� ��
			int t = total();
			if(t < ans)//ġŲ �Ÿ��� ���� ����Ͽ� ��������� �ּڰ��� ans�� ���Ͽ� ������Ʈ
				ans = t;
			return;
		}
		int[] temp = new int[home.size()];
		temp = Arrays.copyOf(dist, dist.length);//���� ���·� ���ư��� ���� ���
		
		for(int i = idx; i < chicken.size(); i++) {
			int[] nxy = chicken.get(i);//ġŲ�� ��ǥ
			update(nxy[0], nxy[1]);//���� ���õ� ġŲ�� ��ǥ�� ġŲ �Ÿ� ������Ʈ
			len++;//���õ� ġŲ�� �� ����
			solve(i + 1);//���� ġŲ�� ����
			len--;//���õ� ġŲ�� �� ����
			dist = Arrays.copyOf(temp, temp.length);//���� ���·�
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());//���ܾ� �ϴ� ġŲ�� ��
		
		home = new ArrayList<>();
		chicken = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				int a = Integer.parseInt(st.nextToken());
				if(a == 1) {//���� ��ǥ ����
					int[] temp = {i, j};
					home.add(temp);
				} else if (a == 2) {//ġŲ���� ��ǥ ����
					int[] temp = {i, j};
					chicken.add(temp);
				}
			}
		}
		
		dist = new int[home.size()];
		for(int i = 0; i < dist.length; i++)//ġŲ �Ÿ� �ִ�� �ʱ�ȭ
			dist[i] = 1000000000;
		
		solve(0);
		System.out.println(ans);
	}

}
