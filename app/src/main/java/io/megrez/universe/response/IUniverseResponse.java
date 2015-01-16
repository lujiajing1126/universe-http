package io.megrez.universe.response;

import com.android.volley.VolleyError;

/**
 * Created by megrez on 15/1/16.
 */
public interface IUniverseResponse<T> {
  void onSuccess(T response);
  void onFail(VolleyError error);
}
