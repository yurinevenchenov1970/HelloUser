package name.yuris.hellouser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.redmadrobot.inputmask.MaskedTextChangedListener;

public class IntroActivity extends AppCompatActivity {

    private EditText mEditText;
    private TextView mHintTextView;
    private Button mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        mEditText = (EditText) findViewById(R.id.edit_text);
        mHintTextView = (TextView) findViewById(R.id.hint_text_view);
        mLoginButton = (Button) findViewById(R.id.login_button);
        mHintTextView.setText(getString(R.string.hint));
        mHintTextView.setVisibility(View.INVISIBLE);


        final MaskedTextChangedListener listener = new MaskedTextChangedListener(
                "+7 ([000]) [000] [00] [00]",
                true,
                mEditText,
                null,
                new ValueListener()
        );

        mEditText.addTextChangedListener(listener);
        mEditText.setOnFocusChangeListener(listener);
        mEditText.setHint(listener.placeholder());
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 6/10/2017 Hide keyboard here!
                startActivity(MainActivity.createExplicitIntent(getApplicationContext()));
            }
        });
        mLoginButton.setEnabled(false);
    }

    private class ValueListener implements MaskedTextChangedListener.ValueListener{

        @Override
        public void onTextChanged(boolean maskFilled, String value) {
            if(maskFilled){
                mLoginButton.setEnabled(true);
                mHintTextView.setVisibility(View.INVISIBLE);
            } else {
                mLoginButton.setEnabled(false);
                mHintTextView.setVisibility(View.VISIBLE);
            }
        }
    }
}