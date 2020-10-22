package ve.develop.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var lastNumeric = false
    private var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
        tvInput.append((view as Button).text)
        lastNumeric = true
    }

    fun onOperator(view: View) {
        if (lastNumeric && !isOperatorAdded(tvInput.text.toString())){
            tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot) {
            tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onClear(view: View) {
        tvInput.text = ""
        lastNumeric = false
        lastDot = false
    }

    fun onEqual(view: View) {
        var tvValue = tvInput.text.toString()
        var prefix = ""

        if (tvValue.startsWith("-")){
            prefix = "-"
            tvValue = tvValue.substring(1)
        }

        if (tvValue.contains("-")){
            val splitValue = tvValue.split("-")
            var firstValue = splitValue[0]
            var secondValue = splitValue[1]

            if (!prefix.isEmpty()){
                firstValue = prefix+firstValue
            }
            tvInput.text = (firstValue.toDouble()-secondValue.toDouble()).toString()
        }

        if (tvValue.contains("+")){
            val splitValue = tvValue.split("+")
            var firstValue = splitValue[0]
            var secondValue = splitValue[1]

            if (!prefix.isEmpty()){
                firstValue = prefix+firstValue
            }
            tvInput.text = (firstValue.toDouble()+secondValue.toDouble()).toString()
        }

        if (tvValue.contains("/")){
            val splitValue = tvValue.split("/")
            var firstValue = splitValue[0]
            var secondValue = splitValue[1]

            if (!prefix.isEmpty()){
                firstValue = prefix+firstValue
            }
            tvInput.text = (firstValue.toDouble()/secondValue.toDouble()).toString()
        }
        if (tvValue.contains("*")){
            val splitValue = tvValue.split("*")
            var firstValue = splitValue[0]
            var secondValue = splitValue[1]

            if (!prefix.isEmpty()){
                firstValue = prefix+firstValue
            }
            tvInput.text = (firstValue.toDouble()*secondValue.toDouble()).toString()
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }

}