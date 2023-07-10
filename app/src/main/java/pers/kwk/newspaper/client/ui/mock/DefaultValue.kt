package pers.kwk.newspaper.client.ui.mock

import pers.kwk.newspaper.client.data.entity.NewspaperEntity
import pers.kwk.newspaper.client.data.entity.TokenEntity
import pers.kwk.newspaper.client.data.entity.UserEntity
import java.math.BigDecimal

val tokenEntity= TokenEntity(0,"")
val userEntity= UserEntity(tokenEntity,"",null)
val newspaperEntity = NewspaperEntity("", "", null, "", 0, "", BigDecimal.ZERO)
