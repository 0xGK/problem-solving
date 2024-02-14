#include<iostream>
#include<algorithm>
#include<string>
#include<vector>
#include<stack>
using namespace std;
#define ull unsigned long long
#define ll long long

int run() {
    // make program
	vector<string> cmdList;
	vector<int> numList;

	string str; cin >> str;
	
	while (1) {
        if (str == "QUIT") return 0;

		cmdList.push_back(str);
		if (str == "END") break;
		else if (str == "NUM") {
			int k; cin >> k;
			numList.push_back(k);
		}
		cin >> str;
	}

    //execute program
	int n; cin >> n;
	for (int i = 0; i < n; i++) {
		stack<int> stack;
		int k; cin >> k;
		stack.push(k);

		int n_cnt = 0;
		for (int cmd=0; cmd<cmdList.size(); cmd++) {
            str=cmdList[cmd];
			if (str == "NUM") {
				stack.push(numList[n_cnt++]);
			}
			else if (str == "POP") {
				if (stack.empty()) {
					cout << "ERROR\n";
					break;
				}
				stack.pop();
			}
			else if (str == "INV") {
				if (stack.empty()) {
					cout << "ERROR\n";
					break;
				}
				int tmp = stack.top();
				stack.pop();
				stack.push(-tmp);
			}
			else if (str == "DUP") {
				if (stack.empty()) {
					cout << "ERROR\n";
					break;
				}
				stack.push(stack.top());
			}
			else if (str == "SWP") {
				if (stack.size() < 2) {
					cout << "ERROR\n";
					break;
				}
				int tmp1 = stack.top(); stack.pop();
				int tmp2 = stack.top(); stack.pop();
				stack.push(tmp1);
				stack.push(tmp2);
			}
			else if (str == "ADD") {
				if (stack.size() < 2) {
					cout << "ERROR\n";
					break;
				}
				int tmp1 = stack.top(); stack.pop();
				int tmp2 = stack.top(); stack.pop();
				int ret = tmp1 + tmp2;
				if (ret > 1000000000) {
					cout << "ERROR\n";
					break;
				}else
					stack.push(ret);
			}
			else if (str == "SUB") {
				if (stack.size() < 2) {
					cout << "ERROR\n";
					break;
				}
				int tmp1 = stack.top(); stack.pop();
				int tmp2 = stack.top(); stack.pop();
				int ret = tmp2 - tmp1;
				if (abs(ret) > 1000000000) {
					cout << "ERROR\n";
					break;
				}
				else
					stack.push(ret);
			}
			else if (str == "MUL") {
				if (stack.size() < 2) {
					cout << "ERROR\n";
					break;
				}
				ll tmp1 = stack.top(); stack.pop();
				ll tmp2 = stack.top(); stack.pop();
				ll ret = tmp1 * tmp2;
				if (ret > 1000000000 || ret < -1000000000) {
					cout << "ERROR\n";
					break;
				}
				else
					stack.push((int)ret);
			}
			else if (str == "DIV") {
				if (stack.size() < 2) {
					cout << "ERROR\n";
					break;
				}
				int tmp1 = stack.top(); stack.pop();
				int tmp2 = stack.top(); stack.pop();
				int symbol = 1;
				if ((tmp1 < 0 && tmp2 >0) || (tmp1>0 && tmp2 < 0)) symbol = -1;

				if (tmp1 == 0) {
					cout << "ERROR\n";
					break;
				}
				int ret = abs(tmp2) / abs(tmp1);
				stack.push(symbol*ret);
			}
			else if (str == "MOD") {
				if (stack.size() < 2) {
					cout << "ERROR\n";
					break;
				}
				int tmp1 = stack.top(); stack.pop();
				int tmp2 = stack.top(); stack.pop();
				int symbol = 1;
				if (tmp2 < 0) symbol = -1;

				if (tmp1 == 0) {
					cout << "ERROR\n";
					break;
				}
				int ret = abs(tmp2) % abs(tmp1);
				stack.push(symbol*ret);
			}
			else if (str == "END") {
				if (stack.size() == 1) cout << stack.top() << '\n'; 
				else cout << "ERROR\n";
			}
		}
		while (!stack.empty()) stack.pop();
	}
	cout << '\n';

	cmdList.clear();
	numList.clear();
	return 1;
}
int main() {
	cin.tie(0); ios::sync_with_stdio(0);
	while(run());
	return 0;
}