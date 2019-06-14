package com.example.electricpower.net.http;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.electricpower.entity.to.TokenSave;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 重写Request
 */

public class ATTJsonRequest<T> extends Request<T>
{
    private static final String PROTOCOL_CHARSET = "utf-8";

    private final Listener<T> mListener;

    private Gson mGson;

    private Class<T> mClass;

    private String mRequestBody = null;

    private boolean isPretreatment = true;
    /**
     * Params to be added to the request body in case of
     * a POST or PUT request or appended to the URL in
     * case of a GET request
     */
    private Map<String, String> mParams = null;

    public void setPretreatment(boolean isPretreatment)
    {
        this.isPretreatment = isPretreatment;
    }

    public ATTJsonRequest(int method, String url, Class<T> clazz, Listener<T> listener,
                          ErrorListener errorListener)
    {
        super(method, url, errorListener);
        mGson = new Gson();
        mClass = clazz;
        mListener = listener;
        setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    public ATTJsonRequest(String url, Class<T> clazz, Listener<T> listener,
                          ErrorListener errorListener)
    {
        this(Method.GET, url, clazz, listener, errorListener);
    }

    public ATTJsonRequest(String url, Class<T> clazz, JSONObject jsonRequest, Listener<T> listener,
                          ErrorListener errorListener)
    {
        this(Method.POST, url, clazz, listener, errorListener);
        if (jsonRequest != null)
        {
            mRequestBody = jsonRequest.toString();
        }
    }

    /**
     * 自定义jsonobject
     *
     * @param url
     * @param clazz
     * @param jsonObject
     * @param listener
     * @param errorListener
     */
    public ATTJsonRequest(String url, Class<T> clazz, JsonObject jsonObject, Listener<T> listener, ErrorListener errorListener)
    {
        this(Method.POST, url, clazz, listener, errorListener);

        if (jsonObject != null)
        {
            mRequestBody = jsonObject.toString();
        }
    }




    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError)
    {
        return super.parseNetworkError(volleyError);
    }

    @Override
    public void deliverError(VolleyError error)
    {
//        if (isPretreatment) {
//
//        }
        super.deliverError(error);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response)
    {
        String result = "";
        try
        {
            String js1 = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));

            String b=js1.substring(1,js1.length()-1);

//            Log.d("b ----",b);
//            jsonString="{\"a\":\"1\",\"b\":\"2\",\"c\":3}";
            String a=b.replace("\\","");

//            Log.d("js1",a);
            String jsonString =js1;
//            String jsonString = "{\"ErrorMsg\":\"\",\"ErrType\":3,\"data\":[{\"Id\":245,\"Name\":\"APP系统菜单\",\"Url\":null,\"Sort\":0,\"ParentId\":0,\"Active\":true,\"AppName\":\"App\"},{\"Id\":246,\"Name\":\"App工单上料\",\"Url\":null,\"Sort\":1,\"ParentId\":245,\"Active\":true,\"AppName\":\"App\"}]}";
//            Log.d("jsonString2",jsonString);

            JSONObject data = new JSONObject(jsonString);
//            if(data.optInt("status") == 2){
//                throw new DataException(data.optString("message"));
//            }
            result = jsonString;
            Log.d("Volley", "ATTJsonRequest get:" + jsonString);
            Response<T> convertResponse = null;
            jsonString.replaceAll("\r", "%r%");
            jsonString.replaceAll("\n", "%r%");
            jsonString.replaceAll("</p>", "%r%");
            jsonString.replaceAll("<\\/p>", "%r%");
            jsonString.replaceAll("\t", "");
            if (mClass.getName().equals(JSONObject.class.getName()))
            {
                convertResponse = (Response<T>) Response.success(new JSONObject(jsonString),
                        HttpHeaderParser.parseCacheHeaders(response));
            } else
            {
                convertResponse = Response.success(mGson.fromJson(jsonString, mClass),
                        HttpHeaderParser.parseCacheHeaders(response));
            }
            return convertResponse;
        }
//        catch (DataException e){
//            e.printStackTrace();
//            return Response.error(new ParseError(response));
//        }
        catch (Exception e)
        {
            Log.i("ATTJsonRequest", e.getMessage());
            String s = "";
            for (StackTraceElement traceElement : e.getStackTrace())
            {
                s += "\n" + traceElement.toString();
            }
            Log.i("ATTJsonRequest", s);

            Map<String, String> stringErrorMap = new HashMap<>();
            stringErrorMap.put("url", getUrl());
            stringErrorMap.put("body", mRequestBody);
            stringErrorMap.put("result", result);
            stringErrorMap.put("error_message", e.getMessage());
            stringErrorMap.put("error_line", s);

            JSONObject object = new JSONObject(stringErrorMap);
//            MobclickAgent.reportError(AppApplication.getInstance().getCurretActivity(), object.toString());
            return Response.error(new ParseError(response));
        }
    }

    @Override
    protected void deliverResponse(T response)
    {
        if (mListener != null)
        {
            mListener.onResponse(response);
        }
    }


    @Override
    public byte[] getBody()
    {
        try
        {
            if (mRequestBody != null)
            {
                return mRequestBody.getBytes(PROTOCOL_CHARSET);
            } else
            {
                return super.getBody();
            }
        } catch (AuthFailureError e)
        {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException uee)
        {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                    mRequestBody, PROTOCOL_CHARSET);
            return null;
        }
    }

    /**
     * Returns a Map of parameters to be used for a POST or PUT request.  Can throw
     * {@link AuthFailureError} as authentication may be required to provide these values.
     * <p>
     * <p>Note that you can directly override {@link #getBody()} for custom data.</p>
     *
     * @throws AuthFailureError in the event of auth failure
     */
    protected Map<String, String> getParams() throws AuthFailureError
    {
        return (mParams == null) ? Collections.<String, String>emptyMap() : mParams;
    }

    /**
     * Sets the parameters to be added to the request body in case of a
     * POST or PUT request, or appended to the URL in case of GET request
     *
     * @param params
     */
    public void setParams(Map<String, String> params)
    {
        mParams = params;
    }

    public void addParams(String key, String value)
    {
        if (mParams == null)
        {
            mParams = new HashMap<String, String>();
        }
        mParams.put(key, value);
    }

    public void addParams(Map<String, String> params)
    {
        if (mParams == null)
        {
            mParams = new HashMap<String, String>();
        }
        mParams.putAll(params);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError
    {
        Map<String, String> header = new HashMap<String, String>();
//        header.put("deviceId", ATTApplication.getInstance().getDeviceid());
        header.put("appkey", "testkey");
        header.put("Authorization", TokenSave.token);
        return header;
    }

    @Override
    public String getBodyContentType() {
        return "application/json;charset="+getParamsEncoding();
    }
}
