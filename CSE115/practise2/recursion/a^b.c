#include <stdio.h>
int power(int, int);

int main()
{
    int num, pow, value;

    printf("Enter a integer: ");
    scanf("%d", &num);

    printf("Enter an integer for power: ");
    scanf("%d", &pow);

    value = power(num, pow);
    printf("%d", value);
    return 0;
}

int power(int a, int b)
{
    if (b == 0)
    {
        return 1;
    }
    else
    {
        return a * power(a, b - 1);
    }
}