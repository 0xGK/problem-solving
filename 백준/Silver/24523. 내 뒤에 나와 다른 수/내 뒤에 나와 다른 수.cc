#include<iostream>
#include<algorithm>
#include<vector>

using namespace std;
#define MAX_N 1000001
int N;
int arr[MAX_N];
vector<int> vec;
int main(){
    cin.tie(0);cout.tie(0);ios::sync_with_stdio(0);

    cin>>N;
    for(int i=1; i<=N; i++) cin>>arr[i];
    
    int idx=-1;

    for(int i=N; i>1; i--){
        vec.push_back(idx);
        if(arr[i-1]!=arr[i]){
            idx=i;
        }
    }
    vec.push_back(idx);
    for(int i=N-1; i>=0; i--) cout<<vec[i]<<' ';

    return 0;
}