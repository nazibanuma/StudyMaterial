//Write a C program to check if an input integer is a multiple of either 2 or 5 but not a multiple of both.
//E.g. of valid numbers are 4, 6, 8, 12, 14, 15, 16, 25, etc. E.g. of invalid numbers are 1, 3, 7, 9, 10, 20, etc
#include<stdio.h>

int main(){
        int num;

        printf("Enter the num: ");
        scanf("%d", &num);

        int casevalue;

        if(num%2 ==0)
        casevalue = 1;
        else if (num%5==0)
        casevalue = 1;
        else
        casevalue = 2;

        switch(casevalue){
            case 1:
            printf("The number is a multiple of either 2 or 5");
            break;
            case 2:
            printf("The number is not a multiple of both");
            break;
        }

    return 0;
}