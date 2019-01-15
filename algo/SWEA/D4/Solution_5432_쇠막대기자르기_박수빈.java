import java.util.Scanner;
import java.util.Stack;

public class Solution_5432_�踷����ڸ���_�ڼ��� {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			int pole = 0;//���� ����
			int result = 0;//�߸� ���� ��
			String str = sc.next();
			Stack st = new Stack();
			for(int i=0; i < str.length(); i++) {
				char c = str.charAt(i);
				if(c == '(') {
					if(str.charAt(i + 1) == ')') {//������
						result += pole;
						i++;
					} else {//���ο� ����
						pole++;
						result++;
						st.push(c);
					}
				} else {//������ ��
					st.pop();
					pole--;
				}
			}
			System.out.println("#" + tc + " " + result);
		}
		sc.close();
	}
}
