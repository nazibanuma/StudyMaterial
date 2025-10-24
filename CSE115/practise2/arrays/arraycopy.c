#include<stdio.h>

int main(){
    int arr1[5]={10,21,37,98,45};
    int arr2[5];
    

    for(int i=0;i<5;i++){
        arr2[i]=arr1[i];
    }

    for(int i=0;i<5;i++){
        printf("%d\t",arr2[i]);
    }
    return 0;
}