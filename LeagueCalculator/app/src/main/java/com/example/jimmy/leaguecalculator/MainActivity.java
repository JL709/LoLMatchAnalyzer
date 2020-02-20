package com.example.jimmy.leaguecalculator;

import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    int selectedRegion;
    EditText summonerNameText;
    String summonerRegion;
    String summonerId;
    String summonerName = "string longer than 16 characters";
    String summonerNameNoSpaces = "string longer than 16 characters";
    String summonerNameComplete = "string longer than 16 characters";
    Spinner regionSpinner;
    TabHost tabHostMasteries;
    JSONArray jsonParticipants;
    private ListView championInfoList;
    short[] masteryInt = new short[7000];
    short[] championsInt = new short[500];
    short[] runesInt = new short[12000];

    Runes[] runes = new Runes[300];
    Mastery[] mastery = new Mastery[45];
    Champions[] champions = new Champions[134];
    String divisionAPIResponse;
    RelativeLayout[] championBlock = new RelativeLayout[10];
    TextView[] championName = new TextView[10];
    TextView[] summonersName = new TextView[10];
    ImageView[] championImage = new ImageView[10];

    ImageButton imageFury;
    TextView textViewFury;
    ImageButton imageFreshBlood;
    TextView textViewFreshBlood;
    ImageButton imageSorcery;
    TextView textViewSorcery;
    ImageButton imageFeast;
    TextView textViewFeast;
    ImageButton imageExposeWeakness;
    TextView textViewExposeWeakness;
    ImageButton imageVampirism;
    TextView textViewVampirism;
    ImageButton imageNaturalTalent;
    TextView textViewNaturalTalent;
    ImageButton imageBountyHunter;
    TextView textViewBountyHunter;
    ImageButton imageDoubleEdgedSword;
    TextView textViewDoubleEdgedSword;
    ImageButton imageBattleTrance;
    TextView textViewBattleTrance;
    ImageButton imageBatteringBlows;
    TextView textViewBatteringBlows;
    ImageButton imageWarlordsBloodlust;
    TextView textViewWarlordsBloodlust;
    ImageButton imageDeathfireTouch;
    TextView textViewDeathfireTouch;
    ImageButton imageFervorofBattle;
    TextView textViewFervorofBattle;
    ImageButton imagePiercingThoughts;
    TextView textViewPiercingThoughts;
    ImageButton imageWanderer;
    TextView textViewWanderer;
    ImageButton imageRunicAffinity;
    TextView textViewRunicAffinity;
    ImageButton imageMerciless;
    TextView textViewMerciless;
    ImageButton imageGreenfathersGift;
    TextView textViewGreenfathersGift;
    ImageButton imagePrecision;
    TextView textViewPrecision;
    ImageButton imageAssassin;
    TextView textViewAssassin;
    ImageButton imageSavagery;
    TextView textViewSavagery;
    ImageButton imageStormraidersSurge;
    TextView textViewStormraidersSurge;
    ImageButton imageMeditation;
    TextView textViewMeditation;
    ImageButton imageDangerousGame;
    TextView textViewDangerousGame;
    ImageButton imageIntelligence;
    TextView textViewIntelligence;
    ImageButton imageWindspeakersBlessing;
    TextView textViewWindspeakersBlessing;
    ImageButton imageSecretStash;
    TextView textViewSecretStash;
    ImageButton imageBandit;
    TextView textViewBandit;
    ImageButton imageThunderlordsDecree;
    TextView textViewThunderlordsDecree;
    ImageButton imageRecovery;
    TextView textViewRecovery;
    ImageButton imageExplorer;
    TextView textViewExplorer;
    ImageButton imageRunicArmor;
    TextView textViewRunicArmor;
    ImageButton imageInsight;
    TextView textViewInsight;
    ImageButton imageGraspoftheUndying;
    TextView textViewGraspoftheUndying;
    ImageButton imageUnyielding;
    TextView textViewUnyielding;
    ImageButton imageSiegemaster;
    TextView textViewSiegemaster;
    ImageButton imageVeteransScars;
    TextView textViewVeteransScars;
    ImageButton imageFearless;
    TextView textViewFearless;
    ImageButton imageLegendaryGuardian;
    TextView textViewLegendaryGuardian;
    ImageButton imageBondofStone;
    TextView textViewBondofStone;
    ImageButton imageToughSkin;
    TextView textViewToughSkin;
    ImageButton imagePerseverance;
    TextView textViewPerseverance;
    ImageButton imageCourageoftheColossus;
    TextView textViewCourageoftheColossus;
    ImageButton imageSwiftness;
    TextView textViewSwiftness;
    int activeTotal;

    public static class nameAndRegion {
        String name;
        String region;

        nameAndRegion(String name, String region) {
            this.name = name;
            this.region = region;
        }
    }

    public static class idAndRegion {
        String id;
        String region;

        idAndRegion(String id, String region) {
            this.id = id;
            this.region = region;

        }
    }

    public class RetrieveSummonerId extends AsyncTask<nameAndRegion, Void, String> {

        private Exception exception;
        private String summName = "";
        private String summRegion = "";

        protected void onPreExecute() {
        }

        protected String doInBackground(nameAndRegion... params) {
            int count = params.length;

            if (count > 0) {
                summName = params[0].name;
                summRegion = params[0].region;
            }
            try {
                URL url = new URL("https://" + summRegion + ".api.pvp.net/api/lol/" + summRegion + "/v1.4/summoner/by-name/" + summName + "?api_key=RGAPI-3643dc11-0cc0-49fe-ab62-232685568739");
                Log.i("INFO", url.toString());
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", "summoner API");
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if (response == null) {
                response = "THERE WAS AN ERROR";
            }
            try {
                JSONObject jsonStr = new JSONObject(response);
                JSONObject jsonStr2 = jsonStr.getJSONObject(summName);
                summonerId = jsonStr2.optString("id");
                summonerNameComplete = jsonStr2.optString("name");
            } catch (Exception ex) {
                Log.e("ERROR", "Summoner JSONObject");
            }
            idAndRegion idAndRegionParams = new idAndRegion(summonerId, summonerRegion);
            RetrieveMatch taskMatch = new RetrieveMatch();
            taskMatch.execute(idAndRegionParams);
        }
    }

    public class RetrieveMatch extends AsyncTask<idAndRegion, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
        }

        String summNumb;
        String summRegion;

        protected String doInBackground(idAndRegion... params) {
            int count = params.length;

            if (count > 0) {
                summNumb = params[0].id;
                summRegion = params[0].region;
            }
            try {
                URL url = new URL("https://" + summRegion + ".api.pvp.net/observer-mode/rest/consumer/getSpectatorGameInfo/" + summRegion.toUpperCase() + "1" + "/" + summNumb + "?api_key=RGAPI-3643dc11-0cc0-49fe-ab62-232685568739");
                Log.i("INFO", url.toString());
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", "division API");
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        // FINISH THIS
        // ADD WHAT TO DO IF MATCH NOT FOUND
        protected void onPostExecute(String response) {
            if (response == null) {
                response = "THERE WAS AN ERROR";
            }
            try {
                Log.i("INFO", response);
                divisionAPIResponse = response;
                JSONObject jsonObj = new JSONObject(response);
                jsonParticipants = jsonObj.getJSONArray("participants");
                JSONObject jsonSummoner;
                for (int x = 0; x < jsonParticipants.length(); x++) {
                    jsonSummoner = jsonParticipants.getJSONObject(x);
                    String summoner = jsonSummoner.optString("summonerName");
                    int championId = jsonSummoner.optInt("championId");
                    Context context = championImage[x].getContext();
                    int id = context.getResources().getIdentifier(champions[championsInt[championId]].imageName, "drawable", context.getPackageName());
                    //int teamId = jsonParticipant.optInt("teamId");
                    summonersName[x].setText(summoner);
                    championName[x].setText(champions[championsInt[championId]].name);
                    championImage[x].setImageResource(id);
                }
            } catch (Exception ex) {
                Log.e("ERROR", "Match JSONObject");
                Log.e("ERROR", ex.getMessage(), ex);
            }
        }
    }


    public String loadJSONFile(String fileName) {
        String json = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open(fileName)));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            bufferedReader.close();
            json = stringBuilder.toString();
        } catch (Exception e) {
            Log.e("ERROR", e.toString());
        }
        return json;
    }
    /*
    public void championIdToIndex(){
        championsInt[1] = 0;
        championsInt[2] = 1;
        championsInt[3] = 2;
        championsInt[4] = 3;
        championsInt[5] = 4;
        championsInt[6] = 5;
        championsInt[7] = 6;
        championsInt[8] = 7;
        championsInt[9] = 8;
        championsInt[10] = 9;
        championsInt[11] = 10;
        championsInt[12] = 11;
        championsInt[13] = 12;
        championsInt[14] = 13;
        championsInt[15] = 14;
        championsInt[16] = 15;
        championsInt[17] = 16;
        championsInt[18] = 17;
        championsInt[19] = 18;
        championsInt[20] = 19;
        championsInt[21] = 20;
        championsInt[22] = 21;
        championsInt[23] = 22;
        championsInt[24] = 23;
        championsInt[25] = 24;
        championsInt[26] = 25;
        championsInt[27] = 26;
        championsInt[28] = 27;
        championsInt[29] = 28;
        championsInt[30] = 29;
        championsInt[31] = 30;
        championsInt[32] = 31;
        championsInt[33] = 32;
        championsInt[34] = 33;
        championsInt[35] = 34;
        championsInt[36] = 35;
        championsInt[37] = 36;
        championsInt[38] = 37;
        championsInt[39] = 38;
        championsInt[40] = 39;
        championsInt[41] = 40;
        championsInt[42] = 41;
        championsInt[43] = 42;
        championsInt[44] = 43;
        championsInt[45] = 44;
        championsInt[48] = 45;
        championsInt[50] = 46;
        championsInt[51] = 47;
        championsInt[53] = 48;
        championsInt[54] = 49;
        championsInt[55] = 50;
        championsInt[56] = 51;
        championsInt[57] = 52;
        championsInt[58] = 53;
        championsInt[59] = 54;
        championsInt[60] = 55;
        championsInt[61] = 56;
        championsInt[62] = 57;
        championsInt[63] = 58;
        championsInt[64] = 59;
        championsInt[67] = 60;
        championsInt[68] = 61;
        championsInt[69] = 62;
        championsInt[72] = 63;
        championsInt[74] = 64;
        championsInt[75] = 65;
        championsInt[76] = 66;
        championsInt[77] = 67;
        championsInt[78] = 68;
        championsInt[79] = 69;
        championsInt[80] = 70;
        championsInt[81] = 71;
        championsInt[82] = 72;
        championsInt[83] = 73;
        championsInt[84] = 74;
        championsInt[85] = 75;
        championsInt[86] = 76;
        championsInt[89] = 77;
        championsInt[90] = 78;
        championsInt[91] = 79;
        championsInt[92] = 80;
        championsInt[96] = 81;
        championsInt[98] = 82;
        championsInt[99] = 83;
        championsInt[101] = 84;
        championsInt[102] = 85;
        championsInt[103] = 86;
        championsInt[104] = 87;
        championsInt[105] = 88;
        championsInt[106] = 89;
        championsInt[107] = 90;
        championsInt[110] = 91;
        championsInt[111] = 92;
        championsInt[112] = 93;
        championsInt[113] = 94;
        championsInt[114] = 95;
        championsInt[115] = 96;
        championsInt[117] = 97;
        championsInt[119] = 98;
        championsInt[120] = 99;
        championsInt[121] = 100;
        championsInt[122] = 101;
        championsInt[126] = 102;
        championsInt[127] = 103;
        championsInt[131] = 104;
        championsInt[133] = 105;
        championsInt[134] = 106;
        championsInt[136] = 107;
        championsInt[143] = 108;
        championsInt[150] = 109;
        championsInt[154] = 110;
        championsInt[157] = 111;
        championsInt[161] = 112;
        championsInt[163] = 113;
        championsInt[164] = 114;
        championsInt[201] = 115;
        championsInt[202] = 116;
        championsInt[203] = 117;
        championsInt[222] = 118;
        championsInt[223] = 119;
        championsInt[236] = 120;
        championsInt[238] = 121;
        championsInt[240] = 122;
        championsInt[245] = 123;
        championsInt[254] = 124;
        championsInt[266] = 125;
        championsInt[267] = 126;
        championsInt[268] = 127;
        championsInt[412] = 128;
        championsInt[420] = 129;
        championsInt[421] = 130;
        championsInt[427] = 131;
        championsInt[429] = 132;
        championsInt[432] = 133;
    }
    */
    /*
    public void championSetUp(){
        short x = 0;
        int y = 0;
        JSONObject json = null;
        try {
            json = new JSONObject(loadJSONFile("champions.txt"));
        }catch(Exception ex){
            Log.e("ERROR", ex.toString());
        }
        while (y < 433) {
            try {
                JSONObject json2 = json.getJSONObject("data");
                JSONObject json3 = json2.getJSONObject(y + "");
                JSONObject json4 = json3.getJSONObject("stats");
                championsInt[json3.optInt("id")] = x;
                champions[x] = new Champions(json3.optInt("id"), json3.getString("name"), json3.getString("name").toLowerCase().replace("'","").replace(" ", ""), json4.optInt("armor"), json4.optInt("armorperlevel"), json4.optInt("attackdamage"),
                        json4.optInt("attackdamageperlevel"), json4.optInt("attackrange"), json4.optInt("attackspeedoffset"), json4.optInt("attackspeedperlevel"), json4.optInt("hp"), json4.optInt("hpperlevel"),
                        json4.optInt("hpregen"), json4.optInt("hpregenperlevel"), json4.optInt("movespeed"), json4.optInt("mp"), json4.optInt("mpperlevel"), json4.optInt("mpregen"), json4.optInt("mpregenperlevel"), json4.optInt("spellblock"), json4.optInt("spellblockperlevel"));
                Log.i("INFO", x + ", " + champions[x].id + ", " + champions[x].imageName);
                x += 1;
            } catch (Exception e) {
                //Log.e("ERROR", e.toString());
            }
            y += 1;
        }
    }
    */
    public class championSetUp extends AsyncTask<Void, Void, String> {
        protected void onPreExecute() {
        }

        protected String doInBackground(Void... params) {
            try {
                URL url = new URL("https://global.api.pvp.net/api/lol/static-data/na/v1.2/champion?dataById=true&champData=stats&api_key=RGAPI-3643dc11-0cc0-49fe-ab62-232685568739");
                Log.i("INFO", url.toString());
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", "runes API");
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(String response) {
            short x = 0;
            int y = 0;
            JSONObject json = null;
            try {
                json = new JSONObject(loadJSONFile("champions.txt"));
            }catch(Exception ex){
                Log.e("ERROR", ex.toString());
            }
            while (y < 433) {
                try {
                    JSONObject json2 = json.getJSONObject("data");
                    JSONObject json3 = json2.getJSONObject(y + "");
                    JSONObject json4 = json3.getJSONObject("stats");
                    championsInt[json3.optInt("id")] = x;
                    champions[x] = new Champions(json3.optInt("id"), json3.getString("name"), json3.getString("name").toLowerCase().replace("'","").replace(" ", ""), json4.optInt("armor"), json4.optInt("armorperlevel"), json4.optInt("attackdamage"),
                            json4.optInt("attackdamageperlevel"), json4.optInt("attackrange"), json4.optInt("attackspeedoffset"), json4.optInt("attackspeedperlevel"), json4.optInt("hp"), json4.optInt("hpperlevel"),
                            json4.optInt("hpregen"), json4.optInt("hpregenperlevel"), json4.optInt("movespeed"), json4.optInt("mp"), json4.optInt("mpperlevel"), json4.optInt("mpregen"), json4.optInt("mpregenperlevel"), json4.optInt("spellblock"), json4.optInt("spellblockperlevel"));
                    Log.i("INFO", x + ", " + champions[x].id + ", " + champions[x].imageName);
                    x += 1;
                } catch (Exception e) {
                    //Log.e("ERROR", e.toString());
                }
                y += 1;
            }
        }
    }
    public class runesSetUp extends AsyncTask<Void, Void, String> {
        protected void onPreExecute() {
        }

        protected String doInBackground(Void... params) {
            try {
                URL url = new URL("https://global.api.pvp.net/api/lol/static-data/na/v1.2/rune?api_key=RGAPI-3643dc11-0cc0-49fe-ab62-232685568739");
                Log.i("INFO", url.toString());
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", "runes API");
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(String response) {
            short x = 0;
            int y = 0;
            JSONObject json = null;
            try {
                json = new JSONObject(response);
            }catch(Exception ex){
                Log.e("ERROR", ex.toString());
            }
            while (y < 10040) {
                try {
                    JSONObject json2 = json.getJSONObject("data");
                    JSONObject json3 = json2.getJSONObject(y + "");
                    runesInt[json3.optInt("id")] = x;
                    runes[x] = new Runes(json3.optInt("id"), json3.optString("name"), json3.optString("description"), Float.parseFloat(json3.optString("description").substring(1, 5)), 0);
                    Log.i("INFO", x + ", " + runes[x].id + ", " + runes[x].name + ", " + runes[x].value);
                    x += 1;
                } catch (Exception e) {
                    //Log.e("ERROR", e.toString());
                }
                y += 1;
            }
        }
    }
    public void masteryIdToIndex(){
        masteryInt[6111] = 0;
        masteryInt[6114] = 1;
        masteryInt[6121] = 2;
        masteryInt[6122] = 3;
        masteryInt[6123] = 4;
        masteryInt[6131] = 5;
        masteryInt[6134] = 6;
        masteryInt[6141] = 7;
        masteryInt[6142] = 8;
        masteryInt[6143] = 9;
        masteryInt[6151] = 10;
        masteryInt[6154] = 11;
        masteryInt[6161] = 12;
        masteryInt[6162] = 13;
        masteryInt[6164] = 14;
        masteryInt[6211] = 15;
        masteryInt[6212] = 16;
        masteryInt[6221] = 17;
        masteryInt[6222] = 18;
        masteryInt[6223] = 19;
        masteryInt[6231] = 20;
        masteryInt[6232] = 21;
        masteryInt[6241] = 22;
        masteryInt[6242] = 23;
        masteryInt[6243] = 24;
        masteryInt[6251] = 25;
        masteryInt[6252] = 26;
        masteryInt[6261] = 27;
        masteryInt[6262] = 28;
        masteryInt[6263] = 29;
        masteryInt[6311] = 30;
        masteryInt[6312] = 31;
        masteryInt[6321] = 32;
        masteryInt[6322] = 33;
        masteryInt[6323] = 34;
        masteryInt[6331] = 35;
        masteryInt[6332] = 36;
        masteryInt[6341] = 37;
        masteryInt[6342] = 38;
        masteryInt[6343] = 39;
        masteryInt[6351] = 40;
        masteryInt[6352] = 41;
        masteryInt[6361] = 42;
        masteryInt[6362] = 43;
        masteryInt[6363] = 44;
    }
    public void masteryImageAndText(){
        imageFury = (ImageButton) findViewById(R.id.imageFury);
        textViewFury = (TextView) findViewById(R.id.textViewFury);
        imageFreshBlood = (ImageButton) findViewById(R.id.imageFreshBlood);
        textViewFreshBlood = (TextView) findViewById(R.id.textViewFreshBlood);
        imageSorcery = (ImageButton) findViewById(R.id.imageSorcery);
        textViewSorcery = (TextView) findViewById(R.id.textViewSorcery);
        imageFeast = (ImageButton) findViewById(R.id.imageFeast);
        textViewFeast = (TextView) findViewById(R.id.textViewFeast);
        imageExposeWeakness = (ImageButton) findViewById(R.id.imageExposeWeakness);
        textViewExposeWeakness = (TextView) findViewById(R.id.textViewExposeWeakness);
        imageVampirism = (ImageButton) findViewById(R.id.imageVampirism);
        textViewVampirism = (TextView) findViewById(R.id.textViewVampirism);
        imageNaturalTalent = (ImageButton) findViewById(R.id.imageNaturalTalent);
        textViewNaturalTalent = (TextView) findViewById(R.id.textViewNaturalTalent);
        imageBountyHunter = (ImageButton) findViewById(R.id.imageBountyHunter);
        textViewBountyHunter = (TextView) findViewById(R.id.textViewBountyHunter);
        imageDoubleEdgedSword = (ImageButton) findViewById(R.id.imageDoubleEdgedSword);
        textViewDoubleEdgedSword = (TextView) findViewById(R.id.textViewDoubleEdgedSword);
        imageBattleTrance = (ImageButton) findViewById(R.id.imageBattleTrance);
        textViewBattleTrance = (TextView) findViewById(R.id.textViewBattleTrance);
        imageBatteringBlows = (ImageButton) findViewById(R.id.imageBatteringBlows);
        textViewBatteringBlows = (TextView) findViewById(R.id.textViewBatteringBlows);
        imageWarlordsBloodlust = (ImageButton) findViewById(R.id.imageWarlordsBloodlust);
        textViewWarlordsBloodlust = (TextView) findViewById(R.id.textViewWarlordsBloodlust);
        imageDeathfireTouch = (ImageButton) findViewById(R.id.imageDeathfireTouch);
        textViewDeathfireTouch = (TextView) findViewById(R.id.textViewDeathfireTouch);
        imageFervorofBattle = (ImageButton) findViewById(R.id.imageFervorofBattle);
        textViewFervorofBattle = (TextView) findViewById(R.id.textViewFervorofBattle);
        imagePiercingThoughts = (ImageButton) findViewById(R.id.imagePiercingThoughts);
        textViewPiercingThoughts = (TextView) findViewById(R.id.textViewPiercingThoughts);
        imageWanderer = (ImageButton) findViewById(R.id.imageWanderer);
        textViewWanderer = (TextView) findViewById(R.id.textViewWanderer);
        imageRunicAffinity = (ImageButton) findViewById(R.id.imageRunicAffinity);
        textViewRunicAffinity = (TextView) findViewById(R.id.textViewRunicAffinity);
        imageMerciless = (ImageButton) findViewById(R.id.imageMerciless);
        textViewMerciless = (TextView) findViewById(R.id.textViewMerciless);
        imageGreenfathersGift = (ImageButton) findViewById(R.id.imageGreenfathersGift);
        textViewGreenfathersGift = (TextView) findViewById(R.id.textViewGreenfathersGift);
        imagePrecision = (ImageButton) findViewById(R.id.imagePrecision);
        textViewPrecision = (TextView) findViewById(R.id.textViewPrecision);
        imageAssassin = (ImageButton) findViewById(R.id.imageAssassin);
        textViewAssassin = (TextView) findViewById(R.id.textViewAssassin);
        imageSavagery = (ImageButton) findViewById(R.id.imageSavagery);
        textViewSavagery = (TextView) findViewById(R.id.textViewSavagery);
        imageStormraidersSurge = (ImageButton) findViewById(R.id.imageStormraidersSurge);
        textViewStormraidersSurge = (TextView) findViewById(R.id.textViewStormraidersSurge);
        imageMeditation = (ImageButton) findViewById(R.id.imageMeditation);
        textViewMeditation = (TextView) findViewById(R.id.textViewMeditation);
        imageDangerousGame = (ImageButton) findViewById(R.id.imageDangerousGame);
        textViewDangerousGame = (TextView) findViewById(R.id.textViewDangerousGame);
        imageIntelligence = (ImageButton) findViewById(R.id.imageIntelligence);
        textViewIntelligence = (TextView) findViewById(R.id.textViewIntelligence);
        imageWindspeakersBlessing = (ImageButton) findViewById(R.id.imageWindspeakersBlessing);
        textViewWindspeakersBlessing = (TextView) findViewById(R.id.textViewWindspeakersBlessing);
        imageSecretStash = (ImageButton) findViewById(R.id.imageSecretStash);
        textViewSecretStash = (TextView) findViewById(R.id.textViewSecretStash);
        imageBandit = (ImageButton) findViewById(R.id.imageBandit);
        textViewBandit = (TextView) findViewById(R.id.textViewBandit);
        imageThunderlordsDecree = (ImageButton) findViewById(R.id.imageThunderlordsDecree);
        textViewThunderlordsDecree = (TextView) findViewById(R.id.textViewThunderlordsDecree);
        imageRecovery = (ImageButton) findViewById(R.id.imageRecovery);
        textViewRecovery = (TextView) findViewById(R.id.textViewRecovery);
        imageExplorer = (ImageButton) findViewById(R.id.imageExplorer);
        textViewExplorer = (TextView) findViewById(R.id.textViewExplorer);
        imageRunicArmor = (ImageButton) findViewById(R.id.imageRunicArmor);
        textViewRunicArmor = (TextView) findViewById(R.id.textViewRunicArmor);
        imageInsight = (ImageButton) findViewById(R.id.imageInsight);
        textViewInsight = (TextView) findViewById(R.id.textViewInsight);
        imageGraspoftheUndying = (ImageButton) findViewById(R.id.imageGraspoftheUndying);
        textViewGraspoftheUndying = (TextView) findViewById(R.id.textViewGraspoftheUndying);
        imageUnyielding = (ImageButton) findViewById(R.id.imageUnyielding);
        textViewUnyielding = (TextView) findViewById(R.id.textViewUnyielding);
        imageSiegemaster = (ImageButton) findViewById(R.id.imageSiegemaster);
        textViewSiegemaster = (TextView) findViewById(R.id.textViewSiegemaster);
        imageVeteransScars = (ImageButton) findViewById(R.id.imageVeteransScars);
        textViewVeteransScars = (TextView) findViewById(R.id.textViewVeteransScars);
        imageFearless = (ImageButton) findViewById(R.id.imageFearless);
        textViewFearless = (TextView) findViewById(R.id.textViewFearless);
        imageLegendaryGuardian = (ImageButton) findViewById(R.id.imageLegendaryGuardian);
        textViewLegendaryGuardian = (TextView) findViewById(R.id.textViewLegendaryGuardian);
        imageBondofStone = (ImageButton) findViewById(R.id.imageBondofStone);
        textViewBondofStone = (TextView) findViewById(R.id.textViewBondofStone);
        imageToughSkin = (ImageButton) findViewById(R.id.imageToughSkin);
        textViewToughSkin = (TextView) findViewById(R.id.textViewToughSkin);
        imagePerseverance = (ImageButton) findViewById(R.id.imagePerseverance);
        textViewPerseverance = (TextView) findViewById(R.id.textViewPerseverance);
        imageCourageoftheColossus = (ImageButton) findViewById(R.id.imageCourageoftheColossus);
        textViewCourageoftheColossus = (TextView) findViewById(R.id.textViewCourageoftheColossus);
        imageSwiftness = (ImageButton) findViewById(R.id.imageSwiftness);
        textViewSwiftness = (TextView) findViewById(R.id.textViewSwiftness);
    }
    public void masteryReset() {
        mastery[masteryInt[6121]] = new Mastery("Fresh Blood", "Your first basic attack against a champion deals an additional 10 + 1 per level damage (6 second cooldown)", 6121, 0, imageFreshBlood, textViewFreshBlood);
        mastery[masteryInt[6261]] = new Mastery("Grasp of the Undying", "Every 4 seconds in combat, your next attack against an enemy champion deals damage equal to 3% of your max Health and" +
                "heals you for 1.5% of your max Health (halved for ranged champions, deals magic damage)", 6261, 0, imageGraspoftheUndying, textViewGraspoftheUndying);
        mastery[masteryInt[6122]] = new Mastery("Feast", "Killing a unit restores 20 Health (30 second cooldown)", 6122, 0, imageFeast, textViewFeast);
        mastery[masteryInt[6123]] = new Mastery("Expose Weakness", "Damaging enemy champions causes them to take 3% more damage from your allies", 6123, 0, imageExposeWeakness, textViewExposeWeakness);
        mastery[masteryInt[6141]] = new Mastery("Bounty Hunter", "Deal 1.5% increased damage for each unique enemy champion you have killed", 6141, 0, imageBountyHunter, textViewBountyHunter);
        mastery[masteryInt[6142]] = new Mastery("Double Edged Sword", "Deal 5% additional damage, take 2.5% additional damage.", 6142, 0, imageDoubleEdgedSword, textViewDoubleEdgedSword);
        mastery[masteryInt[6162]] = new Mastery("Fervor of Battle", "Hitting champions with basic attacks generates a Fervor stack (2 for melee attacks). Stacks of Fervor last 6 seconds " +
                "(max 8 stacks)and increase your AD by 1-8 for each stack.", 6162, 0, imageFervorofBattle, textViewFervorofBattle);
        mastery[masteryInt[6143]] = new Mastery("Battle Trance", "Gain up to 5% increased damage over 5 seconds when in combat with enemy Champions", 6143, 0, imageBattleTrance, textViewBattleTrance);
        mastery[masteryInt[6161]] = new Mastery("Warlord's Bloodlust", "Gain increasingly more Life Steal based on your missing health against champions (up to 20%). Against minions gain 50% " +
                "benefit (25% for ranged champions).", 6161, 0, imageWarlordsBloodlust, textViewWarlordsBloodlust);
        mastery[masteryInt[6323]] = new Mastery("Assassin", "Deal 2% increased damage to champions when no allied champions are nearby", 6323, 0, imageAssassin, textViewAssassin);
        mastery[masteryInt[6341]] = new Mastery("Greenfather's Gift", "Stepping into brush causes your next damaging attack or ability to deal 3% of your target's current health as bonus " +
                "magic damage (9s Cooldown)", 6341, 0, imageGreenfathersGift, textViewGreenfathersGift);
        mastery[masteryInt[6164]] = new Mastery("Deathfire Touch", "Your damaging abilities cause enemy champions to take magic damage over 4 seconds.<br><br>Damage: 8 + 60% Bonus Attack " +
                "Damage and 25% Ability Power. Deathfire Touch's duration is reduced to 2 seconds for area of affect and 1 second for damage over time.", 6164, 0, imageDeathfireTouch, textViewDeathfireTouch);
        mastery[masteryInt[6342]] = new Mastery("Bandit", "Gain 1 gold for each nearby minion killed by an ally. <br><br>Gain 3 gold " +
                "(10 if melee) when hitting an enemy champion with a basic attack (5 second cooldown)", 6342, 0, imageBandit, textViewBandit);
        mastery[masteryInt[6241]] = new Mastery("Insight", "Reduces the cooldown of Summoner Spells by 15%", 6241, 0, imageInsight, textViewInsight);
        mastery[masteryInt[6321]] = new Mastery("Runic Affinity", "Buffs from neutral monsters last 15% longer", 6321, 0, imageRunicAffinity, textViewRunicAffinity);
        mastery[masteryInt[6242]] = new Mastery("Perseverance", "+50% Base Health Regen, increased to +200% when below 25% Health", 6242, 0, imagePerseverance, textViewPerseverance);
        mastery[masteryInt[6322]] = new Mastery("Secret Stash", "Your Potions and Elixirs last 10% longer. Your Health " +
                "Potions are replaced with Biscuits that restore 15 Health and Mana instantly on use", 6322, 0, imageSecretStash, textViewSecretStash);
        mastery[masteryInt[6243]] = new Mastery("Fearless", "Gain 10% +2 per level bonus Armor and Magic Resist when damaged " +
                "by an enemy champion for 2 seconds (9s Cooldown)", 6243, 0, imageFearless, textViewFearless);
        mastery[masteryInt[6262]] = new Mastery("Courage of the Colossus", "Gain a shield for 10 +10 per level + 5% of your " +
                "maximum health for each nearby enemy champion for 4 seconds after hitting an enemy champion with a stun, taunt, " +
                "snare, or knock up(45 - 30 second cooldown based on level).", 6262, 0, imageCourageoftheColossus, textViewCourageoftheColossus);
        mastery[masteryInt[6263]] = new Mastery("Bond of Stone", "+4% Damage Reduction. 6% of the damage from enemy champions " +
                "taken by the nearest allied champion is dealt to you instead. Damage is not redirected if you are below 5% of your maximum health.", 6263, 0, imageBondofStone, textViewBondofStone);
        mastery[masteryInt[6343]] = new Mastery("Dangerous Game", "Champion kills and assists restore 5% of your missing Health and Mana", 6343, 0, imageDangerousGame, textViewDangerousGame);
        mastery[masteryInt[6352]] = new Mastery("Intelligence", "Your Cooldown Reduction cap is increased to 41%/42%/43%/44%/45% and you gain 1%/2%/3%/4%/5% Cooldown Reduction", 6352, 0, imageIntelligence, textViewIntelligence);
        mastery[masteryInt[6351]] = new Mastery("Precision", "Gain 1.7/3.4/5.1/6.8/8.5 Lethality and 0.6/1.2/1.8/2.4/3 + 0.06/0.12/0.18/0.24/0.3 per level Magic Penetration", 6351, 0, imagePrecision, textViewPrecision);
        mastery[masteryInt[6212]] = new Mastery("Unyielding", "+1%/2%/3%/4%/5% Bonus Armor and Magic Resist", 6212, 0, imageUnyielding, textViewUnyielding);
        mastery[masteryInt[6211]] = new Mastery("Recovery", "+0.4/0.8/1.2/1.6/2.0 Health per 5 seconds", 6211, 0, imageRecovery, textViewRecovery);
        mastery[masteryInt[6111]] = new Mastery("Fury", "+0.8%/1.6%/2.4%/3.2%/4% Attack Speed", 6111, 0, imageFury, textViewFury);
        mastery[masteryInt[6134]] = new Mastery("Natural Talent", "Gain 0.4/0.8/1.2/1.6/2.0 + 0.09/0.18/0.27/0.36/0.44 " +
                "per level Attack Damage, and 0.6/1.2/1.8/2.4/3.0 + 0.13/0.27/0.4/0.53/0.67 per level Ability Power " +
                "(+2/4/6/8/10 Attack Damage and 3/6/9/12/15 Ability Power at level 18)", 6134, 0, imageNaturalTalent, textViewNaturalTalent);
        mastery[masteryInt[6114]] = new Mastery("Sorcery", "+0.4%/0.8%/1.2%/1.6%/2.0% increased Ability damage", 6114, 0, imageSorcery, textViewSorcery);
        mastery[masteryInt[6151]] = new Mastery("Battering Blows", "+1.4%/2.8%/4.2%/5.6%/7% Armor Penetration", 6151, 0, imageBatteringBlows, textViewBatteringBlows);
        mastery[masteryInt[6131]] = new Mastery("Vampirism", "+0.4%/0.8%/1.2%/1.6%/2.0% Lifesteal and Spell Vamp", 6131, 0, imageVampirism, textViewVampirism);
        mastery[masteryInt[6231]] = new Mastery("Runic Armor", "Shields, healing, regeneration, and lifesteal on you are 1.6%/3.2%/4.8%/6.4%/8% stronger", 6231, 0, imageRunicArmor, textViewRunicArmor);
        mastery[masteryInt[6311]] = new Mastery("Wanderer", "+0.6%/1.2%/1.8%/2.4%/3.0% Movement Speed out of combat", 6311, 0, imageWanderer, textViewWanderer);
        mastery[masteryInt[6154]] = new Mastery("Piercing Thoughts", "+1.4%/2.8%/4.2%/5.6%/7% Magic Penetration", 6154, 0, imagePiercingThoughts, textViewPiercingThoughts);
        mastery[masteryInt[6232]] = new Mastery("Veteran's Scars", "+10/20/30/40/50 Health", 6232, 0, imageVeteransScars, textViewVeteransScars);
        mastery[masteryInt[6312]] = new Mastery("Savagery", "Single target attacks and spells deal 1/2/3/4/5 bonus damage to minions and monsters", 6312, 0, imageSavagery, textViewSavagery);
        mastery[masteryInt[6331]] = new Mastery("Merciless", "Deal 1%/2%/3%/4%/5% increased damage to champions below 40% Health", 6331, 0, imageMerciless, textViewMerciless);
        mastery[masteryInt[6332]] = new Mastery("Meditation", "Regenerate 0.3%/0.6%/0.9%/1.2%/1.5% of your missing Mana every 5 seconds", 6332, 0, imageMeditation, textViewMeditation);
        mastery[masteryInt[6251]] = new Mastery("Swiftness", "+3%/6%/9%/12%/15% Tenacity and Slow Resist", 6251, 0, imageSwiftness, textViewSwiftness);
        mastery[masteryInt[6252]] = new Mastery("Legendary Guardian", "+0.6/1.2/1.8/2.4/3 Armor and Magic Resist for each nearby enemy champion", 6252, 0, imageLegendaryGuardian, textViewLegendaryGuardian);
        mastery[masteryInt[6221]] = new Mastery("Explorer", "+15 Movement Speed in Brush and River", 6221, 0, imageExplorer, textViewExplorer);
        mastery[masteryInt[6362]] = new Mastery("Thunderlord's Decree", "Your 3rd attack or damaging spell against the same enemy champion deals magic damage in the area." +
                "Damage: 10 per level, plus 30% of your Bonus Attack Damage, and 10% of your Ability Power (25-15 second cooldown, based on level).", 6362, 0, imageThunderlordsDecree, textViewThunderlordsDecree);
        mastery[masteryInt[6361]] = new Mastery("Stormraider's Surge", "Dealing 30% of a champion's max Health within " +
                "2.5 seconds grants you 40% Movement Speed and 75% Slow Resistance for 3 seconds (10 second cooldown).", 6361, 0, imageStormraidersSurge, textViewStormraidersSurge);
        mastery[masteryInt[6363]] = new Mastery("Windspeaker's Blessing", "Your heals and shields are 10% stronger. " +
                "Additionally, your shields and heals on other allies increase their armor by 5-22 (based on level) and their magic resistance by half that amount for 3 seconds.", 6363, 0, imageWindspeakersBlessing, textViewWindspeakersBlessing);
        mastery[masteryInt[6223]] = new Mastery("Tough Skin", "You take 2 less damage from champion and neutral monster basic attacks", 6223, 0, imageToughSkin, textViewToughSkin);
        mastery[masteryInt[6222]] = new Mastery("Siegemaster", "Gain 8 Armor and Magic Resistance when near an allied tower", 6222, 0, imageSiegemaster, textViewSiegemaster);
    }
    public void masteryUI() {
        activeTotal = 0;
        mastery[0].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[0].active != 5) && (activeTotal < 30)) || ((mastery[0].active != 5)
                        && (activeTotal == 30) && (mastery[1].active >= 1))) {
                    mastery[0].active = mastery[0].active + 1;
                    mastery[0].textView.setText(" " + mastery[0].active + "/5 ");
                    if (mastery[1].active > 5 - mastery[0].active || activeTotal == 30) {
                        mastery[1].active = mastery[1].active - 1;
                    }
                    if (mastery[1].active <= 0) {
                        mastery[1].active = 0;
                        mastery[1].textView.setText("");
                    } else {
                        mastery[1].textView.setText(" " + mastery[1].active + "/5 ");
                    }
                    activeTotal = 0;
                    for (int i = 0; i < 45; i++) {
                        activeTotal = activeTotal + mastery[i].active;
                    }
                }
            }
        });
        mastery[1].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[1].active != 5) && (activeTotal < 30)) || ((mastery[1].active != 5)
                        && (activeTotal == 30) && (mastery[0].active >= 1))) {
                    mastery[1].active = mastery[1].active + 1;
                    mastery[1].textView.setText(" " + mastery[1].active + "/5 ");
                    if (mastery[0].active > 5 - mastery[1].active || activeTotal == 30) {
                        mastery[0].active = mastery[0].active - 1;
                    }
                    if (mastery[0].active <= 0) {
                        mastery[0].active = 0;
                        mastery[0].textView.setText("");
                    } else {
                        mastery[0].textView.setText(" " + mastery[0].active + "/5 ");
                    }
                    activeTotal = 0;
                    for (int i = 0; i < 45; i++) {
                        activeTotal = activeTotal + mastery[i].active;
                    }
                }
            }
        });
        mastery[2].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[2].active != 1) && (mastery[0].active + mastery[1].active == 5)
                        && (activeTotal < 30)) || ((mastery[2].active != 1) && (activeTotal == 30)
                        && (mastery[3].active >= 1 || mastery[4].active >= 1) && (mastery[0].active
                        + mastery[1].active == 5))) {
                    mastery[2].active = mastery[2].active + 1;
                    mastery[2].textView.setText(" " + mastery[2].active + "/1 ");
                    mastery[3].active = 0;
                    mastery[3].textView.setText("");
                    mastery[4].active = 0;
                    mastery[4].textView.setText("");
                }
                activeTotal = 0;
                for (int i = 0; i < 45; i++) {
                    activeTotal = activeTotal + mastery[i].active;
                }
            }
        });
        mastery[3].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[3].active != 1) && (mastery[0].active + mastery[1].active == 5)
                        && (activeTotal < 30)) || ((mastery[2].active != 1) && (activeTotal == 30)
                        && (mastery[2].active >= 1 || mastery[4].active >= 1) && (mastery[0].active
                        + mastery[1].active == 5))) {
                    mastery[3].active = mastery[3].active + 1;
                    mastery[3].textView.setText(" " + mastery[3].active + "/1 ");
                    mastery[2].active = 0;
                    mastery[2].textView.setText("");
                    mastery[4].active = 0;
                    mastery[4].textView.setText("");
                }
                activeTotal = 0;
                for (int i = 0; i < 45; i++) {
                    activeTotal = activeTotal + mastery[i].active;
                }
            }
        });
        mastery[4].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[4].active != 1) && (mastery[0].active + mastery[1].active == 5)
                        && (activeTotal < 30)) || ((mastery[2].active != 1) && (activeTotal == 30)
                        && (mastery[2].active >= 1 || mastery[3].active >= 1) && (mastery[0].active
                        + mastery[1].active == 5))) {
                    mastery[4].active = mastery[4].active + 1;
                    mastery[4].textView.setText(" " + mastery[4].active + "/1 ");
                    mastery[3].active = 0;
                    mastery[3].textView.setText("");
                    mastery[2].active = 0;
                    mastery[2].textView.setText("");
                }
                activeTotal = 0;
                for (int i = 0; i < 45; i++) {
                    activeTotal = activeTotal + mastery[i].active;
                }
            }
        });
        mastery[5].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[5].active != 5) && (mastery[4].active + mastery[3].active + mastery[2].active == 1)
                        && (activeTotal < 30)) || ((mastery[5].active != 5) && (activeTotal == 30) && (mastery[6].active >= 1)
                        && (mastery[4].active + mastery[3].active + mastery[2].active == 1))) {
                    mastery[5].active = mastery[5].active + 1;
                    mastery[5].textView.setText(" " + mastery[5].active + "/5 ");
                    if (mastery[6].active > 5 - mastery[5].active || activeTotal == 30) {
                        mastery[6].active = mastery[6].active - 1;
                    }
                    if (mastery[6].active <= 0) {
                        mastery[6].active = 0;
                        mastery[6].textView.setText("");
                    } else {
                        mastery[6].textView.setText(" " + mastery[6].active + "/5 ");
                    }
                    activeTotal = 0;
                    for (int i = 0; i < 45; i++) {
                        activeTotal = activeTotal + mastery[i].active;
                    }
                }
            }
        });

        mastery[6].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[6].active != 5) && (mastery[4].active + mastery[3].active + mastery[2].active == 1)
                        && (activeTotal < 30)) || ((mastery[6].active != 5) && (activeTotal == 30) && (mastery[5].active >= 1)
                        && (mastery[4].active + mastery[3].active + mastery[2].active == 1))) {
                    mastery[6].active = mastery[6].active + 1;
                    mastery[6].textView.setText(" " + mastery[6].active + "/5 ");
                    if (mastery[5].active > 5 - mastery[6].active || activeTotal == 30) {
                        mastery[5].active = mastery[5].active - 1;
                    }
                    if (mastery[5].active <= 0) {
                        mastery[5].active = 0;
                        mastery[5].textView.setText("");
                    } else {
                        mastery[5].textView.setText(" " + mastery[5].active + "/5 ");
                    }
                    activeTotal = 0;
                    for (int i = 0; i < 45; i++) {
                        activeTotal = activeTotal + mastery[i].active;
                    }
                }
            }
        });
        mastery[7].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[7].active != 1) && (mastery[5].active + mastery[6].active == 5)
                        && (activeTotal < 30)) || ((mastery[7].active != 1) && (activeTotal == 30)
                        && (mastery[8].active >= 1 || mastery[9].active >= 1) && (mastery[5].active
                        + mastery[6].active == 5))) {
                    mastery[7].active = mastery[7].active + 1;
                    mastery[7].textView.setText(" " + mastery[7].active + "/1 ");
                    mastery[8].active = 0;
                    mastery[8].textView.setText("");
                    mastery[9].active = 0;
                    mastery[9].textView.setText("");
                }
                activeTotal = 0;
                for (int i = 0; i < 45; i++) {
                    activeTotal = activeTotal + mastery[i].active;
                }
            }
        });
        mastery[8].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[8].active != 1) && (mastery[5].active + mastery[6].active == 5)
                        && (activeTotal < 30)) || ((mastery[8].active != 1) && (activeTotal == 30)
                        && (mastery[7].active >= 1 || mastery[9].active >= 1) && (mastery[5].active
                        + mastery[6].active == 5))) {
                    mastery[8].active = mastery[8].active + 1;
                    mastery[8].textView.setText(" " + mastery[8].active + "/1 ");
                    mastery[7].active = 0;
                    mastery[7].textView.setText("");
                    mastery[9].active = 0;
                    mastery[9].textView.setText("");
                }
                activeTotal = 0;
                for (int i = 0; i < 45; i++) {
                    activeTotal = activeTotal + mastery[i].active;
                }
            }
        });
        mastery[9].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[9].active != 1) && (mastery[5].active + mastery[6].active == 5)
                        && (activeTotal < 30)) || ((mastery[9].active != 1) && (activeTotal == 30)
                        && (mastery[7].active >= 1 || mastery[8].active >= 1) && (mastery[5].active
                        + mastery[6].active == 5))) {
                    mastery[9].active = mastery[9].active + 1;
                    mastery[9].textView.setText(" " + mastery[9].active + "/1 ");
                    mastery[8].active = 0;
                    mastery[8].textView.setText("");
                    mastery[7].active = 0;
                    mastery[7].textView.setText("");
                }
                activeTotal = 0;
                for (int i = 0; i < 45; i++) {
                    activeTotal = activeTotal + mastery[i].active;
                }
            }
        });
        mastery[10].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[10].active != 5) && (mastery[9].active + mastery[8].active + mastery[7].active == 1)
                        && (activeTotal < 30)) || ((mastery[10].active != 5) && (activeTotal == 30) && (mastery[11].active >= 1)
                        && (mastery[9].active + mastery[8].active + mastery[7].active == 1))) {
                    mastery[10].active = mastery[10].active + 1;
                    mastery[10].textView.setText(" " + mastery[10].active + "/5 ");
                    if (mastery[11].active > 5 - mastery[10].active || activeTotal == 30) {
                        mastery[11].active = mastery[11].active - 1;
                    }
                    if (mastery[11].active <= 0) {
                        mastery[11].active = 0;
                        mastery[11].textView.setText("");
                    } else {
                        mastery[11].textView.setText(" " + mastery[11].active + "/5 ");
                    }
                    activeTotal = 0;
                    for (int i = 0; i < 45; i++) {
                        activeTotal = activeTotal + mastery[i].active;
                    }
                }
            }
        });

        mastery[11].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[11].active != 5) && (mastery[9].active + mastery[8].active + mastery[7].active == 1)
                        && (activeTotal < 30)) || ((mastery[11].active != 5) && (activeTotal == 30) && (mastery[10].active >= 1)
                        && (mastery[9].active + mastery[8].active + mastery[7].active == 1))) {
                    mastery[11].active = mastery[11].active + 1;
                    mastery[11].textView.setText(" " + mastery[11].active + "/5 ");
                    if (mastery[10].active > 5 - mastery[11].active || activeTotal == 30) {
                        mastery[10].active = mastery[10].active - 1;
                    }
                    if (mastery[10].active <= 0) {
                        mastery[10].active = 0;
                        mastery[10].textView.setText("");
                    } else {
                        mastery[10].textView.setText(" " + mastery[10].active + "/5 ");
                    }
                    activeTotal = 0;
                    for (int i = 0; i < 45; i++) {
                        activeTotal = activeTotal + mastery[i].active;
                    }
                }
            }
        });
        mastery[12].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[12].active != 1) && (mastery[10].active + mastery[11].active == 5)
                        && (activeTotal < 30)) || ((mastery[12].active != 1) && (activeTotal == 30)
                        && (mastery[13].active >= 1 || mastery[14].active >= 1) && (mastery[10].active
                        + mastery[11].active == 5))) {
                    mastery[12].active = mastery[12].active + 1;
                    mastery[12].textView.setText(" " + mastery[12].active + "/1 ");
                    mastery[13].active = 0;
                    mastery[13].textView.setText("");
                    mastery[14].active = 0;
                    mastery[14].textView.setText("");
                }
                activeTotal = 0;
                for (int i = 0; i < 45; i++) {
                    activeTotal = activeTotal + mastery[i].active;
                }
            }
        });
        mastery[13].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[13].active != 1) && (mastery[10].active + mastery[11].active == 5)
                        && (activeTotal < 30)) || ((mastery[13].active != 1) && (activeTotal == 30)
                        && (mastery[12].active >= 1 || mastery[14].active >= 1) && (mastery[10].active
                        + mastery[11].active == 5))) {
                    mastery[13].active = mastery[13].active + 1;
                    mastery[13].textView.setText(" " + mastery[13].active + "/1 ");
                    mastery[12].active = 0;
                    mastery[12].textView.setText("");
                    mastery[14].active = 0;
                    mastery[14].textView.setText("");
                }
                activeTotal = 0;
                for (int i = 0; i < 45; i++) {
                    activeTotal = activeTotal + mastery[i].active;
                }
            }
        });
        mastery[14].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[14].active != 1) && (mastery[10].active + mastery[11].active == 5)
                        && (activeTotal < 30)) || ((mastery[14].active != 1) && (activeTotal == 30)
                        && (mastery[12].active >= 1 || mastery[13].active >= 1) && (mastery[10].active
                        + mastery[11].active == 5))) {
                    mastery[14].active = mastery[14].active + 1;
                    mastery[14].textView.setText(" " + mastery[14].active + "/1 ");
                    mastery[13].active = 0;
                    mastery[13].textView.setText("");
                    mastery[12].active = 0;
                    mastery[12].textView.setText("");
                }
                activeTotal = 0;
                for (int i = 0; i < 45; i++) {
                    activeTotal = activeTotal + mastery[i].active;
                }
            }
        });
        mastery[15].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[15].active != 5) && (activeTotal < 30)) || ((mastery[15].active != 5)
                        && (activeTotal == 30) && (mastery[16].active >= 1))) {
                    mastery[15].active = mastery[15].active + 1;
                    mastery[15].textView.setText(" " + mastery[15].active + "/5 ");
                    if (mastery[16].active > 5 - mastery[15].active || activeTotal == 30) {
                        mastery[16].active = mastery[16].active - 1;
                    }
                    if (mastery[16].active <= 0) {
                        mastery[16].active = 0;
                        mastery[16].textView.setText("");
                    } else {
                        mastery[16].textView.setText(" " + mastery[16].active + "/5 ");
                    }
                    activeTotal = 0;
                    for (int i = 0; i < 45; i++) {
                        activeTotal = activeTotal + mastery[i].active;
                    }
                }
            }
        });

        mastery[16].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[16].active != 5) && (activeTotal < 30)) || ((mastery[16].active != 5)
                        && (activeTotal == 30) && (mastery[15].active >= 1))) {
                    mastery[16].active = mastery[16].active + 1;
                    mastery[16].textView.setText(" " + mastery[16].active + "/5 ");
                    if (mastery[15].active > 5 - mastery[16].active || activeTotal == 30) {
                        mastery[15].active = mastery[15].active - 1;
                    }
                    if (mastery[15].active <= 0) {
                        mastery[15].active = 0;
                        mastery[15].textView.setText("");
                    } else {
                        mastery[15].textView.setText(" " + mastery[15].active + "/5 ");
                    }
                    activeTotal = 0;
                    for (int i = 0; i < 45; i++) {
                        activeTotal = activeTotal + mastery[i].active;
                    }
                }
            }
        });
        mastery[17].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[17].active != 1) && (mastery[15].active + mastery[16].active == 5)
                        && (activeTotal < 30)) || ((mastery[17].active != 1) && (activeTotal == 30)
                        && (mastery[18].active >= 1 || mastery[19].active >= 1) && (mastery[15].active
                        + mastery[16].active == 5))) {
                    mastery[17].active = mastery[17].active + 1;
                    mastery[17].textView.setText(" " + mastery[17].active + "/1 ");
                    mastery[18].active = 0;
                    mastery[18].textView.setText("");
                    mastery[19].active = 0;
                    mastery[19].textView.setText("");
                }
                activeTotal = 0;
                for (int i = 0; i < 45; i++) {
                    activeTotal = activeTotal + mastery[i].active;
                }
            }
        });
        mastery[18].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[18].active != 1) && (mastery[15].active + mastery[16].active == 5)
                        && (activeTotal < 30)) || ((mastery[18].active != 1) && (activeTotal == 30)
                        && (mastery[17].active >= 1 || mastery[19].active >= 1) && (mastery[15].active
                        + mastery[16].active == 5))) {
                    mastery[18].active = mastery[18].active + 1;
                    mastery[18].textView.setText(" " + mastery[18].active + "/1 ");
                    mastery[17].active = 0;
                    mastery[17].textView.setText("");
                    mastery[19].active = 0;
                    mastery[19].textView.setText("");
                }
                activeTotal = 0;
                for (int i = 0; i < 45; i++) {
                    activeTotal = activeTotal + mastery[i].active;
                }
            }
        });
        mastery[19].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[19].active != 1) && (mastery[15].active + mastery[16].active == 5)
                        && (activeTotal < 30)) || ((mastery[19].active != 1) && (activeTotal == 30)
                        && (mastery[17].active >= 1 || mastery[18].active >= 1) && (mastery[15].active
                        + mastery[16].active == 5))) {
                    mastery[19].active = mastery[19].active + 1;
                    mastery[19].textView.setText(" " + mastery[19].active + "/1 ");
                    mastery[18].active = 0;
                    mastery[18].textView.setText("");
                    mastery[17].active = 0;
                    mastery[17].textView.setText("");
                }
                activeTotal = 0;
                for (int i = 0; i < 45; i++) {
                    activeTotal = activeTotal + mastery[i].active;
                }
            }
        });
        mastery[20].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[20].active != 5) && (mastery[19].active + mastery[18].active + mastery[17].active == 1)
                        && (activeTotal < 30)) || ((mastery[20].active != 5) && (activeTotal == 30) && (mastery[21].active >= 1)
                        && (mastery[19].active + mastery[18].active + mastery[17].active == 1))) {
                    mastery[20].active = mastery[20].active + 1;
                    mastery[20].textView.setText(" " + mastery[20].active + "/5 ");
                    if (mastery[21].active > 5 - mastery[20].active || activeTotal == 30) {
                        mastery[21].active = mastery[21].active - 1;
                    }
                    if (mastery[21].active <= 0) {
                        mastery[21].active = 0;
                        mastery[21].textView.setText("");
                    } else {
                        mastery[21].textView.setText(" " + mastery[21].active + "/5 ");
                    }
                    activeTotal = 0;
                    for (int i = 0; i < 45; i++) {
                        activeTotal = activeTotal + mastery[i].active;
                    }
                }
            }
        });

        mastery[21].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[21].active != 5) && (mastery[19].active + mastery[18].active + mastery[17].active == 1)
                        && (activeTotal < 30)) || ((mastery[21].active != 5) && (activeTotal == 30) && (mastery[20].active >= 1)
                        && (mastery[19].active + mastery[18].active + mastery[17].active == 1))) {
                    mastery[21].active = mastery[21].active + 1;
                    mastery[21].textView.setText(" " + mastery[21].active + "/5 ");
                    if (mastery[20].active > 5 - mastery[21].active || activeTotal == 30) {
                        mastery[20].active = mastery[20].active - 1;
                    }
                    if (mastery[20].active <= 0) {
                        mastery[20].active = 0;
                        mastery[20].textView.setText("");
                    } else {
                        mastery[20].textView.setText(" " + mastery[20].active + "/5 ");
                    }
                    activeTotal = 0;
                    for (int i = 0; i < 45; i++) {
                        activeTotal = activeTotal + mastery[i].active;
                    }
                }
            }
        });
        mastery[22].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[22].active != 1) && (mastery[20].active + mastery[21].active == 5)
                        && (activeTotal < 30)) || ((mastery[22].active != 1) && (activeTotal == 30)
                        && (mastery[23].active >= 1 || mastery[24].active >= 1) && (mastery[20].active
                        + mastery[21].active == 5))) {
                    mastery[22].active = mastery[22].active + 1;
                    mastery[22].textView.setText(" " + mastery[22].active + "/1 ");
                    mastery[23].active = 0;
                    mastery[23].textView.setText("");
                    mastery[24].active = 0;
                    mastery[24].textView.setText("");
                }
                activeTotal = 0;
                for (int i = 0; i < 45; i++) {
                    activeTotal = activeTotal + mastery[i].active;
                }
            }
        });
        mastery[23].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[23].active != 1) && (mastery[20].active + mastery[21].active == 5)
                        && (activeTotal < 30)) || ((mastery[23].active != 1) && (activeTotal == 30)
                        && (mastery[22].active >= 1 || mastery[24].active >= 1) && (mastery[20].active
                        + mastery[21].active == 5))) {
                    mastery[23].active = mastery[23].active + 1;
                    mastery[23].textView.setText(" " + mastery[23].active + "/1 ");
                    mastery[22].active = 0;
                    mastery[22].textView.setText("");
                    mastery[24].active = 0;
                    mastery[24].textView.setText("");
                }
                activeTotal = 0;
                for (int i = 0; i < 45; i++) {
                    activeTotal = activeTotal + mastery[i].active;
                }
            }
        });
        mastery[24].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[24].active != 1) && (mastery[20].active + mastery[21].active == 5)
                        && (activeTotal < 30)) || ((mastery[24].active != 1) && (activeTotal == 30)
                        && (mastery[22].active >= 1 || mastery[23].active >= 1) && (mastery[20].active
                        + mastery[21].active == 5))) {
                    mastery[24].active = mastery[24].active + 1;
                    mastery[24].textView.setText(" " + mastery[24].active + "/1 ");
                    mastery[23].active = 0;
                    mastery[23].textView.setText("");
                    mastery[22].active = 0;
                    mastery[22].textView.setText("");
                }
                activeTotal = 0;
                for (int i = 0; i < 45; i++) {
                    activeTotal = activeTotal + mastery[i].active;
                }
            }
        });
        mastery[25].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[25].active != 5) && (mastery[24].active + mastery[23].active + mastery[22].active == 1)
                        && (activeTotal < 30)) || ((mastery[25].active != 5) && (activeTotal == 30) && (mastery[26].active >= 1)
                        && (mastery[24].active + mastery[23].active + mastery[22].active == 1))) {
                    mastery[25].active = mastery[25].active + 1;
                    mastery[25].textView.setText(" " + mastery[25].active + "/5 ");
                    if (mastery[26].active > 5 - mastery[25].active || activeTotal == 30) {
                        mastery[26].active = mastery[26].active - 1;
                    }
                    if (mastery[26].active <= 0) {
                        mastery[26].active = 0;
                        mastery[26].textView.setText("");
                    } else {
                        mastery[26].textView.setText(" " + mastery[26].active + "/5 ");
                    }
                    activeTotal = 0;
                    for (int i = 0; i < 45; i++) {
                        activeTotal = activeTotal + mastery[i].active;
                    }
                }
            }
        });

        mastery[26].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[26].active != 5) && (mastery[24].active + mastery[23].active + mastery[22].active == 1)
                        && (activeTotal < 30)) || ((mastery[26].active != 5) && (activeTotal == 30) && (mastery[25].active >= 1)
                        && (mastery[24].active + mastery[23].active + mastery[22].active == 1))) {
                    mastery[26].active = mastery[26].active + 1;
                    mastery[26].textView.setText(" " + mastery[26].active + "/5 ");
                    if (mastery[25].active > 5 - mastery[26].active || activeTotal == 30) {
                        mastery[25].active = mastery[25].active - 1;
                    }
                    if (mastery[25].active <= 0) {
                        mastery[25].active = 0;
                        mastery[25].textView.setText("");
                    } else {
                        mastery[25].textView.setText(" " + mastery[25].active + "/5 ");
                    }
                    activeTotal = 0;
                    for (int i = 0; i < 45; i++) {
                        activeTotal = activeTotal + mastery[i].active;
                    }
                }
            }
        });
        mastery[27].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[27].active != 1) && (mastery[25].active + mastery[26].active == 5)
                        && (activeTotal < 30)) || ((mastery[27].active != 1) && (activeTotal == 30)
                        && (mastery[28].active >= 1 || mastery[29].active >= 1) && (mastery[25].active
                        + mastery[26].active == 5))) {
                    mastery[27].active = mastery[27].active + 1;
                    mastery[27].textView.setText(" " + mastery[27].active + "/1 ");
                    mastery[28].active = 0;
                    mastery[28].textView.setText("");
                    mastery[29].active = 0;
                    mastery[29].textView.setText("");
                }
                activeTotal = 0;
                for (int i = 0; i < 45; i++) {
                    activeTotal = activeTotal + mastery[i].active;
                }
            }
        });
        mastery[28].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[28].active != 1) && (mastery[25].active + mastery[26].active == 5)
                        && (activeTotal < 30)) || ((mastery[28].active != 1) && (activeTotal == 30)
                        && (mastery[27].active >= 1 || mastery[29].active >= 1) && (mastery[25].active
                        + mastery[26].active == 5))) {
                    mastery[28].active = mastery[28].active + 1;
                    mastery[28].textView.setText(" " + mastery[28].active + "/1 ");
                    mastery[27].active = 0;
                    mastery[27].textView.setText("");
                    mastery[29].active = 0;
                    mastery[29].textView.setText("");
                }
                activeTotal = 0;
                for (int i = 0; i < 45; i++) {
                    activeTotal = activeTotal + mastery[i].active;
                }
            }
        });
        mastery[29].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[29].active != 1) && (mastery[25].active + mastery[26].active == 5)
                        && (activeTotal < 30)) || ((mastery[29].active != 1) && (activeTotal == 30)
                        && (mastery[27].active >= 1 || mastery[28].active >= 1) && (mastery[25].active
                        + mastery[26].active == 5))) {
                    mastery[29].active = mastery[29].active + 1;
                    mastery[29].textView.setText(" " + mastery[29].active + "/1 ");
                    mastery[28].active = 0;
                    mastery[28].textView.setText("");
                    mastery[27].active = 0;
                    mastery[27].textView.setText("");
                }
                activeTotal = 0;
                for (int i = 0; i < 45; i++) {
                    activeTotal = activeTotal + mastery[i].active;
                }
            }
        });
        mastery[30].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[30].active != 5) && (activeTotal < 30)) || ((mastery[30].active != 5)
                        && (activeTotal == 30) && (mastery[31].active >= 1))) {
                    mastery[30].active = mastery[30].active + 1;
                    mastery[30].textView.setText(" " + mastery[30].active + "/5 ");
                    if (mastery[31].active > 5 - mastery[30].active || activeTotal == 30) {
                        mastery[31].active = mastery[31].active - 1;
                    }
                    if (mastery[31].active <= 0) {
                        mastery[31].active = 0;
                        mastery[31].textView.setText("");
                    } else {
                        mastery[31].textView.setText(" " + mastery[31].active + "/5 ");
                    }
                    activeTotal = 0;
                    for (int i = 0; i < 45; i++) {
                        activeTotal = activeTotal + mastery[i].active;
                    }
                }
            }
        });

        mastery[31].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[31].active != 5) && (activeTotal < 30)) || ((mastery[31].active != 5)
                        && (activeTotal == 30) && (mastery[30].active >= 1))) {
                    mastery[31].active = mastery[31].active + 1;
                    mastery[31].textView.setText(" " + mastery[31].active + "/5 ");
                    if (mastery[30].active > 5 - mastery[31].active || activeTotal == 30) {
                        mastery[30].active = mastery[30].active - 1;
                    }
                    if (mastery[30].active <= 0) {
                        mastery[30].active = 0;
                        mastery[30].textView.setText("");
                    } else {
                        mastery[30].textView.setText(" " + mastery[30].active + "/5 ");
                    }
                    activeTotal = 0;
                    for (int i = 0; i < 45; i++) {
                        activeTotal = activeTotal + mastery[i].active;
                    }
                }
            }
        });
        mastery[32].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[32].active != 1) && (mastery[30].active + mastery[31].active == 5)
                        && (activeTotal < 30)) || ((mastery[32].active != 1) && (activeTotal == 30)
                        && (mastery[33].active >= 1 || mastery[34].active >= 1) && (mastery[30].active
                        + mastery[31].active == 5))) {
                    mastery[32].active = mastery[32].active + 1;
                    mastery[32].textView.setText(" " + mastery[32].active + "/1 ");
                    mastery[33].active = 0;
                    mastery[33].textView.setText("");
                    mastery[34].active = 0;
                    mastery[34].textView.setText("");
                }
                activeTotal = 0;
                for (int i = 0; i < 45; i++) {
                    activeTotal = activeTotal + mastery[i].active;
                }
            }
        });
        mastery[33].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[33].active != 1) && (mastery[30].active + mastery[31].active == 5)
                        && (activeTotal < 30)) || ((mastery[33].active != 1) && (activeTotal == 30)
                        && (mastery[32].active >= 1 || mastery[34].active >= 1) && (mastery[30].active
                        + mastery[31].active == 5))) {
                    mastery[33].active = mastery[33].active + 1;
                    mastery[33].textView.setText(" " + mastery[33].active + "/1 ");
                    mastery[32].active = 0;
                    mastery[32].textView.setText("");
                    mastery[34].active = 0;
                    mastery[34].textView.setText("");
                }
                activeTotal = 0;
                for (int i = 0; i < 45; i++) {
                    activeTotal = activeTotal + mastery[i].active;
                }
            }
        });
        mastery[34].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[34].active != 1) && (mastery[30].active + mastery[31].active == 5)
                        && (activeTotal < 30)) || ((mastery[34].active != 1) && (activeTotal == 30)
                        && (mastery[32].active >= 1 || mastery[33].active >= 1) && (mastery[30].active
                        + mastery[31].active == 5))) {
                    mastery[34].active = mastery[34].active + 1;
                    mastery[34].textView.setText(" " + mastery[34].active + "/1 ");
                    mastery[33].active = 0;
                    mastery[33].textView.setText("");
                    mastery[32].active = 0;
                    mastery[32].textView.setText("");
                }
                activeTotal = 0;
                for (int i = 0; i < 45; i++) {
                    activeTotal = activeTotal + mastery[i].active;
                }
            }
        });
        mastery[35].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[35].active != 5) && (mastery[34].active + mastery[33].active + mastery[32].active == 1)
                        && (activeTotal < 30)) || ((mastery[35].active != 5) && (activeTotal == 30) && (mastery[36].active >= 1)
                        && (mastery[34].active + mastery[33].active + mastery[32].active == 1))) {
                    mastery[35].active = mastery[35].active + 1;
                    mastery[35].textView.setText(" " + mastery[35].active + "/5 ");
                    if (mastery[36].active > 5 - mastery[35].active || activeTotal == 30) {
                        mastery[36].active = mastery[36].active - 1;
                    }
                    if (mastery[36].active <= 0) {
                        mastery[36].active = 0;
                        mastery[36].textView.setText("");
                    } else {
                        mastery[36].textView.setText(" " + mastery[36].active + "/5 ");
                    }
                    activeTotal = 0;
                    for (int i = 0; i < 45; i++) {
                        activeTotal = activeTotal + mastery[i].active;
                    }
                }
            }
        });

        mastery[36].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[36].active != 5) && (mastery[34].active + mastery[33].active + mastery[32].active == 1)
                        && (activeTotal < 30)) || ((mastery[36].active != 5) && (activeTotal == 30) && (mastery[35].active >= 1)
                        && (mastery[34].active + mastery[33].active + mastery[32].active == 1))) {
                    mastery[36].active = mastery[36].active + 1;
                    mastery[36].textView.setText(" " + mastery[36].active + "/5 ");
                    if (mastery[35].active > 5 - mastery[36].active || activeTotal == 30) {
                        mastery[35].active = mastery[35].active - 1;
                    }
                    if (mastery[35].active <= 0) {
                        mastery[35].active = 0;
                        mastery[35].textView.setText("");
                    } else {
                        mastery[35].textView.setText(" " + mastery[35].active + "/5 ");
                    }
                    activeTotal = 0;
                    for (int i = 0; i < 45; i++) {
                        activeTotal = activeTotal + mastery[i].active;
                    }
                }
            }
        });
        mastery[37].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[37].active != 1) && (mastery[35].active + mastery[36].active == 5)
                        && (activeTotal < 30)) || ((mastery[37].active != 1) && (activeTotal == 30)
                        && (mastery[38].active >= 1 || mastery[39].active >= 1) && (mastery[35].active
                        + mastery[36].active == 5))) {
                    mastery[37].active = mastery[37].active + 1;
                    mastery[37].textView.setText(" " + mastery[37].active + "/1 ");
                    mastery[38].active = 0;
                    mastery[38].textView.setText("");
                    mastery[39].active = 0;
                    mastery[39].textView.setText("");
                }
                activeTotal = 0;
                for (int i = 0; i < 45; i++) {
                    activeTotal = activeTotal + mastery[i].active;
                }
            }
        });
        mastery[38].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[38].active != 1) && (mastery[35].active + mastery[36].active == 5)
                        && (activeTotal < 30)) || ((mastery[38].active != 1) && (activeTotal == 30)
                        && (mastery[37].active >= 1 || mastery[39].active >= 1) && (mastery[35].active
                        + mastery[36].active == 5))) {
                    mastery[38].active = mastery[38].active + 1;
                    mastery[38].textView.setText(" " + mastery[38].active + "/1 ");
                    mastery[37].active = 0;
                    mastery[37].textView.setText("");
                    mastery[39].active = 0;
                    mastery[39].textView.setText("");
                }
                activeTotal = 0;
                for (int i = 0; i < 45; i++) {
                    activeTotal = activeTotal + mastery[i].active;
                }
            }
        });
        mastery[39].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[39].active != 1) && (mastery[35].active + mastery[36].active == 5)
                        && (activeTotal < 30)) || ((mastery[39].active != 1) && (activeTotal == 30)
                        && (mastery[37].active >= 1 || mastery[38].active >= 1) && (mastery[35].active
                        + mastery[36].active == 5))) {
                    mastery[39].active = mastery[39].active + 1;
                    mastery[39].textView.setText(" " + mastery[39].active + "/1 ");
                    mastery[38].active = 0;
                    mastery[38].textView.setText("");
                    mastery[37].active = 0;
                    mastery[37].textView.setText("");
                }
                activeTotal = 0;
                for (int i = 0; i < 45; i++) {
                    activeTotal = activeTotal + mastery[i].active;
                }
            }
        });
        mastery[40].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[40].active != 5) && (mastery[39].active + mastery[38].active + mastery[37].active == 1)
                        && (activeTotal < 30)) || ((mastery[40].active != 5) && (activeTotal == 30) && (mastery[41].active >= 1)
                        && (mastery[39].active + mastery[38].active + mastery[37].active == 1))) {
                    mastery[40].active = mastery[40].active + 1;
                    mastery[40].textView.setText(" " + mastery[40].active + "/5 ");
                    if (mastery[41].active > 5 - mastery[40].active || activeTotal == 30) {
                        mastery[41].active = mastery[41].active - 1;
                    }
                    if (mastery[41].active <= 0) {
                        mastery[41].active = 0;
                        mastery[41].textView.setText("");
                    } else {
                        mastery[41].textView.setText(" " + mastery[41].active + "/5 ");
                    }
                    activeTotal = 0;
                    for (int i = 0; i < 45; i++) {
                        activeTotal = activeTotal + mastery[i].active;
                    }
                }
            }
        });

        mastery[41].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[41].active != 5) && (mastery[39].active + mastery[38].active + mastery[37].active == 1)
                        && (activeTotal < 30)) || ((mastery[41].active != 5) && (activeTotal == 30) && (mastery[40].active >= 1)
                        && (mastery[39].active + mastery[38].active + mastery[37].active == 1))) {
                    mastery[41].active = mastery[41].active + 1;
                    mastery[41].textView.setText(" " + mastery[41].active + "/5 ");
                    if (mastery[40].active > 5 - mastery[41].active || activeTotal == 30) {
                        mastery[40].active = mastery[40].active - 1;
                    }
                    if (mastery[40].active <= 0) {
                        mastery[40].active = 0;
                        mastery[40].textView.setText("");
                    } else {
                        mastery[40].textView.setText(" " + mastery[40].active + "/5 ");
                    }
                    activeTotal = 0;
                    for (int i = 0; i < 45; i++) {
                        activeTotal = activeTotal + mastery[i].active;
                    }
                }
            }
        });
        mastery[42].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[42].active != 1) && (mastery[40].active + mastery[41].active == 5)
                        && (activeTotal < 30)) || ((mastery[42].active != 1) && (activeTotal == 30)
                        && (mastery[43].active >= 1 || mastery[44].active >= 1) && (mastery[40].active
                        + mastery[41].active == 5))) {
                    mastery[42].active = mastery[42].active + 1;
                    mastery[42].textView.setText(" " + mastery[42].active + "/1 ");
                    mastery[43].active = 0;
                    mastery[43].textView.setText("");
                    mastery[44].active = 0;
                    mastery[44].textView.setText("");
                }
                activeTotal = 0;
                for (int i = 0; i < 45; i++) {
                    activeTotal = activeTotal + mastery[i].active;
                }
            }
        });
        mastery[43].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[43].active != 1) && (mastery[40].active + mastery[41].active == 5)
                        && (activeTotal < 30)) || ((mastery[43].active != 1) && (activeTotal == 30)
                        && (mastery[42].active >= 1 || mastery[44].active >= 1) && (mastery[40].active
                        + mastery[41].active == 5))) {
                    mastery[43].active = mastery[43].active + 1;
                    mastery[43].textView.setText(" " + mastery[43].active + "/1 ");
                    mastery[42].active = 0;
                    mastery[42].textView.setText("");
                    mastery[44].active = 0;
                    mastery[44].textView.setText("");
                }
                activeTotal = 0;
                for (int i = 0; i < 45; i++) {
                    activeTotal = activeTotal + mastery[i].active;
                }
            }
        });
        mastery[44].image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((mastery[44].active != 1) && (mastery[40].active + mastery[41].active == 5)
                        && (activeTotal < 30)) || ((mastery[44].active != 1) && (activeTotal == 30)
                        && (mastery[42].active >= 1 || mastery[43].active >= 1) && (mastery[40].active
                        + mastery[41].active == 5))) {
                    mastery[44].active = mastery[44].active + 1;
                    mastery[44].textView.setText(" " + mastery[44].active + "/1 ");
                    mastery[43].active = 0;
                    mastery[43].textView.setText("");
                    mastery[42].active = 0;
                    mastery[42].textView.setText("");
                }
                activeTotal = 0;
                for (int i = 0; i < 45; i++) {
                    activeTotal = activeTotal + mastery[i].active;
                }
            }
        });
    }
    public void versusBlocksSetUp(){
        championBlock[0] = (RelativeLayout) findViewById(R.id.championBlock1);
        championName[0] = (TextView) findViewById(R.id.championName1);
        summonersName[0] = (TextView) findViewById(R.id.summonersName1);
        championImage[0] = (ImageView) findViewById(R.id.championImage1);
        championBlock[0] = (RelativeLayout) findViewById(R.id.championBlock1);
        championName[0] = (TextView) findViewById(R.id.championName1);
        summonersName[0] = (TextView) findViewById(R.id.summonersName1);
        championImage[0] = (ImageView) findViewById(R.id.championImage1);
        championBlock[1] = (RelativeLayout) findViewById(R.id.championBlock2);
        championName[1] = (TextView) findViewById(R.id.championName2);
        summonersName[1] = (TextView) findViewById(R.id.summonersName2);
        championImage[1] = (ImageView) findViewById(R.id.championImage2);
        championBlock[2] = (RelativeLayout) findViewById(R.id.championBlock3);
        championName[2] = (TextView) findViewById(R.id.championName3);
        summonersName[2] = (TextView) findViewById(R.id.summonersName3);
        championImage[2] = (ImageView) findViewById(R.id.championImage3);
        championBlock[3] = (RelativeLayout) findViewById(R.id.championBlock4);
        championName[3] = (TextView) findViewById(R.id.championName4);
        summonersName[3] = (TextView) findViewById(R.id.summonersName4);
        championImage[3] = (ImageView) findViewById(R.id.championImage4);
        championBlock[4] = (RelativeLayout) findViewById(R.id.championBlock5);
        championName[4] = (TextView) findViewById(R.id.championName5);
        summonersName[4] = (TextView) findViewById(R.id.summonersName5);
        championImage[4] = (ImageView) findViewById(R.id.championImage5);
        championBlock[5] = (RelativeLayout) findViewById(R.id.championBlock6);
        championName[5] = (TextView) findViewById(R.id.championName6);
        summonersName[5] = (TextView) findViewById(R.id.summonersName6);
        championImage[5] = (ImageView) findViewById(R.id.championImage6);
        championBlock[6] = (RelativeLayout) findViewById(R.id.championBlock7);
        championName[6] = (TextView) findViewById(R.id.championName7);
        summonersName[6] = (TextView) findViewById(R.id.summonersName7);
        championImage[6] = (ImageView) findViewById(R.id.championImage7);
        championBlock[7] = (RelativeLayout) findViewById(R.id.championBlock8);
        championName[7] = (TextView) findViewById(R.id.championName8);
        summonersName[7] = (TextView) findViewById(R.id.summonersName8);
        championImage[7] = (ImageView) findViewById(R.id.championImage8);
        championBlock[8] = (RelativeLayout) findViewById(R.id.championBlock9);
        championName[8] = (TextView) findViewById(R.id.championName9);
        summonersName[8] = (TextView) findViewById(R.id.summonersName9);
        championImage[8] = (ImageView) findViewById(R.id.championImage9);
        championBlock[9] = (RelativeLayout) findViewById(R.id.championBlock10);
        championName[9] = (TextView) findViewById(R.id.championName10);
        summonersName[9] = (TextView) findViewById(R.id.summonersName10);
        championImage[9] = (ImageView) findViewById(R.id.championImage10);

        championBlock[0].setOnClickListener((new View.OnClickListener() {
            public void onClick(View view) {
                displayStats(0);
            }
        }));
        championBlock[1].setOnClickListener((new View.OnClickListener() {
            public void onClick(View view) {
                displayStats(1);
            }
        }));
        championBlock[2].setOnClickListener((new View.OnClickListener() {
            public void onClick(View view) {
                displayStats(2);
            }
        }));
        championBlock[3].setOnClickListener((new View.OnClickListener() {
            public void onClick(View view) {
                displayStats(3);
            }
        }));
        championBlock[4].setOnClickListener((new View.OnClickListener() {
            public void onClick(View view) {
                displayStats(4);
            }
        }));
        championBlock[5].setOnClickListener((new View.OnClickListener() {
            public void onClick(View view) {
                displayStats(5);
            }
        }));
        championBlock[6].setOnClickListener((new View.OnClickListener() {
            public void onClick(View view) {
                displayStats(6);
            }
        }));
        championBlock[7].setOnClickListener((new View.OnClickListener() {
            public void onClick(View view) {
                displayStats(7);
            }
        }));
        championBlock[8].setOnClickListener((new View.OnClickListener() {
            public void onClick(View view) {
                displayStats(8);
            }
        }));
        championBlock[9].setOnClickListener((new View.OnClickListener() {
            public void onClick(View view) {
                displayStats(9);
            }
        }));
    }

    public void displayStats(int participantNumb){
        try{
            //ArrayList<Mastery> activeMasteriesList = new ArrayList<>();
            ArrayList<ChampionAttributesListItem> results = new ArrayList<>();

            // Finds active masteries
            JSONObject jsonSummoner;
            jsonSummoner = jsonParticipants.getJSONObject(participantNumb);
            JSONArray participantMasteries = jsonSummoner.optJSONArray("masteries");
            JSONArray participantRunes = jsonSummoner.optJSONArray("runes");
            Log.i("masterysad", participantMasteries.length() + "");
            Log.i("runesads", participantRunes.length() + "");
            for (int y = 0; y < participantMasteries.length(); y++){
                ChampionAttributesListItem championAttributesListItem = new ChampionAttributesListItem();

                JSONObject masteryDetails;
                masteryDetails = participantMasteries.optJSONObject(y);
                mastery[masteryInt[masteryDetails.optInt("masteryId")]].active = masteryDetails.optInt("rank");
                championAttributesListItem.setchampionAttribute(mastery[masteryInt[masteryDetails.optInt("masteryId")]].description);
                championAttributesListItem.setchampionAttributeRank(mastery[masteryInt[masteryDetails.optInt("masteryId")]].active + "");
                results.add(championAttributesListItem);
                Log.i("mastery", masteryDetails.optInt("masteryId") + "");
            }

            for (int x = 0; x < participantRunes.length(); x++) {
                JSONObject runeDetails;
                runeDetails = participantRunes.optJSONObject(x);
                runes[runesInt[runeDetails.optInt("runeId")]].active = runeDetails.optInt("count");
                Log.i("rune", runeDetails.optInt("runeId") + "");
            }

            championInfoList.setAdapter(new ChampionInfoListAdapter(getApplicationContext(), results));


        }catch(Exception e){
            Log.e("ERROR", e.toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Allows toolbar to display alerts
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        //championIdToIndex();
        championSetUp x = new championSetUp();
        x.execute();
        runesSetUp y = new runesSetUp();
        y.execute();
        masteryIdToIndex();

        // Declares mastery image buttons, texts and actives
        masteryImageAndText();
        masteryReset();
        masteryUI();
        versusBlocksSetUp();


        // RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        //params1.addRule(RelativeLayout.ALIGN_LEFT, image.getId());
        //text.setLayoutParams(params1);

        // Sets up main tabs (Summoner, Masteries, etc.)
        final TabHost tabHost = (TabHost) findViewById(R.id.mainTabHost);
        tabHost.setup();

        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("refreshSummoner");
        tabSpec1.setContent(R.id.tabSummoner);
        tabSpec1.setIndicator("Refresh Summoner");
        tabHost.addTab(tabSpec1);

        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("champions");
        tabSpec2.setContent(R.id.tabChampions);
        tabSpec2.setIndicator("Champions");
        tabHost.addTab(tabSpec2);

        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("mastery");
        tabSpec3.setContent(R.id.tabMasteries);
        tabSpec3.setIndicator("Masteries");
        tabHost.addTab(tabSpec3);

        TabHost.TabSpec tabSpec4 = tabHost.newTabSpec("runes");
        tabSpec4.setContent(R.id.tabRunes);
        tabSpec4.setIndicator("Runes");
        tabHost.addTab(tabSpec4);

        // Sets up the 3 masteries tabs
        tabHostMasteries = (TabHost) findViewById(R.id.masteriesTabHost);
        tabHostMasteries.setup();

        TabHost.TabSpec tabDivisSpec1 = tabHostMasteries.newTabSpec("ferocity");
        tabDivisSpec1.setContent(R.id.tabMasteries1);
        tabDivisSpec1.setIndicator("FEROCITY");
        tabHostMasteries.addTab(tabDivisSpec1);

        TabHost.TabSpec tabDivisSpec2 = tabHostMasteries.newTabSpec("cunning");
        tabDivisSpec2.setContent(R.id.tabMasteries2);
        tabDivisSpec2.setIndicator("CUNNING");
        tabHostMasteries.addTab(tabDivisSpec2);

        TabHost.TabSpec tabDivisSpec3 = tabHostMasteries.newTabSpec("resolve");
        tabDivisSpec3.setContent(R.id.tabMasteries3);
        tabDivisSpec3.setIndicator("RESOLVE");
        tabHostMasteries.addTab(tabDivisSpec3);

        // Sets up main page
        final Button matchSearchButton = (Button) findViewById(R.id.matchSearchButton);
        summonerNameText = (EditText) findViewById(R.id.summonerNameText);
        regionSpinner = (Spinner) findViewById(R.id.regionSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.regions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regionSpinner.setAdapter(adapter);

        regionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int spinnerPosition, long id) {
                selectedRegion = spinnerPosition;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        matchSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedRegion == 0) {
                    summonerRegion = "na";
                } else if (selectedRegion == 1) {
                    summonerRegion = "euw";
                } else if (selectedRegion == 2) {
                    summonerRegion = "eune";
                } else if (selectedRegion == 3) {
                    summonerRegion = "kr";
                } else if (selectedRegion == 4) {
                    summonerRegion = "jp";
                } else if (selectedRegion == 5) {
                    summonerRegion = "br";
                } else if (selectedRegion == 6) {
                    summonerRegion = "lan";
                } else if (selectedRegion == 7) {
                    summonerRegion = "las";
                } else if (selectedRegion == 8) {
                    summonerRegion = "ru";
                } else if (selectedRegion == 9) {
                    summonerRegion = "oce";
                } else if (selectedRegion == 10) {
                    summonerRegion = "tr";
                }
                // Checks if the summoner name has been updated
                // If so, will allow other tabs to be refreshed
                if (summonerName == null || summonerName == "") {
                    // Builds and displays alert
                    AlertDialog summonerNameInvalidAlert = new AlertDialog.Builder(MainActivity.this).create();
                    summonerNameInvalidAlert.setMessage("No Summoner Name");
                    summonerNameInvalidAlert.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    summonerNameInvalidAlert.show();
                } else if (!summonerName.equals(summonerNameText.getText().toString())) {
                    summonerName = summonerNameText.getText().toString();
                    summonerNameNoSpaces = summonerNameText.getText().toString().replace(" ", "");

                    nameAndRegion nameAndRegionParams = new nameAndRegion(summonerNameNoSpaces, summonerRegion);
                    RetrieveSummonerId taskSummonerId = new RetrieveSummonerId();
                    taskSummonerId.execute(nameAndRegionParams);
                } else if (summonerName.equals(summonerNameText.getText().toString())) {
                    idAndRegion idAndRegionParams = new idAndRegion(summonerId, summonerRegion);
                    RetrieveMatch taskMatch = new RetrieveMatch();
                    taskMatch.execute(idAndRegionParams);
                    //setContentView(R.layout.choose_versus);
                }
            }
        });

        championInfoList = (ListView) findViewById(R.id.championInfoList);

/*
    tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

      @Override
      public void onTabChanged(String tabId) {
        int currentTabPosition = tabHost.getCurrentTab();
        // Will only call Riot API if the name has changed
        if (currentTabPosition == 1) {
          if (!matchHistoryLoaded){
            matchHistoryLoaded = true;
          }
        }
        if (currentTabPosition == 2) {
          // Refreshes masteries if they aren't loaded
          if (!divisionsLoaded) {
            try {
              divisionsLoaded = true;
              idAndRegion params = new idAndRegion(summonerId, summonerRegion);
              RetrieveDivisions taskSummerDivision = new RetrieveDivisions();
              taskSummerDivision.execute(params);
            } catch (Exception ex){
              divisionsLoaded = true;
              idAndRegion params = new idAndRegion(summonerId, summonerRegion);
              RetrieveDivisions taskSummerDivision = new RetrieveDivisions();
              taskSummerDivision.execute(params);
            }
          } else {
            try {
              // Sets current tab and list view position to the summoner's
              tabHostMasteries.setCurrentTab(protagonistListTab);
              divisionList[protagonistListTab].setSelection(protagonistPos);
            } catch (Exception e){
              // Displays alert
              AlertDialog noDivisionDialog = new AlertDialog.Builder(MainActivity.this).create();
              noDivisionDialog.setMessage("This player is not currently in any division.");
              noDivisionDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      dialog.dismiss();
                    }
                  });
              noDivisionDialog.show();
            }
          }
        }
      }
    });
    */
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}