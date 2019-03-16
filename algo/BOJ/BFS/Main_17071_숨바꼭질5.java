import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
bfs로 탐색하며 이동 가능한 곳을 홀수 시간과 짝수 시간으로 나누어 최소 시간을 메모이제이션한다.
동생을 이동시키며 메모이제이션된 시간과 비교하고 동생이 도달한 시간이 더 클 경우 잡힌 것으로 출력한다.
*/
public class Main_17071_숨바꼭질5 {
	static int min = Integer.MAX_VALUE;
	static int[] memoOdd;//홀수 시간일 때 각 거리에 도달한 최소 시간
	static int[] memoEven;//짝수 시간일 때 각 거리에 도달한 최소 시간
	static int d;//동생이 맵을 벗어난 시간
	static Queue<Brown> q;
	
	public static class Brown {
		int p, t;//브라운 위치와 시간
		public Brown(int p, int t) {
			this.p = p;
			this.t = t;
		}
	}
	
	public static void bfs() {
		Brown cur = q.poll();
		if(cur.t % 2 == 0) {//짝수일 때 메모이제이션
			if(memoEven[cur.p] == -1) {
				memoEven[cur.p] = cur.t;
			} else
				return;
		} else {//홀수일 때 메모이제이션
			if(memoOdd[cur.p] == -1) {
				memoOdd[cur.p] = cur.t;
			} else
				return;
		}
		
		if(cur.p - 1 >= 0) {//한 칸 뒤로 이동
			q.add(new Brown(cur.p - 1, cur.t + 1));
		}
		if(cur.p + 1 <= 500000) {//한 칸 앞으로 이동
			q.add(new Brown(cur.p + 1, cur.t + 1));
		}
		if(cur.p * 2 <= 500000) {//두배 위치로 이동
			q.add(new Brown(cur.p * 2, cur.t + 1));
		}
		
	}
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();//코니 위치
        int N = sc.nextInt();//브라운 위치

        memoOdd = new int[500001];//홀수 시간일 때 최소 시간
        memoEven = new int[500001];//짝수 시간일 때 최소 시간
        q = new LinkedList<>();
        q.add(new Brown(K, 0));//브라운 출발 위치
        
        for(int i = 0; i < memoOdd.length; i++) {
        	memoOdd[i] = -1;
        	memoEven[i] = -1;
        }
        
        while(!q.isEmpty()) {//수빈 이동
        	bfs();
        }
        d = 0;
        for(int i = N; i <= 500000; i += d) {//동생이 이동하며 메모이제이션된 시간보다 긴 경우 잡힌다
        	if(d % 2 == 0) {
        		if(memoEven[i] <= d) {
            		System.out.print(d);
            		return;
            	}
        	} else {
        		if(memoOdd[i] <= d) {
            		System.out.print(d);
            		return;
            	}
        	}
        	d++;
        }
        
        System.out.println(-1);//못잡은 경우
    }
}
