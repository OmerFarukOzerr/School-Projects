#include <stdio.h>
void udf(int);

void main( ) {
  int n;
  char c;
  
  do {
    printf("asal sayı gir cigerim. 1'den büyük olsun:\n");
    scanf(" %d", &n);
    if(n<1){
      printf("düzgün değer gir lan it\n");
      break;
    }
    udf(n);
    
    printf("tekrar denemek ister misin? y/n? \n");
    scanf(" %c", &c);
  } while(c=='y');
}

void udf(int x) {
  int i=1;
  
  while(i++,i<=x) {
    if( (x%i)==0 ) {
      break;
    }
  }
  
  if(i==x) {
    printf("sayı asal cigerim \n");
    } else {
    printf("sayı asal değil cigerim \n");
    }
}