package io.megrez.universe.response;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import io.megrez.universe.request.UniverseRequest;
import io.megrez.universe.utils.UniverseHttpHeaderParser;
import timber.log.Timber;

/**
 * Created by megrez on 15/1/16.
 */
public class UniverseStringResponseHandle implements UniverseResponseHandle<String> {
  private int method;
  private String url;
  private Map<String,String> headers;
  private Map<String,String> params;
  private IUniverseResponse<String> universeResponse;
  private Response.ErrorListener errorListener;

  public UniverseStringResponseHandle(int method,String url,Map<String,String> headers,Map<String,String> params, final IUniverseResponse<String> universeResponse) {
    this.method = method;
    this.url = url;
    this.headers = headers;
    this.params = params;
    this.universeResponse = universeResponse;
    this.errorListener = new UniverseGlobalErrorResponse() {
      @Override
      public void onErrorResponse(VolleyError error) {
        universeResponse.onFail(error);
      }
    };
  }

  @Override
  public UniverseRequest<String> getRequest() {
    return new UniverseRequest<String>(method,url,headers,params,errorListener) {
      @Override
      protected Response<String> parseNetworkResponse(NetworkResponse response) {
        Timber.d(Thread.currentThread().getName());
        String parsed;
        try {
          parsed = new String(response.data, UniverseHttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
          parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
      }

      @Override
      protected void deliverResponse(String response) {
        universeResponse.onSuccess(response);
      }
    };
  }
}
