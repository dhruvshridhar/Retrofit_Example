package in.blogspot.tecnopandit.retrofit_example;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PlaceHolderApi {

    @GET("{name}") //posts because our api's has relative url as posts. e.g:- https://jsonplaceholder.typicode.com/posts before posts is base url
    //and after that is relative url i.e posts
    Call<List<Posts>> getPosts(@Path("name")String name);
}
