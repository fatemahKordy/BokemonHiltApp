package com.example.bokemonhiltapp.di

import com.example.bokemonhiltapp.network.BASE_URL
import com.example.bokemonhiltapp.network.PokemonsApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)//Application scope
@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun provideKotlinJsonAdapterFactory(): KotlinJsonAdapterFactory = KotlinJsonAdapterFactory()

    @Singleton
    @Provides
    fun providePokemonsApiService( moshi: Moshi): PokemonsApiService {
         // The Retrofit object with the Moshi converter.
        return  Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(BASE_URL)
                .build().create(PokemonsApiService::class.java)
    }


    @Provides
    fun provideMoshi(kotlinJsonAdapterFactory: KotlinJsonAdapterFactory): Moshi {
        // Build the Moshi object with Kotlin adapter factory that Retrofit will be using.
        return Moshi.Builder()
            .add(kotlinJsonAdapterFactory)
            .build()

    }
}