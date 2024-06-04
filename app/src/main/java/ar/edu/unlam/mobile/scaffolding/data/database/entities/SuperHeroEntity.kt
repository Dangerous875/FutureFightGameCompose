package ar.edu.unlam.mobile.scaffolding.data.database.entities
import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

@Entity(tableName="superHero_table")
data class SuperHeroEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")  val id:Int=0,
    @ColumnInfo(name="name") val name:String,
    @ColumnInfo(name="image") val image:String,
    @ColumnInfo(name="winRate") val winRate:Int

)