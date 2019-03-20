import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_1938_�볪���ű�� {
	static int N, min;
	static int[][] map;
	static boolean[][][] visited;
	static Queue<Tree> q;
	static Tree target;//�Űܾ� �ϴ� ��ġ
	
	public static class Tree {
		int x, y;//�߰���ǥ�� ����
		int cnt;//���� Ƚ��
		boolean s;//���� ����(���ι���: false)
		public Tree(int x, int y, int cnt, boolean s) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.s = s;
		}
	}
	
	public static boolean inRange(int x, int y, boolean flag) {//���� �ȿ� �ִ��� Ȯ��
		if(!flag) {//���� ������ ���
			return (x >= 0 && x < N && y - 1 >= 0 && y + 1 < N);
		} else {//���� ������ ���
			return (x - 1 >= 0 && x + 1 < N && y >= 0 && y < N);
		}
	}
	
	public static boolean move(Tree t, int dir) {
		//dir: 0-��, 1-��, 2-��, 3-��, 4-ȸ��
		switch(dir) {
			case 0://��
				if(!t.s) {//���� ������ ���
					if(inRange(t.x - 1, t.y, false) && !visited[t.x - 1][t.y][0] && map[t.x - 1][t.y] == 0 && map[t.x - 1][t.y - 1] == 0 && map[t.x - 1][t.y + 1] == 0) {
						visited[t.x - 1][t.y][0] = true;
						q.add(new Tree(t.x - 1, t.y, t.cnt + 1, false));
						return true;
					}
				} else {//���� ������ ���
					if(inRange(t.x - 1, t.y, true) && !visited[t.x - 1][t.y][1] && map[t.x - 2][t.y] == 0) {
						visited[t.x - 1][t.y][1] = true;
						q.add(new Tree(t.x - 1, t.y, t.cnt + 1, true));
						return true;
					}
				}
				break;
			case 1://��
				if(!t.s) {//���� ������ ���
					if(inRange(t.x + 1, t.y, false) && !visited[t.x + 1][t.y][0] && map[t.x + 1][t.y] == 0 && map[t.x + 1][t.y - 1] == 0 && map[t.x + 1][t.y + 1] == 0) {
						visited[t.x + 1][t.y][0] = true;
						q.add(new Tree(t.x + 1, t.y, t.cnt + 1, false));
						return true;
					}
				} else {//���� ������ ���
					if(inRange(t.x + 1, t.y, true) && !visited[t.x + 1][t.y][1] && map[t.x + 2][t.y] == 0) {
						visited[t.x + 1][t.y][1] = true;
						q.add(new Tree(t.x + 1, t.y, t.cnt + 1, true));
						return true;
					}
				}	
				break;
			case 2://��
				if(!t.s) {//���� ������ ���
					if(inRange(t.x, t.y - 1, false) && !visited[t.x][t.y - 1][0] && map[t.x][t.y - 2] == 0) {
						visited[t.x][t.y - 1][0] = true;
						q.add(new Tree(t.x, t.y - 1, t.cnt + 1, false));
						return true;
					}
				} else {//���� ������ ���
					if(inRange(t.x, t.y - 1, true) && !visited[t.x][t.y - 1][1] && map[t.x - 1][t.y - 1] == 0 && map[t.x][t.y - 1] == 0 && map[t.x + 1][t.y - 1] == 0) {
						visited[t.x][t.y - 1][1] = true;
						q.add(new Tree(t.x, t.y - 1, t.cnt + 1, true));
						return true;
					}
				}
				break;
			case 3://��
				if(!t.s) {//���� ������ ���
					if(inRange(t.x, t.y + 1, false) && !visited[t.x][t.y + 1][0] && map[t.x][t.y + 2] == 0) {
						visited[t.x][t.y + 1][0] = true;
						q.add(new Tree(t.x, t.y + 1, t.cnt + 1, false));
						return true;
					}
				} else {//���� ������ ���
					if(inRange(t.x, t.y + 1, true) && !visited[t.x][t.y + 1][1] && map[t.x - 1][t.y + 1] == 0 && map[t.x][t.y + 1] == 0 && map[t.x + 1][t.y + 1] == 0) {
						visited[t.x][t.y + 1][1] = true;
						q.add(new Tree(t.x, t.y + 1, t.cnt + 1, true));
						return true;
					}
				}
				break;
			case 4://ȸ��
				if(!t.s) {//���� ������ ���
					if(inRange(t.x, t.y, true) && !visited[t.x][t.y][1]) {
						if(map[t.x - 1][t.y - 1] == 0 && map[t.x - 1][t.y] == 0 && map[t.x - 1][t.y + 1] == 0) {//ȸ���� �� ��ֹ��� ������ Ȯ��
							if(map[t.x + 1][t.y - 1] == 0 && map[t.x + 1][t.y] == 0 && map[t.x + 1][t.y + 1] == 0) {
								visited[t.x][t.y][1] = true;
								q.add(new Tree(t.x, t.y, t.cnt + 1, true));
								return true;
							}
						}
					}
				} else {//���� ������ ���
					if(inRange(t.x, t.y, false) && !visited[t.x][t.y][0]) {
						if(map[t.x - 1][t.y - 1] == 0 && map[t.x][t.y - 1] == 0 && map[t.x + 1][t.y - 1] == 0) {//ȸ���� �� ��ֹ��� ������ Ȯ��
							if(map[t.x - 1][t.y + 1] == 0 && map[t.x][t.y + 1] == 0 && map[t.x + 1][t.y + 1] == 0) {
								visited[t.x][t.y][0] = true;
								q.add(new Tree(t.x, t.y, t.cnt + 1, false));
								return true;
							}
						}
					}
				}
				break;
			default:
				break;
		}
		return false;
	}
	
	public static boolean complete(Tree t) {//�����ߴ��� Ȯ���ϴ� �Լ�
		return (t.x == target.x && t.y == target.y && t.s == target.s);
	}

	public static void bfs() {
		while(!q.isEmpty()) {
			Tree cur = q.poll();
			if(complete(cur)) {//��ǥ������ �������� ���
				min = cur.cnt;
				q.clear();
				return;
			}
			for(int i = 0; i < 5; i++) {//�����¿�� ȸ������ �� 5���� ��ȸ
				move(cur, i);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new boolean[N][N][2];//���ι����� ���� ���ι����� �� �и��ؼ� �ߺ�üũ
		q = new LinkedList<>();
		int[] B = new int[6];
		int[] E = new int[6];
		int Bidx = 0;
		int Eidx = 0;
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < N; j++) {
				char c = s.charAt(j);
				if(c == 'B') {//������� ����
					B[Bidx++] = i;
					B[Bidx++] = j;
					map[i][j] = 0;
				} else if(c == 'E') {//�������� ����
					E[Eidx++] = i;
					E[Eidx++] = j;
					map[i][j] = 0;
				} else {
					map[i][j] = c - '0';
				}
			}
		}
		
		Tree start = null;
		if(B[0] == B[2]) {//������ �� ������ ���ι����� ��
			start = new Tree(B[2], B[3], 0, false);
			visited[B[2]][B[3]][0] = true;
		} else {//���ι����� ��
			start = new Tree(B[2], B[3], 0, true);
			visited[B[2]][B[3]][1] = true;
		}
		if(E[0] == E[2]) {//�������� ������ ���ι����� ��
			target = new Tree(E[2], E[3], 0, false);
		} else {//���ι����� ��
			target = new Tree(E[2], E[3], 0, true);
		}
		q.add(start);
		
		bfs();
		
		System.out.println(min);
	}

}
