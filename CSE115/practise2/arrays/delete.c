#include <stdio.h>

int main()
{

    int index = 5, num = 1;
    int arr1[5] = {10, 21, 21, 98, 45};

    for (int i = num; i < index; i++)
    {
        (arr1[i + 1] = arr1[i]);
    }

    for(int i=0;i<5-1;i++){
        printf("%d\t",arr1[i]);
    }
    return 0;
}