#include <stdio.h>

struct Time
{
    int hour;
    int minutes;
    int seconds;
};

int main()
{
    struct Time t1, t2, sum;

    printf("For 1st time input:\n");
    printf("Input hour: \n");
    scanf("%d", &t1.hour);
    printf("Input minutes: \n");
    scanf("%d", &t1.minutes);
    printf("Input seconds: \n");
    scanf("%d", &t1.seconds);

    printf("For 2nd time input:\n");
    printf("Input hour: \n");
    scanf("%d", &t2.hour);
    printf("Input minutes: \n");
    scanf("%d", &t2.minutes);
    printf("Input seconds: \n");
    scanf("%d", &t2.seconds);

    sum.seconds = t2.seconds + t1.seconds;
    sum.minutes = t2.minutes + t1.minutes;
    sum.hour = t2.hour + t1.hour;

    if (sum.seconds >= 60)
    {
        ++sum.minutes;
        sum.seconds = sum.seconds - 60;
    }

    if (sum.minutes >= 60)
    {
        ++sum.hour;
        sum.minutes = sum.minutes - 60;
    }

    printf("%d:%d:%d", sum.hour, sum.minutes, sum.seconds);

    return 0;
}