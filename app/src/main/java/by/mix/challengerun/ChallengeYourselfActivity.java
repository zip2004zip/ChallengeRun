package by.mix.challengerun;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ChallengeYourselfActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent;
    private int seconds = 0;
    private boolean running;
//    private boolean wasRunning; // хранение информации о том, работал ли секундомер перед вызовом метода onStop()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_yourself);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            //  wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

/*    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }*/

    // сохранение показаний перед уничтожением секундомера
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        //   savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

    //Запустить секундомер при щелчке на кнопке Start.
    public void onClickStart(View view) {
        running = true;
    }

    //Остановить секундомер при щелчке на кнопке Stop.
    public void onClickStop(View view) {
        running = false;
    }

    //Обнулить секундомер при щелчке на кнопке Reset.
    public void onClickReset(View view) {
        running = false;
        seconds = 0;
    }

    //Обновление показаний таймера
    private void runTimer() {
        final TextView timeView = (TextView) findViewById(R.id.textView_RuntimeRecorder);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textView_Map:
                intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
                break;
        }
    }
}