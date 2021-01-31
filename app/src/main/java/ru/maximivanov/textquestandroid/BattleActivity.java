package ru.maximivanov.textquestandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import static ru.maximivanov.textquestandroid.MainActivity.*;

public class BattleActivity extends Activity {
    private TextView desc;
    private TextView status;
    private Monsters monster;
    public final int RESULT_WIN = 0;
    public final int RESULT_RUN = 1;
    public static final int RESULT_DEATH = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        desc = findViewById(R.id.desc);
        status = findViewById(R.id.status);
        monster = new Monsters(story.current_situation.isBattle);
        desc.setText("(1) Атаковать!\n(2) Попытаться убежать");
        updateStatus();
    }

    private void updateStatus() {
            status.setText("Здоровье монстра: " + monster.health + "; Ваше здоровье: " + hero.health);

            ((LinearLayout) findViewById(R.id.layout)).removeAllViews();
            for (int i = 0; i < 2; i++) {
                Button b = new Button(this);
                b.setText(Integer.toString(i + 1));
                final int buttonId = i;
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        battle(buttonId + 1);
                    }
                });
                ((LinearLayout) findViewById(R.id.layout)).addView(b);
            }
    }

    /*public void exit(final String str) {
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
    }*/

    public void battle(int choice) {
            if (choice == 1) {
                if (hero.damage == 0) {
                    Intent i = new Intent();
                    i.putExtra("death", "Нападать на монстра без оружия было определенно плохой идеей.\n" +
                            "Монстр убил вас, что было ожидаемо.");
                    setResult(RESULT_DEATH, i);
                    finish();
                }
                int monsterDHealth = (int) (Math.random() * hero.damage);
                monster.health -= monsterDHealth;
                desc.setText(desc.getText() + "\nВы нанесли монстру " + monsterDHealth + " единиц урона");
                if (monster.health <= 0) {
                    desc.setText(desc.getText() + "\nВы победили монстра и получили " +
                            monster.money + " монет.\n Урон вашего меча увеличен на " +
                            monster.level * 3);
                    hero.money += monster.money;
                    hero.damage += monster.level * 3;
                    Intent i = new Intent();
                    setResult(RESULT_WIN, i);
                    finish();
                }
                int heroDHealth = (int) (Math.random() * monster.damage);
                hero.health -= heroDHealth;
                desc.setText(desc.getText() + "\nМонстр нанёс вам " + heroDHealth + " единиц урона");
                if (hero.health <= 0) {
                    Intent i = new Intent();
                    i.putExtra("death","Вы умерли. Конец.");
                    setResult(RESULT_DEATH, i);
                    finish();
                }
            }
            else if (choice == 2) {
                if (monster.level >= 5) {
                    Intent i = new Intent();
                    i.putExtra("death","Дверь оказалась закрытой. Монстр вонзил свой меч вам в спину.\n" +
                            "Конец игры");
                    setResult(RESULT_DEATH, i);
                    finish();
                }
                if (Math.random() <= 0.5) {
                    int dH = monster.damage;
                    hero.health -= dH;
                    if (hero.health <= 0) {
                        Intent i = new Intent();
                        i.putExtra("death","Вы умерли. Конец.");
                        setResult(RESULT_DEATH, i);
                        finish();
                    }
                    else {
                        desc.setText(desc.getText() + "\nВам удалось сбежать, но монстр нанёс вам урон в " + dH + " единиц");
                    }
                }
                Intent i = new Intent();
                setResult(RESULT_RUN, i);
                finish();
            }
            updateStatus();
        }
        class Monsters {
        public int level;
        public int health;
        public int damage;
        public int money;
        public Monsters(int level) {
            this.level = level;
            health = (int) (Math.random() * 10 * level) + 5;
            damage = (int) (Math.random() * 10 * level);
            money = (int) (Math.random() * level * 15);
        }
    }
    }

