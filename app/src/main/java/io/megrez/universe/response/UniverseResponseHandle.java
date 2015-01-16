package io.megrez.universe.response;

import com.android.volley.Request;

/**
 * Created by megrez on 15/1/16.
 */
public interface UniverseResponseHandle<T> {
  Request<T> getRequest();
}
