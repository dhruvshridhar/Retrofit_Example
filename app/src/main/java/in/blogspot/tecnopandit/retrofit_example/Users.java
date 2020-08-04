package in.blogspot.tecnopandit.retrofit_example;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class Users {

    private String name, profile;
    private String avatar;

    public Users(String name, String avatar,String profile) {
        this.name = name;
        this.avatar = avatar;
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getProfile() {
        return profile;
    }

    @BindingAdapter("android:imageUrl")
    public static void loadImage(View view, String imgurl){
        ImageView imageView=(ImageView) view;
        Glide.with(view.getContext()).asDrawable().load(imgurl).into(imageView);
    }
}
