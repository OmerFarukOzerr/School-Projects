package com.example.artbook.view.util.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.example.artbook.view.uı.Arts
import com.example.artbook.view.uı.ArtsDetail
import com.example.artbook.view.uı.ArtsImage
import com.example.artbook.view.uı.adapter.ArtsAdapter
import com.example.artbook.view.uı.adapter.ImageApıAdapter
import com.example.artbook.view.viewmodel.ArtsViewModel
import javax.inject.Inject

class BaseFragmentFactory @Inject constructor(
    private val glide : RequestManager,
    private val artsAdapter: ArtsAdapter,
    private val imageApiAdapter : ImageApıAdapter,
    private val viewModel : ArtsViewModel

) : FragmentFactory() {

    //glideı constructerda istemeseydim lateinint olarak yazsaydım bu sınıftan oluşturulanlara inject edemezdim

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when(className) {
            ArtsDetail::class.java.name -> ArtsDetail(glide, viewModel)
            Arts::class.java.name -> Arts(artsAdapter, viewModel)
            ArtsImage::class.java.name -> ArtsImage(imageApiAdapter, viewModel)

            else-> super.instantiate(classLoader, className)
        }

    }

}