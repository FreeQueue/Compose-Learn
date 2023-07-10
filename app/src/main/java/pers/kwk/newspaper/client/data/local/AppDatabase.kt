package pers.kwk.newspaper.client.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pers.kwk.newspaper.client.data.entity.NewspaperEntity
import pers.kwk.newspaper.client.data.entity.OrderEntity
import pers.kwk.newspaper.client.data.entity.UserEntity
import pers.kwk.newspaper.client.data.local.converter.TokenConverter
import pers.kwk.newspaper.client.data.local.dao.NewspaperDao
import pers.kwk.newspaper.client.data.local.dao.OrderDao
import pers.kwk.newspaper.client.data.local.dao.UserDao

@Database(
	entities = [
		OrderEntity::class,
		UserEntity::class,
		NewspaperEntity::class,
	],
	version = 1
)

@TypeConverters(value = [TokenConverter::class])
abstract class AppDatabase : RoomDatabase() {
	abstract fun orderDao(): OrderDao
	abstract fun userDao(): UserDao
	abstract fun newspaperDao(): NewspaperDao
}