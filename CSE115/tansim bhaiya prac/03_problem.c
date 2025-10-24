#include<stdio.h>

int main()
    {
        int i, j,num=1;
    
        for (i = 1; i <= 4; i++)
        {
            for (j = 1; j <= i; j++)
         {
            //i= i+1
                printf("%d ", num);
                num++;
            }
            printf("\n");
        }
    
    return 0;
}