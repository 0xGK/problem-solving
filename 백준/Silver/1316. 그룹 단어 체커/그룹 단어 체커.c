#include<stdio.h>
#include<string.h>
int main(){
    	int n, len = 0, check, cnt = 0, total = 0;
	char str[100][103];
	int aph[100][26] = { 0 };
	scanf("%d", &n);


	for (int i = 0; i < n; i++) {
		scanf("%s", str[i]);
		len = strlen(str[i]);
		for (int j = 0; j < len; j++) {
			if (aph[i][str[i][j] - 97] == 0) { 
				aph[i][str[i][j] - 97]++; 
				check = str[i][j] - 97;
			}
			else if (aph[i][str[i][j] - 97] > 0 && str[i][j] - 97 != check)
			{
				cnt++;
			}
		}
		if (cnt == 0) total++;
		else cnt = 0;
	}
	printf("%d", total);
    
    
    return 0;
}