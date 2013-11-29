/**
 * Copyright 2013 Ognyan Bankov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.irib_examples;

import android.graphics.Typeface;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.volley_examples.R;
import com.github.irib_examples.app.MyVolley;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * Demonstrates how to execute <code>JsonObjectRequest</code>
 * @author Ognyan Bankov
 *
 */
public class Act_JsonRequest extends Activity {
    private TextView mTvadad;
    private TextView mTvResult;
    private TextView mTvResult2;
    public String urlurl1="http://echo.jsontest.com/key/value/one/two";
    public String urlurl2="http://mvc.learnkey.ir/esl/getmobilenumberbyUseridDeviceID/saber/s54613164sf54/";
    public String urlurl3="http://mvc.learnkey.ir/esl/SetUserIDbyDeviceID/saber/s54613164sf54/";
    public String urlurl4="http://mvc.learnkey.ir/esl/appdetail/=com.glu.ewarriors2/1";
    public String urlurl5="http://mvc.learnkey.ir/esl/getproj/";
    public String url_Set_add_project="http://mvc.learnkey.ir/esl/setNewProj/androidapps/thisprojectwillpublishedsoonasp/13920905";

    public String URL = urlurl5+"1";

    public int count=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.act__json_request);

        mTvadad = (TextView) findViewById(R.id.tv_adad);
        Typeface face = Typeface.createFromAsset(getAssets(),
                "fonts/IRIB.TTF");
        mTvadad.setTypeface(face);
        mTvResult = (TextView) findViewById(R.id.tv_result);
        mTvResult.setTypeface(face);
        mTvResult2 = (TextView) findViewById(R.id.tv_result2);
        mTvResult2.setTypeface(face);



        Button btnJsonRequest = (Button) findViewById(R.id.btn_json_request);
        btnJsonRequest.setTypeface(face);

        btnJsonRequest.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = MyVolley.getRequestQueue();
                URL=urlurl5+Integer.toString(count);
                JsonObjectRequest myReq = new JsonObjectRequest(Method.GET, 
                                                        URL,
                                                        null,
                                                        createMyReqSuccessListener(),
                                                        createMyReqErrorListener());

                queue.add(myReq);

                String tvtxt= mTvResult.getText().toString();
                String tvtxt2= mTvResult2.getText().toString();
                if(tvtxt=="null")
                    count=1;
                else if (tvtxt!=""&&tvtxt2!="")
                    count++;
            }
        });
    }
    
 
    private Response.Listener<JSONObject> createMyReqSuccessListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    mTvadad.setText(Integer.toString(count));
                    mTvResult.setText(response.getString("name"));
                } catch (JSONException e) {
                    mTvResult.setText("Parse error");
                }
                try {
                    mTvResult2.setText(response.getString("desc"));
                } catch (JSONException e) {
                    mTvResult.setText("Parse error");
                }
            }
        };
    }
    
    
    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTvResult.setText(error.getMessage());
            }
        };
    }
}
