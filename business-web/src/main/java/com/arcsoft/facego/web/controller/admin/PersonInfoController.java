package com.arcsoft.facego.web.controller.admin;

import com.alibaba.fastjson.JSON;
import com.arcsoft.facego.StringUtil;
import com.arcsoft.facego.facetech.model.DetectResult;
import com.arcsoft.facego.facetech.model.ExtractResult;
import com.arcsoft.facego.facetech.model.ImageInfo;
import com.arcsoft.facego.facetech.service.FSDKFD;
import com.arcsoft.facego.facetech.service.FSDKFR;
import com.arcsoft.facego.util.bjui.Bjui;
import com.arcsoft.facego.util.bjui.Page;
import com.arcsoft.facego.web.bean.qo.PersonImageQO;
import com.arcsoft.facego.web.bean.vo.PersonImageVO;
import com.arcsoft.facego.web.bean.vo.PersonInfoVO;
import com.arcsoft.facego.web.service.PersonImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.arcsoft.facego.web.service.PersonInfoService;
import com.arcsoft.facego.web.bean.qo.PersonInfoQO;
import com.arcsoft.facego.util.base.BaseController;
import java.io.File;
import com.arcsoft.facego.util.ImageFileUtil;

/**
 * 人员表 
 *
 * @author mark
 * @since 2018-07-04
 */
@Controller
@RequestMapping(value = "/admin/personInfo")
public class PersonInfoController extends BaseController {

	private final static String TARGETID = "admin-personInfo";

	@Autowired
	private PersonInfoService service;
	@Autowired
	private PersonImageService serviceFace;

	@RequestMapping("/add")
	public void add(){
	}

	@RequestMapping(value = "list")
	public void list(){

	}

	@RequestMapping(value = "openlist")
	public void openlist(){

	}
	@ResponseBody
    @RequestMapping("queryByCondition")
	public Page<PersonInfoVO> queryByCondition(@RequestParam(value = "page", defaultValue = "1") int pageCurrent, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,@ModelAttribute PersonInfoQO personInfoQO){
		PersonInfoQO qo = new PersonInfoQO();
		Page<PersonInfoVO> list = service.listForPage(pageCurrent,pageSize,qo);
		list.setStatusCode(200);
		return  list;
	}

	
	@ResponseBody
	@RequestMapping(value = "/save")
	public Bjui save(@ModelAttribute PersonInfoQO qo){
		qo.setType(Byte.parseByte("1"));
		qo.setStatus(Byte.parseByte("1"));
		// 验证图片文件合法性，并将存储为注册照，和反向注册照
		File tmpFile = null;
		File flipFile = null;
		if (qo.getImage64() != null && qo.getImage64().length() > 23) {
			String image64 = qo.getImage64().substring(qo.getImage64().indexOf(",")+1);
			tmpFile = ImageFileUtil.saveImageFileBase64(image64, qo.getCode());
		} else {
			return errorObject("注册图信息错误:");
		}

		//将注册照和flip的前面名称保持一致，方便在删除图片的时候一起删除
		String filpFileName= tmpFile.getName().replaceFirst(".jpg", "_flip.jpg");

		// 方向图片应该是正常图的.jpg前面加flip，方便删除
		flipFile = ImageFileUtil.saveFlipImageFile(tmpFile, filpFileName);
		// 获取图片的RGB图片信息
		ImageInfo info = new ImageInfo();
		info = ImageFileUtil.getRGBData(tmpFile);
		if (info.rgbData == null) {
			return errorObject("图片数据为空:");
		}
		// 获取人脸信息坐标
		DetectResult fdResult = FSDKFD.maxFaceDetect(info);
		if (fdResult.getNface() == 0) {
			return errorObject("未检出出人脸信息:");
		} else {
			qo.setMaterRect(JSON.toJSONString(fdResult.getRcface()[0]));
		}

		ExtractResult extractResult = FSDKFR.extractFeature(info.rgbData,fdResult.getRcface()[0], info.width,	info.height);
		if (extractResult.getErrorcode() != 200) {
			return errorObject(extractResult.getErrormsg());
		}

		long personID = service.save(qo);
		if (personID > 0) {
			PersonImageQO personImageQO = new PersonImageQO();
			personImageQO.setStatus(Byte.parseByte("1"));
			personImageQO.setIsMirror(Byte.parseByte("0"));
			personImageQO.setPersonId(personID);
			personImageQO.setImageUrl(tmpFile.getName());
			personImageQO.setFaceRect(JSON.toJSONString(fdResult.getRcface()[0]));
			personImageQO.setFeature(StringUtil.byteArrayToStr(extractResult.getFeature()));
			if(serviceFace.save(personImageQO) <= 0){
				tmpFile.delete();
				flipFile.delete();
				return errorObject("添加失败.");
			}
		}else{
			tmpFile.delete();
			flipFile.delete();
			return errorObject("添加失败.");
		}
		PersonImageQO personImageQOFlip = new PersonImageQO();
		personImageQOFlip.setStatus(Byte.parseByte("1"));
		personImageQOFlip.setIsMirror(Byte.parseByte("0"));
		personImageQOFlip.setPersonId(personID);

		info = ImageFileUtil.getRGBData(flipFile);
		// 获取反向人脸信息
		fdResult = FSDKFD.maxFaceDetect(info);
		if (fdResult.getNface() == 0) {
			return errorObject("未检出出人脸信息.");
		} else {
			personImageQOFlip.setFaceRect(JSON.toJSONString(fdResult.getRcface()[0]));
		}
		personImageQOFlip.setImageUrl(tmpFile.getName());
		personImageQOFlip.setFeature(StringUtil.byteArrayToStr(extractResult.getFeature()));
		personImageQOFlip.setImageUrl(flipFile.getName());
		personImageQOFlip.setIsMirror(Byte.parseByte("1"));
		personImageQOFlip.setStatus(Byte.parseByte("1"));

		//获取方向照的
		extractResult = FSDKFR.extractFeature(info.rgbData, fdResult.getRcface()[0], info.width,info.height);
		if (extractResult.getErrorcode() != 200) {
			return errorObject(extractResult.getErrormsg());
		}
		personImageQOFlip.setFeature(StringUtil.byteArrayToStr(extractResult.getFeature()));

		if(serviceFace.save(personImageQOFlip) <= 0){
			flipFile.delete();
			return errorObject("添加失败.");
		}

		return successObject(TARGETID);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete")
	public String delete(@RequestParam(value = "id") Long id){
		if (service.deleteById(id) > 0) {
			serviceFace.deleteImage(id);
			return delete(TARGETID);
		}
		return error("删除失败");
	}

	@ResponseBody
	@RequestMapping(value = "/edit")
	public PersonInfoVO edit(@RequestParam(value = "id") Long id){
		PersonInfoVO personInfoVO = service.getById(id);
		if(personInfoVO !=null && personInfoVO.getId() > 0){
			PersonImageQO personImage = new PersonImageQO();
			personImage.setPersonId(id);
			personImage.setStatus(Byte.parseByte("1"));
			Page<PersonImageVO> faceImage = serviceFace.listForPage(1,9999,personImage);
			personInfoVO.setFacedata(faceImage);
		}
		return service.getById(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/update")
	public Bjui update(@ModelAttribute PersonInfoQO qo){
		if (service.updateById(qo) > 0) {
			if(qo.getStatus() != null && qo.getStatus() >=0 && qo.getStatus()< 2){
				//同时修改人员对应的图片
				serviceFace.updateImage(qo.getId(),qo.getStatus());
			}
			return successObject(TARGETID);
		}
		return errorObject("修改失败");
	}


	@RequestMapping(value = "/view")
	public void view(@RequestParam(value = "id") Long id, ModelMap modelMap){
		modelMap.put("bean", service.getById(id));
	}
	
}
