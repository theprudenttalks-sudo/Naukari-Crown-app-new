package in.naukaricrown.app;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {
    private int dp(float v) {
        return Math.round(v * getResources().getDisplayMetrics().density);
    }

    private TextView text(String value, float size, int color, boolean bold) {
        TextView v = new TextView(this);
        v.setText(value);
        v.setTextSize(size);
        v.setTextColor(color);
        if (bold) v.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        return v;
    }

    private Button card(String title) {
        Button b = new Button(this);
        b.setText(title);
        b.setAllCaps(false);
        b.setTextSize(16);
        b.setTextColor(Color.rgb(20,20,20));
        b.setBackgroundColor(Color.rgb(247,247,247));
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, dp(58));
        p.setMargins(0, dp(6), 0, dp(6));
        b.setLayoutParams(p);
        return b;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(Color.WHITE);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(20), dp(24), dp(20), dp(30));

        TextView brand = text("♛  NAUKARI CROWN", 23, Color.rgb(25,25,25), true);
        brand.setGravity(Gravity.CENTER_VERTICAL);
        root.addView(brand);

        TextView sub = text("Competitive Exam Preparation", 14, Color.rgb(110,110,110), false);
        LinearLayout.LayoutParams sp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        sp.setMargins(0, dp(4), 0, dp(22));
        sub.setLayoutParams(sp);
        root.addView(sub);

        TextView welcome = text("Prepare. Practice. Perform.", 25, Color.BLACK, true);
        root.addView(welcome);

        TextView hint = text("Mock tests, courses, PYQs, current affairs and performance analysis in one place.", 15, Color.DKGRAY, false);
        LinearLayout.LayoutParams hp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        hp.setMargins(0, dp(8), 0, dp(18));
        hint.setLayoutParams(hp);
        root.addView(hint);

        TextView exams = text("Popular Exams", 19, Color.BLACK, true);
        root.addView(exams);
        root.addView(card("Bihar Police"));
        root.addView(card("SSC"));
        root.addView(card("Railway"));
        root.addView(card("Banking"));
        root.addView(card("Defence & State Police"));

        TextView tools = text("Quick Access", 19, Color.BLACK, true);
        LinearLayout.LayoutParams tp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        tp.setMargins(0, dp(22), 0, dp(6));
        tools.setLayoutParams(tp);
        root.addView(tools);

        root.addView(card("Mock Tests"));
        root.addView(card("Daily Quiz"));
        root.addView(card("Courses"));
        root.addView(card("Current Affairs"));
        root.addView(card("Previous Year Papers"));

        TextView note = text("Naukari Crown • Android 13+ ready • Target SDK 35", 12, Color.GRAY, false);
        LinearLayout.LayoutParams np = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        np.setMargins(0, dp(26), 0, 0);
        note.setLayoutParams(np);
        note.setGravity(Gravity.CENTER);
        root.addView(note);

        scroll.addView(root);
        setContentView(scroll);
    }
}
