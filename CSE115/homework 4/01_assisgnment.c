// Write a C program to display a given number in words starting from its leftmost digit

#include <stdio.h>

int main()
{

    int num, digit, newnum = 0;

    printf("Enter a number: ");
    scanf("%d", &num);

    while (num != 0)
    {
        digit = num % 10;
        printf("%d\n",digit);
        newnum = newnum * 10 + digit;
        num /= 10;
    }


    switch (digit %10)
    {
    case 0:
        printf("zero");
        break;

    case 1:
        printf("one");
        break;
    case 2:
        printf("two");
        break;
    case 3:
        printf("three");
        break;
    case 4:
        printf("four");
        break;
    case 5:
        printf("five");
        break;
    case 6:
        printf("six");
        break;
    case 7:
        printf("seven");
        break;
    case 8:
        printf("eight");
        break;
    case 9:
        printf("nine");
        break;
    }

    return 0;
}