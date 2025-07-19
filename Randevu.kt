package com.example.kuaforhatirlatici.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "randevular")
data class Randevu(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val musteriAdi: String,
    val tarih: Date,
    val saat: String,
    val hizmetTipi: String,
    val aciklama: String? = null,
    val telefon: String? = null,
    val ekleyenEmail: String,
    val olusturmaTarihi: Long = System.currentTimeMillis(),
    val guncellenmeTarihi: Long = System.currentTimeMillis()
)