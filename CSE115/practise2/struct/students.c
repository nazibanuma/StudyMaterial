#include <stdio.h>

struct Students
{
    char name[50];
    int age;
    float marks;
};

int main()
{
    struct Students p1, p2, average, sum;

    printf("For 1st student:\n");
    printf("Input name: \n");
    scanf("%s", p1.name);
    printf("Input age: \n");
    scanf("%d", &p1.age);
    printf("Input marks: \n");
    scanf("%f", &p1.marks);

    printf("For 2st student:\n");
    printf("Input name: \n");
    scanf("%s", p2.name);
    printf("Input age: \n");
    scanf("%d", &p2.age);
    printf("Input marks: \n");
    scanf("%f", &p2.marks);

    sum.marks = p1.marks + p2.marks;
    average.marks = sum.marks / 2;

    printf("Displaying the info of 1st student:\n");
    printf("Name: %s\n", p1.name);
    printf("Age: %d\n", p1.age);
    printf("marks: %f\n", p1.marks);

    printf("\n");

    printf("Displaying the info of 2nd student:\n");
    printf("Name: %s\n", p2.name);
    printf("Age: %d\n", p2.age);
    printf("marks: %f\n", p2.marks);

    printf("\n");

    printf("Average of marks: %f", average.marks);

    return 0;
}