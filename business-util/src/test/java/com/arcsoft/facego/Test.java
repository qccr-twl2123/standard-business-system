package com.arcsoft.facego;

import com.arcsoft.facego.facetech.jni.FDJNIFactory;
import com.arcsoft.facego.facetech.jni.FDServiceJNI;
import com.arcsoft.facego.util.ConfigUtil;

public class Test {

    public static void main(String[] args) {
      try{
          FDServiceJNI fdServiceJNI = FDJNIFactory.borrowObject();
          System.out.println(fdServiceJNI.FSDK_FD_GetVersion_Ext());

      }catch (Exception ex) {
          System.out.printf(ex.getMessage());
      }
    }
}
