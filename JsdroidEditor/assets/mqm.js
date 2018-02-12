importPackage(java.lang)
importPackage(java.io)
importPackage(java.util.regex)
importPackage(com.jsdroid.script)
importPackage(com.jsdroid.uiautomator2)
importPackage(com.jsdroid.util)
importPackage(com.jsdroid.transaction)
importPackage(com.jsdroid.input)

//等待func返回非空
function wait(func,timeout){
    var endtime = time()+timeout
    while(time()<endtime){
        var result= func()
		if(result!=null){
			return result
		}
        sleep(100)
    }
}
//等待View
function waitView(res,timeout){
    return wait(function(){
        var view = findView(res)
        if(view!=null){
            return view
        }
    },timeout)
}
//等待activity
function waitAct(name,timeout){
    return wait(function(){
        var top = device.getAct()
        if(top.contains(name)){
            return top;
        }
    },timeout)
}
//从配置中读取int
function readInt(key){
	var value = 0
	try{
		value = parseInt(config.get(key))
	}catch(e){
		toast(e)
	}
	return value
}
//从配置中读取String
function readString(key){
	var value=config.get(key)
	return value
}
//从配置中读取boolean
function readBoolean(key){
	var value = false
	try{
		value = parseBoolean(config.get(key))
	}catch(e){
		toast(e)
	}
	return value
}
//从配置中读取float
function readFloat(key){
	var value = 0
	try{
		value = parseFloat(config.get(key))
	}catch(e){
		toast(e)
	}
	return value
}
//查找控件点击
function click(res){
	try{findView(res).click()}catch(e){
		return false
	}
	return true
}
//读取文本
function read(file){
	try{return FileUtil.readAllText(file).trim()}catch(e){return ""}
}
//保存文本
function write(file,content){
	try{FileUtil.write(file,content)}catch(e){}
}
//添加文本
function append(file,content){
	try{FileUtil.append(file,content)}catch(e){}
}
//执行shell
function exec(cmd){
	return ShellUtil.exec(cmd)
}
//滑动到顶部
function scrollToTop(res){
	var lastPosition = null
	while(true){
		var list = findView(res)
		if(list==null){
			break
		}
		var thisPosition = list.getAllText()
		if(lastPosition==null||thisPosition!=lastPosition){
			//非重复，滑动
			list.swipe(Direction.DOWN,parseFloat(0.8),10000)
			try{device.waitForIdle(2000)}catch(e){}
		}else{
			//重复，退出循环
			break
		}
		lastPosition = thisPosition
	}
}

//滑动到底部
function scrollToBottom(res){
	var lastPosition = null
	while(true){
		var list = findView(res)
		if(list==null){
			break
		}
		var thisPosition = list.getAllText()
		if(lastPosition==null||thisPosition!=lastPosition){
			//非重复，滑动
			list.swipe(Direction.UP,parseFloat(0.8),10000)
		}else{
			//重复，退出循环
			break
		}
		lastPosition = thisPosition
	}
}
//返回，直到界面出现
function back(res){
	for(var i=0;i<5;i++){
		if(waitView(res,2000)!=null){
			return true;
		}
		device.pressBack();
	}
}