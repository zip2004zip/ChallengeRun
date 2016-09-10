package by.mix.challengerun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChallengeRunActivity extends AppCompatActivity implements View.OnClickListener {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_run);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_challenge_yourself:
                intent = new Intent(this, ChallengeYourselfActivity.class);
                startActivity(intent);
                break;
            case R.id.button_challenge_friend:
                intent = new Intent(this, ChallengeFriendActivity.class);
                intent.putExtra("fff", 2);
                startActivity(intent);

                break;
            case R.id.button_challenge_team:
                intent = new Intent(this, LocationGPSActivity.class);
                startActivity(intent);
                break;
        }
    }
}

// карты гугл API
// https://developers.google.com/maps/documentation/android-api/?csw=1

// http://www.enterra.ru/blog/gps-android/ Работа с GPS на Android
// http://www.darkraha.com/rus/mobile/android/api/location.php методы класса Location на русском

// формулы измерения расстояния координат
// http://wiki.gis-lab.info/w/%D0%92%D1%8B%D1%87%D0%B8%D1%81%D0%BB%D0%B5%D0%BD%D0%B8%D0%B5_%D1%80%D0%B0%D1%81%D1%81%D1%82%D0%BE%D1%8F%D0%BD%D0%B8%D1%8F_%D0%B8_%D0%BD%D0%B0%D1%87%D0%B0%D0%BB%D1%8C%D0%BD%D0%BE%D0%B3%D0%BE_%D0%B0%D0%B7%D0%B8%D0%BC%D1%83%D1%82%D0%B0_%D0%BC%D0%B5%D0%B6%D0%B4%D1%83_%D0%B4%D0%B2%D1%83%D0%BC%D1%8F_%D1%82%D0%BE%D1%87%D0%BA%D0%B0%D0%BC%D0%B8_%D0%BD%D0%B0_%D1%81%D1%84%D0%B5%D1%80%D0%B5