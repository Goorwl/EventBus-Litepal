##EventBus 3.0 简单使用介绍
###文章简介
* 本文介绍的是EventBus 3.0 版本的使用
* 编译环境

		compileSdkVersion 25
		minSdkVersion 15
		studio版本2.2.3
		classpath 'com.android.tools.build:gradle:2.2.3'
* 准备工作

	    compile 'org.greenrobot:eventbus:3.0.0'		//EventBus 3.0
	    compile 'org.litepal.android:core:1.4.1'	//LitePal 1.4
* PS：还不懂如何使用LitePal的同学参考我的另一篇文章《LitePal使用入门》`http://www.jianshu.com/p/93af9843cabd`
* PS：之所以使用lietpal配合，就是练习一下litepal的使用，litepal的作者是《第一行代码》作者郭霖所写。

###思路简介
* 测试Eventbus的一般事件使用以及粘性事件使用；
* 点击一次发送数据，并存储到litepal数据库中；
* 普通事件和粘性事件都累加之前显示的值，根据值的不同判定粘性事件是否生效。

###EventBus常见错误
> Subscriber class xxx.activity.MainActivity and its super 
> classes have no public methods with the @Subscribe annotation

* 解释：当获取事件的时候，获取事件的方法写的不正确；
* 解决：
	* 1 检查是否写注解了
	* 2 方法的修饰符是否为`public`

###总结：
* EventBus需要先注册再发送消息，才能接收到数据；
* 如果先发送，再注册，只有黏性事件可以接收到消息；
* 注意启动的界面，首先打开的是普通的注册事件所在的界面，之后才是发送消息的界面。