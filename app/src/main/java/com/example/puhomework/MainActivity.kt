package com.example.puhomework

import android.app.appsearch.GenericDocument
import android.media.audiofx.Visualizer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),
    GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener,View.OnTouchListener{
    lateinit var gDetector: GestureDetector
    var PictureNo:Int = 0  //目前顯示第幾張圖
    var TotalPictures:Int = 5 //總共幾張圖片(假設僅顯示pu0-pu3)
    fun ShowPicture() {
        /*
        when (PictureNo) {
            0 -> img.setImageResource(R.drawable.pu0)
            1 -> img.setImageResource(R.drawable.pu1)
            2 -> img.setImageResource(R.drawable.pu2)
            3 -> img.setImageResource(R.drawable.pu3)
        }

         */
        txv.text = PictureNo.toString()
        var res:Int = getResources().getIdentifier("hw" + PictureNo.toString(),
            "drawable",getPackageName())
        img.setImageResource(res)
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gDetector = GestureDetector(this, this)
        img.setOnTouchListener(this)
        var res:Int = -1
        var countDrawables:Int = -1
        while (res != 0) {
            countDrawables++;
            res = getResources().getIdentifier("hw" + countDrawables.toString(),
                "drawable", getPackageName());
        }
        TotalPictures = countDrawables
}

    override fun onTouch(p0: View?, event: MotionEvent?): Boolean {
        gDetector.onTouchEvent(event)
        return true
    }

    override fun onDown(p0: MotionEvent?): Boolean {
        txv.text = "按下"
        return true
    }
    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        PictureNo = 0
        ShowPicture()
        txv.text = "短按"
        return true
    }
    override fun onLongPress(p0: MotionEvent?) {
        PictureNo = TotalPictures - 1
        ShowPicture()
        txv.text = "長按"
    }
    override fun onShowPress(p0: MotionEvent?) {
        txv.text = "按下後無後續動作"
    }
    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        return true
    }
    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        if (e1!!.getX() < e2!!.getX()){  //向右快滑
            PictureNo++
            if (PictureNo == TotalPictures) { PictureNo = 0 }
        }
        else {     //向左快滑
            PictureNo--;
            if (PictureNo < 0) {PictureNo = TotalPictures - 1 }
        }
        ShowPicture()
        return true
    }
    override fun onDoubleTap(p0: MotionEvent?): Boolean {
        return true
    }
    override fun onDoubleTapEvent(p0: MotionEvent?): Boolean {
        txv.text = "連續點兩下"
        return true
    }
    override fun onSingleTapConfirmed(p0: MotionEvent?): Boolean {
        return true
    }
}


