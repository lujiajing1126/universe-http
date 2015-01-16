package io.megrez.universe.response;

import com.android.volley.VolleyError;

import java.util.List;

/**
 * Created by megrez on 15/1/16.
 */
public abstract class UniverseResponse<T> implements IUniverseResponse<T> {
  @Override
  public void onFail(VolleyError error) {
  }

  @Override
  public void onSuccess(T response) {
  }

  @Override
  public void onSuccess(List<T> response) {
  }
}
