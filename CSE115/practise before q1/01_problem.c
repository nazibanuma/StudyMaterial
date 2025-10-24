//1. C program to find maximum between three numbers:

#include<stdio.h>

int main(){
        int num1, num2, num3;

        printf("Enter the value of num1: ");
        scanf("%d", &num1);
        printf("Enter the value of num2: ");
        scanf("%d", &num2);
        printf("Enter the value of num3: ");
        scanf("%d", &num3);

        if(num1> num2 && num1> num3) {
            printf("num1 s the maximum number");
        }
        else if(num2> num3 && num2> num1) {
            printf("num2 s the maximum number");
        }
        else{
            printf("num3 is the maximum number");
        }
    return 0;
}