package com.example.kotlintestdemo;


import com.example.kotlintestdemo.mvp.view.activity.KnifeActivity;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
        assertThat(true, is(true));
        KnifeActivity activity=new KnifeActivity();
        activity.initdata();
    }

}
