package io.megrez.universe.request;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by megrez on 15/1/16.
 */
public class UniverseParamManager {
  public static Map<String,String> buildParams(UniverseParam... universeParams) {
    List<UniverseParam> paramsList = Arrays.asList(universeParams);
    Map<String,String> paramsMap = new HashMap<>();
    for(UniverseParam universeParam: paramsList) {
      paramsMap.put(universeParam.getKey(),universeParam.getValue());
    }
    return paramsMap;
  }
}
