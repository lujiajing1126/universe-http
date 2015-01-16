package io.megrez.universe.response;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.Map;

import io.megrez.universe.request.UniverseRequest;

/**
 * Created by megrez on 15/1/16.
 */
public class UniverseJSONResponseHandle<T> implements UniverseResponseHandle<T> {
  private int method;
  private String url;
  private Map<String,String> headers;
  private Map<String,String> params;
  private IUniverseResponse<T> universeResponse;
  private Response.ErrorListener errorListener;

  public UniverseJSONResponseHandle(int method,String url,Map<String,String> headers,Map<String,String> params, final IUniverseResponse<T> universeResponse) {
    this.method = method;
    this.url = url;
    this.headers = headers;
    this.params = params;
    this.universeResponse = universeResponse;
    this.errorListener = new UniverseGlobalErrorResponse() {
      @Override
      public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
        universeResponse.onFail(error);
      }
    };
  }

  @Override
  public UniverseRequest<T> getRequest() {
    return new UniverseRequest<T>(method,url,headers,params,errorListener) {
      @Override
      protected Response<T> parseNetworkResponse(NetworkResponse response) {
        return null;
      }

      @Override
      protected void deliverResponse(T response) {
        universeResponse.onSuccess(response);
      }
    };
  }
}
