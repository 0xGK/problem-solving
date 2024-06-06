#include<stdio.h>
int main() {
	char str[100];
	int cnt = 0;
	scanf("%s", str);
	int len = 0;
	while (str[len] != 0) len++;
	

	
	for(int i = 97; i < 123; i++) {
		for (int j = 0; j < len; j++) {
			if (str[j] == i) {
				printf("%d ", j);
				cnt++;
				break;
			}
		}
		if (cnt == 1) cnt = 0;
		else printf("-1 ");
	}

	return 0;
}