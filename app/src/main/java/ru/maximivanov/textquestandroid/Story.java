package ru.maximivanov.textquestandroid;

public class Story {
    public Situation current_situation;

    class Situation {
        Situation[] direction;
        String text;
        int dHealth, dDamage, dMoney;
        int isBattle; // число отличное от нуля, показывает уровень противника
        public Situation (String text, int variants, int dh,int dd,int dm, int isBattle) {
            this.text=text;
            dHealth = dh;
            dDamage = dd;
            dMoney = dm;
            this.isBattle = isBattle;
            direction = new Situation[variants];
        }
    }

    Story() {
        int first_monster_level = 1;
        int second_monster_level = 3;
        int boss_level = 5;
        Situation start_story = new Situation(Texts.texts[0],
                2, 0, 0, 0, 0);
        start_story.direction[0] = new Situation(Texts.texts[1], // 0
                2, 0, 10, 0, first_monster_level);
        start_story.direction[1] = new Situation(Texts.texts[1], // 1
                2, 0, 0, 0, first_monster_level);
        start_story.direction[0].direction[0] = new Situation(Texts.texts[2], // 00
                2, 0, 0, 0, 0);
        start_story.direction[0].direction[1] = new Situation(Texts.texts[3], // 01
                4, 0, 0, 0, 0);
        start_story.direction[0].direction[1].direction[1] = new Situation(Texts.texts[4], // 011
                2, 5, 0,0, second_monster_level);
        start_story.direction[0].direction[1].direction[0] = new Situation(Texts.texts[5], // 010
                0, -1000,0,0,0);
        start_story.direction[0].direction[1].direction[2] = new Situation(Texts.texts[5], // 012
                0, -1000,0,0,0);
        start_story.direction[0].direction[1].direction[3] = new Situation(Texts.texts[5], // 013
                0, -1000,0,0,0);
        start_story.direction[1].direction[1] = new Situation(Texts.texts[3], // 11
                4, 0, 0,0,0);
        start_story.direction[1].direction[1].direction[1] = new Situation(Texts.texts[4], // 111
                2, 5, 0,0, second_monster_level);
        start_story.direction[1].direction[1].direction[0] = new Situation(Texts.texts[5], // 110
                0, -1000,0,0,0);
        start_story.direction[1].direction[1].direction[2] = new Situation(Texts.texts[5], // 112
                0, -1000,0,0,0);
        start_story.direction[1].direction[1].direction[3] = new Situation(Texts.texts[5], // 113
                0, -1000,0,0,0);
        start_story.direction[0].direction[0].direction[0] = new Situation(Texts.texts[6], // 000
                2, 0, 0,0, 0);
        start_story.direction[0].direction[0].direction[1] = new Situation(Texts.texts[3], // 001
                4, 0, 0, 0, 0);
        start_story.direction[0].direction[0].direction[1].direction[1] = new Situation(Texts.texts[4], // 0011
                2, 5, 0,0, second_monster_level);
        start_story.direction[0].direction[0].direction[1].direction[0] = new Situation(Texts.texts[5], // 0010
                0, -1000,0,0,0);
        start_story.direction[0].direction[0].direction[1].direction[2] = new Situation(Texts.texts[5], // 0012
                0, -1000,0,0,0);
        start_story.direction[0].direction[0].direction[1].direction[3] = new Situation(Texts.texts[5], // 0013
                0, -1000,0,0,0);
        start_story.direction[0].direction[0].direction[0].direction[0] = new Situation(Texts.texts[7], // 0000
                0, -100, 0, 0, 0);
        start_story.direction[0].direction[0].direction[0].direction[1] = new Situation(Texts.texts[8], // 0001
                2, 0, 5, 0, boss_level);
        start_story.direction[1].direction[1].direction[1].direction[1] = new Situation(Texts.texts[10], // 1111
                1, 0, 0, 0, boss_level);
        start_story.direction[0].direction[0].direction[1].direction[1].direction[0] = new Situation(Texts.texts[9], // 00110
                1, 0, 0, 20, 0);
        start_story.direction[0].direction[0].direction[1].direction[1].direction[1] = new Situation(Texts.texts[10], // 00111
                1, 0, 0, 0, boss_level);
        start_story.direction[0].direction[0].direction[1].direction[1].direction[0].direction[0] = new Situation(Texts.texts[10], // 001100
                1, 0, 0, 0, boss_level);
        start_story.direction[0].direction[1].direction[1].direction[0] = new Situation(Texts.texts[9], // 0110
                1, 0, 0, 20, 0);
        start_story.direction[0].direction[1].direction[1].direction[1] = new Situation(Texts.texts[10], // 0111
                1, 0, 0, 0, boss_level);
        start_story.direction[0].direction[1].direction[1].direction[0].direction[0] = new Situation(Texts.texts[10], // 01100
                1, 0, 0, 0, boss_level);
        start_story.direction[0].direction[1].direction[1].direction[1].direction[0] = new Situation(Texts.texts[11], // 01110
                0, 0, 0, 0, 0);
        start_story.direction[0].direction[1].direction[1].direction[0].direction[0].direction[0] = new Situation(Texts.texts[11], // 011000
                0, 0, 0, 0, 0);
        start_story.direction[0].direction[0].direction[1].direction[1].direction[1].direction[0] = new Situation(Texts.texts[11], // 001110
                0, 0, 0, 0, 0);
        start_story.direction[0].direction[0].direction[1].direction[1].direction[0].direction[0].direction[0] = new Situation(Texts.texts[11], // 011000
                0, 0, 0, 0, 0);
        start_story.direction[0].direction[0].direction[0].direction[1].direction[0] = new Situation(Texts.texts[11], // 00010
                0, 0, 0, 0, 0);
        current_situation = start_story;
    }

    public void go(int num) {
        if (num <= current_situation.direction.length)
            current_situation = current_situation.direction[num];
    }

    public boolean isEnd() {
        return current_situation.direction.length == 0;
    }

    static class Texts {
        public static final String[] texts;
        public static final int amountOfTexts = 12;
        static {
            texts = new String[amountOfTexts];
            texts[0] = "Вы очутились в какой-то тёмной комнате, хорошо, что " +
                    "хоть дверь есть.\n" +
                    "Рядом с Вами лежит меч\n" +
                    "(1) Взять меч, идти дальше;\n" +
                    "(2) И без меча обойдусь";
            texts[1] = "Монстр!!!\n";
            texts[2] = "Вы победили своего первого монстра!\n" +
                    "Перед вами две двери. Из одной из них вы слышите шум.\n" +
                    "Возможно это очередной монстр, а может быть и нет...\n" +
                    "(1) Пойти на шум;\n" +
                    "(2) Пожалуй лучше пойду во вторую комнату.";
            texts[3] = "Вы попали в пустую комнату. Перед вами дверь.\n" +
                    "Но всё не так просто. На двери написано:\n" +
                    "'День спит, ночь глядит,\n" +
                    "Утром умирает, другой сменяет'\n" +
                    "Судя по всему, вам надо разгадать загадку.\n" +
                    "(1)Месяц\n" +
                    "(2)Свечи\n" +
                    "(3)Ветер\n" +
                    "(4)Вампир";
            texts[4] = "Вы ответили правильно, дверь перед вами открылась. Однако расслабляться рано.\n" +
                    "Перед вами очередной монстр, который, скорее всего, сильнее предыдущего";
            texts[5] = "Вам на голову прилетел огромный камень. Судя по всему, загадку вы не разгадали";
            texts[6] = "Со словами 'Спасибо, герой!' вас встретил какой-то старик.\n" +
                    "За убийство монстра он предлагает вам свою помощь - зелье, повышающее все характеристики\n" +
                    "(1) Принять;\n" +
                    "(2) Попросить лучше меч, который вы случайно заметили.";
            texts[7] = "'Чудо-зелье' оказалось ядом. Старик забрал все ваши деньги, пока вы мучительно умирали";
            texts[8] = "Старик, кажется, обиделся, однако дал меч. Вы идете дальше...\n" +
                    "И видите перед собой громадного монстра!!! Это очень серьезный противник!";
            texts[9] = "Как оказалось, этот монстр охранял сундук.\n" +
                    "(1) Взять деньги (20 монет) и пойти дальше";
            texts[10] = "Вы идете дальше... И натыкаетесь на огромного монстра!!!";
            texts[11] = "Вы победили этого ужасного монстра! У него на шее висел ключ, которым вы легко открыли \n" +
                    "следующую дверь. Однако за ней нет никаких монстров, вы видите солнечный свет!";
        }
    }
}
