import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_16234_�α��̵� {
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<int[]> list;//���ձ��� ��ǥ
	static int N, L, R;
	static int sum, cnt, ans;
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static boolean inRange(int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < N);
	}
	
	public static void dfs(int x, int y) {
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(inRange(nx, ny) && !visited[nx][ny]) {//���� ���� �����鼭 �湮���� ���� ��
				int diff = Math.abs(map[x][y] - map[nx][ny]);
				if(diff >= L && diff <= R) {//�α� ���̰� L�̻� R������ ��
					visited[nx][ny] = true;
					int[] temp = {nx, ny};
					sum += map[nx][ny];
					cnt++;
					list.add(temp);//���ձ��� �߰�
					dfs(nx, ny);
				}
			}
		}
	}
	
	public static void change() {
		int avg = sum / cnt;//�α� ��� ���
		for(int i = 0; i < list.size(); i++) {
			int[] temp = list.get(i);
			map[temp[0]][temp[1]] = avg;//�α� �ٲٱ�
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		while(true) {
			visited = new boolean[N][N];
			boolean flag = false;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(!visited[i][j]) {
						list = new ArrayList<>();
						int[] temp = {i, j};
						list.add(temp);
						visited[i][j] = true;
						sum = map[i][j];//���ձ� �α� �� ��
						cnt = 1;//���ձ��� ��
						dfs(i, j);//���ձ� Ȯ��
						if(cnt != 1) {
							change();//�α��̵� ��Ű��
							flag = true;
						}
						list.clear();
					}
				}
			}
			if(flag)//�α� �̵��� �־��� ���
				ans++;
			else {//�α� �̵��� ������ ��� ��� �� ����
				System.out.println(ans);
				return;
			}
		}
		
	}

}
