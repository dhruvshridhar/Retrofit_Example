package in.blogspot.tecnopandit.retrofit_example;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import in.blogspot.tecnopandit.retrofit_example.databinding.ActivityMainBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    MyAdapter myAdapter;


    ArrayList<String> names=new ArrayList<String>();
    ArrayList<String> avatar=new ArrayList<>();
    ArrayList<String> profile=new ArrayList<>();
    ArrayList<Users> result=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        activityMainBinding.rcv.setLayoutManager(new LinearLayoutManager(this));
        activityMainBinding.rcv.setHasFixedSize(true);
        myAdapter=new MyAdapter(this,result);
        activityMainBinding.rcv.setAdapter(myAdapter);

        //create retrofit instance
        //here we pass base url and our interface had relative url
        //converter fac determines what parser u want to use. a gson or any other


        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://api.github.com/").addConverterFactory(GsonConverterFactory.create())
                .build();

        //create placeholderapi instance
        //we could have implemented its methods but that the work of retrofit soo do the below thing
        PlaceHolderApi placeHolderApi=retrofit.create(PlaceHolderApi.class);


        //now to execute our network req we use our call object

        Call<List<Posts>> call = placeHolderApi.getPosts("users");

        //call this call obj and we recieve req
        // we donot use call.execute() because it will run on main thread and make ui unresponsive so we execute it unsynced
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                //here you need to handle 404 responses too
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"OOOOOPSSSYYY!!!",Toast.LENGTH_LONG).show();
                    return;
                }
                else{
                    final List<Posts> res=response.body();
                    for(Posts posts: res){
                        names.add(posts.getLogin());
                        avatar.add(posts.getAvUrl());
                        profile.add(posts.getHtml_url());
                    }
                    for(int i=0;i<names.size();i++){
                        //Users temp;
                        result.add(new Users(names.get(i),avatar.get(i),profile.get(i)));
                    }
                    myAdapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(),"DONE!!",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
            // when our req failed
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


    }
}