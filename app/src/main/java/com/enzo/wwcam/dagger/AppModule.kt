package com.enzo.wwcam.dagger

import com.enzo.wwcam.network.NetworkManager
import com.enzo.wwcam.wct.WctApi
import com.enzo.wwcam.wct.WctApiImpl
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
    fun provideWctApi(networkManager: NetworkManager): WctApi {
        return WctApiImpl(networkManager)
    }

//    @Provides
//    @Singleton
//    fun provideTestInjectedClass(): TestInjectedClass {
//        return TestInjectedClass()
//    }
}