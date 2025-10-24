/*Write a C program to read any integer from user and compute the reverse of given number. Also output
whether the reverse number is prime or not*/

#include <stdio.h>

int main()
{

    int num, digit, finalnum = 0;

    printf("Enter any integer: \n");
    scanf("%d", &num); // found the number

    while (num != 0)
    {
        digit = num % 10; // got the last digit
        // printf("%d", digit);
        finalnum = finalnum * 10 + digit;
        num /= 10; // number is smaller
    }

    printf("%d\n", finalnum);

    if (finalnum % (finalnum / 2) == 0 || finalnum == 0)
    {
        printf("The integer is not prime");
    }
    else
    {
        printf("The number is prime");
    }

    return 0;
}