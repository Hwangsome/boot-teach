package com.bh.grpc.entity

import com.bh.grpc.user.Female
import com.bh.grpc.user.Hobby
import com.bh.grpc.user.Male
import com.bh.grpc.user.Sex
import com.bh.grpc.user.UserInfoContract

fun UserInfoEntity.toContract(): UserInfoContract {
    return this.let { userInfoEntity ->
        UserInfoContract.newBuilder().apply {
            id = userInfoEntity.id
            name = userInfoEntity.name
            age = userInfoEntity.age
            addAllHobby(userInfoEntity.hobbys.map { it.toContract() })
        }.build()
    }
}

fun HobbyEntity.toContract(): Hobby {
    return this.let { hobbyEntity ->
        Hobby.newBuilder().apply {
            id = hobbyEntity.id
            hobbyName = hobbyEntity.name
        }.build()
    }
}

fun SexEntity.toContract(): Sex {
    return this.let { sexEntity ->
        Sex.newBuilder().apply {
            when {
                sexEntity.male != null -> sexEntity.male.toContract()
                sexEntity.feMale != null -> sexEntity.feMale.toContract()
            }
        }.build()
    }
}

fun MaleEntity.toContract(): Male {
    return this.let { maleEntity ->
        Male.newBuilder().apply {
            name = maleEntity.name
        }.build()
    }
}

fun FeMaleEntity.toContract(): Female {
    return this.let { feMaleEntity ->
        Female.newBuilder().apply {
            name = feMaleEntity.name
        }.build()
    }
}

/**==  toDomain ===**/

fun Male.toDomain(): MaleEntity =
    MaleEntity(
        name = this.name
    )

fun Female.toDomain(): FeMaleEntity =
    FeMaleEntity(
        name = this.name
    )

fun Sex.toDomain(): SexEntity {
    return when (sexTypeCase) {
        Sex.SexTypeCase.MALE -> {
            SexEntity(male = male.toDomain())
        }
        Sex.SexTypeCase.FEMALE -> {
            SexEntity(feMale = female.toDomain())
        }
        else -> {
            SexEntity()
        }
    }
}


fun Hobby.toDomain(): HobbyEntity =
    HobbyEntity(id =this.id, name = this.hobbyName)

fun UserInfoContract.toDomain(): UserInfoEntity =
    UserInfoEntity(id = this.id, age=this.age, name = this.name , hobbys = this.hobbyList.map { it.toDomain() }, sex = this.sex.toDomain())