package woowacourse.shopping.domain.repository

import woowacourse.shopping.data.model.Product
import woowacourse.shopping.domain.model.RecentProduct

interface RecentProductRepository {
    fun save(product: Product)

    fun update(productId: Int)

    fun findOrNullByProductId(productId: Int): RecentProduct?

    fun findMostRecentProduct(): RecentProduct?

    fun findAll(limit: Int): List<RecentProduct>

    fun deleteAll()
}
