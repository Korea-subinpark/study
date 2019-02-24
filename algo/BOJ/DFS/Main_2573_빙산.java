import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * dfs�� ������ ���� ���� ���鼭 �� ������ ���̿� ������ �ٴ幰�� ���� �����Ѵ�
 * ������ ���� �� ��� �̻��� �� �ð��� ��� �� �����ϰ�
 * �� ��� ������ �� �����ߴ� �� ������ ������ ���� ������ ���� ���� �ٽ� dfs Ž���� �ݺ��Ѵ�
 * 
 * */


public class Main_2573_���� {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static Queue<Ice> list;//�� ������ ��ǥ�� ����, ������ �ٴ幰�� ���� �����ϴ� ť
	static int num, area, year;//������ ��, ���� ����� ��, ������ �ð�
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static class Ice {
		int x, y, h, num;//��ǥ�� �ڽ��� ����, �ֺ� �ٴ幰�� ��
		public Ice(int x, int y, int h, int num) {
			this.x = x;
			this.y = y;
			this.h = h;
			this.num = num;
		}
	}
	
	public static boolean inRange(int x, int y) {
		if(x >= 0 && x < N && y >= 0 && y < M)
			return true;
		return false;
	}
	
	public static void dfs(int x, int y) {
		int cnt = 0;
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(inRange(nx, ny)) {
				if(map[nx][ny] == 0) {
					cnt++;
				} else if(!visited[nx][ny]) {
					visited[nx][ny] = true;
					dfs(nx, ny);
				}
			}
		}
		list.add(new Ice(x, y, map[x][y], cnt));
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		list = new LinkedList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0)
					num++;
			}
		}
		
		while(true) {
			for(int i = 0; i < N; i++) {//���� �� ����
				for(int j = 0; j < M; j++) {
					if(map[i][j] != 0 && !visited[i][j]) {
						visited[i][j] = true;
						dfs(i, j);
						area++;
					}
				}
			}
			
			if(area > 1) {// �� ��� �̻��� ���
				System.out.print(year);//��� �� ����
				return;
			} else {
				year++;
			}
			
			//���� ���̱�
			while(!list.isEmpty()) {
				Ice temp = list.poll();
				if(temp.h <= temp.num) {//��Ƽ� ����� ���
					map[temp.x][temp.y] = 0;
					num--;
				} else {
					map[temp.x][temp.y] = temp.h - temp.num;
				}
			}
			
			if(num <= 1) {//������ �� ���� ������ �� ����� �� �� ���� ���
				System.out.print(0);
				return;
			}
			
			area = 0;
			visited = new boolean[N][M];
		}
		
	}

}
