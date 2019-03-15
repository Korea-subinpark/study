import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * ��Ʈ �������� ��ϸ� ȸ����Ų��
 * ��ϰ� �´�� �κ��� 3��° ��Ʈ�� 7��° ��Ʈ�� ���� ���Ͽ� ��� ȸ�� ���θ� �����Ѵ�
 * */
public class Main_14891_��Ϲ��� {
	static int a, b, c, d;

	public static int cycle(int num, int dir) {
		if(dir == 1) {//�ð���� ȸ��
			int b = (num & 1);
			num = num >> 1;
			num += (b << 7);
		} else {//�ݽð���� ȸ��
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
	
	public static void solve(int num, int dir) {//������ ��Ϲ��� ��ȣ�� ����
		boolean[] chk = new boolean[3];
		chk[0] = ((a & 32) >> 5) == ((b & 2) >> 1);//1���� 2���� �´��� ��ϰ� ������ ����
		chk[1] = ((b & 32) >> 5) == ((c & 2) >> 1);//2���� 3���� �´��� ��ϰ� ������ ����
		chk[2] = ((c & 32) >> 5) == ((d & 2) >> 1);//3���� 4���� �´��� ��ϰ� ������ ����
		
		switch(num) {
			case 1://1�� ��ϰ� ���� ���ư��� ���
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
			case 2://2�� ��ϰ� ���� ���ư��� ���
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
			case 3://3�� ��ϰ� ���� ���ư��� ���
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
			case 4://4�� ��ϰ� ���� ���ư��� ���
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
		ans += ((a & 128) >> 7);//���� ���
		ans += ((b & 128) >> 6);
		ans += ((c & 128) >> 5);
		ans += ((d & 128) >> 4);
		System.out.println(ans);
	}

}
