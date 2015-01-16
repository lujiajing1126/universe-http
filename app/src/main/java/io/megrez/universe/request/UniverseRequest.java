package io.megrez.universe.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;

import java.util.Map;

/**
 * Created by megrez on 15/1/16.
 */
public abstract class UniverseRequest<T> extends Request<T> implements IUniverseRequest {
  protected Map<String,String> params;
  protected Map<String,String> headers;

  public UniverseRequest(int method,String url, Map<String, String> headers,Map<String,String> params, Response.ErrorListener errorListener) {
    super(method, url, errorListener);
    this.headers = headers;
    this.params = params;
  }

  @Override
  public Map<String, String> getHeaders() throws AuthFailureError {
    return getDefaultHeaders() !=null ? headers:super.getHeaders();
  }

  @Override
  protected Map<String, String> getParams() throws AuthFailureError {
    return params!=null ? params:super.getParams();
  }

  @Override
  public Map<String,String> getDefaultHeaders() {
    return headers;
  }
}
