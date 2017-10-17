package edu.utah.cs4530.painting;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Robert on 9/17/2016.
 */
public class PaintSplotch extends View {

    public interface OnSplotchChangedListener{
        void onSplotchChanged(int name);
    }

    int _name;
    int _splotchColor = Color.WHITE;
    boolean _highlighted = false;
    OnSplotchChangedListener _splotchChangedListener = null;



    public PaintSplotch(Context context, int color, int name){
        super(context);
        _splotchColor = color;
        _name = name;
    }

    public void setOnSplotchChangedListener(OnSplotchChangedListener splotchChangedListener){
        _splotchChangedListener = splotchChangedListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        _highlighted = true;
        invalidate();

        _splotchChangedListener.onSplotchChanged(_name);
        return true; //super.onTouchEvent(event);
    }

    public int get_splotchColor() {
        return _splotchColor;
    }

    public void set_splotchColor(int splotchColor) {
        _splotchColor = splotchColor;
        invalidate();
    }

    public boolean is_highlighted() {
        return _highlighted;
    }

    public void set_highlighted(boolean highlighted) {
        _highlighted = highlighted;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas){

        super.onDraw(canvas);

        RectF splotchRect = new RectF();
        splotchRect.left = getPaddingLeft();
        splotchRect.right = getWidth() - getPaddingRight();
        splotchRect.top = getPaddingTop();
        splotchRect.bottom = getHeight() - getPaddingBottom();

        Paint splotchPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        splotchPaint.setColor(_splotchColor);

        canvas.drawOval(splotchRect, splotchPaint);

        if(_highlighted){
            Paint highlightPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            // TODO What if ello splotch?
            if(_splotchColor == Color.YELLOW){
                highlightPaint.setColor(Color.WHITE);
            }
            else{
                highlightPaint.setColor(Color.YELLOW);
            }

            highlightPaint.setStyle(Paint.Style.STROKE);
            highlightPaint.setStrokeWidth(splotchRect.height() * 0.1f); // Device Independent Pixels
            canvas.drawOval(splotchRect, highlightPaint);
        }
    }


}