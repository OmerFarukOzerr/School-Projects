#include <stdio.h>

void udf(int, int); //makro fonksiyon kullandým deðer döndürmeyecek, ana gövdeden yükü azalttým
int main() {
	
	int m, n;
	char c;
	
	//kullanýcý tercihine göre program kapanmadan tekrarlanabilsin diye do-while kullandým
	do{
		printf("n ve m 3 basamaklýlarý için, n-m arasýndaki rakamlarýnýn toplamý 10 olan sayýlarýn ortalamasýný hesaplayan programdýr \n");
		printf("hesaplama için 3 basamaklý 2 tane doðal sayý deðeri giriniz ilki küçük olan olsun \ndeðerler:");
		scanf(" %d", &n);
		scanf(" %d", &m);
		
		if( (n<100 || n>=1000 || m<100 || m>=1000 || m<n) ) { //kullanýcýdan alýnan deðerlerin kontrolü, ekstradan m<n istediðim için onu da kontrol ettim
			printf("formata uygun deðer giriniz lütfen");
			break;
		}
		
		udf(n, m); //hesaplama iþlermleri burada, çoðu iþ burada yani
		
		printf("tekrar denemek ister misin? y/n \n");
		scanf(" %c", &c);
	} while( c=='y' ); //kullanýcý konsolu kapatmadan tekrar deneyebilir
	return 0;
}

void udf(int a, int b){
	//define, initialization,
	int aD=0, sayac=0, toplam=0; //aD=ara deðer, iþemleri onun üzerinden yapýyorum iþin sonunda 'n'deðiþmesin diye(a=n)

	while( a<=b ) {
		aD = a;
		//rakamlarýna ayýrýp toplamlarý 10 mu kontrol ediyorum, 10'sa 3 basamaklý sayýlarýn toplamýný loop döndükçe alýyorum
		//türlü yollarla yapýlabilir en kolayý bu gibi geldi
		
		if( ((aD/100)+((aD/10)%10)+(aD%10))==10 ) {
			sayac++; //sayaç önemli, bu kapsama giriþ sayýsý, istediðimiz türden kaç sayý var onu gösterir
			toplam = a+toplam;
		}
		a++ ;
	}
	
	if(toplam != 0) { //bu önemli istediðimiz türden sayý yoksa 0/0 olduðu için program çöker
		printf( "hesaplanan deðer %f \n", ( (float)toplam/ (float)sayac) ); //en doðru sonuç için floata çevirmek gerekli
	} else {	
		printf("cevap 0 \n");
	}
}
