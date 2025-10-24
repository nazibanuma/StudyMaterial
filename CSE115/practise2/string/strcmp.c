#include <stdio.h>
#include <string.h>

int main() {
    char str1[] = "hello";
    char str2[] = "hello";

    int result = strcmp(str1, str2);

    if (result == 0)
        printf("Strings are equal.\n");
    return 0;
}
#include <stdio.h>
#include <string.h>

int main() {
    char str1[] = "apple";
    char str2[] = "banana";

    int result = strcmp(str1, str2);

    if (result < 0)
        printf("First string is less than second.\n");
    return 0;
}
#include <stdio.h>
#include <string.h>

int main() {
    char str1[] = "zebra";
    char str2[] = "apple";

    int result = strcmp(str1, str2);

    if (result > 0)
        printf("First string is greater than second.\n");
    return 0;
}

