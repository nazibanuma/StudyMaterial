#include <stdio.h>

int main()
{
    int arr1[50][50], brr1[50][50], crr1[50][50], i, j, k, r1, c1, r2, c2, sum = 0;

    printf("\n\nMultiplication of two Matrices :\n");
    printf("----------------------------------\n");

    printf("\nInput the rows and columns of the first matrix: ");
    scanf("%d %d", &r1, &c1);

    printf("\nInput the rows and columns of the second matrix: ");
    scanf("%d %d", &r2, &c2);

    if (c1 != r2)
    {
        printf("Multiplication of matrices is not possible.\n");
        printf("Column of the first matrix and row of the second matrix must be the same.\n");
    }
    else
    {
        printf("Input elements in the first matrix:\n");
        for (i = 0; i < r1; i++)
        {
            for (j = 0; j < c1; j++)
            {
                printf("element - [%d],[%d] : ", i, j);
                scanf("%d", &arr1[i][j]);
            }
        }

        printf("Input elements in the second matrix:\n");
        for (i = 0; i < r2; i++)
        {
            for (j = 0; j < c2; j++)
            {
                printf("element - [%d],[%d] : ", i, j);
                scanf("%d", &brr1[i][j]);
            }
        }

        printf("\nThe First matrix is:\n");
        for (i = 0; i < r1; i++)
        {
            printf("\n");
            for (j = 0; j < c1; j++)
                printf("%d\t", arr1[i][j]);
        }

        printf("\nThe Second matrix is:\n");
        for (i = 0; i < r2; i++)
        {
            printf("\n");
            for (j = 0; j < c2; j++)
                printf("%d\t", brr1[i][j]);
        }

        for (i = 0; i < r1; i++)
        {
            for (j = 0; j < c2; j++)
            {
                sum = 0;
                for (k = 0; k < c1; k++)
                {
                    sum = sum + arr1[i][k] * brr1[k][j];
                }
                crr1[i][j] = sum;
            }
        }

        printf("\nThe multiplication of two matrices is:\n");
        for (i = 0; i < r1; i++)
        {
            printf("\n");
            for (j = 0; j < c2; j++)
            {
                printf("%d\t", crr1[i][j]);
            }
        }
    }
    printf("\n\n");
    return 0;
}