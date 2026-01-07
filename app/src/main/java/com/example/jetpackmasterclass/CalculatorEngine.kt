package com.example.jetpackmasterclass


object CalculatorEngine {
        fun parse(expression: String): Triple<Double, String, Double>? {
            val operatorRegex = Regex("[+\\-x/]")
            val match = operatorRegex.find(expression) ?: return null

            val operands = expression.split(operatorRegex)
            if (operands.size != 2) return null

            val op1 = operands[0].toDoubleOrNull() ?: return null
            val op2 = operands[1].toDoubleOrNull() ?: return null
            return Triple(op1, match.value, op2)

        }

        fun calculate(expression: String): Double? {
            val (op1, evaluator, op2) = parse(expression) ?: return null

            if (evaluator == "/" && op2 == 0.0) return null

            return when (evaluator) {
                "+" -> op1 + op2
                "-" -> op1 - op2
                "x" -> op1 * op2
                "/" -> op1 / op2
                else -> null
            }
        }
    }
