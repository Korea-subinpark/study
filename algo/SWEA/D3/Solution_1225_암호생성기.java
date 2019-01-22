import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
Queue를 사용하여 구현하였다
dequeue 한 값에 뺄셈을 하고 0보다 작은지 체크한 뒤에 다시 enqueue하였다
0보다 작다면 while문을 탈출하도록 
*/
public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for (int tc = 1; tc <= 10; tc++) {
			Queue<Integer> q = new LinkedList<>();
			sc.nextInt();
			for (int i = 0; i < 8; i++)
				q.add(sc.nextInt());
			
			while(true) {
				int temp = q.poll();
				temp -= 1;
				if(temp <= 0) {
					temp = 0;
					q.add(temp);
					break;
				}
				q.add(temp);
				temp = q.poll();
				temp -= 2;
				if(temp <= 0) {
					temp = 0;
					q.add(temp);
					break;
				}
				q.add(temp);
				temp = q.poll();
				temp -= 3;
				if(temp <= 0) {
					temp = 0;
					q.add(temp);
					break;
				}
				q.add(temp);
				temp = q.poll();
				temp -= 4;
				if(temp <= 0) {
					temp = 0;
					q.add(temp);
					break;
				}
				q.add(temp);
				temp = q.poll();
				temp -= 5;
				if(temp <= 0) {
					temp = 0;
					q.add(temp);
					break;
				}
				q.add(temp);
			}
			System.out.print("#" + tc + " ");
			for(int i = 0; i < 8; i++)
				System.out.print(q.poll() + " ");
			System.out.println();
		}
		
		
		sc.close();
	}

}
