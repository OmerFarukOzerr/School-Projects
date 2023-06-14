package com.example.artbook.view.hilt

import android.content.Context
import androidx.room.Room
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.artbook.R
import com.example.artbook.view.database.Dao
import com.example.artbook.view.database.DataBase
import com.example.artbook.view.repository.ArtRepoInterface
import com.example.artbook.view.repository.ArtRepository
import com.example.artbook.view.service.RetrofitAPI
import com.example.artbook.view.util.Constants.BASE_URL
import com.example.artbook.view.viewmodel.ArtsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //interface functionları otomatik olarak abstractlar
    //abstract class funları otomatik non abstractlar istersen abstract da yapabilirsin
    //abstract bir fun uygulanacağı yerde initialize edilmeli functionları body almaz yani interfacelerde default vardı ama
    //o yüzzden döndürecekleri değer yazar genelde uygulandıkları yerlerde ona göre değer döndürürler

    //bunlar abstact classlar ve interfacelerden uygulandığı için @provider kullanılır
    //absract class methodları döndürücekleri değer verilir ve uygulanırken buna göre uygulanır
    //değer döndürme için single expression functionlarda kullanılabilir(kullanılamaz notttt)
    //body açmış oluyorsun kısmen sadece concise kod olduğu için arkada işliyor

    //burada eşittirlerle tanımlayabiliyoruz çünkü buraya implementationlarını yapıyoruz aslında
    //ve bu sayede implement ettiğimiz şeliyle heryerden ulaşabilieceğiz
    //
    @Singleton
    @Provides
    fun ınjectRoomDataBase(@ApplicationContext context : Context) : DataBase {

        return Room.databaseBuilder(
            context, DataBase::class.java, "database"
             ).build()
    }



    //burada daonun oluşturlma şekli böyle, abstactların direkt instanceı olmuyor o yüzden return olaak classı veremiyoruz
    //dao istendiğinde direkt retrundekini çekiyor o da mantıklı, burada yapmasaydık üstteki functionda olduğu gibi-
    //her yapmak istediğimzi yerde tekrardan oluşturmak zorunda kalırdık boilerplate kod olurdu

    @Singleton
    @Provides
    fun injectDao(dataBaseDaoImplementor : DataBase) = dataBaseDaoImplementor.getDao()


    //RetroFitApı constructerda çağrıldığıda instance ı return olan builder oluyor, o da zaten requestlerden oluşturuluyor
    //ikisi birleşmi gibi oluyor yani

    @Singleton
    @Provides
    fun injectRetrofit() : RetrofitAPI {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitAPI::class.java)

    }

    @Singleton
    @Provides
    fun ınjectGlide(@ApplicationContext context : Context) : RequestManager {

        return Glide.with(context)
                .setDefaultRequestOptions(
                    RequestOptions()
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .error(R.drawable.ic_launcher_foreground)
                )

    }

    @Singleton
    @Provides
    fun injectRepository (dao : Dao, retrofit : RetrofitAPI) : ArtRepoInterface = ArtRepository(dao, retrofit) as ArtRepoInterface


    @Singleton
    @Provides
    fun injectVİewModel(repository: ArtRepository): ArtsViewModel = ArtsViewModel(repository)
}


