#include <stdio.h>

int main()
{
    char arr1[100], arr2[100], count1 = 0, count2 = 0;

    printf("enter your name: ");
    gets(arr1);
    printf("enter your name: ");
    gets(arr2);

    for (int i = 0; arr1[i] != NULL; i++)
    {
        count1++;
    }

    for (int i = 0; arr2[i] != NULL; i++)
    {
        count2++;
    }

    if (count1 != count2)
    {
        printf("Strings are not equal");
    }
    else
    {
        int equal = 1;
        for (int i = 0; arr1[i] != NULL; i++)
        {
            if (arr1[i] != arr2[i])
            {
                equal = 0;
                break;
            }
        }
        if (equal == 1)
        {
            printf("Strings are equal");
        }
        else{
            printf("Strings are not equal");
        }
    }
    return 0;
}