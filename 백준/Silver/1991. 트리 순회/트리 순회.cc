#include<iostream>
using namespace std;

typedef struct Node {
	char name;
	Node* left;
	Node* right;
}Node;

/*
	A: 65
*/

void preorder(Node* root) {
	if (root == nullptr)return;
	cout << (char)(root->name);
	preorder(root->left);
	preorder(root->right);
}
void inorder(Node* root) {
	if (root == nullptr)return;
	inorder(root->left);
	cout << (char)(root->name);
	inorder(root->right);
}
void postorder(Node* root) {
	if (root == nullptr)return;
	postorder(root->left);
	postorder(root->right);
	cout << (char)(root->name);
}
void run() {
	Node nodePool[26];
	int nodeCnt = 0;
	for (int i = 0; i < 26; i++) {
		nodePool[i].name = i + 65;
	}

	int n; cin >> n;
	for (int i = 0; i < n; i++) {
		char c, l, r;
		cin >> c >> l >> r;
		int c_idx = c - 65;
		int l_idx = l - 65;
		int r_idx = r - 65;
		if (l_idx >= 0 && l_idx <= 26)
			nodePool[c_idx].left = &nodePool[l_idx];
		else
			nodePool[c_idx].left = nullptr;
		if (r_idx >= 0 && r_idx <= 26)
			nodePool[c_idx].right = &nodePool[r_idx];
		else
			nodePool[c_idx].right = nullptr;
	}

	preorder(&nodePool[0]); cout << endl;
	inorder(&nodePool[0]); cout << endl;
	postorder(&nodePool[0]); cout << endl;

}



int main() {
	cin.tie(0); ios::sync_with_stdio(0);
	run();
	
	return 0;
}