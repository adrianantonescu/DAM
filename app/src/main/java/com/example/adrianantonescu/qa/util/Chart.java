package com.example.adrianantonescu.qa.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Chart extends View {
    private Map<Long,Integer> source;
    private Paint paint;
    public Chart(Context context, Map<Long,Integer> source) {
        super(context);
        this.source=source;
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(source!=null&&source.size()>0){
            @SuppressLint("CanvasSize") float paddW= (float) (canvas.getWidth()*0.05);
            @SuppressLint("CanvasSize") float paddH= (float) (canvas.getHeight()*0.05);
            @SuppressLint("CanvasSize") float availableWidth= canvas.getWidth()-2*paddW;
            @SuppressLint("CanvasSize") float availableHeight=canvas.getHeight()-2*paddH;
            float widthOfElements = availableWidth/source.size();
            @SuppressLint("DrawAllocation") List<Long> labels = new ArrayList<>(source.keySet());
            //desen bare coordonate
            paint.setStrokeWidth(5);
            canvas.drawLine(paddW,paddH,paddW,paddH+availableHeight,paint);
            canvas.drawLine(paddW,paddH+availableHeight,paddW+availableWidth,paddH+availableHeight,paint);
            for(int i=0;i<labels.size();i++){
                int color=Color.argb(100,120,15,200);
                paint.setColor(color);

                float x1=paddW+i*widthOfElements;
                float y1= (paddH+(1-(float)(source.get(labels.get(i)))/100)*availableHeight);
                float x2=x1+widthOfElements;
                float y2=paddH+availableHeight;
                canvas.drawRect(x1,y1,x2,y2,paint);
                float x=x1+widthOfElements/4;
                float y=availableHeight+paddH/2;
                paint.setColor(Color.BLACK);
                paint.setTextSize((float) (availableHeight*0.05));
                canvas.drawText("Materie "+labels.get(i)+ " - " +source.get(labels.get(i)).toString(),x,y,paint);
            }
        }
    }
}
