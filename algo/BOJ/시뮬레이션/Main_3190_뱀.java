import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main_3190_¹ì {
	static int N, K, L;
	static int currentDir, ans;
	static int[][] map;
	static Queue<Info> q;
	static Deque<P> snake;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static class P {
		int x, y;
		public P(int x, int y) {
			this.x = x;
			this.y = y;
		}
	} 
	
	static class Info {
		int X;
		char C;
		public Info(int X, char C) {
			this.X = X;
			this.C = C;
		}
	}
	
	public static void changeDir(char C) {
		if(C == 'L') {
			if(currentDir == 0) {
				currentDir = 3;
			} else {
				currentDir--;
			}
		} else {
			if(currentDir == 3) {
				currentDir = 0;
			} else {
				currentDir++;
			}
		}
	}
	
	public static boolean inRange(int x, int y) {
		return (x >= 1 && x <= N && y >= 1 && y <= N);
	}
	
	public static boolean go() {
		P head = snake.peekFirst();
		int nheadX = head.x + dx[currentDir];
		int nheadY = head.y + dy[currentDir];
		if(inRange(nheadX, nheadY)) {
			if(map[nheadX][nheadY] == 2) {
				map[nheadX][nheadY] = 1;
				snake.addFirst(new P(nheadX, nheadY));
				return true;
			} else if(map[nheadX][nheadY] == 1) {
				return false;
			} else if(map[nheadX][nheadY] == 0) {
				map[nheadX][nheadY] = 1;
				snake.addFirst(new P(nheadX, nheadY));
				P tail = snake.pollLast();
				map[tail.x][tail.y] = 0;
				return true;
			}
		}
		return false;
	}
	
	/*
	 * dir
	 * 0: À§
	 * 1: ¿À¸¥ÂÊ
	 * 2: ¾Æ·¡
	 * 3: ¿ÞÂÊ
	 * 
	 * map
	 * 0: ºóÄ­
	 * 1: ¹ì
	 * 2: »ç°ú
	 */
	public static void simulation() {
		while(!q.isEmpty()) {
			Info info = q.poll();
			int strate = info.X - ans;
			for(int i = 0; i < strate; i++) {
				if(!go()) {
					return;
				} else {
					ans++;
				}
			}
			changeDir(info.C);
		}
		
		while(true) {
			if(!go()) {
				return;
			} else {
				ans++;
			}
		}
		
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        currentDir = 1;
        
        snake = new LinkedList<>();
        snake.add(new P(1, 1));
        map = new int[N + 1][N + 1];
        map[1][1] = 1;
        for(int i = 0; i < K; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        	map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 2;
        }
        
        L = Integer.parseInt(br.readLine());
        q = new LinkedList<>();
        for(int i = 0; i < L; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        	int X = Integer.parseInt(st.nextToken());
        	char C = st.nextToken().charAt(0);
        	q.add(new Info(X, C));
        }
        
        simulation();
        
        System.out.println(ans + 1);
    }
    
}