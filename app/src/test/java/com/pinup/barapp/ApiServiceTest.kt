import com.pinup.barapp.BuildConfig
import com.pinup.barapp.data.remote.apiservices.ApiService
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceTest {
    private lateinit var server: MockWebServer
    private lateinit var api: ApiService

    @Before
    fun setup() {
        server = MockWebServer()
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val url = original.url.newBuilder()
                    .addQueryParameter("api_token", BuildConfig.API_KEY)
                    .build()
                val request = original.newBuilder().url(url).build()
                chain.proceed(request)
            }
            .addInterceptor(logging)
            .build()

        api = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun requestPathContainsQueryParams() = runBlocking {
        server.enqueue(MockResponse().setBody("{\"data\":[]}"))

        api.getMatchesNext7Days("2024-01-01", "2024-01-08", BuildConfig.API_KEY)

        val recorded = server.takeRequest()
        assertEquals(
            "/fixtures/between/2024-01-01/2024-01-08?include=participants;participants.meta;league&api_token=${BuildConfig.API_KEY}&include=participants;league&api_token=${BuildConfig.API_KEY}",
            recorded.path
        )
    }
}
