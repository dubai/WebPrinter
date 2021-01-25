(function (global, factory) {
	typeof exports === 'object' && typeof module !== 'undefined' ? module.exports = factory(): 
	typeof define === 'function' && define.amd ? define(factory) : (global = global || self, global.WebPrinter = factory());
}(this, function(){
	'use strict';
	
	var version = "0.0.1";
	
	var $ = typeof $ != 'undefined' ? $ : typeof jQuery != 'undefined' ? jQuery : typeof Zepto != 'undefined' ? Zepto : null;
	
	WebPrinter.usejQuery = function(jQuery){
		$ = jQuery;
	};
	
	function WebPrinter(config){
		if( !$ ){
			console.log("jQuery or Zepto is not defined");
			return ;
		}
		if( !config ){
			return ;
		}
		var _this = this;
		
		_this.extend(config);
		
		if( config.fileurl != '' && config.fileurl != undefined){
			_this.getPDFFile(config.fileurl);
		}
		
	}
	WebPrinter.prototype = {
		constructor: WebPrinter,
		baseData: function baseData() {
			return {
				baseOpts:{
					url: "http://127.0.0.1",// 打印服务IP
					post: "9000",			// 打印服务端口
					printMethod: ["/print/printimg","/print/printpdf","/print/printoffice1","/print/printoffice2","/print/posprint"]
				},
				opts: {
					fileurl: '',			// 远程请求文件流地址
					file: new FormData(),	// 要打印的文件流
					fenshu: 1,				// 打印份数
					fangxiang: '1',			// 打印方向 0 横打 1 竖打 2 自动判断方向
					dsm: '0',				// 单双页 0 单面 1 双面
					printerName: '',		// 打印机名称
					methodInd: 0,			// 请求方法index
					posTemInd: 0,			// POS模板序号
					posData: []				// POS机提交数据
				}
			};
		},
		extend: function extend(config){
			var _this = this,
				opts = _this.baseData().opts;
				
			for (var key in opts){
				if(opts[key] && Object.prototype.toString.call(opts[key]) == '[object Object]'){
					for(var key2 in config[key]){
						opts[key][key2] = config[key][key2] == undefined ? opts[key][key2]: config[key][key2];
					}
				}else{
					opts[key] = config[key] || opts[key];
				}
			}
			
			_this.config = opts;
		},
		printFile: function print(){
			var _this = this,
				config = _this.config,
				baseOpts = _this.baseData().baseOpts;

			var url;
			if(config.methodInd == 0 || config.methodInd == 1){
				url = baseOpts.url+":"+baseOpts.post+""+baseOpts.printMethod[config.methodInd]
					+"?fenshu="+config.fenshu+"&fangxiang="+config.fangxiang+"&dsm="+config.dsm
					+"&printername="+config.printerName;
			}else if(config.methodInd == 2 || config.methodInd == 3){
				url = baseOpts.url+":"+baseOpts.post+""+baseOpts.printMethod[config.methodInd]
					+"?printername="+config.printerName;
			}
			
			$.ajax({
				type: "POST",
				url: url,
				data: config.file,
				cache: false,
				contentType: false,   //不去设置Content-Type请求头
				processData: false,   //不去处理发送的数据
				dataType: 'text',
				success: function(result) {
					
				},
				error: function(e){
					
				}
			});
		},
		getPDFFile: function getFile(fileurl){
			var _this = this,
				config = _this.config;
			var xhr = new XMLHttpRequest();
			xhr.open('GET', fileurl, true);
			xhr.setRequestHeader("Content-Type", "application/pdf");
			xhr.responseType = "blob";
			xhr.onload = function() {
				if(this.status === 200){
					var blob = this.response;
					var fileDate = new FormData();
					fileDate.append("file", blob);
					
					config["file"] = fileDate;
					
					_this.config = config;
				}
			}
			// 发送请求
			xhr.send();
		},
		printPOS: function(){
			var _this = this,
				config = _this.config,
				baseOpt = _this.baseData().baseOpts;
			console.log(baseOpt);
			$.ajax({
				type: "POST",
				url: baseOpt.url+":"+baseOpt.post+""+baseOpt.printMethod[config.methodInd],
				beforeSend: function(xhr){
					xhr.setRequestHeader("Content-Type", "application/json;charset=utf-8");
				},
				data: JSON.stringify({
					printName: config.printerName,
					template: POSTemplate[config.posTemInd],
					database: config.posData.join(",")
				}),
				dataType: 'json',
				success: function(result){
					
				}
			});
		}
	};
	
	WebPrinter.version = version;
	
	return WebPrinter;
}));
POSTemplate = [];
// POS机打印模板数组
POSTemplate[0] = {
	"width": 180,
	"height": 105,
	"topHeight": 20,
	"bottomHeight": 30,
	"singles": [{
		"align": "center",
		"bold": 0,
		"fontName": "宋体",
		"fontSize": 10,
		"height": 15,
		"type": "text",
		"width": 180,
		"x": 0,
		"y": 0
	}, {
		"align": "left",
		"bold": 0,
		"fontName": "",
		"fontSize": 10,
		"height": 50,
		"type": "code128",
		"width": 100,
		"x": 5,
		"y": 10
	}, {
		"align": "left",
		"bold": 0,
		"fontName": "宋体",
		"fontSize": 12,
		"height": 30,
		"type": "text",
		"width": 70,
		"x": 110,
		"y": 20
	}, {
		"align": "left",
		"bold": 0,
		"fontName": "宋体",
		"fontSize": 12,
		"height": 30,
		"type": "text",
		"width": 40,
		"x": 110,
		"y": 50
	}, {
		"align": "left",
		"bold": 0,
		"fontName": "宋体",
		"fontSize": 12,
		"height": 30,
		"type": "text",
		"width": 40,
		"x": 140,
		"y": 50
	}, {
		"align": "left",
		"bold": 0,
		"fontName": "宋体",
		"fontSize": 12,
		"height": 15,
		"type": "text",
		"width": 180,
		"x": 5,
		"y": 75
	}, {
		"align": "left",
		"bold": 0,
		"fontName": "宋体",
		"fontSize": 12,
		"height": 15,
		"type": "text",
		"width": 170,
		"x": 5,
		"y": 90
	}]
};
POSTemplate[1] = {
	"width": 180,
	"height": 105,
	"topHeight": 20,
	"bottomHeight": 30,
	"singles": [{
		"align": "center",
		"bold": 0,
		"fontName": "宋体",
		"fontSize": 10,
		"height": 15,
		"type": "text",
		"width": 180,
		"x": 0,
		"y": 0
	}, {
		"align": "left",
		"bold": 0,
		"fontName": "",
		"fontSize": 10,
		"height": 55,
		"type": "codebar",
		"width": 100,
		"x": 5,
		"y": 10
	}, {
		"align": "left",
		"bold": 0,
		"fontName": "宋体",
		"fontSize": 12,
		"height": 20,
		"type": "text",
		"width": 80,
		"x": 110,
		"y": 20
	}, {
		"align": "left",
		"bold": 0,
		"fontName": "宋体",
		"fontSize": 12,
		"height": 20,
		"type": "text",
		"width": 40,
		"x": 110,
		"y": 35
	}, {
		"align": "left",
		"bold": 0,
		"fontName": "宋体",
		"fontSize": 12,
		"height": 20,
		"type": "text",
		"width": 40,
		"x": 145,
		"y": 35
	}, {
		"align": "left",
		"bold": 0,
		"fontName": "宋体",
		"fontSize": 12,
		"height": 20,
		"type": "text",
		"width": 80,
		"x": 110,
		"y": 55
	}, {
		"align": "center",
		"bold": 0,
		"fontName": "宋体",
		"fontSize": 12,
		"height": 20,
		"type": "text",
		"width": 180,
		"x": 0,
		"y": 75
	}]
};
