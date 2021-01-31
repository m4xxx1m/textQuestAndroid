package ru.maximivanov.textquestandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends Activity {
    static Character hero;
    static Story story;
    private TextView desc;
    private TextView status;

    final int REQUEST_CODE_BATTLE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hero = new Character();
        story = new Story();

        desc = findViewById(R.id.desc);
        status = findViewById(R.id.status);

        updateStatus();
    }

    public void exit(final String str) {
        status.setText("Конец игры");
        desc.setText(str);
        ((LinearLayout) findViewById(R.id.layout)).removeAllViews();
        Button b = new Button(this);
        b.setText("Выход");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        ((LinearLayout) findViewById(R.id.layout)).addView(b);
    }

    private void go(int i) {
        story.go(i);
        updateStatus();

        if (story.isEnd() || hero.health <= 0) {
            if (story.current_situation.text.equals(Story.Texts.texts[11])) {
                exit(Story.Texts.texts[11] + "Вы выбрались из подземелья, заработав при этом " +
                        hero.money + " монет!\n\n\n");
                return;
            }
            exit(story.current_situation.text);
        }
    }

    private void updateStatus() {

        hero.add(story.current_situation.dHealth, story.current_situation.dDamage,
                story.current_situation.dMoney);

        status.setText("Здоровье: " + hero.health + ". Урон: " + hero.damage +
                        ". Деньги: " + hero.money);
        desc.setText(story.current_situation.text);
        ((LinearLayout) findViewById(R.id.layout)).removeAllViews();

        if (story.current_situation.isBattle != 0) {
            Intent intent = new Intent(MainActivity.this, BattleActivity.class);
            startActivityForResult(intent, REQUEST_CODE_BATTLE);
            return;
        }

        for (int i = 0; i < story.current_situation.direction.length; i++) {
            Button b = new Button(this);
            b.setText(Integer.toString(i + 1));
            final int buttonId = i;

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    go(buttonId);
                }
            });
            ((LinearLayout) findViewById(R.id.layout)).addView(b);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == BattleActivity.RESULT_DEATH) {
            exit(data.getStringExtra("death"));
        }
        else{
            go(resultCode);
        }

    }

    class Character {
        public int health;
        public int damage;
        public int money;

        public Character() {
            health = 25;
            damage = 0;
            money = 0;
        }

        void add(int dH, int dD, int dM) {
            health += dH;
            damage += dD;
            money += dM;
        }
    }



}