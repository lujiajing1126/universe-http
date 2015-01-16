package io.megrez.universe.http;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by megrez on 15/1/16.
 */
public class UniverseHttpClientConfiguration {
  private Context mContext;
  private RequestQueue requestQueue;

  public UniverseHttpClientConfiguration(Context context, RequestQueue requestQueue) {
    this.mContext = context;
    this.requestQueue = requestQueue;
  }

  public RequestQueue getRequestQueue() {
    return requestQueue;
  }

  public Context getContext() {
    return mContext;
  }

  public static class Builder {
    private Context mContext;
    private RequestQueue requestQueue;
    public Builder(Context context) {
      this.mContext = context;
    }

    public Builder registerAdapter() {
      this.requestQueue = Volley.newRequestQueue(mContext);
      return this;
    }

    public UniverseHttpClientConfiguration build() {
      return new UniverseHttpClientConfiguration(mContext,requestQueue);
    }

  }
}
