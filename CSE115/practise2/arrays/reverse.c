#include<stdio.h>

int main(){
        int arr[5]={10,21,37,98,45};
        int temp;

        for(int i=0;i<5/2;i++){
            temp=arr[i];
           arr[i]= arr[5-i-1];
            arr[5-i-1]=temp;

        }

        for(int i=0;i<5;i++){
            printf("%d\t",arr[i]);
        }

    return 0;
}