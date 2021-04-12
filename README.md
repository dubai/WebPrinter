# web打印服务

实现网页调用本地指定打印机静默打印

实现文件上传打印

根据template JSON串，打印小票

引用项目下的js文件，实现打印服务的启用，同时json模板存放在js文件中
```
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
	}]
};
```
+ /print/printimg: 打印图片
+ /print/printpdf: 打印PDF,
+ /print/printoffice1: 使用jacob直接打印文档
+ /print/printoffice2: 将文档转化成PDF后打印
+ /print/posprint: 简单小票打印  

>demo文件夹中存放页面调用的示例

### 功能增加

+ 增加dll加载功能
+ 增加两款读卡设备功能

### 注意

>在打印word或者excel时 把 jacob-1.20-x64.dll 放到%JAVA_HOME%\bin目录下。
>
>如果通过exe4j工具打包实现程序静默运行，并将jre放置在exe文件的同级下，
>
>则将jacob-1.20-x64.dll 放在C:\Windows\System32 
>
>jar包运行参数配置  -Dfile.encoding=GB18030 -Djava.library.path=C:\Java\JDK1.8\jre
>
>所有的dll文件放在java.library.path设置的路径下
