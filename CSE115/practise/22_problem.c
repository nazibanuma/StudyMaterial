#include <stdio.h>
// Pyramid pattern using the alphabet.

int main()
{
    int i, j, rows = 5;
    char c;

    for (i = 1; i <= rows; i++)
    {
        for (j = rows - i; j <= 1; j--){
        
            printf(" ");    
        }
        for(j=1;j<=i;j++){
            if(j==1 || j==i)
            printf("%c",65)
        }
    }

    return 0;
}