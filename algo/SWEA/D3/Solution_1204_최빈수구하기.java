import java.util.Scanner;

public class Solution_1204_SW문제해결기본1일차최빈수구하기_박수빈 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        
        for(int t=1; t<=tc; t++) {
         	int N = sc.nextInt();
         	int[] arr = new int[101];
            
            for(int i=0; i<1000; i++)
           		arr[sc.nextInt()]++;
            
            
            int max = 0, answer = 0;
            for(int i=0; i<100; i++) {
             	if(max <= arr[i]) {
             		max = arr[i];
             		answer = i;
             	}
                 	
            }
            System.out.println("#" + N + " " + answer);
        }
        sc.close();
	}

}
