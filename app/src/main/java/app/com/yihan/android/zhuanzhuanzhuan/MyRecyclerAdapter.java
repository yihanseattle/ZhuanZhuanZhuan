package app.com.yihan.android.zhuanzhuanzhuan;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

/**
 * Created by HanYi on 7/19/16.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.CustomViewHolder> {
    private List<Item> placeItemList;
    private Context mContext;
    private int lastPosition = -1;
    View view;

    private String[] colors = new String[]{
            "#FFFFE0", "#FFF0E0", "#FFA000", "#E0B880", "#FFF0F0", "#D08040"
            , "#FFF8D0", "#FFC0C0", "#FFD800", "#C09090", "#FF8050", "#20B0A0"
            , "#F0A060", "#E09870", "#FFE8D0", "#F0E890", "#FFF0D0", "#FF8050"
            , "#FFF8E0", "#FFF8D0", "#C09090", "#90F090", "#C0B860", "#506830"
            , "#C0C0C0", "#60C8A0", "#40B070", "#D0C0D0", "#C09090", "#FFF0E0"};

    public MyRecyclerAdapter(Context context, List<Item> placeItemList) {
        this.placeItemList = placeItemList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        this.view = view;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int position) {

        YoYo.with(Techniques.Tada).playOn(customViewHolder.mCardView);
        Item feedItem = placeItemList.get(position);

        //Setting text view title
        customViewHolder.tvPlaceName.setText(Html.fromHtml(feedItem.getPlaceName()));
        customViewHolder.tvLunchOrDinner.setText(Html.fromHtml(feedItem.getLunchOrDinner()));
        customViewHolder.mCardView.setCardBackgroundColor(Color.parseColor(colors[feedItem.getColorIndex()]));

    }

    @Override
    public int getItemCount() {
        return (null != placeItemList ? placeItemList.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        //        protected ImageView imageView;
        protected TextView tvPlaceName;
        protected TextView tvLunchOrDinner;
        protected CardView mCardView;

        public CustomViewHolder(View view) {
            super(view);
//            this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
//            view.setBackgroundColor(Color.parseColor("#ff99cc00"));
            this.tvPlaceName = (TextView) view.findViewById(R.id.placeName);
            this.tvLunchOrDinner = (TextView) view.findViewById(R.id.lunchOrDinner);
            this.mCardView = (CardView) view.findViewById(R.id.card_view);
        }
    }
}
