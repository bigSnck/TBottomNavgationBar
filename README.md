### 添加到Android studio<br>
##### Step1: 在根build.gradle中添加仓库：<br>

```
allprojects {
	repositories {
		
		maven { url 'https://jitpack.io' }
	}
}
```
##### 注意:maven { url "https://jitpack.io" }一定要放到 allprojects 里面不然更新不下来
#### Step2: 在工程中添加依赖：<br>
```
dependencies {
	implementation 'com.github.bigSnck:TDialogLibrary:1.0.1'
}
```
### 具体使用Demo<br>
#### 通常普通用法<br>
##### 代码：<br>
```
 mList.add(new DefaultDataBean("首页", R.mipmap.index1, R.mipmap.index, R.color.unselected_text_color, R.color.selected_text_color, true));
 mList.add(new DefaultDataBean("发现", R.mipmap.find1, R.mipmap.find, R.color.unselected_text_color, R.color.selected_text_color, false));
 mList.add(new DefaultDataBean("消息", R.mipmap.message1, R.mipmap.message, R.color.unselected_text_color, R.color.selected_text_color, false));
 mList.add(new DefaultDataBean("我的", R.mipmap.me1, R.mipmap.me, R.color.unselected_text_color, R.color.selected_text_color, false));
 
 bottomNavigationBar = new TBottomNavigationBar.Builder(this, new DefaultBottomAdapter(this, mList))
                .showBottomLine(true, getResources().getColor(R.color.colorPrimary), 2)//设置底部的线
                .showTopLine(true, getResources().getColor(R.color.colorPrimary), 2)//设置顶部的线
                .clickItemAnimation(true) 
                .setSelectedPosition(2) //设置选中默认的位置
                .setTabStyle(IBottomStyle.STYLE_Common) //设置样式
                .setTabClickStyle(true) //是否设置点击动画
                .setSelectedCallback(new IBottomNavSelectedCallback() {
                    @Override
                    public void filterSelect(int postion, View view) {
                        Toast.makeText(MainActivity.this, "点击不可以刷新=" + postion, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void unFilterSelect(int postion, View view) {
                        Toast.makeText(MainActivity.this, "点击可以刷新=" + postion, Toast.LENGTH_SHORT).show();

                    }
                }).build();

mLlBottom.addView(bottomNavigationBar.getBottomView());
```
##### 效果图：<br>
<img src="https://github.com/bigSnck/TBottomNavgationBar/blob/master/image/bottom.gif" width="300" height="500"/> 

