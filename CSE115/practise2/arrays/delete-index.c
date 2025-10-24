#include <stdio.h>

int main()
{

    int index = 5, index_to_remove = 1;
    int arr1[5] = {10, 32, 21, 98, 45};

    for (int i = index_to_remove; i < index-1; i++)
    {
        (arr1[i] = arr1[i + 1]);
    }

    for(int i=0;i<5-1;i++){
        printf("%d\t",arr1[i]);
    }
    return 0;
}