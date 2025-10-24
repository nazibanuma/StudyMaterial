#include <stdio.h>

int main()
{     

    int i,
        j, row = 7, space;

    space = row - 1;
    for (i = 1; i <= row; i++)
    {
        for (j = space; j >= 1; j--)
        {
            printf(" ");
        }
        space--;
        for (j = 1; j <= i; j++)
        {

            if(j==1 || j==i || i==1|| i==row){
                printf("* ");
            }
            else{
                printf("  ");
            }
        }
        printf("\n");
    }
    return 0;
}