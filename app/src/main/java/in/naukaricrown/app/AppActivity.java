package in.naukaricrown.app;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import java.util.*;

public class AppActivity extends Activity {
    static final int INK = Color.rgb(18,18,20);
    static final int GOLD = Color.rgb(211,169,45);
    static final int GOLD_SOFT = Color.rgb(250,244,224);
    static final int MUTED = Color.rgb(110,110,118);
    static final int LINE = Color.rgb(232,232,236);
    static final int SOFT = Color.rgb(247,247,249);
    static final int GREEN = Color.rgb(31,143,87);
    LinearLayout page;
    int selectedAnswer = -1;
    int questionIndex = 0;
    TextView timerText;

    final String[] questions = {
        "भारतीय संविधान का अनुच्छेद 21 किससे संबंधित है?",
        "15% of 480 is equal to:",
        "Which river is known as the 'Sorrow of Bihar'?",
        "यदि A, B का भाई है और B, C की बहन है, तो A का C से क्या संबंध है?",
        "The SI unit of force is:"
    };
    final String[][] opts = {
        {"समानता का अधिकार","जीवन एवं व्यक्तिगत स्वतंत्रता","धर्म की स्वतंत्रता","संवैधानिक उपचार"},
        {"62","68","72","76"},
        {"Gandak","Kosi","Son","Punpun"},
        {"भाई","बहन","पिता","निर्धारित नहीं किया जा सकता"},
        {"Joule","Newton","Pascal","Watt"}
    };
    final int[] keys = {1,2,1,0,1};

    int dp(float v){ return Math.round(v * getResources().getDisplayMetrics().density); }

    GradientDrawable solid(int color, float radius){
        GradientDrawable d = new GradientDrawable();
        d.setColor(color); d.setCornerRadius(dp(radius));
        return d;
    }
    GradientDrawable bordered(int color, float radius, int border){
        GradientDrawable d = solid(color, radius);
        d.setStroke(dp(1), border); return d;
    }
    GradientDrawable gradient(int c1, int c2, float radius){
        GradientDrawable d = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[]{c1,c2});
        d.setCornerRadius(dp(radius)); return d;
    }
    TextView txt(String s, float size, int color, boolean bold){
        TextView t = new TextView(this);
        t.setText(s); t.setTextSize(size); t.setTextColor(color);
        t.setIncludeFontPadding(false);
        if(bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        return t;
    }
    LinearLayout.LayoutParams lp(int w,int h){ return new LinearLayout.LayoutParams(w,h); }
    void margins(View v,int l,int t,int r,int b){
        LinearLayout.LayoutParams p=(LinearLayout.LayoutParams)v.getLayoutParams();
        p.setMargins(dp(l),dp(t),dp(r),dp(b)); v.setLayoutParams(p);
    }
    ImageView logo(int size){
        ImageView i=new ImageView(this);
        i.setImageResource(R.drawable.naukari_logo);
        i.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        i.setLayoutParams(lp(dp(size),dp(size)));
        return i;
    }
    TextView action(String label,int bg,int fg){
        TextView t=txt(label,15,fg,true);
        t.setGravity(Gravity.CENTER);
        t.setBackground(solid(bg,15));
        t.setLayoutParams(lp(-1,dp(54)));
        return t;
    }
    TextView outlineAction(String label){
        TextView t=action(label,Color.WHITE,INK);
        t.setBackground(bordered(Color.WHITE,15,LINE));
        return t;
    }

    @Override public void onCreate(Bundle b){
        super.onCreate(b);
        getWindow().setStatusBarColor(Color.WHITE);
        getWindow().setNavigationBarColor(Color.WHITE);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        showLogin();
    }

    EditText input(String hint, boolean password){
        EditText e=new EditText(this);
        e.setHint(hint);
        e.setSingleLine(true);
        e.setTextSize(16);
        e.setTextColor(INK);
        e.setHintTextColor(Color.rgb(150,150,156));
        e.setPadding(dp(16),0,dp(16),0);
        e.setBackground(bordered(Color.WHITE,15,LINE));
        e.setLayoutParams(lp(-1,dp(56)));
        e.setInputType(password ? InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD : InputType.TYPE_CLASS_PHONE);
        return e;
    }

    void showLogin(){
        ScrollView sc=new ScrollView(this);
        sc.setFillViewport(true); sc.setBackgroundColor(Color.WHITE);
        LinearLayout root=new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setPadding(dp(24),dp(26),dp(24),dp(34));

        root.addView(logo(118));
        TextView name=txt("NAUKARI CROWN",28,INK,true);
        name.setGravity(Gravity.CENTER); name.setLayoutParams(lp(-1,-2)); margins(name,0,8,0,0); root.addView(name);
        TextView tag=txt("Learn • Practice • Perform",14,MUTED,false);
        tag.setGravity(Gravity.CENTER); tag.setLayoutParams(lp(-1,-2)); margins(tag,0,7,0,28); root.addView(tag);

        LinearLayout card=new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(20),dp(22),dp(20),dp(22));
        card.setBackground(bordered(Color.WHITE,22,LINE));
        card.setElevation(dp(4));
        card.setLayoutParams(lp(-1,-2));

        card.addView(txt("Welcome back",24,INK,true));
        TextView sub=txt("Login to continue your preparation",14,MUTED,false);
        sub.setLayoutParams(lp(-1,-2)); margins(sub,0,6,0,18); card.addView(sub);

        EditText mobile=input("+91  Mobile number",false); card.addView(mobile); margins(mobile,0,0,0,12);
        EditText pass=input("Password",true); card.addView(pass);

        TextView forgot=txt("Forgot Password?",14,GOLD,true);
        forgot.setGravity(Gravity.RIGHT); forgot.setLayoutParams(lp(-1,-2)); margins(forgot,0,12,0,18); card.addView(forgot);

        TextView login=action("Log In",INK,Color.WHITE); card.addView(login);
        TextView otp=outlineAction("Login via OTP"); margins(otp,0,12,0,0); card.addView(otp);

        TextView newAcc=txt("New student?  Create New Account",14,INK,true);
        newAcc.setGravity(Gravity.CENTER); newAcc.setLayoutParams(lp(-1,-2)); margins(newAcc,0,20,0,0); card.addView(newAcc);

        login.setOnClickListener(v->showHome());
        otp.setOnClickListener(v->showHome());

        root.addView(card);
        TextView terms=txt("By continuing, you agree to Naukari Crown Terms & Privacy Policy.",11,MUTED,false);
        terms.setGravity(Gravity.CENTER); terms.setLayoutParams(lp(-1,-2)); margins(terms,10,22,10,0); root.addView(terms);
        sc.addView(root); setContentView(sc);
    }

    void shell(){
        LinearLayout root=new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL); root.setBackgroundColor(Color.WHITE);

        ScrollView sc=new ScrollView(this);
        sc.setFillViewport(true);
        page=new LinearLayout(this);
        page.setOrientation(LinearLayout.VERTICAL);
        page.setPadding(dp(18),dp(14),dp(18),dp(28));
        sc.addView(page);
        sc.setLayoutParams(new LinearLayout.LayoutParams(-1,0,1));
        root.addView(sc);
        root.addView(bottomNav());
        setContentView(root);
    }

    LinearLayout bottomNav(){
        LinearLayout bar=new LinearLayout(this);
        bar.setOrientation(LinearLayout.HORIZONTAL);
        bar.setPadding(dp(4),dp(7),dp(4),dp(7));
        bar.setGravity(Gravity.CENTER);
        bar.setBackgroundColor(Color.WHITE);
        bar.setElevation(dp(14));
        bar.setLayoutParams(lp(-1,dp(72)));
        nav(bar,"⌂","Home",v->showHome());
        nav(bar,"▣","Tests",v->showTests());
        nav(bar,"▶","Courses",v->showCourses());
        nav(bar,"●","Current",v->showCurrent());
        nav(bar,"◉","Profile",v->showProfile());
        return bar;
    }
    void nav(LinearLayout bar,String icon,String label,View.OnClickListener l){
        LinearLayout item=new LinearLayout(this);
        item.setOrientation(LinearLayout.VERTICAL); item.setGravity(Gravity.CENTER);
        item.setLayoutParams(new LinearLayout.LayoutParams(0,-1,1));
        TextView i=txt(icon,20,INK,true); i.setGravity(Gravity.CENTER); item.addView(i);
        TextView t=txt(label,11,MUTED,false); t.setGravity(Gravity.CENTER); item.addView(t);
        item.setOnClickListener(l); bar.addView(item);
    }

    void appHeader(String title,String subtitle){
        LinearLayout row=new LinearLayout(this);
        row.setGravity(Gravity.CENTER_VERTICAL); row.setLayoutParams(lp(-1,-2));
        row.addView(logo(48));
        LinearLayout t=new LinearLayout(this); t.setOrientation(LinearLayout.VERTICAL);
        t.setPadding(dp(10),0,0,0); t.setLayoutParams(new LinearLayout.LayoutParams(0,-2,1));
        t.addView(txt(title,20,INK,true)); t.addView(txt(subtitle,12,MUTED,false)); row.addView(t);
        TextView bell=txt("●",17,GOLD,true); bell.setGravity(Gravity.CENTER);
        bell.setLayoutParams(lp(dp(42),dp(42))); bell.setBackground(solid(SOFT,21)); row.addView(bell);
        page.addView(row);
    }

    TextView section(String s){
        TextView t=txt(s,19,INK,true); t.setLayoutParams(lp(-1,-2)); return t;
    }
    TextView chip(String s){
        TextView t=txt(s,11,INK,true); t.setGravity(Gravity.CENTER);
        t.setPadding(dp(11),dp(7),dp(11),dp(7));
        t.setBackground(solid(GOLD_SOFT,18)); return t;
    }

    LinearLayout card(String title,String subtitle,String badge,View.OnClickListener l){
        LinearLayout c=new LinearLayout(this);
        c.setOrientation(LinearLayout.VERTICAL);
        c.setPadding(dp(16),dp(15),dp(16),dp(15));
        c.setBackground(bordered(Color.WHITE,18,LINE));
        c.setElevation(dp(2)); c.setLayoutParams(lp(-1,-2));

        LinearLayout top=new LinearLayout(this); top.setGravity(Gravity.CENTER_VERTICAL); top.setLayoutParams(lp(-1,-2));
        TextView h=txt(title,16,INK,true); h.setLayoutParams(new LinearLayout.LayoutParams(0,-2,1)); top.addView(h);
        if(badge!=null) top.addView(chip(badge));
        c.addView(top);

        TextView s=txt(subtitle,13,MUTED,false); s.setLayoutParams(lp(-1,-2)); margins(s,0,8,0,0); c.addView(s);
        if(l!=null)c.setOnClickListener(l);
        return c;
    }

    LinearLayout quick(String icon,String title,String subtitle,View.OnClickListener l){
        LinearLayout c=new LinearLayout(this); c.setOrientation(LinearLayout.VERTICAL);
        c.setPadding(dp(14),dp(14),dp(14),dp(14));
        c.setBackground(bordered(Color.WHITE,18,LINE)); c.setElevation(dp(1));
        c.setLayoutParams(new LinearLayout.LayoutParams(0,dp(116),1));
        TextView i=txt(icon,22,GOLD,true); c.addView(i);
        TextView h=txt(title,15,INK,true); h.setLayoutParams(lp(-1,-2)); margins(h,0,9,0,3); c.addView(h);
        c.addView(txt(subtitle,11,MUTED,false));
        c.setOnClickListener(l); return c;
    }

    LinearLayout rowTwo(LinearLayout a,LinearLayout b){
        LinearLayout r=new LinearLayout(this); r.setOrientation(LinearLayout.HORIZONTAL); r.setLayoutParams(lp(-1,-2));
        r.addView(a); Space sp=new Space(this); sp.setLayoutParams(lp(dp(10),1)); r.addView(sp); r.addView(b); return r;
    }

    void showHome(){
        shell(); appHeader("Hello, Student 👋","Ready for today's target?");

        LinearLayout search=new LinearLayout(this);
        search.setGravity(Gravity.CENTER_VERTICAL); search.setPadding(dp(15),0,dp(15),0);
        search.setBackground(solid(SOFT,16)); search.setLayoutParams(lp(-1,dp(52))); margins(search,0,18,0,0);
        search.addView(txt("⌕  Search tests, courses, PYQs, PDFs...",14,MUTED,false)); page.addView(search);

        LinearLayout hero=new LinearLayout(this); hero.setOrientation(LinearLayout.VERTICAL);
        hero.setPadding(dp(20),dp(20),dp(20),dp(20)); hero.setBackground(gradient(Color.rgb(15,15,16),Color.rgb(52,42,14),24));
        hero.setLayoutParams(lp(-1,-2)); margins(hero,0,18,0,0);
        hero.addView(txt("BIHAR POLICE • TARGET BATCH",11,GOLD,true));
        TextView h=txt("Your preparation,\nall in one place.",27,Color.WHITE,true); h.setLayoutParams(lp(-1,-2)); margins(h,0,9,0,0); hero.addView(h);
        TextView hs=txt("Mock tests, courses, PYQs, PDFs, current affairs and detailed analysis.",13,Color.rgb(210,210,214),false);
        hs.setLayoutParams(lp(-1,-2)); margins(hs,0,9,0,16); hero.addView(hs);
        TextView start=action("Start Free Mock Test",GOLD,INK); start.setOnClickListener(v->showTests()); hero.addView(start);
        page.addView(hero);

        TextView q=section("Quick Access"); margins(q,0,24,0,12); page.addView(q);
        page.addView(rowTwo(quick("▣","Mock Tests","Rank & analysis",v->showTests()),quick("▶","Courses","Free + paid",v->showCourses())));
        LinearLayout qr2=rowTwo(quick("⌁","PYQs","Previous papers",v->showExam("Previous Year Papers")),quick("▤","PDFs","Study material",v->showExam("Study Material")));
        margins(qr2,0,10,0,0); page.addView(qr2);

        TextView e=section("Exam Categories"); margins(e,0,24,0,12); page.addView(e);
        HorizontalScrollView hsv=new HorizontalScrollView(this); hsv.setHorizontalScrollBarEnabled(false); hsv.setLayoutParams(lp(-1,-2));
        LinearLayout cats=new LinearLayout(this); cats.setOrientation(LinearLayout.HORIZONTAL);
        String[] names={"Bihar Police","SSC","Railway","Banking","Defence","Teaching"};
        String[] icons={"★","✓","↯","₹","◆","✦"};
        for(int i=0;i<names.length;i++){
            final String n=names[i];
            LinearLayout c=new LinearLayout(this); c.setOrientation(LinearLayout.VERTICAL); c.setPadding(dp(15),dp(14),dp(15),dp(14));
            c.setBackground(bordered(Color.WHITE,18,LINE)); c.setLayoutParams(lp(dp(150),dp(104)));
            c.addView(txt(icons[i],21,GOLD,true)); TextView nn=txt(n,14,INK,true); nn.setLayoutParams(lp(-1,-2)); margins(nn,0,8,0,0); c.addView(nn);
            c.setOnClickListener(v->showExam(n));
            cats.addView(c); Space sp=new Space(this); sp.setLayoutParams(lp(dp(10),1)); cats.addView(sp);
        }
        hsv.addView(cats); page.addView(hsv);

        TextView d=section("Today's Practice"); margins(d,0,24,0,12); page.addView(d);
        LinearLayout quiz=card("Daily Quiz • 10 Questions","Current Affairs + GK/GS • Instant score","START",v->startDemoTest());
        page.addView(quiz);

        TextView ctitle=section("Continue Learning"); margins(ctitle,0,24,0,12); page.addView(ctitle);
        LinearLayout course=card("Bihar Police Target Batch 3.0","Lucent + NCERT + Current Affairs","42%",v->showCourses());
        ProgressBar pb=new ProgressBar(this,null,android.R.attr.progressBarStyleHorizontal);
        pb.setMax(100); pb.setProgress(42); pb.setProgressTintList(ColorStateList.valueOf(GOLD)); pb.setProgressBackgroundTintList(ColorStateList.valueOf(Color.rgb(235,235,238)));
        pb.setLayoutParams(lp(-1,dp(6))); margins(pb,0,13,0,0); course.addView(pb); page.addView(course);

        TextView news=section("Current Affairs"); margins(news,0,24,0,12); page.addView(news);
        page.addView(card("Daily Current Affairs • 22 Sept","Exam-focused headlines + quiz + PDF","READ",v->showCurrent()));

        LinearLayout note=new LinearLayout(this); note.setOrientation(LinearLayout.VERTICAL); note.setPadding(dp(16),dp(16),dp(16),dp(16));
        note.setBackground(solid(GOLD_SOFT,18)); note.setLayoutParams(lp(-1,-2)); margins(note,0,18,0,0);
        note.addView(txt("Latest Announcement",12,GOLD,true));
        TextView n=txt("New mock tests and course updates will appear here.",14,INK,true); n.setLayoutParams(lp(-1,-2)); margins(n,0,6,0,0); note.addView(n); page.addView(note);
    }

    void showExam(String name){
        shell(); appHeader(name,"Preparation resources");
        TextView h=section("Explore"); margins(h,0,24,0,12); page.addView(h);
        String[][] items={
            {"Mock Tests","Full & sectional tests","TESTS"},
            {"Practice Questions","Topic-wise practice","PRACTICE"},
            {"Previous Year Papers","PYQs with solutions","PYQ"},
            {"Courses","Live + recorded batches","COURSE"},
            {"Study Material","PDF notes & books","PDF"},
            {"Current Affairs","Daily + monthly","CA"},
            {"Exam Pattern","Latest pattern details","INFO"},
            {"Syllabus","Subject-wise syllabus","INFO"}
        };
        for(String[] it:items){LinearLayout c=card(it[0],it[1],it[2],v->Toast.makeText(this,"Backend connection pending for this module",Toast.LENGTH_SHORT).show()); page.addView(c); margins(c,0,0,0,10);}
    }

    void showTests(){
        shell(); appHeader("Mock Tests","Real exam-style practice");
        LinearLayout stat=new LinearLayout(this); stat.setOrientation(LinearLayout.HORIZONTAL); stat.setPadding(dp(16),dp(16),dp(16),dp(16));
        stat.setBackground(solid(SOFT,18)); stat.setLayoutParams(lp(-1,-2)); margins(stat,0,20,0,0);
        stat.addView(statCell("12","Attempts")); stat.addView(statCell("78%","Accuracy")); stat.addView(statCell("91.4","Best %ile")); page.addView(stat);

        TextView h=section("Recommended Tests"); margins(h,0,24,0,12); page.addView(h);
        LinearLayout a=card("Bihar Police Full Mock Test - 01","100 Questions • 90 min • Hindi / English • Detailed analysis","FREE",v->startDemoTest()); page.addView(a);
        LinearLayout b=card("SSC GD Practice Test","80 Questions • 60 min • Rank enabled","START",v->startDemoTest()); margins(b,0,10,0,0); page.addView(b);
        LinearLayout c=card("Railway NTPC Mini Mock","25 Questions • 20 min • Section analysis","NEW",v->startDemoTest()); margins(c,0,10,0,0); page.addView(c);

        TextView p=section("Performance"); margins(p,0,24,0,12); page.addView(p);
        page.addView(card("Your test analysis","Correct 74 • Incorrect 21 • Unattempted 5","VIEW",v->{}));
    }

    LinearLayout statCell(String big,String small){
        LinearLayout x=new LinearLayout(this); x.setOrientation(LinearLayout.VERTICAL); x.setGravity(Gravity.CENTER);
        x.setLayoutParams(new LinearLayout.LayoutParams(0,-2,1));
        TextView b=txt(big,20,INK,true); b.setGravity(Gravity.CENTER); x.addView(b);
        TextView s=txt(small,11,MUTED,false); s.setGravity(Gravity.CENTER); x.addView(s); return x;
    }

    void showCourses(){
        shell(); appHeader("Courses","Free & paid batches");
        TextView h=section("Popular Courses"); margins(h,0,24,0,12); page.addView(h);
        LinearLayout a=courseCard("Bihar Police Target Batch 3.0","Complete syllabus • Tests • PDFs • Guidance","₹199","POPULAR"); page.addView(a);
        LinearLayout b=courseCard("SSC GD 2027 Mentorship","Maths • Reasoning • Language • GK/GS","FREE","FREE"); margins(b,0,12,0,0); page.addView(b);
        LinearLayout c=courseCard("Railway NTPC Target Batch","Recorded classes • Practice • Full test series","₹199","NEW"); margins(c,0,12,0,0); page.addView(c);
    }

    LinearLayout courseCard(String title,String sub,String price,String badge){
        LinearLayout c=card(title,sub,badge,null);
        LinearLayout foot=new LinearLayout(this); foot.setGravity(Gravity.CENTER_VERTICAL); foot.setLayoutParams(lp(-1,-2)); margins(foot,0,14,0,0);
        TextView p=txt(price,18,INK,true); p.setLayoutParams(new LinearLayout.LayoutParams(0,-2,1)); foot.addView(p);
        TextView buy=txt("View Course",13,Color.WHITE,true); buy.setGravity(Gravity.CENTER); buy.setBackground(solid(INK,12)); buy.setLayoutParams(lp(dp(110),dp(40))); foot.addView(buy);
        c.addView(foot); return c;
    }

    void showCurrent(){
        shell(); appHeader("Current Affairs","Daily exam-relevant updates");
        TextView h=section("Today"); margins(h,0,24,0,12); page.addView(h);
        page.addView(card("Daily Current Affairs","National • International • Bihar • Economy","READ",v->{}));
        LinearLayout q=card("Current Affairs Quiz","10 questions • Instant score","QUIZ",v->startDemoTest()); margins(q,0,10,0,0); page.addView(q);
        LinearLayout m=card("Monthly Current Affairs PDF","Revision-ready compiled PDF","PDF",v->{}); margins(m,0,10,0,0); page.addView(m);
        TextView cat=section("Categories"); margins(cat,0,24,0,12); page.addView(cat);
        page.addView(rowTwo(quick("◎","National","India updates",v->{}),quick("₹","Economy","Budget & finance",v->{})));
        LinearLayout r=rowTwo(quick("⚑","Bihar","State updates",v->{}),quick("◈","Science","Tech & space",v->{})); margins(r,0,10,0,0); page.addView(r);
    }

    void showProfile(){
        shell(); appHeader("My Profile","Account & progress");
        LinearLayout profile=new LinearLayout(this); profile.setGravity(Gravity.CENTER_VERTICAL); profile.setPadding(dp(16),dp(18),dp(16),dp(18));
        profile.setBackground(gradient(Color.rgb(248,244,232),Color.WHITE,20)); profile.setLayoutParams(lp(-1,-2)); margins(profile,0,20,0,0);
        profile.addView(logo(70));
        LinearLayout t=new LinearLayout(this); t.setOrientation(LinearLayout.VERTICAL); t.setPadding(dp(14),0,0,0);
        t.addView(txt("Student",20,INK,true)); t.addView(txt("Naukari Crown Learner",13,MUTED,false)); profile.addView(t); page.addView(profile);

        TextView p=section("Progress"); margins(p,0,24,0,12); page.addView(p);
        page.addView(rowTwo(quick("✓","78%","Accuracy",v->{}),quick("★","12","Tests",v->{})));

        TextView h=section("Account & Learning"); margins(h,0,24,0,12); page.addView(h);
        String[] list={"Purchased Courses","Test History","Saved Questions","Bookmarked PDFs","Performance Statistics","Achievements","Payments & Receipts","Notifications"};
        for(String s:list){LinearLayout c=card(s,"View and manage","›",v->{}); page.addView(c); margins(c,0,0,0,9);}
        TextView logout=outlineAction("Log out"); margins(logout,0,12,0,0); logout.setOnClickListener(v->showLogin()); page.addView(logout);
    }

    void startDemoTest(){
        questionIndex=0; selectedAnswer=-1;
        LinearLayout root=new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setBackgroundColor(Color.WHITE);

        LinearLayout top=new LinearLayout(this); top.setGravity(Gravity.CENTER_VERTICAL); top.setPadding(dp(16),dp(12),dp(16),dp(12));
        top.setBackgroundColor(INK);
        TextView close=txt("‹",30,Color.WHITE,true); close.setGravity(Gravity.CENTER); close.setLayoutParams(lp(dp(42),dp(42))); close.setOnClickListener(v->showTests()); top.addView(close);
        TextView name=txt("Mock Test",17,Color.WHITE,true); name.setLayoutParams(new LinearLayout.LayoutParams(0,-2,1)); top.addView(name);
        timerText=txt("05:00",15,GOLD,true); top.addView(timerText); root.addView(top);

        ScrollView sc=new ScrollView(this); sc.setLayoutParams(new LinearLayout.LayoutParams(-1,0,1));
        page=new LinearLayout(this); page.setOrientation(LinearLayout.VERTICAL); page.setPadding(dp(18),dp(18),dp(18),dp(24)); sc.addView(page); root.addView(sc);

        LinearLayout controls=new LinearLayout(this); controls.setOrientation(LinearLayout.HORIZONTAL); controls.setPadding(dp(12),dp(8),dp(12),dp(8));
        controls.setBackgroundColor(Color.WHITE); controls.setElevation(dp(12)); controls.setLayoutParams(lp(-1,dp(72)));
        TextView mark=outlineAction("Mark for Review"); mark.setLayoutParams(new LinearLayout.LayoutParams(0,dp(50),1)); controls.addView(mark);
        Space gap=new Space(this); gap.setLayoutParams(lp(dp(10),1)); controls.addView(gap);
        TextView next=action("Save & Next",INK,Color.WHITE); next.setLayoutParams(new LinearLayout.LayoutParams(0,dp(50),1)); controls.addView(next);
        next.setOnClickListener(v->{ if(questionIndex<questions.length-1){questionIndex++;selectedAnswer=-1;renderQuestion();} else showResult();});
        root.addView(controls); setContentView(root);

        new CountDownTimer(300000,1000){
            public void onTick(long m){long s=m/1000; timerText.setText(String.format(Locale.US,"%02d:%02d",s/60,s%60));}
            public void onFinish(){showResult();}
        }.start();
        renderQuestion();
    }

    void renderQuestion(){
        page.removeAllViews();
        TextView progress=txt("Question "+(questionIndex+1)+" of "+questions.length,12,GOLD,true); page.addView(progress);
        ProgressBar pb=new ProgressBar(this,null,android.R.attr.progressBarStyleHorizontal); pb.setMax(questions.length); pb.setProgress(questionIndex+1);
        pb.setProgressTintList(ColorStateList.valueOf(GOLD)); pb.setProgressBackgroundTintList(ColorStateList.valueOf(LINE));
        pb.setLayoutParams(lp(-1,dp(6))); margins(pb,0,10,0,20); page.addView(pb);

        LinearLayout qcard=new LinearLayout(this); qcard.setOrientation(LinearLayout.VERTICAL); qcard.setPadding(dp(18),dp(18),dp(18),dp(18));
        qcard.setBackground(bordered(Color.WHITE,18,LINE)); qcard.setLayoutParams(lp(-1,-2)); qcard.setElevation(dp(2));
        TextView q=txt(questions[questionIndex],18,INK,true); q.setLineSpacing(0,1.15f); qcard.addView(q); page.addView(qcard);

        TextView choose=txt("Choose the correct answer",13,MUTED,false); choose.setLayoutParams(lp(-1,-2)); margins(choose,0,22,0,10); page.addView(choose);

        for(int i=0;i<4;i++){
            final int idx=i;
            TextView op=txt((char)('A'+i)+".  "+opts[questionIndex][i],15,INK,false);
            op.setGravity(Gravity.CENTER_VERTICAL); op.setPadding(dp(16),0,dp(16),0);
            op.setBackground(bordered(Color.WHITE,15,LINE)); op.setLayoutParams(lp(-1,dp(58))); margins(op,0,0,0,10);
            op.setOnClickListener(v->{selectedAnswer=idx; renderQuestionWithSelection();});
            page.addView(op);
        }

        TextView clear=txt("Clear response",13,MUTED,true); clear.setGravity(Gravity.RIGHT); clear.setLayoutParams(lp(-1,-2));
        clear.setOnClickListener(v->{selectedAnswer=-1;renderQuestion();}); page.addView(clear);

        TextView palette=section("Question Palette"); margins(palette,0,26,0,12); page.addView(palette);
        LinearLayout nums=new LinearLayout(this); nums.setOrientation(LinearLayout.HORIZONTAL);
        for(int i=0;i<questions.length;i++){
            final int qi=i; TextView n=txt(String.valueOf(i+1),13,i==questionIndex?Color.WHITE:INK,true); n.setGravity(Gravity.CENTER);
            n.setBackground(solid(i==questionIndex?INK:SOFT,18)); n.setLayoutParams(lp(dp(36),dp(36))); n.setOnClickListener(v->{questionIndex=qi;selectedAnswer=-1;renderQuestion();});
            nums.addView(n); Space sp=new Space(this); sp.setLayoutParams(lp(dp(8),1)); nums.addView(sp);
        }
        page.addView(nums);
    }

    void renderQuestionWithSelection(){
        renderQuestion();
        int baseIndex=4;
        int child=baseIndex;
        for(int i=0;i<4;i++){
            View v=page.getChildAt(child+i);
            if(v instanceof TextView && i==selectedAnswer){
                v.setBackground(bordered(GOLD_SOFT,15,GOLD));
            }
        }
    }

    void showResult(){
        shell(); appHeader("Test Result","Demo performance summary");
        LinearLayout hero=new LinearLayout(this); hero.setOrientation(LinearLayout.VERTICAL); hero.setGravity(Gravity.CENTER);
        hero.setPadding(dp(18),dp(24),dp(18),dp(24)); hero.setBackground(gradient(INK,Color.rgb(55,45,16),22)); hero.setLayoutParams(lp(-1,-2)); margins(hero,0,20,0,0);
        hero.addView(txt("SCORE",11,GOLD,true)); TextView sc=txt("4 / 5",36,Color.WHITE,true); sc.setGravity(Gravity.CENTER); hero.addView(sc);
        TextView per=txt("80% Accuracy • Demo Result",13,Color.rgb(215,215,220),false); per.setGravity(Gravity.CENTER); hero.addView(per); page.addView(hero);

        TextView h=section("Performance"); margins(h,0,24,0,12); page.addView(h);
        page.addView(rowTwo(quick("✓","4","Correct",v->{}),quick("×","1","Incorrect",v->{})));
        LinearLayout r=rowTwo(quick("—","0","Unattempted",v->{}),quick("★","80%","Accuracy",v->{})); margins(r,0,10,0,0); page.addView(r);
        LinearLayout a=card("Question-wise Solutions","Review answers and explanations","REVIEW",v->{}); margins(a,0,20,0,0); page.addView(a);
        TextView again=action("Attempt Another Test",INK,Color.WHITE); margins(again,0,16,0,0); again.setOnClickListener(v->showTests()); page.addView(again);
    }
}
