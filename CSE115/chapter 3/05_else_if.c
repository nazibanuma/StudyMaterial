#include<stdio.h>

int main(){
        int age = 45;
        
        if(age>60){
            printf("You can drive and you are a senior citizen");
        }
        else if(age>18){
            printf("You can drive");
        }
        else{
            printf("You can not drive");
        }
    return 0;
}