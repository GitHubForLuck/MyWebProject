	var x = 510;
	var y = 85;
	var maxTop = 10;
	var spacing = 150;
	var move = 25;
	var cans = null;
	var ri_index = 0;
	var cansType = {
			"font":"15px Courier New",
			"strokeStyle":"black",
			"lineWidth":2
	};
	
	var startNode = {"left":"450px",
			"top":"50px",
			"cls":"startdiv"
	};
	
	var Node = {"left":"410px",
			"top":maxTop,
			"cls":"nodediv"
	};
	
	var endNode = {"left":"450px",
			"top":maxTop,
			"cls":"enddiv"
	};

	function drawNode(div,node){
		Node["top"] = maxTop+"px";
		$(div).append("<div class='"+Node["cls"]+"' id='"+node["id"]+"'><p>"+node["name"]+"</p></div>");
		$("#"+node["id"]).css("left",Node["left"]).css("top",Node["top"]);
	}

	function drawStart(div) {
		$(div).append("<div class='"+startNode["cls"]+"' id='start'><p>开始</p></div>");
		$("#start").css("left",startNode["left"]).css("top",startNode["top"]);
		drawStraightLink(null,x,y);
	}

	function drawEnd(div) {
		endNode["top"] = maxTop+spacing+"px";
		$(div).append("<div class='"+endNode['cls']+"' id='end'><p>结束</p></div>");
		$("#end").css("left",endNode["left"]).css("top",endNode["top"]);
		
	}
	/**
	初始化画板
	**/
	function formatCan(div){
		var can=document.getElementById(div);
		cans = can.getContext('2d');
		cans.lineWidth=cansType["lineWidth"];
		cans.font=cansType["font"];
	    cans.strokeStyle = cansType["strokeStyle"];
	}

	/**
	计算节点
	**/
	function sumLinkXY(istartNode,iendNode){
		var startNode = $('#'+istartNode);
		var endNode = $('#'+iendNode);
		
		var startNodeX =0;
		var startNodeY=0;
		var endNodeX=0;
		var endNodeY=0;
		
		if(null != startNode) {
			startNodeX = startNode.offset().top; 
			startNodeY = startNode.offset().left;
		}
		
		if(null != endNode) {
			endNodeX = endNode.offset().top; 
			endNodeY = endNode.offset().left; 
		}
		
		var Link = {
				"startX":startNodeY+spacing/2+move,
				"startY":startNodeX+spacing/2+5,
				"endX":endNodeY+spacing/2+move,
				"endY":endNodeX
		}
		
		if("end" == iendNode) {
			Link["endX"] = Link["startX"];
		}
		
		return Link;
	}

	/**
	画直线
	**/
	function drawStraightLink(rule,i_x,i_y) {
		var link_x=i_x;
		var link_y=i_y;
		var text_x = i_x;
		var text_y = i_y;
		cans.strokeStyle = cansType["strokeStyle"];
		if (null != rule) {
			var link = sumLinkXY(rule["id"],rule["nextNode"]);
			if("Straight" == rule["type"]) {
		        cans.moveTo(link["startX"]+spacing/2,link["startY"]-spacing/4);//第一个起点
		        cans.lineTo(link["startX"]+spacing+move,link["startY"]-spacing/4);//第二个点
		        cans.lineTo(link["startX"]+spacing+move,link["endY"]+spacing/4);//第三个点（以第二个点为起点）
		        
		        cans.stroke();
		        paintArrow({x:link["startX"]+spacing+move,y:link["endY"]+spacing/4},{x:link["startX"]+spacing/2+move,y:link["endY"]+spacing/4},cans);
				
		        text_x = link["startX"] + spacing-move;
				text_y = link["startY"];
				rule["text"] += "到" + $('#'+rule["nextNode"]).text();  
			} else {
				//直线
				paintArrow({x:link["startX"],y:link["startY"]},{x:link["endX"],y:link["endY"]},cans);
				text_x = link["startX"]+10
				text_y = link["startY"]+40;
			}
			
			 //标注
	        if(null != rule["text"]) {
	        	cans.fillText(rule["text"],text_x,text_y);
	        }
			
			
		} else {
			 paintArrow({x:link_x,y:link_y},{x:link_x,y:link_y+spacing/2},cans);
			 text_y = link_y+40;
			 cans.fillText("",text_x,text_y);
		}
		
	}
	/**
	随机颜色
	**/
	function getRandomColor(){ 
		return "#"+("00000"+((Math.random()*16777215+0.5)>>0).toString(16)).slice(-6); 
	}
/**
 * 实现两点间画箭头的功能
 * @author mapleque@163.com
 * @version 1.0
 * @date 2013.05.23
 */
;(function(window,document){
 if (window.mapleque==undefined)
  window.mapleque={};
 if (window.mapleque.arrow!=undefined)
  return;
 
 /**
  * 组件对外接口
  */
 var proc={
  /**
  * 接收canvas对象，并在此上画箭头（箭头起止点已经设置）
  * @param context
  */
  paint:function(context){paint(this,context);},
  /**
  * 设置箭头起止点
  * @param sp起点
  * @param ep终点
  * @param st强度
  */
  set:function(sp,ep,st){init(this,sp,ep,st);},
  /**
  * 设置箭头外观
  * @param args
  */
  setPara:function(args){
   this.size=args.arrow_size;//箭头大小
   this.sharp=args.arrow_sharp;//箭头锐钝
  }
 };
 
 var init=function(a,sp,ep,st){
  a.sp=sp;//起点
  a.ep=ep;//终点
  a.st=st;//强度
 };
 var paint=function(a,context){
  var sp=a.sp;
  var ep=a.ep;
  if (context==undefined)
   return;
  //画箭头主线
  context.beginPath();
  context.moveTo(sp.x,sp.y);
  context.lineTo(ep.x,ep.y);
  //画箭头头部
  var h=_calcH(a,sp,ep,context);
  context.moveTo(ep.x,ep.y);
  context.lineTo(h.h1.x,h.h1.y);
  context.moveTo(ep.x,ep.y);
  context.lineTo(h.h2.x,h.h2.y);
  context.stroke();
 };
 //计算头部坐标
 var _calcH=function(a,sp,ep,context){
  var theta=Math.atan((ep.x-sp.x)/(ep.y-sp.y));
  var cep=_scrollXOY(ep,-theta);
  var csp=_scrollXOY(sp,-theta);
  var ch1={x:0,y:0};
  var ch2={x:0,y:0};
  var l=cep.y-csp.y;
  ch1.x=cep.x+l*(a.sharp||0.025);
  ch1.y=cep.y-l*(a.size||0.05);
  ch2.x=cep.x-l*(a.sharp||0.025);
  ch2.y=cep.y-l*(a.size||0.05);
  var h1=_scrollXOY(ch1,theta);
  var h2=_scrollXOY(ch2,theta);
  return {
   h1:h1,
   h2:h2
    };
 };
 //旋转坐标
 var _scrollXOY=function(p,theta){
  return {
   x:p.x*Math.cos(theta)+p.y*Math.sin(theta),
   y:p.y*Math.cos(theta)-p.x*Math.sin(theta)
  };
 };
 
 var arrow=new Function();
 arrow.prototype=proc;
 window.mapleque.arrow=arrow;
})(window,document);

/**
画箭头
**/
function paintArrow(start,end,cans) {
	 var a1=new window.mapleque.arrow();
      	a1.set(start,end);
      	a1.setPara({
      	 arrow_size:0.2,
      	 arrow_sharp:0.1
      	})
      	a1.paint(cans);
}


