#include <stdio.h>
#define SIZE 3

int main()
{
    int arr[3][3];
    printf("matrix elements: \n");

    for (int i = 0; i < SIZE; i++)
    {
        for (int j = 0; j < SIZE; j++)
        {
            printf("[%d][%d] = ", i, j);
            scanf("%d", &arr[i][j]);
        }
    }

    printf("\n");
    for (int i = 0; i < SIZE; i++)
    {
        for (int j = 0; j < SIZE; j++)
        {
            if ((i + j) < SIZE - 1)
                printf("%d ", arr[i][j]);
            else
            {
                printf(" ");
            }
        }
        printf("\n");
    }

    return 0;
}