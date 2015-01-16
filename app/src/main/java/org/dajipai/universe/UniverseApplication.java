package org.dajipai.universe;

import android.app.Application;
import android.content.Context;

import io.megrez.universe.http.UniverseHttpClient;
import io.megrez.universe.http.UniverseHttpClientConfiguration;
import timber.log.Timber;

/**
 * Created by megrez on 15/1/15.
 */
public class UniverseApplication extends Application {
  public static final String TAG = UniverseApplication.class.getSimpleName();
  public static Context mContext;
  private static UniverseApplication instance;
  private static UniverseHttpClientConfiguration universeHttpClientConfiguration;

  public UniverseApplication() {
    instance = this;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    universeHttpClientConfiguration = new UniverseHttpClientConfiguration.Builder(this).registerAdapter().build();
    UniverseHttpClient.initialize(universeHttpClientConfiguration);
    Timber.plant(new Timber.DebugTree());
  }

  @Override
  public void onTerminate() {
    super.onTerminate();
  }

  public static UniverseApplication getInstance() {
    return instance;
  }
}
