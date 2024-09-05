#include <bits/stdc++.h>
using namespace std;
#define MAX_N 100001
int n,m;
int cnt=0;
vector<int> graph[MAX_N];
int s[MAX_N], e[MAX_N];

vector<int>segTree(MAX_N*4);
vector<int>lazy(MAX_N*4);
vector<int>n_segTree(MAX_N*4);
vector<int>n_lazy(MAX_N*4);
void dfs(int v){
    s[v] = ++cnt;
    for(int nv : graph[v]){
        dfs(nv);
    }
    e[v]=cnt;
}
int create_segment(int n,int s,int e){
    n_segTree[n]=0;
    if(s==e){return segTree[n]=0;}
    return segTree[n]=create_segment(n*2,s,(s+e)/2)+create_segment(n*2+1,(s+e)/2+1,e);
}
void update_lazy(vector<int>&segtree,vector<int>&lazytree,int n,int s,int e){
    if(lazytree[n]!=0){
        if(s!=e){
            lazytree[n*2]+=lazytree[n];
            lazytree[n*2+1]+=lazytree[n];
        }
        segtree[n]+=lazytree[n]*(e-s+1);
        lazytree[n]=0;
    }
}
int query(vector<int>&segtree,vector<int>&lazytree,int n,int s,int e,int left,int right){
    update_lazy(segtree,lazytree,n,s,e);
    if(left>e||right<s){return 0;}
    if(left<=s&&e<=right){return segtree[n];}
    return query(segtree,lazytree,n*2,s,(s+e)/2,left,right)+query(segtree,lazytree,n*2+1,(s+e)/2+1,e,left,right);
}
void update_range(vector<int>&segtree,vector<int>&lazytree,int n,int s,int e,int s1,int e1,int f){
    update_lazy(segtree,lazytree,n,s,e);
    if(s1<=s&&e<=e1){
        lazytree[n]+=f;
        update_lazy(segtree,lazytree,n,s,e);
        return;}
    if(e1<s||s1>e){return;}
    update_range(segtree,lazytree,n*2,s,(s+e)/2,s1,e1,f);
    update_range(segtree,lazytree,n*2+1,(s+e)/2+1,e,s1,e1,f);
    segtree[n]=segtree[n*2]+segtree[n*2+1];
}
int main(){
    ios::sync_with_stdio(0);
    cin.tie(0),cout.tie(0);
    cin >> n >> m;
    int q,c,d,p,ce=0,fr,se;
    for(int i = 1; i <= n; i++){
        cin >> p;
        if(p!=-1){graph[p].push_back(i);}
    }
    dfs(1);
    create_segment(1,1,n);
    for(int i=0;i<m;i++){
        cin >> q;
        if(q==1){
            cin >> c >> d;
            if(ce%2==0)update_range(segTree,lazy,1,1,n,s[c],e[c],d);
            else update_range(n_segTree,n_lazy,1,1,n,s[c],s[c],d);
        }else{
            if(q==2){
                cin >> c;
                fr=query(segTree,lazy,1,1,n,s[c],s[c]);
                se=query(n_segTree,n_lazy,1,1,n,s[c],e[c]);
                cout << fr+se << '\n';
            }else{ce++;}
        }
    }
}