#include<stdio.h>

int main(){
        char arr[100];

        printf("enter your name: ");
        gets(arr);

        for(int i=0;arr[i]!=NULL;i++){
            printf("%c  ",arr[i]);

        }

    return 0;
}