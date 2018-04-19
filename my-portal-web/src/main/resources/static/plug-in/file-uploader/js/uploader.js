/***********************控件配置****************************/
var uploaderConfig = {
	swf : "/uploader/cn/common/tools/Uploader.swf",
	//auto : false,				//设置为 true 后，不需要手动调用上传，有文件选择即开始上传。
	server : "/uploader/file/fileUpload.do",				//接受分片的服务端路径
	disableGlobalDnd : true,  	//是否禁掉整个页面的拖拽功能，如果不禁用，图片拖进来的时候会默认被浏览器打开。
	threads : 1,				//上传并发数。允许同时最大上传进程数。（*服务端将分片逐个合并处理,请勿改动并发数*）
	chunked : true,				//是否要分片处理大文件上传。
	duplicate : false,			//去重， 根据文件名字、文件大小和最后修改时间来生成hash Key.
	compress : false,			//配置压缩的图片的选项。如果此选项为false, 则图片在上传前不进行压缩。
	fileNumLimit : 20,			//验证文件总数量, 超出则不允许加入队列。
	chunkSize :  1 * 1024 * 1024,//如果要分片，分多大一片。
	fileSingleSizeLimit : 1000000000000000000000000 * 1024 * 1024,//验证单个文件大小是否超出限制, 超出则不允许加入队列。
	accept : {extensions: 'gif,jpg,jpeg,bmp,png,pdf,ppt,txt,rar,zip,xls,doc,html,et,ett,xlt,xlsx'}//指定接受哪些类型的文件。
};
/***********************控件配置****************************/




//Webuploader常量对象
var uploaderObject = {
	speed : 0,
	getDate : null,
	uploader : null,
	chunkList : null
};
var uploaderInit = function(){
	//初始化控件配置
	$("body").append('<div id="uploaderWin">'+
		'<div id="uploaderWin_head">'+
			'<div id="uploaderWin_btn">'+
				'<div id="uploadDialogHeadline">上传完成</div>'+
				'<div id="uploadDialogCloseTop" title="关闭"></div>'+
				'<div id="uploadDialogCloseMinimumTop" class="" title="最小化"></div>'+
			'</div>'+
		'</div>'+
		'<div id="uploaderMsg">'+
			'<div id="uploaderMsg_icon"></div>'+
			'<div id="uploaderMsg_content">这里是我的一些小小的提示,你可以不用管我!</div>'+
		'</div>'+
		'<div id="uploaderContent">'+
			'<div id="uploader" class="wu-example">'+
				'<div id="theList" class="uploader-list"></div>'+
				'<div class="btns">'+
		       	 	'<div id="picker">+</div>'+
		    	'</div>'+
			'</div>'+
		'</div>'+
	'</div>');
	uploaderObject.uploader = WebUploader.create({
	    swf : uploaderConfig.swf,
	    //auto : uploaderConfig.auto,
	    server : uploaderConfig.server,
	    pick: '#picker',
	    dnd :'#theList',
	    disableGlobalDnd :uploaderConfig.disableGlobalDnd,
	    threads: uploaderConfig.threads,
	    chunked: uploaderConfig.chunked,
	    duplicate :uploaderConfig.duplicate,
	    compress: uploaderConfig.compress,
	    fileNumLimit : uploaderConfig.fileNumLimit,
	    chunkSize : uploaderConfig.chunkSize,
	    fileSingleSizeLimit : uploaderConfig.fileSingleSizeLimit
	    /*accept: uploaderConfig.accept*/
	});
	//封装提示框 {msg : 提示信息,type : 操作类型 ,showType : 是否关闭之前的所有提示 框}
	$.Messag = { 
		info : function(msg,type,showType){
			if(showType && $("#myModal").length>0) return $('#myModal p').html(msg);
			if(type == "open"){
				$("body").append('<div id="myModal" class="reveal-modal"><p class="bg-warning">Msg!</p><a class="close-reveal-modal">&#215;</a></div>');
				$('#myModal p').html(msg);
				$("#myModal").reveal({
				     animation: 'fadeAndPop',
				     animationspeed: 300,
				     closeonbackgroundclick: true,
				     dismissmodalclass: 'close-reveal-modal'
				});
			}else if(!type && msg == "close"){
				$(".close-reveal-modal").click();
			}
		}
	};
	//初始化最小化/最大化
	$("#uploadDialogCloseMinimumTop").click(function(){
		$("#uploadDialogCloseMinimumTop").toggleClass("true");
		var toggle = $("#uploadDialogCloseMinimumTop").attr("class");
		var uploaderWin_Height;
		if(toggle == "true"){
			uploaderWin_Height = "38px";
			$("#uploadDialogCloseMinimumTop").attr("title","最大化");
			$("#uploadDialogCloseMinimumTop").css("background-position","0 -192px");
		}else{
			uploaderWin_Height = "417px";
			$("#uploadDialogCloseMinimumTop").attr("title","最小化");
			$("#uploadDialogCloseMinimumTop").css("background-position","0 -162px");
		}
		$("#uploaderWin").stop(true).animate({"height":uploaderWin_Height},300,function(){ });
	});
	//初始化关闭
	$("#uploadDialogCloseTop").click(function(){ $.windowsFile.options("close"); });
	//打开或关闭附件上传框
	$.windowsFile = {
		options : function(type){
			$("#uploadDialogCloseMinimumTop").attr("class") != "" ? $("#uploadDialogCloseMinimumTop").toggleClass("true") : "";
			$("#uploadDialogCloseMinimumTop").attr("title","最小化");
			$("#uploadDialogCloseMinimumTop").css("background-position","0 -162px");
			if(type == "open") $("#uploaderWin").css("height","417px").css("border","1px solid #6d7a89");
			else if(type == "close") $("#uploaderWin").css("height","0px").css("border","0");
		}
	}; 
};
/********************************************************/
$.Webuploader = {
	create : function (paramData){
		//添加组件
		WebUploader.Uploader.register({
			"before-send-file": "beforeSendFile",
		 	"before-send": "beforeSend",
		 	"after-send-file": "afterSendFile"
    	}, {
    		beforeSendFile: function(file){
    			var task = $.Deferred();
    			uploaderObject.uploader.md5File(file,0,1*1024*1024).then(function(ret) {
					//添加文件信息
					$.ajax({
    		    		type:"POST",
    		    		dataType:"json",
                        url: "/uploader/file/insert.do",
                        data:"param="+JSON.stringify({
							"RELATION_ID" : paramData["relationId"],
							"FILE_NAME" : file["name"],
							"FILE_SIZE" : file["size"],
							"FILE_URL" : paramData["filePath"]+WebUploader.Base.guid()+"["+ret+"]",
							"FILE_MD5" : ret,
							"CRE_USER_ID" : paramData["creUserId"],
							"FILE_CHUNKS" : file["chunks"] ? file["chunks"] : '0'
						})
                    }).then(function(data, textStatus, jqXHR){
                    	var addData = JSON.parse(data);
                    	if(!addData["Message"] || addData["Message"] == "N") { task.reject();$.Webuploader.uplaoderError(file); }
                    	file["fileId"] = addData["resultMap"]["FILE_ID"];
                    	file["fileMd5"] = ret;
                    	if(addData["SecondPass"] == "Y"){
                    		task.reject();
                    		uploaderObject.uploader.skipFile(file);
                    		$.Webuploader.updateSuccess(file,false);
                    		$("#"+file["id"]+" .filePro").html('快速上传成功.').css("color","");
                    	}else{
                    		uploaderObject.uploader.options.formData = {
                				"fileSize":file.size,
                				"filePath":addData["resultMap"]["FILE_URL"],
                				"tFileId":addData["resultMap"]["FILE_ID"],
                				"fileMd5":addData["resultMap"]["FILE_MD5"],
                				"creUserId":addData["resultMap"]["CRE_USER_ID"]
            	    		};
                    		$.ajax({
        		   	    		type:"POST",dataType:"json",
        		   				data:"param="+JSON.stringify({"FILE_ID":addData["resultMap"]["FILE_ID"] }),
        		   				url:"/uploader/file/getChunksListBy_FileId.do"
        		   			}).then(function(data, textStatus, jqXHR){ 
        		   				if(!addData["Message"] || addData["Message"] == "N") { 
    		   						task.reject();
    		   						$.Webuploader.uplaoderError(file); 
    		   					}else {
    		   						uploaderObject.chunkList = JSON.parse(data)["resultMap"];
    		   						task.resolve();
    		   					}
        		   			},function(jqXHR, textStatus, errorThrown){ task.resolve(); });
                    	}
                    });
				});
    			return task.promise();
    		},
    		beforeSend: function(block){
    			var task = $.Deferred();
    			//上传分片前验证是否存在相同文件并且已完成上传
    			$.ajax({
	   	    		type:"POST",dataType:"json",
	   				data:"param="+JSON.stringify({
	   					"FILE_MD5" : block.file["fileMd5"],
	   					"FILE_SIZE" : block.file["size"],
	   					"FILE_STATUS" : "Y"
	   				}),
	   				url:"/uploader/file/getFileBy_Md5_Size.do"
	   			}).then(function(data, textStatus, jqXHR){
   					if(JSON.parse(data)["resultMap"] != null && JSON.parse(data)["resultMap"].length > 0){
   						task.reject();
   						uploaderObject.uploader.skipFile(block.file);
   						$.Webuploader.updateSuccess(block.file,false);
                		$("#"+block.file["id"]+" .filePro").html('快速上传成功.').css("color","");
                		$.Webuploader.updateFile({"FILE_ID":block.file["fileId"],"FILE_STATUS":"Y" });
                		$.ajax({type:"POST",dataType:"json",data:"param="+JSON.stringify({ "FILE_ID" : block.file["fileId"] }),url:"/uploader/chunk/delBy_FileId.do" });
   					}else{
   						uploaderObject.getDate = new Date(JSON.parse(data)["date"]).getTime();
		    			if(uploaderObject.chunkList!=null && uploaderObject.chunkList[block["chunk"]]){
		    				task.reject();
		            	}else task.resolve();
   					}
                });
    			return task.promise();
    		},
    		afterSendFile: function(file){
    			$.Webuploader.updateSuccess(file,true);
    			$.Webuploader.updateFile({"FILE_ID":file["fileId"],"FILE_STATUS":"Y" });
    			if($("."+$("#"+file["id"]).attr("class")).last().index() == $("#"+file["id"]).index()) { $("#uploadDialogHeadline").html("上传完成"); }
    			uploaderObject.uploader.upload();
    			//完成上传之后回调
    			paramData.callback_Success();
    		}
    	});
		//初始化
		uploaderInit();
		/**************************添加队列之前****************************/
		uploaderObject.uploader.on( 'beforeFileQueued', function( file ) {
			if(file.size/1024/1024 == 0) {
				$.Messag.info("无法添加0字节文件入队列.","open",true);
				return false;
			}
			if(file.size > uploaderConfig.fileSingleSizeLimit){
				$.Messag.info("单个文件大小不能超出100M.","open",true);
				return false;
			}
			if($("#theList .item").length >= uploaderConfig.fileNumLimit){
				$.Messag.info("上传列队不能超出"+uploaderConfig.fileNumLimit+"个文件.","open",true);
				return false;
			}
			//加入队列前提供自定义验证回调函数(可不传)
    		if(paramData.callback_Queue) if(!paramData.callback_Queue(file)) return false; 
		});
		//添加队列之后
		uploaderObject.uploader.on( 'fileQueued', function( file ) {
			var fileName = file.name.length > 15 ? file.name.substring(0,15)+"..." : file.name;
			var size = file.size/1024 > 1024 ? (file.size/1024/1024).toFixed(1)+"MB" : (file.size/1024).toFixed(1)+"KB";
			$("#theList").append(
				'<div id="'+file["id"]+'" class="item">'+
					'<div class="fileImg"><img width="25px" height="22px" src="/uploader/cn/common/images/'+file["ext"]+'.png"/></div>'+
					'<div class="fileDiv">'+
						'<span title="'+file["name"]+'" class="fileName">'+fileName+'</span>'+
						'<span class="fileSize">'+size+'</span>'+
						'<span class="filePro">排队中...</span>'+
					'</div>'+
					'<div onclick="$.Webuploader.delFile(\''+file.id+'\')" class="fileClose"></div>'+
					'<div class="rogressDiv"></div>'+
				'</div>');
			if($("#"+file["id"]).index() == 0) {
				$.Webuploader.Synchronous(file);
			}else $("#uploadDialogHeadline span").html(uploaderObject.uploader.getFiles().length);
			uploaderObject.uploader.upload();
		});
		//每个文件开始上传
		uploaderObject.uploader.on( 'uploadStart', function( file ) {
			
		});
		//上传过程中触发，携带上传进度
		uploaderObject.uploader.on( 'uploadProgress', function( file,percentage ) {
			$.Webuploader.Synchronous(file);
			$("#"+file["id"]+" .rogressDiv").css("width",(percentage * 100).toFixed(2) + '%');
			var temp;
			if(uploaderObject.speed > 1024){
				temp = (uploaderObject.speed/1024).toFixed(1)+'M/s';
			}else temp = uploaderObject.speed.toFixed(1)+'KB/s';
			$("#"+file["id"]+" .filePro").html((percentage * 100).toFixed(2) + '%&nbsp;('+temp+')');
		});
		//当某个文件上传到服务端响应后，会派送此事件来询问服务端响应是否有效
		uploaderObject.uploader.on( 'uploadAccept', function( file,response ) {
			uploaderObject.speed = (uploaderConfig.chunkSize/1024)/((new Date(JSON.parse(response)["date"]).getTime() - uploaderObject.getDate)/1000);
			uploaderObject.speed < 0 ? uploaderObject.speed = 0 : "";
			if(JSON.parse(response)["success"]=="N") {
				if(JSON.parse(response)["fileMessage"]) $.Messag.info(JSON.parse(response)["fileMessage"],"open");
				return false;
			}
		});
		//当文件上传出错时触发
		uploaderObject.uploader.on( 'uploadError', function( file ) {
			$.Webuploader.uplaoderError(file);
		});
	},
	Synchronous : function(file){
		//队列总数
		var allCount = uploaderObject.uploader.getFiles().length;
		$("#uploadDialogHeadline").html("正在上传:"+($("#"+file["id"]).index()+1)+"/<span>"+allCount+"</span>");
	},
	delFile : function(ele){
		$( '#'+ele).stop(true).animate({"height":"0px","opacity":"0"},300,function(){
			$( '#'+ele).remove();
			var file = uploaderObject.uploader.getFile(ele);
			uploaderObject.uploader.removeFile(ele,true);
			if(uploaderObject.uploader.getFiles().length == 0)
				$("#uploadDialogHeadline").html("上传完成");
			else $("#uploadDialogHeadline span").html(uploaderObject.uploader.getFiles().length);
		});
	},
	uplaoderError : function(file){
		$("#"+file["id"]+" .filePro").html("上传出错!").css("color","red");
		$("#"+file["id"]+" .rogressDiv").css("width",'0%');
	},
	updateSuccess : function(file,type){
		$("#"+file["id"]+" .rogressDiv").css("width",'0%');
		$("#"+file["id"]+" .fileClose").css("background-position","-24px -165px").attr("onclick","");
		if(type) $("#"+file["id"]+" .filePro").html('已完成');
	},
	updateFile : function(data){ $.ajax({type:"POST",dataType:"json",url:"/uploader/file/update.do",data:"param="+JSON.stringify(data) }); }
};
