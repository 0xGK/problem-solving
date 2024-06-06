#include<stdio.h>
int main() {
	int cnt, level, len, i, sum, n;
	level = len = i = sum = 0;
	cnt = 1;
	char arr[80];
	scanf("%d", &n);
	while (level < n) {
		scanf("%s", arr);
		while (arr[i] != 0) {
			if (arr[i] == 'O') sum += cnt++;
			else cnt = 1;
			i++;
		}
		printf("%d\n", sum);
		sum = i=0;
		cnt = 1;
		level++;
	}







	return 0;
}