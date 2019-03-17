package com.example.controller;

import com.example.Gson.*;
import com.example.pojo.*;
import com.example.service.*;
import org.apache.logging.log4j.util.StringBuilders;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("mybatis")
public class Mybatis {
    @Autowired
    private UserService userService;

    @RequestMapping("/queryUser")
    public User queryUser() {
        String userId = "2";
        User user = userService.queryUserById(userId);
        System.out.println(user.getName());
//        System.out.println(userService.queryUserById(userId));
        return userService.queryUserById(userId);
    }
    public String RequestReader(HttpServletRequest request){
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = request.getReader()){
            char[] buff = new char[1024];
            int len;
            while ((len=br.read(buff))!=-1){
                sb.append(buff,0,len);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            return sb.toString();
        }
    }
    @Autowired
    private DxtService dxtService;

    @RequestMapping("/xzt")
    public String insertXzt(@RequestBody DxtGson dxtGson){
        try {
            System.out.println(dxtGson.getXx().get(0).getText());
            Danxuanti danxuanti = new Danxuanti();
            danxuanti.setUid(1);
            danxuanti.setTigan(dxtGson.getTigan());
            String temp ="";
            for (int i=0;i<dxtGson.getXx().size();i++){
                if (i==(dxtGson.getXx().size()-1)){
                    temp+=dxtGson.getXx().get(i).getText();
                }else {
                    temp+=dxtGson.getXx().get(i).getText()+"|";
                }
            }
            danxuanti.setXx(temp);
            danxuanti.setNyd(dxtGson.getNyd());
            danxuanti.setDa(dxtGson.getDa());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            danxuanti.setCreateTime(df.format(new Date()));
            dxtService.saveDanxuanti(danxuanti);
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }
    @RequestMapping(value = "/queryXztAll")
    @ResponseBody
    public List<DxtGson> queryXztAll(){
        List<Danxuanti> danxuantis = dxtService.queryDanxuantiAll();
        List<DxtGson> dxtGsons = new ArrayList<>();
        for (int i=0;i<danxuantis.size();i++){
            DxtGson dxtGson = new DxtGson();
            dxtGson.setTigan(danxuantis.get(i).getTigan());
            dxtGson.setCreateTime(danxuantis.get(i).getCreateTime());
            dxtGson.setDa(danxuantis.get(i).getDa());
            dxtGson.setId(danxuantis.get(i).getId());
            dxtGson.setNyd(danxuantis.get(i).getNyd());
            dxtGson.setUid(danxuantis.get(i).getUid());
            String[] xx = danxuantis.get(i).getXx().split("\\|");
            List<DxtXxGson> dxtXxGsonList = new ArrayList<>();
            for (int j=0;j<xx.length;j++){
                DxtXxGson dxtXxGson = new DxtXxGson();
                dxtXxGson.setText(xx[j]);
                dxtXxGsonList.add(dxtXxGson);
            }
            dxtGson.setXx(dxtXxGsonList);
            dxtGsons.add(dxtGson);
        }
        return dxtGsons;
    }
    @Autowired
    private DuoxtService duoxtService;

    @RequestMapping("/dxt")
    public String insertDzt(@RequestBody DuoxtGson dxtGson){
        try {
            System.out.println(dxtGson.getXx().get(0).getText());
            Duoxuanti duoxuanti = new Duoxuanti();
            duoxuanti.setUid(1);
            duoxuanti.setTigan(dxtGson.getTigan());
            String temp ="";
            for (int i=0;i<dxtGson.getXx().size();i++){
                if (i==(dxtGson.getXx().size()-1)){
                    temp+=dxtGson.getXx().get(i).getText();
                }else {
                    temp+=dxtGson.getXx().get(i).getText()+"|";
                }
            }
            duoxuanti.setXx(temp);
            duoxuanti.setNyd(dxtGson.getNyd());
            String da = "";
            for (int i=0;i<dxtGson.getDa().size();i++){
                if (i==(dxtGson.getDa().size()-1)){
                    da+=dxtGson.getDa().get(i);
                }else {
                    da+=dxtGson.getDa().get(i)+"|";
                }
            }
            duoxuanti.setDa(da);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            duoxuanti.setCreatetime(df.format(new Date()));
            duoxtService.saveDuoxuanti(duoxuanti);
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }
    @Autowired
    private TktService tktService;

    @RequestMapping(value = "/tkt")
    public String insertTkt(@RequestBody TktGson tktGson){
        try {
            Tiankongti tiankongti = new Tiankongti();
            tiankongti.setTigan(tktGson.getTigan());
            tiankongti.setNyd(tktGson.getNyd());
            String da="";
            for (int i=0;i<tktGson.getDa().size();i++) {
                if(i==(tktGson.getDa().size()-1)){
                    da+=tktGson.getDa().get(i).getText();
                }else {
                    da+=tktGson.getDa().get(i).getText()+"|";
                }
            }
            tiankongti.setDa(da);
            tiankongti.setCreateTime( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//            tiankongti.setUid(1);
            tktService.saveTiankongti(tiankongti);
            return "true";
        }catch (Exception e){
            System.out.println(e.toString());
            return e.toString();
        }

    }
    @Autowired
    private PdtService pdtService;
    @RequestMapping(value = "pdt")
    public String insertPdt(@RequestBody PdtGson pdtGson){
        try{
            Panduanti panduanti = new Panduanti();
            panduanti.setTigan(pdtGson.getTigan());
            panduanti.setDa(pdtGson.getDa());
            panduanti.setNyd(pdtGson.getNyd());
            panduanti.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            pdtService.savePanduanti(panduanti);
            return "true";
        }catch (Exception e){
            return e.toString();
        }

    }
    @Autowired
    private JdtService jdtService;
    @RequestMapping(value = "jdt")
    public String insertJdt(@RequestBody JdtGson jdtGson){
        try {
            Jiandati jiandati = new Jiandati();
            jiandati.setTigan(jdtGson.getTigan());
            jiandati.setDa(jdtGson.getDa());
            jiandati.setNyd(jdtGson.getNyd());
            jiandati.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            jdtService.saveJandati(jiandati);
            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }
    @Autowired
    private ZhtService zhtService;
    @RequestMapping(value = "zht")
    public String insertZht(@RequestBody ZhtGson zhtGson){
        try {
            Zongheti zongheti = new Zongheti();
            zongheti.setTigan(zhtGson.getTigan());
            zongheti.setNyd(zhtGson.getNyd());
            String tm="";
            String da="";
            for (int i=0;i<zhtGson.getDa().size();i++){
                if (i==(zhtGson.getDa().size()-1)){
                    tm+=zhtGson.getDa().get(i).getTm();
                    da+=zhtGson.getDa().get(i).getDa();
                }else {
                    tm+=zhtGson.getDa().get(i).getTm()+"|";
                    da+=zhtGson.getDa().get(i).getDa()+"|";
                }
            }
            zongheti.setTm(tm);
            zongheti.setDa(da);
            zongheti.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            zhtService.saveZongheti(zongheti);
            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }
}
