package aga.android.migrations.sample.cache

import io.requery.Entity
import io.requery.ForeignKey
import io.requery.Generated
import io.requery.Key
import io.requery.ReferentialAction
import io.requery.Table

@Entity
@Table(name = "\"child\"")
interface IChild {

    @get:Generated
    @get:Key
    val id: Int

    val name: String

    @get:ForeignKey(
        delete = ReferentialAction.CASCADE,
        update = ReferentialAction.NO_ACTION,
        references = IParent::class
    )
    val parentId: Int
}