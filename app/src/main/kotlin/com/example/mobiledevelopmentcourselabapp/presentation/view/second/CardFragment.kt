package com.example.mobiledevelopmentcourselabapp.presentation.view.second
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.PluralsRes
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.mobiledevelopmentcourselabapp.R
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentCardBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.module.NeuroUIClass
class CardFragment  : Fragment() {
    private var _binding: FragmentCardBinding? = null
    private val binding get() = _binding!!
    private val neuro by lazy { arguments?.getSerializable(CARD_NEURO_KEY) as? NeuroUIClass }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        var arrayTypeWords = arrayOf(" слов в минуту", " мелодий за 20 секунд", " картинки в минуту", " картинок в минуту",
            " мелодия за 20 секунд");
        var arrayTypeWordsGames = arrayOf(" игра", " игры", " игр")
        var arrayTypeWordsWin = arrayOf(" победа", " победы", " побед")
        var arrayTypeWordsGreenCards = arrayOf(" зеленая", "зеленые", " зеленых")
        var arrayTypeWordsCountUpgrades = arrayOf(" обновление", "обновления", " обновлений")
        var arrayTypeWordsYellowCards= arrayOf(" желтая", " желтые", " желтых")
        neuro?.let { neuro ->
            binding.name.text = neuro.position.Name
            binding.number.text = neuro.number.toString()
            binding.positionValue.text = neuro.easy_content_velocity_gen.toString()
            binding.teamValue.text = neuro.company
            binding.fightsCount.text = neuro.fightsCount.toString()
            var fightsCount = binding.fightsCount
            if (fightsCount.text == "1")
            {
                fightsCount.append(arrayTypeWordsGames[0])
            }
            else if (fightsCount.text == "2" || fightsCount.text == "3" || fightsCount.text =="4") {
                fightsCount.append(arrayTypeWordsGames[1])
            }
            else {
                fightsCount.append(arrayTypeWordsGames[2])
            }
            var content = binding.positionValue
            if (neuro?.position?.Name == "GPT-4" || neuro?.position?.Name == "Gemini") {
                content.append(arrayTypeWords[0])
            }
            else if (neuro?.position?.Name == "MusicFX") {
                content.append(arrayTypeWords[1])
            }
            else if (neuro?.position?.Name == "MidJourney") {
                content.append(arrayTypeWords[2])
            }
            else {
                content.append(arrayTypeWords[3])
            }
            binding.winCount.text = neuro.winCount.toString()
            var winCount = binding.winCount
            if (winCount.text == "1")
            {
                winCount.append(arrayTypeWordsWin[0])
            }
            else if (winCount.text == "2" || winCount.text == "3" || winCount.text =="4") {
                winCount.append(arrayTypeWordsWin[1])
            }
            else {
                winCount.append(arrayTypeWordsWin[2])
            }
            binding.greenCard.text = neuro.greenCardsCount.toString()
            var greenCount = binding.greenCard
            if (greenCount.text == "1")
            {
                greenCount.append(arrayTypeWordsGreenCards[0])
            }
            else if (greenCount.text == "2" ||greenCount.text == "3" || greenCount.text =="4") {
                greenCount.append(arrayTypeWordsGreenCards[1])
            }
            else {
                greenCount.append(arrayTypeWordsGreenCards[2])
            }
            setStat(binding.upgradesCount, neuro.upgradesCount, R.plurals.plurals_upgrades)
            binding.yellowCard.text = neuro.yellowCardCount.toString()
            var yellowCards = binding.yellowCard
            if (yellowCards.text == "1")
            {
                yellowCards.append(arrayTypeWordsYellowCards[0])
            }
            else if (yellowCards.text == "2" ||yellowCards.text == "3" || yellowCards.text =="4") {
                yellowCards.append(arrayTypeWordsYellowCards[1])
            }
            else {
                yellowCards.append(arrayTypeWordsYellowCards[2])
            }
            Glide
                .with(this)
                .load(neuro?.photoUrl)
                .circleCrop()
                .into(binding.icon)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.card_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                shareNeuroText()
                true
            }
            else -> true
        }
    }
    private fun shareNeuroText() {

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,
                """
                    Название: ${neuro?.position?.Name}
                    Компания: ${neuro?.company}
                    Задачи нейросети: ${neuro?.neuroTask}
                    Скорость генерации контента: ${binding.positionValue.text}
                    Место: ${neuro?.number}
                    ${neuro?.photoUrl}
                """.trimIndent())
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
    private fun setStat(view: TextView, count: Int, @PluralsRes plural: Int) {
        view.text =
            resources.getString(
                R.string.count_pattern,
                count,
                resources.getQuantityString(plural, count)
            )
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val CARD_NEURO_KEY = "NEURO"
    }
}



private fun Int?.append(s: String) {

}
