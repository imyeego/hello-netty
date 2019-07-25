## 基于netty的项目合集

#### Features

- 剑指Offer和部分leetcode算法实现，该leetcode部分代码全部被accepted
- 基于netty helloworld的程序
- 基于netty 的聊天和推送console程序
- 数据传输格式采用protobuf(netty-hello模块),支持跨语言(python测试通过)，支持多种protobuf实例类型
- 数据传输格式支持自定义，序列化/反序列化支持json(python测试通过)
- 实现了一个JSON解析器，支持对象与列表的解析和对象的序列化
    1. 增加对象内嵌套List解析与序列化
    2. reader与writer支持java.io流
    3. 增加注解配置