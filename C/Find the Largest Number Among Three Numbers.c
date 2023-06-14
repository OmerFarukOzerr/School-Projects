#include <stdio.h>
void udf(float, float, float);

int main( ) {
//define
float a, b, c;
char cevap;  

  do{
    printf("3 sayı gir cigerim sırasını unututma ama:\n");
    scanf(" %f", &a);
    scanf(" %f", &b);
    scanf(" %f", &c);
    udf(a, b, c);  

    printf("devam etmek ister misin? y or n:\t");
    scanf(" %c", &cevap);
  } while(cevap == 'y');
return 0;
}

void udf(float x, float y, float z) {
  if(x>y && x>z) {
    printf("1.sayı en büyük cigerim\n");
  
  } else if (y >z && y>x) {
    printf("2.sayı en büyük cigerim\n");
  
  } else if(x==y && x==y && y==z) {
    printf("hepsi eşit kardeş\n");

  } else{
    printf("3.sayı en büyük cigerim\n");
  }  
}