# iViewer_1.0
## 一个使用javafx编写电子相片管理程序

**1.** **系统分析**

前言：随着数码相机的普及，越来越多的人拥有大量的数字像片。本程序的目的是编写一个能够对数字像片进行管理和查看的应用程序。

**1.1**     **问题描述**

图片管理器可作为计算机使用者浏览各种格式图片的载体而存在，作为人们日使用计算机工作的一大可视化工具。随着手机的日益普及，个人相片的数量呈现指数级增长。因此，一款出色的图片管理程序便显得尤为必要。

**1.2**     **系统功能分析**

 

**1.2.1**    **必须实现的功能**

•    目录树功能

•    图片选择功能（包括单张和多张图片的选择，选中方式有两种：鼠标框中和ctrl+鼠标右键）

•    图片查看功能（支持等格式）

•    图片放大缩小功能

•    图片删除功能

•    图片复制功能

•    图片重命名功能（多选状态下，图片命名由“用户命名”+递增的序号 组成）

**1.2.2**    **额外实现的功能**

•    多种风格的图片滤镜，共有六种滤镜（Original、Overlay、Sepiatone、Bloom、Mercury、Exclusion），并且支持灰度滑条和添加滤镜后导出。

•    幻灯片功能：支持多种风格的幻灯片播放样式

•    截图功能：支持屏幕任意位置截图

**1.3**     **开发平台及工具介绍**

**1.3.1**    **开发平台：windows 10**

**开发工具：**
​            ①、IDE—eclipse oxygen 2018

​           ②、SceneBuilder:** 

JavaFX Scene Builder 是一个可视化布局工具，可快速      设计 JavaFX 应用程序用户界面，无需编写代码。用户可以拖放UI 组件到工作区，修改组件的属性，应用样式表，而且在后台自动生成所创建布局的 FXML 代码。最后得到的是一个可以稍后与 Java 项目整合到一起的 FXML 文件，从而将 UI 与应用程序逻辑绑定起来。

**1.3.2**   **使用语言：javaFX 和 Java8**

**2.** **系统设计**

**2.1**     **系统的总体结构：**

本程序使用MVC模式，进行整体项目的架构

**2.1.1**    **MVC**模式简介

MVC模式将交互式应用分成模型（Model）、视图（View）和控制器（Controller）三部分。

①  、模型是指从现实世界中挖掘出来的对象模型，是应用逻辑的反映。模型封装了数据和对数据的操作，是实际进行数据处理的计算的地方。

②  视图是应用和用户之间的接口，它负责将应用显现给用户和显示模型的状态。

③  、控制器负责视图和模型之间的交互，控制对用户输入的响应响应方式和流程，它主要负责两方面的动作：把用户的请求分发到相应的模型；将模型的改变及时反应到视图上。

MVC将这些对象分离以提高灵活性和复用性。MVC模式的结构如下图所示：
  ![01](https://img-blog.csdn.net/20181004115519114?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTU1MzYw/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

本程序使用javaFX和SceneBuilder作为开发语言和界面设计工具，其天生便符合MVC开发模式。

**2.1.2**    **项目结构设计**

①  、Model：本项目自我编写了

•    PictureFile模型对图片文件的数据产生和列表等操作进行了封装。

•    PictureNode 模型通过扩展Label类，自定义了包含ImageView和Text的图片节点，内部封装了各种鼠标事件的监听器，是整个项目数据操作的主要来源。

•    MyContextMenu 模型封装了整个项目的鼠标右击事件和数据操作处理

•    TreeFile 模型为TreeView遍历系统目录树提供数据封装以及数据处理

②  、Controller：借助javaFX 及 Scenebuilder，我们通过.fxml文件进行界面设计，本处Controller均是基于.fxml文件自动生成。

•    MainUIController：主界面的控制器，响应用户的鼠标操作，并将其呈现在视图上，例如用户的目录树点击操作、打开复制粘贴剪切删除重命名等操作。

•    ViewUIController：图片查看界面的控制器，将用户的操作分发到ImageView模型上，并将模型的改变反应到视图上，提供人机交互。 

③  、View：使用Scenebuilder进行可视化人机交互界面设计。

•    MainUI.fxml 主界面布局文件

•    ViewUI.fxml 图片查看界面布局文件

④  、action：为了提高项目的可读性和可重用更改性，将所有界面的Button的相应操作封装到一个包内。

⑤  、service: 为整个项目供服务，包括数据链接服务（DataService）、界面转换服务（ChangeService）、鼠标监听服务（MouseEvenHandler和PaneListener）

**2.2**     **系统各个类及类之间的关系设计**

![02](https://img-blog.csdn.net/20181004115558346?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTU1MzYw/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

**2.3**     **数据存储的设计**

本程序数据存储设计较为简单，仅使用了自定义的**ArrayList**<PictureNode> 对象链表和image文件的读写，不涉及数据库等复杂数据存储设计。

**2.4**     **界面设计**

我们知道JavaFX自从2.0版本开始，已经完全抛弃了之前的script语言，才用纯java来实现。这样的好处就是1.让使用Java的IDE进行JavaFX的开发成为可能，2.Java与JavaFX的API相互调用更容易 3.JavaFX的程序部署也更简单。

**2.4.1**    **使用Scenebuilder 进行界面设计的注意点：**

①  、fx:id是一个很重要的属性，在事件逻辑层要获取JavaFX Scene Builder编辑的XML中的控件，需要通过fx:id来获取。

②  、可在右边属性的events中指定事件的方法。如下图所示，Menu中的Open事件对应onMenuOpen方法。

![03](https://img-blog.csdn.net/20181004115644616?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTU1MzYw/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

 

③  、Stage是JavaFX最顶层的容器，最原始的Stage(也就是start方法后的参数)是根据系统平台进行创建的(也是跨平台的重点)。当然，你也可以在程序其他地方创建Stage。

 Scene是包括控件等所有内容的容器，应用程序必须指定Scene的根节点。既可以像上面代码中初始化时传入根节点，也可以通过setRoot方法来设定根节点。 

Parent是所有包含子节点的节点的基类。它是一个继承于Node的抽象类。因此Loader里其实是用到了向上转型。

④  、JavaFX使用很常见的反射机制将UI层和事件层完全分离了。查看上面的study.xml，你就可以看到根节点有一个fx:controller属性。这个属性就是指定事件处理的类。

**2.4.2**   **主要界面演示**：

**①**  **、主界面：**
![04](https://img-blog.csdn.net/20181004115729519?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTU1MzYw/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

**②**  **、查看图片界面：**
![05](https://img-blog.csdn.net/2018100411575788?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTU1MzYw/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

**③**  **、美化图片界面：**
![06](https://img-blog.csdn.net/20181004115856294?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTU1MzYw/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)
**④**  **、幻灯片界面：**
![07](https://img-blog.csdn.net/20181004115915570?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTU1MzYw/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

**3.** **系统实现**

**3.1**     **程序框图**
![08](https://img-blog.csdn.net/20181004120008914?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTU1MzYw/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)
**3.1.1**    **PictureNode** **的 实现：**

本类是我们小组写的最重要的一个model，我们在主界面除了treeView之外，所有的操作包括选中状态的高亮，右键菜单，鼠标框选等等，一下是重写listener的 invalidated方法，是该PictiureNode在被鼠标点选后，显示蓝色高亮状态。
![09](https://img-blog.csdn.net/20181004120127890?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTU1MzYw/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

**3.1.2**    **MainUIController**右边flowPane中PictureNode被框选时的高亮状态：

监听鼠标，当鼠标被按下时，初始化选择矩阵的左上角，当鼠标放开，更新选择矩阵的左上角及边长，通过数学运算，将矩阵中包含的PictureNode节点返回给ArrayList<PictureNode> selectedPictures = new ArrayList<>();代码如下

![在这里插入图片描述](https://img-blog.csdn.net/20181004120208713?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTU1MzYw/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

**3.1.3**    **图片放大缩小的实现，代码如下：**

![在这里插入图片描述](https://img-blog.csdn.net/20181004120219955?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTU1MzYw/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

**3.1.4**   **幻灯片的实现**，通过Timeline进行定时，使用定时下一张关键帧（图片），在加ImageView的特效转换，如imageview.setEffect和setViewport setRotate等，代码如下：
![在这里插入图片描述](https://img-blog.csdn.net/20181004120232615?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTU1MzYw/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)
