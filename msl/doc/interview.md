##当我拿到项目，做业务开始的时候是不是就的考虑性能问题？
个人习惯先把业务搞好，其他的再综合去考虑
但是有个前提，基础的知识需要了解，或者最起码的编程规范要清楚，不能业务编码过程中给自己随意留坑吧 
举两个简单的例子，变量定义声明在需要用的地方不要放太远，尽量少用全局变量，尽量不玩花搞什么多线程编程
阿里编码规范里的内容在实际编码过程中记住就好，不需要记JVM的，这两个中间有一些些gap的
什么时候做性能优化，这是很自然而然的事情，业务体量发展到一定程度会倒逼leader或者开发去考虑这个事情的
如何定位JVM问题，百度一把案例多不胜数然而不如自己遇到一次问题折腾三天解决掉这样理解的透，
编程开发就是一个体力劳动，做的多了会有肌肉记忆的


#去哪儿网：

   dubbo的协议和http的区别

   dubbo的原理

   hashmap的put和get方法

   1.8的改变

   使用红黑树的优势

   aqs的原理

   lock和synchronized区别

   zk的选举原理

   redis的数据结构
#金山：

	二叉树的排序

	dubbo的运行原理

	spring的ioc和aop

	冒泡排序的缺陷和优点 

	场景题：ｒｅｄｉｓ的ｌｉｓｔ做队列



#阿里巴巴：

	项目

	ｊｖｍ

	ｇｃ怎么分析和排查问题　优化

	aqs的原理

	红黑树

	spring源码，讨论学习过程 -- 聊天

	画类图

	策略模式

	并发包

	手撕代码-- 3个线程轮流交替打印abc



#搜狐：

	职位-cdn开发

	zookeeper集群的安装过程

	mysql的二级缓存

	linux命令

	redsi的数据结构



#用钱宝：

	python

	二分查找

	mq的主要字段



#快手：

	hashmap {

		put方法的实现

		hashtable的区别

		& 运算

		为什么2的整数次幂

		resize方法

		hash &（n-1） == ？ hash % n

	}

	arraylist的扩容

	redis的过期策略

	memcached与redis的区别

	b+tree

	最左前缀原则

	聚集索引

	从一个数组中找出出现次数最多的一个数



#大疆创新：

	测评题直接挂



#蚂蚁短租：

	简单的基础

	mysql的优化



#58集团：

	基础架构部



    手撕代码： 单链表反转

    		  从一个数组中把奇数放到前面 偶数放到后面（o(n)）



    redis的数据结构

    一些简答基础



#美菜网：

	mysql的优化

	b+tree

	表的设计



#小米：

	归并排序

	两阶段提交和三阶段提交

	dubbo的expert方法执行过程

	gc参数

	linux命令



#凤凰网：

	mysql的索引

	gc的调优



#百度：

	spring的依赖注入

	mysql的隔离级别及实现

	单链表翻转



#喜马拉雅：

	spring的配置文件如何配置



#贝壳：

	redis的数据结构

	reis锁的缺陷

	dubbo的原理

	zookeeper的选举

	mysql的优化

	手撕代码：单例模式

			 设计一个queue队列



#微鲤科技：

	zookeeper实现读写锁

	string和stringbuilder和stringbuffer的区别

	zk的选举

	redis锁

	从1亿个数据中找出唯一一个没出过的数据（位图   桶排序）



#陌陌：

	redis主从复制的网络中断   怎么同步

	mysql的优化

	归并排序

	双向链表put操作

	mysql的事物

	for update



	



3去哪儿网：

	redis的master-slave的分片 

	paoxs与zab的区别  

	zk的选举

	dubbo的原理

	手撕代码：

		单链表是否有环

		找出入环的第一个节点

		统计入环的总结点数

		写一个死锁案例

	场景题： 内存泄漏的思路及排查

	spring的注解





阿里巴巴：

	dubbo的负载均衡及路由

	单链表的冒泡排序

	手写一个spring aop   jdk自带的类

	hashmap为什么用红黑树 不用avl

	线程池如何知道一个线程执行完了任务



玩吧：

	mysql优化

	设计一个系统

	hashmap的put方法

	扩容

	hashmap线程不安全的原因

	为什么是2的整数次幂

	hashmap的迭代过程中put数据会发生什么

	手写单例模式

	给定一个表   设计索引

	dubbo的原理





滴滴：

	如何实现一个死锁

	jvm

	gc去哪儿网：

   dubbo的协议和http的区别

   dubbo的原理

   hashmap的put和get方法

   1.8的改变

   使用红黑树的优势

   aqs的原理

   lock和synchronized区别

   zk的选举原理

   redis的数据结构



金山：

	二叉树的排序

	dubbo的运行原理

	spring的ioc和aop

	冒泡排序的缺陷和优点 

	场景题：ｒｅｄｉｓ的ｌｉｓｔ做队列



阿里巴巴：

	项目

	ｊｖｍ

	ｇｃ怎么分析和排查问题　优化

	aqs的原理

	红黑树

	spring源码，讨论学习过程 -- 聊天

	画类图

	策略模式

	并发包

	手撕代码-- 3个线程轮流交替打印abc



搜狐：

	职位-cdn开发

	zookeeper集群的安装过程

	mysql的二级缓存

	linux命令

	redsi的数据结构



用钱宝：

	python

	二分查找

	mq的主要字段



快手：

	hashmap {

		put方法的实现

		hashtable的区别

		& 运算

		为什么2的整数次幂

		resize方法

		hash &（n-1） == ？ hash % n

	}

	arraylist的扩容

	redis的过期策略

	memcached与redis的区别

	b+tree

	最左前缀原则

	聚集索引

	从一个数组中找出出现次数最多的一个数



大疆创新：

	测评题直接挂



蚂蚁短租：

	简单的基础

	mysql的优化



58集团：

	基础架构部



    手撕代码： 单链表反转

    		  从一个数组中把奇数放到前面 偶数放到后面（o(n)）



    redis的数据结构

    一些简答基础



美菜网：

	mysql的优化

	b+tree

	表的设计



小米：

	归并排序

	两阶段提交和三阶段提交

	dubbo的expert方法执行过程

	gc参数

	linux命令



凤凰网：

	mysql的索引

	gc的调优



百度：

	spring的依赖注入

	mysql的隔离级别及实现

	单链表翻转



喜马拉雅：

	spring的配置文件如何配置



贝壳：

	redis的数据结构

	reis锁的缺陷

	dubbo的原理

	zookeeper的选举

	mysql的优化

	手撕代码：单例模式

			 设计一个queue队列



微鲤科技：

	zookeeper实现读写锁

	string和stringbuilder和stringbuffer的区别

	zk的选举

	redis锁

	从1亿个数据中找出唯一一个没出过的数据（位图   桶排序）



陌陌：

	redis主从复制的网络中断   怎么同步

	mysql的优化

	归并排序

	双向链表put操作

	mysql的事物

	for update



	



去哪儿网：

	redis的master-slave的分片 

	paoxs与zab的区别  

	zk的选举

	dubbo的原理

	手撕代码：

		单链表是否有环

		找出入环的第一个节点

		统计入环的总结点数

		写一个死锁案例

	场景题： 内存泄漏的思路及排查

	spring的注解





阿里巴巴：

	dubbo的负载均衡及路由

	单链表的冒泡排序

	手写一个spring aop   jdk自带的类

	hashmap为什么用红黑树 不用avl

	线程池如何知道一个线程执行完了任务



玩吧：

	mysql优化

	设计一个系统

	hashmap的put方法

	扩容

	hashmap线程不安全的原因

	为什么是2的整数次幂

	hashmap的迭代过程中put数据会发生什么

	手写单例模式

	给定一个表   设计索引

	dubbo的原理





滴滴：

	如何实现一个死锁

	jvm

	gc



1.final finally finalize区别 

2.Hashmap结构是什么样？jdkl.8对它做了哪些优化 

3.你知道的平衡二叉树有哪些 

4.你知道的锁有哪几种类型?这几种类型的特点是啥?

5.简单实现AOP， 请使用AOP实现统计函数运行时间？

6.jvm内存模型具体有哪些区域， 说说他们的作用？

7．思考题 

   有10亿个杂乱无章的数，怎样最快地求出其中前1000大的数。 
实现冒泡排序、二分杳找 
请用循环和递归实现二叉树的左右树转换
请实现一个线程安全的单例模式 
请用0(n)的效率 ，实现删除链表的倒数第n个元素