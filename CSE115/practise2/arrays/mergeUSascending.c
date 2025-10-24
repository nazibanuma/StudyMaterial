#include <stdio.h>

int main()
{
    int index = 5, temp;
    int a1[5] = {21, 9, 11, 13, 37};
    int a2[6] = {20, 10,2 , 11, 14, 35};
    int a3[5 + 6];

    for (int i = 0; i < index - 1; i++)
    {
        int smallest = i;
        for (int j = i + 1; j < index; j++)
        {
            if (a1[smallest] > a1[j])
            {
                smallest = j;
            }
        }
        temp = a1[smallest];
        a1[smallest] = a1[i];
        a1[i] = temp;
    }
    for (int i = 0; i < index - 1; i++)
    {
        int smallest = i;
        for (int j = i + 1; j < index; j++)
        {
            if (a2[smallest] > a2[j])
            {
                smallest = j;
            }
        }
        temp = a2[smallest];
        a2[smallest] = a2[i];
        a2[i] = temp;
    }

    int i = 0, j = 0, k = 0;
    while (i < 5 && j < 6)
    {
        if (a1[i] < a2[j])
        {
            a3[k] = a1[i];
            k++;
            i++;
        }
        else
        {
            a3[k] = a2[j];
            k++;
            j++;
        }
    }

    while (i < 5)
    {
        a3[k++] = a1[i++];
    }

    while (j < 6)
    {
        a3[k++] = a2[j++];
    }

    for (k = 0; k < (5 + 6); k++)
    {
        printf("%d\t", a3[k]);
    }
    return 0;
}