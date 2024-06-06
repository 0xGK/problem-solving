#include<iostream>
#include<string>

using namespace std;

void run() {
	string str; cin >> str;
	// 마지막으로 열린 괄호 뭔지
	/*
		(: 40
		): 41
		[: 91
		]: 93
	*/

	int trace[30];
	int stack[30];
	int trace_top = -1, stack_top = -1;

	for (int i = 0; i < str.size(); i++) {
		stack[++stack_top] = str[i] - 100;
		if (str[i] == '(' || str[i] == '[')
			trace[++trace_top] = str[i] - 100;



		//do something
		if (trace[trace_top] == '(' - 100 && str[i] == ')') {
			int val=0;
			stack_top--;
			if (stack[stack_top] == '(' - 100) {
				stack[stack_top] = 2;
			}
			else if (stack[stack_top] >= 0) {
				while (stack[stack_top] != '(' - 100) {
					val += stack[stack_top--];
				}
				stack[stack_top] = 2*val;
			}
			trace_top--;
		}
		else if (trace[trace_top] == '['-100 && str[i] == ']') {
			int val = 0;
			stack_top--;
			if (stack[stack_top] == '[' - 100) {
				stack[stack_top] = 3;
			}
			else if (stack[stack_top] >= 0) {
				while (stack[stack_top] != '[' - 100) {
					val += stack[stack_top--];
				}
				stack[stack_top] = 3*val;
			}
			trace_top--;
		}

	}
		int sum = 0;
		for (int i = 0; i < stack_top + 1; i++) {
			if (stack[i] < 0) {
				sum = 0; break;
			}
			sum += stack[i];
		}
		cout << sum;
		return;
}


int main() {
	cin.tie(0); ios::sync_with_stdio(0);
	run();
	return 0;
}