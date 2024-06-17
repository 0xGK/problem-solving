#include <iostream>
#include <string>
using namespace std;

int main(){
    string str;
    cin >> str;
    int m, s;
    m = stoi(str);
    // cout<<m<<endl;
    s = stoi(&str[3]);
    // cout<<s<<endl;

    int cnt=0;
    cnt+= m/10;
    cnt+= m%10;
    if(s>=30){
        s-=30;
    }
    cnt+=s/10;
    cnt++;

    cout<<cnt<<endl;

    return 0;
}