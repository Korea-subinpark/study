import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_1767_���μ��������ϱ� {
	static int N, min, maxCellCnt;
	static int[][] cell;
	static ArrayList<Core> cores;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static class Core {
		int x, y;
		int[] len;//�� ������ ���� ����
		boolean connected;
		public Core(int x, int y) {
			this.x = x;
			this.y = y;
			len = new int[4];
		}
	}
	
	public static void init(BufferedReader br) throws Exception {
		N = Integer.parseInt(br.readLine().trim());
		cell = new int[N][N];
		cores = new ArrayList<>();
		min = Integer.MAX_VALUE;
		maxCellCnt = 0;
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				cell[i][j] = Integer.parseInt(st.nextToken());
				if(cell[i][j] == 1) {
					Core c = new Core(i, j);
					c.len[0] = i;
					c.len[1] = N - 1 - i;
					c.len[2] = j;
					c.len[3] = N - 1 - j;
					for(int k = 0; k < 4; k++)
						if(c.len[k] == 0)
							c.connected = true;//���尡���� �ִ� ���
					cores.add(c);
				}
			}
		}
	}
	
	public static void dfs(int depth, int sum, int[][] curCell, int cellCnt) {
		if(depth == cores.size()) {//�ִ��� ���� �ھ �����ϸ鼭 �ּ����� ������ ����� �� ������Ʈ
			if(maxCellCnt < cellCnt) {
				maxCellCnt = cellCnt;
				min = sum;
				return;
			} else if(maxCellCnt == cellCnt) {
				if(min > sum)
					min = sum;
			}
			return;
		}
		
		int flag2 = 0;
		
		Core c = cores.get(depth);
		for(int i = 0; i < 4; i++) {//4���� ��� ��ȸ
			if(c.connected == true) {//�����ڸ��� �ִ� �ھ�
				dfs(depth + 1, sum, curCell, cellCnt + 1);
				return;
			}
			
			int[][] temp = new int[N][N];
			for(int j = 0; j < N; j++)
				System.arraycopy(curCell[j], 0, temp[j], 0, N);
			
			
			int nx = c.x;
			int ny = c.y;
			boolean flag = false;
			
			for(int j = 0; j < c.len[i]; j++) {
				nx += dx[i];
				ny += dy[i];
				if(temp[nx][ny] == 0) {
					temp[nx][ny] = 2;
				} else {
					flag = true;
					flag2++;
					break;
				}
			}
			
			if(flag)//�����ִ� ���
				continue;
			
			dfs(depth + 1, sum + c.len[i], temp, cellCnt + 1);
		}
		if(flag2 == 4)//4������ ��� �����ִ� ��� ������� �ʴ´�
			dfs(depth + 1, sum, curCell, cellCnt);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= T; tc++) {
			init(br);
			dfs(0, 0, cell, 0);
			System.out.println("#" + tc + " " + min);
		}
	}

}
