package com.teambear.goodielist.models

import android.os.Parcelable
import com.teambear.goodielist.serializers.DateSerializer
import com.teambear.goodielist.serializers.UUIDSerializer
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable
import java.util.*

@Parcelize
data class ParcelRecipe (
    @Serializable(with = UUIDSerializer::class)
    var id: UUID,

    var username: String,

    var created: Long,

    var name: String,

    var tags: List<String>,

    var ingredients: List<String>,

    var description: String,

    var steps: List<String>
) : Parcelable{

    constructor(newRecipe: Recipe) : this(
        newRecipe.id,
        newRecipe.username,
        newRecipe.created,
        newRecipe.name,
        newRecipe.tags,
        newRecipe.ingredients,
        newRecipe.description,
        newRecipe.steps
    )

    companion object{

        fun deparcelize(parcel: ParcelRecipe) : Recipe {
            return Recipe(
                parcel.id,
                parcel.username,
                parcel.created,
                parcel.name,
                parcel.tags,
                parcel.ingredients,
                parcel.description,
                parcel.steps
            )
        }

    }
}
