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
        for (int j = 0; j < 3; j++)
        {
            if (j >i)
            {
                printf("%d ", arr[i][j]);
            }
            else
            {
                printf("  ");
            }
        }

        printf("\n");
    }

    return 0;
}