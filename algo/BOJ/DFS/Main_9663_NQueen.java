import java.util.Scanner;

/*
 * �ϳ��� �࿡ �ϳ��� ���� ��ġ�ϴ� �������
 * ���� ���� ��� �߿��� �ڽŰ� ���� ���� �ְų� ���� �밢�� , ������ �밢���� �ִ��� Ȯ���Ͽ� ��Ʈ��ŷ�Ѵ�
 * ���� �밢���� ��� ���� �� ���� ���� ������ �밢���� ��� ���� ���� ���� ����
 */

public class Main_9663_NQueen {
	static int[] arr;
	static int n, cnt;
	
	public static void dfs(int idx) {
		if(idx == n) {
			cnt++;
			return;
		}
		
		for(int i = 0; i < n; i++) {
			arr[idx] = i;
			boolean flag = false;
			for(int j = 0; j < idx; j++) {
				if(arr[j] == i || j - arr[j] == idx - arr[idx] || j + arr[j] == idx + arr[idx]) {//���� ���� �밢�� üũ
					flag = true;
					break;
				}
			}
			if(flag == false) {//���� �� �Ǵ� �밢���� ���� ���� ���
				dfs(idx + 1);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		
		arr = new int[n];
		
		for(int i = 0; i < n; i++) {
			arr[0] = i;
			dfs(1);
		}
		
		System.out.println(cnt);
		sc.close();
	}

}
