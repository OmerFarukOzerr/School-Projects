package com.ofo.yemektariflerikitabi

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.*
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_tarif_kaydet.*
import java.io.ByteArrayOutputStream

class tarif_kaydet : Fragment() {
    var secilenGorselUri : Uri? = null
    var secilenBitmap : Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val View = inflater.inflate(R.layout.fragment_tarif_kaydet, container, false)
        return View

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonKaydet.setOnClickListener {
            kaydet(it)
        }

        imageView1.setOnClickListener {
            resimYukle(it)
        }

        arguments?.let {
            val boolean = tarif_kaydetArgs.fromBundle(it).boolean
            val idArg = tarif_kaydetArgs.fromBundle(it).id

                if (boolean.equals(true)) {
                    buttonKaydet.visibility = View.INVISIBLE

                    context?.let {
                        try {
                            val db = it.openOrCreateDatabase("Tarif", Context.MODE_PRIVATE, null)
                            val cursor = db.rawQuery("SELECT * FROM tarif WHERE id = ?", arrayOf(idArg.toString()))

                            val tarifAdColumnIndex = cursor.getColumnIndex("tarifad")
                            val tarifColumnIndex = cursor.getColumnIndex("tarif")
                            val gorselColumnIndex = cursor.getColumnIndex("gorsel")

                            while (cursor.moveToNext()) {
                                editTextAd.setText(cursor.getString(tarifAdColumnIndex))
                                editTexTarif.setText(cursor.getString(tarifColumnIndex))

                                val byteDizisi = cursor.getBlob(gorselColumnIndex)
                                val gorselBitmap = BitmapFactory.decodeByteArray(byteDizisi, 0, byteDizisi.size)
                                imageView1.setImageBitmap(gorselBitmap)


                            }
                            cursor.close()

                        } catch (e : Exception) {
                            e.printStackTrace()
                        }
                    }

                }else {
                    editTextAd.setText("")
                    editTexTarif.setText("")
                    buttonKaydet.visibility = View.VISIBLE

                    val gorselSecmeGorseli = BitmapFactory.decodeResource(context?.resources, R.drawable.resim_gir)
                    imageView1.setImageBitmap(gorselSecmeGorseli)


                }


        }



    }

    fun kaydet(view: View) {
    //SQlite kayıt
        val tarif = editTexTarif.text.toString()
        val tarifAd = editTextAd.text.toString()

        if (secilenBitmap != null) {
            var kücükBitmap = BitmapKücült(300, secilenBitmap!!)
            var OutputStream = ByteArrayOutputStream()
            kücükBitmap.compress(Bitmap.CompressFormat.PNG,50, OutputStream)
            val ByteDizisi = OutputStream.toByteArray() //OutPutStreamden ByteArraye dönüştü

                try {
                    activity?.let{
                        val dataBase = it.openOrCreateDatabase("Tarif", Context.MODE_PRIVATE, null)
                        dataBase.execSQL("CREATE TABLE IF NOT EXISTS tarif (id INTEGER PRIMARY KEY, tarifad VARCHAR, tarif VARCHAR, gorsel BLOB)")

                        val SQLstring = "INSERT INTO tarif (tarifad, tarif, gorsel) VALUES(?, ?, ?)"
                        val statement = dataBase.compileStatement(SQLstring) // STATEMENT = sqLStatement tan gelir
                        //buradaki Stringi alıp SQL kodu gibi çalıştırmaya olanak tanır
                        //**binding yapmayı sağlar / soru işaretleri yerine değerler atamamaızı sağlar
                        statement.bindString(1, tarifAd)
                        statement.bindString(2, tarif)
                        statement.bindBlob(3, ByteDizisi)

                        statement.execute()


                    }
                } catch(e : Exception) {
                    e.printStackTrace()
                }

            val action = tarif_kaydetDirections.actionTarifKaydetToTarifListe()
            Navigation.findNavController(view).navigate(action)
        //fonksiyonda view çalıştığı yerdeki görünüm için

        }
    }
    fun resimYukle(view : View) {
        //izin sorulur if (expression yazılır) sonra kulanıcıdan resim alıır
        activity?.let {
            if(ContextCompat.checkSelfPermission(it.applicationContext,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PERMISSION_GRANTED){
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
            } else {
                val galeriIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galeriIntent, 2)
            //request kodlar, karar mekanizmaları için lazım, dallanıyor işte
            }
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1)
            if (grantResults.size > 0 && PackageManager.PERMISSION_GRANTED == grantResults[0]) {
                val galeriIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galeriIntent, 2)
            }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) { //intenti action için kullandık o da uriyle alakalı ondan tipi böyle sanırın
        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null)

            secilenGorselUri = data.data //ikincisi get data, ilk data da döndürülen parametre
            //activity yi yuarıda ntentle başlatmıştık orada uri aldı muhtemelen direkt uri ı almış Olduk
            try {
            context?.let {
                if (secilenGorselUri != null) {
                    if(Build.VERSION.SDK_INT >= 28) {
                        val source = ImageDecoder.createSource(it.contentResolver, secilenGorselUri!!)
                        secilenBitmap = ImageDecoder.decodeBitmap(source)
                        imageView1.setImageBitmap(secilenBitmap)

                    } else {
                        secilenBitmap = MediaStore.Images.Media.getBitmap(it.contentResolver, secilenGorselUri)
                        imageView1.setImageBitmap(secilenBitmap)
                    }
                }
            }

            } catch (e : Exception) {
                e.printStackTrace()
            }
        super.onActivityResult(requestCode, resultCode, data)

    }
    fun BitmapKücült (maximumDeger : Int, KullanıcıdansecilenBitmap : Bitmap) : Bitmap {

        var width = KullanıcıdansecilenBitmap.width
        var height = KullanıcıdansecilenBitmap.height
        var BitmapOrani = width.toDouble() / height.toDouble()
        if (BitmapOrani > 1) {
            width = maximumDeger
            var minikHeight = width / BitmapOrani
            height = minikHeight.toInt()

        } else {
            height = maximumDeger
            var minikWidth = height * BitmapOrani
            width = minikWidth.toInt()
        }



    return Bitmap.createScaledBitmap(KullanıcıdansecilenBitmap, width, height, true)
    }

}

//resultlarda şu mantık var, işlem onaylandı mı ? değer döndü mü?

//dangerous olmayan izinler manifesste yazılıp geçilebilir ma diğerlerinin sorulması lazım
//permissions başlığından bulunabilrir
//tehlikel izinler bir defaya mahsus sorulrlar ona göre ayar çekmek lazım, daha önce izin verilmişmi
// verilmemiş mi kontrol edeceğiz

//kontrol checkSelfPermisson ile yapılır, ama activity'den ulaşılabilir

//contexti buradan kontrol ederid : ContextCompat, API uyumlu mu değil mi kendi bakar, çünkü bazı izinler API
//ile sorulmasına zamanla gerek kalmmıştır