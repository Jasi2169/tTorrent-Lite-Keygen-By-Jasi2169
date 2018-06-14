package jasi2169.ttorrentlite.keygen;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/*
* Created By Jasi2169
* 14/Jun/2018
* Android Studio 3.0.1
* */

public class LetsKeygen extends AppCompatActivity {

    static final String[][] a;
    private static final char[] b = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };

    static {
        String[] String1 = {"D", "3", "U", "E", "Q", "P", "S", "E", "T", "J", "Y", "Z", "9", "3"};
        String[] String2 = new String[0];
        String[] String3 = new String[0];
        String[] String4 = new String[0];
        String[] String5 = {"7", "C", "A", "X", "F", "1", "R", "J", "T", "L", "S", "A", "L", "P", "X", "4", "3", "P", "9"};
        String[] String6 = {"T", "G", "7", "I", "C", "U", "Y", "B", "B", "2", "J", "Q", "H", "G"};
        String[] String7 = new String[0];
        String[] String8 = new String[0];
        String[] String9 = new String[0];
        String[] String10 = new String[0];
        String[] String11 = {"D", "R", "R", "5", "0", "4", "F", "F", "N", "2", "R", "K", "9", "A", "E", "C"};
        a = new String[][]{new String[0], {"E", "R", "7", "D", "S", "U", "F", "V", "J", "2", "L", "T"}, String1, String2, String3, {"A", "E", "V", "1"}, {"R", "P", "H", "J", "7", "N", "X", "W", "P", "E", "C", "I", "3", "6", "Q", "E", "O", "U", "5", "D"}, new String[0], {"M", "G", "0", "B", "I", "4", "A", "O"}, String4, {"F", "4", "L", "L", "4", "Z", "Y", "A", "7", "X", "6", "A", "5"}, String5, String6, String7, String8, {"K", "C", "V", "L", "X", "K", "5", "N", "I", "K", "H", "U", "A", "B", "3", "Q", "S", "J"}, String9, String10, new String[0], String11};
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lets_keygen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    public void generate(View v){

        TextView tv = (TextView) findViewById(R.id.textView2);
        RadioButton rb1 = (RadioButton) findViewById(R.id.radioButton1);

        String generatedserial = serial(rb1.isChecked() ? getmac() : getid());

        tv.setText("Serial :- "+ generatedserial);

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("serial",generatedserial);
        clipboard.setPrimaryClip(clip);

        Toast.makeText(this,"Key copied to clipboard!",Toast.LENGTH_LONG).show();

    }

    private String getmac(){

        WifiInfo localWifiInfo = ((WifiManager) this.getApplicationContext().getSystemService(this.WIFI_SERVICE)).getConnectionInfo();
        String str;

        if (localWifiInfo != null) {
            str = localWifiInfo.getMacAddress();
            if (str != null) {
                StringBuilder localObject = new StringBuilder();
                localObject.append(str.replaceAll("[:.; ]", "").toLowerCase());
                localObject.append("add0");
                str = localObject.toString();
                if (str.length() == 16) {
                    return str;
                }
            }
        }

        return null;
    }

    private String getid()
    {
        // won't work on android 8+
        String str = Settings.Secure.getString(this.getContentResolver(), "android_id");
        if (str == null)
        {
            StringBuilder sb = new StringBuilder();
            sb.append(Build.PRODUCT);
            sb.append(Build.DEVICE);
            sb.append(Build.DISPLAY);
            str = sb.toString();
            long l1 = 0L;
            char[] c = str.toCharArray();
            int j = c.length;
            int i = 0;
            while (i < j)
            {
                long l2 = c[i];
                i++;
                l1 += l2;
            }
            str = idcalc(l1);
        }
        return str.toLowerCase();
    }

    private static String idcalc(long val)
    {
        char[] chararray = new char[16];
        for (int i = 0; i < 16; i++)
        {
            int j = (int)(val & 0xF);
            chararray[(16 - i - 1)] = ((char)b[j]);
            val >>= 4;
        }
        return new String(chararray);
    }

    private String serial(String data)
    {

        char[] serial = new char[]{'J','A','S','I','J','A','S','I','J','A','S','I','J','A','S','I'};

        try
            {
                char[] chararray = data.toCharArray();
                int k = chararray.length;
                int j = 0;
                int i = j;
                while (j < k)
                {
                    i += chararray[j];
                    j++;
                }

                for (int j = 0; j < 16; j++)
                {
                    int m = (j * 29 + i) % a.length;
                    if (a[m].length > 0)
                    {
                        k = 15 - j;
                        if (k < data.length()) {
                            k = Integer.parseInt(data.substring(k, k + 1), 16);
                        } else {
                            k = 0;
                        }
                        int n = a[m].length;
                        m = a[m][(((k ^ i) & 0xF) * 53 % n)].charAt(0);

                        serial[j] = (char) m;

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        return formatkey(new String(serial));
    }

    private String formatkey(String serial){

        serial = serial.substring(0, 4) + "-" + serial.substring(4, 8) + "-" + serial.substring(8, 12) + "-" + serial.substring(12, 16);

        return  serial;
    }
}
