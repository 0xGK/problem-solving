#include<stdio.h>
/*
int main() {
	int len = 0;;
	int T, R;
	char str[1000][21];
	scanf("%d", &T);
	for (int i = 0; i < T; i++) {
		scanf("%d", &R);
		scanf("%s", str[i]);

		while (str[i][len] != 0) len++;

		for (int j = 0; j < len; j++) {
			for (int k = 0; k < R; k++) printf("%c", str[i][j]);
		}
		printf("\n");
		len = 0;
	}
	return 0;
}
*/
int main() {
	char str[1000000];
	int count[200] = { 0 };
	int idx,max,len,cnt;
	len=idx = 0;
	scanf("%s", str);
	
	//len구하기
	while (str[len] != 0) len++;

	for (int i = 0; i < len; i++) {
		for (int j = 65; j < 91; j++) {
			if (str[i] == j || str[i] == j + 32) count[j]++;
		}

	}
	max = 0;
	cnt = 0;

	for (int j = 65; j < 91; j++) {
		if (max < count[j]) {
			idx = j;
			max = count[j];
			cnt=1;
		}
		else if (max == count[j]) cnt++;
	}
	
	if (cnt > 1|| cnt==0) printf("?");
	else
		printf("%c", idx);





	return 0;
}