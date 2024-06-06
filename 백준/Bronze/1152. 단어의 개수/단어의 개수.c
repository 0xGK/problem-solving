#include<stdio.h>
#include<string.h>
int main() {
	char str[1000002];
	int len=0, cnt = 0,check=0;
	gets(str);
	while (str[len] != 0) len++;

	for (int i = 1; i < len -1; i++) {
		if (str[i - 1] != 32 && str[i] == 32 && str[i + 1] != 32) {
			cnt++;
		}
	}
	for (int i = 0; i < len ; i++) {
		if ( str[i] == 32) {
			check++;
		}
	}
	if (check == len) printf("%d", 0);
	else printf("%d", cnt + 1);
	return 0;
}