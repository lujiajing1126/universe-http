package org.dajipai.universe.http;

import org.dajipai.universe.models.ResultResponse;
import org.dajipai.universe.models.SigninResponse;

import io.megrez.universe.http.UniverseHttpClient;
import io.megrez.universe.request.UniverseStringParam;
import io.megrez.universe.response.IUniverseResponse;
import io.megrez.universe.utils.MD5Enctyption;

/**
 * Created by megrez on 15/1/16.
 */
public class DemoHttp {
  private static final String URL = "http://staging.whosv.net/api/v2";
  private static final String SIGN_IN = "/users/sign_in.json";

  private static UniverseHttpClient universeHttpClient;
  private static DemoHttp demoHttp;

  public static DemoHttp getInstance() {
    if(universeHttpClient == null)
      universeHttpClient = UniverseHttpClient.getInstance();
    if(demoHttp == null)
      demoHttp = new DemoHttp();
    return demoHttp;
  }

  public void signIn(String username,String password,IUniverseResponse<SigninResponse> universeResponse) {
    universeHttpClient.postJSON(fullUrl(SIGN_IN),new AOPHandler<>(universeResponse),null,SigninResponse.class,new UniverseStringParam("phone",username),new UniverseStringParam("password", MD5Enctyption.getCipher(password)));
  }
  private String fullUrl(String url) {
    return String.format("%s%s",URL,url);
  }


}
