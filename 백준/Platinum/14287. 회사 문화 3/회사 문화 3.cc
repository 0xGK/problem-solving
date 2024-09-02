#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef pair<ll, ll> pll;
#define MAX_N 100001
// const ll N=1000000;
ll n,m;
vector<ll> graph[MAX_N];
pll Qu[MAX_N]; 
ll cnf=0,p;
vector<ll>segTree(MAX_N*4);
vector<ll>tree1(MAX_N*4);

void dfs(ll v,ll p){
    Qu[v].first = ++cnf;
    for(ll nv : graph[v]){
        if (nv==p){continue;}
        dfs(nv,v);
    }
    Qu[v].second=cnf;
}
ll update_segment(ll n,ll s,ll e){
    if(s==e){return segTree[n]=0;}
    return segTree[n]=update_segment(n*2,s,(s+e)/2)+update_segment(n*2+1,(s+e)/2+1,e);
}
void update_lazy(ll n,ll s,ll e){
    if(tree1[n]!=0){
        if(s!=e){
            tree1[n*2]+=tree1[n];
            tree1[n*2+1]+=tree1[n];
        }
        segTree[n]+=tree1[n]*(e-s+1);
        tree1[n]=0;
    }
}
ll max1(ll n,ll s,ll e,ll left,ll right){
    update_lazy(n,s,e);
    if(left>e||right<s){return 0;}
    if(left<=s&&e<=right){return segTree[n];}
    return max1(n*2,s,(s+e)/2,left,right)+max1(n*2+1,(s+e)/2+1,e,left,right);
}
void change(ll n,ll s,ll e,ll s1,ll e1,ll f){
    update_lazy(n,s,e);
    if(s1<=s&&e<=e1){tree1[n]+=f;update_lazy(n,s,e);return;}
    if(e1<s||s1>e){return;}
    change(n*2,s,(s+e)/2,s1,e1,f);
    change(n*2+1,(s+e)/2+1,e,s1,e1,f);
    segTree[n]=segTree[n*2]+segTree[n*2+1];
}
int main(){
    ios::sync_with_stdio(0);
    cin.tie(0),cout.tie(0);
    cin >> n >> m;
    ll q,c,d;
    for(int i = 1; i <= n; i++){
        cin >> p;
        if(p!=-1){graph[p].push_back(i);}
    }
    dfs(1, 0);
    //for(int i=1;i<=n;i++){
    //    cout << Qu[i].first << ' ' << Qu[i].second <<'\n';
    //}
    update_segment(1,1,n);
    for(int i=0;i<m;i++){
        cin >> q;
        if(q==1){
            cin >> c >> d;
            change(1,1,n,Qu[c].first,Qu[c].first,d);
        }else{
            cin >> c;
            cout << max1(1,1,n,Qu[c].first,Qu[c].second) << '\n';
        }
    }
}