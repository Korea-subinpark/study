import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;
/*
 * ArrayList 사용시 remove에서 많은 시간이 걸리기 때문에 시간 초과가 발생한다
 * 
 */
public class Main_16235_나무재테크 {
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
			//봄
			for(Tree cur : ts) {
				if(map[cur.x][cur.y] >= cur.age) {//먹을 양분이 있는 경우
					map[cur.x][cur.y] -= cur.age;
					cur.age += 1;
					if(cur.age % 5 == 0)
						momTree.add(cur);
				} else {//양분이 충분히 없는 경우
					cur.dead = true;
				}
			}
			//여름
			for(Iterator<Tree> it = ts.listIterator(); it.hasNext(); ) {
				Tree cur = it.next();
				if(cur.dead) {//죽은 나무인 경우
					map[cur.x][cur.y] += (cur.age >> 1);//양분으로 추가
					it.remove();
				}
			}
			//가을
			for(Tree cur : momTree) {
				for(int dir = 0; dir < 8; dir++) {//주변 8방 순회
					int nx = cur.x + dx[dir];
					int ny = cur.y + dy[dir];
					if(nx >= 0 && nx < N && ny >= 0 && ny < N)
						babyTree.add(0, new Tree(nx ,ny, 1));//새로운 나무 생성
				}
			}
			ts.addAll(0, babyTree);
			//겨울
			for(int j = 0; j < N; j++)
				for(int k = 0; k < N; k++)
					map[j][k] += A[j][k];//양분 더하기
		}
		System.out.println(ts.size());
	}

}
