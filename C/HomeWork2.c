#include <stdio.h>

int main() {
	
	float n, hY, d1; //type casting doðru olsun diye ilgili deðerler foat alýnýr
	int d2;
	char a, c='%'; //yüzde iþareti kullanabilmek için
	
	do { //tekrar denenebilirlik için
	
		printf("bir kare alma programýdýr, lütfen bir tam sayý deðer girin deðer: \n");
		scanf(" %f", &n);
		//tek satýrda da yapýlabilir, böylesi daha okunaklý hem de iþ kompleksleþirse çözmesi kolay olur
	
		d1 = n*n;
		d2 = (int)n*(int)n;
		hY = ( (d1-d2)/d1 )*100;
		//hY ='hataYüzdesi' , hata payý kontrol edilir, doðruysa 0 olduðundan else'e geçilir
		
		if( hY>0 ) { //hata yüzdesinin 0'dan küçük olduðu bir durum yok
			printf("krdþm tam sayý gir demedim mi ben? Cevap hatalý çýkacak bak: \n");
			printf("%c%.2f hatayla %d deðerini buldun. Aslýnda cevap: %f \n",c, hY, d2, d1); 
			//büyük	sayýlarda yüzde 0 yaklaþýyor ondan formatlý yazdýrdým int istediðinizi biliyorum
	
		} else if( hY==0 ) { //0'sa hata yok zaten
			printf("cevap: %d \n", d2);
	
		} else {
			// denklem 0'da hatalý oluyor (0/0 tanýmsýzlýðý), böyle çözdüm sorunu
			printf("cevap: 0 \n ");
		}
		printf("tekrar denemek ister misin? y/n \n");
		scanf(" %c", &a);
	} while( a=='y' );
	return 0;
}
