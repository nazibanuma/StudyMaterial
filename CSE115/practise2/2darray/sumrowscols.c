#include <stdio.h>

int main()
{
    int arr[3][3], sum = 0;
    printf("matrix elements: \n");

    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            printf("[%d][%d] = ", i, j);
            scanf("%d", &arr[i][j]);
        }
    }

    printf("\n");
    for (int i = 0; i < 3; i++)
    {
        sum = 0;
        for (int j = 0; j < 3; j++)
        {
            printf("%d  ", arr[i][j]);
            sum = sum + arr[i][j];
        }
        printf(" = %d ", sum);
        printf("\n");
    }
    for (int i = 0; i < 3; i++)
    {

        printf("|| ");
    }
    printf("\n");

    for (int i = 0; i < 3; i++)
    {
        int sum1 = 0;
        for (int j = 0; j < 3; j++)
        {
            sum1 = sum1 + arr[j][i];
        }
        printf("%d ", sum1);
    }

    return 0;
}
