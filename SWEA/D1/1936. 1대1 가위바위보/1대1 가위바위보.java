import java.util.Scanner;
import java.io.FileInputStream;
 
class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int gap = a-b;
        if(gap == 1 || gap == -2){
            System.out.println("A");
        } else {
            System.out.println("B");
        }
        sc.close();
    }
}