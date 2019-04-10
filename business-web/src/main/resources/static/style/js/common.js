/** common.js By Beginner Emain:zheng_jinfan@126.com HomePage:http://www.zhengjinfan.cn */
layui.define(['layer'], function(exports) {
	"use strict";

	var $ = layui.jquery,
		layer = layui.layer;

	var common = {
		/**
		 * 抛出一个异常错误信息
		 * @param {String} msg
		 */
		throwError: function(msg) {
			throw new Error(msg);
			return;
		},
		/**
		 * 弹出一个错误提示
		 * @param {String} msg
		 */
		msgError: function(msg) {
			layer.msg(msg, {
				icon: 5
			});
			return;
		}
	};

	exports('common', common);
});


//判断是否是整数
function isNumber(str) {
    str = str.toString();
    if ("" == str) {
        return false;
    }
    var reg = /\D/;
    return str.match(reg) == null;
}


//验证字符串是否是数字
function checkNumber(theObj) {
    var reg = /^[0-9]+.?[0-9]*$/;
    if (reg.test(theObj)) {
        return true;
    }
    return false;
}

//判断是否是float类型
function checkFloat(val) {
    var result = true
    if (val != undefined && val != null && $.trim(val) != "") {
        if (val.substr(0, 1) == "-" || val.substr(0, 1) == ".") {
            result = false;
        } else {
            var regex = /^[+|-]?\d+\.?\d*$/;
            if (!regex.test(val)) {
                result = false;
            }
        }
    } else {
        result = false;
    }
    return result;
}


//判断是否是float类型(包括正浮点数)
function checkFloatAll(val) {
    var result = true
    if (val != undefined && val != null && $.trim(val) != "") {
        if (val.substr(0, 1) == ".") {
            result = false;
        } else {
            var regex = /^[+|-]?\d+\.?\d*$/;
            if (!regex.test(val)) {
                result = false;
            }
        }
    } else {
        result = false;
    }
    return result;
}


//检测的字符串是否符合规定  checkPara 需教研的字符串，checkName 字符串如果出错提示的名称
function checkParam_str(checkPara,checkName){
	var resultCode = 0;
	if(checkPara == ""){
			layer.msg("请输入"+checkName, {
				icon: 5,
				anim: 6
			});
		resultCode = 1;
	}else if(checkPara.length > 30){
		layer.msg(checkName+"不能大于30个字符", {
			icon: 5,
			anim: 6
		});
		resultCode = 1;
	}
	return resultCode;
}

//检测数字是否符合是整数，并且大于0   checkPara 需教研的字符串，checkName 字符串如果出错提示的名称
function checkParam_num(checkPara,checkName){
	var resultCode = 0;
	if(checkPara == "" || checkNumber(checkPara) == false){
		layer.msg(checkName+"格式错误", {
			icon: 5,
			anim: 6
		});
		resultCode = 1;
	}
	return resultCode;
}

//获取参数值
function getUrlParam(name){
	var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)');
	var result = window.location.search.substr(1).match(reg);
	return result ? decodeURIComponent(result[2]) : null;
}