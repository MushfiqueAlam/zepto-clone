package com.central_api.util;

import java.util.HashMap;

public interface ApiUtil {
    public Object makeGetCall(String apiUrl, String apiEndPoint, HashMap<String,String>queryParams);
    public Object makePostCall(String apiUrl,String apiEndPoint,HashMap<String,String>queryParams,Object requestBody);
    public Object makePutCall(String apiUrl,String apiEndPoint,HashMap<String,String>queryParams,Object requestBody);

}
