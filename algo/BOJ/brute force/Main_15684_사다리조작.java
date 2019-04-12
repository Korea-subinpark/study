import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_15684_��ٸ����� {
	static int N, M, H, min;
	static int[][] ladder;
	static ArrayList<P> list;//��ٸ��� ���� ���� �� �ִ� �ڸ�
	
	static class P {
		int x, y;
		public P (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static boolean simulation() {//��ٸ� Ÿ�� �ùķ��̼�
		for(int i = 1; i <= N; i++) {
			int curP = i;
			for(int j = 1; j <= H; j++) {
				if(ladder[j][curP] == 1)
					curP++;
				else if(ladder[j][curP - 1] == 1)
					curP--;
			}
			if(i != curP)
				return false;
		}
		return true;
	}
	
	public static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		min = Integer.MAX_VALUE;
		
		ladder = new int[H + 1][N + 1];
		for(int i = 1; i <= H; i++) {
			ladder[i][0] = 2;
			ladder[i][N] = 2;
		}
		
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			ladder[a][b] = 1;
			ladder[a][b - 1] = 2;//��ٸ� �� ���� ���ο� ��ٸ��� ���� �� ����
			ladder[a][b + 1] = 2;
		}
		
		for(int i = 1; i <= H; i++)
			for(int j = 1; j <= N; j++)
				if(ladder[i][j] == 0)//��ٸ��� ���� �� �ִ� �� ��� ����
					list.add(new P(i, j));
		
	}
	
	public static void solve() {
		for(int i = 0; i < list.size(); i++) {//��ٸ� �ϳ� �߰�
			P a = list.get(i);
			ladder[a.x][a.y] = 1;
			if(simulation()) {
				min = 1;
				return;
			}
			ladder[a.x][a.y]= 0; 
		}
		
		for(int i = 0; i < list.size() - 1; i++) {//��ٸ� �� �� �߰�
			for(int j = i + 1; j < list.size(); j++) {
				P a = list.get(i);
				P b = list.get(j);
				ladder[a.x][a.y] = 1;
				ladder[b.x][b.y] = 1;
				if(simulation()) {
					min = 2;
					return;
				}
				ladder[a.x][a.y] = 0;
				ladder[b.x][b.y] = 0;
			}
		}
		
		for(int i = 0; i < list.size() - 2; i++) {//��ٸ� �� �� �߰�
			for(int j = i + 1; j < list.size() - 1; j++) {
				for(int k = j + 1; k < list.size(); k++) {
					P a = list.get(i);
					P b = list.get(j);
					P c = list.get(k);
					ladder[a.x][a.y] = 1;
					ladder[b.x][b.y] = 1;
					ladder[c.x][c.y] = 1;
					if(simulation()) {
						min = 3;
						return;
					}
					ladder[a.x][a.y] = 0;
					ladder[b.x][b.y] = 0;
					ladder[c.x][c.y] = 0;
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		init();
		if(simulation())//���ο� ��ٸ��� ���� �ʾƵ� �����ϴ� ���
			min = 0;
		else
			solve();
		if(min == Integer.MAX_VALUE)
			System.out.println(-1);//�Ұ����� ���
		else
			System.out.println(min);
	}

}
