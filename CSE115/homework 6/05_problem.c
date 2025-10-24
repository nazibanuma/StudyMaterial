#include <stdio.h>

int main()
{
    int i, j, row, space;

    printf("enter the number of row: ");
    scanf("%d", &row);

    
    for (i =0; i < row; i++)
    {
        //space=row-1;
        for(j=0;j<=i-1;j++){
            printf(" ");
        }
        //space--;
        for (j = row-i; j >= 1; j--)
        {
            printf("* ");
        }
        
        printf("\n");
    }

    return 0;

}