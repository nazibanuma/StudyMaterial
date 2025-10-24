// Write a C program to read a number from user and check whether given number is a palindrome or not.
#include <stdio.h>

int main()
{

    int num, digit, finalnum = 0;

    printf("Enter a positive integer: \n");
    scanf("%d", &num); // found the number

    while (num != 0)
    {

        digit = num % 10; // got the last digit
        printf("%d", digit);
        finalnum = finalnum * 10 + digit; // problem understanding this line but i understand the concept
        num /= 10;                        // number is smaller
    }
    if (num == finalnum)
    {
        printf("The number is palindrome\n");
    }
    else
    {
        printf("its not\n");
    }

    return 0;
}
