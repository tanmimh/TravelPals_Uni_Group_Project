package travelpals.travelpals;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final EditText etName = (EditText) findViewById(R.id.etName);


        Intent userIntent = getIntent();
        String name = userIntent.getStringExtra("name");
        int age = userIntent.getIntExtra("age", -1);
        String username = userIntent.getStringExtra("username");

        etUsername.setText(username);
        etName.setText(name);
        etAge.setText(age + "");

    }
}
