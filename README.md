#（Android）自定义一个相对于宽度等比的View

<br>
##无需求不码字，上需求：

- **有一张图片是正方形的，且它的宽度为屏幕大小，即宽度属性为match_parent，这时高度就尴尬了，是不是希望有个属性是根据宽度来的？**
- **有一张图片，要求宽度充满屏幕，然后高度是图片宽度的1/3。因为屏幕宽度是相对的，这个1/3要怎么取？继续尴尬中。。**

##上效果图
为了方便看效果 我将父控件的宽度设置为200dp，效果如下
![这里写图片描述](https://github.com/qzsang/ScaleView/blob/master/doc/demo.jpg)


##上代码
``` python
	//关键代码就一段，即重写onMeasure方法 heightWeight为高的权重，widthWeight为宽的权重  
	//heightWeight和widthWeight为float类型
	@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		 super.onMeasure(
	                    widthMeasureSpec,
	                    MeasureSpec.makeMeasureSpec(
		                    (int)( MeasureSpec.getSize(widthMeasureSpec) * (heightWeight/widthWeight) ),
			                    MeasureSpec.EXACTLY));

	}
```

##原理

1、onMeasure
: View在绘制的时候，会通过onMeasure方法测量view的大小，在测量出宽高后会通过 setMeasuredDimension(widthSize, heightSize);的方法保存测量的结果，如果是继承某个View，如ImageView就可以直接调用super.onMeasure（），因为里面已经有这个实现的方法了

2、关于MeasureSpec 类
: 在这个类有三个重要参数，即：spec、size和mode
	> spec为规格的缩写，如果知道spec的值就可以通过 MeasureSpec.getSize(spec)方法得到size大小，和    MeasureSpec.getMode(spec)方法得到mode值 
	> <br>size为长度的值，单位为px
	> <br>mode为模式，一共有3种： UNSPECIFIED(未指定),父元素部队自元素施加任何束缚，子元素可以得到任意想要的大小；EXACTLY(完全)，父元素决定自元素的确切大小，子元素将被限定在给定的边界里而忽略它本身大小；AT_MOST(至多)，子元素至多达到指定大小的值 
: 到此，我们可以得出一个结论 spec = size + mode

3、到此，我们可以开始实践啦
: 重写onMeasure方法，会看到有两个参数  (int widthMeasureSpec, int heightMeasureSpec) ，接下来把spec替换成我们自己想要的spec，然后再继承原方法即可。
<br>假设，我们需要的宽高比是1:2，以宽度为标准

``` python
	//既然以宽为标准，那宽度的规格就不变，我们来重写一个heightMeasureSpec即可
	@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	    //得到heightSize 
	    int heightSize = (int)( MeasureSpec.getSize(widthMeasureSpec) * (1/2) );
	    //通过heightSize  和指定的EXACTLY模式得到heightMeasureSpec
	    heightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize , MeasureSpec.EXACTLY);
	    //继承原方法  其实只是改动了heightMeasureSpec
		 super.onMeasure(widthMeasureSpec,heightMeasureSpec);
	}
```

