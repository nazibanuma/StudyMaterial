/*
do{

   }while(condition); ((((";" very special in this case))))
*/

/* The answer is At Least once
 'While' is a loop in which the condition is first checked, and then the program is executed. 
In 'Do While' The program is executed at least once then the Condition in while ( ) is checked.*/

#include<stdio.h>

int main(){
    int i=0;
        do{
         printf("the value of i is %d\n",i);
           i++;
        } while (i<4);
        
    return 0;
}