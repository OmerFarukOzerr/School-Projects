#include <stdio.h>

const float PI=3.14;
void hesapla(int c,int *cev,int *a);

int main() {
  
  int c, ca, a, cev;
  printf(" kullanıcıdan çap alan buna göre çevre, alan veya her ikisini de ekrana yazdıran programdır \n");
  printf(" cap giriniz:\n");
  scanf(" %d", &ca);
  
  hesapla(ca, &cev, &a); 
  
  do {
    printf("yazdırmak istediğiniz değer nedir?");
    printf("alan için'0' çevre için: '1' her ikisinide ekrana bastırmak için: '2' ye basın \n");
    scanf(" %d", &c);
    
  } while(c<0 && c>3);
  
  if(c==0){
    printf("alan: %.2f", a*PI);    
  } else if (c==1) {
    printf("çevre: %.2f", cev*PI);
    
  } else if (c==2) {
    printf("alan ve çevre: %.2f, %.2f", a*PI, cev*PI);  
  }
  
  return 0;
}

void hesapla(int ca,int *cev,int *a) {
  *cev= 2*ca;
  *a = ca*ca;

}