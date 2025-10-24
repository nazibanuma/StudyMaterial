// C program to check Leap Year (a year is a leap year if – (i) it is divisible by 4 but not divisible by 100 OR (ii)
//it is divisible by 400) ~USINNG SWITCH
#include<stdio.h>

int main(){
        int year;

        printf("Enter the year:");
        scanf("%d", &year);

        int CaseValue;

        if( year%4 ==0)
        CaseValue = 1;
        else if( year%100 ==0)
        CaseValue = 2;
        else if( year%400 ==0)
        CaseValue = 1;
        else
        CaseValue = 2;

        switch(CaseValue){
            case 1:
            printf("The year is leap year");
            break;
            case 2:
            printf("The year is NOT leap year");
            break;

        }
    return 0;
}