package com.example.kuaforhatirlatici.data

package com.yourdomain.kuaforhatirlatici.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "appointments")
data class Appointment(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val customerName: String,
    val date: String, // ISO-8601 format (yyyy-MM-dd)
    val time: String, // HH:mm
    val serviceType: String,
    val note: String? = null,
    val phoneNumber: String? = null,
    val createdBy: String, // Kullanıcı e-posta veya Google id
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)