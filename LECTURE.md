# 说明文档

主要用于课程 Code Review 环节的讲解.

## Philosophy

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

其实, 问题很简单, 我们小学就已经学过, 有的有理数可以表示成有限小数, 有的有理数不能.

只是, 小学的时候并没有说, 对不同的进制, 可以表示成有限小数的那些数是不一样的.

比如十进制下的 `1/3`, 在三进制下就是 `0.1`.

碰巧因为 10 = 2 * 5, 所以二进制下的有限位转换到十进制也一定是有限位, 但转回去就没有这么巧了.

## Problem

### 人格分裂

基本所有人的商品价格都用的是 `double`, 同时, 基本所有人的价格匹配都用的是 `\d+`, 所以? 凡碰到正则先盯着正则思考5分钟.

### O(n^2)

这个之前说过了, 略过.

