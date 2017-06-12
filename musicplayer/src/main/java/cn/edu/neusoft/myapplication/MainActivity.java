package cn.edu.neusoft.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lv_data);

        MyAdapter adapter = new MyAdapter();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,PlayService.class);
                intent.putExtra("pos",position);//传给service点击的是第几首音乐
                startService(intent);
            }
        });

    }
    //重写MainActivity继承的父类里的onDestroy方法，让运行着的服务关闭，如果不关闭服务当关闭应用的时候音乐还会在一直响
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(MainActivity.this,PlayService.class);
        stopService(intent);
    }

    //四种Adapter  ArrayAdapter    SimpleAdapter   SimpleCursorAdapter   自定义Adapter

    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return MyApplication.musicList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_music,null);
            }
            Music music = MyApplication.musicList.get(position);

            TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            TextView tv_artist = (TextView) convertView.findViewById(R.id.tv_artist);

            tv_name.setText(music.getName());
            tv_artist.setText(music.getArtist());

            return convertView;
        }
    }
}