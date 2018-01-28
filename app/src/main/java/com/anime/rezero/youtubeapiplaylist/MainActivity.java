package com.anime.rezero.youtubeapiplaylist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static String API_KEY = "AIzaSyDrlomTIOVtkMOwpnPcfA8w6spWYcLkFzk";
    public static String ID_PLAYLIST = "PLwtbQVl7q1j_bxPa54S-oZ3OUVujRvb13&disable_polymer=true";
    ListView lvVideo;
    ArrayList<VideoYouTube> videoYouTubeArrayList;
    VideoYoutubeAdapter videoYoutubeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        String urlGetJson ="https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId="+ID_PLAYLIST+"&key="+API_KEY+"&maxResults=50";
        GetJsonYouTube(urlGetJson);
        setEven();
    }
    public void setEven(){
        lvVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,PlayVideoActivity.class);
                intent.putExtra("idVideoYoutube",videoYouTubeArrayList.get(position).getIdVideo());
                startActivity(intent);
            }
        });
    }
    public void initWidget(){
        lvVideo = (ListView) findViewById(R.id.lv_video);
        videoYouTubeArrayList = new ArrayList<>();
        videoYoutubeAdapter = new VideoYoutubeAdapter(this,R.layout.row_video_youtube,videoYouTubeArrayList);
        lvVideo.setAdapter(videoYoutubeAdapter);
    }
    private void GetJsonYouTube(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonItems = response.getJSONArray("items");
                    String title=""; String url=""; String videoid="";
                    for(int i = 0 ; i < jsonItems.length();i++){
                        JSONObject jsonItem = jsonItems.getJSONObject(i);

                        JSONObject jsonSnippet = jsonItem.getJSONObject("snippet");
                        title = jsonSnippet.getString("title");

                        JSONObject jsonThumbnail = jsonSnippet.getJSONObject("thumbnails");
                        JSONObject jsonMedium = jsonThumbnail.getJSONObject("medium");
                        url = jsonMedium.getString("url");

                        JSONObject jsonResourceID = jsonSnippet.getJSONObject("resourceId");
                        videoid = jsonResourceID.getString("videoId");
                        videoYouTubeArrayList.add(new VideoYouTube(title,url,videoid));
                    }
                    videoYoutubeAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
