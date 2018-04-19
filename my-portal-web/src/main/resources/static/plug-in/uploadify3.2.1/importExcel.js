/**
 * Excel 导入
 * @param opts 初始化参数
 */
$.fn.importExcel = function (opts) {
	if (!this.length) {
		return;
	}
	var self = this;
	$(this).uploadify({
		multi: false,
		width: opts.width || 30, 
		height: opts.height || 15,
		preserve_relative_urls: true,
		successTimeout: 60 * 5,
		buttonText: opts.text || (opts.text = "导入"),
		fileSizeLimit: (opts.limit || 10) + "MB",
		fileTypeExts: "*.xls;*.xlsx;",
		itemTemplate: "<div><div>",
		overrideEvents: ["onDialogClose"],
		uploader: opts.url,
		swf: ctx + "/plug-in/uploadify3.2.1/uploadify.swf",
		formData: opts.param || {},
		onUploadSuccess: function(file, data, response) {
			var def = this.settings.deferred;
			if (response) {
				if (data) {
					var obj = JSON.parse(data);
					if (opts.callback) {
						opts.callback(obj, (obj && obj.result === true));
					}
					if (obj.result === true) {
						if (def) {
							def.resolve();
						}
						return;
					}
				}
			}
			if (def) {
				def.reject();
			}
		},
		onUploadStart: function(file) {
			if ($.isFunction(opts.uploadCheck)) {
				var flag = opts.uploadCheck();
				if (!flag) {
					return $(self).uploadify("cancel", file.id);
				}
			}
			
			if (opts.showWaiting == true) {
				var def = $.Deferred();
				def.promise();
				this.settings.deferred = def;
				waiting(def, "body", "正在执行" + opts.text + ", 请稍等。。。", opts.text + "失败, 请重新导入!");
			}
		},
		onSelectError: function(file, errorCode, errorMsg) {
			var msg_m = opts.text + "失败";
			var msg_s = (file ? file.name : "") + "文件";
            switch(errorCode) {
            	case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED: 
            		msg_s += "每次最多只能选择 " + this.settings.queueSizeLimit + "个文件!";
            		break;
                case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
					msg_s += "大小超出系统限制的" + this.settings.fileSizeLimit + "大小!";
					break;
                case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
                    msg_s += "为空!";
                    break;
                case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
					msg_s += "类型不正确, 请选择文件后缀为: " + this.settings.fileTypeExts + "文件!";
                    break;
                default: 
                	msg_s = "错误代码: " + errorCode + "\n" + errorMsg;
                	break;
            }
            $.messager.alert(msg_m, msg_s);
        },
		onInit: function() {
			var id = "#" + this[0].id;
			var v_elem_obj = $(id).find("object.swfupload");
			var v_elem_btn = $(id).find("div.uploadify-button");
			$(id + "-queue").hide();
			if (opts.style) {
				var v_style = opts.style;
				if (v_style.self) {
					$(id).css(v_style.self);
				}
				if (v_style.object) {
					v_elem_obj.css(v_style.object);
				}
				if (v_style.button) {
					v_elem_btn.css(v_style.button);
				}
			} else {
				v_elem_obj.css({ padding: "8px 5px 8px 10px" });
				v_elem_btn.css("color", "#444");
			}
			
			if ($.isFunction(opts.init)) {
				opts.init(id, v_elem_obj, v_elem_btn);
			}
			
			if (opts.resetCleanUp) {
				$(id).data("uploadify").cleanUp = uploadifyCleanUp;
			}
		}
	});
};

$.extend($.messager, {
	imptExErrBox: function(msgList, title, tableName, callback) {
		if (!msgList) {
			return;
		}
		var len = msgList.length;
		var msg_info = "<div style='float:left;white-space:nowrap;'>导入的" + (tableName || "") + "列表, 第" + msgList[len - 1] + "行";
		for (var i = 0; i < len - 1; i++) {
			msg_info += (i != (len - 1) ? "<br />" : "") + msgList[i];
		}
		var win = this.alert(title || "信息", msg_info+"</div>", "", callback);
		var winWidth = win.width();
		var msgWidth = win.find("div").first().find("div").width();
		if (winWidth < msgWidth) {
			$(win).window({width: msgWidth + 30});
		}
	}
});

function uploadifyCleanUp(f) {
	try {
		if (this.movieElement && typeof(f.CallFunction) === "unknown") {
			this.debug("Removing Flash functions hooks (this should only run in IE and should prevent memory leaks)");
			for (var h in f) {
				try {
					if (typeof(f[h]) === "function" && h[0] >= 'A' && h[0] <= 'Z') {
						f[h] = null;
					}
				} catch (e) {}

			}
		}
	} catch (g) {}

	window.__flash__removeCallback = function (c, b) {
		try {
			if (c) {
				c[b] = null;
			}
		} catch (a) {}

	};
}