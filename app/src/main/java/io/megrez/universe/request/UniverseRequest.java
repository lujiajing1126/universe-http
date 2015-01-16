package io.megrez.universe.request;

import com.android.volley.Request;
import com.android.volley.Response;

import java.util.Map;

/**
 * Created by megrez on 15/1/16.
 */
public abstract class UniverseRequest<T> extends Request<T>{
  public UniverseRequest(String url, Map<String, String> headers,
                         Response.Listener<T> listener, Response.ErrorListener errorListener) {
    super(Method.GET, url, errorListener);
  }
}
