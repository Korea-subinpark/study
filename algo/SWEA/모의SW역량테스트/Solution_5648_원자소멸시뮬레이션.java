import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
 * map�� ũ�Ⱑ [4001][4001]�̱� ������
 * �׽�Ʈ ���̽����� map�� ������ϸ� ��Ÿ�� ������ �߻��Ͽ���
 * map�� static ������ ���� �ڿ� �ѹ��� �����ϰ� ��� �׽�Ʈ���̽����� �����Ͽ�
 * ��Ÿ�� ������ �ذ��Ͽ���
 * */
public class Solution_5648_���ڼҸ�ùķ��̼� {
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	static int[][] map;
	
	static class Atom {
		int x, y, dir, k;
		boolean crush;
		public Atom(int x, int y, int dir, int k) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.k = k;
		}
	}
	
	public static boolean inRange(int x, int y) {
		return (x >= 0 && x < 4001 && y >= 0 && y < 4001);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		map = new int[4001][4001];//���ڵ� ��ȣ�� ����
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			Atom[] atoms = new Atom[N + 1];//���ڵ��� ������ ����
			
			int atomCnt = N;//���� ����
			for(int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken()) * 2 + 2000;
				int y = Integer.parseInt(st.nextToken()) * 2 + 2000;
				map[y][x] = i;
				atoms[i] = new Atom(x, y, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			int ans = 0;
			while(atomCnt > 0) {//���� ���ڰ� ���� ������
				ArrayList<Atom> crushList = new ArrayList<>();//�浹�� ���ڵ� ����
				
				for(int i = 1; i <= N; i++) {
					if(atoms[i].crush) continue;
					int nx = atoms[i].x + dx[atoms[i].dir];//�̵�
					int ny = atoms[i].y + dy[atoms[i].dir];
					
					if(!inRange(nx, ny)) {//�ٱ����� ���� ���
						if(map[atoms[i].y][atoms[i].x] == i)
							map[atoms[i].y][atoms[i].x] = 0;
						atoms[i].crush = true;
						atomCnt--;
						continue;
					}
					
					if(map[ny][nx] == 0) {//�̵��ϴ� ���� �ƹ� ���ڵ� ���� ���
						if(map[atoms[i].y][atoms[i].x] == i)
							map[atoms[i].y][atoms[i].x] = 0;
						map[ny][nx] = i;
						atoms[i].x = nx;
						atoms[i].y = ny;
 					} else {
 						if(map[ny][nx] > i) {//���� �̵����� ���� ������ ���
 							if(map[atoms[i].y][atoms[i].x] == i)
 								map[atoms[i].y][atoms[i].x] = 0;
 							map[ny][nx] = i;
 							atoms[i].x = nx;
 							atoms[i].y = ny;
 						} else {//�浹
 							if(map[atoms[i].y][atoms[i].x] == i)
 								map[atoms[i].y][atoms[i].x] = 0;
 							crushList.add(atoms[i]);
 							crushList.add(atoms[map[ny][nx]]);
 							atoms[i].x = nx;
 							atoms[i].y = ny;
 						}
 					}
				}
				for(int i = 0; i < crushList.size(); i++) {//�浹�� ���ڵ� ó��
					Atom temp = crushList.get(i);
					if(!temp.crush) {
						map[temp.y][temp.x] = 0;
						temp.crush = true;
						ans += temp.k;
						atomCnt--;
					}
				}
			}
			System.out.println("#" + tc + " " + ans);
		}
	}

}
