#include <stdio.h>

int main() {
int deger;
scanf("%d", &deger);

printf("yüzler basamağı :%d\n", deger/100);
printf("onlar basmağı: %d\n", (deger/10)%10);  
printf("birler basamağı: %d\n", deger%10);
}