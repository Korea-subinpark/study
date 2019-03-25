import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;
/*
 * ArrayList ���� remove���� ���� �ð��� �ɸ��� ������ �ð� �ʰ��� �߻��Ѵ�
 * 
 */
public class Main_16235_��������ũ {
	static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	public static class Tree {
		int x, y, age;
		boolean dead;
		public Tree(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}
	}
    
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] A = new int[N][N];
		int[][] map = new int[N][N];
		LinkedList<Tree> ts = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5;
			}
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());
			ts.add(new Tree(x, y, age));
		}
		
		for(int i = 0; i < K; i++) {
			LinkedList<Tree> momTree = new LinkedList<>();
			LinkedList<Tree> babyTree = new LinkedList<>();
			//��
			for(Tree cur : ts) {
				if(map[cur.x][cur.y] >= cur.age) {//���� ����� �ִ� ���
					map[cur.x][cur.y] -= cur.age;
					cur.age += 1;
					if(cur.age % 5 == 0)
						momTree.add(cur);
				} else {//����� ����� ���� ���
					cur.dead = true;
				}
			}
			//����
			for(Iterator<Tree> it = ts.listIterator(); it.hasNext(); ) {
				Tree cur = it.next();
				if(cur.dead) {//���� ������ ���
					map[cur.x][cur.y] += (cur.age >> 1);//������� �߰�
					it.remove();
				}
			}
			//����
			for(Tree cur : momTree) {
				for(int dir = 0; dir < 8; dir++) {//�ֺ� 8�� ��ȸ
					int nx = cur.x + dx[dir];
					int ny = cur.y + dy[dir];
					if(nx >= 0 && nx < N && ny >= 0 && ny < N)
						babyTree.add(0, new Tree(nx ,ny, 1));//���ο� ���� ����
				}
			}
			ts.addAll(0, babyTree);
			//�ܿ�
			for(int j = 0; j < N; j++)
				for(int k = 0; k < N; k++)
					map[j][k] += A[j][k];//��� ���ϱ�
		}
		System.out.println(ts.size());
	}

}
