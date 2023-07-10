package pers.kwk.newspaper.client.util

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale

fun formatDate(dateUTC: String?): String {
	if (dateUTC.isNullOrEmpty()) return ""

	val date = SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss'Z'", Locale.getDefault()).parse(dateUTC)
	val newFormat = SimpleDateFormat("dd/MM/yyy", Locale.getDefault())
	return date?.let { newFormat.format(it) }.orEmpty()
}

fun BigDecimal.toYuan(): String {
	val formatter = NumberFormat.getCurrencyInstance(Locale.CHINA) as DecimalFormat
	// 设置最少保留两位小数
	formatter.minimumFractionDigits = 2
	// 设置最多保留两位小数
	formatter.maximumFractionDigits = 2
	// 返回格式化后的字符串
	return formatter.format(this)
}