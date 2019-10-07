package mfr.example.movies_tvshow

import android.app.Application
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.interceptors.HttpLoggingInterceptor
import mfr.example.movies_tvshow.di.Injection
import org.koin.android.ext.android.startKoin

class MyApps  : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(Injection.getModule()))
        AndroidNetworking.initialize(applicationContext)
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY)
        }

    }
}