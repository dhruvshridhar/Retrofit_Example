package in.blogspot.tecnopandit.retrofit_example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PlaceHolderForUser {
    @GET("{name}")
    Call<Posts> getPostOfUser(@Path("name")String name);
}
