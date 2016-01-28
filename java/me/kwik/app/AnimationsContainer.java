package me.kwik.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.widget.ImageView;
import android.os.Handler;
import java.lang.ref.SoftReference;

/**
 * Created by Farid on 1/14/2016.
 */
public class AnimationsContainer {

    public int FPS = 23;  // animation FPS

    // single instance procedures
    private static AnimationsContainer mInstance;

    private AnimationsContainer() {
    };

    public static AnimationsContainer getInstance() {
        if (mInstance == null)
            mInstance = new AnimationsContainer();
        return mInstance;
    }

    // animation progress dialog frames
    private int[] mButtonToApAnimFrames = {
            R.drawable.button_to_ap_animation_frame1,
            R.drawable.button_to_ap_animation_frame2,
            R.drawable.button_to_ap_animation_frame3,
            R.drawable.button_to_ap_animation_frame4,
            R.drawable.button_to_ap_animation_frame5,
            R.drawable.button_to_ap_animation_frame6,
            R.drawable.button_to_ap_animation_frame7,
            R.drawable.button_to_ap_animation_frame8,
            R.drawable.button_to_ap_animation_frame9,
            R.drawable.button_to_ap_animation_frame10,
            R.drawable.button_to_ap_animation_frame11,
            R.drawable.button_to_ap_animation_frame12,
            R.drawable.button_to_ap_animation_frame13,
            R.drawable.button_to_ap_animation_frame14,
            R.drawable.button_to_ap_animation_frame15,
            R.drawable.button_to_ap_animation_frame16,
            R.drawable.button_to_ap_animation_frame17,
            R.drawable.button_to_ap_animation_frame18,
            R.drawable.button_to_ap_animation_frame19,
            R.drawable.button_to_ap_animation_frame20,
            R.drawable.button_to_ap_animation_frame21,
            R.drawable.button_to_ap_animation_frame22,
            R.drawable.button_to_ap_animation_frame23,
            R.drawable.button_to_ap_animation_frame24,
            R.drawable.button_to_ap_animation_frame25,
            R.drawable.button_to_ap_animation_frame26,
            R.drawable.button_to_ap_animation_frame27,
            R.drawable.button_to_ap_animation_frame28,
            R.drawable.button_to_ap_animation_frame29,
            R.drawable.button_to_ap_animation_frame30,
            R.drawable.button_to_ap_animation_frame31,
            R.drawable.button_to_ap_animation_frame32,
            R.drawable.button_to_ap_animation_frame33,
            R.drawable.button_to_ap_animation_frame34,
            R.drawable.button_to_ap_animation_frame35,
            R.drawable.button_to_ap_animation_frame36,
            R.drawable.button_to_ap_animation_frame37,
            R.drawable.button_to_ap_animation_frame38,
            R.drawable.button_to_ap_animation_frame39,
            R.drawable.button_to_ap_animation_frame40,
            R.drawable.button_to_ap_animation_frame41,
            R.drawable.button_to_ap_animation_frame42,
            R.drawable.button_to_ap_animation_frame43,
            R.drawable.button_to_ap_animation_frame44,
            R.drawable.button_to_ap_animation_frame45,
            R.drawable.button_to_ap_animation_frame46,
            R.drawable.button_to_ap_animation_frame47,
            R.drawable.button_to_ap_animation_frame48,
            R.drawable.button_to_ap_animation_frame49,
            R.drawable.button_to_ap_animation_frame50,
            R.drawable.button_to_ap_animation_frame51,
            R.drawable.button_to_ap_animation_frame52,
            R.drawable.button_to_ap_animation_frame53,
            R.drawable.button_to_ap_animation_frame54,
            R.drawable.button_to_ap_animation_frame55,
            R.drawable.button_to_ap_animation_frame56,
            R.drawable.button_to_ap_animation_frame57,
            R.drawable.button_to_ap_animation_frame58,
            R.drawable.button_to_ap_animation_frame59,
            R.drawable.button_to_ap_animation_frame60,
            R.drawable.button_to_ap_animation_frame61,
            R.drawable.button_to_ap_animation_frame62,
            R.drawable.button_to_ap_animation_frame63,
            R.drawable.button_to_ap_animation_frame64,
            R.drawable.button_to_ap_animation_frame65,
            R.drawable.button_to_ap_animation_frame66,
            R.drawable.button_to_ap_animation_frame67,
            R.drawable.button_to_ap_animation_frame68,
            R.drawable.button_to_ap_animation_frame69,
            R.drawable.button_to_ap_animation_frame70,
            R.drawable.button_to_ap_animation_frame71,
            R.drawable.button_to_ap_animation_frame72,
            R.drawable.button_to_ap_animation_frame73,
            R.drawable.button_to_ap_animation_frame74,
            R.drawable.button_to_ap_animation_frame75,
            R.drawable.button_to_ap_animation_frame76,
            R.drawable.button_to_ap_animation_frame77,
            R.drawable.button_to_ap_animation_frame78,
            R.drawable.button_to_ap_animation_frame79,
            R.drawable.button_to_ap_animation_frame80,
            R.drawable.button_to_ap_animation_frame81,
            R.drawable.button_to_ap_animation_frame82,
            R.drawable.button_to_ap_animation_frame83,
            R.drawable.button_to_ap_animation_frame84,
            R.drawable.button_to_ap_animation_frame85,
            R.drawable.button_to_ap_animation_frame86,
            R.drawable.button_to_ap_animation_frame87,
            R.drawable.button_to_ap_animation_frame88,
            R.drawable.button_to_ap_animation_frame89,
            R.drawable.button_to_ap_animation_frame90,
            R.drawable.button_to_ap_animation_frame91,
            R.drawable.button_to_ap_animation_frame92,
            R.drawable.button_to_ap_animation_frame93,
            R.drawable.button_to_ap_animation_frame94,
            R.drawable.button_to_ap_animation_frame95,
            R.drawable.button_to_ap_animation_frame96,
            R.drawable.button_to_ap_animation_frame97,
            R.drawable.button_to_ap_animation_frame98,
            R.drawable.button_to_ap_animation_frame99,
            R.drawable.button_to_ap_animation_frame100,
            R.drawable.button_to_ap_animation_frame101,
            R.drawable.button_to_ap_animation_frame102,
            R.drawable.button_to_ap_animation_frame103,
            R.drawable.button_to_ap_animation_frame104,
            R.drawable.button_to_ap_animation_frame105,
            R.drawable.button_to_ap_animation_frame106,
            R.drawable.button_to_ap_animation_frame107,
            R.drawable.button_to_ap_animation_frame108,
            R.drawable.button_to_ap_animation_frame109,
            R.drawable.button_to_ap_animation_frame110,
            R.drawable.button_to_ap_animation_frame111,
            R.drawable.button_to_ap_animation_frame112,
            R.drawable.button_to_ap_animation_frame113,
            R.drawable.button_to_ap_animation_frame114,
            R.drawable.button_to_ap_animation_frame115,
            R.drawable.button_to_ap_animation_frame116,
            R.drawable.button_to_ap_animation_frame117,
            R.drawable.button_to_ap_animation_frame118,
            R.drawable.button_to_ap_animation_frame119,
            R.drawable.button_to_ap_animation_frame120,
            R.drawable.button_to_ap_animation_frame121,
            R.drawable.button_to_ap_animation_frame122,
            R.drawable.button_to_ap_animation_frame123,
            R.drawable.button_to_ap_animation_frame124,
            R.drawable.button_to_ap_animation_frame125,
            R.drawable.button_to_ap_animation_frame126,
            R.drawable.button_to_ap_animation_frame127,
            R.drawable.button_to_ap_animation_frame128,
            R.drawable.button_to_ap_animation_frame129,
            R.drawable.button_to_ap_animation_frame130,
            R.drawable.button_to_ap_animation_frame131,
            R.drawable.button_to_ap_animation_frame132,
            R.drawable.button_to_ap_animation_frame133,
            R.drawable.button_to_ap_animation_frame134,
            R.drawable.button_to_ap_animation_frame135,
            R.drawable.button_to_ap_animation_frame136,
            R.drawable.button_to_ap_animation_frame137,
            R.drawable.button_to_ap_animation_frame138,
            R.drawable.button_to_ap_animation_frame139,
            R.drawable.button_to_ap_animation_frame140,
            R.drawable.button_to_ap_animation_frame141,
            R.drawable.button_to_ap_animation_frame142,
            R.drawable.button_to_ap_animation_frame143,
            R.drawable.button_to_ap_animation_frame144,
            R.drawable.button_to_ap_animation_frame145,
            R.drawable.button_to_ap_animation_frame146,
            R.drawable.button_to_ap_animation_frame147,
            R.drawable.button_to_ap_animation_frame148,
            R.drawable.button_to_ap_animation_frame149,
            R.drawable.button_to_ap_animation_frame150,
            R.drawable.button_to_ap_animation_frame151,
            R.drawable.button_to_ap_animation_frame152,
            R.drawable.button_to_ap_animation_frame153,
            R.drawable.button_to_ap_animation_frame154,
            R.drawable.button_to_ap_animation_frame155,
            R.drawable.button_to_ap_animation_frame156,
            R.drawable.button_to_ap_animation_frame157,
            R.drawable.button_to_ap_animation_frame158,
            R.drawable.button_to_ap_animation_frame159,
            R.drawable.button_to_ap_animation_frame160,
            R.drawable.button_to_ap_animation_frame161,
            R.drawable.button_to_ap_animation_frame162,
            R.drawable.button_to_ap_animation_frame163,
            R.drawable.button_to_ap_animation_frame164,
            R.drawable.button_to_ap_animation_frame165,
            R.drawable.button_to_ap_animation_frame166,
            R.drawable.button_to_ap_animation_frame167,
            R.drawable.button_to_ap_animation_frame168,
            R.drawable.button_to_ap_animation_frame169,
            R.drawable.button_to_ap_animation_frame170,
            R.drawable.button_to_ap_animation_frame171,
            R.drawable.button_to_ap_animation_frame172,
            R.drawable.button_to_ap_animation_frame173,
            R.drawable.button_to_ap_animation_frame174,
            R.drawable.button_to_ap_animation_frame175,
            R.drawable.button_to_ap_animation_frame176,
            R.drawable.button_to_ap_animation_frame177,
            R.drawable.button_to_ap_animation_frame178,
            R.drawable.button_to_ap_animation_frame179,
            R.drawable.button_to_ap_animation_frame180,
            R.drawable.button_to_ap_animation_frame181,
            R.drawable.button_to_ap_animation_frame182,
            R.drawable.button_to_ap_animation_frame183,
            R.drawable.button_to_ap_animation_frame184,
            R.drawable.button_to_ap_animation_frame185,
            R.drawable.button_to_ap_animation_frame186,
            R.drawable.button_to_ap_animation_frame187,
            R.drawable.button_to_ap_animation_frame188,
            R.drawable.button_to_ap_animation_frame189,
            R.drawable.button_to_ap_animation_frame190,
            R.drawable.button_to_ap_animation_frame191,
            R.drawable.button_to_ap_animation_frame192,
            R.drawable.button_to_ap_animation_frame193,
            R.drawable.button_to_ap_animation_frame194,
            R.drawable.button_to_ap_animation_frame195,
            R.drawable.button_to_ap_animation_frame196,
            R.drawable.button_to_ap_animation_frame197,
            R.drawable.button_to_ap_animation_frame198,
            R.drawable.button_to_ap_animation_frame199,
            R.drawable.button_to_ap_animation_frame200,
            R.drawable.button_to_ap_animation_frame201,
            R.drawable.button_to_ap_animation_frame202,
            R.drawable.button_to_ap_animation_frame203,
            R.drawable.button_to_ap_animation_frame204,
            R.drawable.button_to_ap_animation_frame205,
            R.drawable.button_to_ap_animation_frame206,
            R.drawable.button_to_ap_animation_frame207,
            R.drawable.button_to_ap_animation_frame208,
            R.drawable.button_to_ap_animation_frame209,
            R.drawable.button_to_ap_animation_frame210,
            R.drawable.button_to_ap_animation_frame211,
            R.drawable.button_to_ap_animation_frame212,
            R.drawable.button_to_ap_animation_frame213,
            R.drawable.button_to_ap_animation_frame214,
            R.drawable.button_to_ap_animation_frame215,
            R.drawable.button_to_ap_animation_frame216,
            R.drawable.button_to_ap_animation_frame217,
            R.drawable.button_to_ap_animation_frame218,
            R.drawable.button_to_ap_animation_frame219,
            R.drawable.button_to_ap_animation_frame220,
            R.drawable.button_to_ap_animation_frame221,
            R.drawable.button_to_ap_animation_frame222,
            R.drawable.button_to_ap_animation_frame223,
            R.drawable.button_to_ap_animation_frame224,
            R.drawable.button_to_ap_animation_frame225,
            R.drawable.button_to_ap_animation_frame226,
            R.drawable.button_to_ap_animation_frame227,
            R.drawable.button_to_ap_animation_frame228,
            R.drawable.button_to_ap_animation_frame229,
            R.drawable.button_to_ap_animation_frame230,
            R.drawable.button_to_ap_animation_frame231,
            R.drawable.button_to_ap_animation_frame232,
            R.drawable.button_to_ap_animation_frame233,
            R.drawable.button_to_ap_animation_frame234,
            R.drawable.button_to_ap_animation_frame235,
            R.drawable.button_to_ap_animation_frame236,
            R.drawable.button_to_ap_animation_frame237,
            R.drawable.button_to_ap_animation_frame238,
            R.drawable.button_to_ap_animation_frame239,
            R.drawable.button_to_ap_animation_frame240,
            R.drawable.button_to_ap_animation_frame241,
            R.drawable.button_to_ap_animation_frame242,
            R.drawable.button_to_ap_animation_frame243,
            R.drawable.button_to_ap_animation_frame244,
            R.drawable.button_to_ap_animation_frame245,
            R.drawable.button_to_ap_animation_frame246,
            R.drawable.button_to_ap_animation_frame247,
            R.drawable.button_to_ap_animation_frame248,
            R.drawable.button_to_ap_animation_frame249,
            R.drawable.button_to_ap_animation_frame250,
            R.drawable.button_to_ap_animation_frame251,
            R.drawable.button_to_ap_animation_frame252,
            R.drawable.button_to_ap_animation_frame253,
            R.drawable.button_to_ap_animation_frame254,
            R.drawable.button_to_ap_animation_frame255,
            R.drawable.button_to_ap_animation_frame256,
            R.drawable.button_to_ap_animation_frame257,
            R.drawable.button_to_ap_animation_frame258,
            R.drawable.button_to_ap_animation_frame259,
            R.drawable.button_to_ap_animation_frame260,
            R.drawable.button_to_ap_animation_frame261,
            R.drawable.button_to_ap_animation_frame262,
            R.drawable.button_to_ap_animation_frame263,
            R.drawable.button_to_ap_animation_frame264,
            R.drawable.button_to_ap_animation_frame265,
            R.drawable.button_to_ap_animation_frame266,
            R.drawable.button_to_ap_animation_frame267,
            R.drawable.button_to_ap_animation_frame268,
            R.drawable.button_to_ap_animation_frame269,
            R.drawable.button_to_ap_animation_frame270,
            R.drawable.button_to_ap_animation_frame271,
            R.drawable.button_to_ap_animation_frame272,
            R.drawable.button_to_ap_animation_frame273,
            R.drawable.button_to_ap_animation_frame274,
            R.drawable.button_to_ap_animation_frame275,
            R.drawable.button_to_ap_animation_frame276,
            R.drawable.button_to_ap_animation_frame277,
            R.drawable.button_to_ap_animation_frame278,
            R.drawable.button_to_ap_animation_frame279,
            R.drawable.button_to_ap_animation_frame280,
            R.drawable.button_to_ap_animation_frame281,
            R.drawable.button_to_ap_animation_frame282,
            R.drawable.button_to_ap_animation_frame283,
            R.drawable.button_to_ap_animation_frame284,
            R.drawable.button_to_ap_animation_frame285,
            R.drawable.button_to_ap_animation_frame286,
            R.drawable.button_to_ap_animation_frame287,
            R.drawable.button_to_ap_animation_frame288,
            R.drawable.button_to_ap_animation_frame289,
            R.drawable.button_to_ap_animation_frame290,
            R.drawable.button_to_ap_animation_frame291,
            R.drawable.button_to_ap_animation_frame292,
            R.drawable.button_to_ap_animation_frame293,
            R.drawable.button_to_ap_animation_frame294,
            R.drawable.button_to_ap_animation_frame295,
            R.drawable.button_to_ap_animation_frame296,
            R.drawable.button_to_ap_animation_frame297,
            R.drawable.button_to_ap_animation_frame298,
            R.drawable.button_to_ap_animation_frame299,
            R.drawable.button_to_ap_animation_frame300,
            R.drawable.button_to_ap_animation_frame301,
            R.drawable.button_to_ap_animation_frame302,
            R.drawable.button_to_ap_animation_frame303,
            R.drawable.button_to_ap_animation_frame304,
            R.drawable.button_to_ap_animation_frame305,
            R.drawable.button_to_ap_animation_frame306,
            R.drawable.button_to_ap_animation_frame307,
            R.drawable.button_to_ap_animation_frame308,
            R.drawable.button_to_ap_animation_frame309,
            R.drawable.button_to_ap_animation_frame310,
            R.drawable.button_to_ap_animation_frame311,
            R.drawable.button_to_ap_animation_frame312,
            R.drawable.button_to_ap_animation_frame313,
            R.drawable.button_to_ap_animation_frame314,
            R.drawable.button_to_ap_animation_frame315,
            R.drawable.button_to_ap_animation_frame316,
            R.drawable.button_to_ap_animation_frame317,
            R.drawable.button_to_ap_animation_frame318,
            R.drawable.button_to_ap_animation_frame319,
            R.drawable.button_to_ap_animation_frame320,
            R.drawable.button_to_ap_animation_frame321,
            R.drawable.button_to_ap_animation_frame322,
            R.drawable.button_to_ap_animation_frame323,
            R.drawable.button_to_ap_animation_frame324,
            R.drawable.button_to_ap_animation_frame325,
            R.drawable.button_to_ap_animation_frame326,
            R.drawable.button_to_ap_animation_frame327,
            R.drawable.button_to_ap_animation_frame328,
            R.drawable.button_to_ap_animation_frame329,
            R.drawable.button_to_ap_animation_frame330,
            R.drawable.button_to_ap_animation_frame331,
            R.drawable.button_to_ap_animation_frame332,
            R.drawable.button_to_ap_animation_frame333,
            R.drawable.button_to_ap_animation_frame334,
            R.drawable.button_to_ap_animation_frame335,
            R.drawable.button_to_ap_animation_frame336,
            R.drawable.button_to_ap_animation_frame337

    };

    // animation splash screen frames
    private int[] mSplashAnimFrames = {
            R.drawable.signup_animation_frame1,
            R.drawable.signup_animation_frame2,
            R.drawable.signup_animation_frame3,
            R.drawable.signup_animation_frame4,
            R.drawable.signup_animation_frame5,
            R.drawable.signup_animation_frame6,
            R.drawable.signup_animation_frame7,
            R.drawable.signup_animation_frame8,
            R.drawable.signup_animation_frame9,
            R.drawable.signup_animation_frame10,
            R.drawable.signup_animation_frame11,
            R.drawable.signup_animation_frame12,
            R.drawable.signup_animation_frame13,
            R.drawable.signup_animation_frame14,
            R.drawable.signup_animation_frame15,
            R.drawable.signup_animation_frame16,
            R.drawable.signup_animation_frame17,
            R.drawable.signup_animation_frame18,
            R.drawable.signup_animation_frame19,
            R.drawable.signup_animation_frame20,
            R.drawable.signup_animation_frame21,
            R.drawable.signup_animation_frame22,
            R.drawable.signup_animation_frame23,
            R.drawable.signup_animation_frame24,
            R.drawable.signup_animation_frame25,
            R.drawable.signup_animation_frame26,
            R.drawable.signup_animation_frame27,
            R.drawable.signup_animation_frame28,
            R.drawable.signup_animation_frame29,
            R.drawable.signup_animation_frame30,
            R.drawable.signup_animation_frame31,
            R.drawable.signup_animation_frame32,
            R.drawable.signup_animation_frame33,
            R.drawable.signup_animation_frame34,
            R.drawable.signup_animation_frame35,
            R.drawable.signup_animation_frame36,
            R.drawable.signup_animation_frame37,
            R.drawable.signup_animation_frame38,
            R.drawable.signup_animation_frame39,
            R.drawable.signup_animation_frame40,
            R.drawable.signup_animation_frame41,
            R.drawable.signup_animation_frame42,
            R.drawable.signup_animation_frame43,
            R.drawable.signup_animation_frame44,
            R.drawable.signup_animation_frame45,
            R.drawable.signup_animation_frame46,
            R.drawable.signup_animation_frame47,
            R.drawable.signup_animation_frame48,
            R.drawable.signup_animation_frame49,
            R.drawable.signup_animation_frame50,
            R.drawable.signup_animation_frame51,
            R.drawable.signup_animation_frame52,
            R.drawable.signup_animation_frame53,
            R.drawable.signup_animation_frame54,
            R.drawable.signup_animation_frame55,
            R.drawable.signup_animation_frame56,
            R.drawable.signup_animation_frame57,
            R.drawable.signup_animation_frame58,
            R.drawable.signup_animation_frame59,
            R.drawable.signup_animation_frame60,
            R.drawable.signup_animation_frame61,
            R.drawable.signup_animation_frame62,
            R.drawable.signup_animation_frame63,
            R.drawable.signup_animation_frame64,
            R.drawable.signup_animation_frame65,
            R.drawable.signup_animation_frame66,
            R.drawable.signup_animation_frame67,
            R.drawable.signup_animation_frame68,
            R.drawable.signup_animation_frame69,
            R.drawable.signup_animation_frame70,
            R.drawable.signup_animation_frame71,
            R.drawable.signup_animation_frame72,
            R.drawable.signup_animation_frame73,
            R.drawable.signup_animation_frame74,
            R.drawable.signup_animation_frame75,
            R.drawable.signup_animation_frame76,
            R.drawable.signup_animation_frame77,
            R.drawable.signup_animation_frame78,
            R.drawable.signup_animation_frame79,
            R.drawable.signup_animation_frame80,
            R.drawable.signup_animation_frame81,
            R.drawable.signup_animation_frame82,
            R.drawable.signup_animation_frame83,
            R.drawable.signup_animation_frame84,
            R.drawable.signup_animation_frame85,
            R.drawable.signup_animation_frame86,
            R.drawable.signup_animation_frame87,
            R.drawable.signup_animation_frame88,
            R.drawable.signup_animation_frame89,
            R.drawable.signup_animation_frame90,
            R.drawable.signup_animation_frame91,
            R.drawable.signup_animation_frame92,
            R.drawable.signup_animation_frame93,
            R.drawable.signup_animation_frame94,
            R.drawable.signup_animation_frame95,
            R.drawable.signup_animation_frame96,
            R.drawable.signup_animation_frame97,
            R.drawable.signup_animation_frame98,
            R.drawable.signup_animation_frame99,
            R.drawable.signup_animation_frame100,
            R.drawable.signup_animation_frame101,
            R.drawable.signup_animation_frame102,
            R.drawable.signup_animation_frame103,
            R.drawable.signup_animation_frame104,
            R.drawable.signup_animation_frame105,
            R.drawable.signup_animation_frame106,
            R.drawable.signup_animation_frame107,
            R.drawable.signup_animation_frame108,
            R.drawable.signup_animation_frame109,
            R.drawable.signup_animation_frame110,
            R.drawable.signup_animation_frame111,
            R.drawable.signup_animation_frame112,
            R.drawable.signup_animation_frame113,
            R.drawable.signup_animation_frame114,
            R.drawable.signup_animation_frame115,
            R.drawable.signup_animation_frame116,
            R.drawable.signup_animation_frame117,
            R.drawable.signup_animation_frame118,
            R.drawable.signup_animation_frame119,
            R.drawable.signup_animation_frame120,
            R.drawable.signup_animation_frame121,
            R.drawable.signup_animation_frame122,
            R.drawable.signup_animation_frame123,
            R.drawable.signup_animation_frame124,
            R.drawable.signup_animation_frame125,
            R.drawable.signup_animation_frame126,
            R.drawable.signup_animation_frame127,
            R.drawable.signup_animation_frame128,
            R.drawable.signup_animation_frame129,
            R.drawable.signup_animation_frame130,
            R.drawable.signup_animation_frame131,
            R.drawable.signup_animation_frame132,
            R.drawable.signup_animation_frame133,
            R.drawable.signup_animation_frame134,
            R.drawable.signup_animation_frame135,
            R.drawable.signup_animation_frame136,
            R.drawable.signup_animation_frame137,
            R.drawable.signup_animation_frame138,
            R.drawable.signup_animation_frame139,
            R.drawable.signup_animation_frame140,
            R.drawable.signup_animation_frame141,
            R.drawable.signup_animation_frame142,
            R.drawable.signup_animation_frame143,
            R.drawable.signup_animation_frame144,
            R.drawable.signup_animation_frame145,
            R.drawable.signup_animation_frame146,
            R.drawable.signup_animation_frame147,
            R.drawable.signup_animation_frame148,
            R.drawable.signup_animation_frame149,
            R.drawable.signup_animation_frame150,
            R.drawable.signup_animation_frame151,
            R.drawable.signup_animation_frame152,
            R.drawable.signup_animation_frame153,
            R.drawable.signup_animation_frame154,
            R.drawable.signup_animation_frame155,
            R.drawable.signup_animation_frame156,
            R.drawable.signup_animation_frame157,
            R.drawable.signup_animation_frame158,
            R.drawable.signup_animation_frame159,
            R.drawable.signup_animation_frame160,
            R.drawable.signup_animation_frame161,
            R.drawable.signup_animation_frame162,
            R.drawable.signup_animation_frame163,
            R.drawable.signup_animation_frame164,
            R.drawable.signup_animation_frame165,
            R.drawable.signup_animation_frame166,
            R.drawable.signup_animation_frame167,
            R.drawable.signup_animation_frame168,
            R.drawable.signup_animation_frame169,
            R.drawable.signup_animation_frame170,
            R.drawable.signup_animation_frame171,
            R.drawable.signup_animation_frame172,
            R.drawable.signup_animation_frame173,
            R.drawable.signup_animation_frame174,
            R.drawable.signup_animation_frame175,
            R.drawable.signup_animation_frame176,
            R.drawable.signup_animation_frame177,
            R.drawable.signup_animation_frame178,
            R.drawable.signup_animation_frame179,
            R.drawable.signup_animation_frame180,
            R.drawable.signup_animation_frame181,
            R.drawable.signup_animation_frame182,
            R.drawable.signup_animation_frame183,
            R.drawable.signup_animation_frame184,
            R.drawable.signup_animation_frame185,
            R.drawable.signup_animation_frame186,
            R.drawable.signup_animation_frame187,
            R.drawable.signup_animation_frame188,
            R.drawable.signup_animation_frame189,
            R.drawable.signup_animation_frame190,
            R.drawable.signup_animation_frame191,
            R.drawable.signup_animation_frame192,
            R.drawable.signup_animation_frame193,
            R.drawable.signup_animation_frame194,
            R.drawable.signup_animation_frame195,
            R.drawable.signup_animation_frame196,
            R.drawable.signup_animation_frame197,
            R.drawable.signup_animation_frame198,
            R.drawable.signup_animation_frame199,
            R.drawable.signup_animation_frame200,
            R.drawable.signup_animation_frame201,
            R.drawable.signup_animation_frame202,
            R.drawable.signup_animation_frame203,
            R.drawable.signup_animation_frame204,
            R.drawable.signup_animation_frame205,
            R.drawable.signup_animation_frame206,
            R.drawable.signup_animation_frame207,
            R.drawable.signup_animation_frame208,
            R.drawable.signup_animation_frame209,
            R.drawable.signup_animation_frame210,
            R.drawable.signup_animation_frame211,
            R.drawable.signup_animation_frame212,
            R.drawable.signup_animation_frame213,
            R.drawable.signup_animation_frame214,
            R.drawable.signup_animation_frame215,
            R.drawable.signup_animation_frame216
    };


    /**
     * @param imageView
     * @return progress dialog animation
     */
    public FramesSequenceAnimation createButtonToApAnim(ImageView imageView) {
        return new FramesSequenceAnimation(imageView, mButtonToApAnimFrames,FPS);
    }

    /**
     * @param imageView
     * @return splash screen animation
     */
    public FramesSequenceAnimation createSplashAnim(ImageView imageView) {
        return new FramesSequenceAnimation(imageView, mSplashAnimFrames,FPS);
    }

    /**
     * AnimationPlayer. Plays animation frames sequence in loop
     */
    public class FramesSequenceAnimation {
        private int[] mFrames; // animation frames
        private int mIndex; // current frame
        private boolean mShouldRun; // true if the animation should continue running. Used to stop the animation
        private boolean mIsRunning; // true if the animation currently running. prevents starting the animation twice
        private SoftReference<ImageView> mSoftReferenceImageView; // Used to prevent holding ImageView when it should be dead.
        private Handler mHandler;
        private int mDelayMillis;
      //  private OnAnimationStoppedListener mOnAnimationStoppedListener;

        private Bitmap mBitmap = null;
        private BitmapFactory.Options mBitmapOptions;

        public FramesSequenceAnimation(ImageView imageView, int[] frames, int fps) {
            mHandler = new Handler();
            mFrames = frames;
            mIndex = -1;
            mSoftReferenceImageView = new SoftReference<ImageView>(imageView);
            mShouldRun = false;
            mIsRunning = false;
            mDelayMillis = fps;

            imageView.setImageResource(mFrames[0]);

            // use in place bitmap to save GC work (when animation images are the same size & type)
            if (Build.VERSION.SDK_INT >= 11) {
                Bitmap bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                int width = bmp.getWidth();
                int height = bmp.getHeight();
                Bitmap.Config config = bmp.getConfig();
                mBitmap = Bitmap.createBitmap(width, height, config);
                mBitmapOptions = new BitmapFactory.Options();
                // setup bitmap reuse options.
                mBitmapOptions.inBitmap = mBitmap;
                mBitmapOptions.inMutable = true;
                mBitmapOptions.inSampleSize = 1;
            }
        }

        private int getNext() {
            mIndex++;
            if (mIndex >= mFrames.length)
                mIndex = 0;
            return mFrames[mIndex];
        }

        /**
         * Starts the animation
         */
        public synchronized void start() {
            mShouldRun = true;
            if (mIsRunning)
                return;

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    ImageView imageView = mSoftReferenceImageView.get();
                    if (!mShouldRun || imageView == null) {
                        mIsRunning = false;
                       // if (mOnAnimationStoppedListener != null) {
                        //    mOnAnimationStoppedListener.AnimationStopped();
                        //}
                        return;
                    }

                    mIsRunning = true;
                    mHandler.postDelayed(this, mDelayMillis);

                    if (imageView.isShown()) {
                        int imageRes = getNext();
                        if (mBitmap != null) { // so Build.VERSION.SDK_INT >= 11
                            Bitmap bitmap = null;
                            try {
                                bitmap = BitmapFactory.decodeResource(imageView.getResources(), imageRes, mBitmapOptions);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (bitmap != null) {
                                imageView.setImageBitmap(bitmap);
                            } else {
                                imageView.setImageResource(imageRes);
                                mBitmap.recycle();
                                mBitmap = null;
                            }
                        } else {
                            imageView.setImageResource(imageRes);
                        }
                    }

                }
            };

            mHandler.post(runnable);
        }

        /**
         * Stops the animation
         */
        public synchronized void stop() {
            mShouldRun = false;
        }
    }
}
