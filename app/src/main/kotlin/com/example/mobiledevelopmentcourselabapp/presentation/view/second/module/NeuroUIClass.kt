package com.example.mobiledevelopmentcourselabapp.presentation.view.second.module
import android.os.Parcelable
import com.example.mobiledevelopmentcourselabapp.databinding.ItemNeuroBinding
import java.io.Serializable
interface ItemUIModel: Serializable
data class NeuroUIClass(
    val name: String,
    val photoUrl: String,
    val number: Int,
    val company: String,
    var position: Position,
    val easy_content_velocity_gen: Int,
    val hard_content_velocity_gen: Int,
    val neuroTask: String,
    val fightsCount: Int,
    val winCount: Int,
    val upgradesCount: Int,
    val yellowCardCount: Int,
    val greenCardsCount: Int,
    var isExpanded: Boolean = false
) : ItemUIModel
enum class Position(val Name: String = ""){
    TEXT("GPT-4"),
    IMAGE("StableDiffusion"),
    TEXT_2("Gemini"),
    IMAGE_2("MidJourney"),
    MUSIC("MusicFX")
}
class AdUiModel(val text: String) : ItemUIModel
class AddStrClass(
    val photo: String,
    val description: String
) : ItemUIModel



