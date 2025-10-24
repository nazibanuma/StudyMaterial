#include <stdio.h>

struct BirthCert
{
    int day, month, year;
    char name[100], fatherName[100], motherName[100];
};

int main()
{
    struct BirthCert p1, p2;

    printf("Enter first Person info: \n");
    printf("Name: \n");
    scanf("%s", p1.name);
    printf("Date of birth: \n");
    scanf("%d/%d/%d", &p1.day, &p1.month, &p1.year);

    printf("Enter second Person info: \n");
    printf("Name: \n");
    scanf("%s", p2.name);
    printf("Date of birth: \n");
    scanf("%d/%d/%d", &p2.day, &p2.month, &p2.year);

    // if (p1.year != p2.year)
    // {
    //     return p1.year < p2.year;
    // }
    // else if (p1.month != p2.month)
    // {
    //     return p1.month < p2.month;
    // }
    // else
    // {
    //    return p1.day < p2.day;
    // }

    if (p1.year < p2.year ||
        (p1.year == p2.year && p1.month < p2.month) ||
        (p1.year == p2.year && p1.month == p2.month && p1.day < p2.day))
    {
        printf("%s is older.\n", p1.name);
    }
    else if (p1.year == p2.year && p1.month == p2.month && p1.day == p2.day)
    {
        printf("%s and %s are of the same age.\n", p1.name, p2.name);
    }
    else
    {
        printf("%s is older.\n", p2.name);
    }

    return 0;
}