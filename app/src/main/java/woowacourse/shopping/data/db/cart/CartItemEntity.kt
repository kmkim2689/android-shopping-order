package woowacourse.shopping.data.db.cart

import androidx.room.Entity
import androidx.room.PrimaryKey
import woowacourse.shopping.domain.model.CartItem

@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val productId: Int,
    val productName: String,
    val price: Int,
    val imageUrl: String,
    val quantity: Int,
)

fun CartItemEntity.toCartItem(): CartItem {
    return CartItem(
        id = this.id,
        productId = this.productId,
        productName = this.productName,
        price = this.price,
        imageUrl = this.imageUrl,
        quantity = quantity,
    )
}
