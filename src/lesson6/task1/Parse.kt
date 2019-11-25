@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import java.lang.Exception

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String? {
    val list = str.split(" ")
    var res = ""
    val element = list[0].toInt()
    if (list.size == 3 && element in 1..31) {
        if (list[1] == "января") res = String.format("%02d", element) + ".01."
        else if (list[1] == " февряля" && element <= 29) res = String.format("%02d", element) + ".02." + list[2]
        else if (list[1] == "марта") res = String.format("%02d", element) + ".03." + list[2]
        else if (list[1] == "апреля" && element <= 30) res = String.format("%02d", element) + ".04." + list[2]
        else if (list[1] == "мая") res = String.format("%02d", element) + ".05." + list[2]
        else if (list[1] == "июня" && element <= 30) res = String.format("%02d", element) + ".06." + list[2]
        else if (list[1] == "июля") res = String.format("%02d", element) + ".07." + list[2]
        else if (list[1] == "августа") res = String.format("%02d", element) + "08." + list[2]
        else if (list[1] == "сентября" && element <= 30) res = String.format("%02d", element) + ".09." + list[2]
        else if (list[1] == "октября") res = list[0] + ".10." + list[2]
        else if (list[1] == "ноября" && element <= 30) res = list[0] + ".11." + list[2]
        else if (list[1] == "декабря") res = list[0] + ".12." + list[2]
    } else return ""
    return res
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val list = digital.split(".")
    val res: String
    try {
        val element = list[0].toInt()
        if (list.size == 3 && list[0].toInt() in 1..31) {
            res = when {
                list[1] =="01" -> String.format("%s", element) + " января " + list[2]
                list[1] =="02" && element <= 28 -> String.format("%s", element) + " февраля " + list[2]
                list[1] =="03" -> String.format("%s", element) + " марта " + list[2]
                list[1] == "04" && element <= 30 -> String.format("%s", element) + " апреля " + list[2]
                list[1] == "05" -> String.format("%s", element) + " мая " + list[2]
                list[1] == "06" && element <= 30 -> String.format("%s", element) + " июня " + list[2]
                list[1] == "07" -> String.format("%s", element) + " июля " + list[2]
                list[1] == "08" -> String.format("%s", element) + " августа " + list[2]
                list[1] == "09" && element <= 30 -> String.format("%s", element) + " сентября " + list[2]
                list[1] == "10" -> String.format("%s", element) + " октября " + list[2]
                list[1] == "11" && element <= 30 -> String.format("%s", element) + " ноября " + list[2]
                list[1] == "12" -> String.format("%s", element) + " декабря " + list[2]
                else -> ""
            }
        } else res = ""
        return res
    } catch (e: Exception) {
        return ""
    }
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String {
    val list = phone.split("").toMutableList()
    return try {
        for (i in 0 until list.size) {
            if (list[0] == "+") continue
            if (list[i] == "-" || list[i] == "(" || list[i] == ")" || list[i] == " ") {
                list.removeAt(i)
            } else if (list[i].toInt() in 0..9) continue
        }
        list.joinToString(separator = "")
    } catch (e: Exception) {
        ""
    }
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    val list = jumps.split(" ")
    var max = 0
    return try {
        for (i in 0 until list.size) {
            if (list[i] == "-" || list[i] == "%") continue
            else if (list[i].toInt() > max) max = list[i].toInt()
        }
        if (max != 0) max
        else -1
    } catch (e: Exception) {
        -1
    }


}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int = TODO()

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int = TODO() /*
    val list = expression.split(" ").toMutableList()
    var sum = 0
    try {
        for (i in 0..list.size - 2) {
            if (i % 2 == 0) {
                if (list[i].toInt() in 0..9) {
                    val res = list[i]
                }
            } else
                if (list[i] == "+" || list[i] == "-") {
                    if (list[i] == "+")

                }
        }
    } catch (e: IllegalArgumentException) {
        ""
    } */

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int = TODO()

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String = TODO()

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int = TODO()

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {
    require(commands.matches(Regex("""[+\-\]\[\s><]*""")))
    val list = mutableListOf<Int>()
    var k = cells / 2
    var l = 0
    for (i in 0 until commands.length) {
        if (commands[i] == '[') l += 1
        else if (commands[i] == ']') l -= 1
    }
    if (l != 0) throw IllegalArgumentException()
    for (i in 0 until cells) list.add(0)
    var i = 0
    var index = 0
    while (i != commands.length && index != limit) {
        if (k == -1 || k == list.size) throw IllegalStateException()
        when (commands[i]) {
            '+' -> list[k] += 1
            '-' -> list[k] -= 1
            '>' -> k += 1
            '<' -> k -= 1
            ' ' -> list[k]
            '[' -> {
                if (list[k] == 0) {
                    var c = 1
                    while (c != 0) {
                        i += 1
                        if (commands[i] == '[') c += 1
                        else if (commands[i] == ']') c -= 1
                    }
                }
            }
            ']' -> {
                if (list[k] != 0) {
                    var c = 1
                    while (c != 0) {
                        i -= 1
                        if (commands[i] == '[') c -= 1
                        else if (commands[i] == ']') c += 1
                    }
                }
            }
        }
        i += 1
        index += 1
    }
    return list
}
