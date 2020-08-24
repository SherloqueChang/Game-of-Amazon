# 实现AI的亚马逊棋游戏
## 项目简介
### 界面及功能
基于javax.swing，实现了游戏的图像界面，包括初始界面和棋盘界面如下所示：
![imgge](https://github.com/SherloqueChang/Game-of-Amazon/blob/master/pic/d8521c911efc65e8ca3d5d13b079447.png)
<img src="https://github.com/SherloqueChang/Game-of-Amazon/blob/master/pic/d8521c911efc65e8ca3d5d13b079447.png" style="zoom: 33%;" />

<img src="https://github.com/SherloqueChang/Game-of-Amazon/blob/master/pic/947c5765f051d43b9760d45ff0fe20c.png" style="zoom:33%;" />

#### 初始界面

1. 单人游戏：实现了人机对战，点击按钮后打开选择方界面：

  <img src="https://github.com/SherloqueChang/Game-of-Amazon/blob/master/pic/240c5ff9f8efbb01f85f5f31b5ab168.png" style="zoom:40%;" />

2. 选择后进入棋盘界面进行人机对战。AI部分见**游戏AI**部分所述

3. 双人游戏：本机进行两人游戏操作

4. 继续游戏：读取本地文件，加载之前保存的游戏对局信息

5. 规则说明：跳出规则说明的弹窗，如下：

   <img src="https://github.com/SherloqueChang/Game-of-Amazon/blob/master/pic/a72b5c332910e7061e84ef0852f5cb4.png" style="zoom:40%;" />

6. 退出游戏：退出游戏界面

  #### 棋盘界面
6. 保存：文件流操作，保存当前对局信息到本地；但在每次选择**单人游戏**或**双人游戏**后保存的游戏信息会被清除并设为初始状态
7. 退出：返回到游戏初始界面
8. 文本框：显示游戏进行状态

### 游戏AI

​	主要思路：

	1. 设计评估函数，对某种棋盘状态下的黑色方和白色方的局势进行评估并赋分
 	2. 根据当前的状态进行模拟落子，依回合数不同模拟接下来1步到3步
 	3. 基于min-max准则进行博弈，做出决策，并采用α-β剪支来减小运算量

​	评估函数：

    1. 从三个角度进行评估，分别是领地territory，位置position，灵活度mobility
 	2. 对territory的计算涉及到Queen Move和King Move两种走法，对于每一个空格，计算黑色方和白色方分别通过Queen Move和King Move所需要的最小移动步数，存储在MyQueenMoveNumber，myKingMoveNumber，enemyQueenMoveNumber，enemyKingMoveNumber这几个数组中。对比对应数组中的同一位置，其中值较小的一方认为控制该领地。用territory1来衡量QueenMove走法下当我方控制领地的大小，用territory2来衡量KingMove走法下当我方控制领地的大小。
 	3. 对position的计算则反应了对某一位置的控制权的大小，position1衡量QueenMove走法下的对某位置控制权的大小，用该位置下，2的MyQueenMoveNumber次幂减去2的enemyQueenMoveNumber来衡量。position2衡量KingMove走法下的对某位置控制权的大小，用相应位置上myKingMoveNumber和enemyKingMoveNumber的差除以6来衡量。
 	4. 对mobility的计算反应的是棋子的灵活度，由某一棋子通过QueenMove能够到达的位置的灵活度负权求和来衡量。

​    特色：

&emsp;根据回合数的不同来划分棋局所处的阶段，初期较为重视棋子的灵活度，中期	加大位置的权重，后期将灵活度的权重设为0，而更加重视领地。

&emsp;同时随着棋局的进行，棋子课以落子的状态也会减少，这时候可以增加搜索的	圣都以达到更好的效果。



### 代码实现
项目总共包括6个类文件：
&ensp;1. Amazons.java: 实现了棋盘界面，并在界面右侧添加了操作按钮
&ensp;2. Boardconfig.java: 作为接口定义了游戏的各项参数，包括界面大小、字体设置、背景图片等
&ensp;3. ButtonListener.java: 设置按钮事件监听，实现了游戏的主要功能
&ensp;4. FrameListener.java: 主要是设置鼠标监听事件，规定了游戏的操作规则
&ensp;5. InitUI.java: 实现了游戏初始界面
&ensp;6. AI.java: 实现AI的实现

### 程序运行
在解压目录中有已打包的Amazon.jar文件，双击直接进入游戏
