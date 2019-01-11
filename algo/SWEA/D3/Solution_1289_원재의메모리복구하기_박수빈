import java.util.Scanner;
 
public class Solution {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         
        int N = sc.nextInt();
         
        for(int n=1; n<=N; n++) {
            String num = sc.next();
            int answer = 0;
             
            for(int i=0; i<num.length() - 1; i++) {
                if(num.charAt(i) != num.charAt(i+1))
                    answer++;
            }
            if(num.charAt(0) == '1')
                answer++;
            System.out.println("#" + n + " " + answer);
             
        }
        sc.close();
    }
 
}
