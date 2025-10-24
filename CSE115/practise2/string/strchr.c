#include <stdio.h>
#include <string.h>

int main() {
    char str[] = "banana";
    char ch = 'b';

    char *ptr = strchr(str, ch);

    if (ptr != NULL)
        printf("Character '%c' found : %s\n", ch, ptr);
    else
        printf("Character not found.\n");

    return 0;
}
