package io.megrez.universe.http;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import java.util.Map;

import io.megrez.universe.request.UniverseParam;
import io.megrez.universe.request.UniverseParamManager;
import io.megrez.universe.response.IUniverseResponse;
import io.megrez.universe.exceptions.UniverseHttpClientNotInitializedException;
import io.megrez.universe.response.UniverseStringResponseHandle;

/**
 * Created by megrez on 15/1/16.
 */
public class UniverseHttpClient implements UniverseHttpRequest {
  private static final String TAG = UniverseHttpClient.class.getSimpleName();
  private static UniverseHttpClientConfiguration mHttpClientConfiguration;
  private static UniverseHttpClient httpClient;
  private static RequestQueue requestQueue;
  private static boolean isInitialized = false;
  private static Context mContext;

  public static synchronized void initialize(UniverseHttpClientConfiguration httpClientConfiguration) {
    if (isInitialized) {
      Log.w(TAG, "Universe has already initialized");
      return;
    }
    mHttpClientConfiguration = httpClientConfiguration;
    mContext = mHttpClientConfiguration.getContext();
    requestQueue = mHttpClientConfiguration.getRequestQueue();
    httpClient = new UniverseHttpClient();
    isInitialized = true;
  }

  public static UniverseHttpClient getInstance() {
    checkIfHasInitialized();
    return httpClient;
  }

  private static synchronized void checkIfHasInitialized() {
    if (!isInitialized) {
      throw new UniverseHttpClientNotInitializedException();
    }
  }

  @Override
  public void post(String url,IUniverseResponse<String> universeResponse,Map<String,String> headers,UniverseParam... universeParams) {
    post(url,headers,UniverseParamManager.buildParams(universeParams),universeResponse);
  }

  @Override
  public void get(String url,IUniverseResponse<String> universeResponse,Map<String,String> headers,UniverseParam... universeParams) {
    get(url,headers,UniverseParamManager.buildParams(universeParams),universeResponse);
  }

  private void post(String url,final Map<String,String> headers,final Map<String,String> params,IUniverseResponse<String> universeResponse) {
    Request<String> request = new UniverseStringResponseHandle(Request.Method.POST,url,headers,params,universeResponse).getRequest();
    requestQueue.add(request);
  }

  private void get(String url,final Map<String,String> headers,final Map<String,String> params,IUniverseResponse<String> universeResponse) {
    Request<String> request = new UniverseStringResponseHandle(Request.Method.GET,url,headers,params,universeResponse).getRequest();
    requestQueue.add(request);
  }
}
