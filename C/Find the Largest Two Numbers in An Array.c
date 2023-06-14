#include <stdio.h>

int main() {
  int n, l, sL, aD;
  printf("dizi elementleri ve sınırlarıyla işlemler ve en büyük iki elemnti bulma \n");
  printf("dizi kaç elemanlı olsun? \n");
  scanf(" %d", &n);
  printf("dizinin elementleri(tam sayı olsunlar): \n");
  
  int array[n]; //element defination
  for(int i=0; i<n ; i++) {    
    scanf(" %d", &array[i]);   
  
  }
  l=array[0];
  sL=array[1];
  
  for(int i=0; i<n ; i++) {
   
    if( array[i]>l ){
      sL=l;
      l=array[i];
      
    } else if( array[i]!=l && array[i]>sL) {
      sL=array[i];      
    }
  }
 printf("en büyük = %d, ikinci en büyük= %d \n", l, sL); 
 return 0;
}