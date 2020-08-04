package in.blogspot.tecnopandit.retrofit_example;

import com.google.gson.annotations.SerializedName;


//This is a model class made to map local variables to gson keys.

public class Posts {
    private String login;
    private String avatar_url;
    private String html_url;
    private String public_repos;
    private  String public_gists;
    private String followers;
    private String following;
    //@SerializedName("body") //when var name not same as key in gson use serialised name .
    //private String node_id;

    public String getLogin() {
        return login;
    }

    public String getAvUrl() {
        return avatar_url;
    }

    /*public String getTitle() {
        return title;
    }*/

    public String getHtml_url() {
        return html_url;
    }

    public String getPublic_repos() {
        return public_repos;
    }

    public String getPublic_gists() {
        return public_gists;
    }

    public String getFollowers() {
        return followers;
    }

    public String getFollowing() {
        return following;
    }
}
