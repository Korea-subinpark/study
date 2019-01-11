import java.util.Scanner;

public class Main_1107_리모컨_박수빈 {
	static boolean[] button = new boolean[10];

	static int moveChanel(int ch) {
		int len = 0;
		String chS = Integer.toString(ch);
		for(int i=0; i<chS.length(); i++) {
			if(button[ch % 10] == true) {//버튼이 고장나서 이동할 수 없는 채널
				return 0;
			}
			else {
				len++;
				ch = ch / 10;
			}
		}
		return len;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int targetChanel = sc.nextInt();
		int N = sc.nextInt();
		for (int i = 0; i < N; i++)
			button[sc.nextInt()] = true;

		int answer = Math.abs(targetChanel - 100);// 위아래 버튼으로만 조작했을 때
		int len = 0, result = 0;

		for (int i = 0; i < 999900; i++) {
			len = moveChanel(i);
			
			if(len != 0)
				result = Math.abs(targetChanel - i) + len;
			
			if(result > 0 && result < answer)
				answer = result;
		}
		System.out.println(answer);
		sc.close();
	}

}
