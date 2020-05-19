package com.itstyle.quartz.job;

import org.quartz.*;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.Date;

/**
 * @author sc
 * @createTime 2020/4/13 21:09
 * @description
 */
@DisallowConcurrentExecution
public class MeJobDemo implements Job,Serializable {



    @Override
    public void execute(JobExecutionContext context){
        JobDetail jobDetail = context.getJobDetail();
        JobDataMap dataMap = jobDetail.getJobDataMap();
        /**
         * 获取任务中保存的方法名字，动态调用方法
         */
        String methodName = dataMap.getString("jobMethodName");
        try {
            MeJobDemo job = new MeJobDemo();
            Method method = job.getClass().getMethod(methodName);
            method.invoke(job);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void test1(){
        System.out.println("MeJobDemo测试方法1"+ DateFormat.getDateTimeInstance().format(new Date()));
    }

    public void test2(){
        System.out.println("MeJobDemo测试方法2"+ DateFormat.getDateTimeInstance().format(new Date()));
    }
}
