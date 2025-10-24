#include <stdio.h>
int Sum(int);

int main()
{
    int num, sum;

    printf("Enter a integer: ");
    scanf("%d", &num);

    sum = Sum(num);
    printf("%d", sum);
    return 0;
}

int Sum(int a)
{
    if (a == 1)
    {
        return 1;
    }
    else
    {
        return a * a + Sum(a - 1);
    }
}