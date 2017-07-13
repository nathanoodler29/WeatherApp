package prototype.android.darkweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ConnectionLost extends AppCompatActivity {

    /**
     * This activity just displays an image and text for when internet connect is lost.
     * Or if the Dark sky api is down and con't be connected too.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_lost);


    }
}
