package com.narsli.blotsmartnote;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.narsli.blotsmartnote.Model.Hashiv_Razobrani;

//sarqum enq dialog vorn kpnum e mer razmetka failin
//nerqevi knopkan` BottomSheetDialogFragment-interfeysov e realizacvum
public class ExtraButtonDialog extends BottomSheetDialogFragment {

    private Hashiv_Razobrani hashiv_Razobrani;
    private Button
            btn_1, btn_2, btn_3, btn_4, btn_5, btn_6,
            btn_7, btn_8, btn_9, btn_0, btn_K, btn_tk;
    CheckBox chckBx_BLot,
            chckBx_tuz_4tuxtDialog, chckBx_valet_4tuxtDialog,
            chckBx_9_4tuxtDialog;
    ImageButton imgbtn_terz_minus, imgbtn_terz_plus,
            btn_4paper_minus, btn_chorstuxt_plus,
            btn_sto_minus, btn_100_plus,
            btn_50_minus, btn_50_plus,
            imgBtn_Back, imgBtn_Ok, imgBtn_Ok_4tuxt_dialog;
    ImageView imgeViw_close_4tuxt_dialog;
    Boolean kaput_banali = false, tak_banali = false;
    private Dialog dialog;
    TextView txtView_Extra, txtView_terz_Qanak,
            txtViw_Extra, txtView_combination,
            txtViw_ArdyunqMiavor, editTxt_taracMiavor,
            txtView_blot,
            txtView_chorstuxt_Qanak_4tuxtDialog,
            txtView_100_Qanak_4tuxtDialog,
            txtView_50_Qanak_4tuxtDialog;
    String order = "0";
    int combination = 0, tarac_miavor = 0, tarac_itog;
    //         byte kom1_kom2;
    private BottomSheetListener mListener;//mer sarqac interfeysi ekzempliar
    private int temp_tiv;
    private byte kom1_kom2, kom1_kom2_kanchele;
    private int mast, boy_xoz = 0;
    String xosacac_tiv;
    int hakarakord_miavor_avtomat_hashvac = 0, kom1_2_Ekrani_Miavor;

    //--------------------------------------------------------------------------
//konstruktor
    public ExtraButtonDialog(
            int kom1_2_Ekrani_Miavor,
            byte kom1_kom2, byte kom1_kom2_kanchele, int mast, String xosacac_tiv) {
        this.kom1_2_Ekrani_Miavor = kom1_2_Ekrani_Miavor;
        this.kom1_kom2 = kom1_kom2;
        this.kom1_kom2_kanchele = kom1_kom2_kanchele;
        this.mast = mast;//ete 4-e boy e, h.d. sovarakan xoz
        this.xosacac_tiv = xosacac_tiv;
//------------------------------------------------------------------
//-------------------------------------------------------------------
//        stanum enq xosacac tivn, quansh e, te kapuyt e ev ayln
//_________________ xosacac tvi razborka ev verlucum___________________
        hashiv_Razobrani = new Hashiv_Razobrani();

        // hashiv_Razobrani.setMast(mast);
//variant quansh
        if (xosacac_tiv.indexOf("x4") != -1) {
            hashiv_Razobrani.setQuansh(4);
        } else if (xosacac_tiv.indexOf("x2") != -1)
            hashiv_Razobrani.setQuansh(2);
        else
            hashiv_Razobrani.setQuansh(1);//quansh chka
//-----------------------------------------------
//ete kapuyt ka variant
        if (xosacac_tiv.indexOf('K') != -1) {//kapuyt ka
            hashiv_Razobrani.setKapuyt(true);
            if (xosacac_tiv.indexOf('K') == 0) {
                hashiv_Razobrani.setXosacac_tiv(25);


            } else {
                hashiv_Razobrani.setXosacac_tiv(Integer.parseInt(xosacac_tiv.substring(0, 2)));
            }
        } else//ete kapuyt chka
        {
            hashiv_Razobrani.setKapuyt(false);
            //quansh ka te che
            if (xosacac_tiv.indexOf("x4") != -1) {
                hashiv_Razobrani.setXosacac_tiv(
                        Integer.parseInt(xosacac_tiv.substring(0, xosacac_tiv.length() - 2)));

            } else if (xosacac_tiv.indexOf("x2") != -1) {
                hashiv_Razobrani.setXosacac_tiv(
                        Integer.parseInt(xosacac_tiv.substring(0, xosacac_tiv.length() - 2)));
            } else {
                hashiv_Razobrani.setXosacac_tiv(Integer.parseInt(xosacac_tiv));
            }

        }
//-------------------------------------------------------------------
//--------------------------------------------
//stanum enq extra miavor ete usern xozn boy e xosacel
        if (mast == 4)
            //ete xozn boy e extra miavor e avelanum
            boy_xoz = hashiv_Razobrani.getXosacac_tiv();
        else//ete xozn boy che
            boy_xoz = 0;
//--------------------------------------------
//_______________________________________________
    }

    //--------------------------------------------------------------------------
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.extra_buttons, container, false);
//----------------------
        //inicializacia
        btn_1 = v.findViewById(R.id.Btn_1);
        btn_2 = v.findViewById(R.id.Btn_2);
        btn_3 = v.findViewById(R.id.Btn_3);
        btn_4 = v.findViewById(R.id.Btn_4);
        btn_5 = v.findViewById(R.id.Btn_5);
        btn_6 = v.findViewById(R.id.Btn_6);
        btn_7 = v.findViewById(R.id.Btn_7);
        btn_8 = v.findViewById(R.id.Btn_8);
        btn_9 = v.findViewById(R.id.Btn_9);
        btn_0 = v.findViewById(R.id.Btn_0);
        btn_K = v.findViewById(R.id.Btn_KP);
        btn_tk = v.findViewById(R.id.Btn_tk);

        imgBtn_Back = v.findViewById(R.id.ImgBtn_Back);
        txtView_Extra = v.findViewById(R.id.TxtViw_Extra);
        imgBtn_Ok = v.findViewById(R.id.ImgBtn_Ok);

        txtView_terz_Qanak = v.findViewById(R.id.txtView_terz_Qanak);
        txtViw_Extra = v.findViewById(R.id.TxtViw_Extra);
        txtView_combination = v.findViewById(R.id.txtView_combination);
        txtViw_ArdyunqMiavor = v.findViewById(R.id.txtViw_ArdyunqMiavor);

        txtView_blot = v.findViewById(R.id.txtView_blot);

        chckBx_BLot = v.findViewById(R.id.chckBx_BLot);

        imgbtn_terz_minus = v.findViewById(R.id.Imgbtn_terz_minus);
        imgbtn_terz_plus = v.findViewById(R.id.Imgbtn_terz_plus);
        editTxt_taracMiavor = v.findViewById(R.id.editTxt_taracMiavor);
//-------------------------------------------------------------------------------
//stanum enq ekrani vrainshvac tmi  @ntacik hashivn, isk ete chka apa 0 (ete=null-i)
        if (kom1_2_Ekrani_Miavor == 0)
            order = "0";
        else
            order = "" + kom1_2_Ekrani_Miavor;

        tarac_miavor = kom1_2_Ekrani_Miavor;
        tarac_itog = tarac_miavor + combination;
        editTxt_taracMiavor.setText(order);
        txtViw_ArdyunqMiavor.setText(order);
        //Log.d("ttt", "tarac_itog= "+tarac_itog);


//-------------------------------------------------------------------------------
        imgbtn_terz_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    temp_tiv = Integer.parseInt(txtView_terz_Qanak.getText().toString());

                } catch (ClassCastException e) {
                    throw new ClassCastException();
                }

                if (!txtView_terz_Qanak.getText().equals("4")) {
                    combination = combination + 2;
                    txtView_terz_Qanak.setText("" + (temp_tiv + 1));
                    txtView_combination.setText("" + combination);
                    txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                    // mListener.onButtonClicked("" + (tarac_miavor + combination), hakarakord_miavor_avtomat_hashvac);
                }
            }

        });
//-------------------------------------------------------------------------------
        imgbtn_terz_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    temp_tiv = Integer.parseInt(txtView_terz_Qanak.getText().toString());

                } catch (ClassCastException e) {
                    throw new ClassCastException();
                }

                if (!txtView_terz_Qanak.getText().equals("0")) {
                    txtView_terz_Qanak.setText("" + (temp_tiv - 1));
                    combination = combination - 2;
                    txtView_combination.setText("" + combination);
                    txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                    // mListener.onButtonClicked("" + (tarac_miavor + combination), hakarakord_miavor_avtomat_hashvac);
                }

            }

        });
//-------------------------------------------------------------------------------
        chckBx_BLot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (chckBx_BLot.isChecked()) {
                    combination = combination + 2;
                    txtView_blot.setTextColor(getResources().getColor(R.color.colorRed));
                } else {
                    combination = combination - 2;
                    txtView_blot.setTextColor(getResources().getColor(R.color.colorBlack));
                }
                txtView_combination.setText("" + combination);
                txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                //mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
            }
        });
//-------------------------------------------------------------------------------
        btn_tk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tarac_miavor = 0;
                if (tak_banali) {
                    //---------------------------------------------
                    btn_tk.setBackgroundResource(R.color.colorBlack);
                    btn_K.setBackgroundResource(R.color.colorBlack);
                    order = "0";

                    kaput_banali = false;
                    txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                    //  mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
                } else {
//---------------------------------------------
                    order = "Tk";
                    btn_tk.setBackgroundResource(R.color.colorGray);
                    btn_K.setBackgroundResource(R.color.colorBlack);
                    kaput_banali = false;
                    txtViw_ArdyunqMiavor.setText("" + tarac_miavor);
                    //  mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
                }
                tak_banali = !tak_banali;


                editTxt_taracMiavor.setText(order);
//                    txtViw_ArdyunqMiavor.setText(""+(tarac_miavor+combination));
//                    mListener.onButtonClicked(""+(tarac_miavor+combination));
            }

        });
//------------------------------------------------------------------
//-------------------------------------------------------------------------------
        btn_K.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (kaput_banali) {
                    //---------------------------------------------
                    btn_K.setBackgroundResource(R.color.colorBlack);
                    btn_tk.setBackgroundResource(R.color.colorBlack);
                    order = "0";
                    tarac_miavor = 0;
                    tak_banali = false;
                } else {
//---------------------------------------------
                    order = "K";
                    if (hashiv_Razobrani.getXosacac_tiv() == 25)
                        tarac_miavor = 50 + boy_xoz;//kapuyt asel kapuyt arel e
                    else//miamit e kapuyt arel
                        tarac_miavor = 25 + hashiv_Razobrani.getXosacac_tiv() + boy_xoz;
                    btn_K.setBackgroundResource(R.color.colorBlue);
                    btn_tk.setBackgroundResource(R.color.colorBlack);
                    tak_banali = false;
                }
                kaput_banali = !kaput_banali;

                editTxt_taracMiavor.setText(order);
                txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                // mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);

            }

        });
//------------------------------------------------------------------

//_______________________________________________________________________________
        imgBtn_Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //--------------
//taraca miavorner + kombunacia
                tarac_itog = (tarac_miavor + combination);
//ete xalastoy menak xmbagrman rejim e
                if (kom1_kom2_kanchele == 0) //ete voch mek chi xosacel
                {
                    hakarakord_miavor_avtomat_hashvac = 0;
                } else //erb nor tox grman rejim e
                    hakarakord_Tmi_Miavor_hashvum();
//____________________________________________________
                mListener.onButtonClicked(tarac_itog,
                        hakarakord_miavor_avtomat_hashvac);
                //miangamic pakel nerqevi toxi
                dismiss();

            }

        });
//---------------------------------------------------------------------
        imgBtn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//mer sarqac interfeysi realizacia` onButtonClicked- metodi realizacia

                if (order != "Tk" & order != "K" & order.length() == 1 & order != "0") {
                    order = order.substring(0, order.length() - 1);
                    order = "0";
                    tarac_miavor = 0;
                    // mListener.onButtonClicked(order, hakarakord_miavor_avtomat_hashvac);
                    editTxt_taracMiavor.setText(order);
                    txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                }
                if (order != "Tk" & order.length() > 1) {
                    order = order.substring(0, order.length() - 1);
                    tarac_miavor = Integer.parseInt(order);
                    // mListener.onButtonClicked(order, hakarakord_miavor_avtomat_hashvac);
                    editTxt_taracMiavor.setText(order);
                    txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                }
            }
        });
//---------------------------------------------------------------------
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//mer sarqac interfeysi realizacia` onButtonClicked- metodi realizacia
                if (order.equals("0")) {
                    order = "1";

                    editTxt_taracMiavor.setText(order);
                    tarac_miavor = Integer.parseInt(order);
                    txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                    // mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
                } else if (order != "Tk" & order != "K" & order.length() < 3) {
                    order = order + "1";

                    editTxt_taracMiavor.setText(order);
                    tarac_miavor = Integer.parseInt(order);
                    txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                    // mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
                }
            }
        });
//------------------------------------------------------------------
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//mer sarqac interfeysi realizacia` onButtonClicked- metodi realizacia
                if (order.equals("0")) {
                    order = "2";
                    editTxt_taracMiavor.setText(order);
                    tarac_miavor = Integer.parseInt(order);
                    txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                    //  mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
                } else if (order != "Tk" & order != "K" & order.length() < 3) {
                    order = order + "2";

                    editTxt_taracMiavor.setText(order);
                    tarac_miavor = Integer.parseInt(order);
                    txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                    //  mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
                }
            }
        });
        //---------------------------------------------------------------------
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//mer sarqac interfeysi realizacia` onButtonClicked- metodi realizacia
                if (order.equals("0")) {
                    order = "3";
                    editTxt_taracMiavor.setText(order);
                    tarac_miavor = Integer.parseInt(order);
                    txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                    //  mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
                } else if (order != "Tk" & order != "K" & order.length() < 3) {
                    order = order + "3";

                    editTxt_taracMiavor.setText(order);
                    tarac_miavor = Integer.parseInt(order);
                    txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                    //   mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
                }
            }
        });//---------------------------------------------------------------------
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//mer sarqac interfeysi realizacia` onButtonClicked- metodi realizacia
                if (order.equals("0")) {
                    order = "4";
                    editTxt_taracMiavor.setText(order);
                    tarac_miavor = Integer.parseInt(order);
                    txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                    //    mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
                } else if (order != "Tk" & order != "K" & order.length() < 3) {
                    order = order + "4";

                    editTxt_taracMiavor.setText(order);
                    tarac_miavor = Integer.parseInt(order);
                    txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                    //   mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
                }
            }
        });//---------------------------------------------------------------------
        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//mer sarqac interfeysi realizacia` onButtonClicked- metodi realizacia
                if (order.equals("0")) {
                    order = "5";
                    editTxt_taracMiavor.setText(order);
                    tarac_miavor = Integer.parseInt(order);
                    txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                    //   mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
                } else if (order != "Tk" & order != "K" & order.length() < 3) {
                    order = order + "5";

                    editTxt_taracMiavor.setText(order);
                    tarac_miavor = Integer.parseInt(order);
                    txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                    //  mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
                }
            }
        });//---------------------------------------------------------------------
        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//mer sarqac interfeysi realizacia` onButtonClicked- metodi realizacia
                if (order.equals("0")) {
                    order = "6";
                    editTxt_taracMiavor.setText(order);
                    tarac_miavor = Integer.parseInt(order);
                    txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                    //  mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
                } else if (order != "Tk" & order != "K" & order.length() < 3) {
                    order = order + "6";

                    editTxt_taracMiavor.setText(order);
                    tarac_miavor = Integer.parseInt(order);
                    txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                    //   mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
                }
            }
        });//---------------------------------------------------------------------
        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//mer sarqac interfeysi realizacia` onButtonClicked- metodi realizacia
                if (order.equals("0")) {
                    order = "7";
                    editTxt_taracMiavor.setText(order);
                    tarac_miavor = Integer.parseInt(order);
                    txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                    //  mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
                } else if (order != "Tk" & order != "K" & order.length() < 3) {
                    order = order + "7";

                    editTxt_taracMiavor.setText(order);
                    tarac_miavor = Integer.parseInt(order);
                    txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                    //  mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
                }
            }
        });//---------------------------------------------------------------------
        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//mer sarqac interfeysi realizacia` onButtonClicked- metodi realizacia
                if (order.equals("0")) {
                    order = "8";
                    editTxt_taracMiavor.setText(order);
                    tarac_miavor = Integer.parseInt(order);
                    txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                    //   mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
                } else if (order != "Tk" & order != "K" & order.length() < 3) {
                    order = order + "8";

                    editTxt_taracMiavor.setText(order);
                    tarac_miavor = Integer.parseInt(order);
                    txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                    //   mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
                }
            }
        });//---------------------------------------------------------------------
        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//mer sarqac interfeysi realizacia` onButtonClicked- metodi realizacia
                if (order.equals("0")) {
                    order = "9";
                    editTxt_taracMiavor.setText(order);
                    tarac_miavor = Integer.parseInt(order);
                    txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                    //    mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
                } else if (order != "Tk" & order != "K" & order.length() < 3) {
                    order = order + "9";

                    editTxt_taracMiavor.setText(order);
                    tarac_miavor = Integer.parseInt(order);
                    txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                    //   mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
                }
            }
        });
//---------------------------------------------------------------------
        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//mer sarqac interfeysi realizacia` onButtonClicked- metodi realizacia

                if (order != "Tk" & order != "K" & order != "0" & order.length() < 3) {
                    {
                        order = order + "0";

                        editTxt_taracMiavor.setText(order);
                        tarac_miavor = Integer.parseInt(order);
                        txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                        //      mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
                    }
                }
            }
        });
//_____________________________________________________________________

//--------------------------------------------------------------------------
//----------------------------------dialog activity(4 tuxtn)----------------
        dialog = new Dialog(getContext());//stexcum enq dialog
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//anjatum enq vernagirn
        dialog.setContentView(R.layout.chors_tuxt_dialog);//kpcnum enq razmetkain(maketin)
        //dialog-i foni hetevn lini tapancik
        // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//asum enq dialogi mej inch ka et chaperov el lini dilaogn
//         dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
//                 WindowManager.LayoutParams.MATCH_PARENT);
//        dialog.setCancelable(false);//nazad knopkan anjatum enq

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//-------------------------------------------------------------------------
//    dialog-i view-neri inicializacia
        imgeViw_close_4tuxt_dialog = dialog.findViewById(R.id.ImgeViw_closeX);
        imgBtn_Ok_4tuxt_dialog = dialog.findViewById(R.id.ImgBtn_Ok);

        chckBx_tuz_4tuxtDialog = dialog.findViewById(R.id.chckBx_tuz);
        chckBx_valet_4tuxtDialog = dialog.findViewById(R.id.chckBx_valet);
        chckBx_9_4tuxtDialog = dialog.findViewById(R.id.chckBx_9);

        btn_4paper_minus = dialog.findViewById(R.id.btn_4paper_minus);
        btn_chorstuxt_plus = dialog.findViewById(R.id.btn_chorstuxt_plus);

        btn_sto_minus = dialog.findViewById(R.id.btn_sto_minus);
        btn_100_plus = dialog.findViewById(R.id.btn_100_plus);

        btn_50_minus = dialog.findViewById(R.id.btn_50_minus);
        btn_50_plus = dialog.findViewById(R.id.btn_50_plus);

        txtView_chorstuxt_Qanak_4tuxtDialog = dialog.findViewById(R.id.txtView_chorstuxt_Qanak);
        txtView_100_Qanak_4tuxtDialog = dialog.findViewById(R.id.txtView_100_Qanak);
        txtView_50_Qanak_4tuxtDialog = dialog.findViewById(R.id.txtView_50_Qanak);
//--------------------------------------------------------------------------------
//   4 tuxt- dialog-i kanch
        txtView_Extra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//mer sarqac interfeysi realizacia` onButtonClicked- metodi realizacia
                dialog.show();
                //miangamic pakel nerqevi toxi
//                dismiss();
            }
        });
//-------------------------------------------------------------------------------
        chckBx_tuz_4tuxtDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (chckBx_tuz_4tuxtDialog.isChecked()) {
                    if (mast == 4)//xozn boy e
                        combination = combination + 19;
                    else//xozn boy che
                        combination = combination + 11;
                } else if (mast == 4)//xozn boy e
                    combination = combination - 19;
                else//xozn boy che
                    combination = combination - 11;
                txtView_combination.setText("" + combination);
                txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                //  mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
            }
        });
//-------------------------------------------------------------------------------
        chckBx_valet_4tuxtDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (chckBx_valet_4tuxtDialog.isChecked()) {
                    if (mast != 4)//xozn boy chi
                        combination = combination + 20;
                    else
                        combination = combination + 10;
                } else if (mast != 4)//xozn boy chi
                    combination = combination - 20;
                else
                    combination = combination - 10;

                txtView_combination.setText("" + combination);
                txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                //  mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
            }
        });
//-------------------------------------------------------------------------------
        chckBx_9_4tuxtDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (chckBx_9_4tuxtDialog.isChecked()) {
                    if (mast != 4)//xozn boy chi
                        combination = combination + 14;
//                    else
//                     combination = combination + 0;
                } else if (mast != 4)//xozn boy chi
                    combination = combination - 14;
//                else
//                    combination = combination - 0;
                txtView_combination.setText("" + combination);
                txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                //  mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
            }
        });
//-------------------------------------------------------------------------------
//-------------------------------------------------------------------------------
        btn_chorstuxt_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    temp_tiv = Integer.parseInt(txtView_chorstuxt_Qanak_4tuxtDialog.getText().toString());

                } catch (ClassCastException e) {
                    throw new ClassCastException();
                }

                if (!txtView_chorstuxt_Qanak_4tuxtDialog.getText().equals("3")) {
                    combination = combination + 10;
                    txtView_chorstuxt_Qanak_4tuxtDialog.setText("" + (temp_tiv + 1));
                    txtView_combination.setText("" + combination);
                    txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                    //      mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
                }
            }

        });
//-------------------------------------------------------------------------------
        btn_4paper_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    temp_tiv = Integer.parseInt(txtView_chorstuxt_Qanak_4tuxtDialog.getText().toString());

                } catch (ClassCastException e) {
                    throw new ClassCastException();
                }

                if (!txtView_chorstuxt_Qanak_4tuxtDialog.getText().equals("0")) {
                    txtView_chorstuxt_Qanak_4tuxtDialog.setText("" + (temp_tiv - 1));
                    combination = combination - 10;
                    txtView_combination.setText("" + combination);
                    txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                    //     mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
                }

            }

        });
//-------------------------------------------------------------------------------
        btn_100_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    temp_tiv = Integer.parseInt(txtView_100_Qanak_4tuxtDialog.getText().toString());

                } catch (ClassCastException e) {
                    throw new ClassCastException();
                }

                if (!txtView_100_Qanak_4tuxtDialog.getText().equals("2")) {
                    combination = combination + 10;
                    txtView_100_Qanak_4tuxtDialog.setText("" + (temp_tiv + 1));
                    txtView_combination.setText("" + combination);
                    txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                    //    mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
                }
            }

        });
//-------------------------------------------------------------------------------
        btn_sto_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    temp_tiv = Integer.parseInt(txtView_100_Qanak_4tuxtDialog.getText().toString());

                } catch (ClassCastException e) {
                    throw new ClassCastException();
                }

                if (!txtView_100_Qanak_4tuxtDialog.getText().equals("0")) {
                    txtView_100_Qanak_4tuxtDialog.setText("" + (temp_tiv - 1));
                    combination = combination - 10;
                    txtView_combination.setText("" + combination);
                    txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                    //    mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
                }

            }

        });
//-------------------------------------------------------------------------------
//-------------------------------------------------------------------------------
        btn_50_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    temp_tiv = Integer.parseInt(txtView_50_Qanak_4tuxtDialog.getText().toString());

                } catch (ClassCastException e) {
                    throw new ClassCastException();
                }

                if (!txtView_50_Qanak_4tuxtDialog.getText().equals("4")) {
                    combination = combination + 5;
                    txtView_50_Qanak_4tuxtDialog.setText("" + (temp_tiv + 1));
                    txtView_combination.setText("" + combination);
                    txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                    //    mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
                }
            }

        });
//-------------------------------------------------------------------------------
        btn_50_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    temp_tiv = Integer.parseInt(txtView_50_Qanak_4tuxtDialog.getText().toString());

                } catch (ClassCastException e) {
                    throw new ClassCastException();
                }

                if (!txtView_50_Qanak_4tuxtDialog.getText().equals("0")) {
                    txtView_50_Qanak_4tuxtDialog.setText("" + (temp_tiv - 1));
                    combination = combination - 5;
                    txtView_combination.setText("" + combination);
                    txtViw_ArdyunqMiavor.setText("" + (tarac_miavor + combination));
                    //    mListener.onButtonClicked("" + (tarac_miavor + combination), kom1_2_miavor_avtomat_hashvac);
                }

            }

        });
//-------------------------------------------------------------------------------
        //   4 tuxt- dialog-i ok button
        imgBtn_Ok_4tuxt_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//mer sarqac interfeysi realizacia` onButtonClicked- metodi realizacia
//                dialog.show();
                //miangamic pakel nerqevi toxi
                dialog.dismiss();
            }
        });
//-------------------------------------------------------------------------------
        //   4 tuxt- dialog-i close button
        imgeViw_close_4tuxt_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//mer sarqac interfeysi realizacia` onButtonClicked- metodi realizacia
//                dialog.show();
                //miangamic pakel nerqevi toxi
                dialog.dismiss();
            }
        });
//-------------------------------------------------------------------------------


//-------------------------------------------------------------------------------
//onCreate-i avart
        return v;
    }

    //-------------------------------------------------------------------------------
//!!!!!!!!!!!!!!!!!!!!!!
//stexcum enq interfeys, vorn uni mek metod, vorin el 2 parametr enq talis
//ays interfeysi metodn petq e pereopredelyatca lini partaadir mainactivty-um qani vor
//mainActivity-n implement e anum mer ays ExtraButtonDialog clasi ays BottomSheetListener
//metodin (ExtraButtonDialog.BottomSheetListener), @nd vorum mainActivty-um
// pereopredelyatca anelis, ays metodn stanum e 2 String parametr ays clasic pryamoy
    public interface BottomSheetListener {
        void onButtonClicked(int taracMiavor_Kom1_2, int kom1_2_miavor_avtomat_hashvac);
    }

    //----------------------------------------------------------
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement BottomSheetListener");
        }
    }

    //_____________________________________________________________________
//ays metodum katarum enq hakarakord tmi miavornern, @nd vorum reversi efekt enq arel
// vorpeszi kodn 2 angam chgrenq mi hat paymanov stugum enq te kom1-n e te kom2-e xozn xosacel
//apa ete kom1-n e toxum enq vonc ka
// (qani vor kodn grel  enq kom1-n e xosacel variantov) isk ete kom2-n e, uxaki kom1-i
//kom2-i texern enq poxum
    private void hakarakord_Tmi_Miavor_hashvum() {
        int temp_kom1_kom2;
//----------------------------
//ete mezic mekn e xosacel
        if (kom1_kom2_kanchele == 1) {//ete es em xosacel
            temp_kom1_kom2 = kom1_kom2;
        } else {//ete duq eq xosacel,
            if (kom1_kom2 == 1)
                temp_kom1_kom2 = 2;
            else
                temp_kom1_kom2 = 1;
        }

        //if (temp_Xosacac_Tim == 1)
        {
//ete xozn xosacel e kom1-n, u usern grum e kom1-i taracn arajinn
            if (temp_kom1_kom2 == 1) {
                //kom1 xosacele ev tak e tvel
                if (tarac_itog < (hashiv_Razobrani.getXosacac_tiv() * 2)) {
                    hakarakord_miavor_avtomat_hashvac =
                            (16 + (hashiv_Razobrani.getXosacac_tiv()
                                    *//ete quansh ka. ete chka angam 1-ov e
                                    hashiv_Razobrani.getQuansh()) + combination + boy_xoz);
                    tarac_itog = 0;
                } else//kom1 xosacel e ev hanel e
                    if (((16 + boy_xoz) - (tarac_miavor - hashiv_Razobrani.getXosacac_tiv())) > 0)
//Log.d("aaa", ""+tarac_miavor);
                        hakarakord_miavor_avtomat_hashvac = 16 + boy_xoz -
                                (tarac_miavor - hashiv_Razobrani.getXosacac_tiv());
            }
//ete xozn xosacel e kom1-n, u usern grum e kom2-i taracn arajinn
//------------------------------------
            else if (temp_kom1_kom2 == 2) {//usern grum e kom2-i taracn arajinn
                if (hashiv_Razobrani.getKapuyt()//ete kapuyt e xosacel kom1-n
                        || //kam quansh e asvel
                        hashiv_Razobrani.getQuansh() > 1) {
//ete kapuyt e xosacel ka, quansh kom1-n ev chi hanel
//                           Log.d("astx",""+ tarac_miavor);
                    if (tarac_miavor > 0)
                        hakarakord_miavor_avtomat_hashvac = 0;
                    else//ete kom2 0 miavor e havaqel
                    {//hakarakord-in verarum enq ir miavornern k || quansh depqerum

                        hakarakord_miavor_avtomat_hashvac =
                                ((hashiv_Razobrani.getKapuyt() ? 25 : 16) +//ete kapuyt e + 25, h.d. + 16
                                        (hashiv_Razobrani.getXosacac_tiv()
                                                *
                                                hashiv_Razobrani.getQuansh()) + boy_xoz);
                    }
                } else { // ete kapuyt chka
//xozn xosacel e kom1-n ev hanel e, bayc arajinn usern grum e kom2-in
                    if (tarac_miavor <= (16 - hashiv_Razobrani.getXosacac_tiv()))
//                        Log.d("aaa",""+
//                                (16 - tarac_miavor + (hashiv_Razobrani.getXosacac_tiv()+boy_xoz)));
                        hakarakord_miavor_avtomat_hashvac =
                                (16 - tarac_miavor + (hashiv_Razobrani.getXosacac_tiv() + boy_xoz));
//xozn xosacel e kom1-n ev chi hanel, bayc arajinn usern grum e kom2-in
                    else if (tarac_miavor >= (16 + hashiv_Razobrani.getXosacac_tiv()))
                        hakarakord_miavor_avtomat_hashvac = 0;
                }
            }
        }
    }
//_____________________________________________________________________
}
