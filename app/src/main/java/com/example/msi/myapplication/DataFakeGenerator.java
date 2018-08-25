package com.example.msi.myapplication;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;

import com.example.msi.myapplication.animation.AnimationMainActivity;
import com.example.msi.myapplication.datamodel.Cloth;
import com.example.msi.myapplication.datamodel.MenuItem;
import com.example.msi.myapplication.datamodel.Post;
import com.example.msi.myapplication.view.activity.ClothActivity;
import com.example.msi.myapplication.view.activity.LastNewsActivity;
import com.example.msi.myapplication.view.activity.MainActivity;
import com.example.msi.myapplication.view.activity.ProfileActivity;
import com.example.msi.myapplication.view.activity.SignUpActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by msi on 8/13/2017.
 */
public class DataFakeGenerator {
    public static List<Post> getData(Context context){
        int rand;
        Random randomNumber = new Random();
        List<Post> posts = new ArrayList<>();
       /* for (int i = 1; i <= 6; i++){
            Post post = new Post();
            rand = randomNumber.nextInt(22)+1;
            post.setId(i);
            post.setTitle("لورم اپیسوم");
            post.setContent("لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است. چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است و برای شرایط فعلی تکنولوژی مورد نیاز و کاربردهای متنوع با هدف بهبود ابزارهای کاربردی می باشد. کتابهای زیادی در شصت و سه درصد گذشته، حال و آینده شناخت فراوان جامعه و متخصصان را می طلبد تا با نرم افزارها شناخت بیشتری را برای طراحان رایانه ای علی الخصوص طراحان خلاقی و فرهنگ پیشرو در زبان فارسی ایجاد کرد.");
            post.setDate(rand + "ساعت پیش");
            switch (i){
                case 1:
                    post.setPostImageUrl(ResourcesCompat.getDrawable(context.getResources(), R.drawable.pic1, null));
                    break;
                case 2:
                    post.setPostImageUrl(ResourcesCompat.getDrawable(context.getResources(), R.drawable.pic2, null));
                    break;
                case 3:
                    post.setPostImageUrl(ResourcesCompat.getDrawable(context.getResources(), R.drawable.pic3, null));
                    break;
                case 4:
                    post.setPostImageUrl(ResourcesCompat.getDrawable(context.getResources(), R.drawable.pic4, null));
                    break;
                case 5:
                    post.setPostImageUrl(ResourcesCompat.getDrawable(context.getResources(), R.drawable.pic5, null));
                    break;
                case 6:
                    post.setPostImageUrl(ResourcesCompat.getDrawable(context.getResources(), R.drawable.pic6, null));
                    break;
            }
            posts.add(post);
        }*/
        return posts;
    }

    public static List<Cloth> getClothes(Context context){
        List<Cloth> cloths = new ArrayList<>();
        Random random = new Random();
        //int randomNumber;
        for (int i=1; i<=8; i++){
            Cloth cloth = new Cloth();
            cloth.setId(i);
            cloth.setTitle("لورم اپیسوم");
            cloth.setCount((random.nextInt(8)+1)*100);
            switch (i){
                case 1:
                    cloth.setImage(ResourcesCompat.getDrawable(context.getResources(),R.drawable.pic1_cloth,null));
                    break;
                case 2:
                    cloth.setImage(ResourcesCompat.getDrawable(context.getResources(),R.drawable.pic2_cloth,null));
                    break;
                case 3:
                    cloth.setImage(ResourcesCompat.getDrawable(context.getResources(),R.drawable.pic3_cloth,null));
                    break;
                case 4:
                    cloth.setImage(ResourcesCompat.getDrawable(context.getResources(),R.drawable.pic4_cloth,null));
                    break;
                case 5:
                    cloth.setImage(ResourcesCompat.getDrawable(context.getResources(),R.drawable.pic5_cloth,null));
                    break;
                case 6:
                    cloth.setImage(ResourcesCompat.getDrawable(context.getResources(),R.drawable.pic6_cloth,null));
                    break;
                case 7:
                    cloth.setImage(ResourcesCompat.getDrawable(context.getResources(),R.drawable.pic7_cloth,null));
                    break;
                case 8:
                    cloth.setImage(ResourcesCompat.getDrawable(context.getResources(),R.drawable.pic8_cloth,null));
                    break;
            }
        cloths.add(cloth);
        }
        return cloths;
    }

    public static List<MenuItem> getMenuItem(Context context){
        List<MenuItem> menuItems = new ArrayList<>();
        for (int i=1; i<=7; i++){
            MenuItem item = new MenuItem();
            switch(i){
                case 1: {
                    item.setId(MenuItem.ID_USER_PROFILE);
                    item.setMenueTitleText("پروفایل کابر");
                    item.setMenuImage(ResourcesCompat.getDrawable(context.getResources(),R.drawable.profile,null));
                    item.setDestinationActivity(ProfileActivity.class);
                    break;
                    }
                case 2: {
                    item.setId(MenuItem.ID_POSTS_ACTIVITY);
                    item.setMenueTitleText("لیست آخرین اخبار");
                    item.setMenuImage(ResourcesCompat.getDrawable(context.getResources(), R.drawable.news, null));
                    item.setDestinationActivity(LastNewsActivity.class);
                    break;
                }
                case 3: {
                    item.setId(MenuItem.ID_MUSIC);
                    item.setMenueTitleText("پخش موسیقی");
                    item.setMenuImage(ResourcesCompat.getDrawable(context.getResources(), R.drawable.music_player, null));
                    item.setDestinationActivity(MainActivity.class);
                    break;
                }
                case 4: {
                    item.setId(MenuItem.ID_FASHION);
                    item.setMenueTitleText("فروشگاه لباس");
                    item.setMenuImage(ResourcesCompat.getDrawable(context.getResources(), R.drawable.adidas, null));
                    item.setDestinationActivity(ClothActivity.class);
                    break;
                }
                case 5: {
                    item.setId(MenuItem.ID_VIDEO);
                    item.setMenueTitleText("پخش ویدیو");
                    item.setMenuImage(ResourcesCompat.getDrawable(context.getResources(), R.drawable.video_player, null));
                    item.setDestinationActivity(MainActivity.class);
                    break;
                }
                case 6: {
                    item.setId(MenuItem.ID_LOGIN);
                    item.setMenueTitleText("ورود به حساب کاربری");
                    item.setMenuImage(ResourcesCompat.getDrawable(context.getResources(), R.drawable.profile, null));
                    item.setDestinationActivity(SignUpActivity.class);
                    break;
                }
                case 7: {
                    item.setId(MenuItem.ID_ANIMATION);
                    item.setMenueTitleText("انیمیشن ها");
                    item.setMenuImage(ResourcesCompat.getDrawable(context.getResources(), R.drawable.animations_in_android, null));
                    item.setDestinationActivity(AnimationMainActivity.class);
                    break;
                }
            }
            menuItems.add(item);
        }
        return menuItems;
    }
}
