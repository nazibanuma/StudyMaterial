// Write a program in C to print Floyd's Triangle.

#include <stdio.h>

int main()
{
    int i, j, rows = 6, start;

    for (i = 1; i <= rows; i++)
    {
        if (i % 2 == 0)
        {
            start = 0;
        }
        else
        {
            start = 1;
        }


        for (j = 1; j <= i; j++)
        {

            if (j % 2 == 0)

                printf("%d", !start);
            else
            {
                printf("%d",start);
            }
        }

        printf("\n");
    }
    return 0;
}