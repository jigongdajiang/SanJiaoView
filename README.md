# SanJiaoView
### 示例代码
```Java
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/activity_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin">
   <RelativeLayout
       android:layout_width="match_parent"
       android:layout_height="wrap_content"
       android:background="@color/colorAccent">
      <com.gjg.sanjiaoview.SanJiaoTextView
          android:id="@+id/stv"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:layout_alignParentRight="true"
          android:layout_alignParentTop="true"
          app:stv_text="新手"
          app:stv_angel_length="50dp"
          app:stv_text_color="@color/colorAccent"
          app:stv_text_size="11sp"
          app:stv_background="@color/colorPrimaryDark"/>
   </RelativeLayout>

   <RelativeLayout
       android:layout_width="match_parent"
       android:layout_height="wrap_content"
       android:background="#848484"
       android:layout_marginTop="10dp">
      <com.gjg.sanjiaoview.SanJiaoTextView
          android:id="@+id/stv2"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:layout_alignParentRight="true"
          android:layout_alignParentTop="true"
          app:stv_text="新手"
          app:stv_angel_length="80dp"
          app:stv_text_color="@color/colorPrimaryDark"
          app:stv_text_size="11sp"
          app:stv_background="@color/colorAccent"/>
   </RelativeLayout>

   <RelativeLayout
       android:layout_width="match_parent"
       android:layout_height="wrap_content"
       android:background="@color/colorPrimaryDark"
       android:layout_marginTop="10dp">
      <com.gjg.sanjiaoview.SanJiaoTextView
          android:id="@+id/stv3"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:layout_alignParentRight="true"
          android:layout_alignParentTop="true"
          app:stv_text="稳定型"
          app:stv_padding="20dp"
          app:stv_text_color="@color/colorPrimaryDark"
          app:stv_text_size="11sp"
          app:stv_background="@color/colorAccent"/>
   </RelativeLayout>
</LinearLayout>

```

### 示例图片
![示例](https://github.com/jigongdajiang/SanJiaoView/raw/master/img_show.png "示例图片")
