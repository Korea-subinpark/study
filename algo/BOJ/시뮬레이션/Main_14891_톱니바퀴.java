import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 비트 연산으로 톱니를 회전시킨다
 * 톱니가 맞닿는 부분인 3번째 비트와 7번째 비트를 각각 비교하여 톱니 회전 여부를 결정한다
 * */
public class Main_14891_톱니바퀴 {
	static int a, b, c, d;

	public static int cycle(int num, int dir) {
		if(dir == 1) {//시계방향 회전
			int b = (num & 1);
			num = num >> 1;
			num += (b << 7);
		} else {//반시계방향 회전
			int b = (num & 128);
			if(b == 128) {
				num -= 128;
				num = num << 1;
				num += (b >> 7);
			} else
				num = num << 1;
		}
		return num;
	}
	
	public static void solve(int num, int dir) {//돌리는 톱니바퀴 번호와 방향
		boolean[] chk = new boolean[3];
		chk[0] = ((a & 32) >> 5) == ((b & 2) >> 1);//1번과 2번의 맞닿은 톱니가 같은지 여부
		chk[1] = ((b & 32) >> 5) == ((c & 2) >> 1);//2번과 3번의 맞닿은 톱니가 같은지 여부
		chk[2] = ((c & 32) >> 5) == ((d & 2) >> 1);//3번과 4번의 맞닿은 톱니가 같은지 여부
		
		switch(num) {
			case 1://1번 톱니가 먼저 돌아가는 경우
				if(chk[0]) {
					a = cycle(a, dir);
				} else if(chk[1]) {
					a = cycle(a, dir);
					b = cycle(b, -dir);
				} else if(chk[2]) {
					a = cycle(a, dir);
					b = cycle(b, -dir);
					c = cycle(c, dir);
				} else {
					a = cycle(a, dir);
					b = cycle(b, -dir);
					c = cycle(c, dir);
					d = cycle(d, -dir);
				}
				break;
			case 2://2번 톱니가 먼저 돌아가는 경우
				if(!chk[0])
					a = cycle(a, -dir);
				if(chk[1]) {
					b = cycle(b, dir);
				} else if(chk[2]) {
					b = cycle(b, dir);
					c = cycle(c, -dir);
				} else {
					b = cycle(b, dir);
					c = cycle(c, -dir);
					d = cycle(d, dir);
				}
				break;
			case 3://3번 톱니가 먼저 돌아가는 경우
				if(!chk[2])
					d = cycle(d, -dir);
				if(chk[1]) {
					c = cycle(c, dir);
				} else if(chk[0]) {
					c = cycle(c, dir);
					b = cycle(b, -dir);
				} else {
					c = cycle(c, dir);
					b = cycle(b, -dir);
					a = cycle(a, dir);
				}
				break;
			case 4://4번 톱니가 먼저 돌아가는 경우
				if(chk[2]) {
					d = cycle(d, dir);
				} else if(chk[1]) {
					d = cycle(d, dir);
					c = cycle(c, -dir);
				} else if(chk[0]) {
					d = cycle(d, dir);
					c = cycle(c, -dir);
					b = cycle(b, dir);
				} else {
					d = cycle(d, dir);
					c = cycle(c, -dir);
					b = cycle(b, dir);
					a = cycle(a, -dir);
				}
				break;
			default:
				break;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		a = Integer.parseInt(br.readLine(), 2);
		b = Integer.parseInt(br.readLine(), 2);
		c = Integer.parseInt(br.readLine(), 2);
		d = Integer.parseInt(br.readLine(), 2);
	
		int K = Integer.parseInt(br.readLine());
		for(int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			solve(num, dir);
		}
		int ans = 0;
		ans += ((a & 128) >> 7);//점수 계산
		ans += ((b & 128) >> 6);
		ans += ((c & 128) >> 5);
		ans += ((d & 128) >> 4);
		System.out.println(ans);
	}

}
