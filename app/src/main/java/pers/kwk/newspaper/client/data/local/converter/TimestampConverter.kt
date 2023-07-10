package pers.kwk.newspaper.client.data.local.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.sql.Timestamp

@ProvidedTypeConverter
class TimestampConverter {
	@TypeConverter
	fun fromTimestamp(value: Timestamp): String {
		return value.toString()
	}

	@TypeConverter
	fun toTimestamp(value: String): Timestamp {
		return Timestamp.valueOf(value)
	}
}