package ro.pub.cs.systems.eim.practicaltest01var07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ro.pub.cs.systems.eim.practicaltest01var07.general.Constants;

public class PracticalTest01Var07MainActivity extends AppCompatActivity {
    private EditText topLeftEditText;
    private EditText bottomLeftEditText;
    private EditText topRightEditText;
    private EditText bottomRightEditText;
    private Button setButton;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.set_button:
                    if (topLeftEditText.getText().length() > 0 &&
                            topRightEditText.getText().length() > 0 &&
                            bottomLeftEditText.getText().length() > 0 &&
                            bottomRightEditText.getText().length() > 0) {
                        int topLeftNumber = Integer.parseInt(topLeftEditText.getText().toString());
                        int topRightNumber = Integer.parseInt(topRightEditText.getText().toString());
                        int bottomLeftNumber = Integer.parseInt(bottomLeftEditText.getText().toString());
                        int bottomRightNumber = Integer.parseInt(bottomRightEditText.getText().toString());
                        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var07SecondaryActivity.class);
                        intent.putExtra(Constants.TOP_LEFT_NUMBER, topLeftNumber);
                        intent.putExtra(Constants.TOP_RIGHT_NUMBER, topRightNumber);
                        intent.putExtra(Constants.BOTTOM_LEFT_NUMBER, bottomLeftNumber);
                        intent.putExtra(Constants.BOTTOM_RIGHT_NUMBER, bottomRightNumber);
                        startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
                    }
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var07_main);

        topLeftEditText = (EditText) findViewById(R.id.top_left_edit_text);
        topRightEditText = (EditText) findViewById(R.id.top_right_edit_text);
        bottomLeftEditText = (EditText) findViewById(R.id.bottom_left_edit_text);
        bottomRightEditText = (EditText) findViewById(R.id.bottom_right_edit_text);
        setButton = (Button) findViewById(R.id.set_button);
        setButton.setOnClickListener(buttonClickListener);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(Constants.TOP_LEFT_NUMBER)) {
                topLeftEditText.setText(savedInstanceState.getString(Constants.TOP_LEFT_NUMBER));
            }
            if (savedInstanceState.containsKey(Constants.TOP_RIGHT_NUMBER)) {
                topRightEditText.setText(savedInstanceState.getString(Constants.TOP_RIGHT_NUMBER));
            }
            if (savedInstanceState.containsKey(Constants.BOTTOM_LEFT_NUMBER)) {
                bottomLeftEditText.setText(savedInstanceState.getString(Constants.BOTTOM_LEFT_NUMBER));
            }
            if (savedInstanceState.containsKey(Constants.BOTTOM_RIGHT_NUMBER)) {
                bottomRightEditText.setText(savedInstanceState.getString(Constants.BOTTOM_RIGHT_NUMBER));
            }
        }
        intentFilter.addAction(Constants.ACTION);
        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var07Service.class);
        getApplicationContext().startService(intent);
    }

    private IntentFilter intentFilter = new IntentFilter();
    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();

    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[Receiver]", String.valueOf(intent.getIntExtra(Constants.TOP_LEFT_NUMBER, -1)));
            int topLeftNumber = intent.getIntExtra(Constants.TOP_LEFT_NUMBER, -1);
            int topRightNumber = intent.getIntExtra(Constants.TOP_RIGHT_NUMBER, -1);
            int bottomLeftNumber = intent.getIntExtra(Constants.BOTTOM_LEFT_NUMBER, -1);
            int bottomRightNumber = intent.getIntExtra(Constants.BOTTOM_RIGHT_NUMBER, -1);

            topLeftEditText.setText(topLeftNumber);
            topRightEditText.setText(topRightNumber);
            bottomLeftEditText.setText(bottomLeftNumber);
            bottomRightEditText.setText(bottomRightNumber);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var07Service.class);
        stopService(intent);
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(Constants.TOP_LEFT_NUMBER, topLeftEditText.getText().toString());
        savedInstanceState.putString(Constants.TOP_RIGHT_NUMBER, topRightEditText.getText().toString());
        savedInstanceState.putString(Constants.BOTTOM_LEFT_NUMBER, bottomLeftEditText.getText().toString());
        savedInstanceState.putString(Constants.BOTTOM_RIGHT_NUMBER, bottomRightEditText.getText().toString());
    }
}