package Utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

public class LineTextView extends androidx.appcompat.widget.AppCompatTextView {
    private Rect mRect;
    private Paint mPaint;

    // constructor cho LayoutInflater
    public LineTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(Color.parseColor("#C0C0C0")); // chọn màu yêu thích
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //int count = getLineCount();

        int height = getHeight();
        int line_height = getLineHeight();

        int count = height / line_height;

        if (getLineCount() > count)
            count = getLineCount(); // scroll cho long text

        Rect r = mRect;
        Paint paint = mPaint;
        int baseline = getLineBounds(0, r); // dong` dau tien

        for (int i = 0; i < count; i++) {

            canvas.drawLine(r.left, baseline + 1, r.right, baseline + 1, paint);
            baseline += getLineHeight(); // dong tiep theo
        }

        super.onDraw(canvas);
    }
}
