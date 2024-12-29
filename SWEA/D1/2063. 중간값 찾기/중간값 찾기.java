import java.util.Scanner;
import java.io.FileInputStream;
import java.util.Arrays;
 
class Solution
{
    public static void main(String args[]) throws Exception
    {
        // System.setIn(new FileInputStream("res/input.txt"));
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
 
        int[] arr = new int[N];
 
        for(int n=0; n<N; n++){
            arr[n] = sc.nextInt();
        }
 
        Arrays.sort(arr);
        System.out.println(arr[N/2]);
 
        sc.close();
    }
}