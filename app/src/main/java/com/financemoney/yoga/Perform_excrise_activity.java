package com.financemoney.yoga;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.financemoney.yoga.database.Favourite_databse;
import com.financemoney.yoga.helper.Youga_helper;
import com.financemoney.yoga.internalstorage.Constants;
import com.financemoney.yoga.internalstorage.Mypreference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Perform_excrise_activity extends AppCompatActivity implements View.OnClickListener {
    private static final String DONE = "Done";
    private static final String GETREADY = "Get Ready";
    private static final String SKEEP = "Skeep";
    private static final String START = "Start";

    LinearLayout bannerContainer;

    CountDownTimer countDownTimer;
    int counter;

    TextView countertext;

    public int currenposition = 0;
    public int exceriseimage;
    TextView exceriseinstruction;
    TextView excerisename;
    ImageView exitbtn;

    Favourite_databse favouritedatabse;

    boolean islike = false;

    RelativeLayout likeunlikebtn;
    ImageView lileicon;
    MediaPlayer mediaPlayer;

    public Mypreference mypreference;

    ProgressBar progressBar;
    LinearLayout restview;

    TextView startbtntext;
    int startdelaycounter = 6;

    RelativeLayout workoutview;

    public ArrayList<Youga_helper> yougahelpers = new ArrayList<>();
    ImageView yougaimage;

    static int access(Perform_excrise_activity performexcriseactivity) {
        int i = performexcriseactivity.currenposition;
        performexcriseactivity.currenposition = i + 1;
        return i;
    }


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_perform_excrise_activity);
        this.mypreference = new Mypreference(this);
        this.favouritedatabse = new Favourite_databse(this);
        this.bannerContainer = (LinearLayout) findViewById(R.id.bannerContainer);
        this.excerisename = (TextView) findViewById(R.id.excerise_name);
        this.exitbtn = (ImageView) findViewById(R.id.exit_btn);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.countertext = (TextView) findViewById(R.id.counter);
        this.restview = (LinearLayout) findViewById(R.id.rest_view);
        this.workoutview = (RelativeLayout) findViewById(R.id.workout_view);
        this.exceriseinstruction = (TextView) findViewById(R.id.excerise_instruction);
        this.likeunlikebtn = (RelativeLayout) findViewById(R.id.like_unlike_btn);
        this.startbtntext = (TextView) findViewById(R.id.start_btn_text);
        this.yougaimage = (ImageView) findViewById(R.id.youga_image);
        this.lileicon = (ImageView) findViewById(R.id.lile_icon);
        this.yougahelpers = (ArrayList) getIntent().getSerializableExtra("youga_helpers");
        this.currenposition = getIntent().getIntExtra("current_position", 0);
        this.excerisename.setText(getIntent().getStringExtra("excerise_name"));
        this.exceriseimage = getIntent().getIntExtra("excerise_image", 0);
        this.exitbtn.setOnClickListener(this);

        this.exceriseinstruction.setText(this.yougahelpers.get(this.currenposition).getInstruction());

        Glide.with((FragmentActivity) this).asBitmap().load(Uri.parse(this.yougahelpers.get(this.currenposition).getYougaimage())).into(this.yougaimage);
        Typeface createFromAsset = Typeface.createFromAsset(getAssets(), "fonts/BubbleBobble.ttf");
        Typeface createFromAsset2 = Typeface.createFromAsset(getAssets(), "fonts/baloo_regular.ttf");
        this.countertext.setTypeface(createFromAsset);
        this.startbtntext.setTypeface(createFromAsset);
        this.excerisename.setTypeface(createFromAsset2);
        this.exceriseinstruction.setTypeface(createFromAsset2);
        this.progressBar.setMax(this.mypreference.getInt(Constants.WORKOUT_SECOND));
        TextView textView = this.countertext;
        textView.setText(this.mypreference.getInt(Constants.WORKOUT_SECOND) + "");
        this.startbtntext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Perform_excrise_activity.this.startbtntext.getText().equals(Perform_excrise_activity.SKEEP)) {
                    Perform_excrise_activity.this.startdelaytimer(5);
                } else if (Perform_excrise_activity.this.startbtntext.getText().equals(Perform_excrise_activity.DONE)) {
                    Perform_excrise_activity.this.playfile(R.raw.finish);
                    Perform_excrise_activity.this.countDownTimer.cancel();
                    TextView access300 = Perform_excrise_activity.this.countertext;
                    access300.setText(Perform_excrise_activity.this.mypreference.getInt(Constants.WORKOUT_SECOND) + "");
                    Perform_excrise_activity.this.progressBar.setProgress(Perform_excrise_activity.this.mypreference.getInt(Constants.WORKOUT_SECOND));
                    Perform_excrise_activity.this.startbtntext.setText(Perform_excrise_activity.START);
                } else if (Perform_excrise_activity.this.startbtntext.getText().equals(Perform_excrise_activity.START)) {
                    Perform_excrise_activity.this.startdelaytimer(5);
                }
            }
        });
        this.likeunlikebtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Perform_excrise_activity.this.islike) {
                    Perform_excrise_activity.this.islike = false;
                    Perform_excrise_activity performexcriseactivity = Perform_excrise_activity.this;
                    performexcriseactivity.setfav(performexcriseactivity.islike, ((Youga_helper) Perform_excrise_activity.this.yougahelpers.get(Perform_excrise_activity.this.currenposition)).getId());
                    Perform_excrise_activity.this.checkfav();
                } else if (!Perform_excrise_activity.this.islike) {
                    Perform_excrise_activity.this.islike = true;
                    Perform_excrise_activity performexcriseactivity2 = Perform_excrise_activity.this;
                    performexcriseactivity2.setfav(performexcriseactivity2.islike, ((Youga_helper) Perform_excrise_activity.this.yougahelpers.get(Perform_excrise_activity.this.currenposition)).getId());
                    Perform_excrise_activity.this.checkfav();
                }
            }
        });
        new Main_application().loadBanner(this.bannerContainer, this);
        new Main_application().loadIndestrialads(this);
        checkfav();
    }

    public void checkfav() {
        if (this.favouritedatabse.isfav(this.yougahelpers.get(this.currenposition).getId())) {
            this.islike = true;
            this.lileicon.setImageResource(R.mipmap.ic_fav1);
            return;
        }
        this.islike = false;
        this.lileicon.setImageResource(R.mipmap.ic_fav);
    }

    public void setfav(boolean z, String str) {
        this.favouritedatabse.updatedata(str, z ? "1" : "0");
    }

    public void timer(int i) {
        this.counter = 0;
        this.progressBar.setMax(i);
        CountDownTimer countDownTimer2 = this.countDownTimer;
        if (countDownTimer2 != null) {
            countDownTimer2.cancel();
        }
        MediaPlayer mediaPlayer2 = this.mediaPlayer;
        if (mediaPlayer2 != null && mediaPlayer2.isPlaying()) {
            this.mediaPlayer.stop();
            this.mediaPlayer.release();
        }
        final int i2 = i;
        this.countDownTimer = new CountDownTimer((long) (i * 1000), 1000) {
            public void onTick(long j) {
                Perform_excrise_activity.this.progressBar.setProgress(Perform_excrise_activity.this.counter);
                TextView access300 = Perform_excrise_activity.this.countertext;
                access300.setText((i2 - Perform_excrise_activity.this.counter) + "");
                Perform_excrise_activity performexcriseactivity = Perform_excrise_activity.this;
                performexcriseactivity.counter = performexcriseactivity.counter + 1;
            }

            public void onFinish() {
                Perform_excrise_activity performexcriseactivity = Perform_excrise_activity.this;
                performexcriseactivity.counter = 0;
                if (performexcriseactivity.mypreference.getString(Constants.EXCERISE_OPTION).equals(Constants.AUTOMATICALLY)) {
                    Perform_excrise_activity performexcriseactivity2 = Perform_excrise_activity.this;
                    performexcriseactivity2.runresttimer(performexcriseactivity2.mypreference.getInt(Constants.WORKOUT_REST));
                    return;
                }
                Perform_excrise_activity.this.progressBar.setProgress(i2);
                Perform_excrise_activity.this.countertext.setText("0");
                Perform_excrise_activity.this.startbtntext.setText(Perform_excrise_activity.START);
            }
        }.start();
    }

    public void startdelaytimer(int i) {
        this.startdelaycounter = 6;
        this.counter = 0;
        this.progressBar.setMax(i);
        CountDownTimer countDownTimer2 = this.countDownTimer;
        if (countDownTimer2 != null) {
            countDownTimer2.cancel();
        }
        playfile(R.raw.ready);
        this.countDownTimer = new CountDownTimer((long) (i * 1000), 1000) {
            public void onTick(long j) {
                Perform_excrise_activity performexcriseactivity = Perform_excrise_activity.this;
                performexcriseactivity.startdelaycounter--;
                Perform_excrise_activity.this.counter++;
                Perform_excrise_activity.this.progressBar.setProgress(Perform_excrise_activity.this.counter);
                Perform_excrise_activity.this.countertext.setText(Perform_excrise_activity.this.startdelaycounter + "");
                Perform_excrise_activity.this.startbtntext.setText(Perform_excrise_activity.GETREADY);
            }

            public void onFinish() {
                if (Perform_excrise_activity.this.currenposition + 1 == Perform_excrise_activity.this.yougahelpers.size()) {
                    new Main_application().loadIndestrialads(Perform_excrise_activity.this);
                }
                if (Perform_excrise_activity.this.currenposition + 1 == 10) {
                    Calendar instance = Calendar.getInstance();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    if (!Perform_excrise_activity.this.favouritedatabse.isdateinserted(simpleDateFormat.format(instance.getTime()))) {
                        Perform_excrise_activity.this.favouritedatabse.inserthistroy(simpleDateFormat.format(instance.getTime()));
                    }
                }
                Perform_excrise_activity performexcriseactivity = Perform_excrise_activity.this;
                performexcriseactivity.counter = 0;
                performexcriseactivity.startdelaycounter = 6;
                if (performexcriseactivity.currenposition >= Perform_excrise_activity.this.yougahelpers.size()) {
                    Perform_excrise_activity.this.currenposition = 0;
                    Perform_excrise_activity performexcriseactivity2 = Perform_excrise_activity.this;
                    performexcriseactivity2.loadautomatically(performexcriseactivity2.currenposition);
                    Perform_excrise_activity.access(Perform_excrise_activity.this);
                    return;
                }
                Perform_excrise_activity performexcriseactivity3 = Perform_excrise_activity.this;
                performexcriseactivity3.loadautomatically(performexcriseactivity3.currenposition);
                Perform_excrise_activity.access(Perform_excrise_activity.this);
            }
        }.start();
    }

    public void runresttimer(int i) {
        this.restview.setVisibility(View.VISIBLE);
        this.workoutview.setVisibility(View.GONE);
        this.counter = 0;
        this.progressBar.setMax(i);
        CountDownTimer countDownTimer2 = this.countDownTimer;
        if (countDownTimer2 != null) {
            countDownTimer2.cancel();
        }
        playfile(R.raw.rest);
        final int i2 = i;
        this.countDownTimer = new CountDownTimer((long) (i * 1000), 1000) {
            public void onTick(long j) {
                Perform_excrise_activity.this.progressBar.setProgress(Perform_excrise_activity.this.counter);
                TextView access300 = Perform_excrise_activity.this.countertext;
                access300.setText((i2 - Perform_excrise_activity.this.counter) + "");
                Perform_excrise_activity.this.startbtntext.setText(Perform_excrise_activity.SKEEP);
                Perform_excrise_activity performexcriseactivity = Perform_excrise_activity.this;
                performexcriseactivity.counter = performexcriseactivity.counter + 1;
            }

            public void onFinish() {
                Perform_excrise_activity performexcriseactivity = Perform_excrise_activity.this;
                performexcriseactivity.counter = 0;
                performexcriseactivity.startdelaytimer(5);
            }
        }.start();
    }

    public void loadautomatically(int i) {
        this.progressBar.setProgress(0);
        this.exceriseinstruction.setText(this.yougahelpers.get(i).getInstruction());
        if (!isFinishing()) {
            Glide.with((FragmentActivity) this).asBitmap().load(Uri.parse(this.yougahelpers.get(i).getYougaimage())).into(this.yougaimage);
        }
        this.excerisename.setText(this.yougahelpers.get(i).getYouganame());
        this.restview.setVisibility(View.GONE);
        this.workoutview.setVisibility(View.VISIBLE);
        this.startbtntext.setText(DONE);
        timer(this.mypreference.getInt(Constants.WORKOUT_SECOND));
        checkfav();
    }


    public void onClick(View view) {
        if (view.getId() == R.id.exit_btn) {
            finish();
        }
    }

    public void playfile(int i) {
        if (this.mediaPlayer != null) {
            stopPlayer();
        }
        this.mediaPlayer = MediaPlayer.create(this, i);
        this.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
        this.mediaPlayer.start();
    }


    @Override
    public void onPause() {
        super.onPause();
        stopPlayer();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        stopPlayer();
    }

    public void stopPlayer() {
        try {
            if (this.mediaPlayer != null) {
                this.mediaPlayer.stop();
                this.mediaPlayer.release();
                this.mediaPlayer.reset();
                this.mediaPlayer = null;
            }
        } catch (Exception e) {
            this.mediaPlayer = null;
            e.printStackTrace();
        }
    }
}
