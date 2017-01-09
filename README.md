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
* PS：还不懂如何使用LitePal的同学参考我的另一篇文章[《LitePal使用入门》](http://www.jianshu.com/p/93af9843cabd)
* PS：之所以使用lietpal配合，就是练习一下litepal的使用，litepal的作者是《第一行代码》作者郭霖所写。

###思路简介
* 测试Eventbus的一般事件使用以及粘性事件使用；
* 点击一次发送数据，并存储到litepal数据库中；
* 普通事件和粘性事件都累加之前显示的值，根据值的不同判定粘性事件是否生效。
###使用介绍
* 发送普通消息

		//发送普通消息
	    private void sendNormal(int i) {
	        EventBean ev = new EventBean();
	        LitepalBean bean = DataSupport.find(LitepalBean.class, i);
	        if (bean == null) {
	            ev.number = 1;
	        } else {
	            //每次增加10
	            ev.number = 10;
	        }
	        EventBus.getDefault().post(ev);		//这个才是重点
	        Toast.makeText(this, "发送普通数据为："+ev.number, Toast.LENGTH_SHORT).show();
	    }

* 发送粘性消息
	
		//发送粘性消息
	    private void sendSticky(int size) {
	        LitepalBean litepalBean = DataSupport.find(LitepalBean.class, size);
	        if (litepalBean == null){
	            litepalBean = new LitepalBean();
	            litepalBean.number = 1;
	        }
	        EventBean bean = new EventBean();
	        bean.number = litepalBean.number;
	        EventBus.getDefault().postSticky(bean);		//这个是重点
	        Toast.makeText(this, "发送粘性数据为："+bean.number, Toast.LENGTH_SHORT).show();
	    }
* 接收普通消息

	    //注解必须写
	    @Subscribe()
	    public void showNormal(EventBean bean) {
	        String s = mTvShow.getText().toString();
	        int res = Integer.valueOf(s) + bean.number;
	        mTvShow.setText(res + "");
	    }

* 接收粘性消息

	    //注解必须写，注意设置sticky属性
	    @Subscribe(sticky = true)
	    public void showSticky(EventBean bean){
	        String s = mTvShow.getText().toString();
	        int res = Integer.valueOf(s) + bean.number;
	        SPutils.putString(StickyActivity.this,"temp",res+"");
	        Log.e(TAG, "showSticky: 3 ");
	    }
* 移除粘性消息

	    @Override
	    protected void onDestroy() {
	        //移除全部黏性事件【粘性事件建议这么写，不然可能会出现预料之外的问题】
	        EventBus.getDefault().removeAllStickyEvents();
			//   移除单个粘性事件
			//   EventBus.getDefault().removeStickyEvent(StickyActivity.class);
	        //反注册eventbus事件【黏性事件可以不需要反注册】
	        EventBus.getDefault().unregister(StickyActivity.this);
	        super.onDestroy();
	    }

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
* 普通事件可以接收粘性事件消息，但是注册的黏性事件，只能接收粘性消息；
* 注意启动的界面，首先打开的是普通的注册事件所在的界面，之后才是发送消息的界面。
* 如果事件处理中有对控件的操作，在注册时，建议注册在对控件初始化的下方。

![执行顺序](http://i.imgur.com/wBXGlGz.png)

* PS：项目地址：[Goorwl的测试](https://github.com/Goorwl/EventBus-Litepal)
* PS：联系方式：goorwl@163.com