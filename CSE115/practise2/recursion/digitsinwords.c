#include <stdio.h>
void Digit(int);
void Print(int a);

int main()
{
    int num, digit;

    printf("Enter a integer: ");
    scanf("%d", &num);

    Digit(num);
    return 0;
}

void Digit(int a)
{
    if (a == 0)
    {
        return 0;
    }

    Digit(a / 10);
    Print(a % 10);
}

void Print(int a)
{
    if (a == 0)
    {
        printf("Zero ");
    }
    else if (a == 1)
    {
        printf("One ");
    }
    else if (a == 2)
    {
        printf("Two ");
    }
    else if (a == 3)
    {
        printf("Three ");
    }
    else if (a == 4)
    {
        printf("Four ");
    }
    else if (a == 5)
    {
        printf("Five ");
    }
    else if (a == 6)
    {
        printf("Six ");
    }
    else if (a == 7)
    {
        printf("Seven ");
    }
    else if (a == 8)
    {
        printf("Eight ");
    }
    else
    {
        printf("Nine ");
    }
}