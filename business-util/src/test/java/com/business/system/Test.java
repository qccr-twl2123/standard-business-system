package com.business.system;

import com.business.system.facetech.jni.FDJNIFactory;
import com.business.system.facetech.jni.FDServiceJNI;

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
