package pers.kwk.newspaper.client.data.local.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.math.BigDecimal

@ProvidedTypeConverter
class BigDecimalConverter {
	@TypeConverter
	fun fromBigDecimal(value: BigDecimal): String {
		return value.toString()
	}

	@TypeConverter
	fun toBigDecimal(value: String): BigDecimal {
		return BigDecimal(value)
	}
}