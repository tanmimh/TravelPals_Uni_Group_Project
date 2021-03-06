package travelpals.travelpals;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class loginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final TextView tvLinktoRegister = (TextView) findViewById(R.id.tvLinktoRegister);

        tvLinktoRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(loginActivity.this, RegisterActivity.class);
                loginActivity.this.startActivity(registerIntent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                String name = jsonResponse.getString("name");
                                String dob = jsonResponse.getString("dob");
                                String username = jsonResponse.getString("username");
                                String email = jsonResponse.getString("email");
                                String gender = jsonResponse.getString("gender");

//
                                Intent intent = new Intent(loginActivity.this, MainMenuActivity.class);
                                intent.putExtra("name", name);
                                intent.putExtra("dob", dob);
                                intent.putExtra("username", username);
                                intent.putExtra("email", email);
                                intent.putExtra("gender", gender);


                                loginActivity.this.startActivity(intent);
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(loginActivity.this);
                                builder.setMessage("Login failed")
                                        .setNegativeButton("Try again", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(loginActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}
