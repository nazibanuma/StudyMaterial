#include<stdio.h> //PROBLEMMMMMMMMMMMMMMMMMM

//Write a program in C to make such a pattern like a right angle triangle with the number increased by 1.

int main(){
        int i,j,row=4,k=1;

        for(i=1;i<=row;i++){
            for(j=1;j<=i;j++){
                printf("%d ",k++);
            }
            printf("\n");
        }
    return 0;
}