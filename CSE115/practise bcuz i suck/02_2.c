// C program to check whether an input is an odd number or an even number using switch-case

#include <stdio.h>

int main()
{
    int num;

    printf("Enter any integer: \n");
    scanf("%d", &num);

    switch (num % 3){
    case 0:
        printf("the number is even\n");
    break;
case 1:
    printf("the number is odd\n");
    break;
}

    return 0;
}