#include <stdio.h>
#include <math.h>

// Write a program in C to display the cube of the number up to an integer

int main()
{

    int i, term, cube;

    printf("enter the desired term: \n");
    scanf("%d", &term);

    for (i = 1; i <= term; i++)
    {
        cube = pow(i,3);
        printf("enter the number %d and the cube of the number is %d: \n" ,i ,cube);
        
        
    }

    return 0;
}