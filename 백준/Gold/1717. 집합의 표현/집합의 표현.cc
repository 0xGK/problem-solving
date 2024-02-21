#include<iostream>
#include<algorithm>
using namespace std;
const int MAX_N = 1000001;
int parent[MAX_N];

int getParent(int a) {
	if (parent[a] == a)
		return a;
	return parent[a] = getParent(parent[a]);
}

bool find(int a, int b) {
	int x = getParent(a);
	int y = getParent(b);
	return x == y;
}

void _union(int a, int b) {
	int x = getParent(a);
	int y = getParent(b);

	if (x < y) {
		parent[y] = parent[x];
	}
	else {
		parent[x] = parent[y];
	}

	/*  최적화
		if(x == y) return ;

		if(prank[x]< prank[y]){
			parent[x] = y;
		}else{
			parent[y] = x;
		}
		if(prank[x] == prank[y]){
			prank[x]++;
		}
	*/

}

int main() {
	cin.tie(0); ios::sync_with_stdio(0);
	int n, m; cin >> n >> m;
	for (int i = 0; i <= n; i++) {
		parent[i] = i;
	}	
	for (int i = 0; i < m; i++) {
		int x, a, b; cin >> x >> a >> b;
		if (x) {
			if (find(a, b)) cout << "YES\n";
			else cout << "NO\n";
		}
		else {
			_union(a, b);
		}

	}

}