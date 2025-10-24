// Write a C program to compute the sum of digits of an input number and check if this sum is a prime or not.

#include <stdio.h>

int main()
{

    int num, sum = 0;

    printf("Enter any integer: \n");
    scanf("%d", &num); // found the number

    while (num != 0)
    {
        sum = sum + num % 10;
        num /= 10; // number is smaller
    }

    printf("sum of its digits is %d\n", sum);

    // proving now if its prime

    if (sum % (sum / 2) == 0 || sum == 0)
    {
        printf("The integer is not prime");
    }
    else
    {
        printf("The number is prime");
    }

    return 0;
}