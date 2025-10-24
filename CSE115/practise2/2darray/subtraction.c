#include <stdio.h>

int main()
{
    int arr1[2][2], arr2[2][2], arr3[2][2];

    printf("elements for 1st matrix: ");
    for (int i = 0; i < 2; i++)
    {
        for (int j = 0; j < 2; j++)
        {
            scanf("%d", &arr1[i][j]);
        }
    }

    printf("elements for 2nd matrix: ");
    for (int i = 0; i < 2; i++)
    {
        for (int j = 0; j < 2; j++)
        {
            scanf("%d", &arr2[i][j]);
        }
    }

    printf("matrix after subtraction: \n");
    for (int i = 0; i < 2; i++)
    {
        for (int j = 0; j < 2; j++)
        {
            arr3[i][j] = arr1[i][j] - arr2[i][j];
            printf("%d  ", arr3[i][j]);
        }
        printf("\n");
    }

    return 0;
}