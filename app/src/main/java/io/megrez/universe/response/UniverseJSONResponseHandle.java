package io.megrez.universe.response;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import io.megrez.universe.http.UniverseHttpClient;
import io.megrez.universe.request.UniverseRequest;
import io.megrez.universe.utils.UniverseHttpHeaderParser;

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
  private Class<?> clazz;

  public UniverseJSONResponseHandle(int method,String url,Map<String,String> headers,Map<String,String> params, final IUniverseResponse<T> universeResponse, Class<?> clazz) {
    this.method = method;
    this.url = url;
    this.headers = headers;
    this.params = params;
    this.universeResponse = universeResponse;
    this.clazz = clazz;
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
        try {
          String jsonString = new String(response.data, UniverseHttpHeaderParser.parseCharset(response.headers));
          T responseObject = (T)UniverseHttpClient.getInstance().getGson().fromJson(jsonString,clazz);
          return Response.success(responseObject,HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
          return Response.error(new ParseError(e));
        } catch (ClassCastException ex) {
          return Response.error(new ParseError(ex));
        }
      }

      @Override
      protected void deliverResponse(T response) {
        universeResponse.onSuccess(response);
      }
    };
  }

}
