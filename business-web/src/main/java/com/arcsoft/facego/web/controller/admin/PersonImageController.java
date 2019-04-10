package com.arcsoft.facego.web.controller.admin;

import com.alibaba.fastjson.JSON;
import com.arcsoft.facego.StringUtil;
import com.arcsoft.facego.facetech.model.DetectResult;
import com.arcsoft.facego.facetech.model.ExtractResult;
import com.arcsoft.facego.facetech.model.ImageInfo;
import com.arcsoft.facego.facetech.service.FSDKFD;
import com.arcsoft.facego.facetech.service.FSDKFR;
import com.arcsoft.facego.util.ImageFileUtil;
import com.arcsoft.facego.util.bjui.Bjui;
import com.arcsoft.facego.web.bean.vo.PersonInfoVO;
import com.arcsoft.facego.web.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arcsoft.facego.web.service.PersonImageService;
import com.arcsoft.facego.web.bean.qo.PersonImageQO;
import com.arcsoft.facego.util.base.BaseController;

import java.io.File;

/**
 * 用户图片 
 *
 * @author mark
 * @since 2018-07-04
 */
@Controller
@RequestMapping(value = "/admin/personImage")
public class PersonImageController extends BaseController {

	private final static String TARGETID = "admin-personImage";

	@Autowired
	private PersonImageService service;
	@Autowired
	private PersonInfoService serviceInfo;
	
	@RequestMapping(value = "/list")
	public void list(@RequestParam(value = "pageCurrent", defaultValue = "1") int pageCurrent, @RequestParam(value = "pageSize", defaultValue = "20") int pageSize, @ModelAttribute PersonImageQO qo, ModelMap modelMap){
		modelMap.put("page", service.listForPage(pageCurrent, pageSize, qo));
		modelMap.put("pageCurrent", pageCurrent);
		modelMap.put("pageSize", pageSize);
		modelMap.put("bean", qo);
	}
	
	@ResponseBody
	@RequestMapping(value = "/save")
	public Bjui save(@ModelAttribute PersonImageQO qo){
		if(qo.getPersonId()< 1) {
			return errorObject("人员信息不存在:");
		}
		PersonInfoVO personInfoResponse = serviceInfo.getById(qo.getPersonId());
		if(personInfoResponse == null || personInfoResponse.getId() < 1) {
			return errorObject("人员信息不存在:");
		}

		// SaveFile
		File tmpFile = null;
		File flipFile = null;

		if (qo.getImage64() != null && qo.getImage64().length() > 23) {
			String image64 = qo.getImage64().substring(qo.getImage64().indexOf(",")+1);
			tmpFile = ImageFileUtil.saveImageFileBase64(image64, personInfoResponse.getCode());
		}  else {
			return errorObject("图片信息不能为空:");
		}


		//将注册照和flip的前面名称保持一致，方便在删除图片的时候一起删除
		String filpFileName= tmpFile.getName().replaceFirst(".jpg", "_flip.jpg");

		// 方向图片应该是正常图的.jpg前面加flip，方便删除，要看PM设计了，发布以后需要将此注释删掉
		flipFile = ImageFileUtil.saveFlipImageFile(tmpFile, filpFileName);


		// 获取图片的坐标等信息
		ImageInfo info = new ImageInfo();
		info = ImageFileUtil.getRGBData(tmpFile);
		if (info.rgbData == null) {
			return errorObject("图片数据为空:");
		}

		DetectResult fdResult = FSDKFD.maxFaceDetect(info);
		if (fdResult.getNface() == 0) {
			return errorObject("未能检测到人脸:");
		} else {
			qo.setFaceRect(JSON.toJSONString(fdResult.getRcface()[0]));
		}
		qo.setStatus(Byte.parseByte("1"));
		qo.setIsMirror(Byte.parseByte("0"));
		qo.setImageUrl(tmpFile.getName());

		ExtractResult extractResult = FSDKFR.extractFeature(info.rgbData, fdResult.getRcface()[0], info.width,info.height);
		if (extractResult.getErrorcode() != 200) {
			return errorObject(extractResult.getErrormsg());
		}
		qo.setFeature(StringUtil.byteArrayToStr(extractResult.getFeature()));

		int resultCode = service.save(qo);
		if (resultCode > 0) {
			//成功
		}else {
			tmpFile.delete();
		}
		tmpFile = null;
		info = ImageFileUtil.getRGBData(flipFile);
		// 检查人脸坐标信息
		fdResult = FSDKFD.maxFaceDetect(info);
		if (fdResult.getNface() == 0) {
			return errorObject("不能检测到人脸坐标:");
		} else {
			qo.setFaceRect(JSON.toJSONString(fdResult.getRcface()[0]));
		}
		qo.setImageUrl(flipFile.getName());
		extractResult = FSDKFR.extractFeature(info.rgbData, fdResult.getRcface()[0], info.width,info.height);
		if (extractResult.getErrorcode() != 200) {
			return errorObject(extractResult.getErrormsg());
		}
		qo.setFeature(StringUtil.byteArrayToStr(extractResult.getFeature()));

		flipFile = null;

		qo.setIsMirror(Byte.parseByte("1"));
		// 追加注册并保存到数据库
		if(service.save(qo) <=0) {
			return errorObject("添加失败:");
		}

		return successObject(TARGETID);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete")
	public Bjui delete(@RequestParam(value = "id") Long id){
		if (service.deleteById(id) > 0) {
			return deleteObject(TARGETID);
		}
		return errorObject("删除失败");
	}

	@ResponseBody
	@RequestMapping(value = "/change")
	public Bjui change(@ModelAttribute PersonImageQO qo){
		if (service.updateById(qo) > 0) {
			return successObject(TARGETID);
		}
		return errorObject("修改失败");
	}
}
