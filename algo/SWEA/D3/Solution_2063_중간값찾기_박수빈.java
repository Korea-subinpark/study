import java.util.Arrays;
import java.util.Scanner;

public class Solution_2063_�߰���ã��_�ڼ��� {

	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        int[] arrSort = new int[N];
        
        
        for(int i=0; i<N; i++)
         	   arr[i] = sc.nextInt();
        
        //����
        //0 ~ n-2
        //0 ~ n-3
        //...
        //0 ~ 0
        /*int temp = 0;
        for(int i=N-1; i>=0; i--) {
        	for(int j=0; j<i; j++) {
        		if(arr[j] > arr[j+1]) {
        			temp = arr[j+1];
        			arr[j+1] = arr[j];
        			arr[j] = temp;
        		}
        	}
        	if (i == N/2-1) break; //�߰����� ã���� �ǹǷ� �ݸ� ����
        }
        //System.out.println(Arrays.toString(arr));*/
        
        //ī���� ����
        int[] freq = new int[101];
		for(int i=0; i<N; i++)
			freq[arr[i]]++;
		
		for(int i=1; i<101; i++)
			freq[i] = freq[i] + freq[i-1];
		
		
		for(int i=N-1; i>=1; i--) {
			arrSort[freq[arr[i]]-1] = arr[i];
			freq[arr[i]]--;
		}
        //Arrays.sort(arr);
		//System.out.println(Arrays.toString(arrSort));
		//System.out.println(arr[N/2]);
		System.out.println(arrSort[N/2]);
		sc.close();
	}
}
