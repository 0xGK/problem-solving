#include<stdio.h>
#include<string.h>
int main(){
	char str[104];
	int cnt = 0;
	int check = 0;
	scanf("%s", str);
	int len = strlen(str);
	for (int i = 0; i < len; i++) {
		if (check > 0) check--;
		else if (str[i] == 'c' && str[i + 1] == '=') { cnt++; check++;  }
		else if (str[i] == 'c' && str[i + 1] == '-') { cnt++; check++;  }
		else if (str[i] == 'd' && str[i + 1] == 'z' && str[i + 2] == '=') { cnt++;  check++; check++;  }
		else if (str[i] == 'd' && str[i + 1] == '-') { cnt++; check++;  }
		else if (str[i] == 'l' && str[i + 1] == 'j') { cnt++; check++;  }
		else if (str[i] == 'n' && str[i + 1] == 'j') { cnt++; check++;  }
		else if (str[i] == 's' && str[i + 1] == '=') { cnt++; check++;  }
		else if (str[i] == 'z' && str[i + 1] == '=') { cnt++; check++;  }
		else { cnt++; }
	}
	printf("%d", cnt);
    
    return 0;
}