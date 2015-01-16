package org.dajipai.universe.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.VolleyError;

import org.dajipai.universe.R;
import org.dajipai.universe.http.DemoHttp;
import org.dajipai.universe.http.DemoResponse;
import org.dajipai.universe.models.SigninResponse;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.megrez.universe.response.UniverseResponse;
import timber.log.Timber;


public class MainActivity extends ActionBarActivity {
  private static final String TAG = MainActivity.class.getSimpleName();
  @InjectView(R.id.activity_main_hello_world)
  TextView HelloWorldTextView;

  @InjectView(R.id.activity_main_login)
  Button LoginBtn;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.inject(this);
    initView();
  }

  protected void initView() {
    LoginBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        DemoHttp.getInstance().signIn("15902122792","12345678",new DemoResponse<SigninResponse>() {
          @Override
          public void onSuccess(SigninResponse response) {
            Timber.d(response.toString());
            HelloWorldTextView.setText(response.getName());
          }
          @Override
          public void onFail(VolleyError error) {
            Timber.d(error.toString());
          }
        });
      }
    });
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
