package com.example.codebreak.data

import com.example.codebreak.R

object MissionsData {
    val missions = listOf(
        // МИССИЯ 1 — ТЕМА: БАЗОВЫЙ IF
        Mission(
            id = 1,
            district = "Транзитный узел",
            title = "Сбой светофора",
            duration = "3 минуты",
            requiredKeys = 0,
            imageRes = R.drawable.img_traffic_light,
            questions = listOf(
                Question(
                    id = 101,
                    title = "Урок 1: Конструкция if",
                    bitHint = "LOGIC_LECTURE\n" +
                            "📖 ПОЛНАЯ ХАКЕРСКАЯ ЛЕКЦИЯ:\n" +
                            "Компьютеры принимают решения с помощью команды 'if' (в переводе — 'если'). Она оценивает условие, написанное после неё. Если это условие правдиво (выдает True), компьютер заходит внутрь ветки 'if' и выполняет вложенные команды.\n\n" +
                            "⚠️ СИНТАКСИС ПРАВИЛ:\n" +
                            "1. После условия ОБЯЗАТЕЛЬНО ставится двоеточие ':'. Оно говорит Python, что дальше идет тело проверки.\n" +
                            "2. Все команды внутри 'if' пишутся с ОТСТУПОМ в 4 пробела (или 1 нажатие Tab). Если отступа нет, программа вылетит с ошибкой!\n\n" +
                            "Пример:\n" +
                            "if pedestrian_waiting == True:\n" +
                            "    light = \"green\"  # Выполнится ТОЛЬКО при True\n\n" +
                            "Если переменная pedestrian_waiting станет ложной (False), Python полностью проигнорирует эту строчку и пойдет дальше.",
                    codeSnippet = "pedestrian_waiting = True\n\nif pedestrian_waiting == True:\n    light = \"green\"\nelse:\n    light = \"red\"",
                    questionText = "Пешеход подошел к переходу и ждет (pedestrian_waiting = True). Какая ветка сработает в коде?",
                    options = listOf("Ветка if (light = \"green\")", "Ветка else (light = \"red\")", "Никакая"),
                    correctAnswerIndex = 0,
                    successExplanation = "Если pedestrian_waiting — True, то выполняется ветка if и светофор становится green. Люди могут идти!",
                    errorExplanation = "not меняет True на False. Выполняется else, поэтому свет остаётся красным. Измени условие и попробуй снова."
                )
            )
        ),
        // МИССИЯ 2 — ТЕМА: РАЗВИЛКА IF-ELSE
        Mission(
            id = 2,
            district = "Техноквартал",
            title = "Робот на проводе",
            duration = "4 минуты",
            requiredKeys = 1,
            imageRes = R.drawable.img_traffic_light,
            questions = listOf(
                Question(
                    id = 201,
                    title = "Урок 2: Развилка if-else",
                    bitHint = "LOGIC_LECTURE\n" +
                            "📖 ПОЛНАЯ ХАКЕРСКАЯ ЛЕКЦИЯ:\n" +
                            "Одиночный 'if' выполняет код только при успехе. Но что делать, если условие ложно (False)? Для этого используют блок 'else' (в переводе — 'иначе'). Это наш железный запасной план.\n\n" +
                            "⚠️ СИНТАКСИС ПРАВИЛ:\n" +
                            "1. Блок 'else:' пишется на том же уровне вертикали, что и 'if', строго без отступов.\n" +
                            "2. После слова 'else' ЗАПРЕЩЕНО писать новые условия. Просто ставим двоеточие ':' и переходим на новую строку.\n" +
                            "3. Действия внутри 'else' также требуют отступа в 4 пробела.\n\n" +
                            "Пример:\n" +
                            "if путь_свободен:\n" +
                            "    move_forward()  # Если True\n" +
                            "else:\n" +
                            "    turn_left()     # Если False (запасной план)\n\n" +
                            "Компьютер НИКОГДА не выполнит оба действия вместе. Он выберет строго одну ветку.",
                    codeSnippet = "steps = 0\nfor step in range(3):\n    steps += 1\n\nprint(steps)",
                    questionText = "Сколько раз выполнится цикл range(3)?",
                    options = listOf("2 раза", "3 раза", "4 раза"),
                    correctAnswerIndex = 1,
                    successExplanation = "Функция range(3) генерирует 3 итерации: 0, 1 и 2. Цикл сделает ровно 3 шага.",
                    errorExplanation = "Указано неверное число итераций. Робот не дойдёт до зарядки."
                )
            )
        ),
        // МИССИЯ 3 — ТЕМА: ОПЕРАТОРЫ СРАВНЕНИЯ
        Mission(
            id = 3,
            district = "Серверная башня",
            title = "Код доступа",
            duration = "5 минут",
            requiredKeys = 2,
            imageRes = R.drawable.img_traffic_light,
            questions = listOf(
                Question(
                    id = 301,
                    title = "Урок 3: Операторы сравнения",
                    bitHint = "LOGIC_LECTURE\n" +
                            "📖 ПОЛНАЯ ХАКЕРСКАЯ ЛЕКЦИЯ:\n" +
                            "В условиях 'if' мы постоянно сравниваем переменные и числа. Для этого в Python есть набор специальных операторов:\n" +
                            "• '==' — строгое равенство (не путай с одним '=', это создание переменной!)\n" +
                            "• '!=' — не равно\n" +
                            "• '>' и '<' — больше и меньше\n" +
                            "• '>=' и '<=' — больше или равно / меньше или равно\n\n" +
                            "⚠️ ХАКЕРСКАЯ ОШИБКА (БАГ):\n" +
                            "Если написать 'if energy > 50:', то при значении ровно 50 условие выдаст False, ведь 50 не больше 50! Защита отключится. Чтобы программа сработала при 50, нужно использовать комбинированный знак '>=', который проверяет и равенство, и превышение одновременно.",
                    codeSnippet = "codes = [101, 202, 303]\nmain_code = codes[0]\n\nprint(main_code)",
                    questionText = "Чему равно значение codes[0]?",
                    options = listOf("101", "202", "303"),
                    correctAnswerIndex = 0,
                    successExplanation = "В Python нумерация элементов начинается с 0, поэтому codes[0] равен 101.",
                    errorExplanation = "Индекс 1 вернет 202. Первый элемент всегда находится под индексом 0."
                )
            )
        )
    )
}
