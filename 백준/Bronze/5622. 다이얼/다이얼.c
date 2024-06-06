#include<stdio.h>
#include<string.h>
int main(){
    
    
    char str[20];
	int time = 0;
	scanf("%s", str);
	int len = strlen(str);
	for (int i = 0; i < len; i++) {
		if(str[i]-59 <24)
			time += 1+(str[i] - 59) / 3;
		else if(str[i] - 59 < 31)
			time += 1 + (str[i] - 60) / 3;
		else
			time += 1 + (str[i] - 61) / 3;
	}
	printf("%d", time);
    
    return 0;
}