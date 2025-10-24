#include <stdio.h>

int main()
{
    // Write a C program to read an integer from user and output its last and first digit.

    int i, num;

    printf("Enter an integer: ");
    scanf("%d", &num);

    for (i = num; i != 0; i++)
    {
        if (i = num % 10)
        {
            printf("%d\n", i);
        }
        i = num / 10;
    }

    printf("%d", i);
    return 0;
}
