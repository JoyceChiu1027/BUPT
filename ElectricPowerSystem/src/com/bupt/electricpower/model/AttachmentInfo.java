package com.bupt.electricpower.model;

import java.io.File;
import java.util.Date;
import java.util.HashMap;

import android.util.Log;

public class AttachmentInfo {
	 public static final int NOT_UPLOAD = 1, PREPARE_UPLOAD = 2, UP_LOADING = 3,
			  UP_LOADING_SUCCESS = 4, UP_LOADING_FAIL = 5, NOT_DOWNLOAD = 6,  
           PREPARE_DOWNLOAD = 7, DOWN_LOADING = 8, DOWN_LOADING_SUCCESS = 9,  
           DOWN_LOADING_FAIL = 10, ALREADY_SAVE = 11;  
   // 本地有，服务器也已保存，则为已保存状态  
	  static {  
	        addToStateMap(1, "未上传");  
	        addToStateMap(2, "准备上传");  
	        addToStateMap(3, "上传中：");  
	        addToStateMap(4, "上传完成");  
	        addToStateMap(5, "上传失败");  
	        addToStateMap(6, "下载");  
	        addToStateMap(7, "准备下载");  
	        addToStateMap(8, "下载中:");  
	        addToStateMap(9, "下载完成");  
	        addToStateMap(10, "下载失败");  
	        addToStateMap(11, "已和服务器同步");  
	    }    
   public static HashMap<Integer, String> stateMap;  
   // 服务器返回属性  
   public String id;  
   public String fileName; // 文件名  
   public String name;
   public String path; // 文件路径，服务器路径  
   public long size; // 文件大小，字节  
   public String time;
   public int resId;
   // 自定义属性  
   public int state; //文件状态  
   public int progress = 0; // 操作进度  
   public String fileNativePath; // 文件在本地的地址  
   public String content;
   public static void addToStateMap(int state, String stateText) {  
       if (stateMap == null) {  
           stateMap = new HashMap<Integer, String>();  
       }  
       stateMap.put(state, stateText);  
   }  
 
   public AttachmentInfo() {  
 
   }  
 
   public AttachmentInfo(String name, String path, long size,  
           String modifyTime, int state, int progress, String fileNativePath) {  
       super();  
       this.fileName = name;  
       this.path = path;  
       this.size = size;  
       this.time = modifyTime;  
       this.state = state;  
       this.progress = progress;  
       this.fileNativePath = fileNativePath;  
   }  
 
   public AttachmentInfo(File file, int state) {  
       if (file.exists()) {  
           // 如果文件存在  
           this.state = state;  
           this.fileName = file.getName();  
           this.fileNativePath = file.getAbsolutePath();  
           this.size = file.length();  
           try {  
               this.time =new Date(file  
                       .lastModified()).toString();/* ConvertUtil.dateToString(new Date(file  
                       .lastModified()))*/
           } catch (Exception e) {  
               Log.d("根据文件信息创建附件转化时间时不正确", e.getMessage());  
           }  
       }  
 
   }  
}
