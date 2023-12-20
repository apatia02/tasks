package com.example.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import com.example.customview.databinding.QuizWidgetBinding

class QuizWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val binding = QuizWidgetBinding.inflate(LayoutInflater.from(context), this)


    init {
        val binder = QuizBinder()
        binder.bind(this)
        binder.emitEvent(QuizEvent.LoadEvent)
        with(binding){
            refreshButton.setOnClickListener {
                binder.emitEvent(QuizEvent.LoadEvent)
            }
            newQuestionBtn.setOnClickListener {
                binder.emitEvent(QuizEvent.LoadEvent)
            }
        }
    }

    fun renderState(state: QuizState) {
        when (state) {
            is QuizState.Loading -> showLoadingState()
            is QuizState.Error -> showErrorState()
            is QuizState.Data -> showDataState(state.question)
        }
    }

    private fun showLoadingState() {
        with(binding) {
            progressBar.visibility = VISIBLE
            errorText.visibility = GONE
            questionText.visibility = GONE
            refreshButton.visibility = GONE
            newQuestionBtn.visibility = GONE
        }
    }

    private fun showErrorState() {
        with(binding) {
            progressBar.visibility = GONE
            errorText.visibility = VISIBLE
            questionText.visibility = GONE
            refreshButton.visibility = VISIBLE
            newQuestionBtn.visibility = GONE
        }
    }

    private fun showDataState(question: String) {
        with(binding) {
            progressBar.visibility = GONE
            errorText.visibility = GONE
            questionText.visibility = VISIBLE
            refreshButton.visibility = GONE
            newQuestionBtn.visibility = VISIBLE
            questionText.text = question
        }
    }
}