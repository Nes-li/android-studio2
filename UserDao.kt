package com.example.kuaforhatirlatici.data

package com.yourdomain.kuaforhatirlatici.data

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE uid = :uid LIMIT 1")
    suspend fun getUserByUid(uid: String): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)
}