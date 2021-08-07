package jp.ac.jec.ws.p3_splashtutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ZodiacActivity extends AppCompatActivity {

    private GridView gridViewZodiac;
    private String[] verticalZodiacs = {
        "ne","mi","ushi","uma","tora","hitsuji","u","saru","tatsu","tori"
    };
    private String[] horizontalZodiacs = {
            "ne","ushi","mi","uma","tora","u","hitsuji","saru","tatsu","white","tori","white"
    };

    private List<Integer> imageLists = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zodiac);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isPortrait()){
            setGridView(verticalZodiacs);
        } else {
            setGridView(horizontalZodiacs);
        }
    }

    /**
     * 干支の画像のGrid表示
     * @param zodiacs
     */
    private void setGridView(String[] zodiacs) {
        for (String tmp : zodiacs){
            int imageID = getResources().getIdentifier(tmp,"drawable",getPackageName());
            imageLists.add(imageID);
        }
        gridViewZodiac = findViewById(R.id.gridViewZodiac);
        GridAdapter gridAdapter = new GridAdapter(ZodiacActivity.this,R.layout.grid_items,imageLists);
        gridViewZodiac.setAdapter(gridAdapter);
    }

    /**
     * 縦向きか横向きの判定
     * @return
     */
    private boolean isPortrait() {
        int orientation = getResources().getConfiguration().orientation;
        boolean isVertical = true;

        if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            isVertical = false;
        }
        return isVertical;
    }

    /**
     * GridViewのAdapterクラス
     */
    private class GridAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private int layoutID;
        private List<Integer> imageLists = new ArrayList<>();

        class ViewHolder{
            ImageView imageViewZodiac;
        }

        public GridAdapter(Context context, int layoutID, List<Integer> imageLists) {
            super();
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.layoutID = layoutID;
            this.imageLists = imageLists;
        }

        @Override
        public int getCount() {
            return imageLists.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder;
            if (convertView == null){
                convertView = inflater.inflate(layoutID,parent,false);
                viewHolder = new ViewHolder();

                viewHolder.imageViewZodiac = convertView.findViewById(R.id.imageViewZodiacAnimal);
                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();

            }

            viewHolder.imageViewZodiac.setImageResource(imageLists.get(position));

            return convertView;
        }
    }
}
