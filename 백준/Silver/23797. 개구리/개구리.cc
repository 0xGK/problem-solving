#include<iostream>
#include<algorithm>
#include<string>

using namespace std;
int main(){
    cin.tie(0);cout.tie(0);ios::sync_with_stdio(0);
    string str; cin>>str;
    int K, P; K=P=0;
    int ret=1;

    if(str[0]=='K') K++;
    else P++;

    for(int i=1; i<str.size(); i++){
        if(str[i]=='K'){
            K++;
            if(P>0) P--;
            
        }else {
            P++;
            if(K>0) K--;
        }
        ret=max(ret, max(K, P));
    }
    cout<<ret;
    return 0;
}