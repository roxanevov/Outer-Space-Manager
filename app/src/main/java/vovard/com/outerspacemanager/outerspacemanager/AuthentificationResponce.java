package vovard.com.outerspacemanager.outerspacemanager;

/**
 * Created by rvovard on 16/01/2018.
 */

public class AuthentificationResponce {
    public String token;
    public String expires;

    public AuthentificationResponce(String token, String expires) {
        this.token = token;
        this.expires = expires;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }


}
