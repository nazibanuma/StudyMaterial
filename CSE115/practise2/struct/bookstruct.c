#include <stdio.h>

struct Book
{
    char title[50];
    char name[50];
    float price;
};

int main()
{
    struct Book book[10], max, min;

    for (int i = 0; i < 3; i++)
    {

        printf("For %d book:\n", i + 1);
        printf("Input title: \n");
        scanf("%s", book[i].title);
        printf("Input name: \n");
        scanf("%s", book[i].name);
        printf("Input price: \n");
        scanf("%f", &book[i].price);
    }

    printf("Displaying the books info: ");

    for (int i = 0; i < 3; i++)
    {
        printf("For %d book:\n", i + 1);
        printf("Input title: %s\n", book[i].title);

        printf("Input name: %s\n", book[i].name);

        printf("Input price: %f\n", book[i].price);

        printf("\n\n");
    }

    max.price = book[0].price;
    for (int i = 0; i < 3; i++)
    {
        if (book[i].price > max.price)
        {
            max.price = book[i].price;
        }
    }

    printf("Most expensive book: %f\n", max.price);

    min.price = book[0].price;
    for (int i = 0; i < 3; i++)
    {
        if (book[i].price < min.price)
        {
            min.price = book[i].price;
        }
    }

    printf("Most loest priced book: %f", min.price);

    return 0;
}