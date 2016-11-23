package com.example.admin.w6d2menufragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTAG_";
    private boolean firstMenu;
    private boolean secondMenu;
    private boolean thirdMenu;
    private ShareActionProvider miShareAction;
    private Intent shareIntent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ImageView ivImage = (ImageView) findViewById(R.id.aMainImage);
        // Load image async from remote URL, setup share when completed
        Picasso.with(this).load("http://latinodek.com/wp-content/uploads/2015/02/chivas-rayadas.png").into(ivImage, new Callback() {
            @Override
            public void onSuccess() {
                // Setup share intent now that image has loaded
                Log.d(TAG, "onSuccess: ");
                prepareShareIntent();
                attachShareIntentAction();
            }

            @Override
            public void onError() {
                // ...
                Log.d(TAG, "onError: ");
            }
        });

    }

    public Uri getLocalBitmapUri(ImageView imageView) {
        // Extract Bitmap from ImageView drawable
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable){
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }
        // Store image to default external storage directory
        Uri bmpUri = null;
        try {
            // Use methods on Context to access package-specific directories on external storage.
            // This way, you don't need to request external read/write permission.
            // See https://youtu.be/5xVh-7ywKpE?t=25m25s
            File file =  new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            // **Warning:** This will fail for API > 24, use a FileProvider as shown below instead.
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }

    public void prepareShareIntent() {
        // Fetch Bitmap Uri locally
        ImageView ivImage = (ImageView) findViewById(R.id.aMainImage);
        Uri bmpUri = getLocalBitmapUri(ivImage); // see previous remote images section
        // Construct share intent as described above based on bitmap
        shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
        shareIntent.setType("image/*");
    }

    // Attaches the share intent to the share menu item provider
    public void attachShareIntentAction() {
        if (miShareAction != null && shareIntent != null)
            miShareAction.setShareIntent(shareIntent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Log.d(TAG, "onCreateOptionsMenu: ");
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem item = menu.findItem(R.id.action_share);
        // Fetch reference to the share action provider
        miShareAction = (ShareActionProvider) MenuItemCompat.getActionProvider(item);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_share:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;


            case R.id.action_hello:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            case R.id.action_bye:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            case R.id.action_other:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            case R.id.action_another:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.d(TAG, "onPrepareOptionsMenu: ");
        MenuItem hello = menu.findItem(R.id.action_hello);
        MenuItem bye = menu.findItem(R.id.action_bye);
        MenuItem favorite = menu.findItem(R.id.action_share);
        MenuItem other = menu.findItem(R.id.action_other);
        MenuItem another = menu.findItem(R.id.action_another);
        MenuItem settings = menu.findItem(R.id.action_settings);

        if(firstMenu){
            settings.setVisible(false);
            other.setVisible(false);
            another.setVisible(false);
            hello.setVisible(true);
            bye.setVisible(true);
        }else if(secondMenu){
            settings.setVisible(false);
            other.setVisible(true);
            another.setVisible(false);
            hello.setVisible(false);
            bye.setVisible(false);
        }else if(thirdMenu){
            settings.setVisible(false);
            other.setVisible(false);
            another.setVisible(true);
            hello.setVisible(false);
            bye.setVisible(false);

        }else{
            hello.setVisible(false);
            bye.setVisible(false);
            favorite.setVisible(true);
            other.setVisible(false);
            another.setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public void menuFirst(View view) {
        firstMenu = true;
        secondMenu = false;
        thirdMenu = false;
    }

    public void menuSecond(View view) {
        secondMenu = true;
        firstMenu = false;
        thirdMenu = false;
    }

    public void menuThird(View view) {
        thirdMenu = true;
        secondMenu = false;
        firstMenu = false;
    }
}
