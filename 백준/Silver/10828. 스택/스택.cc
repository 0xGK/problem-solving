#include<iostream>	
#include<string>
using namespace std;

void run() {
	int n; cin >> n;
	int stack[10001];
	int top = -1;

	while (n--) {
		string cmd; cin >> cmd;
		if (cmd == "push") {
			int k; cin >> k;
			stack[++top] = k;
		}
		else if (cmd == "empty") {
			if (top < 0) cout << 1 << endl;
			else cout << 0 << endl;
		}
		else if (cmd == "size") {
			cout << top+1<<endl;
		}
		else if (cmd == "top") {
			if (top < 0) cout << top << endl;
			else cout << stack[top] << endl;
		}
		else if (cmd == "pop") {
			if (top < 0) cout << top << endl;
			else cout << stack[top--] << endl;
		}
	}

}


int main() {
	run();

	return 0;
}
