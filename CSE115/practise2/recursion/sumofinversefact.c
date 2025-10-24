#include <stdio.h>
float Sum(int);
int Factorial(int);

int main()
{
    int num;
    float sum;

    printf("Enter a integer: ");
    scanf("%d", &num);

    sum = Sum(num);
    printf("%f", sum);
    return 0;
}

float Sum(int a)
{
    if (a == 1)
    {
        return 1;
    }
    else
    {
        return (float)a / Factorial(a) + Sum(a - 1);
    }
}

int Factorial(int b)
{
    if (b == 0)
    {
        return 1;
    }
    else
    {
        return b * Factorial(b - 1);
    }
}