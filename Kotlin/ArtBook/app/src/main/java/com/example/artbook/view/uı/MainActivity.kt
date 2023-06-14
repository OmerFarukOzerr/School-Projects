package com.example.artbook.view.uı

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import com.example.artbook.R
import com.example.artbook.view.util.base.BaseFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    //bu injection, (factoryde inject etiğimiz constructer sayesinde inject edebildik)
    //yapılmasaydı fragment factory oluşturmak için glide da oluşturmamız gerekirdi
    @Inject
    lateinit var FragmentFactory : BaseFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = FragmentFactory
        setContentView(R.layout.activity_main)
    }
}