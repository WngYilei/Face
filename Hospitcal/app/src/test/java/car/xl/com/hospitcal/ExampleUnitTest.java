package car.xl.com.hospitcal;

import android.content.DialogInterface;
import android.content.SyncStatusObserver;
import android.text.style.BulletSpan;
import android.util.Log;

import com.xl.toast.OkhtpPost;
import com.xl.toast.OkhttpGet;
import com.xl.toast.OkhttpPost;
import com.xl.toast.UrlPost;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.net.URL;

import okhttp3.FormBody;
import okhttp3.Response;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
            String url = "http://192.168.14.178:8080/NewHospitcal/UserLogin";
        try {
            String name = "2";
            String passwrod = "2";
            UrlPost urlPost = new UrlPost();
            String json = urlPost.up(url, name, passwrod);
            System.out.print(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}