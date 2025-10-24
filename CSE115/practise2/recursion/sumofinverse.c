#include <stdio.h>
float Sum(int);

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
        return (1.0 / a) * Sum(a - 1);
    }
}