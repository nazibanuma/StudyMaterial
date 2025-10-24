#include <stdio.h>

int main()
{
    int i, j;

    for (i = 1; i <= 4; i++)
    {
        for (j = 1; j<=4; j++)
        {
            if(j==4)
            printf("%d x %d = %d  ",j, i, j*i);
            else
            printf("%d x %d = %d , ",j, i, j*i);
        }
        printf("\n");
    }
    return 0;
}