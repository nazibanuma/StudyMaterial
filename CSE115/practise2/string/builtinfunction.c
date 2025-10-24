#include<stdio.h>
#include<string.h>

int main(){
        // char *str ="numa ali";
        // int x = strlen(str);
        // printf("%d",x);

        // char s1[12] ="numa ali";
        // char s2[12];
        // strcpy(s2,s1);
        // s2[0]="y";
        // printf("%s",s2);


        char s1[12] ="numa ";
        char s2[12]="ali";;
        strcat(s1,s2); //merge
       
        printf("%s",s1);

    return 0;
}