package com.ymlct.weatherapp.data.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.ymlct.weatherapp.data.repository.WeatherRepoImpl
import com.ymlct.weatherapp.data.repository.local.CityWeatherDao
import com.ymlct.weatherapp.data.repository.local.WeatherDatabase
import com.ymlct.weatherapp.data.repository.remote.response.ApiResponseAdapterFactory
import com.ymlct.weatherapp.data.repository.remote.api.OpenWeatherApi
import com.ymlct.weatherapp.data.repository.remote.api.YandexWeatherApi
import com.ymlct.weatherapp.domain.repository.WeatherRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherDataModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

    @Provides
    @Singleton
    @Named("Yandex")
    fun provideRetrofitYandex(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.weather.yandex.ru/")
            .addCallAdapterFactory(ApiResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    @Named("OpenWeather")
    fun provideRetrofitOpenWeather(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/")
            .addCallAdapterFactory(ApiResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideYandexWeatherApi(@Named("Yandex") retrofit: Retrofit): YandexWeatherApi {
        return retrofit.create(YandexWeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOpenWeatherApi(@Named("OpenWeather") retrofit: Retrofit): OpenWeatherApi {
        return retrofit.create(OpenWeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepo(
        yandexWeatherApi: YandexWeatherApi,
        openWeatherApi: OpenWeatherApi,
        cityWeatherDao: CityWeatherDao
    ): WeatherRepo {
        return WeatherRepoImpl(
            yandexWeatherApi,
            openWeatherApi,
            cityWeatherDao
        )
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(
        application: Application
    ): WeatherDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            WeatherDatabase::class.java,
            "WeatherDb").build()
    }

    @Provides
    @Singleton
    fun provideCityWeatherDao(
        database: WeatherDatabase
    ): CityWeatherDao {
        return database.cityWeatherDao()
    }


}