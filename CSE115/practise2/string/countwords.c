#include <stdio.h>

int main()
{
    char arr[100], count = 0;

    printf("enter a sentence: ");
    gets(arr);

    for (int i = 0; arr[i] != NULL; i++)
    {
        if (arr[i] == ' ')
        {
            count++;
        }
    }

    printf("Total number of words in the string is : %d",count+1);

    return 0;
}