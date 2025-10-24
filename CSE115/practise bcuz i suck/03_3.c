/*write a C program to check whether an input alphabet is a vowel or a consonant using
switch case (assume that the input is an English letter)*/

#include <stdio.h>

int main()
{

    char ch;

    printf("Enter a character: ");
    scanf("%c", &ch);

    switch (ch)
    {

    case 'a': case 'i': case 'u': case 'e': case 'o': case 'I': case 'A': case 'E': case 'U': case 'O':
        printf("the input is vowel", &ch);
        break;

    default:
        printf("the input is CONSONANT", &ch);
        break;
    }
    return 0;
}
