#include <stdio.h>

int main() {
    float totalAmount = 0, totalPrice = 0, amount, price;

    while (1) {
        
        printf("Enter amount (in kg): ");
        scanf("%f", &amount);
        
       
        if (amount == 0) {
            break;
        }
        
       
        if (amount < 0) {
            printf("Invalid input, enter a positive number\n");
            continue;  
        }

        
        printf("Enter price: ");
        scanf("%f", &price);

       
        if (price < 0) {
            printf("Invalid input, enter a positive number\n");
            continue;  
        }

      
        totalAmount += amount;
        totalPrice += price;
    }

   
    if (totalAmount > 0) {
        float averagePrice = totalPrice / totalAmount;
        printf("Total amount (in kg): %.2f, Total price: %.2f, Average price per kg: %.2f\n", totalAmount, totalPrice, averagePrice);
    }

    return 0;
}
