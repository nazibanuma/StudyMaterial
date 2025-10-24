#include <stdio.h>
#include <ctype.h>

int main() {
    char str[] = "Hello WORLD!";

    for (int i = 0; str[i] != '\0'; i++) {
        str[i] = tolower(str[i]);
    }

    printf("Lowercase string: %s\n", str);

    return 0;
}
#include <stdio.h>
#include <ctype.h>

int main() {
    char str[] = "Hello world!";

    for (int i = 0; str[i] != '\0'; i++) {
        str[i] = toupper(str[i]);
    }

    printf("Uppercase string: %s\n", str);

    return 0;
}
