package com.teambear.goodielist.storage

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.util.*

@ProvidedTypeConverter
internal class UuidDbConverter {
    @TypeConverter
    fun UUIDToString(uuid: UUID): String? {
        return uuid.toString();
    }

    @TypeConverter
    fun StringToUUID(string: String): UUID? {
        return UUID.fromString(string)
    }
}
