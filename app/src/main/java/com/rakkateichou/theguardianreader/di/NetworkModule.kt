package com.rakkateichou.theguardianreader.di

import com.rakkateichou.theguardianreader.BuildConfig
import com.rakkateichou.theguardianreader.Constants.API_BASE_URL
import com.rakkateichou.theguardianreader.api.NewsApi
import com.rakkateichou.theguardianreader.data.model.Section
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val url = original.url().newBuilder()
                .addQueryParameter("order-by", "relevance") // order by relevance by default
                .addQueryParameter("show-fields",
                    "headline,byline,commentable,liveBloggingNow") // additional info fields
                .addQueryParameter("show-tags", "tone") // a way to determine importance of the story
                .addQueryParameter("api-key", BuildConfig.API_KEY) // api key
                .build()
            val new = original.newBuilder().url(url).build()
            return@addInterceptor chain.proceed(new)
        }
        .build()

    @Singleton
    @Provides
    fun providesNewsService(okHttpClient: OkHttpClient): NewsApi = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(object : Converter.Factory() {
            override fun stringConverter(
                type: Type,
                annotations: Array<out Annotation>,
                retrofit: Retrofit
            ): Converter<*, String>? {
                if (annotations.hasIdOfSection()) {
                    return Converter<Section, String> { value -> value.id }
                }
                return super.stringConverter(type, annotations, retrofit)
            }

            fun Array<out Annotation>.hasIdOfSection(): Boolean {
                for (annotation in this) {
                    if (annotation is IdOfSection) return true
                }
                return false
            }
        })
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsApi::class.java)
}

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class IdOfSection