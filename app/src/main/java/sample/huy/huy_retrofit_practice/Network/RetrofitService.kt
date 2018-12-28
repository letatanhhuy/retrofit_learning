package sample.huy.huy_retrofit_practice.activity.Network

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
class RetrofitService {
    @Module
    companion object {
        private const val BASE_URL = "https://my-json-server.typicode.com"
        private const val BASE_URL_LOCAL = "http://10.0.2.2:3000"
        private var retrofit:Retrofit = retrofit2.Retrofit.Builder()
                                .baseUrl(BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()

        private var retrofitLocal:Retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL_LOCAL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        @Provides
        @Singleton
        fun getInstance():Retrofit {
            return retrofit
        }

        @Provides
        @Singleton
        fun getInstanceLocal():Retrofit {
            return retrofitLocal
        }
    }
}