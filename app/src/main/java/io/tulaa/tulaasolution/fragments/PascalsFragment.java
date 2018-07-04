package io.tulaa.tulaasolution.fragments;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import io.tulaa.tulaasolution.application.TulaaApplication;
import io.tulaa.tulaasolution.configs.Config;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import io.tulaa.tulaasolution.R;
import io.tulaa.tulaasolution.models.InterviewRequest;
import io.tulaa.tulaasolution.models.InterviewResponse;
import io.tulaa.tulaasolution.utils.HttpsTrustManager;

public class PascalsFragment extends Fragment {
private android.widget.EditText pascalInputEditText;
private Button activateButton;
     @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                             android.os.Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(io.tulaa.tulaasolution.R.layout.pascals_fragment, container, false);
        pascalInputEditText = (EditText)view.findViewById(io.tulaa.tulaasolution.R.id.pascalInputEditText);
        activateButton = (Button)view.findViewById(R.id.activate_button);
     return view;
     }

     @Override
     public void onResume(){
         super.onResume();
         setListeners();
     }

     public void setListeners(){
         activateButton.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View v){
                 sendHttpRequest();
             }
         });
     }

     public void sendHttpRequest(){
         final ProgressDialog progressDialog = new ProgressDialog(PascalsFragment.this.getActivity());
         progressDialog.setCancelable(true);
         progressDialog.setMessage("Performing operation...");
         progressDialog.show();
         InterviewRequest user = new InterviewRequest();
         user.setPascalsTriangleInput(Integer.parseInt(pascalInputEditText.getText().toString()));
         String request = new Gson().toJson(user);
         JsonObjectRequest req = null;
         try {
             HttpsTrustManager.allowAllSSL();
             req = new JsonObjectRequest(Config.BASE_URL + Config.PASCAL_ENDPOINT, new JSONObject(request),
                     new Response.Listener<JSONObject>() {
                         @Override
                         public void onResponse(JSONObject response) {
                             try {
                                 InterviewResponse interviewResponse = new Gson().fromJson(response.toString(), InterviewResponse.class);
                                 progressDialog.dismiss();
                                 showDialog("The Kth Row of the Pascal's Triangle is\n"+interviewResponse.getPascalResponse());
                             } catch (Exception ex) {
                                 Log.e("Exception interview ",ex.getMessage());

                                 progressDialog.dismiss();
                                 Toast.makeText(PascalsFragment.this.getActivity(), "Check internet connection", Toast.LENGTH_LONG).show();

                             }
                         }
                     }, new Response.ErrorListener() {
                 @Override
                 public void onErrorResponse(VolleyError error) {
                     if(error.getMessage() != null && error.getMessage().contains("JSONException")){
                         progressDialog.dismiss();
                         Toast.makeText(PascalsFragment.this.getActivity(), "Check internet connection", Toast.LENGTH_LONG).show();
                     }
                     else {
                         Log.e("Error interview ",error.getMessage());
                         progressDialog.dismiss();
                         Toast.makeText(PascalsFragment.this.getActivity(), "Check internet connection", Toast.LENGTH_LONG).show();
                     }

                 }
             });
         } catch (JSONException e) {
             e.printStackTrace();
             progressDialog.dismiss();
         }
         req.setRetryPolicy(new DefaultRetryPolicy(
                 0,
                 DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                 DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
         //Creating a Request Queue
         RequestQueue requestQueue = TulaaApplication.getRequestQueue(PascalsFragment.this.getActivity());
         requestQueue.add(req);
     }

     public void generatePascalInput(){
         String input = pascalInputEditText.getText().toString();
         if(input.length() > 0){
             //proceed
         }
         else {
             Toast.makeText(PascalsFragment.this.getContext(),"Please input index k",Toast.LENGTH_SHORT).show();
         }
     }

    public void showDialog(String message) {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(PascalsFragment.this.getActivity());
        builder1.setMessage(message);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();


    }
}
