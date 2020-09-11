package com.example.guessthecelebrity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    List<String> imageSource = new ArrayList<String>();
    List<String> names = new ArrayList<String>();
    String html;
    ImageView picture;
    int indexNum;
    List<String> choices = new ArrayList<String>();
    Button b0;
    Button b1;
    Button b2;
    Button b3;
    int answerIndex;

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
        URL url;
        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream in = connection.getInputStream();
                Bitmap myImage = BitmapFactory.decodeStream(in);
                return myImage;
            } catch(Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

//    public class HTMLReceiver extends AsyncTask<String, Void, String> {
//        String result = "";
//        URL url;
//        HttpURLConnection connection;
//        @Override
//        protected String doInBackground(String... urls) {
//            try {
//                url = new URL(urls[0]);
//                connection = (HttpURLConnection) url.openConnection();
//                InputStream in = connection.getInputStream();
//                InputStreamReader reader = new InputStreamReader(in);
//                int data = reader.read();
//
//                while (data != -1) {
//                    char current = (char) data;
//                    result += current;
//                    data = reader.read();
//                }
//                return result;
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                return null;
//            }
//        }
//    }


    public void choose(View view) {
        if(Integer.parseInt(view.getTag().toString()) == answerIndex) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong :(", Toast.LENGTH_SHORT).show();
        }
        this.setImage();
        this.setChoices();
        Log.i("tag", view.getTag().toString());

    }

    public void setImage() {
        int i = (int) (Math.random() * 10);
        ImageDownloader downloader = new ImageDownloader();
        try {
            this.picture.setImageBitmap(downloader.execute(imageSource.get(i)).get());
            this.indexNum = i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("answerIndex", "" + this.answerIndex);
    }

    public void setChoices() {
        choices.clear();
        while (choices.size() < 4) {
            int i = (int) (Math.random() * 10);
            if (!choices.contains(names.get(i)) && names.get(i) != names.get(indexNum)) {
                choices.add(names.get(i));
            } else {
                continue;
            }
        }
        answerIndex = (int) (Math.random() * 4);
        this.choices.set(answerIndex, names.get(indexNum));
        this.b0.setText(choices.get(0));
        this.b1.setText(choices.get(1));
        this.b2.setText(choices.get(2));
        this.b3.setText(choices.get(3));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        picture = findViewById(R.id.imageView);
        b0 = findViewById(R.id.button0);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);

//        HTMLReceiver receiver = new HTMLReceiver();
//        String result = null;

//        try {
//            result = receiver.execute("https://svenskainfluencers.nu/svenska-influenser/").get();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//        Log.i("result", result);

        html = "1. MARGAUX DIETZ</em></h4>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<p>Programledare, mamma och framgångsrik Youtuber det blir väl inte bättre? Trots detta är allas favorit Marguax Dietz fortfarande singel ….eller?&nbsp;</p>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<figure class=\"wp-block-image size-large\"><img src=\"https://svenskainfluencers.nu/wp-content/uploads/Skärmavbild-2020-06-28-kl.-22.33.08.png\" alt=\"\" class=\"wp-image-231\" srcset=\"https://svenskainfluencers.nu/wp-content/uploads/Skärmavbild-2020-06-28-kl.-22.33.08.png 344w, https://svenskainfluencers.nu/wp-content/uploads/Skärmavbild-2020-06-28-kl.-22.33.08-239x300.png 239w\" sizes=\"(max-width: 344px) 100vw, 344px\" /><figcaption>Instagram: Marguax Dietz</figcaption></figure>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<h4><em>2. ISABELLA LÖWENGRIP&nbsp;</em></h4>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<p>Hon har drivit Sveriges bästa blogg, en stark entreprenör och mamma, vem skulle tacka nej till att vara med Isabella Löwngrip?&nbsp;</p>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<figure class=\"wp-block-image size-large\"><img src=\"https://svenskainfluencers.nu/wp-content/uploads/Skärmavbild-2020-06-28-kl.-22.31.16.png\" alt=\"\" class=\"wp-image-230\" srcset=\"https://svenskainfluencers.nu/wp-content/uploads/Skärmavbild-2020-06-28-kl.-22.31.16.png 346w, https://svenskainfluencers.nu/wp-content/uploads/Skärmavbild-2020-06-28-kl.-22.31.16-236x300.png 236w\" sizes=\"(max-width: 346px) 100vw, 346px\" /><figcaption>Instagram: Isabella Löwengrip</figcaption></figure>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<h4><em>3. BENJAMIN INGROSSO</em></h4>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<p>En av Sveriges sexigaste artister letar fortfarande efter någon, hur är det ens möjligt? </p>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<figure class=\"wp-block-image size-large\"><img src=\"https://svenskainfluencers.nu/wp-content/uploads/Skärmavbild-2020-06-28-kl.-22.30.30.png\" alt=\"\" class=\"wp-image-229\" srcset=\"https://svenskainfluencers.nu/wp-content/uploads/Skärmavbild-2020-06-28-kl.-22.30.30.png 440w, https://svenskainfluencers.nu/wp-content/uploads/Skärmavbild-2020-06-28-kl.-22.30.30-300x201.png 300w\" sizes=\"(max-width: 440px) 100vw, 440px\" /><figcaption>Instagram: Benjamin Ingrosso</figcaption></figure>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<h4><em>4. LINN AHLBORG</em></h4>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<p>Influencern och entreprenören är fortfarande singel, men heta sommarnätter kanske ändrar på det?</p>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<figure class=\"wp-block-image size-large\"><img src=\"https://svenskainfluencers.nu/wp-content/uploads/Skärmavbild-2020-06-28-kl.-22.29.25.png\" alt=\"\" class=\"wp-image-228\" srcset=\"https://svenskainfluencers.nu/wp-content/uploads/Skärmavbild-2020-06-28-kl.-22.29.25.png 269w, https://svenskainfluencers.nu/wp-content/uploads/Skärmavbild-2020-06-28-kl.-22.29.25-248x300.png 248w\" sizes=\"(max-width: 269px) 100vw, 269px\" /><figcaption>Instagram: Linn Ahlborg</figcaption></figure>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<h4><em>5. OSCAR ENESTAD</em></h4>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<p>Oscar Enstad, 23, sägs ha dejtat Cecilia Dahlbom, 52, men nu har de avföljt varandra på sociala medier så kanske är han redo för kärleken igen?&nbsp;</p>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<figure class=\"wp-block-image size-large\"><img src=\"https://svenskainfluencers.nu/wp-content/uploads/Skärmavbild-2020-06-28-kl.-22.28.17.png\" alt=\"\" class=\"wp-image-227\" srcset=\"https://svenskainfluencers.nu/wp-content/uploads/Skärmavbild-2020-06-28-kl.-22.28.17.png 353w, https://svenskainfluencers.nu/wp-content/uploads/Skärmavbild-2020-06-28-kl.-22.28.17-239x300.png 239w\" sizes=\"(max-width: 353px) 100vw, 353px\" /><figcaption>Instagram: Oscar Enestad</figcaption></figure>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<h4><em>6. OSCAR ZIA</em></h4>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<p>Vad vi hört är han fortfarande singel! (Hur är det ens möjligt?)</p>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<figure class=\"wp-block-image size-large\"><img src=\"https://svenskainfluencers.nu/wp-content/uploads/Skärmavbild-2020-06-28-kl.-22.27.21.png\" alt=\"\" class=\"wp-image-226\" srcset=\"https://svenskainfluencers.nu/wp-content/uploads/Skärmavbild-2020-06-28-kl.-22.27.21.png 349w, https://svenskainfluencers.nu/wp-content/uploads/Skärmavbild-2020-06-28-kl.-22.27.21-239x300.png 239w\" sizes=\"(max-width: 349px) 100vw, 349px\" /><figcaption>Instagram: Oscar Zia</figcaption></figure>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<h4><em>7. MAURI HERMUNDSSON</em></h4>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<p>Finns det någon charmigare än Marui? Att detta charmtroll fortfarande är singel håller nog inte länge. Frågan är bara vem som vinner hans hjärta? </p>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<figure class=\"wp-block-image size-large\"><img src=\"https://svenskainfluencers.nu/wp-content/uploads/Skärmavbild-2020-06-28-kl.-22.26.42.png\" alt=\"\" class=\"wp-image-225\" srcset=\"https://svenskainfluencers.nu/wp-content/uploads/Skärmavbild-2020-06-28-kl.-22.26.42.png 324w, https://svenskainfluencers.nu/wp-content/uploads/Skärmavbild-2020-06-28-kl.-22.26.42-300x296.png 300w\" sizes=\"(max-width: 324px) 100vw, 324px\" /><figcaption>Instagram: Mauri Hermundsson</figcaption></figure>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<h4><em>8. FELICIA BERGSTRÖM</em></h4>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<p>Hur glad blir man inte när man tittar på Felicia? Vad vi vet är hon singel och kanske redo att hitta den rätta? </p>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<figure class=\"wp-block-image size-large\"><img src=\"https://svenskainfluencers.nu/wp-content/uploads/Skärmavbild-2020-06-28-kl.-22.24.06.png\" alt=\"\" class=\"wp-image-224\" srcset=\"https://svenskainfluencers.nu/wp-content/uploads/Skärmavbild-2020-06-28-kl.-22.24.06.png 344w, https://svenskainfluencers.nu/wp-content/uploads/Skärmavbild-2020-06-28-kl.-22.24.06-235x300.png 235w\" sizes=\"(max-width: 344px) 100vw, 344px\" /><figcaption>Instagram: Felicia Bergström</figcaption></figure>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<h4><em>9. ALICE STENLÖF </em></h4>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<p>Alice är nybliven singel och kanske inte är redo för kärleken ännu men även hon finns ute på marknaden.&nbsp;<em></em></p>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<figure class=\"wp-block-image size-large\"><img src=\"https://svenskainfluencers.nu/wp-content/uploads/Skärmavbild-2020-06-28-kl.-22.21.13.png\" alt=\"\" class=\"wp-image-223\" srcset=\"https://svenskainfluencers.nu/wp-content/uploads/Skärmavbild-2020-06-28-kl.-22.21.13.png 389w, https://svenskainfluencers.nu/wp-content/uploads/Skärmavbild-2020-06-28-kl.-22.21.13-266x300.png 266w\" sizes=\"(max-width: 389px) 100vw, 389px\" /><figcaption>Instagram: Alice Stenlöf</figcaption></figure>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<h4><em> 10. NICOLE FALCIANI </em></h4>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<p>Efter att bröllopet med Erik Saade blev inställt så är artisten singel. Hon kanske inte är redo att träffa någon än men hon finns på marknaden. </p>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<figure class=\"wp-block-image size-large\"><img src=\"https://svenskainfluencers.nu/wp-content/uploads/Skärmavbild-2020-06-28-kl.-22.19.55-1.png\" alt=\"\" class=\"wp-image-222\" srcset=\"https://svenskainfluencers.nu/wp-content/uploads/Skärmavbild-2020-06-28-kl.-22.19.55-1.png 350w, https://svenskainfluencers.nu/wp-content/uploads/Skärmavbild-2020-06-28-kl.-22.19.55-1-238x300.png 238w\" sizes=\"(max-width: 350px) 100vw, 350px\" /><figcaption>Instagram: Nicole Falciani</figcaption></figure>\n";


        Pattern p = Pattern.compile("img src=\"(.*?)\" alt");
        Matcher m = p.matcher(html);
        while (m.find()) {
             imageSource.add(m.group(1));

        }

        Pattern p2 = Pattern.compile("Instagram: (.*?)</figcaption>");
        Matcher m2 = p2.matcher(html);

        while (m2.find()) {
            names.add(m2.group(1));
        }


        this.setImage();
        this.setChoices();
    }
}