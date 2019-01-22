/*
두 개의 지도를 or 연산하여 2진수로 변환한 뒤에 1은 #으로 0은 공백으로 
*/
class Solution {
  public String[] solution(int n, int[] arr1, int[] arr2) {
      String[] answer = new String[n];
      String[] temp = new String[n];
      for(int i = 0; i < n; i++) {
          temp[i] = Integer.toBinaryString(arr1[i] | arr2[i]);
          answer[i] = "";
          for(int j = 0; j < n-temp[i].length(); j++) {//앞자리 공백 채우기
              answer[i] += " ";
          }
          for(int j = 0; j < temp[i].length(); j++) {
              if(temp[i].charAt(j) == '1')
                  answer[i] += "#";
              else
                  answer[i] += " ";
          }
      }
      return answer;
  }
}
