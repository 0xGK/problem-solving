#include <iostream>
using namespace std;

int main(){
    unsigned long long n;
    cin >> n;
    n--;
    int f_cnt = 0;
    while (n > 0)
    {
        if (n % 2){
            f_cnt++;
        }
        n /= 2;
    }
    cout << f_cnt % 2;

    return 0;
}