import java.util.Arrays;
import java.util.Scanner;

public class Solution_1208_SW문제해결기본1일차_Flatten_박수빈 {

	public static void main(String[] args) {
		
	 	Scanner sc = new Scanner(System.in);
	    
	    for(int tc=1; tc<=1; tc++) {
	        int N = sc.nextInt();
	    	int[] arr = new int[100];
	        for(int i=0; i<100; i++)
	            arr[i] = sc.nextInt();
	        for(int i=0; i<N; i++) {
	         	Arrays.sort(arr);   
	            arr[0]++;
	            arr[99]--;
	        }
	        Arrays.sort(arr);
	        int ans = arr[99]-arr[0];
	        System.out.println("#" + tc + " " + ans);
	    }
	    sc.close();
	}

}
