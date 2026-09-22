package in.naukaricrown.app;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class AppActivity extends Activity {
  static final int BLACK=Color.rgb(15,15,16), GOLD=Color.rgb(211,169,45), MUTED=Color.rgb(108,108,116), SOFT=Color.rgb(247,247,248), BORDER=Color.rgb(231,231,235);
  LinearLayout page;

  int dp(float v){return Math.round(v*getResources().getDisplayMetrics().density);}
  GradientDrawable box(int c,float r){GradientDrawable d=new GradientDrawable();d.setColor(c);d.setCornerRadius(dp(r));return d;}
  GradientDrawable stroke(int c,float r,int s){GradientDrawable d=box(c,r);d.setStroke(dp(1),s);return d;}
  TextView text(String s,float z,int c,boolean b){TextView v=new TextView(this);v.setText(s);v.setTextSize(z);v.setTextColor(c);v.setIncludeFontPadding(false);if(b)v.setTypeface(Typeface.DEFAULT,Typeface.BOLD);return v;}
  LinearLayout.LayoutParams p(int w,int h){return new LinearLayout.LayoutParams(w,h);}
  void m(View v,int l,int t,int r,int b){LinearLayout.LayoutParams x=(LinearLayout.LayoutParams)v.getLayoutParams();x.setMargins(dp(l),dp(t),dp(r),dp(b));v.setLayoutParams(x);}
  ImageView logo(int size){ImageView i=new ImageView(this);i.setImageResource(R.drawable.naukari_logo);i.setScaleType(ImageView.ScaleType.CENTER_INSIDE);i.setLayoutParams(p(dp(size),dp(size)));return i;}

  @Override public void onCreate(Bundle b){
    super.onCreate(b);
    getWindow().setStatusBarColor(Color.WHITE);
    getWindow().setNavigationBarColor(Color.WHITE);
    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    login();
  }

  Button primary(String s){Button b=new Button(this);b.setText(s);b.setAllCaps(false);b.setTextSize(16);b.setTypeface(Typeface.DEFAULT,Typeface.BOLD);b.setTextColor(Color.WHITE);b.setBackground(box(BLACK,15));b.setStateListAnimator(null);b.setLayoutParams(p(-1,dp(56)));return b;}
  Button outline(String s){Button b=new Button(this);b.setText(s);b.setAllCaps(false);b.setTextSize(15);b.setTypeface(Typeface.DEFAULT,Typeface.BOLD);b.setTextColor(BLACK);b.setBackground(stroke(Color.WHITE,15,BLACK));b.setStateListAnimator(null);b.setLayoutParams(p(-1,dp(54)));return b;}
  EditText field(String hint,boolean pass){EditText e=new EditText(this);e.setHint(hint);e.setTextSize(16);e.setSingleLine();e.setTextColor(BLACK);e.setHintTextColor(Color.rgb(145,145,150));e.setPadding(dp(16),0,dp(16),0);e.setBackground(stroke(Color.WHITE,14,BORDER));e.setLayoutParams(p(-1,dp(56)));e.setInputType(pass?InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD:InputType.TYPE_CLASS_PHONE);return e;}

  void login(){
    ScrollView s=new ScrollView(this);s.setFillViewport(true);s.setBackgroundColor(Color.WHITE);
    LinearLayout r=new LinearLayout(this);r.setOrientation(LinearLayout.VERTICAL);r.setGravity(Gravity.CENTER_HORIZONTAL);r.setPadding(dp(24),dp(28),dp(24),dp(32));
    r.addView(logo(132));
    TextView brand=text("NAUKARI CROWN",27,BLACK,true);brand.setGravity(Gravity.CENTER);brand.setLayoutParams(p(-1,-2));m(brand,0,8,0,0);r.addView(brand);
    TextView sub=text("Competitive exam preparation, simplified.",14,MUTED,false);sub.setGravity(Gravity.CENTER);sub.setLayoutParams(p(-1,-2));m(sub,0,7,0,28);r.addView(sub);
    LinearLayout card=new LinearLayout(this);card.setOrientation(LinearLayout.VERTICAL);card.setPadding(dp(20),dp(22),dp(20),dp(22));card.setBackground(stroke(Color.WHITE,20,BORDER));card.setElevation(dp(3));card.setLayoutParams(p(-1,-2));
    card.addView(text("Welcome back",24,BLACK,true));
    TextView d=text("Login to continue your preparation",14,MUTED,false);d.setLayoutParams(p(-1,-2));m(d,0,6,0,20);card.addView(d);
    EditText mob=field("Mobile number",false);card.addView(mob);m(mob,0,0,0,12);
    EditText pass=field("Password",true);card.addView(pass);
    TextView f=text("Forgot Password?",14,GOLD,true);f.setGravity(Gravity.RIGHT);f.setLayoutParams(p(-1,-2));m(f,0,12,0,18);card.addView(f);
    Button l=primary("Log In");card.addView(l);
    Button otp=outline("Login via OTP");m(otp,0,12,0,0);card.addView(otp);
    TextView create=text("New student?  Create New Account",14,MUTED,true);create.setGravity(Gravity.CENTER);create.setLayoutParams(p(-1,-2));m(create,0,18,0,0);card.addView(create);
    l.setOnClickListener(v->home());otp.setOnClickListener(v->home());
    r.addView(card);s.addView(r);setContentView(s);
  }

  void shell(){
    LinearLayout root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setBackgroundColor(Color.WHITE);
    page=new LinearLayout(this);page.setOrientation(LinearLayout.VERTICAL);page.setPadding(dp(18),dp(14),dp(18),dp(26));
    ScrollView sc=new ScrollView(this);sc.addView(page);sc.setLayoutParams(new LinearLayout.LayoutParams(-1,0,1));root.addView(sc);
    root.addView(nav());setContentView(root);
  }

  LinearLayout nav(){
    LinearLayout n=new LinearLayout(this);n.setOrientation(LinearLayout.HORIZONTAL);n.setGravity(Gravity.CENTER);n.setPadding(dp(3),dp(8),dp(3),dp(8));n.setBackgroundColor(Color.WHITE);n.setElevation(dp(12));n.setLayoutParams(p(-1,dp(72)));
    navItem(n,"⌂","Home",v->home());navItem(n,"▣","Tests",v->tests());navItem(n,"▶","Courses",v->courses());navItem(n,"●","Current",v->current());navItem(n,"◉","Profile",v->profile());return n;
  }
  void navItem(LinearLayout n,String i,String s,View.OnClickListener c){LinearLayout x=new LinearLayout(this);x.setOrientation(LinearLayout.VERTICAL);x.setGravity(Gravity.CENTER);x.setLayoutParams(new LinearLayout.LayoutParams(0,-1,1));TextView a=text(i,20,BLACK,true);a.setGravity(Gravity.CENTER);x.addView(a);TextView b=text(s,11,MUTED,false);b.setGravity(Gravity.CENTER);x.addView(b);x.setOnClickListener(c);n.addView(x);}

  void header(String title,String sub){
    LinearLayout h=new LinearLayout(this);h.setOrientation(LinearLayout.HORIZONTAL);h.setGravity(Gravity.CENTER_VERTICAL);h.setLayoutParams(p(-1,-2));h.addView(logo(52));
    LinearLayout t=new LinearLayout(this);t.setOrientation(LinearLayout.VERTICAL);t.setPadding(dp(10),0,0,0);t.setLayoutParams(new LinearLayout.LayoutParams(0,-2,1));t.addView(text(title,21,BLACK,true));t.addView(text(sub,13,MUTED,false));h.addView(t);
    TextView bell=text("●",20,GOLD,true);bell.setGravity(Gravity.CENTER);bell.setLayoutParams(p(dp(44),dp(44)));bell.setBackground(box(SOFT,22));h.addView(bell);page.addView(h);
  }
  TextView section(String s){TextView t=text(s,20,BLACK,true);t.setLayoutParams(p(-1,-2));return t;}
  TextView pill(String s){TextView t=text(s,11,BLACK,true);t.setGravity(Gravity.CENTER);t.setPadding(dp(11),dp(7),dp(11),dp(7));t.setBackground(box(Color.rgb(249,240,211),20));return t;}
  LinearLayout wide(String title,String meta,String tag,View.OnClickListener c){LinearLayout x=new LinearLayout(this);x.setOrientation(LinearLayout.VERTICAL);x.setPadding(dp(16),dp(16),dp(16),dp(16));x.setBackground(stroke(Color.WHITE,18,BORDER));x.setElevation(dp(1));x.setLayoutParams(p(-1,-2));LinearLayout top=new LinearLayout(this);top.setGravity(Gravity.CENTER_VERTICAL);top.setLayoutParams(p(-1,-2));TextView h=text(title,16,BLACK,true);h.setLayoutParams(new LinearLayout.LayoutParams(0,-2,1));top.addView(h);top.addView(pill(tag));x.addView(top);TextView d=text(meta,13,MUTED,false);d.setLayoutParams(p(-1,-2));m(d,0,10,0,0);x.addView(d);x.setOnClickListener(c);return x;}
  LinearLayout exam(String icon,String title,String meta,View.OnClickListener c){LinearLayout x=new LinearLayout(this);x.setOrientation(LinearLayout.VERTICAL);x.setPadding(dp(14),dp(15),dp(14),dp(15));x.setBackground(stroke(Color.WHITE,18,BORDER));x.setElevation(dp(1));x.setLayoutParams(new LinearLayout.LayoutParams(0,dp(126),1));x.addView(text(icon,23,GOLD,true));TextView h=text(title,16,BLACK,true);h.setLayoutParams(p(-1,-2));m(h,0,8,0,4);x.addView(h);x.addView(text(meta,12,MUTED,false));x.setOnClickListener(c);return x;}

  void home(){
    shell();header("Naukari Crown","Your preparation dashboard");
    LinearLayout search=new LinearLayout(this);search.setGravity(Gravity.CENTER_VERTICAL);search.setPadding(dp(15),0,dp(15),0);search.setBackground(box(SOFT,16));search.setLayoutParams(p(-1,dp(52)));m(search,0,18,0,0);search.addView(text("⌕  Search tests, courses, PYQs...",15,MUTED,false));page.addView(search);
    LinearLayout hero=new LinearLayout(this);hero.setOrientation(LinearLayout.VERTICAL);hero.setPadding(dp(20),dp(20),dp(20),dp(20));hero.setBackground(box(BLACK,22));hero.setLayoutParams(p(-1,-2));m(hero,0,18,0,0);hero.addView(text("SELECTION MODE",11,GOLD,true));TextView ht=text("Prepare smarter.\nScore higher.",27,Color.WHITE,true);ht.setLayoutParams(p(-1,-2));m(ht,0,8,0,0);hero.addView(ht);TextView hd=text("Daily targets, mock tests, PYQs, courses and performance analysis.",14,Color.rgb(205,205,210),false);hd.setLayoutParams(p(-1,-2));m(hd,0,10,0,16);hero.addView(hd);Button start=primary("Start Mock Test");start.setTextColor(BLACK);start.setBackground(box(GOLD,14));start.setOnClickListener(v->tests());hero.addView(start);page.addView(hero);
    TextView e=section("Exam Categories");m(e,0,24,0,12);page.addView(e);
    LinearLayout r1=new LinearLayout(this);r1.addView(exam("★","Bihar Police","Mock • PYQ • Course",v->examPage("Bihar Police")));Space g1=new Space(this);g1.setLayoutParams(p(dp(10),1));r1.addView(g1);r1.addView(exam("✓","SSC","CGL • CHSL • GD",v->examPage("SSC")));page.addView(r1);
    LinearLayout r2=new LinearLayout(this);r2.setLayoutParams(p(-1,-2));m(r2,0,10,0,0);r2.addView(exam("↯","Railway","NTPC • Group D",v->examPage("Railway")));Space g2=new Space(this);g2.setLayoutParams(p(dp(10),1));r2.addView(g2);r2.addView(exam("₹","Banking","PO • Clerk • RRB",v->examPage("Banking")));page.addView(r2);
    TextView rec=section("Recommended for you");m(rec,0,24,0,12);page.addView(rec);
    LinearLayout a=wide("Bihar Police Full Mock Test - 01","100 Questions • 90 min • Hindi / English","FREE",v->tests());page.addView(a);m(a,0,0,0,10);page.addView(wide("SSC GD Daily Practice","25 Questions • Topic-wise analysis","DAILY",v->tests()));
    TextView con=section("Continue Learning");m(con,0,24,0,12);page.addView(con);page.addView(wide("Bihar Police Target Batch","Lucent + NCERT + Current Affairs • 42% completed","COURSE",v->courses()));
  }

  void examPage(String name){shell();header(name,"All preparation resources");TextView s=section("Choose a module");m(s,0,24,0,12);page.addView(s);String[] list={"Mock Tests","Practice Questions","Previous Year Papers","Courses","Study Material / PDFs","Current Affairs","Exam Pattern","Syllabus"};for(String z:list){LinearLayout c=wide(z,"Open "+name+" "+z.toLowerCase(),"OPEN",v->Toast.makeText(this,"Module ready for backend connection",Toast.LENGTH_SHORT).show());page.addView(c);m(c,0,0,0,10);}}
  void tests(){shell();header("Mock Tests","Practice in real exam conditions");TextView s=section("Recommended Tests");m(s,0,24,0,12);page.addView(s);page.addView(wide("Bihar Police Full Mock Test - 01","100 Questions • 90 min • bilingual • detailed analysis","START",v->Toast.makeText(this,"Test engine screen is next module",Toast.LENGTH_SHORT).show()));LinearLayout a=wide("SSC GD Practice Test","80 Questions • 60 min • rank enabled","FREE",v->{});m(a,0,10,0,0);page.addView(a);LinearLayout b=wide("Railway NTPC Mini Mock","25 Questions • 20 min • section analysis","NEW",v->{});m(b,0,10,0,0);page.addView(b);TextView x=section("Performance");m(x,0,26,0,12);page.addView(x);page.addView(wide("Accuracy 78%","12 tests attempted • Best percentile 91.4","ANALYSIS",v->{}));}
  void courses(){shell();header("Courses","Free & paid batches");TextView s=section("Popular Courses");m(s,0,24,0,12);page.addView(s);page.addView(wide("Bihar Police Target Batch 3.0","Video lectures • PDFs • Tests • Full guidance","POPULAR",v->{}));LinearLayout a=wide("SSC GD 2027 Mentorship","Maths • Reasoning • Language • GK/GS","FREE",v->{});m(a,0,10,0,0);page.addView(a);LinearLayout b=wide("Railway NTPC Target Batch","Recorded classes • Practice • Test series","PAID",v->{});m(b,0,10,0,0);page.addView(b);}
  void current(){shell();header("Current Affairs","Daily exam-relevant updates");TextView s=section("Today");m(s,0,24,0,12);page.addView(s);page.addView(wide("Daily Current Affairs","National, international and exam-focused updates","READ",v->{}));LinearLayout a=wide("Current Affairs Quiz","10 questions • instant score","QUIZ",v->{});m(a,0,10,0,0);page.addView(a);LinearLayout b=wide("Monthly Current Affairs PDF","Revision-ready downloadable notes","PDF",v->{});m(b,0,10,0,0);page.addView(b);}
  void profile(){shell();header("My Profile","Account & progress");LinearLayout h=new LinearLayout(this);h.setGravity(Gravity.CENTER_VERTICAL);h.setPadding(dp(16),dp(18),dp(16),dp(18));h.setBackground(box(SOFT,20));h.setLayoutParams(p(-1,-2));m(h,0,20,0,0);h.addView(logo(72));LinearLayout q=new LinearLayout(this);q.setOrientation(LinearLayout.VERTICAL);q.setPadding(dp(14),0,0,0);q.addView(text("Student",20,BLACK,true));q.addView(text("Naukari Crown learner",13,MUTED,false));h.addView(q);page.addView(h);TextView s=section("Account & Learning");m(s,0,24,0,12);page.addView(s);String[] list={"Purchased Courses","Test History","Saved Questions","Bookmarked PDFs","Performance Statistics","Achievements","Payments & Receipts","Notifications"};for(String z:list){LinearLayout c=wide(z,"View and manage","›",v->{});page.addView(c);m(c,0,0,0,9);}Button out=outline("Log out");m(out,0,14,0,0);out.setOnClickListener(v->login());page.addView(out);}
}
