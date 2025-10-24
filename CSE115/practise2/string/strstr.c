#include <stdio.h>
#include <string.h>

int main() {
    char str[] = "I love programming in C.";
    char sub[] = "programming";

    char *ptr = strstr(str, sub);

    if (ptr != NULL)
        printf("Substring found: %s\n", ptr);
    else
        printf("Substring not found.\n");

    return 0;
}
