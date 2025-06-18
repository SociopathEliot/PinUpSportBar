import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.pinup.barapp.data.remote.local.CartDao
import com.pinup.barapp.data.repositories.CartRepository
import com.pinup.barapp.domain.models.CartItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CartRepositoryTest {
    private lateinit var db: AppDatabase
    private lateinit var dao: CartDao
    private lateinit var repository: CartRepository

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.cartDao()
        repository = CartRepository(dao)
    }

    @After
    fun teardown() {
    }

    @Test
    fun emptyCartReturnsZeroTotals() = runBlocking {
        val quantity = repository.getTotalQuantity().first()
        val price = repository.getTotalPrice().first()
        assertEquals(0, quantity)
        assertEquals(0.0, price, 0.0)
    }

}
