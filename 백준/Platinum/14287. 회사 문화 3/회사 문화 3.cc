#include <bits/stdc++.h>
using namespace std;
#define MAX_N 100001
int n,m;
int cnt=0;
int s[MAX_N], e[MAX_N];
vector<int> graph[MAX_N];
vector<int>segTree(MAX_N*4);
vector<int>lazy(MAX_N*4);

void dfs(int v){
    s[v] = ++cnt;
    for(int nv : graph[v]){
        dfs(nv);
    }
    e[v]=cnt;
}

int create_segment(int node,int s,int e){
    if(s==e){return segTree[node]=0;}
    return segTree[node]=create_segment(node*2,s,(s+e)/2)+create_segment(node*2+1,(s+e)/2+1,e);
}
void update_lazy(int node,int s,int e){
    if(lazy[node]!=0){
        segTree[node]+=lazy[node]*(e-s+1);
        if(s!=e){
            lazy[node*2]+=lazy[node];
            lazy[node*2+1]+=lazy[node];
        }
        lazy[node]=0;
    }
}
int query(int n,int s,int e,int left,int right){
    update_lazy(n,s,e);

    if(left>e||right<s) return 0;
    
    if(left<=s&&e<=right) return segTree[n];

    return query(n*2,s,(s+e)/2,left,right)+query(n*2+1,(s+e)/2+1,e,left,right);
}
void update_range(int n,int s,int e,int sidx,int eidx,int diff){
    update_lazy(n,s,e);
    
    if(eidx<s||sidx>e) return;

    if(sidx<=s&&e<=eidx){
        lazy[n]+=diff;
        update_lazy(n,s,e);
        return;
    }

    update_range(n*2,s,(s+e)/2,sidx,eidx,diff);
    update_range(n*2+1,(s+e)/2+1,e,sidx,eidx,diff);
    segTree[n]=segTree[n*2]+segTree[n*2+1];
}
int main(){
    ios::sync_with_stdio(0);
    cin.tie(0),cout.tie(0);
    cin >> n >> m;
    int q,c,d,p;
    for(int i = 1; i <= n; i++){
        cin >> p;
        if(p!=-1)
            graph[p].push_back(i);
    }
    dfs(1);
    create_segment(1,1,n);
    for(int i=0;i<m;i++){
        cin >> q;
        if(q==1){
            cin >> c >> d;
            update_range(1,1,n,s[c],s[c],d);
        }else{
            cin >> c;
            cout << query(1,1,n,s[c],e[c]) << '\n';
        }
    }
}