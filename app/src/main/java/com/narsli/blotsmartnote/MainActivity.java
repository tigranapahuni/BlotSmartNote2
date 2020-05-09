package com.narsli.blotsmartnote;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.narsli.blotsmartnote.Adapter.RecyclerViewAdapter;
import com.narsli.blotsmartnote.Data.HashivAppDatabase;
import com.narsli.blotsmartnote.Model.BloteNote;
import com.narsli.blotsmartnote.Model.RecyclerViewItem;

import java.util.ArrayList;

//--------------------------------------------------------------

//-------------------------------------------------------------
public class MainActivity extends AppCompatActivity implements
        ExtraButtonDialog.BottomSheetListener,
        ExtraOrderBtnDialog.BottomSheetListenerOrder,
        View.OnClickListener {
    //_____________________________________________________________________
//--------------------------- POPOXAKANNER DB-ov----------------------------
//    private HashivAdapter hashivnerAdapter;
    private ArrayList<BloteNote> hashivArrayList = new ArrayList<>();
    private RecyclerView recyclerView_hashiv;
    //mer koxmic sarqac clasi ekzempliar
    private HashivAppDatabase hashivsAppDatabase;


    //--------------------------- POPOXAKANNER ----------------------------
    private TextView
            igrok1_D, igrok2_D, igrok3_D, igrok4_D;
    private Button
            txt_player1, txt_player2, txt_player3, txt_player4;
    private ImageView Img_mast;
    private ImageButton imgeViw_close_diler_dialog, imgBtn_Ok,
            imgeViw_close_diler_newGame, imgBtn_Ok_newGame;
    byte kom1_kom2, kom1_kom2_kanchele;
    int mast, Partia_Hashiv_Kom1 = 0, Partia_Hashiv_Kom2 = 0,
            fullHashiv_kom1, fullHashiv_kom2;
    String order;
    private LinearLayout resultLayout;
    private TextView textView_Kom1_New, textView_Kom2_New;
    private Dialog dialog_diler, dialog_newGame;
    private EditText
            edtTxt_igrok1, edtTxt_igrok2, edtTxt_igrok3, edtTxt_igrok4;
    private ArrayList<Integer> hashiv_Zangvac_kom1, hashiv_Zangvac_kom2;
    boolean mejtex_Nor_Tox = true;//ete true e nor tox e, ete false mejtexi
//_____________________________________________________________________
//___________________________menu popoxakanner ________________________

    FloatingActionButton fabMain, fabOne, fabTwo, fabThree, fabFour;
    TextView fabTextNg, fabTextEng, fabTextTheme, fabTextShare;
    Float translationY = 100f;

    OvershootInterpolator interpolator = new OvershootInterpolator();

    private static final String TAG = "MainActivity";

    Boolean isMenuOpen = false;
//     int igrok_ochered = 1;
//_____________________________________________________________________
//------------------------------ RecyvleView --------------------------

    //ays popoxakannern petq en vorpeszi razmetkai vra-i RecyclerView-ern
//    kapenq java kodi het
    private RecyclerView recyclerView;
    //adaptern nman e mosti vorn kapum e tvyalnern RecyclerView-i het
// henc adaptern e vor RecyclerView-n listview-i nkatmamb aravelutyun e sarqum \
// aysinqn  adaptern amboxj elemntnernn chi lcnum RecyclerView-i mej ayl miayn el
    // elementnern voronq hima user-n tesnum e ev mi qani hat harevan.
    private RecyclerView.Adapter adapter;
    //    xekavarum e elementneri dirq layout-i vra
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<RecyclerViewItem> recyclerViewItem_ArrayList;
    int taracMiavor_Kom1_2, kom1_2_miavor_avtomat_hashvac;
    private int position;
    //_____________________________________________________________________
//_____________________________________________________________________
//----------------------------DB -RecyvleView Strart-------------------

    //_____________________________________________________________________
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//mneu ininializacia

        initFabMenu();
        //  activity1.setContentView(R.layout.hashiv_list_item);//kpcnum enq razmetkain(maketin)

//------------------
        txt_player1 = findViewById(R.id.txt_player1);
        txt_player2 = findViewById(R.id.txt_player2);
        txt_player3 = findViewById(R.id.txt_player3);
        txt_player4 = findViewById(R.id.txt_player4);

        igrok1_D = findViewById(R.id.igrok1_D);
        igrok2_D = findViewById(R.id.igrok2_D);
        igrok3_D = findViewById(R.id.igrok3_D);
        igrok4_D = findViewById(R.id.igrok4_D);
//--------------------------------------------------------------------------
        //anjatum enq dileri D tarern userneri anunneri motic
        igrok2_D.setVisibility(View.INVISIBLE);
        igrok3_D.setVisibility(View.INVISIBLE);
        igrok4_D.setVisibility(View.INVISIBLE);

        resultLayout = findViewById(R.id.ResultLayout);
        // createNewLineHashiv();
//_____________________________________________________________________
//__________________________ Diler dialog _____________________________
        dialog_diler = new Dialog(this);//stexcum enq dialog
        dialog_diler.requestWindowFeature(Window.FEATURE_NO_TITLE);//anjatum enq vernagirn
        dialog_diler.setContentView(R.layout.xaxacoxneri_anunner);//kpcnum enq razmetkain(maketin)


        dialog_newGame = new Dialog(this);//stexcum enq dialog
        dialog_newGame.requestWindowFeature(Window.FEATURE_NO_TITLE);//anjatum enq vernagirn
        dialog_newGame.setContentView(R.layout.new_game_dialog);//kpcnum enq razmetkain(maketin)

        //dialog-i foni hetevn lini tapancik
        // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//asum enq dialogi mej inch ka et chaperov el lini dilaogn
//         dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
//                 WindowManager.LayoutParams.MATCH_PARENT);
//        dialog.setCancelable(false);//nazad knopkan anjatum enq

//ays toxn anhrajesht e vorpeszi verevi aj ankyuni pakman x-n normal dur ekac ereva
        dialog_diler.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


//-----------------
        imgeViw_close_diler_dialog = dialog_diler.findViewById(R.id.ImgeViw_closeX);
        imgBtn_Ok = dialog_diler.findViewById(R.id.imgBtn_Ok);
        edtTxt_igrok1 = dialog_diler.findViewById(R.id.edtTxt_igrok1);
        edtTxt_igrok2 = dialog_diler.findViewById(R.id.edtTxt_igrok2);
        edtTxt_igrok3 = dialog_diler.findViewById(R.id.edtTxt_igrok3);
        edtTxt_igrok4 = dialog_diler.findViewById(R.id.edtTxt_igrok4);
//---------------
//ays toxn anhrajesht e vorpeszi verevi aj ankyuni pakman x-n normal dur ekac ereva
        dialog_newGame.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        imgeViw_close_diler_newGame = dialog_newGame.findViewById(R.id.ImgeViw_closeX);
        imgBtn_Ok_newGame = dialog_newGame.findViewById(R.id.imgBtn_Ok);
//_____________________________________________________________________
        newGame();
        //hashivneri zangvavnern enq inicializacnum
        hashiv_Zangvac_kom1 = new ArrayList<>();
        hashiv_Zangvac_kom2 = new ArrayList<>();

//------------------------------ RecyvleView --------------------------
//        mer stexcac klas-i ekzemplyar enq stexcum
        recyclerViewItem_ArrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView_hashiv);
//arajin toxn enq stanum hashivneri
        recyclerViewItem_ArrayList.add(new RecyclerViewItem(0, null));
        recyclerViewItem_ArrayList.add(new RecyclerViewItem(0, null));
        recyclerViewItem_ArrayList.add(new RecyclerViewItem(0, "0"));

        // ays toxn petq e vor RecyclerView-n amboxj arraylistn chatni hishoxutyun ayl mi
// masn, vor heraxosn chkaxi, tvyal depqum asum enq vor fiqsac size- uni
        recyclerView.setHasFixedSize(true);
//adapteri inicializaci enq anum, vorin talis enq mer koxmic sarqac arrayList` recyclerViewItem-n
        adapter = new RecyclerViewAdapter(recyclerViewItem_ArrayList,
                MainActivity.this);
//aysex layouti pahern e, karevor che
        layoutManager = new LinearLayoutManager(this);
//inicializaciaic heto LayoutManager-n u adapter-n texadrum enq mer recyclerView hamar
        recyclerView.setAdapter(adapter);
//nuyn elementic 3 hat-ov mi toxi vra, GridLayoutManager-axyusaki tesqov
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
//        recyclerView.setLayoutManager(layoutManager);
//_____________________________________________________________________
//_____________________________________________________________________
//----------------------------DB -RecyvleView Strart-------------------
//        recyclerView_hashiv = findViewById(R.id.recyclerView_hashiv);

//----------------------------------------DB-------------------------------------------------
//----------------------------------@ndamen@ ays toxov db linum e----------------------------
//-----------------------------------------------------------------------------------------
//inicializacia enq anum mer  koxmic sarqac clasi ekzempliar
//talis enq mer koxmic sarqac clasn CarsAppDatabase, ev talis enq voch db-in anun`
// "CarsDB", voch te mek table-i
//        hashivsAppDatabase = Room.databaseBuilder(getApplicationContext(),
//                HashivAppDatabase.class, "CarsDB")
//                .build();
////-----------------------------------------------------------------------------------------
////-----------------------------------------------------------------------------------------
////-----------------------------------------------------------------------------------------
////-----------------------------------------------------------------------------------------
////stexcum enq nor objekt` mer nerqim ev private clasi, arajin dimumn e mer kodum background
//// potokin (AsyncTask), vortex stanum enq bolor tvyalnern db-ic ev cuyc talis ekranin
//        new GetAllHashivnerAsyncTask().execute();
////-----------------------------------------------------------------------------
//        hashivnerAdapter = new HashivAdapter(this, hashivArrayList, MainActivity.this);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView_hashiv.setLayoutManager(mLayoutManager);
//        recyclerView_hashiv.setItemAnimator(new DefaultItemAnimator());
//        recyclerView_hashiv.setAdapter(hashivnerAdapter);
////----------------------------DB -RecyvleView End----------------------
////_____________________________________________________________________
////-----------------------------------------------------
////--------------------------------------------------------------------------
//    }
//    //-------------------------------------------------------------------------------------------
////-----------------------------------------------------------------------------------------
////-----------------------ays metodum e  texi unenum AlertDialog-i mijocov add, edit ev
//// delete-i realizacian
//    public void addAndEditHashiv(final boolean isUpdate, final BloteNote bloteNote, final int position){
//        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
////layout_add_car.xml-i 1-textview-n ev 2 EditText-ern darnalu en dialogBox-i razmetken
//        View view = layoutInflaterAndroid.inflate(R.layout.layout_add_car, null);
//
//        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(MainActivity.this);
//        alertDialogBuilderUserInput.setView(view);
//
//
//       // createCar(nameEditText.getText().toString(), priceEditText.getText().toString());
//
//
//        TextView newCarTitle = view.findViewById(R.id.newCarTitle);
//        final EditText nameEditText = view.findViewById(R.id.nameEditText);
//        final EditText priceEditText = view.findViewById(R.id.priceEditText);
////vernagri @ntrutyun te vorn e linleu vernagirn
//        newCarTitle.setText(!isUpdate ? "Add Car" : "Edit Car");
////-----------------------------------------------------------------
//        if (isUpdate && bloteNote != null) {
//            nameEditText.setText(bloteNote.getKom2());
//            priceEditText.setText(bloteNote.getKom2());
//        }
//
//        alertDialogBuilderUserInput
//                .setCancelable(false)
//                .setPositiveButton(isUpdate ? "Update" : "Save", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialogBox, int id) {
//
//                    }
//                })//negativ button-i sexman realizacian
//                .setNegativeButton(isUpdate ? "Delete" : "Cancel",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialogBox, int id) {
//                                //kam jnjel
//                                if (isUpdate) {
//
//                                    deleteHashiv(bloteNote, position);
//                                } else {
//                                    //dialogBox-n cancel anel
//                                    dialogBox.cancel();
//
//                                }
//
//                            }
//                        });
//
////kanchum ev cuyc enq talis alertDialog-n
//        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
//        alertDialog.show();
////et alertDialog-i positiv knokpan e sexmvel
//        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
//            @Override//positiv knopkai sexman realizacian
//            public void onClick(View v) {
////ete datark e nameEditText-n
//                if (TextUtils.isEmpty(nameEditText.getText().toString())) {
//                    Toast.makeText(MainActivity.this, "Enter bloteNote name!", Toast.LENGTH_SHORT).show();
//                    return;
////ete datark e priceEditText-n
//                } else if (TextUtils.isEmpty(priceEditText.getText().toString())) {
//                    Toast.makeText(MainActivity.this, "Enter bloteNote price!", Toast.LENGTH_SHORT).show();
//                    return;
//                } else { //h.d. pakel dialogn
//                    alertDialog.dismiss();
//                }
//                //---
////voroshum e updateCar() in te createCar() kanchi
//                if (isUpdate && bloteNote != null) {
//
//                    updateHashiv(nameEditText.getText().toString(), priceEditText.getText().toString(), position);
//                } else {
//                    createHashiv(nameEditText.getText().toString(), priceEditText.getText().toString(), position);
////                    createHashiv(nameEditText.getText().toString(), priceEditText.getText().toString());
//                }
//            }
//        });
//    }
//    //------------------------------------------------------------------------------------------
//    private void deleteHashiv(BloteNote bloteNote, int position){
//
//        hashivArrayList.remove(position);
//
//        new DeleteHashivAsyncTask().execute(bloteNote);
//
//    }
//
//    private void updateHashiv(String kom1, String kom2, int position){
//
//        BloteNote bloteNote = hashivArrayList.get(position);
//
//        bloteNote.setKom1(kom1);
//        bloteNote.setKom2(kom2);
//
//        new UpdateHashivAsyncTask().execute(bloteNote);
//
//        hashivArrayList.set(position, bloteNote);
//
//
//    }
//    //-------------------------------stecxel nor tox db-i table-um---------------------------
////    private void createHashiv(String kom1, String kom2){
//    private void createHashiv(String kom1, String kom2, int position){
//
////kanchum enq (zugaher patokum ashxatox) CreateCarAsyncTask clasin, vorin talis enq
//// nor stexcac BlotNote clasi ekzempliar, id chenq poxancum (ayn avtomat e), miayn name ev price
//        new CreateHashivAsyncTask().execute(new BloteNote(0, kom1, kom2, "0"));
//
//    }
//    //---------------------------------------------------------------------------------
////---------------------------------------------------------------------------------
////---------------------- background potoki realizacia -----------------------------
////  stexcum enq mer nerqin clas, vorn jarangum e AsyncTask-ic, vorn voch mi parametr chi
////  veradarcnum (<Void, Void, Void> <Params, Progress, Result>) ete petq lini xaxerum, ays
////    3 -ov karox enq Userin cuyc tal progressbari mijocov progrress-n talov, kam verjum result-i
////    mijocov ardyunq cuyc talov.
////    ayn kanchvum e onCreate()-metodum
//    private class GetAllHashivnerAsyncTask extends AsyncTask<Void, Void, Void> {
//
//        // zugaher potoki (background potokum)  anhrajesht amboxch gorcoxutyunn texi e unenum ays metodum
//        @Override
//        protected Void doInBackground(Void... voids) {
////zugaher potokum amboxch infon kardum enq db-ic ev pahum arrayList-i mej
//            hashivArrayList.addAll(hashivsAppDatabase.getHashivDAO().getAllHashivner());
//            return null;
//        }
//
//        //sa AsyncTask-i verjin result metodn e, vorn kanchvum e doInBackground-ic heto,
//// men use ayn nra hamar, qani vor mez anhrajesht e db-i het avarteluc heto miayn infon
//// artacel  recicleView view-i vra
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
////asum enq adapterin vor texi e unecel tvyalneri popoxutyun vorpeszi
//// recicleView view-n obnovlyatca lini
//            hashivnerAdapter.notifyDataSetChanged();
//        }
//    }
//
//    //-----------------------------------------------------------------------------
////---------------------- background potoki realizacia nor tvyal db-um grelu jamanak,
////    vortex potokin tlis enq mutqayin tvyal car tipi clasi ekzempliar,
////    ays clasn kanchvum e CreateCar() metodum
//    private class CreateHashivAsyncTask extends AsyncTask<BloteNote, Void, Void> {
//
//
//        @Override
//        protected Void doInBackground(BloteNote... blot_hashiv) {
////            HashivAdapter
//           // final BloteNote bloteNote = blot_hashiv.get(position);
//
//            long id = hashivsAppDatabase.getHashivDAO().addHashiv(
//                    blot_hashiv[0]  );
//
//            BloteNote blotHashiv = hashivsAppDatabase.getHashivDAO().getHashiv(id);
//
//            if (blotHashiv != null) {
//
//                hashivArrayList.add(0, blotHashiv);
//
//
//            }
//            return null;
//        }
////sa AsyncTask-i verjin result metodn e, vorn kanchvum e doInBackground-ic heto,
//// men use ayn nra hamar, qani vor mez anhrajesht e db-i het avarteluc heto miayn infon
//// artacel  recicleView view-i vra
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
////asum enq adapterin vor texi e unecel tvyalneri popoxutyun vorpeszi
//// recicleView view-n obnovlyatca lini
//            hashivnerAdapter.notifyDataSetChanged();
//        }
//    }
//    //nmanakerp tes verevi clasneri komentn
////---------------------------------------------------------------------------------------
//    private class UpdateHashivAsyncTask extends AsyncTask<BloteNote, Void, Void> {
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//
//            hashivnerAdapter.notifyDataSetChanged();
//        }
//        //sa AsyncTask-i verjin result metodn e, vorn kanchvum e doInBackground-ic heto,
//// men use ayn nra hamar, qani vor mez anhrajesht e db-i het avarteluc heto miayn infon
//// artacel  recicleView view-i vra
//        @Override
//        protected Void doInBackground(BloteNote... blot_hashiv) {
////asum enq adapterin vor texi e unecel tvyalneri popoxutyun vorpeszi
//// recicleView view-n obnovlyatca lini
//            hashivsAppDatabase.getHashivDAO().updateHashiv(blot_hashiv[0]);
//
//            return null;
//        }
//    }
//    //nmanakerp tes verevi clasneri komentn
////----------------------------------------------------------------------------------
//    private class DeleteHashivAsyncTask extends AsyncTask<BloteNote, Void, Void> {
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//
//            hashivnerAdapter.notifyDataSetChanged();
//        }
////sa AsyncTask-i verjin result metodn e, vorn kanchvum e doInBackground-ic heto,
//// men use ayn nra hamar, qani vor mez anhrajesht e db-i het avarteluc heto miayn infon
//// artacel  recicleView view-i vra
//        @Override
//        protected Void doInBackground(BloteNote... blot_hashiv) {
////asum enq adapterin vor texi e unecel tvyalneri popoxutyun vorpeszi
//// recicleView view-n obnovlyatca lini
//            hashivsAppDatabase.getHashivDAO().deleteHashiv(blot_hashiv[0]);
//
//            return null;
//        }
//-------------------------------------------------------------------------
//-------------------------------------------------------------------------
//---------------------    listView-i realizacnum enq onClick-n  -------------------------

//        recyclerView.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Car car = carArrayList.get(position);
//
//                car.setName(name);
//                car.setPrice(price);
//
//
//                carArrayList.set(position, car);
//            }

//            @Override
//     public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//       //------------------------------------- Toast ----------------------------------------------
//       parent.setVisibility(view.GONE);//listview-i visibility- false enq anum
////                // listView-i yuraqanchyur dashtn mek view-e
//                view.setVisibility(view.GONE);//click-i t darcnum enq antesaneli view-eric mekn
//
//                                            }
//                                        });
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
//            {
////            parent.setVisibility(view.GONE);//listview-i visibility- false enq anum
//                // listView-i yuraqanchyur dashtn mek view-e
////                view.setVisibility(view.GONE);//click-i t darcnum enq antesaneli view-eric mekn
////------------------------------------------------------------------------------------------
////------------------------------------- Toast ----------------------------------------------
//                Toast.makeText(MainActivity.this, "duq sexmel eq:"+ position+" toxin",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//
    }

    //-------------------------------------------------------------------------
//----------------------------DB -RecyvleView End----------------------
//-------------------------------------------------------------------------
//    private void createNewLineHashiv() {
////                resultLayout.removeAllViews();
//      //  LinearLayout layoutLine = new LinearLayout(resultLayout.getContext());
//        LinearLayout layoutLine = new LinearLayout(this);
//        layoutLine.setOrientation(LinearLayout.HORIZONTAL);
//
//       textView_Kom1_New = new TextView(this);
//        textView_Kom1_New.setTextSize(15);
//        textView_Kom1_New.setTextColor(getResources().getColor(R.color.colorBlack));
//        textView_Kom1_New.setText("Lusin");
//
//        LinearLayout.LayoutParams leftMarginParams = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        leftMarginParams.leftMargin = 50;
//        leftMarginParams.gravity = Gravity.RIGHT;
//
//
////        android:layout_width="0dp"
////        android:layout_height="45dp"
////        android:layout_weight="33"
////        android:gravity="center"
////        android:hint="0"
//
//        textView_Kom2_New = new TextView(this);
//        textView_Kom2_New.setTextSize(15);
//        textView_Kom2_New.setTextColor(getResources().getColor(R.color.colorBlack));
//        textView_Kom2_New.setText("arev");
//
//        LinearLayout.LayoutParams buttonParams =
//                new LinearLayout.LayoutParams(
//                        LinearLayout.LayoutParams.WRAP_CONTENT,
//                        LinearLayout.LayoutParams.WRAP_CONTENT);
//       // buttonParams.gravity = Gravity.CENTER_HORIZONTAL;
//
//       layoutLine.addView(textView_Kom1_New, buttonParams);
//        layoutLine.addView(textView_Kom2_New, leftMarginParams);
//
//
//        LinearLayout.LayoutParams buttonParams1 =
//                new LinearLayout.LayoutParams(
//                        LinearLayout.LayoutParams.MATCH_PARENT,
//                        LinearLayout.LayoutParams.MATCH_PARENT);
//
//
//        resultLayout.addView(layoutLine, buttonParams1 );
//        //setContentView(layoutLine);
//    }
//--------------------------------------------------------------------
//ays metodn avtomat texi e unenum hetevyal metodnern kanchvelis 1.editHashiv,
// 2.open_extra_buttons_Team1, 3.open_extra_buttons_Team2, qani vor ays 3-um el ka
//ExtraButtonDialog-interfeysi metodi pereopreedlyactia
//@nd vorum stanum e mutqayin tvyalner interfeysic, (te inch hashiv petq e gri)
    @Override
    public void onButtonClicked(int taracMiavor_Kom1_2, int kom1_2_miavor_avtomat_hashvac) {
//---------------------------- popoxakanner  ------------------------------
        int temp;
//--------------------------------
        this.taracMiavor_Kom1_2 = taracMiavor_Kom1_2;
        this.kom1_2_miavor_avtomat_hashvac = kom1_2_miavor_avtomat_hashvac;

        if (kom1_kom2 == 1)//araji syun tarberak
        {
//nor tox tarberak
            if (mejtex_Nor_Tox == true) {
                //zangvaci mej pahum enq stacvac hashcivnern syunakneri kom1-i ev kom2-i
                hashiv_Zangvac_kom1.add(taracMiavor_Kom1_2);
                hashiv_Zangvac_kom2.add(kom1_2_miavor_avtomat_hashvac);


                fullHashiv_kom1 = syuneri_gumar(hashiv_Zangvac_kom1);
                fullHashiv_kom2 = syuneri_gumar(hashiv_Zangvac_kom2);

                recyclerViewItem_ArrayList.get(position).setZakaz("" + fullHashiv_kom1);
                recyclerViewItem_ArrayList.get(position + 1).setZakaz("" + fullHashiv_kom2);
//stugum enq xaxn avartvum e te voch
                if (fullHashiv_kom1 > fullHashiv_kom2) {
                    if (fullHashiv_kom1 >= 301) {
//                        Partia_Hashiv_Kom1=Partia_Hashiv_Kom1+1;
//                        recyclerViewItem_ArrayList.add(new RecyclerViewItem(0, ""+Partia_Hashiv_Kom1));
//                        recyclerViewItem_ArrayList.add(new RecyclerViewItem(0, null));
//                        recyclerViewItem_ArrayList.add(new RecyclerViewItem(0, ""+Partia_Hashiv_Kom2));

                        dialog_newGame.show();

                    }
                } else if (fullHashiv_kom2 > fullHashiv_kom1) {
                    if (fullHashiv_kom2 >= 301) {

                        dialog_newGame.show();
//                        Partia_Hashiv_Kom2=Partia_Hashiv_Kom2+1;
//                        recyclerViewItem_ArrayList.add(new RecyclerViewItem(0, ""+Partia_Hashiv_Kom1));
//                        recyclerViewItem_ArrayList.add(new RecyclerViewItem(0, null));
//                        recyclerViewItem_ArrayList.add(new RecyclerViewItem(0, ""+Partia_Hashiv_Kom2));

                    }

                }
            }
            //-----------------
            else {//exac toxn enq xmbagrum tarberak
                temp = 0;

                hashiv_Zangvac_kom1.set((position / 3), taracMiavor_Kom1_2);
                int i = 1;
                do {
                    recyclerViewItem_ArrayList.get(temp).setZakaz("" + syuneri_gumar(hashiv_Zangvac_kom1, i));
                    i++;
                    temp = temp + 3;
                }
                while (i <= hashiv_Zangvac_kom1.size());//
            }
//
            //image-n xalastoy 0 enq grum
            recyclerViewItem_ArrayList.get(position).setImageResource(0);

        } else if (kom1_kom2 == 2) {//2-syun tarbeak


            if (mejtex_Nor_Tox == true) {//nnor tox varinatne
//zangvaci mej pahum enq stacvac hashcivnern syunakneri kom1-i ev kom2-i
                hashiv_Zangvac_kom1.add(kom1_2_miavor_avtomat_hashvac);
                hashiv_Zangvac_kom2.add(taracMiavor_Kom1_2);

                fullHashiv_kom1 = syuneri_gumar(hashiv_Zangvac_kom2);
                fullHashiv_kom2 = syuneri_gumar(hashiv_Zangvac_kom1);

                recyclerViewItem_ArrayList.get(position).setZakaz("" + fullHashiv_kom1);
                recyclerViewItem_ArrayList.get(position - 1).setZakaz("" + fullHashiv_kom2);
            } else {//exac toxn enq xmbagrum tarberak
                temp = 1;
                hashiv_Zangvac_kom2.set((position / 3), taracMiavor_Kom1_2);
                int i = 1;
                do {
                    recyclerViewItem_ArrayList.get(temp).setZakaz("" + syuneri_gumar(hashiv_Zangvac_kom2, i));
                    i++;
                    temp = temp + 3;
                }
                while (i <= hashiv_Zangvac_kom2.size());//
            }
        }

//new Line nor tox bacelu pahn e
        if (position >= recyclerViewItem_ArrayList.size() - 3) {
            recyclerViewItem_ArrayList.add(new RecyclerViewItem(0, null));
            recyclerViewItem_ArrayList.add(new RecyclerViewItem(0, null));
            recyclerViewItem_ArrayList.add(new RecyclerViewItem(0, "0"));
            //diler poxelu pahn e
            diler();
        }

//scrollView-i regulirovka
//vorpeszi  amen nor tox haytnvelis focusn lini verjin toxi vra, scrollView-i mej
        ((LinearLayoutManager) recyclerView.getLayoutManager()).
                scrollToPositionWithOffset(recyclerViewItem_ArrayList.size() - 1, 0);

//tarmacnum enq adaptern, vor useri katarac popoxutyunnern erevan
        adapter.notifyDataSetChanged();
    }

    //-----------------------------------------------------------------------------
//ays metodn avtomat kanchvum e open_extra_Order_buttons-metodi kanchic heto, qani vor
//ExtraOrderBtnDialog-interfeysi pereopreedlyactia e, isk open_extra_Order_buttons-metodum
//  ExtraOrderBtnDialog-interferysn-i ekzempliar enq sarqum ev cuyc talis
//@nd vorum stanum e mutqayin tvyalner interfeysic, (te vor komandan e xosacel, mast, tiv)
//ays metodn mianum e useri order-n uzeluc heto(zakaz knopkai vra sexmeluc heto)
    @Override
    public void order_OnButtonClicked(byte kom1_kom2_kanchele, int mast, String order) {
//--------------
        this.kom1_kom2_kanchele = kom1_kom2_kanchele;
        this.mast = mast;//tvysl oyini mastn e
        this.order = order;//tvyal oyini xosacac tivn e
        int imageResurce;
//kaxvac vor mastn e @ntrel usern ayd masti nkarn enq dnum ekrani vra
        switch (mast) {
            case 0:
                imageResurce = R.drawable.x_24;
                //new CreateHashivAsyncTask().execute(new BloteNote(0, "0", "0",R.drawable.x_24, order));
                break;
            case 1:
                imageResurce = R.drawable.s_24;
                break;
            case 2:
                imageResurce = R.drawable.xar_24;
                break;
            case 3:
                imageResurce = R.drawable.q_24;
                break;
            default:
                imageResurce = R.drawable.t_24;
                break;
        }

//ete sexmel e 3 syan vra, apa ayd syan vra popoxutyun enq anum
        if (position % 3 == 2) {
            recyclerViewItem_ArrayList.get(position).setZakaz(order);
            recyclerViewItem_ArrayList.get(position).setImageResource(imageResurce);
        }
//ete derevs xoz chxosacac toxi vra sexmel e syun1 kam syun2-i vra eli bacum e xoz xosalun@
        else {
            recyclerViewItem_ArrayList.get(recyclerViewItem_ArrayList.size() - 1).setZakaz(order);
            recyclerViewItem_ArrayList.get(recyclerViewItem_ArrayList.size() - 1).setImageResource(imageResurce);
        }

//tarmacnum enq adaptern, vor useri katarac popoxutyunnern erevan
        adapter.notifyDataSetChanged();
    }

    //_____________________________________________________________________
//-------------------------------------------------------------------------
//ays metodn kanchvum e RecyclerViewAdapter -klaci mejic RecyclerView-i vra sexmelis
// @nd voorum position-ov stanum e useri koxmic nshvac toxi hamarn
//position-in darcnum enq global, aynuhetev kanchum enq hashivner nermucelu`
// ExtraButtonDialog, vori avartic heto, vorpes interfeysi pereopredlenni metodi (onButtonClicked)
// realizacia` texi unenum metod onButtonClicked-i kanchn, vorn el stanum mutqayin tvyalner
// interfeysic, te inch hashiv petq e gri
    public void editHashiv(final int position) {
        int mnacord, temp;

//sa nshanakum e usern sexmel e toxi (=positioni) vra ev uzum e hashivnern xmbagri
        // kom1_kom2=1-i kom1-n e sexmvac, kom1_kom2=2 kom2- e sexmvac

        this.position = position;
//        Log.d("qqq", ""+position/3);
//---------
//3 syunic axyusak enq stanum, vortex 3-i bajanelis mnacordum
// 1-in syunin mnum e mnacord= 0, 2-rd syunin mnacord=1, isk 3-rdin mnacord=2
        mnacord = position % 3;
//New Game rejimn e, xaxi skzbum erb voch mek der chi xosacel
        //{
        if (mnacord == 2)//bacel xoz xosalu texn
        {
            try {
                ExtraOrderBtnDialog bottomSheet = new ExtraOrderBtnDialog();
                bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
            } catch (ClassCastException e) {
                throw new ClassCastException();
            }
        }//bacel hashiv grelu dashtn (1-in syunn)
        else if (mnacord == 0) {
//isk ete derevs xoz xosalu dashtn datark e, apa eli bacel miayn xoz xosalu texn
            if (recyclerViewItem_ArrayList.get(position + 2).getZakaz().equals("0")) {
                try {
                    ExtraOrderBtnDialog bottomSheet = new ExtraOrderBtnDialog();
                    bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
                } catch (ClassCastException e) {
                    throw new ClassCastException();
                }
            } else {//tvyal toxi xozn arden xosacel en
                kom1_kom2 = 1;
                if (recyclerViewItem_ArrayList.get(position).getZakaz() == null) {
                    temp = 0;
                    mejtex_Nor_Tox = true;//ete true e nor tox e, ete false mejtexi
                } else {
                    temp = hashiv_Zangvac_kom1.get((position / 3));
                    mejtex_Nor_Tox = false;//ete true e nor tox e, ete false mejtexi
                    //xalastoy rejim, miayn xmbagrel tmeric meki hashivn
                    kom1_kom2_kanchele = 0;
                }
//mer sarqac interfeysi ekzempliar
                ExtraButtonDialog bottomSheet = new ExtraButtonDialog(
                        temp,
                        // recyclerViewItem_ArrayList.get(position).getZakaz(),
                        kom1_kom2, kom1_kom2_kanchele, mast, order);
                bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
            }
        } else if (mnacord == 1) {//bacel hashiv grelu dashtn (2-in syunn)
//isk ete derevs xoz xosalu dashtn datark e ayd toxi vra,
// apa eli bacel miayn xoz xosalu texn
            if (recyclerViewItem_ArrayList.get(position + 1).getZakaz().equals("0")) {
                try {
                    ExtraOrderBtnDialog bottomSheet = new ExtraOrderBtnDialog();
                    bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
                } catch (ClassCastException e) {
                    throw new ClassCastException();
                }
            } else {//tvyal toxi xozn arden xosacel en
                kom1_kom2 = 2;
                if (recyclerViewItem_ArrayList.get(position).getZakaz() == null) {
                    temp = 0;
                    mejtex_Nor_Tox = true;//ete true e nor tox e, ete false mejtexi
                } else {
                    temp = hashiv_Zangvac_kom2.get((position / 3));
                    mejtex_Nor_Tox = false;//ete true e nor tox e, ete false mejtexi
                    //xalastoy rejim, miayn xmbagrel tmeric meki hashivn
                    kom1_kom2_kanchele = 0;
                }
//mer sarqac interfeysi ekzempliar
                ExtraButtonDialog bottomSheet = new ExtraButtonDialog(
                        temp,
                        // recyclerViewItem_ArrayList.get(position).getZakaz(),
                        kom1_kom2, kom1_kom2_kanchele, mast, order);
                bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");

            }
        }
    }

    //}
//_________________________________________________________________________
//_________________________________________________________________________
//dileri D tarn teqelu pahn e
    private void diler() {
        int igrok_ochered = recyclerViewItem_ArrayList.size() % 4;
        //if (igrok_ochered==0 )
//Log.d("aaa", ""+igrok_ochered);
        switch (igrok_ochered) {
            case 3:
                igrok1_D.setVisibility(View.VISIBLE);
                igrok2_D.setVisibility(View.INVISIBLE);
                igrok3_D.setVisibility(View.INVISIBLE);
                igrok4_D.setVisibility(View.INVISIBLE);
                break;
            case 2:
                igrok3_D.setVisibility(View.VISIBLE);
                igrok1_D.setVisibility(View.INVISIBLE);
                igrok2_D.setVisibility(View.INVISIBLE);
                igrok4_D.setVisibility(View.INVISIBLE);
                break;
            case 1:
                igrok2_D.setVisibility(View.VISIBLE);
                igrok1_D.setVisibility(View.INVISIBLE);
                igrok3_D.setVisibility(View.INVISIBLE);
                igrok4_D.setVisibility(View.INVISIBLE);
                break;
            case 0:
                igrok4_D.setVisibility(View.VISIBLE);
                igrok1_D.setVisibility(View.INVISIBLE);
                igrok2_D.setVisibility(View.INVISIBLE);
                igrok3_D.setVisibility(View.INVISIBLE);
                break;
        }


    }

    //------------------------------- MENU  -----------------------------------
    private void initFabMenu() {
        fabMain = findViewById(R.id.fabMain);
        fabOne = findViewById(R.id.fabOne);
        fabTwo = findViewById(R.id.fabTwo);
        fabThree = findViewById(R.id.fabThree);
        fabFour = findViewById(R.id.fabFour);
        fabTextNg = findViewById(R.id.fabTextNg);
        fabTextEng = findViewById(R.id.fabTextEng);
        fabTextTheme = findViewById(R.id.fabTextTheme);
        fabTextShare = findViewById(R.id.fabTextShare);


        fabOne.setAlpha(0f);
        fabTwo.setAlpha(0f);
        fabThree.setAlpha(0f);
        fabFour.setAlpha(0f);
        fabTextNg.setAlpha(0f);
        fabTextEng.setAlpha(0f);
        fabTextTheme.setAlpha(0f);
        fabTextShare.setAlpha(0f);


        fabOne.setTranslationY(translationY);
        fabTwo.setTranslationY(translationY);
        fabThree.setTranslationY(translationY);
        fabFour.setTranslationY(translationY);
        fabTextNg.setTranslationY(translationY);
        fabTextEng.setTranslationY(translationY);
        fabTextTheme.setTranslationY(translationY);
        fabTextShare.setTranslationY(translationY);

        fabMain.setOnClickListener(this);
        fabOne.setOnClickListener(this);
        fabTwo.setOnClickListener(this);
        fabThree.setOnClickListener(this);
        fabFour.setOnClickListener(this);
        fabTextNg.setOnClickListener(this);
        fabTextEng.setOnClickListener(this);
        fabTextTheme.setOnClickListener(this);
        fabTextShare.setOnClickListener(this);
    }

    private void openMenu() {
        isMenuOpen = !isMenuOpen;

        fabMain.animate().setInterpolator(interpolator).rotation(45f).setDuration(300).start();

        fabOne.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabTwo.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabThree.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabFour.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabTextNg.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabTextEng.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabTextTheme.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabTextShare.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();


    }

    private void closeMenu() {
        isMenuOpen = !isMenuOpen;

        fabMain.animate().setInterpolator(interpolator).rotation(0f).setDuration(300).start();

        fabOne.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fabTwo.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fabThree.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fabFour.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fabTextNg.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fabTextEng.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fabTextTheme.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fabTextShare.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();


    }

    private void handleFabOne() {
        Log.i(TAG, "handleFabOne: ");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fabMain:
                //  Log.i(TAG, "onClick: fab main");
                if (isMenuOpen) {
                    closeMenu();
                } else {
                    openMenu();
                }
                break;
            case R.id.fabOne:
                // Log.i(TAG, "onClick: fab one");
                handleFabOne();
                if (isMenuOpen) {
                    closeMenu();
                } else {
                    openMenu();
                }
                break;
            case R.id.fabTwo:
                //  Log.i(TAG, "onClick: fab two");
                break;
            case R.id.fabThree:
                //Log.i(TAG, "onClick: fab three");
                break;
            case R.id.fabFour:
                //Log.i(TAG, "onClick: fab four");
                break;
            case R.id.fabTextNg:
                //Log.i(TAG, "onClick: fab four");
                break;
            case R.id.fabTextEng:
                //Log.i(TAG, "onClick: fab four");
                break;
            case R.id.fabTextTheme:
                //Log.i(TAG, "onClick: fab four");
                break;
            case R.id.fabTextShare:
                //Log.i(TAG, "onClick: fab four");
                break;
        }

    }

    //_________________________________________________________________________________
//_____________________________ DILER ---------------------------------------------
//dileri poxman pahn e
    public void diler(View view) {
//dialogi stexcman mek ayl dzev en kirarel
        dialog_diler.show();
    }

    //-------------------------------------------------------------------------------
//-------------------------------------------------------------------------------
    public void diler_close(View view) {
        dialog_diler.dismiss();
    }

    //-------------------------------------------------------------------------------
    public void diler_ok(View view) {

        if (TextUtils.isEmpty(edtTxt_igrok1.getText().toString())) {
            txt_player1.setText("A-1");
        } else
            txt_player1.setText(edtTxt_igrok1.getText());
//--**********************
        if (TextUtils.isEmpty(edtTxt_igrok2.getText().toString())) {
            txt_player2.setText("A-2");
        } else
            txt_player2.setText(edtTxt_igrok2.getText());
//--**********************
        if (TextUtils.isEmpty(edtTxt_igrok3.getText().toString())) {
            txt_player3.setText("B-1");
        } else
            txt_player3.setText(edtTxt_igrok3.getText());
//--**********************
        if (TextUtils.isEmpty(edtTxt_igrok4.getText().toString())) {
            txt_player4.setText("B-2");
        } else
            txt_player4.setText(edtTxt_igrok4.getText());
//
        dialog_diler.dismiss();
    }

    //_________________________________________________________________________
//syuneri hashivneri gumarn enq hshvum, vortex syunn mek arrayList e
    private int syuneri_gumar(ArrayList<Integer> syun) {
        int summa = 0;
        for (int i = 0; i < syun.size(); i++)
            summa += syun.get(i);
        return summa;
    }

    //---------------------------------------------------------------------------
//syuneri hashivneri gumarn enq hshvum, minchev size-chapn, vortex syunn mek arrayList e
    private String syuneri_gumar(ArrayList<Integer> syun, int size) {
        int summa = 0, i = 0;
        do {
            summa = summa + syun.get(i);
            i++;
        }
        while (i < size);
        return "" + summa;
    }

    //new Game -dialogi pakman knopka
    public void newGame_close(View view) {
        dialog_newGame.dismiss();
    }

    //new Game -dialogi ok knopka
    public void newGame_ok(View view) {
        if (fullHashiv_kom1 > fullHashiv_kom2)
            Partia_Hashiv_Kom1 = Partia_Hashiv_Kom1 + 1;
        else if (fullHashiv_kom2 > fullHashiv_kom1)
            Partia_Hashiv_Kom2 = Partia_Hashiv_Kom2 + 1;
        recyclerViewItem_ArrayList.get(recyclerViewItem_ArrayList.size() - 3).
                setZakaz("" + Partia_Hashiv_Kom1);
        recyclerViewItem_ArrayList.get(recyclerViewItem_ArrayList.size() - 1).
                setZakaz("" + Partia_Hashiv_Kom2);

//        recyclerViewItem_ArrayList.add(new RecyclerViewItem(0, null));
//        recyclerViewItem_ArrayList.add(new RecyclerViewItem(0, null));
//        recyclerViewItem_ArrayList.add(new RecyclerViewItem(0, "0"));

        //newGame();

        ((LinearLayoutManager) recyclerView.getLayoutManager()).
                scrollToPositionWithOffset(recyclerViewItem_ArrayList.size() - 1, 0);

        adapter.notifyDataSetChanged();


        //hashivneri zangvavnern enq inicializacnum
        hashiv_Zangvac_kom1 = new ArrayList<>();
        hashiv_Zangvac_kom2 = new ArrayList<>();

//------------------------------ RecyvleView --------------------------
//        mer stexcac klas-i ekzemplyar enq stexcum
        recyclerViewItem_ArrayList = new ArrayList<>();

        recyclerViewItem_ArrayList.add(new RecyclerViewItem(0, null));
        recyclerViewItem_ArrayList.add(new RecyclerViewItem(0, null));
        recyclerViewItem_ArrayList.add(new RecyclerViewItem(0, "0"));


//tarmacnum enq adaptern, vor useri katarac popoxutyunnern erevan
        adapter.notifyDataSetChanged();
        dialog_newGame.dismiss();
    }

    //nor xax enq bacum, zroyacnelov arrayListern
    private void newGame() {
        //hashivneri zangvavnern enq inicializacnum
        hashiv_Zangvac_kom1 = new ArrayList<>();
        hashiv_Zangvac_kom2 = new ArrayList<>();

//------------------------------ RecyvleView --------------------------
//        mer stexcac klas-i ekzemplyar enq stexcum
        recyclerViewItem_ArrayList = new ArrayList<>();
//arajin toxn enq stanum hashivneri
        recyclerViewItem_ArrayList.add(new RecyclerViewItem(0, null));
        recyclerViewItem_ArrayList.add(new RecyclerViewItem(0, null));
        recyclerViewItem_ArrayList.add(new RecyclerViewItem(0, "0"));
//        ((LinearLayoutManager) recyclerView.getLayoutManager()).
//                scrollToPositionWithOffset(recyclerViewItem_ArrayList.size() - 1, 0);

//tarmacnum enq adaptern, vor useri katarac popoxutyunnern erevan
        // adapter.notifyDataSetChanged();
    }
//_________________________________________________________________________________
}
