#include <stdio.h>

int main()
{
    // print n natural numbers using for loop

    int n, i;
    scanf("%d", &n);

    for (i = 0; i <= n; i++)
    {
        printf("the value of i is %d\n", i);
    }
    return 0;
}