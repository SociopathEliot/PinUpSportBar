import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.pinup.barapp.data.remote.local.AppDatabase
import com.pinup.barapp.data.repositories.CartRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CartRepositoryTest {
    private lateinit var db: AppDatabase
    private lateinit var repository: CartRepository

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        repository = CartRepository(db.cartDao())
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun emptyCartReturnsZeroTotals() = runBlocking {
        val quantity = repository.getTotalQuantity().first()
        val price = repository.getTotalPrice().first()
        assertEquals(0, quantity)
        assertEquals(0.0, price, 0.0)
    }
}
