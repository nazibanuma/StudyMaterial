#include <stdio.h>

void countVowel(char s[])
{
    int count = 0, i;
    for (i = 0; s[i] != NULL; i++)
    {
        if (s[i] == 'A' || s[i] == 'E' || s[i] == 'I' || s[i] == 'O' || s[i] == 'U' || 
            s[i] == 'a' || s[i] == 'e' || s[i] == 'i' || s[i] == 'o' || s[i] == 'u')
        {
            count++;
        }
    }
    printf("Total no. of vowel is %d", count);
}

int main()
{
    char str[100];
    printf("Enter a string :: ");
    gets(str);

    countVowel(str);

    return 0;
}

