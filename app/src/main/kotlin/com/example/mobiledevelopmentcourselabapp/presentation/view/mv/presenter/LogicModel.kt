package com.example.mobiledevelopmentcourselabapp.presentation.view.mv.presenter

// должна располагаться где-то отдельно, тут для примера
object LogicModel {
    fun calculate(h: Int, w: Int) :String {
        val result = w * 1.0 / (h / 100.0 * h / 100)
        return "${String.format("%.2f", result)}, ${getVerdict(result)}"
    }

    private fun getVerdict(index: Double): String {
        return when (index) {
            in (0.0..16.0) -> "Выраженный дефицит массы тела"
            in (16.0..18.5) -> "Недостаточная (дефицит) масса тела"
            in (18.5..25.0) ->	"Норма"
            in (25.0..30.0) ->	"Избыточная масса тела (предожирение)"
            in (30.0..35.0) ->	"Ожирение первой степени"
            in (35.0..40.0) ->	"Ожирение второй степени"
            in (40.0..Double.MAX_VALUE) -> "Ожирение третьей степени (морбидное)"
            else -> "Мои искренние соболезнования с днем Башкортостана"
        }
    }
}