package aga.android.migrations.sample.cache

import io.requery.Column
import io.requery.Entity
import io.requery.Generated
import io.requery.Key
import io.requery.Table

@Entity
@Table(name = "parent")
internal interface IParent {

    @get:Generated
    @get:Key
    val id: Int

    val name: String

    @get:Column(nullable = false, value = "\'default@mail.com\'")
    val email: String
}