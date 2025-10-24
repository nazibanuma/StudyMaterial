#include <stdio.h>

int main()
{
    int arr1[2][2], arr2[2][2], arr3[2][2];

    printf("matrix elements for 1st matrix: \n");

    for (int i = 0; i < 2; i++)
    {
        for (int j = 0; j < 2; j++)
        {
            printf("[%d][%d] = ", i, j);
            scanf("%d", &arr1[i][j]);
        }
    }

    printf("\n");

    printf("matrix elements for 2nd matrix: \n");

    for (int i = 0; i < 2; i++)
    {
        for (int j = 0; j < 2; j++)
        {
            printf("[%d][%d] = ", i, j);
            scanf("%d", &arr2[i][j]);
        }
    }

    printf("\n");

    for (int i = 0; i < 2; i++)
    {
        for (int j = 0; j < 2; j++)
        {
            arr3[i][j] = arr1[i][j] + arr2[i][j];
            printf("%d ", arr3[i][j]);
        }
        printf("\n");
    }

    return 0;
}