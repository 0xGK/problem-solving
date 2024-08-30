#include<iostream>
#include<algorithm>
#include<vector>
using namespace std;

#define MAX_N 100001
int n, m;

vector<int> graph[MAX_N];
int points[MAX_N];

void init(){
    scanf("%d %d", &n, &m);
    for(int i=1; i<=n; i++){
        int a; scanf("%d",&a);
        if(a==-1) continue;
        graph[a].push_back(i);
        points[i]=0;
    }
    for(int i=0; i<m; i++){
        int staff, point; scanf("%d %d",&staff, &point);
        points[staff]+=point;
    }

}
void add_point(int s, int point){
    points[s]+=point;
    for(auto s2:graph[s]){
        add_point(s2, points[s]);
    }

}
void solve(){
    add_point(1, points[1]);

    for(int i=1; i<=n; i++){
        printf("%d ", points[i]);
    }
}
int main(){
    ios::sync_with_stdio(0); cin.tie(0);
    //freopen("input.txt","r",stdin);
    init();
    solve();
    return 0;
}