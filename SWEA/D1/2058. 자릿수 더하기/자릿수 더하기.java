import java.util.Scanner;
import java.io.FileInputStream;
 
class Solution
{
    public static void main(String args[]) throws Exception
    {
        // System.setIn(new FileInputStream("res/input.txt"));
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        String str = String.valueOf(a);
        int ans=0;
        for(int i=0; i<str.length(); i++){
            ans += str.charAt(i)-'0';
        }
        System.out.println(ans);
 
        sc.close();
    }
}