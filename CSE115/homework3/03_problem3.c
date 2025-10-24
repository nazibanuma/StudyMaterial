// write a C program to enter any number from user and find the reverse of a given number using loop
#include <stdio.h>

int main()
{

    int num, digit;

    printf("Enter a positive integer: \n");
    scanf("%d", &num); // found the number

    while (num != 0)
    {

        digit = num % 10; // got the last digit
        printf("%d", digit);

        num /= 10; // number is smaller
    }

    return 0;
}