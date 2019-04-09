package com.example.controller;

import com.example.Gson.*;
import com.example.Gson.Ksda.KsdaGson;
import com.example.Gson.sjda.ksresult;
import com.example.Gson.sjda.sjda;
import com.example.pojo.*;
import com.example.service.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.util.StringBuilders;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.json.JSONObject;
import org.json.JSONString;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("mybatis")
public class Mybatis {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(@RequestBody User userGet) {
        User user = userService.selectByName(userGet.getName());
            if (user.getPass().equals(userGet.getPass())&&user.getName().equals(userGet.getName())){
                return "true";
            }else {
                return "请检查账号密码是否正确";
            }

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
            Danxuanti danxuanti = new Danxuanti();
            danxuanti.setUid(0);
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
    @RequestMapping("/updateXzt")
    public String updateXzt(@RequestBody DxtGson dxtGson){
        try {
            Danxuanti danxuanti = new Danxuanti();
            danxuanti.setId(dxtGson.getId());
            danxuanti.setUid(dxtGson.getUid());
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
            dxtService.updateDanxuanti(danxuanti);
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }
    @RequestMapping("/updateXztUid")
    public String updateXztUid(@RequestBody UidListGson gson){
        try {
            for (int i=0;i<gson.getId().size();i++) {
                Danxuanti danxuanti = new Danxuanti();
                danxuanti.setId(gson.getId().get(i));
                danxuanti.setUid(gson.getUid());
                dxtService.updataUid(danxuanti);
            }
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }
    @RequestMapping(value = "/deleteXztById")
    public String deleteXztById(@RequestBody IdList Gson){
        try{
            for (int i=0;i<Gson.getId().size();i++){
                dxtService.deleteDanxuanti(Gson.getId().get(i));
            }
            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }

    @Autowired
    private DuoxtService duoxtService;

    @RequestMapping("/dxt")
    public String insertDzt(@RequestBody DuoxtGson dxtGson){
        try {
            Duoxuanti duoxuanti = new Duoxuanti();
            duoxuanti.setUid(0);
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
    @RequestMapping(value = "queryDxtAll")
    public List<DuoxtGson> queryDxtAll(){
        List<Duoxuanti> duoxuantis = duoxtService.queryDxtAll();
        List<DuoxtGson> duoxtGsons = new ArrayList<>();
        for (int i=0;i<duoxuantis.size();i++){
            DuoxtGson duoxtGson = new DuoxtGson();
            duoxtGson.setId(duoxuantis.get(i).getId());
            duoxtGson.setTigan(duoxuantis.get(i).getTigan());
            duoxtGson.setCreateTime(duoxuantis.get(i).getCreatetime());
            duoxtGson.setNyd(duoxuantis.get(i).getNyd());
            duoxtGson.setUid(duoxuantis.get(i).getUid());
            String[] xx = duoxuantis.get(i).getXx().split("\\|");
            List<DuoxtXxGson> xxlist = new ArrayList<>();
            String[] da = duoxuantis.get(i).getDa().split("\\|");
            List<String> dalist = new ArrayList<>();
            for (int j=0;j<xx.length;j++){
                DuoxtXxGson duoxtXxGson = new DuoxtXxGson();
                duoxtXxGson.setText(xx[j]);
                xxlist.add(duoxtXxGson);
            }
            duoxtGson.setXx(xxlist);
            for (int j=0;j<da.length;j++){
                dalist.add(da[j]);
            }
            duoxtGson.setDa(dalist);
            duoxtGsons.add(duoxtGson);
        }
        return duoxtGsons;
    }
    @RequestMapping(value = "/deleteDxtById")
    public String deleteDxtById(@RequestBody IdList Gson){
        try{
            for (int i=0;i<Gson.getId().size();i++){
                duoxtService.deleteDxtById(Gson.getId().get(i));
            };
            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }
    @RequestMapping("/updateDxt")
    public String updateDzt(@RequestBody DuoxtGson dxtGson){
        try {
            Duoxuanti duoxuanti = new Duoxuanti();
            duoxuanti.setId(dxtGson.getId());
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
            duoxtService.updataById(duoxuanti);
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }
    @RequestMapping("/updateDxtUid")
    public String updateDxtUid(@RequestBody UidListGson Gson){
        try {
            for (int i=0;i<Gson.getId().size();i++){
                Duoxuanti duoxuanti = new Duoxuanti();
                duoxuanti.setId(Gson.getId().get(i));
                duoxuanti.setUid(Gson.getUid());
                duoxtService.updataUid(duoxuanti);
            }

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
            tiankongti.setUid(0);
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
            tktService.saveTiankongti(tiankongti);
            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }
    @RequestMapping(value = "queryTktAll")
    public List<TktGson> queryTktAll(){
        List<Tiankongti> tiankongtis = tktService.queryTktAll();
        List<TktGson> tktGsons = new ArrayList<>();
        for (int i=0;i<tiankongtis.size();i++){
            TktGson tktGson = new TktGson();
            tktGson.setId(tiankongtis.get(i).getId());
            tktGson.setTigan(tiankongtis.get(i).getTigan());
            tktGson.setCreateTime(tiankongtis.get(i).getCreateTime());
            tktGson.setNyd(tiankongtis.get(i).getNyd());
            tktGson.setUid(tiankongtis.get(i).getUid());
            String[] da = tiankongtis.get(i).getDa().split("\\|");
            List<TktDaGson> dalist = new ArrayList<>();
            for (int j=0;j<da.length;j++){
                TktDaGson tktDaGson = new TktDaGson();
                tktDaGson.setText(da[j]);
                dalist.add(tktDaGson);
            }
            tktGson.setDa(dalist);
            tktGsons.add(tktGson);
        }
        return tktGsons;
    }
    @RequestMapping(value = "/deleteTktById")
    public String deleteTktById(@RequestBody IdList Gson){
        try{
            for (int i=0;i<Gson.getId().size();i++){
                tktService.deleteTktById(Gson.getId().get(i));
            }
            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }
    @RequestMapping(value = "/updateTkt")
    public String updateTkt(@RequestBody TktGson tktGson){
        try {
            Tiankongti tiankongti = new Tiankongti();
            tiankongti.setId(tktGson.getId());
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
            tktService.updataById(tiankongti);
            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }
    @RequestMapping(value = "/updateTktUid")
    public String updateTktUid(@RequestBody UidListGson Gson){
        try {
            for (int i=0;i<Gson.getId().size();i++){
                Tiankongti tiankongti = new Tiankongti();
                tiankongti.setId(Gson.getId().get(i));
                tiankongti.setUid(Gson.getUid());
                tktService.updataUid(tiankongti);
            }


            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }
    @Autowired
    private PdtService pdtService;
    @RequestMapping(value = "pdt")
    public String insertPdt(@RequestBody PdtGson pdtGson){
        try{
            Panduanti panduanti = new Panduanti();
            panduanti.setUid(0);
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
    @RequestMapping(value = "queryPdtAll")
    public List<PdtGson> queryPdtAll(){
        List<Panduanti> panduantis = pdtService.queryPdtAll();
        List<PdtGson> pdtGsons = new ArrayList<>();
        for (int i=0;i<panduantis.size();i++){
            PdtGson pdtGson = new PdtGson();
            pdtGson.setId(panduantis.get(i).getId());
            pdtGson.setTigan(panduantis.get(i).getTigan());
            pdtGson.setCreateTime(panduantis.get(i).getCreateTime());
            pdtGson.setNyd(panduantis.get(i).getNyd());
            pdtGson.setUid(panduantis.get(i).getUid());
            pdtGson.setDa(panduantis.get(i).getDa());
            pdtGsons.add(pdtGson);
        }
        return pdtGsons;
    }
    @RequestMapping(value = "/deletePdtById")
    public String deletePdtById(@RequestBody IdList Gson){
        try{
            for (int i=0;i<Gson.getId().size();i++){
                pdtService.deletePdtById(Gson.getId().get(i));
            }
            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }
    @RequestMapping(value = "updatePdt")
    public String updatePdt(@RequestBody PdtGson pdtGson){
        try{
            Panduanti panduanti = new Panduanti();
            panduanti.setId(pdtGson.getId());
            panduanti.setTigan(pdtGson.getTigan());
            panduanti.setDa(pdtGson.getDa());
            panduanti.setNyd(pdtGson.getNyd());
            panduanti.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            pdtService.updataById(panduanti);
            return "true";
        }catch (Exception e){
            return e.toString();
        }
    }
    @RequestMapping(value = "updatePdtUid")
    public String updatePdtUid(@RequestBody UidListGson Gson){
        try{
            for(int i=0;i<Gson.getId().size();i++){
                Panduanti panduanti = new Panduanti();
                panduanti.setId(Gson.getId().get(i));
                panduanti.setUid(Gson.getUid());
                pdtService.updataUid(panduanti);
            }
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
            jiandati.setUid(0);
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
    @RequestMapping(value = "queryJdtAll")
    public List<JdtGson> queryJdtAll(){
        List<Jiandati> jiandatis = jdtService.queryJdtAll();
        List<JdtGson> jdtGsons = new ArrayList<>();
        for (int i=0;i<jiandatis.size();i++){
            JdtGson jdtGson = new JdtGson();
            jdtGson.setId(jiandatis.get(i).getId());
            jdtGson.setTigan(jiandatis.get(i).getTigan());
            jdtGson.setCreateTime(jiandatis.get(i).getCreateTime());
            jdtGson.setNyd(jiandatis.get(i).getNyd());
            jdtGson.setUid(jiandatis.get(i).getUid());
            jdtGson.setDa(jiandatis.get(i).getDa());
            jdtGsons.add(jdtGson);
        }
        return jdtGsons;
    }
    @RequestMapping(value = "/deleteJdtById")
    public String deleteJdtById(@RequestBody IdList Gson){
        try{
            for (int i=0;i<Gson.getId().size();i++){
                jdtService.deleteJdtById(Gson.getId().get(i));
            }
            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }
    @RequestMapping(value = "updateJdt")
    public String updateJdt(@RequestBody JdtGson jdtGson){
        try {
            Jiandati jiandati = new Jiandati();
            jiandati.setId(jdtGson.getId());
            jiandati.setTigan(jdtGson.getTigan());
            jiandati.setDa(jdtGson.getDa());
            jiandati.setNyd(jdtGson.getNyd());
            jiandati.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            jdtService.updataById(jiandati);
            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }
    @RequestMapping(value = "updateJdtUid")
    public String updateJdtUid(@RequestBody UidListGson Gson){
        try {
            for (int i=0;i<Gson.getId().size();i++){
                Jiandati jiandati = new Jiandati();
                jiandati.setId(Gson.getId().get(i));
                jiandati.setUid(Gson.getUid());
                jdtService.updataUid(jiandati);
            }

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
            zongheti.setUid(0);
            zongheti.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            zhtService.saveZongheti(zongheti);
            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }
    @RequestMapping(value = "queryZhtAll")
    public List<ZhtGson> queryZhtAll(){
        List<Zongheti> zonghetis = zhtService.queryZhtAll();
        List<ZhtGson> zhtGsons = new ArrayList<>();
        for (int i=0;i<zonghetis.size();i++){
            ZhtGson zhtGson = new ZhtGson();
            zhtGson.setId(zonghetis.get(i).getId());
            zhtGson.setTigan(zonghetis.get(i).getTigan());
            zhtGson.setCreateTime(zonghetis.get(i).getCreateTime());
            zhtGson.setNyd(zonghetis.get(i).getNyd());
            zhtGson.setUid(zonghetis.get(i).getUid());
            String[] tm = zonghetis.get(i).getTm().split("\\|");
            List<ZhtTmGson> tmlist = new ArrayList<>();
            String[] da = zonghetis.get(i).getDa().split("\\|");
            for (int j=0;j<tm.length;j++){
                ZhtTmGson zhtTmGson = new ZhtTmGson();
                zhtTmGson.setTm(tm[j]);
                zhtTmGson.setDa(da[j]);
                tmlist.add(zhtTmGson);
            }
            zhtGson.setDa(tmlist);
            zhtGsons.add(zhtGson);
        }
        return zhtGsons;
    }
    @RequestMapping(value = "/deleteZhtById")
    public String deleteZhtById(@RequestBody IdList Gson){
        try{
            for (int i=0;i<Gson.getId().size();i++){
                zhtService.deleteZhtById(Gson.getId().get(i));
            }
            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }
    @RequestMapping(value = "updateZht")
    public String updateZht(@RequestBody ZhtGson zhtGson){
        try {
            Zongheti zongheti = new Zongheti();
            zongheti.setId(zhtGson.getId());
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
            zhtService.updataById(zongheti);
            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }
    @RequestMapping(value = "updateZhtUid")
    public String updateZhtUid(@RequestBody UidListGson Gson){
        try {
            for (int i = 0; i < Gson.getId().size(); i++) {
                Zongheti zongheti = new Zongheti();
                zongheti.setId(Gson.getId().get(i));
                zongheti.setUid(Gson.getUid());
                zhtService.updataUid(zongheti);
            }

            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }
    Integer xztxxNum = 0;
    Integer dxtxxNum = 0;
    int[] uploadnum = new int[6];
    @RequestMapping(value = "upload")
    public String upload(MultipartFile file){
        try {
            if (file.isEmpty()){
                return "文件为空";
            }
            String filename = file.getOriginalFilename();
            String[] filelast = filename.split("\\.");
            String filefrist = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String root = "H:/";
            String filedest = "upload/file/"+filefrist+filelast[1]+"/";
            File dest = new File(root+filedest+filename);
            if (!dest.getParentFile().exists()){
                dest.getParentFile().mkdir();
            }
            try {
                file.transferTo(dest);
            }catch (Exception e){
                e.printStackTrace();
                return e.toString();
            }
            File file1 = new File(root+filedest+filename);
            FileInputStream fileInputStream = new FileInputStream(file1);
            XWPFDocument xwpfDocument = new XWPFDocument(fileInputStream);
            XWPFParagraph xwpfParagraph;
            Iterator<XWPFParagraph> iterable = xwpfDocument.getParagraphsIterator();
            StringBuilder fileString = new StringBuilder();
            while (iterable.hasNext()){
                xwpfParagraph = iterable.next();
                fileString.append(xwpfParagraph.getText());
            }
            List<XWPFParagraph> paragraphList = xwpfDocument.getParagraphs();
            List<String> imageNameList = new ArrayList<>();
            List<String>lastParagraphTextList = new ArrayList<>();
            for(int i = 0;i < paragraphList.size();i++){
                List<String> imageBundleList = XWPFUtils.readImageInParagraph(paragraphList.get(i));
                if(CollectionUtils.isNotEmpty(imageBundleList)){
                    for(String pictureId:imageBundleList){
                        XWPFPictureData pictureData = xwpfDocument.getPictureDataByID(pictureId);
                        byte[] pic = pictureData.getData();
                        FileOutputStream fos = new FileOutputStream(root+filedest+pictureData.getFileName());
                        String imageName = pictureData.getFileName();
                        fos.write(pic);
                        String lastParagraphText = paragraphList.get(i-1).getParagraphText();
                        imageNameList.add(imageName);
                        lastParagraphTextList.add(lastParagraphText);
                    }
                }
            }
            for (int i = 0; i < imageNameList.size(); i++) {
                int imageIndex = fileString.indexOf(lastParagraphTextList.get(i));
                String img = "<img src=\"http://localhost:8011/"+filedest+imageNameList.get(i)+"\" title=\""+imageNameList.get(i)+"\" alt=\""+imageNameList.get(i)+"\" />";
                fileString.insert(imageIndex+lastParagraphTextList.get(i).length(),img);
            }

            int xztnum = xztupload(fileString.toString());
            int dxtnum = dxtupload(fileString.toString());
            int tktnum = tktupload(fileString.toString());
            int pdtnum = pdtupload(fileString.toString());
            int jdtnum = jdtupload(fileString.toString());
            int zhtnum = zhtupload(fileString.toString());
            uploadnum[0] = xztnum;
            uploadnum[1] = dxtnum;
            uploadnum[2] = tktnum;
            uploadnum[3] = pdtnum;
            uploadnum[4] = jdtnum;
            uploadnum[5] = zhtnum;

            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }
    @RequestMapping(value = "uploadnum")
    public void uploadnum(@RequestBody TmNumGson tmNumGson){
        xztxxNum = tmNumGson.getXzt();
        dxtxxNum = tmNumGson.getDxt();
    }
    @RequestMapping(value = "postuploadnum")
    public int[] postuploadnum(){
        return uploadnum;
    }
    public int xztupload(String fileString){
        Matcher allString = Pattern.compile("\\[选择题\\].*?\\[\\/选择题\\]").matcher(fileString.toString());
        String xztString = new String();
        if (allString.find()){
            xztString = allString.group();
        }
        Matcher tmString = Pattern.compile("[0-9]{1,3}.*?[A]:").matcher(xztString);
        List<String> tmList = new ArrayList<>();
        while (tmString.find()) {
            StringBuilder tm = new StringBuilder(tmString.group());
            tm.delete(0,2);
            tm.delete(tm.length()-2,tm.length());
            tm.append("</p>");
            tm.insert(0,"<p>");
            tmList.add(tm.toString());
        }
        List<String> xztxxlisttemp = new ArrayList<>();
        for (char i = 'A'; i <= 'A'+dxtxxNum; i++) {
            char temp = i;
            temp++;
            String pattern = "[" + i + "]:.*?[" + temp + "]:";

            Matcher m1 = Pattern.compile(pattern).matcher(xztString);
            while (m1.find()) {
                StringBuilder xx = new StringBuilder(m1.group());
                xx.delete(0,2);
                xx.delete(xx.length()-2,xx.length());
                xztxxlisttemp.add(xx.toString());
            }
            char temp1 = (char)(65+dxtxxNum-1);
            if (temp1==i) {
                pattern = "[" + i + "]:.*?答案";
                m1 = Pattern.compile(pattern).matcher(xztString);
                while (m1.find()) {
                    StringBuilder xx = new StringBuilder(m1.group());
                    xx.delete(0, 2);
                    xx.delete(xx.length() - 2, xx.length());
                    xztxxlisttemp.add(xx.toString());
                }
            }
        }
        String pattern = "答案:[A-Z]";
        Matcher matcherDa = Pattern.compile(pattern).matcher(xztString);
        List<String> dalist = new ArrayList<>();
        while (matcherDa.find()){
            StringBuilder da = new StringBuilder(matcherDa.group());
            da.delete(0,3);
            dalist.add(da.toString());
        }
        List<String> xxlist = new ArrayList<>();
        for (int i = 0; i < tmList.size(); i++) {
            String temp = "";
            for (int j = i; j < xztxxlisttemp.size(); j+=tmList.size()) {
                temp +=xztxxlisttemp.get(j)+"|";
            }
            StringBuilder stringBuilder = new StringBuilder(temp);
            stringBuilder.delete(stringBuilder.length()-1,stringBuilder.length());
            xxlist.add(stringBuilder.toString());
        }
        for (int i = 0; i < tmList.size(); i++) {
            Danxuanti danxuanti = new Danxuanti();
            danxuanti.setTigan(tmList.get(i));
            danxuanti.setXx(xxlist.get(i));
            danxuanti.setDa(dalist.get(i));
            danxuanti.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            danxuanti.setUid(0);
            try {
                dxtService.saveDanxuanti(danxuanti);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tmList.size();
    }
    public int dxtupload(String fileString){
        Matcher allString = Pattern.compile("\\[多选题\\].*?\\[\\/多选题\\]").matcher(fileString.toString());
        String dxtString = new String();
        if (allString.find()){
            dxtString = allString.group();
        }
        System.out.println(dxtString);
        Matcher tmString = Pattern.compile("[1-9]{1,3}.*?[A]:").matcher(dxtString);
        List<String> tmList = new ArrayList<>();
        while (tmString.find()) {
            StringBuilder tm = new StringBuilder(tmString.group());
            tm.delete(0,2);
            tm.delete(tm.length()-2,tm.length());
            tm.append("</p>");
            tm.insert(0,"<p>");
            tmList.add(tm.toString());
        }
        List<String> xztxxlisttemp = new ArrayList<>();
        for (char i = 'A'; i <= 'A'+dxtxxNum; i++) {
            char temp = i;
            temp++;
            String pattern = "[" + i + "]:.*?[" + temp + "]:";

            Matcher m1 = Pattern.compile(pattern).matcher(dxtString);
            while (m1.find()) {
                StringBuilder xx = new StringBuilder(m1.group());
                xx.delete(0,2);
                xx.delete(xx.length()-2,xx.length());
                xztxxlisttemp.add(xx.toString());
            }
            char temp1 = (char)(65+dxtxxNum-1);
            if (temp1==i) {
                pattern = "[" + i + "]:.*?答案";
                m1 = Pattern.compile(pattern).matcher(dxtString);
                while (m1.find()) {
                    StringBuilder xx = new StringBuilder(m1.group());
                    xx.delete(0, 2);
                    xx.delete(xx.length() - 2, xx.length());
                    xztxxlisttemp.add(xx.toString());
                }
            }
        }
        String pattern = "答案:.*?([0-9]{1,2}\\.|\\[\\/)";
        Matcher matcherDa = Pattern.compile(pattern).matcher(dxtString);
        List<String> dalist = new ArrayList<>();
        while (matcherDa.find()){
            StringBuilder da = new StringBuilder(matcherDa.group());
            da.delete(0,3);
            da.delete(da.length()-2,da.length());
            dalist.add(da.toString());
        }
        List<String> xxlist = new ArrayList<>();
        for (int i = 0; i < tmList.size(); i++) {
            String temp = "";
            for (int j = i; j < xztxxlisttemp.size(); j+=2) {
                temp +=xztxxlisttemp.get(j)+"|";
            }
            StringBuilder stringBuilder = new StringBuilder(temp);
            stringBuilder.delete(stringBuilder.length()-1,stringBuilder.length());
            xxlist.add(stringBuilder.toString());
        }
        for (int i = 0; i < tmList.size(); i++) {
            Duoxuanti duoxuanti = new Duoxuanti();
            duoxuanti.setTigan(tmList.get(i));
            duoxuanti.setXx(xxlist.get(i));
            duoxuanti.setDa(dalist.get(i));
            duoxuanti.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            duoxuanti.setUid(0);
            try {
                duoxtService.saveDuoxuanti(duoxuanti);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tmList.size();
    }
    public int tktupload(String fileString){
        Matcher allString = Pattern.compile("\\[填空题\\].*?\\[\\/填空题\\]").matcher(fileString.toString());
        String tktString = new String();
        if (allString.find()){
            tktString = allString.group();
        }
        System.out.println(tktString);
        Matcher tmString = Pattern.compile("[0-9]{1,2}\\..*?答案:").matcher(tktString);
        List<String> tmList = new ArrayList<>();
        while (tmString.find()) {
            StringBuilder tm = new StringBuilder(tmString.group());
            tm.delete(0,2);
            tm.delete(tm.length()-3,tm.length());
            tm.append("</p>");
            tm.insert(0,"<p>");
            tmList.add(tm.toString());
        }

        String pattern = "答案:.*?([0-9]{1,2}\\.|\\[\\/)";
        Matcher matcherDa = Pattern.compile(pattern).matcher(tktString);
        List<String> dalist = new ArrayList<>();
        while (matcherDa.find()){
            StringBuilder da = new StringBuilder(matcherDa.group());
            da.delete(0,3);
            da.delete(da.length()-2,da.length());
            dalist.add(da.toString());
        }
        for (int i = 0; i < tmList.size(); i++) {
            Tiankongti tiankongti = new Tiankongti();
            tiankongti.setTigan(tmList.get(i));
            tiankongti.setDa(dalist.get(i));
            tiankongti.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            tiankongti.setUid(0);
            try {
                tktService.saveTiankongti(tiankongti);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tmList.size();
    }
    public int pdtupload(String fileString){
        Matcher allString = Pattern.compile("\\[判断题\\].*?\\[\\/判断题\\]").matcher(fileString.toString());
        String pdtString = new String();
        if (allString.find()){
            pdtString = allString.group();
        }
        System.out.println(pdtString);
        Matcher tmString = Pattern.compile("[0-9]{1,2}\\..*?答案:").matcher(pdtString);
        List<String> tmList = new ArrayList<>();
        while (tmString.find()) {
            StringBuilder tm = new StringBuilder(tmString.group());
            tm.delete(0,2);
            tm.delete(tm.length()-3,tm.length());
            tm.append("</p>");
            tm.insert(0,"<p>");
            tmList.add(tm.toString());
        }

        String pattern = "答案:.*?([0-9]{1,2}\\.|\\[\\/)";
        Matcher matcherDa = Pattern.compile(pattern).matcher(pdtString);
        List<String> dalist = new ArrayList<>();
        while (matcherDa.find()){
            StringBuilder da = new StringBuilder(matcherDa.group());
            da.delete(0,3);
            da.delete(da.length()-2,da.length());
            dalist.add(da.toString());
        }
        for (int i = 0; i < tmList.size(); i++) {
            Panduanti panduanti = new Panduanti();
            panduanti.setTigan(tmList.get(i));
            panduanti.setDa(dalist.get(i));
            panduanti.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            panduanti.setUid(0);
            try {
                pdtService.savePanduanti(panduanti);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tmList.size();
    }
    public int jdtupload(String fileString){
        Matcher allString = Pattern.compile("\\[简答题\\].*?\\[\\/简答题\\]").matcher(fileString.toString());
        String jdtString = new String();
        if (allString.find()){
            jdtString = allString.group();
        }
        System.out.println(jdtString);
        Matcher tmString = Pattern.compile("[0-9]{1,2}\\..*?答案:").matcher(jdtString);
        List<String> tmList = new ArrayList<>();
        while (tmString.find()) {
            StringBuilder tm = new StringBuilder(tmString.group());
            tm.delete(0,2);
            tm.delete(tm.length()-3,tm.length());
            tm.append("</p>");
            tm.insert(0,"<p>");
            tmList.add(tm.toString());
        }

        String pattern = "答案:.*?([0-9]{1,2}\\.|\\[\\/)";
        Matcher matcherDa = Pattern.compile(pattern).matcher(jdtString);
        List<String> dalist = new ArrayList<>();
        while (matcherDa.find()){
            StringBuilder da = new StringBuilder(matcherDa.group());
            da.delete(0,3);
            da.delete(da.length()-2,da.length());
            dalist.add(da.toString());
        }
        for (int i = 0; i < tmList.size(); i++) {
            Jiandati jiandati = new Jiandati();
            jiandati.setTigan(tmList.get(i));
            jiandati.setDa(dalist.get(i));
            jiandati.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            jiandati.setUid(0);
            try {
                jdtService.saveJandati(jiandati);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tmList.size();
    }
    public int zhtupload(String fileString){
        Matcher allString = Pattern.compile("\\[综合题\\].*?\\[\\/综合题\\]").matcher(fileString.toString());
        String zhtString = new String();
        if (allString.find()){
            zhtString = allString.group();
        }
        System.out.println(zhtString);
        Matcher xtnumString = Pattern.compile("小题数量:[1-9]{1,3}").matcher(zhtString);
        List<Integer> xtnumlist = new ArrayList<>();
        while (xtnumString.find()){
            StringBuilder stringBuilder = new StringBuilder(xtnumString.group());
            stringBuilder.delete(0,5);
            Integer num = Integer.parseInt(stringBuilder.toString());
            xtnumlist.add(num);
        }
        Matcher tmString = Pattern.compile("[0-9]{1,2}\\..*?\\([0-9]\\)").matcher(zhtString);
        List<String> tmList = new ArrayList<>();
        while (tmString.find()) {
            StringBuilder tm = new StringBuilder(tmString.group());
            tm.delete(0,2);
            tm.delete(tm.length()-3,tm.length());
            tm.append("</p>");
            tm.insert(0,"<p>");
            tmList.add(tm.toString());
        }
        List<String> xtlisttemp = new ArrayList<>();
        Matcher xtString = Pattern.compile("\\([0-9]\\).*?答案:").matcher(zhtString);
        while (xtString.find()){
            StringBuilder xt = new StringBuilder(xtString.group());
            xt.delete(0,4);
            xt.delete(xt.length()-3,xt.length());
            xtlisttemp.add(xt.toString());
        }
        String pattern = "答案:.*?(\\([0-9]{1,2}\\)|\\[\\/综|[0-9]\\..|\\[小题)";
        Matcher matcherDa = Pattern.compile(pattern).matcher(zhtString);
        List<String> dalisttemp = new ArrayList<>();
        while (matcherDa.find()){
            StringBuilder da = new StringBuilder(matcherDa.group());
            da.delete(0,3);
            da.delete(da.length()-3,da.length());
            dalisttemp.add(da.toString());
        }
        List<String> xtlist = new ArrayList<>();
        List<String> dalist = new ArrayList<>();
        Integer num=0;
        for (int i = 0; i < tmList.size(); i++) {
            String xt = "";
            String da = "";
            for (int j = num; j <(xtnumlist.get(i)+num); j++) {
                xt += xtlisttemp.get(j)+"|";
                da += dalisttemp.get(j)+"|";
            }
            num+=xtnumlist.get(i);
            StringBuilder stringBuilder = new StringBuilder(xt);
            StringBuilder stringBuilder1 = new StringBuilder(da);
            stringBuilder.delete(stringBuilder.length()-1,stringBuilder.length());
            stringBuilder1.delete(stringBuilder1.length()-1,stringBuilder1.length());
            xtlist.add(stringBuilder.toString());
            dalist.add(stringBuilder1.toString());
        }
        for (int i = 0; i < tmList.size(); i++) {
            Zongheti zongheti = new Zongheti();
            zongheti.setTigan(tmList.get(i));
            zongheti.setDa(dalist.get(i));
            zongheti.setTm(xtlist.get(i));
            zongheti.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            zongheti.setUid(0);
            try {
                zhtService.saveZongheti(zongheti);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tmList.size();
    }
    @RequestMapping(value = "uploadexcel")
    public String uploadexce(MultipartFile file){
        try {
            if (file.isEmpty()){
                return "文件为空";
            }
            String filename = file.getOriginalFilename();
            String[] filelast = filename.split("\\.");
            String filefrist = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String root = "H:/";
            String filedest = "upload/file/"+filefrist+filelast[1]+"/";
            File dest = new File(root+filedest+filename);
            if (!dest.getParentFile().exists()){
                dest.getParentFile().mkdir();
            }
            try {
                file.transferTo(dest);
            }catch (Exception e){
                e.printStackTrace();
                return e.toString();
            }
            File file1 = new File(root+filedest+filename);
            String[] split = file1.getName().split("\\.");
            Workbook wb;
            if ("xls".equals(split[1])){
                FileInputStream fileInputStream = new FileInputStream(file1);
                wb = new HSSFWorkbook(fileInputStream);
            }else if ("xlsx".equals(split[1])){
                wb = new XSSFWorkbook(file1);
            }else {
                return "文件类型错误";
            }
            Sheet sheet = wb.getSheetAt(0);
            uploadnum[0] = xztuploadexcel(sheet);
            uploadnum[1] = dxtuploadexcel(sheet);
            uploadnum[2] = tktuploadexcel(sheet);
            uploadnum[3] = pdtuploadexcel(sheet);
            uploadnum[4] = jdtuploadexcel(sheet);
            uploadnum[5] =  zhtuploadexcel(sheet);
            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }
    public int xztuploadexcel(Sheet sheet) throws Exception {
        int firstRowIndex = sheet.getFirstRowNum();
        int lastRowIndes = sheet.getLastRowNum();
        List<String> tmlist = new ArrayList<>();
        List<String> xxlist = new ArrayList<>();
        List<String> dalist = new ArrayList<>();
        for (int i=firstRowIndex;i<=lastRowIndes;i++){
            Row row=sheet.getRow(i);
            if (row!=null){
                int firstCellIndex = row.getFirstCellNum();
                int lastCellIndex = row.getLastCellNum();
                for (int j = firstCellIndex; j < lastCellIndex; j++) {
                    Cell cell = row.getCell(j);
                    if (cell!=null){
                        if (cell.toString().equals("选择题")){
                            j++;
                            tmlist.add(row.getCell(j).toString());
                            j++;
                            for (int k = j; k < j+xztxxNum; k++) {
                                xxlist.add(row.getCell(k).toString());
                            }
                            j=j+xztxxNum;
                            dalist.add(row.getCell(j).toString());
                        }
                    }
                }
            }
        }
        for (int i = 0; i < tmlist.size(); i++) {
            Danxuanti danxuanti = new Danxuanti();
            danxuanti.setTigan("<p>"+tmlist.get(i)+"</p>");
            danxuanti.setDa(dalist.get(i));
            danxuanti.setUid(0);
            danxuanti.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            StringBuilder xx = new StringBuilder();
            for (int j = i*xztxxNum ; j <(i+1)*xztxxNum ; j++) {
                xx.append(xxlist.get(j)+"|");
            }
            xx.delete(xx.length()-1,xx.length());
            danxuanti.setXx(xx.toString());
            dxtService.saveDanxuanti(danxuanti);
        }
        return tmlist.size();
    }
    public int dxtuploadexcel(Sheet sheet) throws Exception {
        int firstRowIndex = sheet.getFirstRowNum();
        int lastRowIndes = sheet.getLastRowNum();
        List<String> tmlist = new ArrayList<>();
        List<String> xxlist = new ArrayList<>();
        List<String> dalist = new ArrayList<>();
        for (int i=firstRowIndex;i<=lastRowIndes;i++){
            Row row=sheet.getRow(i);
            if (row!=null){
                int firstCellIndex = row.getFirstCellNum();
                int lastCellIndex = row.getLastCellNum();
                for (int j = firstCellIndex; j < lastCellIndex; j++) {
                    Cell cell = row.getCell(j);
                    if (cell!=null){
                        if (cell.toString().equals("多选题")){
                            j++;
                            tmlist.add(row.getCell(j).toString());
                            j++;
                            for (int k = j; k < j+dxtxxNum; k++) {
                                xxlist.add(row.getCell(k).toString());
                            }
                            j=j+dxtxxNum;
                            dalist.add(row.getCell(j).toString());
                        }
                    }
                }
            }
        }
        for (int i = 0; i < tmlist.size(); i++) {
            Duoxuanti duoxuanti = new Duoxuanti();
            duoxuanti.setTigan("<p>"+tmlist.get(i)+"</p>");
            duoxuanti.setDa(dalist.get(i));
            duoxuanti.setUid(0);
            duoxuanti.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            StringBuilder xx = new StringBuilder();
            for (int j = i*dxtxxNum ; j <(i+1)*dxtxxNum ; j++) {
                xx.append(xxlist.get(j)+"|");
            }
            xx.delete(xx.length()-1,xx.length());
            duoxuanti.setXx(xx.toString());
            duoxtService.saveDuoxuanti(duoxuanti);
        }
        return tmlist.size();
    }
    public int tktuploadexcel(Sheet sheet) throws Exception {
        int firstRowIndex = sheet.getFirstRowNum();
        int lastRowIndes = sheet.getLastRowNum();
        List<String> tmlist = new ArrayList<>();
        List<String> dalist = new ArrayList<>();
        for (int i=firstRowIndex;i<=lastRowIndes;i++){
            Row row=sheet.getRow(i);
            if (row!=null){
                int firstCellIndex = row.getFirstCellNum();
                int lastCellIndex = row.getLastCellNum();
                for (int j = firstCellIndex; j < lastCellIndex; j++) {
                    Cell cell = row.getCell(j);
                    if (cell!=null){
                        if (cell.toString().equals("填空题")){
                            j++;
                            tmlist.add(row.getCell(j).toString());
                            j++;
                            dalist.add(row.getCell(j).toString());
                        }
                    }
                }
            }
        }
        for (int i = 0; i < tmlist.size(); i++) {
            Tiankongti tiankongti = new Tiankongti();
            tiankongti.setTigan("<p>"+tmlist.get(i)+"</p>");
            tiankongti.setDa(dalist.get(i));
            tiankongti.setUid(0);
            tiankongti.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            tktService.saveTiankongti(tiankongti);
        }
        return tmlist.size();
    }
    public int pdtuploadexcel(Sheet sheet) throws Exception {
        int firstRowIndex = sheet.getFirstRowNum();
        int lastRowIndes = sheet.getLastRowNum();
        List<String> tmlist = new ArrayList<>();
        List<String> dalist = new ArrayList<>();
        for (int i=firstRowIndex;i<=lastRowIndes;i++){
            Row row=sheet.getRow(i);
            if (row!=null){
                int firstCellIndex = row.getFirstCellNum();
                int lastCellIndex = row.getLastCellNum();
                for (int j = firstCellIndex; j < lastCellIndex; j++) {
                    Cell cell = row.getCell(j);
                    if (cell!=null){
                        if (cell.toString().equals("判断题")){
                            j++;
                            tmlist.add(row.getCell(j).toString());
                            j++;
                            dalist.add(row.getCell(j).toString());
                        }
                    }
                }
            }
        }
        for (int i = 0; i < tmlist.size(); i++) {
            Panduanti panduanti = new Panduanti();
            panduanti.setTigan("<p>"+tmlist.get(i)+"</p>");
            panduanti.setDa(dalist.get(i));
            panduanti.setUid(0);
            panduanti.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            pdtService.savePanduanti(panduanti);
        }
        return tmlist.size();
    }
    public int jdtuploadexcel(Sheet sheet) throws Exception {
        int firstRowIndex = sheet.getFirstRowNum();
        int lastRowIndes = sheet.getLastRowNum();
        List<String> tmlist = new ArrayList<>();
        List<String> dalist = new ArrayList<>();
        for (int i=firstRowIndex;i<=lastRowIndes;i++){
            Row row=sheet.getRow(i);
            if (row!=null){
                int firstCellIndex = row.getFirstCellNum();
                int lastCellIndex = row.getLastCellNum();
                for (int j = firstCellIndex; j < lastCellIndex; j++) {
                    Cell cell = row.getCell(j);
                    if (cell!=null){
                        if (cell.toString().equals("简答题")){
                            j++;
                            tmlist.add(row.getCell(j).toString());
                            j++;
                            dalist.add(row.getCell(j).toString());
                        }
                    }
                }
            }
        }
        for (int i = 0; i < tmlist.size(); i++) {
            Jiandati jiandati = new Jiandati();
            jiandati.setTigan("<p>"+tmlist.get(i)+"</p>");
            jiandati.setDa(dalist.get(i));
            jiandati.setUid(0);
            jiandati.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            jdtService.saveJandati(jiandati);
        }
        return tmlist.size();
    }
    public int zhtuploadexcel(Sheet sheet) throws Exception {
        int firstRowIndex = sheet.getFirstRowNum();
        int lastRowIndes = sheet.getLastRowNum();
        List<String> tmlist = new ArrayList<>();
        List<String> dalist = new ArrayList<>();
        List<String> xtlist = new ArrayList<>();
        List<Integer> xtnumlist = new ArrayList<>();
        for (int i=firstRowIndex;i<=lastRowIndes;i++){
            Row row=sheet.getRow(i);
            if (row!=null){
                int firstCellIndex = row.getFirstCellNum();
                int lastCellIndex = row.getLastCellNum();
                for (int j = firstCellIndex; j < lastCellIndex; j++) {
                    Cell cell = row.getCell(j);
                    if (cell!=null){
                        if (cell.toString().equals("综合题")){
                            j++;
                            xtnumlist.add(Integer.parseInt( new DecimalFormat("0").format(Double.valueOf(row.getCell(j).toString()))));
                            j++;
                            tmlist.add(row.getCell(j).toString());

                            for (int k = 0; k < xtnumlist.get(i); k++) {
                                j++;
                                xtlist.add(row.getCell(j).toString());
                                j++;
                                dalist.add(row.getCell(j).toString());
                            }
                        }
                    }
                }
            }
        }
        Integer temp = 0;
        for (int i = 0; i < tmlist.size(); i++) {
            Zongheti zongheti = new Zongheti();
            zongheti.setTigan("<p>"+tmlist.get(i)+"</p>");
            StringBuilder da = new StringBuilder();
            StringBuilder xt = new StringBuilder();
            for (int j = temp ; j <(temp+xtnumlist.get(i)) ; j++) {
                da.append(dalist.get(j)+"|");
                xt.append(xtlist.get(j)+"|");
            }
            temp+=xtnumlist.get(i);
            da.delete(da.length()-1,da.length());
            xt.delete(xt.length()-1,xt.length());
            zongheti.setTm(xt.toString());
            zongheti.setDa(da.toString());
            zongheti.setUid(0);
            zongheti.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            zhtService.saveZongheti(zongheti);
        }
        return tmlist.size();
    }
    @Autowired
    StudentService studentService;
    @RequestMapping(value = "queryAllStudent")
    public List<Student> queryAllStudent(){
        return studentService.queryAll();
    }
    ksxxGson ksxxGson = new ksxxGson();
    @RequestMapping(value = "getstudent")
    public void getstudent(@RequestBody ksxxGson Gson){
        if (Gson.getType().equals("1")){
            ksxxGson.setName(Gson.getName());
            ksxxGson.setKl(Gson.getKl());
            ksxxGson.setType(Gson.getType());
            ksxxGson.setKsxz(Gson.getKsxz());
        }else if (Gson.getType().equals("2")){
            List<Integer> ks = new ArrayList<>();
            for (int i = 0; i < Gson.getKs().size(); i++) {
                ks.add(Gson.getKs().get(i));
            }
            ksxxGson.setName(Gson.getName());
            ksxxGson.setType(Gson.getType());
            ksxxGson.setKsxz(Gson.getKsxz());
            ksxxGson.setKs(ks);
        }
    }
    @RequestMapping(value = "/queryXztByUid")
    @ResponseBody
    public List<DxtGson> queryXztByUid(){
        List<Danxuanti> danxuantis = dxtService.selectByUid();
        List<DxtGson> dxtGsons = new ArrayList<>();
        for (int i=0;i<danxuantis.size();i++){
            DxtGson dxtGson = new DxtGson();
            dxtGson.setTigan(danxuantis.get(i).getTigan());
            dxtGson.setCreateTime(danxuantis.get(i).getCreateTime());
            dxtGson.setDa(danxuantis.get(i).getDa());
            dxtGson.setId(danxuantis.get(i).getId());
            dxtGson.setNyd(danxuantis.get(i).getNyd());
            dxtGson.setUid(danxuantis.get(i).getUid());
            dxtGson.setFz(2);
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
    @RequestMapping(value = "queryDxtByUid")
    public List<DuoxtGson> queryDxtByUid(){
        List<Duoxuanti> duoxuantis = duoxtService.queryDxtByUid();
        List<DuoxtGson> duoxtGsons = new ArrayList<>();
        for (int i=0;i<duoxuantis.size();i++){
            DuoxtGson duoxtGson = new DuoxtGson();
            duoxtGson.setId(duoxuantis.get(i).getId());
            duoxtGson.setFz(5);
            duoxtGson.setTigan(duoxuantis.get(i).getTigan());
            duoxtGson.setCreateTime(duoxuantis.get(i).getCreatetime());
            duoxtGson.setNyd(duoxuantis.get(i).getNyd());
            duoxtGson.setUid(duoxuantis.get(i).getUid());
            String[] xx = duoxuantis.get(i).getXx().split("\\|");
            List<DuoxtXxGson> xxlist = new ArrayList<>();
            String[] da = duoxuantis.get(i).getDa().split("\\|");
            List<String> dalist = new ArrayList<>();
            for (int j=0;j<xx.length;j++){
                DuoxtXxGson duoxtXxGson = new DuoxtXxGson();
                duoxtXxGson.setText(xx[j]);
                xxlist.add(duoxtXxGson);
            }
            duoxtGson.setXx(xxlist);
            for (int j=0;j<da.length;j++){
                dalist.add(da[j]);
            }
            duoxtGson.setDa(dalist);
            duoxtGsons.add(duoxtGson);
        }
        return duoxtGsons;
    }
    @RequestMapping(value = "queryTktByUid")
    public List<TktGson> queryTktByUid(){
        List<Tiankongti> tiankongtis = tktService.queryTktByUid();
        List<TktGson> tktGsons = new ArrayList<>();
        for (int i=0;i<tiankongtis.size();i++){
            TktGson tktGson = new TktGson();
            tktGson.setId(tiankongtis.get(i).getId());
            tktGson.setFz(3);
            tktGson.setTigan(tiankongtis.get(i).getTigan());
            tktGson.setCreateTime(tiankongtis.get(i).getCreateTime());
            tktGson.setNyd(tiankongtis.get(i).getNyd());
            tktGson.setUid(tiankongtis.get(i).getUid());
            String[] da = tiankongtis.get(i).getDa().split("\\|");
            List<TktDaGson> dalist = new ArrayList<>();
            for (int j=0;j<da.length;j++){
                TktDaGson tktDaGson = new TktDaGson();
                tktDaGson.setText(da[j]);
                dalist.add(tktDaGson);
            }
            tktGson.setDa(dalist);
            tktGsons.add(tktGson);
        }
        return tktGsons;
    }
    @RequestMapping(value = "queryPdtByUid")
    public List<PdtGson> queryPdtByUid(){
        List<Panduanti> panduantis = pdtService.queryPdtByUid();
        List<PdtGson> pdtGsons = new ArrayList<>();
        for (int i=0;i<panduantis.size();i++){
            PdtGson pdtGson = new PdtGson();
            pdtGson.setFz(1);
            pdtGson.setId(panduantis.get(i).getId());
            pdtGson.setTigan(panduantis.get(i).getTigan());
            pdtGson.setCreateTime(panduantis.get(i).getCreateTime());
            pdtGson.setNyd(panduantis.get(i).getNyd());
            pdtGson.setUid(panduantis.get(i).getUid());
            pdtGson.setDa(panduantis.get(i).getDa());
            pdtGsons.add(pdtGson);
        }
        return pdtGsons;
    }
    @RequestMapping(value = "queryJdtByUid")
    public List<JdtGson> queryJdtByUid(){
        List<Jiandati> jiandatis = jdtService.queryJdtByUid();
        List<JdtGson> jdtGsons = new ArrayList<>();
        for (int i=0;i<jiandatis.size();i++){
            JdtGson jdtGson = new JdtGson();
            jdtGson.setFz(5);
            jdtGson.setId(jiandatis.get(i).getId());
            jdtGson.setTigan(jiandatis.get(i).getTigan());
            jdtGson.setCreateTime(jiandatis.get(i).getCreateTime());
            jdtGson.setNyd(jiandatis.get(i).getNyd());
            jdtGson.setUid(jiandatis.get(i).getUid());
            jdtGson.setDa(jiandatis.get(i).getDa());
            jdtGsons.add(jdtGson);
        }
        return jdtGsons;
    }
    @RequestMapping(value = "queryZhtByUid")
    public List<ZhtGson> queryZhtByUid(){
        List<Zongheti> zonghetis = zhtService.queryZhtByUid();
        List<ZhtGson> zhtGsons = new ArrayList<>();
        for (int i=0;i<zonghetis.size();i++){
            ZhtGson zhtGson = new ZhtGson();
            zhtGson.setFz(10);
            zhtGson.setId(zonghetis.get(i).getId());
            zhtGson.setTigan(zonghetis.get(i).getTigan());
            zhtGson.setCreateTime(zonghetis.get(i).getCreateTime());
            zhtGson.setNyd(zonghetis.get(i).getNyd());
            zhtGson.setUid(zonghetis.get(i).getUid());
            String[] tm = zonghetis.get(i).getTm().split("\\|");
            List<ZhtTmGson> tmlist = new ArrayList<>();
            String[] da = zonghetis.get(i).getDa().split("\\|");
            for (int j=0;j<tm.length;j++){
                ZhtTmGson zhtTmGson = new ZhtTmGson();
                zhtTmGson.setTm(tm[j]);
                zhtTmGson.setDa(da[j]);
                tmlist.add(zhtTmGson);
            }
            zhtGson.setDa(tmlist);
            zhtGsons.add(zhtGson);
        }
        return zhtGsons;
    }
    @Autowired
    SjService sjService;
    @RequestMapping(value = "getSj")
    public String getSj(@RequestBody SjGson Gson){
        try{
            float zf = 0;
            StringBuilder xzt = new StringBuilder();
            if (!Gson.getXzt().isEmpty()) {
                for (int i = 0; i < Gson.getXzt().size(); i++) {
                    xzt.append(Gson.getXzt().get(i).getId() + "," + Gson.getXzt().get(i).getFz() + "|");
                    zf+=Gson.getXzt().get(i).getFz();
                }
                xzt.delete(xzt.length() - 1, xzt.length());
            }
            StringBuilder dxt = new StringBuilder();
            if (!Gson.getDxt().isEmpty()) {
                for (int i = 0; i < Gson.getDxt().size(); i++) {
                    dxt.append(Gson.getDxt().get(i).getId() + "," + Gson.getDxt().get(i).getFz() + "|");
                    zf+=Gson.getDxt().get(i).getFz();
                }
                dxt.delete(dxt.length() - 1, dxt.length());
            }
            StringBuilder tkt = new StringBuilder();
            if (!Gson.getTkt().isEmpty()) {
                for (int i = 0; i < Gson.getTkt().size(); i++) {
                    tkt.append(Gson.getTkt().get(i).getId() + "," + Gson.getTkt().get(i).getFz() + "|");
                    zf+=Gson.getTkt().get(i).getFz();
                }
                tkt.delete(tkt.length() - 1, tkt.length());
            }
            StringBuilder pdt = new StringBuilder();
            if (!Gson.getPdt().isEmpty()) {
                for (int i = 0; i < Gson.getPdt().size(); i++) {
                    pdt.append(Gson.getPdt().get(i).getId() + "," + Gson.getPdt().get(i).getFz() + "|");
                    zf+=Gson.getPdt().get(i).getFz();
                }
                pdt.delete(pdt.length() - 1, pdt.length());
            }
            StringBuilder jdt = new StringBuilder();
            if (!Gson.getJdt().isEmpty()) {
                for (int i = 0; i < Gson.getJdt().size(); i++) {
                    jdt.append(Gson.getJdt().get(i).getId() + "," + Gson.getJdt().get(i).getFz() + "|");
                    zf+=Gson.getJdt().get(i).getFz();
                }
                jdt.delete(jdt.length() - 1, jdt.length());
            }
            StringBuilder zht = new StringBuilder();
            if (!Gson.getZht().isEmpty()) {
                for (int i = 0; i < Gson.getZht().size(); i++) {
                    zht.append(Gson.getZht().get(i).getId() + "," + Gson.getZht().get(i).getFz() + "|");
                    zf+=Gson.getZht().get(i).getFz();
                }
                zht.delete(zht.length() - 1, zht.length());
            }
            Sj sj = new Sj();
            if (ksxxGson.getType().equals("1")){
                sj.setXzt(xzt.toString());
                sj.setDxt(dxt.toString());
                sj.setTkt(tkt.toString());
                sj.setPdt(pdt.toString());
                sj.setJdt(jdt.toString());
                sj.setZht(zht.toString());
                sj.setName(ksxxGson.getName());
                sj.setType(ksxxGson.getType());
                sj.setKsxz(ksxxGson.getKsxz());
                sj.setKl(ksxxGson.getKl());
                sj.setZf(zf+"");
            }
            if (ksxxGson.getType().equals("2")){
                sj.setXzt(xzt.toString());
                sj.setDxt(dxt.toString());
                sj.setTkt(tkt.toString());
                sj.setPdt(pdt.toString());
                sj.setJdt(jdt.toString());
                sj.setZht(zht.toString());
                sj.setName(ksxxGson.getName());
                sj.setType(ksxxGson.getType());
                sj.setKsxz(ksxxGson.getKsxz());
                StringBuilder ks = new StringBuilder();
                for (int i = 0; i < ksxxGson.getKs().size(); i++) {
                    ks.append(ksxxGson.getKs().get(i)+"|");
                }
                ks.delete(ks.length()-1,ks.length());
                sj.setKs(ks.toString());
                sj.setZf(zf+"");
            }
            int ID =  sjService.saveSj(sj);
            return ID+"";
        }catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }
    @RequestMapping(value = "getSjContent")
    public SjGson getSjConten(Integer ID){
        Sj sj = sjService.selectById(ID);
        float num =0;
        String xztIdFzString = null;
        List<Integer> xztId = new ArrayList<>();
        List<Integer> xztFz = new ArrayList<>();
        String dxtIdFzString = null;
        List<Integer> dxtId = new ArrayList<>();
        List<Integer> dxtFz = new ArrayList<>();
        String tktIdFzString = null;
        List<Integer> tktId = new ArrayList<>();
        List<Integer> tktFz = new ArrayList<>();
        String pdtIdFzString = null;
        List<Integer> pdtId = new ArrayList<>();
        List<Integer> pdtFz = new ArrayList<>();
        String jdtIdFzString = null;
        List<Integer> jdtId = new ArrayList<>();
        List<Integer> jdtFz = new ArrayList<>();
        String zhtIdFzString = null;
        List<Integer> zhtId = new ArrayList<>();
        List<Integer> zhtFz = new ArrayList<>();
        if (!sj.getXzt().isEmpty()){
             xztIdFzString = sj.getXzt();
        }
        if (!sj.getDxt().isEmpty()){
            dxtIdFzString = sj.getDxt();
        }
        if (!sj.getTkt().isEmpty()){
            tktIdFzString = sj.getTkt();
        }
        if (!sj.getPdt().isEmpty()){
            pdtIdFzString = sj.getPdt();
        }
        if (!sj.getJdt().isEmpty()){
            jdtIdFzString = sj.getJdt();
        }
        if (!sj.getZht().isEmpty()){
            zhtIdFzString = sj.getZht();
        }

        if (xztIdFzString!=null){
            String[] temp = xztIdFzString.split("\\|");
            for (int i = 0; i < temp.length; i++) {
                String[] temp1 = temp[i].split(",");
                xztId.add(Integer.parseInt(temp1[0]));
                xztFz.add(Integer.parseInt(temp1[1]));
            }
        }
        if (dxtIdFzString != null){
            String[] temp = dxtIdFzString.split("\\|");
            for (int i = 0; i < temp.length; i++) {
                String[] temp1 = temp[i].split(",");
                dxtId.add(Integer.parseInt(temp1[0]));
                dxtFz.add(Integer.parseInt(temp1[1]));
            }
        }
        if (tktIdFzString!=null){
            String[] temp = tktIdFzString.split("\\|");
            for (int i = 0; i < temp.length; i++) {
                String[] temp1 = temp[i].split(",");
                tktId.add(Integer.parseInt(temp1[0]));
                tktFz.add(Integer.parseInt(temp1[1]));
            }
        }
        if (pdtIdFzString!=null){
            String[] temp = pdtIdFzString.split("\\|");
            for (int i = 0; i < temp.length; i++) {
                String[] temp1 = temp[i].split(",");
                pdtId.add(Integer.parseInt(temp1[0]));
                pdtFz.add(Integer.parseInt(temp1[1]));
            }
        }
        if (jdtIdFzString!=null){
            String[] temp = jdtIdFzString.split("\\|");
            for (int i = 0; i < temp.length; i++) {
                String[] temp1 = temp[i].split(",");
                jdtId.add(Integer.parseInt(temp1[0]));
                jdtFz.add(Integer.parseInt(temp1[1]));
            }
        }
        if (zhtIdFzString!=null){
            String[] temp = zhtIdFzString.split("\\|");
            for (int i = 0; i < temp.length; i++) {
                String[] temp1 = temp[i].split(",");
                zhtId.add(Integer.parseInt(temp1[0]));
                zhtFz.add(Integer.parseInt(temp1[1]));
            }
        }
        SjGson gson = new SjGson();
        List<DxtGson> dxtGsons = new ArrayList<>();
        for (int i = 0; i < xztId.size(); i++) {
            Danxuanti danxuanti = dxtService.selectById(xztId.get(i));
            DxtGson dxtGson = new DxtGson();
            dxtGson.setTigan(danxuanti.getTigan());
            dxtGson.setId(danxuanti.getId());
            dxtGson.setFz(xztFz.get(i));
            num+=xztFz.get(i);
            dxtGson.setDa(danxuanti.getDa());
            String[] xx = danxuanti.getXx().split("\\|");
            List<DxtXxGson> dxtXxGsonList = new ArrayList<>();
            for (int j=0;j<xx.length;j++){
                DxtXxGson dxtXxGson = new DxtXxGson();
                dxtXxGson.setText(xx[j]);
                dxtXxGsonList.add(dxtXxGson);
            }
            dxtGson.setXx(dxtXxGsonList);
            dxtGsons.add(dxtGson);
        }
        gson.setXzt(dxtGsons);
        List<DuoxtGson> duoxtGsons = new ArrayList<>();
        for (int i = 0; i < dxtId.size(); i++) {
            Duoxuanti duoxuanti = duoxtService.selectById(dxtId.get(i));
            DuoxtGson duoxtGson = new DuoxtGson();
            duoxtGson.setId(duoxuanti.getId());
            duoxtGson.setFz(dxtFz.get(i));
            num+=dxtFz.get(i);
            duoxtGson.setTigan(duoxuanti.getTigan());
            String[] xx = duoxuanti.getXx().split("\\|");
            List<DuoxtXxGson> xxlist = new ArrayList<>();
            String[] da = duoxuanti.getDa().split("\\|");
            List<String> dalist = new ArrayList<>();
            for (int j=0;j<xx.length;j++){
                DuoxtXxGson duoxtXxGson = new DuoxtXxGson();
                duoxtXxGson.setText(xx[j]);
                xxlist.add(duoxtXxGson);
            }
            duoxtGson.setXx(xxlist);
            for (int j=0;j<da.length;j++){
                dalist.add(da[j]);
            }
            duoxtGson.setDa(dalist);
            duoxtGsons.add(duoxtGson);
        }
        gson.setDxt(duoxtGsons);
        List<TktGson> tktGsons = new ArrayList<>();
        for (int i=0;i<tktId.size();i++){
            Tiankongti tiankongti = tktService.selectById(tktId.get(i));
            TktGson tktGson = new TktGson();
            tktGson.setFz(tktFz.get(i));
            num+=tktFz.get(i);
            tktGson.setId(tiankongti.getId());
            tktGson.setTigan(tiankongti.getTigan());
            String[] da = tiankongti.getDa().split("\\|");
            List<TktDaGson> dalist = new ArrayList<>();
            for (int j=0;j<da.length;j++){
                TktDaGson tktDaGson = new TktDaGson();
                tktDaGson.setText(da[j]);
                dalist.add(tktDaGson);
            }
            tktGson.setDa(dalist);
            tktGsons.add(tktGson);
        }
        gson.setTkt(tktGsons);
        List<PdtGson> pdtGsons = new ArrayList<>();
        for (int i=0;i<pdtId.size();i++){
            Panduanti panduanti = pdtService.selectById(pdtId.get(i));
            PdtGson pdtGson = new PdtGson();
            pdtGson.setFz(pdtFz.get(i));
            num+=pdtFz.get(i);
            pdtGson.setId(panduanti.getId());
            pdtGson.setTigan(panduanti.getTigan());
            pdtGson.setDa(panduanti.getDa());
            pdtGsons.add(pdtGson);
        }
        gson.setPdt(pdtGsons);
        List<JdtGson> jdtGsons = new ArrayList<>();
        for (int i=0;i<jdtId.size();i++){
            Jiandati jiandati = jdtService.selectById(jdtId.get(i));
            JdtGson jdtGson = new JdtGson();
            jdtGson.setFz(jdtFz.get(i));
            num+=jdtFz.get(i);
            jdtGson.setId(jiandati.getId());
            jdtGson.setTigan(jiandati.getTigan());
            jdtGson.setDa(jiandati.getDa());
            jdtGsons.add(jdtGson);
        }
        gson.setJdt(jdtGsons);
        List<ZhtGson> zhtGsons = new ArrayList<>();
        for (int i=0;i<zhtId.size();i++){
            Zongheti zongheti = zhtService.selectById(zhtId.get(i));
            ZhtGson zhtGson = new ZhtGson();
            zhtGson.setFz(zhtFz.get(i));
            num+=zhtFz.get(i);
            zhtGson.setId(zongheti.getId());
            zhtGson.setTigan(zongheti.getTigan());
            String[] tm = zongheti.getTm().split("\\|");
            List<ZhtTmGson> tmlist = new ArrayList<>();
            String[] da = zongheti.getDa().split("\\|");
            for (int j=0;j<tm.length;j++){
                ZhtTmGson zhtTmGson = new ZhtTmGson();
                zhtTmGson.setTm(tm[j]);
                zhtTmGson.setDa(da[j]);
                tmlist.add(zhtTmGson);
            }
            zhtGson.setDa(tmlist);
            zhtGsons.add(zhtGson);
        }
        gson.setZht(zhtGsons);
        gson.setAllfz(num);
        gson.setName(sj.getName());
        return gson;
    }
    @RequestMapping(value = "querySjAll")
    public List<Sj> querySjAll(){
        return sjService.selectAll();
    }
    @RequestMapping(value = "kslogin")
    public String kslogin(@RequestBody ksloginGson gson){
        try{
            int temp = 0;
            Sj sj =  sjService.selectById(gson.getSjid());
            String[] ksuser = sj.getKs().split("\\|");
            for (int i = 0; i < ksuser.length; i++) {
                if (ksuser[i].equals(gson.getUser())){
                    temp++;
                    Student student = studentService.queryByUser(gson.getUser());
                    if (student.getUser().equals(gson.getUser())&&student.getPass().equals(gson.getPass())){
                        return "ture";
                    }else {
                        return "请检查账号密码是否正确";
                    }
                }
            }
            if (temp ==0){
                return "考生不在此场考试内";
            }else {
                return "ture";
            }
        }catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }
    @Autowired
    KsdaService ksdaService;
    @RequestMapping(value = "submitksda")
    public ksresult submitksda(@RequestBody sjda sjda){
        Sj sj = sjService.selectById(sjda.getKsid());
        ksresult ksresult = new ksresult();
        Ksda ksda = new Ksda();
        ksda.setMf(sj.getZf());
        ksda.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        ksda.setName(sjda.getUser());
        ksda.setSjid(sjda.getKsid());
        float num = 0;
        float xztfs = 0;
        StringBuilder ksxzt = new StringBuilder();
        for (int i = 0; i < sjda.getXzt().size(); i++) {
            Danxuanti danxuanti = dxtService.selectById(sjda.getXzt().get(i).getId());
            ksxzt.append(sjda.getXzt().get(i).getId()+",");
            if (danxuanti.getDa().equals(sjda.getXzt().get(i).getDa())){
                num+=sjda.getXzt().get(i).getFz();
                xztfs+=sjda.getXzt().get(i).getFz();
                ksxzt.append(sjda.getXzt().get(i).getFz()+"|");
            }else {
                ksxzt.append(0+"|");
            }
        }
        if (ksxzt.length()>0){
            ksxzt.delete(ksxzt.length()-1,ksxzt.length());
        }
        ksda.setXzt(ksxzt.toString());
        ksresult.setXzt(xztfs);
        float dxtfs = 0;
        StringBuilder ksdxt = new StringBuilder();
        for (int i = 0; i < sjda.getDxt().size(); i++) {
            Duoxuanti duoxuanti = duoxtService.selectById(sjda.getDxt().get(i).getId());
            ksdxt.append(sjda.getDxt().get(i).getId() + ",");
            String[] da = duoxuanti.getDa().split("\\|");
            float temp = sjda.getDxt().get(i).getFz() / (float) da.length;
            float temp1 = 0;
            for (int j = 0; j < da.length; j++) {
                for (int k = 0; k < sjda.getDxt().get(i).getDa().size(); k++) {
                    if (da[j].equals(sjda.getDxt().get(i).getDa().get(k))) {
                        num += temp;
                        dxtfs += temp;
                        temp1 += temp;
                    }
                }
                ksdxt.append(temp1 + "|");
            }
        }
        if (ksdxt.length()>0){
            ksdxt.delete(ksdxt.length()-1,ksdxt.length());
        }
        ksda.setDxt(ksdxt.toString());
        ksresult.setDxt(dxtfs);
        float tktfs = 0;
        StringBuilder kstkt = new StringBuilder();
        for (int i = 0; i < sjda.getTkt().size(); i++) {
            Tiankongti tiankongti = tktService.selectById(sjda.getTkt().get(i).getId());
            kstkt.append(sjda.getTkt().get(i).getId()+",");
            String[] dalist = tiankongti.getDa().split("\\|");
            String[][] da = new String[dalist.length][];
            for (int j = 0; j < dalist.length; j++) {
                da[j] =  dalist[j].split(",");
            }
            float temp = sjda.getTkt().get(i).getFz()/(float)dalist.length;
            float temp1 = 0;
            for (int j = 0; j < dalist.length; j++) {
                for (int k = 0; k < da[j].length; k++) {
                    if (da[j][k].equals(sjda.getTkt().get(i).getDa().get(j).getDa())){
                        num+=temp;
                        tktfs+=temp;
                        temp1+=temp;
                    }
                }
            }
            kstkt.append(temp1+"|");
        }
        if (kstkt.length()>0){
            kstkt.delete(kstkt.length()-1,kstkt.length());
        }
        ksda.setTkt(kstkt.toString());
        ksresult.setTkt(tktfs);
        float pdtfs = 0;
        StringBuilder kspdt = new StringBuilder();
        for (int i = 0; i < sjda.getPdt().size(); i++) {
            Panduanti panduanti = pdtService.selectById(sjda.getPdt().get(i).getId());
            kspdt.append(sjda.getPdt().get(i).getId()+",");
            if (panduanti.getDa().equals(sjda.getPdt().get(i).getDa())){
                num+=sjda.getPdt().get(i).getFz();
                pdtfs+=sjda.getPdt().get(i).getFz();
                kspdt.append(sjda.getPdt().get(i).getFz()+"|");
            }else {
                kspdt.append(0+"|");
            }
        }
        if (kspdt.length()>0){
            kspdt.delete(kspdt.length()-1,kspdt.length());
        }
        ksda.setPdt(kspdt.toString());
        ksresult.setPdt(pdtfs);
        float jdtfs = 0;
        StringBuilder ksjdt = new StringBuilder();
        for (int i = 0; i < sjda.getJdt().size(); i++) {
            Jiandati jiandati = jdtService.selectById(sjda.getJdt().get(i).getId());
            ksjdt.append(sjda.getJdt().get(i).getId()+",");
            float temp = levenshtein.levenshtein(jiandati.getDa(),sjda.getJdt().get(i).getDa());
            num+=sjda.getJdt().get(i).getFz()*temp;
            jdtfs +=sjda.getJdt().get(i).getFz()*temp;
            ksjdt.append(sjda.getJdt().get(i).getFz()*temp+"|");
        }
        if (ksjdt.length()>0){
            ksjdt.delete(ksjdt.length()-1,ksjdt.length());
        }
        ksda.setJdt(ksjdt.toString());
        ksresult.setJdt(jdtfs);
        float zhtfs = 0;
        StringBuilder kszht = new StringBuilder();
        for (int i = 0; i < sjda.getZht().size(); i++) {
            Zongheti zongheti = zhtService.selectById(sjda.getZht().get(i).getId());
            kszht.append(sjda.getZht().get(i).getId()+",");
            String[] da = zongheti.getDa().split("\\|");
            float temp1 = 0;
            for (int j = 0; j < da.length; j++) {
                float temp = levenshtein.levenshtein(da[j],sjda.getZht().get(i).getDa().get(j).getDa());
                num+=sjda.getZht().get(i).getFz()*temp;
                zhtfs+=sjda.getZht().get(i).getFz()*temp;
                temp1+=sjda.getZht().get(i).getFz()*temp;
            }
            kszht.append(temp1+"|");
        }
        if (kszht.length()>0){
            kszht.delete(kszht.length()-1,kszht.length());
        }
        ksda.setZht(kszht.toString());
        ksresult.setZht(zhtfs);
        ksresult.setAllfz(num);
        ksda.setFs(num+"");
        ksdaService.saveKsda(ksda);
        return ksresult;
    }
    @RequestMapping(value = "sjDeleteById")
    public String sjDeleteById(Integer id){
        try {
            sjService.deleteById(id);
            return "ture";
        }catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }
    @RequestMapping(value = "TmNum")
    public AllTmNumGson TmNum(){
        AllTmNumGson allTmNumGson = new AllTmNumGson();
        allTmNumGson.setXzt(dxtService.selectNum());
        allTmNumGson.setDxt(duoxtService.selectNum());
        allTmNumGson.setTkt(tktService.selectNum());
        allTmNumGson.setPdt(pdtService.selectNum());
        allTmNumGson.setJdt(jdtService.selectNum());
        allTmNumGson.setZht(zhtService.selectNum());
        return allTmNumGson;
    }
    @RequestMapping(value = "getksda")
    public List<KsdaGson> getksda(int id){
        List<Ksda> ksdas = ksdaService.slectById(id);
        List<KsdaGson> ksdaGsons = new ArrayList<>();
        for (int i = 0; i < ksdas.size(); i++) {
            KsdaGson ksdaGson = new KsdaGson();
            ksdaGson.setFs(Float.parseFloat(ksdas.get(i).getFs()));
            ksdaGson.setName(ksdas.get(i).getName());
            ksdaGson.setMf(Float.parseFloat(ksdas.get(i).getMf()));
            ksdaGsons.add(ksdaGson);
        }

        return ksdaGsons;
    }
}
