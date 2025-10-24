#include <stdio.h>

int main()
{
    char arr[100];
    int count1 = 0, count2 = 0;

    printf("enter the string: ");
    gets(arr);

    for (int i = 0; arr[i] != '\0'; i++)
    {
        if ((arr[i] <= 'Z' && arr[i] >= 'A') || (arr[i] <= 'z' && arr[i] >= 'a'))
        {
            if (arr[i] == 'E' || arr[i] == 'A' || arr[i] == 'O' || arr[i] == 'U' || arr[i] == 'I' ||
                arr[i] == 'e' || arr[i] == 'a' || arr[i] == 'o' || arr[i] == 'u' || arr[i] == 'i')
            {
                count1++;
            }
            else
            {
                count2++;
            }
        }
    }
    printf("The total number of vowel in the string is : %d\n", count1);
    printf("The total number of consonant in the string is: %d\n", count2);

    return 0;
}
