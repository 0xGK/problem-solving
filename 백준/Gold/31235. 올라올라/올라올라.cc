#include <iostream>
#include<algorithm>
using namespace std;

int main(){
    int ret, cnt, N, prev;
    cin >> N;
    cin >> prev;
    ret=cnt=1;
    while (--N){
        int cur; cin >> cur;
        if (cur < prev){
            cnt++;
        }
        else{
            ret = max(ret, cnt);
            prev = cur;
            cnt = 1;
        }
    }
    ret=max(ret,cnt);
    
    cout << ret<<endl;
    return 0;
}