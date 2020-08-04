package in.blogspot.tecnopandit.retrofit_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserDetails extends AppCompatActivity {
String nameofdev="";
ImageView imageView;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        nameofdev=getIntent().getStringExtra("selected");
        textView=findViewById(R.id.details);
        imageView=findViewById(R.id.personpic);
        Toast.makeText(this,nameofdev+" is selected",Toast.LENGTH_SHORT).show();


        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://api.github.com/users/").addConverterFactory(GsonConverterFactory.create())
                .build();

        PlaceHolderForUser placeHolderApi=retrofit.create(PlaceHolderForUser.class);

        Call<Posts> call= placeHolderApi.getPostOfUser(nameofdev);
        call.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                String result="";
                Posts temp=response.body();
                if(response.isSuccessful()){
                    result="Name: "+temp.getLogin()+"\nProfile URL: "+temp.getHtml_url()+"\nFollowers: "+temp.getFollowers()+"\nFollowing: "+temp.getFollowing()
                            +"\nGists: "+temp.getPublic_gists()+"\nRepos: "+temp.getPublic_repos();
                    textView.setText(result);
                    Glide.with(getApplicationContext()).asDrawable().load(temp.getAvUrl()).into(imageView);
                }
                else {
                    textView.setText("Something went wrong :(");
                }
            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });

    }
}