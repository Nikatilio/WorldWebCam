package com.enzo.wwcam.dagger

import android.content.Context
import com.enzo.wwcam.network.NetworkManager
import com.enzo.wwcam.wct.WctApi
import com.enzo.wwcam.wct.WctApiImpl
import com.enzo.wwcam.wct.WctCacheManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

//    @Binds
//    fun bindWctApi(wctApiImpl: WctApiImpl): WctApi

    @Provides
    @Singleton
    fun provideNetworkManager(): NetworkManager {
        return NetworkManager()
    }

    @Provides
    @Singleton
    fun provideCacheManager(applicationContext: Context): WctCacheManager {
        return WctCacheManager(applicationContext)
    }

    @Provides
    @Singleton
    fun provideWctApi(networkManager: NetworkManager, cacheManager: WctCacheManager): WctApi {
        return WctApiImpl(networkManager, cacheManager)
    }

//    @Provides
//    @Singleton
//    fun provideTestInjectedClass(): TestInjectedClass {
//        return TestInjectedClass()
//    }
}