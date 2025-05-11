# urlchat
使用url进行一对一聊天

对于single_edition:
使用java1.8.0_181编译，在.class的目录下执行java UrlChat，在本地提供web服务
本地浏览器地址栏输入localhost:8080/message，服务端的终端即可收到message这一信息
此时浏览器卡在加载页面，服务端终端输入想回复的消息并回车，浏览器加载完毕，得到服务端的消息
浏览器再次发起请求输入localhost:8080/message2并回车，以进行下次聊天

或者，在同一局域网下的设备的浏览器输入：服务端的ip:8080/message，然后服务端回复消息，同上

# single_edition
使用java生成单个.class文件。

# project_editon
暂无。
