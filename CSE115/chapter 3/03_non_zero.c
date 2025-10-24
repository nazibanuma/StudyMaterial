#include<stdio.h>

int main(){
        if(1){
            printf("This if is executed\n");
        }
        if(2.34){
            printf("This if is also executed\n");
        }
        if('c'){
            printf("This if is also executed\n");
        }
        if(0){
            printf("I am zero I am not executed");
        } //it wont execute bcuz its zero
    return 0;
}