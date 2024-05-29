package woowacourse.shopping

import android.app.Application
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import woowacourse.shopping.data.db.cart.CartDatabase
import woowacourse.shopping.data.db.recent.RecentProductDatabase
import woowacourse.shopping.data.remote.BasicAuthInterceptor
import woowacourse.shopping.data.remote.CartService
import woowacourse.shopping.data.remote.ProductService
import woowacourse.shopping.data.remote.RemoteCartDataSource
import woowacourse.shopping.data.remote.RemoteProductDataSource

class ShoppingApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val logging =
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

        val client =
            OkHttpClient.Builder()
                .addInterceptor(BasicAuthInterceptor(username = "kmkim2689", password = "password"))
                .addInterceptor(logging)
                .build()

        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        val productService = retrofit.create(ProductService::class.java)
        val cartService = retrofit.create(CartService::class.java)

        remoteCartDataSource = RemoteCartDataSource(cartService)
        remoteProductDataSource = RemoteProductDataSource(productService)
        cartDatabase = CartDatabase.getInstance(this)
        recentProductDatabase = RecentProductDatabase.getInstance(this)
    }

    companion object {
        lateinit var cartDatabase: CartDatabase
        lateinit var recentProductDatabase: RecentProductDatabase
        lateinit var remoteCartDataSource: RemoteCartDataSource
        lateinit var remoteProductDataSource: RemoteProductDataSource
        private const val BASE_URL = "http://54.180.95.212:8080/"
    }
}
