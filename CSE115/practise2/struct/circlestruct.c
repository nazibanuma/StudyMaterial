#include <stdio.h>
struct Circle
{
    double radius;
};

double Area(struct Circle c)
{
    return 3.1416 * c.radius * c.radius;
}

double Perimeter(struct Circle c)
{
    return 3.1416 * 2 * c.radius;
}

int main()
{
    struct Circle c1, c2;

    double pi = 3.1416;
    printf("input the 1st radius: \n");
    scanf("%lf", &c1.radius);
    printf("input the 2nd radius: ");
    scanf("%lf", &c2.radius);

    printf("for 1st circle: \n");
    printf("Area: %lf\n", Area(c1));
    printf("Perimeter: %lf\n", Perimeter(c1));

    printf("for 2nd circle: \n");
    printf("Area: %lf\n", Area(c2));
    printf("Perimeter: %lf\n", Perimeter(c2));

    return 0;
}