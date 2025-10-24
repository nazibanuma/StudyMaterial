#include <stdio.h>

int main()
{
    int arr[3][3];
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
        for (int j = i + 1; j < 3; j++)
        {
            // if (i != j)
            //{
            int temp = arr[i][j];
            arr[i][j] = arr[j][i];
            arr[j][i] = temp;
            //}
        }
        printf("\n");
    }

    printf("\n");
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            printf("%d ", arr[i][j]);
        }
        printf("\n");
    }

    return 0;
}