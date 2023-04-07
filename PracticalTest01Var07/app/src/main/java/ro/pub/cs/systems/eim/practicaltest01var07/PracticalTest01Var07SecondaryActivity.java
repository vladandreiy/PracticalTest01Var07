package ro.pub.cs.systems.eim.practicaltest01var07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import ro.pub.cs.systems.eim.practicaltest01var07.general.Constants;

public class PracticalTest01Var07SecondaryActivity extends AppCompatActivity {
    private TextView topLeftTextView;
    private TextView bottomLeftTextView;
    private TextView topRightTextView;
    private TextView bottomRightTextView;
    private Button sumButton, productButton;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int topLeftNumber = Integer.valueOf(topLeftTextView.getText().toString());
            int topRightNumber = Integer.valueOf(topRightTextView.getText().toString());
            int bottomLeftNumber = Integer.valueOf(bottomLeftTextView.getText().toString());
            int bottomRightNumber = Integer.valueOf(bottomRightTextView.getText().toString());

            switch (view.getId()) {
                case R.id.sum_button:
                    int sum = topLeftNumber + topRightNumber + bottomLeftNumber + bottomRightNumber;
                    Toast.makeText(getApplication().getBaseContext(), "Sum: " + sum, Toast.LENGTH_LONG).show();
                    break;
                case R.id.product_button:
                    int product = topLeftNumber * topRightNumber * bottomLeftNumber * bottomRightNumber;
                    Toast.makeText(getApplication().getBaseContext(), "Product: " + product, Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var07_secondary);

        topLeftTextView = (TextView) findViewById(R.id.top_left_text_view);
        topRightTextView = (TextView) findViewById(R.id.top_right_text_view);
        bottomLeftTextView = (TextView) findViewById(R.id.bottom_left_text_view);
        bottomRightTextView = (TextView) findViewById(R.id.bottom_right_text_view);
        sumButton = (Button) findViewById(R.id.sum_button);
        sumButton.setOnClickListener(buttonClickListener);
        productButton = (Button) findViewById(R.id.product_button);
        productButton.setOnClickListener(buttonClickListener);

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getExtras().containsKey(Constants.TOP_LEFT_NUMBER)) {
                int topLeftNumber = intent.getIntExtra(Constants.TOP_LEFT_NUMBER, -1);
                topLeftTextView.setText(String.valueOf(topLeftNumber));
            }
            if (intent.getExtras().containsKey(Constants.TOP_RIGHT_NUMBER)) {
                int topRightNumber = intent.getIntExtra(Constants.TOP_RIGHT_NUMBER, -1);
                topRightTextView.setText(String.valueOf(topRightNumber));
            }
            if (intent.getExtras().containsKey(Constants.BOTTOM_LEFT_NUMBER)) {
                int bottomLeftNumber = intent.getIntExtra(Constants.BOTTOM_LEFT_NUMBER, -1);
                bottomLeftTextView.setText(String.valueOf(bottomLeftNumber));
            }
            if (intent.getExtras().containsKey(Constants.BOTTOM_RIGHT_NUMBER)) {
                int bottomRightNumber = intent.getIntExtra(Constants.BOTTOM_RIGHT_NUMBER, -1);
                bottomRightTextView.setText(String.valueOf(bottomRightNumber));
            }

        }
    }

}