package com.anime.rezero.youtubeapiplaylist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by zing on 1/26/2018.
 */

public class VideoYoutubeAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<VideoYouTube> videoYouTubeList;

    public VideoYoutubeAdapter(Context context, int layout, List<VideoYouTube> videoYouTubeList) {
        this.context = context;
        this.layout = layout;
        this.videoYouTubeList = videoYouTubeList;
    }

    @Override
    public int getCount() {
        return videoYouTubeList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHoler {
        ImageView imgThumnail;
        TextView txtTitle;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoler holer;
        if (convertView == null) {
            holer = new ViewHoler();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holer.imgThumnail = (ImageView) convertView.findViewById(R.id.img_thumbnail);
            holer.txtTitle = (TextView) convertView.findViewById(R.id.txt_title);
            convertView.setTag(holer);
        } else {
            holer = (ViewHoler) convertView.getTag();
        }
        VideoYouTube videoYouTube = videoYouTubeList.get(position);
        holer.txtTitle.setText(videoYouTube.getTitle());
        Picasso.with(context).load(videoYouTube.getThumnails()).into(holer.imgThumnail);
        return convertView;
    }
}
