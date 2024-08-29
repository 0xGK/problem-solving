#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <vector>
#include <algorithm>

#define MAXSIZE 1000001

using namespace std;
typedef pair<int, int> p;
typedef long long ll;

int n, m, k;
ll arr[MAXSIZE], segtree[MAXSIZE * 4], lazy[MAXSIZE * 4];

void create_segtree(int node, int start, int end) {
    if (start == end) {
        // Leaf node
        segtree[node] = arr[start];
        return;
    }
    int mid = (start + end) / 2;
    create_segtree(node * 2, start, mid);
    create_segtree(node * 2 + 1, mid + 1, end);
    segtree[node] = segtree[node * 2] + segtree[node * 2 + 1];
}

void update_lazy(int node, int start, int end) {
    // Apply lazy value to the current node
    if (lazy[node] != 0) {
        segtree[node] += ((ll)end - start + 1) * lazy[node];
        if (start != end) {
            // Propagate lazy value to child nodes
            lazy[node * 2] += lazy[node];
            lazy[node * 2 + 1] += lazy[node];
        }
        lazy[node] = 0; // Reset lazy value
    }
}

void update_range(int node, int start, int end, int l, int r, ll val) {
    // Range update function
    update_lazy(node, start, end); // Apply lazy value to the current node

    if (l > end || r < start) return; // Out of range

    // Fully within range
    if (l <= start && end <= r) {
        segtree[node] += ((ll)end - start + 1) * val;
        if (start != end) {
            // Propagate lazy value to child nodes
            lazy[node * 2] += val;
            lazy[node * 2 + 1] += val;
        }
        return;
    }

    // Partially within range
    int mid = (start + end) / 2;
    update_range(node * 2, start, mid, l, r, val);
    update_range(node * 2 + 1, mid + 1, end, l, r, val);
    segtree[node] = segtree[node * 2] + segtree[node * 2 + 1];
}

ll query(int node, int start, int end, int l, int r) {
    // Range query function
    update_lazy(node, start, end); // Apply lazy value to the current node

    if (l > end || r < start) return 0; // Out of range

    // Fully within range
    if (l <= start && end <= r) return segtree[node];

    // Partially within range
    int mid = (start + end) / 2;
    return query(node * 2, start, mid, l, r) + query(node * 2 + 1, mid + 1, end, l, r);
}

void init() {
    scanf("%d %d %d", &n, &m, &k);

    for (int i = 1; i <= n; i++) scanf("%lld", &arr[i]);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    init();
    create_segtree(1, 1, n);

    int t, f, s;
    ll v;
    int udt = 0, qry = 0;
    while(1){
        if (udt == m && qry == k) break;
        scanf("%d %d %d", &t, &f, &s);
        if (t == 1) {
            scanf("%lld", &v);
            update_range(1, 1, n, f, s, v);
            udt++;
        }
        else {
            printf("%lld\n", query(1, 1, n, f, s));
            qry++;
        }
    }

    return 0;
}
