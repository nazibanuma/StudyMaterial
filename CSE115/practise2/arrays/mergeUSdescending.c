#include <stdio.h>

int main()
{
    int index = 6;
    int a1[6] = {1, 4, 3, 6, 2, 5}, a2[6] = {2, 5, 4, 3, 3, 1};
    int i, j, k, temp;

    for (i = 0; i < index - 1; i++)
    {
        int largest = i;
        for (j = i + 1; j < index; j++)
        {
            if (a1[j] > a1[largest])
            {
                largest = j;
            }
        }
        temp = a1[largest];
        a1[largest] = a1[i];
        a1[i] = temp;
    }

    /*for (i = 0; i < index; i++)
    {
        printf("%d\t", a1[i]);
    }*/

    for (i = 0; i < index - 1; i++)
    {
        int largest = i;
        for (j = i + 1; j < index; j++)
        {
            if (a2[j] > a2[largest])
            {
                largest = j;
            }
        }
        temp = a2[largest];
        a2[largest] = a2[i];
        a2[i] = temp;
    }

    int a3[2 * index];
    i = 0, j = 0, k = 0;

    while (i < 6 && j < 6)
    {
        if (a1[i] > a2[j])
        {
            a3[k++] = a1[i++];
        }
        else
        {
            a3[k] = a2[j];
            k++;
            j++;
        }
    }

    while (j < 6)
    {
        a3[k] = a2[j];
        k++;
        j++;
    }

    while (i < 6)
    {
        a3[k] = a1[i];
        k++;
        i++;
    }

    for (k = 0; k < 12; k++)
    {
        printf("%d\t", a3[k]);
    }

    return 0;
}