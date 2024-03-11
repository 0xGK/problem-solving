#include<iostream>
using namespace std;
#define MAX_N 1001

int N, M, K;

int main(){
    cin>>N>>M>>K;
    if(K<N+M-1){
        cout<<"NO\n";
    }else{
        cout<<"YES\n";
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                cout<<1+i+j<<' ';
            }
            cout<<'\n';
        }
    }




    return 0;
}