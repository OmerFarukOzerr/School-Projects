package com.ofo.country_appsd.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class MotherViewModel(app : Application) : AndroidViewModel(app), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


    override fun onCleared() {
        super.onCleared()
        job.cancel()

        //yenir bir coroutine oluşturduğunu
        //bir Job isteneceği diğer işlemlere bloklamadan threading yapacağı buranın özeti

    }
}