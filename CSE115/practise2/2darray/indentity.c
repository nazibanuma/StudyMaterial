#include <stdio.h>

int main()
{
    int arr[3][3], identity = 1;
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
            if (i == j && arr[i][j] != 1 || i != j && arr[i][j] != 0)
            {
                identity = 0;
            }
            else
            {
                identity = 1;
            }
        }
    }
    if (identity == 1)
    {
        printf("identity matrix YES");
    }
    else
    {
        printf("not identity matrix");
    }

    return 0;
}