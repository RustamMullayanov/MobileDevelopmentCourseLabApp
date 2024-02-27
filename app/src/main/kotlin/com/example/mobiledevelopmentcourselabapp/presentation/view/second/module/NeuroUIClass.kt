package com.example.mobiledevelopmentcourselabapp.presentation.view.second.module

import com.example.mobiledevelopmentcourselabapp.databinding.ItemNeuroBinding


class NeuroUIClass(
    val name: String,
    val photoUrl: String,
    val number: Int,
    val company: String,
    var position: Position,
    val easy_content_velocity_gen: Int,
    val hard_content_velocity_gen: Int,
    val neuroTask: String,
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
interface ItemUIModel


