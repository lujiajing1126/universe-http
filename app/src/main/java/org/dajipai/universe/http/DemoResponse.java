package org.dajipai.universe.http;

import com.android.volley.VolleyError;

import io.megrez.universe.response.UniverseResponse;

/**
 * Created by megrez on 15/1/16.
 */
public abstract class DemoResponse<T> extends UniverseResponse<T> {
  @Override
  public void onFail(VolleyError error) {

  }
}
