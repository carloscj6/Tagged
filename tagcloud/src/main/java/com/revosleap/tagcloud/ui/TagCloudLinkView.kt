package com.revosleap.tagcloud.ui

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.*
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.nex3z.flowlayout.FlowLayout
import com.revosleap.tagcloud.R
import com.revosleap.tagcloud.entities.Tag
import java.util.*

class TagCloudLinkView : FlowLayout {

    /** tag list  */
    private val mTags = ArrayList<Tag>()
    private var tagBackground = 0
    private var removeBackground = 0
    /**
     * System Service
     */
    private var mInflater: LayoutInflater? = null
    private var mDisplay: Display? = null
    private var mViewTreeObserber: ViewTreeObserver? = null

    /**
     * listener
     */
    private var mSelectListener: OnTagSelectListener? = null
    private var mDeleteListener: OnTagDeleteListener? = null

    /** view size param  */
    private var mWidth: Int = 0
    private var mHeight: Int = 0

    /**
     * layout initialize flag
     */
    private var mInitialized = false

    /**
     * custom layout param
     */

    private var mTagTextColor: Int = 0
    private var mTagTextSize: Float = 0.toFloat()
    private var mIsDeletable: Boolean = false
    private var mDeletableTextColor: Int = 0
    private var mDeletableTextSize: Float = 0.toFloat()

    /**
     * get tag list
     * @return mTags TagObject List
     */
    val tags: List<Tag>
        get() = mTags

    /**
     * constructor
     * @param ctx
     * @param attrs
     */
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
        initialize(ctx, attrs, 0)
    }

    /**
     * constructor
     * @param ctx
     * @param attrs
     * @param defStyle
     */
    constructor(ctx: Context, attrs: AttributeSet, defStyle: Int) : super(ctx, attrs) {
        initialize(ctx, attrs, defStyle)
    }

    /**
     * initalize instance
     * @param ctx
     * @param attrs
     * @param defStyle
     */
    private fun initialize(ctx: Context, attrs: AttributeSet, defStyle: Int) {
        mInflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mDisplay = (ctx.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
        mViewTreeObserber = viewTreeObserver
        mViewTreeObserber!!.addOnGlobalLayoutListener {
            if (!mInitialized) {
                mInitialized = true
                drawTags()
            }
        }


        // get AttributeSet
        val typeArray =
            ctx.obtainStyledAttributes(attrs, R.styleable.TagCloudLinkView, defStyle, defStyle)
        tagBackground = typeArray.getResourceId(
            R.styleable.TagCloudLinkView_tagBackground,
            R.drawable.default_tag_bg
        )
        removeBackground = typeArray.getResourceId(
            R.styleable.TagCloudLinkView_deletableBackground,
            R.drawable.defautl_remove_bg
        )
        mTagTextColor = typeArray.getColor(
            R.styleable.TagCloudLinkView_tagTextColor, DEFAULT_TAG_TEXT_COLOR
        )
        mTagTextSize = typeArray.getDimension(
            R.styleable.TagCloudLinkView_tagTextSize, DEFAULT_TEXT_SIZE.toFloat()
        )
        mIsDeletable = typeArray.getBoolean(
            R.styleable.TagCloudLinkView_isDeletable, false
        )
        mDeletableTextColor = typeArray.getColor(
            R.styleable.TagCloudLinkView_deletableTextColor, DEFAULT_DELETABLE_TEXT_COLOR
        )
        mDeletableTextSize = typeArray.getDimension(
            R.styleable.TagCloudLinkView_deletableTextSize, DEFAULT_TEXT_SIZE.toFloat()
        )

        typeArray.recycle()
    }

    /**
     * onSizeChanged
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        mWidth = w
        mHeight = h
    }

    /**
     * view width
     * @return layout width
     */
    fun width(): Int {
        return mWidth
    }

    /**
     * view height
     * @return int layout height
     */
    fun height(): Int {
        return mHeight
    }

    /**
     * add Tag
     * @param tag
     */
    fun add(tag: Tag) {
        mTags.add(tag)
    }

    /**
     * tag draw
     */
    private fun drawTags() {

        if (!mInitialized) {
            return
        }

        // clear all tag
        removeAllViews()

        // layout padding left & layout padding right

        var index = 1

        // List Index
        for ((listIndex, item) in mTags.withIndex()) {

            // inflate tag layout
            val tagLayout = mInflater!!.inflate(R.layout.tag, null) as View
            tagLayout.id = index
            tagLayout.background = ContextCompat.getDrawable(context, tagBackground)

            // tag text
            val tagView = tagLayout.findViewById<View>(R.id.tag_txt) as TextView
            tagView.text = item.text
            tagView.setPadding(
                INNER_VIEW_PADDING,
                INNER_VIEW_PADDING,
                INNER_VIEW_PADDING,
                INNER_VIEW_PADDING
            )
            tagView.setTextColor(mTagTextColor)
            tagView.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTagTextSize)
            tagLayout.setOnClickListener {
                if (mSelectListener != null) {
                    mSelectListener!!.onTagSelected(item, listIndex)
                }
            }
            val deletableView = tagLayout.findViewById<View>(R.id.delete_txt) as TextView
            if (mIsDeletable) {
                deletableView.visibility = View.VISIBLE
                deletableView.text = DEFAULT_DELETABLE_STRING
                deletableView.setTextColor(mDeletableTextColor)
                deletableView.background = ContextCompat.getDrawable(context, removeBackground)
                deletableView.textSize = mDeletableTextSize
                deletableView.setOnClickListener {
                    if (mDeleteListener != null) {
                        this@TagCloudLinkView.remove(listIndex)
                        mDeleteListener!!.onTagDeleted(item, listIndex)
                    }
                }

            } else {
                deletableView.visibility = View.GONE
            }

            val tagParams = LayoutParams(HEIGHT_WC, HEIGHT_WC)
            addView(tagLayout, tagParams)
            index++
        }

    }


    /**
     * remove tag
     *
     * @param position
     */
    fun remove(position: Int) {
        mTags.removeAt(position)
        drawTags()
    }

    /**
     * setter for OnTagSelectListener
     * @param selectListener
     */
    fun setOnTagSelectListener(selectListener: OnTagSelectListener) {
        mSelectListener = selectListener
    }

    /**
     * setter for OnTagDeleteListener
     * @param deleteListener
     */
    fun setOnTagDeleteListener(deleteListener: OnTagDeleteListener) {
        mDeleteListener = deleteListener
    }

    /**
     * listener for tag select
     */
    interface OnTagSelectListener {
        fun onTagSelected(tag: Tag, position: Int)
    }

    /**
     * listener for tag delete
     */
    interface OnTagDeleteListener {
        fun onTagDeleted(tag: Tag, position: Int)
    }

    companion object {
        private val HEIGHT_WC = LayoutParams.WRAP_CONTENT
        private val INNER_VIEW_PADDING = 10
        private val DEFAULT_TEXT_SIZE = 16
        private val DEFAULT_TAG_TEXT_COLOR = Color.parseColor("#1a1a1a")
        private val DEFAULT_DELETABLE_TEXT_COLOR = Color.parseColor("#FFFFFF")
        private val DEFAULT_DELETABLE_STRING = "×"
    }
}
