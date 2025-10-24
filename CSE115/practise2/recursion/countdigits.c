#include <stdio.h>

int Count(int);

int main()
{
    int num, count;

    printf("Enter a integer: ");
    scanf("%d", &num);

    count = Count(num);
    printf("%d", count);
    return 0;
}

int Count(int a)
{
    int count = 0;
    if (a == 0)
    {
        return 0;
    }
    else
    {

        count++;

        return count + Count(a / 10);
    }
}
