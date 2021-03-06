package Games;

import Functions.Iterationable;
import Users.User;

import java.util.Random;

//Карты - 6 7 8 9 10
// Валет - 2, Дама - 3, Король - 4, Туз - 11
public class Score21 implements Iterationable {
    private int score = 0;
    private int sumBot;
    private int sumPlayer;
    private Random rnd;
    private boolean first = true;
    private boolean firstForBot = true;

    public Score21()
    {
        rnd = new Random();
    }

    @Override
    public String iteration(String text) {
        String message = "";

        if (first) {
            int number = rnd.nextInt(11);
            int numberSecond = rnd.nextInt(11);
            sumPlayer = number + numberSecond;
            message += "Первые две карты: " + number + " и " + numberSecond;
            message += "\nЕще?";
            first = false;
            return message;
        }

        if (text.equals("+")) {
            message = positiveAnswer();
        }

        if (text.equals("-")) {
            message = negativeAnswer();
        }

        if (message.equals(""))
            message += "Введите + или -";

        return message;
    }

    private String negativeAnswer() {
        String message = "Хорошо, теперь карты беру я.";

        int firstNumber = rnd.nextInt(11);
        int secondNumber = rnd.nextInt(11);
        sumBot = firstNumber + secondNumber;
        message += "\nМои карты: " + firstNumber + " и " + secondNumber;
        message += "\nВсего: " + sumBot;
        first = true;

        if (sumBot > 21 || (sumBot < sumPlayer)) {
            score += 3;
            message += "\nЯ проиграл :(\n";
            message += "\nТы заработал 3 очка";
            return message;
        }

        if ((sumBot == 21 && sumPlayer == 21) || (sumBot == sumPlayer))
        {
            score += 1;
            message += "\nНичья, ты заработал 1 очко";
            return message;
        }

        if ((sumBot == 21 && sumPlayer < 21)
                || (sumBot > sumPlayer))
        {
            message += "\nЯ выиграл!";
            return message;
        }

        return message;
    }

    @Override
    public String start(User user) {
        return "Добро пожаловать в игру 21 или ОЧКО!\n" +
                "Правила просты: я даю тебе две карты. Твоя задача набрать 21 очко.\n" +
                "Если ты набираешь 21 очко - ты выигрываешь, если больше - проигрываешь.\n" +
                "А если набираешь меньше 21, то сравниваем с моими картами. И выигрывает тот - у кого больше.\n" +
                "Чтобы попросить еще карту - напиши: +, если хочешь остановиться: -\n" +
                "Отправь любое сообщение, чтобы начать.\n";
    }

    @Override
    public String getStartCommand() {
        return "/start2";
    }

    @Override
    public String exit() {
        return "Всего очков набрано: " + score;
    }

    @Override
    public int getScores() {
        return score;
    }

    private String positiveAnswer()
    {
        String message = "";

        int number = rnd.nextInt(11);
        sumPlayer += number;
        message += "Выпало число: " + number;
        message += "\nСумма карт: " + sumPlayer;

        if (sumPlayer > 21)
        {
            message += "\nСумма карт больше 21, вы проиграли :(\n" +
                    "если хотите начать заного, напишите любое сообщение.\n" +
                    "Вы заработали очков: " + score;
            first = true;
            return message;
        }

        if (sumPlayer == 21)
        {
            score += 3;
            first = true;
            message += "\nВы выиграли!" +
                    "\n Вы заработали очков: " + score;
            return message;
        }

        if (sumPlayer < 21)
        {
            message += "\nЕще?";
            return message;
        }

        return message;
    }
}
