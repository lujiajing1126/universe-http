package io.megrez.universe.response;

import com.android.volley.VolleyError;

import java.util.List;

/**
 * Created by megrez on 15/1/16.
 */
public interface IUniverseResponse<T> {
  void onSuccess(List<T> response);
  void onSuccess(T response);
  void onFail(VolleyError error);
}
