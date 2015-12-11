# 说明文档

主要用于课程 Code Review 环节的讲解.

## Principles

### Occam's Razor
 
如无必要, 勿增实体. 

在 int 和 String 之间转换时, 我们会很自然的使用:

```
int someInt = Integer.parseInt("1234");
String someString = String.valueOf(someInt); // In more language: someInt.toString();
```

而不是:

```
IntegerParser integerParser = new IntegerParser();
int someInt = integerParser.parseInt("1234");
IntegerStringifier integerStringifier = new IntegerStringifier();
String someString = integerStringifier.stringify(someInt);
```

单一职责的初衷是好的, 单一职责的滥用是可怕的.

### Floating Number

`有限字长效应` 是无限精度数值的计算机存储问题, 在这里, 是结果, 而不是原因.

问题的本质是为什么在十进制下明明非常有限的数值到二进制下会变成无限?

// Todo 


