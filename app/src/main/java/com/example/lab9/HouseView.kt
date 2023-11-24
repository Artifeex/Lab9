package com.example.lab9

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import kotlin.properties.Delegates

class HouseView(
    context: Context,
    attributeSet: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : View(context, attributeSet, defStyleAttr, defStyleRes) {

    private var backgroundTopColor by Delegates.notNull<Int>()
    private var backgroundBottomColor by Delegates.notNull<Int>()
    private var roofColor by Delegates.notNull<Int>()
    private var chimneyColor by Delegates.notNull<Int>()
    private var bodyColor by Delegates.notNull<Int>()

    private lateinit var backgroundTopPaint: Paint
    private lateinit var backgroundBottomPaint: Paint
    private lateinit var roofPaint: Paint
    private lateinit var chimneyPaint: Paint
    private lateinit var bodyPaint: Paint

    private val fieldRect = RectF(0f, 0f, 0f, 0f)

    private val pathForRoof = Path()

    private var roofWidth by Delegates.notNull<Int>()
    private var roofHeight by Delegates.notNull<Int>()


    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, R.attr.houseViewStyle)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attributeSet,
        defStyleAttr,
        R.style.DefaultStyleHouseView
    )

    init {
        if (attributeSet != null) {
            initAttrs(attributeSet, defStyleAttr, defStyleRes)
        } else {
            initDefaultColors()
        }
        initPaints()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        fieldRect.left = paddingLeft.toFloat()
        fieldRect.right = width - paddingRight.toFloat()
        fieldRect.top = paddingTop.toFloat()
        fieldRect.bottom = height - paddingBottom.toFloat()
    }


    override fun onDraw(canvas: Canvas) {
        drawBackgroundTop(canvas)
        drawBackgroundBottom(canvas)
        drawChimney(canvas)
        drawRoof(canvas)
        drawBody(canvas)
    }

    private fun drawBackgroundTop(canvas: Canvas) {
        canvas.drawRect(RectF(fieldRect.left, fieldRect.top, fieldRect.right, fieldRect.bottom * 4/5), backgroundTopPaint)
    }

    private fun drawBackgroundBottom(canvas: Canvas) {
        canvas.drawRect(RectF(fieldRect.left, fieldRect.bottom * 4/5, fieldRect.right, fieldRect.bottom), backgroundBottomPaint)
    }

    private fun drawChimney(canvas: Canvas) {
        val height = fieldRect.height() / 7
        val width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30f, resources.displayMetrics)
        val xTop = fieldRect.width() / 2 + (fieldRect.width() / 7).toInt()
        val yTop = fieldRect.height() * 1 / 2 + TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 90f, resources.displayMetrics)

        canvas.drawRect(xTop, yTop, xTop + width, yTop - height, chimneyPaint)
    }

    private fun drawRoof(canvas: Canvas) {
        val xTop = fieldRect.width() / 2
        val yTop = fieldRect.height() / 2 + TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6f, resources.displayMetrics)
        roofWidth = (fieldRect.width() / 3).toInt()
        roofHeight = (fieldRect.height() / 7).toInt()

        pathForRoof.moveTo(xTop, yTop)
        pathForRoof.lineTo(xTop - roofWidth, yTop + roofHeight)
        pathForRoof.lineTo(xTop + roofWidth, yTop + roofHeight)
        canvas.drawPath(pathForRoof, roofPaint)
    }

    private fun drawBody(canvas: Canvas) {
        val xStart = fieldRect.width() / 2 - fieldRect.width() / 4
        val yBottom = fieldRect.bottom * 4 / 5 + (fieldRect.bottom - fieldRect.bottom * 4 / 5) / 2
        val yTop = yBottom - fieldRect.height() / 4
        val xEnd = xStart + fieldRect.width() / 2

        canvas.drawRect(xStart, yTop, xEnd, yBottom, bodyPaint)
    }


    private fun initAttrs(attributeSet: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        val typedArray = context.obtainStyledAttributes(
            attributeSet,
            R.styleable.HouseView,
            defStyleAttr,
            defStyleRes
        )
        backgroundTopColor = typedArray.getColor(R.styleable.HouseView_backgroundTopColor, DEFAULT_BACKGROUND_TOP_COLOR)
        backgroundBottomColor = typedArray.getColor(R.styleable.HouseView_backgroundBottomColor, DEFAULT_BACKGROUND_BOTTOM_COLOR)
        roofColor = typedArray.getColor(R.styleable.HouseView_roofColor, DEFAULT_ROOF_COLOR)
        chimneyColor = typedArray.getColor(R.styleable.HouseView_chimneyColor, DEFAULT_CHIMNEY_COLOR)
        bodyColor = typedArray.getColor(R.styleable.HouseView_bodyColor, DEFAULT_BODY_COLOR)

        typedArray.recycle()
    }

    private fun initDefaultColors() {
        backgroundTopColor = DEFAULT_BACKGROUND_TOP_COLOR
        backgroundBottomColor = DEFAULT_BACKGROUND_BOTTOM_COLOR
        roofColor = DEFAULT_ROOF_COLOR
        chimneyColor = DEFAULT_CHIMNEY_COLOR
        bodyColor = DEFAULT_BODY_COLOR
    }

    private fun initPaints() {
        backgroundTopPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        with(backgroundTopPaint) {
            color = backgroundTopColor
            style = Paint.Style.FILL_AND_STROKE
            strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4f, resources.displayMetrics)
        }
        backgroundBottomPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        with(backgroundBottomPaint) {
            color = backgroundBottomColor
            style = Paint.Style.FILL_AND_STROKE
            strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4f, resources.displayMetrics)
        }
        roofPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        with(roofPaint) {
            color = roofColor
            style = Paint.Style.FILL
            strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4f, resources.displayMetrics)
        }
        chimneyPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        with(chimneyPaint) {
            color = chimneyColor
            style = Paint.Style.FILL
            strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4f, resources.displayMetrics)
        }
        bodyPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        with(bodyPaint) {
            color = bodyColor
            style = Paint.Style.FILL
            strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4f, resources.displayMetrics)
        }
    }

    companion object {

        const val DEFAULT_BACKGROUND_TOP_COLOR = Color.BLUE
        const val DEFAULT_BACKGROUND_BOTTOM_COLOR = Color.GREEN
        const val DEFAULT_ROOF_COLOR = Color.BLACK
        const val DEFAULT_CHIMNEY_COLOR = Color.RED
        const val DEFAULT_BODY_COLOR = Color.BLACK
    }


}