package com.evciller.sifrele;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;

public class Tercihler extends AppCompatActivity {

    RadioButton harfli, harfsiz, buyuklu, buyuksuz, ikiside, simgeli, simgesiz;
    RadioGroup grup1, grup2, grup3;
    Button SifreOlustur;
    TextView yaziyiKaybet;
    EditText basamak;

    boolean harfliOlsun = false, buyukHarfli = false, simgeOlsun = false,
            BuKu = false, harfAcik = false, yazdi = false;
    int BasamakSayisi = 0, hatalı = 0, emin = 0;

    char [] Bharfler = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};//0
    char [] Kharfler = {'a', 'b', 'c', 'd', 'e'  , 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};//1
    char [] simgeler = {'!', '?', '+', '-', '*', '/', '_', '.', ':', ',', ';', '£', '^', '#', '@', '$', '%', '&', '{', '}', '(', ')', '[', ']', '=', '€', '<', '>', '|','~'};//2
    char [] rakamlar = {'0','1','2','3','4','5','6','7','8','9'};//3

    String olustur="Şifre Oluştur";

    public char [] olusanSifre;

    //!!YEDEK DEĞİŞKENLER!!

        public int QBasamakSayisi;
        public boolean QharfliOlsun, QbuyukHarfli, QBuKu, QsimgeOlsun, QharfAcik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercihler);

//Radio Buttonlarını tanımladık konumlarının yerini
        harfli = (RadioButton) findViewById(R.id.harfli);
        harfsiz = (RadioButton) findViewById(R.id.harfsiz);
        buyuklu = (RadioButton) findViewById(R.id.buyuk);
        buyuksuz = (RadioButton) findViewById(R.id.kucuk);
        ikiside = (RadioButton) findViewById(R.id.ikiside);
        simgeli = (RadioButton) findViewById(R.id.simgeli);
        simgesiz = (RadioButton) findViewById(R.id.simgesiz);

//Radio Buttonlarının içlerini boşalttım.
        buyuklu.setChecked(false);
        buyuksuz.setChecked(false);
        simgeli.setChecked(false);
        simgesiz.setChecked(false);
        harfli.setChecked(false);
        harfsiz.setChecked(false);
        ikiside.setChecked(false);
//İlk değer atandı
        BasamakSayisi = 0;
//Grupları tanımladım
        grup1 = (RadioGroup) findViewById(R.id.HarfGroup);
        grup2 = (RadioGroup) findViewById(R.id.BuKuGroup);
        grup3 = (RadioGroup) findViewById(R.id.Simge);

//Reseferansların belirlenmesi
        yaziyiKaybet = (TextView) findViewById(R.id.textView2);
        basamak = (EditText) findViewById(R.id.basamak);
        SifreOlustur = (Button) findViewById(R.id.SifreOlustur);

//Button üzerindeki yazıyı yazdır
        SifreOlustur.setText(olustur);

//!!!!!!!!!!!-->Üste tanımladığım "sifreOlustur" button unun ne yapacağını belirlerdim<--!!!!!!!!!!!
        SifreOlustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hatalı = secimler(); // secenekeleri alıyor.
                if(hatalı == 0)// eğer tercih yapmamış ise tercihlerin tamamlanmasını bekliyoruz.
                {
                    olustur(); // yapılan tercihlere göre rast gele üretim başlıyor.
                    Intent git = new Intent(getApplication(),Sifre.class); // şifreyi göstereceğimiz sayfaya yol açıyorum.
                    git.putExtra("sonuc",olusanSifre);// oluşan şifre dizisi ni diğer sayfaya yolluyorum.
                    git.putExtra("basamak",BasamakSayisi);// seçilen basamak sayısınız yolluyorum for için
                    git.putExtra("Ybasamak",QBasamakSayisi);
                    git.putExtra("Ybuku",QBuKu);
                    git.putExtra("Ybuyuk",QbuyukHarfli);
                    git.putExtra("YhAcik",QharfAcik);
                    git.putExtra("Yharfli",QharfliOlsun);
                    git.putExtra("Ysimge",QsimgeOlsun);
                    startActivity(git);// sonuc sayfasını başlatıyoruz.
                    finish();
                }
            }

//Tercihler yapılıyor
            public int secimler()
            {
                int harfVarYok = grup1.getCheckedRadioButtonId();// radiobutton ların gruplarina ulasiyorum
                int harfBuKu = grup2.getCheckedRadioButtonId();// Buyuk harler olsun mu? Grubu
                int simgeVarYok = grup3.getCheckedRadioButtonId(); // simge olsun mu olmasın mı?
//EditText in boş olup olmadığını kontrol ediyoruz. bos fonk. yolluyoruz.
                yazdi = bos(basamak);
//Basamak değerinin belirlendiğinden emin oluyoruz değilse uyarıyoruz.
                if(yazdi == false)
                {
                    BasamakSayisi = Integer.parseInt(basamak.getText().toString());

                    if(BasamakSayisi < 1)
                    {
                        Toast.makeText(getApplicationContext(), "Daha büyük bir basamak değeri giriniz!!", Toast.LENGTH_SHORT).show();
                        return 1;// Basamak sayısı bekirlenmez ise hata var olarak 1 yolluyoruz.
                    }
                    else if(BasamakSayisi >32 && BasamakSayisi < 10000)
                    {
                        if(emin==0)
                        {
                            Toast.makeText(getApplicationContext(), "Bu boyutta olmasını istediğinizden emin misiniz?", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), "Eminseniz tekrar 'Şifre Oluştur' a basınız!!", Toast.LENGTH_LONG).show();
                            emin++;
                            return 1;
                        }
                        else
                        {
                            olusanSifre = new char [BasamakSayisi];

                            QBasamakSayisi = BasamakSayisi;
                        }
                    }
                    else if(BasamakSayisi >10000)
                    {
                        Toast.makeText(getApplicationContext(), "Bu kadar büyük şifreyi napacaksın?", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "En fazla On Bin(10000) basamak olur sana!!", Toast.LENGTH_SHORT).show();
                        return 1;
                    }
                    else
                    {
                        olusanSifre = new char [BasamakSayisi];

                        QBasamakSayisi = BasamakSayisi;
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Şifre Boyutu Belirleyiniz!!!", Toast.LENGTH_SHORT).show();
                    return 1;
                }

                switch (harfVarYok)
                {
                    case R.id.harfli: {
                        harfliOlsun = true;
                        break;
                    }
                    case R.id.harfsiz: {
                        harfliOlsun = false;
                        break;
                    }
                    default: {
                        Toast.makeText(getApplicationContext(), "Harf Bilgisini Belirleyiniz!", Toast.LENGTH_SHORT).show();
                        return 1;// harf bilgisi girilmediği için hata bilidiriyoruz
                    }
                }

                QharfliOlsun = harfliOlsun;

                if(harfAcik == true)
                {
                    switch (harfBuKu)
                    {
                        case R.id.buyuk: {
                            buyukHarfli = true;
                            break;
                        }
                        case R.id.kucuk: {
                            buyukHarfli = false;
                            break;
                        }
                        case R.id.ikiside: {
                            BuKu = true;
                            break;
                        }
                        default: {
                            Toast.makeText(getApplicationContext(), "Harf Özelliğini Belirleyiniz!", Toast.LENGTH_SHORT).show();
                            return 1;// harf özelliği belirlenmediği için hata bildiriyoruz.
                        }
                    }
                }

                QbuyukHarfli = buyukHarfli;
                QBuKu = BuKu;

                switch (simgeVarYok) {
                    case R.id.simgeli: {
                        simgeOlsun = true;
                        break;
                    }
                    case R.id.simgesiz: {
                        simgeOlsun = false;
                        break;
                    }
                    default: {
                        Toast.makeText(getApplicationContext(), "Simge Bilgisini Belirleyiniz!", Toast.LENGTH_SHORT).show();
                        return 1;//Simge bilgisi girilmediği için hata giriliyor.
                    }
                }

                QsimgeOlsun = simgeOlsun;

                return 0;
            }
            //Basamak değeri belirlemek için birşey yazıldı mı?
            private boolean bos(EditText yazan) {

                if (yazan.getText().toString().trim().length() > 0)
                {
                    return false; // boş değilse
                }
                else
                {
                    return true; // boşsa
                }
            }

            public void olustur()// tercihlere göre şifre oluşturma işleni başlıyor.
            {
                Random salla = new Random();// rastgele sayı üretmek için javanın kütüphanesinden fonksiyon çağırıyorum.

                for(int i=0;i<BasamakSayisi;i++)
                {
                    int karistir = salla.nextInt(4); // 0 ile 3 arasından sayı arasından sayı seçtiriyorum

                    if(karistir == 0 && harfliOlsun == true && (buyukHarfli == true || BuKu == true))//Harf olsun seçili ise büyük harf olsunda seçili ise Büyük harf alınıyor ama ikiside seçili ise çalışacak
                    {
                        int buyukHarfSec = salla.nextInt(26);//26 tane harfin içinden rastgele harf seçiyor.
                        olusanSifre[i] = Bharfler[buyukHarfSec];// seçilen karakter diziye atanıyor.
                    }
                    else if(karistir == 1 && harfliOlsun == true && (buyukHarfli == false || BuKu == true))//Harf olsun seçili ise küçük harf olsunda seçili ise küçük harf alınıyor ama ikiside seçili ise çalışacak
                    {
                        int kucukHarfSec = salla.nextInt(26);//26 tane harfin içinden rastgele harf seçiyor.
                        olusanSifre[i] = Kharfler[kucukHarfSec];// seçilen karakter diziye atanıyor.
                    }
                    else if(karistir == 2 && simgeOlsun == true)//simge olsun seçili ise simge giren sıkım
                    {
                        int simgeSec = salla.nextInt(30);//30 tane simge arasından 1 tanesini seçiyor.
                        olusanSifre[i] = simgeler[simgeSec];// seçilen karakter diziye atanıyor.
                    }
                    else if(karistir == 3)
                    {
                        int sayiSec = salla.nextInt(10);//10 rakam arasından 1 tanesi seçiliyor.
                        olusanSifre[i] = rakamlar[sayiSec];// seçilen karakter diziye atanıyor.
                    }
                    else// eğer sadece rakam olsun seçili ise bura kullanılacak
                    {
                        int sayiSec = salla.nextInt(10);//10 rakam arasından 1 tanesi seçiliyor.
                        olusanSifre[i] = rakamlar[sayiSec];// seçilen karakter diziye atanıyor.
                    }
                }
                return;
            }
        });
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

//Eğer harfli olsunu seçmiş ise harf özellikleri gözüksin.
        harfli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                harfAcik = true;
                yaziyiKaybet.setVisibility(yaziyiKaybet.VISIBLE);
                buyuklu.setVisibility(buyuklu.VISIBLE);
                buyuksuz.setVisibility(buyuksuz.VISIBLE);
                ikiside.setVisibility(ikiside.VISIBLE);

                QharfAcik = harfAcik;
            }
        });
//Eğer harfli olsunu seçmiş ise harf özellikleri gözükmesin.
        harfsiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                harfAcik = false;
                yaziyiKaybet.setVisibility(yaziyiKaybet.INVISIBLE);
                buyuklu.setVisibility(buyuklu.INVISIBLE);
                buyuksuz.setVisibility(buyuksuz.INVISIBLE);
                ikiside.setVisibility(ikiside.INVISIBLE);

                QharfAcik = harfAcik;
            }
        });
    }
}