package sample.huy.huy_retrofit_practice.activity.Network

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
class RetrofitService {
    @Module
    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com"
        private var retrofit:Retrofit = retrofit2.Retrofit.Builder()
                                .baseUrl(BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()
        @Provides
        @Singleton
        fun getInstance():Retrofit {
            return retrofit
        }
    }
}