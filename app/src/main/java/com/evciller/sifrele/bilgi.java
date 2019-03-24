package com.evciller.sifrele;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class bilgi extends AppCompatActivity {

    TextView basamak, harf, boyut, simge, olustur, tekrar, kaydet, degis, kopyala;
    ImageView okBas, okHarf, okBoyut, okSimge, okOlustur, okTekrar, okKaydet, okDegis, okKopyala;

    int sira = 0;

    String basamakYazi = "İstediğiniz basamak boyutunu belirleyeceğiniz kısım.", harfYazi = "Harf bilgilerini belirleyeceğiniz kısım.",
            boyutYazi = "Harflerin özelliğini belirleyeceğiniz kısım.", simgeYazi = "Şifrenizin simge bilgilerini belerleyeceğiniz kısım.",
            olusturYazi = "Şifreyi oluşturmayı sağlayan düğme", tekrarYazi = "Tercihlerinizi değiştirebilirsiniz.",
            kaydetYazi = "Oluşturdurulan şifreyi kaydedebilirsiniz.", degisYazi = "Oluşan şifreyi, tercihlerinize göre değişebilirsiniz.",
            kopyalaYazi = "Oluşan şifreyi, üzerine iki kere basarak veya üzerine basılı tutarak kopyalayabilirsiniz.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilgi);

//Yazıların ve düğmelerin TANIMLAMALARI

        basamak = (TextView) findViewById(R.id.basamak);
        harf = (TextView) findViewById(R.id.harf);
        boyut = (TextView) findViewById(R.id.boyut);
        simge = (TextView) findViewById(R.id.simge);
        olustur = (TextView) findViewById(R.id.olustur);
        tekrar = (TextView) findViewById(R.id.tekrar);
        kaydet = (TextView) findViewById(R.id.kaydet);
        degis = (TextView) findViewById(R.id.degis);
        kopyala = (TextView) findViewById(R.id.kopyala);

        okBas = (ImageView) findViewById(R.id.ok1);
        okBoyut = (ImageView) findViewById(R.id.ok3);
        okHarf = (ImageView) findViewById(R.id.ok2);
        okSimge = (ImageView) findViewById(R.id.ok4);
        okOlustur = (ImageView) findViewById(R.id.ok5);
        okTekrar = (ImageView) findViewById(R.id.ok6);
        okKaydet = (ImageView) findViewById(R.id.ok7);
        okDegis = (ImageView) findViewById(R.id.ok8);
        okKopyala = (ImageView) findViewById(R.id.ok9);

        final RelativeLayout rL = (RelativeLayout) findViewById(R.id.rela);

//Yazıların görünüp görünmemeleri başlangıçta
        okBas.setVisibility(okBas.VISIBLE);
        okHarf.setVisibility(okHarf.INVISIBLE);
        okBoyut.setVisibility(okBas.INVISIBLE);
        okSimge.setVisibility(okSimge.INVISIBLE);
        okOlustur.setVisibility(okOlustur.INVISIBLE);
        okTekrar.setVisibility(okTekrar.INVISIBLE);
        okKaydet.setVisibility(okKaydet.INVISIBLE);
        okDegis.setVisibility(okDegis.INVISIBLE);
        okKopyala.setVisibility(okKopyala.INVISIBLE);

        basamak.setVisibility(basamak.VISIBLE);
        harf.setVisibility(harf.INVISIBLE);
        boyut.setVisibility(boyut.INVISIBLE);
        simge.setVisibility(simge.INVISIBLE);
        olustur.setVisibility(olustur.INVISIBLE);
        tekrar.setVisibility(tekrar.INVISIBLE);
        kaydet.setVisibility(kaydet.INVISIBLE);
        degis.setVisibility(degis.INVISIBLE);
        kopyala.setVisibility(kopyala.INVISIBLE);

//Yazıların renkleri
        basamak.setTextColor(Color.WHITE);
        harf.setTextColor(Color.WHITE);
        boyut.setTextColor(Color.WHITE);
        simge.setTextColor(Color.WHITE);
        olustur.setTextColor(Color.WHITE);
        tekrar.setTextColor(Color.WHITE);
        kaydet.setTextColor(Color.WHITE);
        degis.setTextColor(Color.WHITE);
        kopyala.setTextColor(Color.WHITE);

        basamak.setBackgroundColor(Color.BLACK);
        harf.setBackgroundColor(Color.BLACK);
        boyut.setBackgroundColor(Color.BLACK);
        simge.setBackgroundColor(Color.BLACK);
        olustur.setBackgroundColor(Color.BLACK);
        tekrar.setBackgroundColor(Color.BLACK);
        kaydet.setBackgroundColor(Color.BLACK);
        degis.setBackgroundColor(Color.BLACK);
        kopyala.setBackgroundColor(Color.BLACK);


        //RESİMLERLE ANLAT ANLAMIYOR MİLLET!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        basamak.setText(basamakYazi);
        harf.setText(harfYazi);
        boyut.setText(boyutYazi);
        simge.setText(simgeYazi);
        olustur.setText(olusturYazi);
        tekrar.setText(tekrarYazi);
        kaydet.setText(kaydetYazi);
        degis.setText(degisYazi);
        kopyala.setText(kopyalaYazi);


        rL.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (sira == 0)
                {
                    okBas.setVisibility(okBas.INVISIBLE);
                    okHarf.setVisibility(okHarf.VISIBLE);
                    okBoyut.setVisibility(okBas.INVISIBLE);
                    okSimge.setVisibility(okSimge.INVISIBLE);
                    okOlustur.setVisibility(okOlustur.INVISIBLE);
                    okTekrar.setVisibility(okTekrar.INVISIBLE);
                    okKaydet.setVisibility(okKaydet.INVISIBLE);
                    okDegis.setVisibility(okDegis.INVISIBLE);
                    okKopyala.setVisibility(okKopyala.INVISIBLE);

                    basamak.setVisibility(basamak.INVISIBLE);
                    harf.setVisibility(harf.VISIBLE);
                    boyut.setVisibility(boyut.INVISIBLE);
                    simge.setVisibility(simge.INVISIBLE);
                    olustur.setVisibility(olustur.INVISIBLE);
                    tekrar.setVisibility(tekrar.INVISIBLE);
                    kaydet.setVisibility(kaydet.INVISIBLE);
                    degis.setVisibility(degis.INVISIBLE);
                    kopyala.setVisibility(kopyala.INVISIBLE);
                    sira++;
                }
                else if (sira == 1)
                {
                    okBas.setVisibility(okBas.INVISIBLE);
                    okHarf.setVisibility(okHarf.INVISIBLE);
                    okBoyut.setVisibility(okBas.VISIBLE);
                    okSimge.setVisibility(okSimge.INVISIBLE);
                    okOlustur.setVisibility(okOlustur.INVISIBLE);
                    okTekrar.setVisibility(okTekrar.INVISIBLE);
                    okKaydet.setVisibility(okKaydet.INVISIBLE);
                    okDegis.setVisibility(okDegis.INVISIBLE);
                    okKopyala.setVisibility(okKopyala.INVISIBLE);

                    basamak.setVisibility(basamak.INVISIBLE);
                    harf.setVisibility(harf.INVISIBLE);
                    boyut.setVisibility(boyut.VISIBLE);
                    simge.setVisibility(simge.INVISIBLE);
                    olustur.setVisibility(olustur.INVISIBLE);
                    tekrar.setVisibility(tekrar.INVISIBLE);
                    kaydet.setVisibility(kaydet.INVISIBLE);
                    degis.setVisibility(degis.INVISIBLE);
                    kopyala.setVisibility(kopyala.INVISIBLE);
                    sira++;
                }
                else if (sira == 2)
                {
                    okBas.setVisibility(okBas.INVISIBLE);
                    okHarf.setVisibility(okHarf.INVISIBLE);
                    okBoyut.setVisibility(okBas.INVISIBLE);
                    okSimge.setVisibility(okSimge.VISIBLE);
                    okOlustur.setVisibility(okOlustur.INVISIBLE);
                    okTekrar.setVisibility(okTekrar.INVISIBLE);
                    okKaydet.setVisibility(okKaydet.INVISIBLE);
                    okDegis.setVisibility(okDegis.INVISIBLE);
                    okKopyala.setVisibility(okKopyala.INVISIBLE);

                    basamak.setVisibility(basamak.INVISIBLE);
                    harf.setVisibility(harf.INVISIBLE);
                    boyut.setVisibility(boyut.INVISIBLE);
                    simge.setVisibility(simge.VISIBLE);
                    olustur.setVisibility(olustur.INVISIBLE);
                    tekrar.setVisibility(tekrar.INVISIBLE);
                    kaydet.setVisibility(kaydet.INVISIBLE);
                    degis.setVisibility(degis.INVISIBLE);
                    kopyala.setVisibility(kopyala.INVISIBLE);
                    sira++;
                }
                else if (sira == 3)
                {
                    okBas.setVisibility(okBas.INVISIBLE);
                    okHarf.setVisibility(okHarf.INVISIBLE);
                    okBoyut.setVisibility(okBas.INVISIBLE);
                    okSimge.setVisibility(okSimge.INVISIBLE);
                    okOlustur.setVisibility(okOlustur.VISIBLE);
                    okTekrar.setVisibility(okTekrar.INVISIBLE);
                    okKaydet.setVisibility(okKaydet.INVISIBLE);
                    okDegis.setVisibility(okDegis.INVISIBLE);
                    okKopyala.setVisibility(okKopyala.INVISIBLE);

                    basamak.setVisibility(basamak.INVISIBLE);
                    harf.setVisibility(harf.INVISIBLE);
                    boyut.setVisibility(boyut.INVISIBLE);
                    simge.setVisibility(simge.INVISIBLE);
                    olustur.setVisibility(olustur.VISIBLE);
                    tekrar.setVisibility(tekrar.INVISIBLE);
                    kaydet.setVisibility(kaydet.INVISIBLE);
                    degis.setVisibility(degis.INVISIBLE);
                    kopyala.setVisibility(kopyala.INVISIBLE);
                    sira++;
                }
                else
                {
                    if(sira == 4)
                    {
                        rL.setBackgroundResource(R.drawable.sonuc);

                        okBas.setVisibility(okBas.INVISIBLE);
                        okHarf.setVisibility(okHarf.INVISIBLE);
                        okBoyut.setVisibility(okBas.INVISIBLE);
                        okSimge.setVisibility(okSimge.INVISIBLE);
                        okOlustur.setVisibility(okOlustur.INVISIBLE);
                        okTekrar.setVisibility(okTekrar.VISIBLE);
                        okKaydet.setVisibility(okKaydet.INVISIBLE);
                        okDegis.setVisibility(okDegis.INVISIBLE);
                        okKopyala.setVisibility(okKopyala.INVISIBLE);

                        basamak.setVisibility(basamak.INVISIBLE);
                        harf.setVisibility(harf.INVISIBLE);
                        boyut.setVisibility(boyut.INVISIBLE);
                        simge.setVisibility(simge.INVISIBLE);
                        olustur.setVisibility(olustur.INVISIBLE);
                        tekrar.setVisibility(tekrar.VISIBLE);
                        kaydet.setVisibility(kaydet.INVISIBLE);
                        degis.setVisibility(degis.INVISIBLE);
                        kopyala.setVisibility(kopyala.INVISIBLE);

                        sira++;
                    }
                    else if(sira == 5)
                    {
                        okBas.setVisibility(okBas.INVISIBLE);
                        okHarf.setVisibility(okHarf.INVISIBLE);
                        okBoyut.setVisibility(okBas.INVISIBLE);
                        okSimge.setVisibility(okSimge.INVISIBLE);
                        okOlustur.setVisibility(okOlustur.INVISIBLE);
                        okTekrar.setVisibility(okTekrar.INVISIBLE);
                        okKaydet.setVisibility(okKaydet.VISIBLE);
                        okDegis.setVisibility(okDegis.INVISIBLE);
                        okKopyala.setVisibility(okKopyala.INVISIBLE);

                        basamak.setVisibility(basamak.INVISIBLE);
                        harf.setVisibility(harf.INVISIBLE);
                        boyut.setVisibility(boyut.INVISIBLE);
                        simge.setVisibility(simge.INVISIBLE);
                        olustur.setVisibility(olustur.INVISIBLE);
                        tekrar.setVisibility(tekrar.INVISIBLE);
                        kaydet.setVisibility(kaydet.VISIBLE);
                        degis.setVisibility(degis.INVISIBLE);
                        kopyala.setVisibility(kopyala.INVISIBLE);

                        sira++;
                    }
                    else if(sira == 6)
                    {
                        okBas.setVisibility(okBas.INVISIBLE);
                        okHarf.setVisibility(okHarf.INVISIBLE);
                        okBoyut.setVisibility(okBas.INVISIBLE);
                        okSimge.setVisibility(okSimge.INVISIBLE);
                        okOlustur.setVisibility(okOlustur.INVISIBLE);
                        okTekrar.setVisibility(okTekrar.INVISIBLE);
                        okKaydet.setVisibility(okKaydet.INVISIBLE);
                        okDegis.setVisibility(okDegis.VISIBLE);
                        okKopyala.setVisibility(okKopyala.INVISIBLE);

                        basamak.setVisibility(basamak.INVISIBLE);
                        harf.setVisibility(harf.INVISIBLE);
                        boyut.setVisibility(boyut.INVISIBLE);
                        simge.setVisibility(simge.INVISIBLE);
                        olustur.setVisibility(olustur.INVISIBLE);
                        tekrar.setVisibility(tekrar.INVISIBLE);
                        kaydet.setVisibility(kaydet.INVISIBLE);
                        degis.setVisibility(degis.VISIBLE);
                        kopyala.setVisibility(kopyala.INVISIBLE);

                        sira++;
                    }
                    else if(sira == 7)
                    {
                        okBas.setVisibility(okBas.INVISIBLE);
                        okHarf.setVisibility(okHarf.INVISIBLE);
                        okBoyut.setVisibility(okBas.INVISIBLE);
                        okSimge.setVisibility(okSimge.INVISIBLE);
                        okOlustur.setVisibility(okOlustur.INVISIBLE);
                        okTekrar.setVisibility(okTekrar.INVISIBLE);
                        okKaydet.setVisibility(okKaydet.INVISIBLE);
                        okDegis.setVisibility(okDegis.INVISIBLE);
                        okKopyala.setVisibility(okKopyala.VISIBLE);

                        basamak.setVisibility(basamak.INVISIBLE);
                        harf.setVisibility(harf.INVISIBLE);
                        boyut.setVisibility(boyut.INVISIBLE);
                        simge.setVisibility(simge.INVISIBLE);
                        olustur.setVisibility(olustur.INVISIBLE);
                        tekrar.setVisibility(tekrar.INVISIBLE);
                        kaydet.setVisibility(kaydet.INVISIBLE);
                        degis.setVisibility(degis.INVISIBLE);
                        kopyala.setVisibility(kopyala.VISIBLE);

                        sira++;
                    }
                    else
                    {
                        finish();
                    }
                }
            }
        });
    }
}
