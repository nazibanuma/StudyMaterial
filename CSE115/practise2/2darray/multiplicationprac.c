#include <stdio.h>

int main()
{
    int arr1[100][100], arr2[100][100], arr3[100][100];
    int r1 = 2, r2 = 3, c1 = 3, c2 = 2, sum = 0;

    printf("1st matrix elements: \n");

    for (int i = 0; i < r1; i++)
    {
        for (int j = 0; j < c1; j++)
        {
            printf("[%d][%d] = ", i, j);
            scanf("%d", &arr1[i][j]);
        }
    }

    printf("\n");
    for (int i = 0; i < r1; i++)
    {
        for (int j = 0; j < c1; j++)
        {
            printf("%d ", arr1[i][j]);
        }
        printf("\n");
    }

    printf("2nd matrix elements: \n");

    for (int i = 0; i < r2; i++)
    {
        for (int j = 0; j < c2; j++)
        {
            printf("[%d][%d] = ", i, j);
            scanf("%d", &arr2[i][j]);
        }
    }

    printf("\n");
    for (int i = 0; i < r2; i++)
    {
        for (int j = 0; j < c2; j++)
        {
            printf("%d ", arr2[i][j]);
        }
        printf("\n");
    }

    for (int i = 0; i < r1; i++)
    {

        for (int j = 0; j < c2; j++)
        {
            sum = 0;
            for (int k = 0; k < c1; k++)
            {
                sum = sum + arr1[i][k] * arr2[k][j];
            }
            arr3[i][j]=sum;
        }
    }

    printf("\n");
    for (int i = 0; i < r1; i++)
    {
        for (int j = 0; j < c2; j++)
        {
            printf("%d ", arr3[i][j]);
        }
        printf("\n");
    }


    return 0;
}